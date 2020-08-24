import com.alibaba.fastjson.JSONObject;
import com.elasticsearch.ElasticsearchApplication;
import com.elasticsearch.constants.ElasticsearchContants;
import com.elasticsearch.utils.ElasticsearchUtils;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@SpringBootTest(classes = ElasticsearchApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TestJava {

    @Autowired
    ElasticsearchContants elasticsearchContants;

    @Test
    public void elasticsearchTest(){
/*
        ElasticsearchUtils.createIndex("test");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","李四");
        jsonObject.put("city","北京");
        jsonObject.put("age","11");
        jsonObject.put("desc","这是李四的描述");
        ElasticsearchUtils.addData(jsonObject,"test","user","1");
        jsonObject = new JSONObject();
        jsonObject.put("name","王五");
        jsonObject.put("city","上海");
        jsonObject.put("age","21");
        jsonObject.put("desc","王五就是我，我就是隔壁老王");
        ElasticsearchUtils.addData(jsonObject,"test","user");
        ElasticsearchUtils.delDataById("test","user","8fIjC3QBsN7wCGyBwPzu");
        ElasticsearchUtils.updateDataById(jsonObject,"test","user","1");
        System.out.println(JSONObject.toJSON(ElasticsearchUtils.queryDataById("test","user","1","name")).toString());


        //打印结果   [{"name":"王五","id":"8vIqC3QBsN7wCGyBOPw6","city":"上海","age":"21","desc":"王五就是我，我就是隔壁老王"}]
        QueryBuilder queryBuilder = QueryBuilders.commonTermsQuery("name","王");
        System.out.println(JSONObject.toJSON(ElasticsearchUtils.searchDataList("test","user",queryBuilder,null,null,null, null)));

        //打印结果   [{"name":"王五","id":"8vIqC3QBsN7wCGyBOPw6","city":"上海","age":"21","desc":"王五就是我，我就是隔壁老王"}]
        QueryBuilder queryBuilder1 = QueryBuilders.commonTermsQuery("name","王五");
        System.out.println(JSONObject.toJSON(ElasticsearchUtils.searchDataList("test","user",queryBuilder1,null,null,null, null)));*/

        //打印结果  [{"name":"王五","id":"8vIqC3QBsN7wCGyBOPw6","city":"上海","age":"21","desc":"王五就是我，我就是隔壁老王"},{"name":"李四","id":"1","city":"北京","age":"11","desc":"这是李四的描述"}]
//        QueryBuilder queryBuilder2 = QueryBuilders.commonTermsQuery("desc","就是");
//        System.out.println(JSONObject.toJSON(ElasticsearchUtils.searchDataList("test","user",queryBuilder2,100,"name,city","name", SortOrder.DESC)));


//        ElasticsearchUtils.createIndex("test_1");
        System.out.println(ElasticsearchUtils.createIndex("test_1","user_1",getMappering()));
//
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","王五");
        jsonObject.put("city","上海");
        jsonObject.put("age","21");
        jsonObject.put("desc","王五就是我，我就是隔壁老王");
        ElasticsearchUtils.addData(jsonObject,"test_1","user_1","1");
//        ElasticsearchUtils.delIndex("test_1");
//        ElasticsearchUtils.createIndex("test_1");
//        ElasticsearchUtils.createIndex("test_1","user_1",getMappering());
//        QueryBuilder queryBuilder3 = QueryBuilders.commonTermsQuery("desc","就是");
//        System.out.println(JSONObject.toJSON(ElasticsearchUtils.searchDataList("test_1","user_1",queryBuilder3,100,"name,city","city", SortOrder.DESC)));
    }

    private XContentBuilder getMappering(){
        XContentBuilder builder = null;
        try {
            //{"user_1" : {"properties" : {"age" : { "type" : "long"},"city" : {"type" : "text","fields" : {"keyword" : {"type" : "keyword","ignore_above" : 256}},"fielddata":true},"name" : {"type" : "text","fields" : {"keyword" : {"type" : "keyword","ignore_above" : 256}}},"desc" : {"type" : "text","fields" : {"keyword" : {"type" : "keyword","ignore_above" : 256}}}}}}
            builder= XContentFactory.jsonBuilder()
                    .startObject().startObject("user_1").startObject("properties").
                            startObject("age").field("type","long").endObject().
                            startObject("city").field("type","text").field("fielddata","true").endObject().
                            startObject("name").field("type","text").endObject()
            .endObject().endObject().endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder;
    }
}
