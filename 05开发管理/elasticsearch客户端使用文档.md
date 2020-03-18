# elasticsearch客户端使用文档

<a name="bb283989"></a>
## 1.说明
elasticsearch的Java客户端分为两种：<br />1.Java api，通过9300端口通信。有两种使用方式ElasticSearchRepository和ElasticTemplate。<br />2.rest客户端，通过9200端口通信。有rest-high-level和rest-low-level。<br />

<a name="gGJ7j"></a>
## 2. Java api

<a name="sB1lj"></a>
### 2.1 pom.xml 依赖

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
</dependency>
```
注，elasticsearch的客户端版本一定要和elasticsearch服务匹配，否则会出现连不上问题

| Spring Data Elasticsearch | Elasticsearch |
| --- | --- |
| 3.2.x | 6.7.2 |
| 3.1.x | 6.2.2 |
| 3.0.x | 5.5.0 |
| 2.1.x | 2.4.0 |
| 2.0.x | 2.2.0 |
| 1.3.x | 1.5.2 |

<a name="amos6"></a>
### 2.2 环境配置
src/main/resources 目录下 appliaction.properties 配置文件内容如下：

```
server.port=8888

spring.data.elasticsearch.repositories.enabled=true
spring.data.elasticsearch.cluster-nodes=39.100.79.132:9300,39.100.79.132:9301,39.100.79.132:9302
spring.data.elasticsearch.cluster-name=elasticsearch-cluster

```

<a name="U8zGu"></a>
### 2.3 使用示例

<a name="M7y8r"></a>
#### 2.3.1 使用ElasticsearchRepository

```java
@RestController
@RequestMapping("/repository")
public class EmployeeRepositoryController {
    @Resource
    private EmployRepository employRepository;

    /**
     * 复杂查询的常用流程：分页+排序+匹配
     * 查询员工信息
     *
     * @param page          第几页
     * @param size          每页多少条
     * @param searchContent 搜索内容
     * @return List
     */
    @GetMapping("/search")
    public List<Employee> search(@RequestParam(value = "page") Integer page,
                                 @RequestParam(value = "size") Integer size,
                                 @RequestParam(value = "searchContent") String searchContent) {

        // 1.创建分页参数，springboot2.0中通过构造方法创建的方式已经废弃
        Pageable pageable = PageRequest.of(page, size);

        // 2.创建排序，根据id倒叙排序
        FieldSortBuilder sort = SortBuilders.fieldSort("id").order(SortOrder.DESC);

        // 3.创建布尔查询，content字段包含searchContent
        BoolQueryBuilder contentBuilder = QueryBuilders.boolQuery();
        /* contentBuilder.should(QueryBuilders.term("type", searchContent)); */
        contentBuilder.should(QueryBuilders.matchQuery("content", searchContent));

        // 4.创建DSL查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder().
                withQuery(contentBuilder).
                withSort(sort).
                withPageable(pageable).
                build();

        // 5.查询
        Page<Employee> employees = employRepository.search(searchQuery);
        return employees.getContent();
    }

    /**
     * 复杂查询的常用流程：范围查询+多条件
     * 查询员工信息
     *
     * @param searchContent 搜索内容
     * @return List
     */
    @GetMapping("/search1")
    public List<Employee> search1(@RequestParam(value = "searchContent") String searchContent) {

        // 1.条件一，范围查询，id大于等于2的
        BoolQueryBuilder contentBuilder = QueryBuilders.boolQuery();
        contentBuilder.must(QueryBuilders.rangeQuery("id").gte(2));

        // 2.增加条件二，content包含searchContent
        contentBuilder.must(QueryBuilders.matchQuery("content", searchContent));
        // 3.创建DSL查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder().
                withQuery(contentBuilder).
                build();

        // 4.查询
        Page<Employee> employees = employRepository.search(searchQuery);
        return employees.getContent();
    }

    /**
     * 复杂查询的常用流程：模糊查询
     * 查询员工信息
     *
     * @param searchContent 搜索内容
     * @return List
     */
    @GetMapping("/search2")
    public List<Employee> search2(@RequestParam(value = "searchContent") String searchContent) {

        BoolQueryBuilder contentBuilder = QueryBuilders.boolQuery();
        contentBuilder.must(QueryBuilders.wildcardQuery("type", "*" + searchContent + "*"));

        SearchQuery searchQuery = new NativeSearchQueryBuilder().
                withQuery(contentBuilder).
                build();

        Page<Employee> employees = employRepository.search(searchQuery);
        return employees.getContent();
    }

    /**
     * 复杂查询的常用流程：聚合查询
     * 对文档的name字段进行聚合查询
     *
     * @return List
     */
    @GetMapping("/search3")
    public List<String> search3() {
        List<String> list = new ArrayList<>();

        // 1.matchAllQuery查询所有，相当于不设置查询条件
        MatchAllQueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();

        // 2.聚合，对字段"name"进行聚合，聚合出来的名字叫"sum"
        TermsAggregationBuilder tab = AggregationBuilders.
                terms("sum").
                field("name").
                order(Terms.Order.count(false));

        // 3.构建查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder().
                withQuery(matchAllQuery).
                withSearchType(SearchType.QUERY_THEN_FETCH).
                // 指定index和type
                withIndices("inf").
                withTypes("employee").
                addAggregation(tab).
                build();

        Page<Employee> employees = employRepository.search(searchQuery);
        List<Employee> content = employees.getContent();
        for (Employee employee : content) {
            list.add(employee.getName());
        }
        return list;
    }

    /**
     * 复杂查询的常用流程：多字段匹配查询+高亮字段
     * 注，目前测试使用ElasticsearchRepository时返回的json中没有高亮
     *
     * @return List
     */
    @GetMapping("/search4")
    public Page<Employee> search4(@RequestParam String searchContent) {

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(searchContent,"type", "name");

        // 1.高亮提示
        HighlightBuilder.Field highlightField = new HighlightBuilder.Field("type")
                .preTags("<a>")
                .postTags("</a>");

        // 2.构建查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder().
                withQuery(multiMatchQueryBuilder).
                withHighlightFields(highlightField.preTags("</a>")).
                build();
        Page<Employee> employees = employRepository.search(searchQuery);
        return employees;
    }

    /**
     * 索引一个文档
     *
     * @return Object
     */
    @PutMapping("/create")
    public Object create(@RequestBody Employee employee) {
        return employRepository.index(employee);
    }

    /**
     * 根据id判断文档是否存在
     *
     * @return Object
     */
    @GetMapping("/exists/{id}")
    public Object exists(@PathVariable String id) {
        return employRepository.existsById(Long.valueOf(id));
    }

    /**
     * 根据id查找文档
     *
     * @return Object
     */
    @GetMapping("/get/{id}")
    public Object get(@PathVariable String id) {
        return employRepository.findById(Long.valueOf(id));
    }

    /**
     * 自定义方法，根据name属性查找文档
     *
     * @return Object
     */
    @GetMapping("/get/name/{name}")
    public Object getByName(@PathVariable String name) {
        return employRepository.findByName(name);
    }

    /**
     * 更新文档
     *
     * @return Object
     */
    @PutMapping("/update")
    public Object update(@RequestBody Employee employee) {
        return employRepository.save(employee);
    }

    /**
     * 删除文档
     *
     * @return Object
     */
    @DeleteMapping("/delete/{id}")
    public Object delete(@PathVariable String id) {
        employRepository.deleteById(Long.valueOf(id));
        return true;
    }

    /**
     * refresh
     *
     * @return Object
     */
    @PostMapping("/refresh")
    public Object refresh() {
        employRepository.refresh();
        return true;
    }
}

```

<a name="hlou8"></a>
#### 2.3.2 使用ElasticsearchTemplate（和ElasticRepository大同小异，这里只列举了一个例子）

```java
RestController
@RequestMapping("/template")
public class EmployeeTemplateController {
    @Resource
    ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 复杂查询的常用流程：分页+匹配
     * 查询员工信息
     *
     * @param page          第几页
     * @param size          每页多少条
     * @param searchContent 搜索内容
     * @return List
     */
    @GetMapping("/search")
    public List<Employee> search(@RequestParam(value = "page") Integer page,
                                 @RequestParam(value = "size") Integer size,
                                 @RequestParam(value = "searchContent") String searchContent) {

        // 1.创建分页参数，springboot2.0中通过构造方法创建的方式已经废弃
        Pageable pageable = PageRequest.of(page, size);

        // 2.匹配查询
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("content", searchContent);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().
                withQuery(queryBuilder).
                withPageable(pageable).
                build();

        return elasticsearchTemplate.queryForList(searchQuery, Employee.class);
    }
}
```

<a name="mw8KP"></a>
## 3.rest api
rest-high-level封装很好，但是有少许api不完善。rest-low-level需要自己封装，但是api完善。这里介绍rest-high-level的配置和使用
<a name="qdfeU"></a>
### 3.1 pom.xml依赖
```java
        <dependency>
            <groupId>org.elasticsearch.client</groupId>
            <artifactId>elasticsearch-rest-high-level-client</artifactId>
            <version>6.6.1</version>
        </dependency>
```

<a name="pM2cC"></a>
### 3.2 配置
```java
@Configuration
public class ElasticsearchHighRestConfig {
    /**
     * 集群地址，多个用,隔开
     **/
    private static final String HOSTS = "39.100.79.132:9200,39.100.79.132:9201,39.100.79.132:9202";
    /**
     * 使用的协议
     **/
    private static final String SCHEMA = "http";
    private static ArrayList<HttpHost> hostList;
    /**
     * 连接超时时间
     **/
    private static int connectTimeOut = 1000;
    /**
     * 连接超时时间
     **/
    private static int socketTimeOut = 30000;
    /**
     * 获取连接的超时时间
     **/
    private static int connectionRequestTimeOut = 500;
    /**
     * 最大连接数
     **/
    private static int maxConnectNum = 100;
    /**
     * 最大路由连接数
     **/
    private static int maxConnectPerRoute = 100;

    static {
        hostList = new ArrayList<>();
        String[] addresses = HOSTS.split(",");
        for (String address : addresses) {
            String[] host = address.split(":");
            hostList.add(new HttpHost(host[0], Integer.valueOf(host[1]), SCHEMA));
        }
    }

    @Bean
    public RestHighLevelClient client() {
        RestClientBuilder builder = RestClient.builder(hostList.toArray(new HttpHost[0]));
        // 异步httpclient连接延时配置
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(connectTimeOut);
            requestConfigBuilder.setSocketTimeout(socketTimeOut);
            requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
            return requestConfigBuilder;
        });
        // 异步httpclient连接数配置
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(maxConnectNum);
            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
            return httpClientBuilder;
        });
        return new RestHighLevelClient(builder);
    }
}
```

<a name="Aa6s0"></a>
### 3.3 使用示例

```java
@RestController
@RequestMapping("/rest-high")
public class RestHighLevelController {
    @Resource
    private RestHighLevelClient client;

    /**
     * 复杂查询的常用流程：分页+匹配
     * 查询员工信息
     *
     * @param page          第几页
     * @param size          每页多少条
     * @param searchContent 搜索内容
     * @return List
     */
    @GetMapping("/search")
    public Object search(@RequestParam(value = "page") Integer page,
                         @RequestParam(value = "size") Integer size,
                         @RequestParam(value = "searchContent") String searchContent) throws IOException, JSONException {

        // 1.匹配查询
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        boolBuilder.must(QueryBuilders.matchQuery("content", searchContent));
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolBuilder);

        // 2.分页参数
        sourceBuilder.from(page);
        sourceBuilder.size(size);

        // 3.指定索引和类型
        SearchRequest searchRequest = new SearchRequest("inf");
        searchRequest.types("employee");
        searchRequest.source(sourceBuilder);

        // 4.搜索
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        return new JSONObject(String.valueOf(response));
    }
}

```

