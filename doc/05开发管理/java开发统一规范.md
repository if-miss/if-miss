# JAVA开发统一规范

<a name="article-title"></a>
# 
<a name="rSLXa"></a>
# 1 注释
<a name="knzhw"></a>
#### 1.1 类/接口
类或接口需加注释

```java
/**
 * 商品修改审批单
 *
 * @author dengweiyi
 * @date 2019-11-09
 */
@RequestMapping("/commodity/goodsModifyApprovals")
public interface ModifyApprovalApi {
```

文件模版设置：

![image.png](https://cdn.nlark.com/yuque/0/2019/png/318181/1574665735486-68bce372-b45f-4e6f-aeea-d8ac34ae1621.png#align=left&display=inline&height=510&name=image.png&originHeight=1020&originWidth=2620&size=310409&status=done&style=none&width=1310)

快捷键设置：

![image.png](https://cdn.nlark.com/yuque/0/2019/png/318181/1574665689523-9d1b354a-9e81-4cea-9133-8818555a37d3.png#align=left&display=inline&height=505&name=image.png&originHeight=1010&originWidth=2622&size=221679&status=done&style=none&width=1311)

<a name="1fNys"></a>
#### 1.2 接口方法
接口每个方法需加注释，方法间保留一行空格

```java
public interface BanService {
    /**
     * 添加禁售信息
     *
     * @param banDTO dto
     * @return boolean
     */
    boolean addBan(BanDTO banDTO);
```

快捷键设置：

![image.png](https://cdn.nlark.com/yuque/0/2019/png/318181/1574665593568-c6b42895-d3df-4153-8304-ec7fc728b015.png#align=left&display=inline&height=503&name=image.png&originHeight=1006&originWidth=2624&size=214987&status=done&style=none&width=1312)

<a name="jg1JS"></a>
#### 1.3 字段
POJO类每个字段需加注释，字段间保留一行空格

```java
public class BanDO extends SuperEntity {
    /**
     * 品牌编码
     */
    private String brandCode;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 渠道编码
     */
    private String channelCode;
```

快捷键设置：

![image.png](https://cdn.nlark.com/yuque/0/2019/png/318181/1574665765686-8076dde3-5ea7-4207-a31d-adadfdca5e3d.png#align=left&display=inline&height=510&name=image.png&originHeight=1020&originWidth=2626&size=214970&status=done&style=none&width=1313)

<a name="SoGcA"></a>
# 2 空格
与1.3类似，不只是POJO类，其它例如自动注入的Service、Dao、Mapper，建议统一用一行空格隔开，并且类与起始字段间不要加空格

反例：

```java
public class GoodsManagerServiceImpl implements GoodsManagerService {

    @Autowired
    private SpuService spuService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SkuService skuService;
    @Autowired
    private GoodsAttachmentService goodsAttachmentService;
    @Autowired
    private GoodsPropertyValueService goodsPropertyValueService;
    @Autowired
    private GoodsDetailService goodsDetailService;
    @Autowired
    private GoodsConfigRelationshipService goodsConfigRelationshipService;
    @Autowired
    private GoodsConfigInfoService goodsConfigInfoService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private AppRuntimeEnv appRuntimeEnv;
    @Autowired
    private GoodsApprovalService goodsApprovalService;
    @Autowired
    private GoodsApprovalRelationService goodsApprovalRelationService;
```

正例：

```java
public class SkuServiceImpl implements SkuService {
    @Resource
    private SkuDAO skuDAO;

    @Resource
    private SkuLabelRelationDAO skuLabelRelationDAO;

    @Resource
    private SkuAggregateService skuAggregateService;

    @Resource
    private SkuResolveService skuResolveService;
```

<a name="LCbkM"></a>
# 3 未引用
未引用的字段尽量去掉

![image.png](https://cdn.nlark.com/yuque/0/2019/png/318181/1574666368598-d6e8ed9e-0d68-4a4f-bcf1-7b4eae8afaf0.png#align=left&display=inline&height=408&name=image.png&originHeight=816&originWidth=1402&size=237008&status=done&style=none&width=701)

未引用的包尽量去掉

![image.png](https://cdn.nlark.com/yuque/0/2019/png/318181/1574666407735-4a283749-c8f0-4c72-8015-275543848761.png#align=left&display=inline&height=483&name=image.png&originHeight=966&originWidth=1584&size=252329&status=done&style=none&width=792)

