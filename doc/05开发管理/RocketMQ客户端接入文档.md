# RocketMQ客户端接入文档

<a name="mojU1"></a>
# 1. 前提条件
- 已经正确部署了rocketMQ集群
- 新建Springboot应用

<a name="dOOn2"></a>
# 2. 添加客户端Maven依赖

```
        <dependency>
            <groupId>com.deepexi.inf</groupId>
            <artifactId>deepexi-rocketmq-client</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
```
<a name="7PeeY"></a>
# 3. 配置
<a name="kn7Mo"></a>
## 3.1 配置nameserver
在application.properties中加入如下配置：

```
rocketmq.name-server=39.100.104.49:9876;39.100.104.49:9877
```
nameserver按部署情况配置，配置集群中所有的nameserver实例，多个按分号隔开。
<a name="ignS5"></a>
## 3.2 配置生产者组
在application.properties中加入如下配置：

```
rocketmq.producer.group=my-group1
```

其他配置项比如rocketmq.producer.sendMessageTimeout可选。

<a name="rgDX7"></a>
# 4. 生产消息
<a name="KD4zD"></a>
## 4.1 注入RocketMQTemplate

```
 @Resource
    private RocketMQTemplate rocketMQTemplate;
```

<a name="4uazO"></a>
## 4.2 发送同步消息

```
 SendResult sendResult = rocketMQTemplate.syncSend(topic, "Hello, World!");
```

<a name="OKO06"></a>
## 4.3 发送异步消息

```
        rocketMQTemplate.asyncSend(orderPaidTopic, new OrderPaidEvent("T_001", new BigDecimal("88.00")), new SendCallback() {
            @Override
            public void onSuccess(SendResult var1) {
                LOGGER.info(String.format("async onSucess SendResult=%s %n", var1));
            }

            @Override
            public void onException(Throwable var1) {
                LOGGER.info("async onException Throwable=%s %n", var1);
            }

        });
```

<a name="nt69T"></a>
## 4.4 发送顺序消息
假设要发送10条顺序消息：

```
    private void sendOrderlyMessageDemo() throws InterruptedException {
        int orderlyMessageCount = 10;
        for (int i = 0; i < orderlyMessageCount; i++) {
            OrderPaidEvent order =  new OrderPaidEvent(String.valueOf(i), new BigDecimal("88.00"));
            rocketMQTemplate.syncSendOrderly(orderPaidTopic,order,"0");
        }
    }
```

rocketMQTemplate.syncSendOrderly(orderPaidTopic,order,"0");这一行代码中的参数“0”会被用来计算hashcode，根据此hashcode决定将消息发送到某个队列，hashcode相同的消息都会被顺序发送。
<a name="pAjBg"></a>
## 4.5 批量发送消息

```
    private void batchMessagesDemo() {
        List<Message> msgs = new ArrayList<>();
        int batchMessagesCount = 10;
        for (int i = 0; i < batchMessagesCount; i++) {
            msgs.add(MessageBuilder.withPayload("Hello RocketMQ Batch Msg#" + i).
                    setHeader(RocketMQHeaders.KEYS, "KEY_" + i).build());
        }

        SendResult sr = rocketMQTemplate.syncSend(springTopic, msgs, 60000);

        LOGGER.info("--- Batch messages send result :" + sr);
    }
```

<a name="xrtUc"></a>
## 4.6 发送事务消息
<a name="c5OkB"></a>
### 4.6.1 发送事务消息

```
private void transactionMessageDemo() throws MessagingException {
        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
    int transactionMessageCount = 10;
        for (int i = 0; i < transactionMessageCount; i++) {
            try {

                Message msg = MessageBuilder.withPayload("Hello RocketMQ " + i).
                        setHeader(RocketMQHeaders.TRANSACTION_ID, "KEY_" + i).build();
                SendResult sendResult = rocketMQTemplate.sendMessageInTransaction(transactionProducerGroup,
                        springTransTopic + ":" + tags[i % tags.length], msg, null);

                LOGGER.info(String.format("------ send Transactional msg body = %s , sendResult=%s %n",
                        msg.getPayload(), sendResult.getSendStatus()));

                Thread.sleep(10);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(),e);
            }
        }
    }
```

<a name="Xrjqf"></a>
### 4.6.2 注册本地事务监听器

```
@RocketMQTransactionListener(txProducerGroup = "${demo.rocketmq.transaction.producer.group}")
class TransactionListenerImpl implements RocketMQLocalTransactionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionListenerImpl.class);

    private AtomicInteger transactionIndex = new AtomicInteger(0);

    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        String transId = (String)msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        LOGGER.info(String.format("#### executeLocalTransaction is executed, msgTransactionId=%s %n",
                transId));
        int value = transactionIndex.getAndIncrement();
        int status = value % 3;
        if (transId == null)
        {
            return RocketMQLocalTransactionState.UNKNOWN;
        }
        localTrans.put(transId, status);
        if (status == 0) {
            // Return local transaction with success(commit), in this case,
            // this message will not be checked in checkLocalTransaction()
            LOGGER.info(String.format("    # COMMIT # Simulating msg %s related local transaction exec succeeded! ### %n", msg.getPayload()));
            return RocketMQLocalTransactionState.COMMIT;
        }

        if (status == 1) {
            // Return local transaction with failure(rollback) , in this case,
            // this message will not be checked in checkLocalTransaction()
            LOGGER.info(String.format("    # ROLLBACK # Simulating %s related local transaction exec failed! %n", msg.getPayload()));
            return RocketMQLocalTransactionState.ROLLBACK;
        }

        LOGGER.info("    # UNKNOW # Simulating %s related local transaction exec UNKNOWN! \n");
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        String transId = (String)msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        RocketMQLocalTransactionState retState = RocketMQLocalTransactionState.COMMIT;
        if (transId == null)
        {
            return RocketMQLocalTransactionState.UNKNOWN;
        }
        Integer status = localTrans.get(transId);
        if (null != status) {
            switch (status) {
                case 1:
                    retState = RocketMQLocalTransactionState.COMMIT;
                    break;
                case 2:
                    retState = RocketMQLocalTransactionState.ROLLBACK;
                    break;
                case 0:
                default:
                        retState = RocketMQLocalTransactionState.UNKNOWN;

            }
        }
        LOGGER.info(String.format("------ !!! checkLocalTransaction is executed once," +
                        " msgTransactionId=%s, TransactionState=%s status=%s %n",
                transId, retState, status));
        return retState;
    }

```


<a name="eRcwa"></a>
# 5 消费消息
<a name="i44fX"></a>
## 5.1  接收顺序消息

```
@Service
@RocketMQMessageListener(topic = "${demo.rocketmq.orderTopic}", consumerGroup = "order-paid-service",consumeMode = ConsumeMode.ORDERLY)
public class OrderPaidEventConsumer implements RocketMQListener<OrderPaidEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderPaidEventConsumer.class);

    @Override
    public void onMessage(OrderPaidEvent message) {
        LOGGER.info(String.format("message received: %s \n", message.getOrderId()));
    }
}
```
注意：

1. 发送和接收的topic要相同且要顺序接收的消息必须顺序发送
1. 顺序发送的消息要想顺序接收，必须指定consumeMode = ConsumeMode.ORDERLY

<a name="ipXfb"></a>
## 5.2 其他类型消息的消费
其他类型的消息消费大致相同，需要与生产者保持一致的topic，然后指定消费者组。

<a name="80WPB"></a>
# 6 消息轨迹
通过以下配置开启生产者、消费者的消息轨迹：

```
rocketmq.producer.enable-msg-trace=true
rocketmq.consumer.enable-msg-trace=true
```

开启后，可在控制页面的“消息轨迹”下面安装messageId或者key查询消息，并查看消息轨迹。

<a name="7LFRS"></a>
# 7 ACL
在消息生产端配置消息队列的认证信息

```
rocketmq.producer.access-key=AK
rocketmq.producer.secret-key=SK
```

然后消费者端输入同样的认证信息：

```
    @RocketMQTransactionListener(
        txProducerGroup = TX_PGROUP_NAME,
        accessKey = "AK", // if not setting, it will read by `rocketmq.producer.access-key` key
        secretKey = "SK"  // if not setting, it will read by `rocketmq.producer.secret-key` key
        )
```

也可以在配置文件中统一配置认证信息：
```
rocketmq.producer.access-key=AK
rocketmq.producer.secret-key=SK
```

