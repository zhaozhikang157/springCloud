package com.elasticsearch.utils;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.elasticsearch.constants.ElasticsearchContants;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ElasticsearchUtils {

    @Autowired
    private ElasticsearchContants elasticsearchContants;

    private static TransportClient client;

    @PostConstruct
    private void init(){
        client = elasticsearchContants.initElasticsearch();
    }

    /**
     * 创建索引库
     *
     * @param index
     * @return
     */
    public static boolean createIndex(String index) {
        if(isIndexExist(index)){
            return true;
        }
        CreateIndexResponse createIndexResponse = client.admin().indices().prepareCreate(index).execute().actionGet();
        return createIndexResponse.isAcknowledged();
    }

    /**
     * 创建复杂索引，包括初始化类型，mapping
     * @param index
     * @param type
     * @param mapping  json数据字符串
     * @return
     */
    public static boolean createIndex(String index, String type, XContentBuilder mapping){
        if(isIndexExist(index)){
            return true;
        }
        //umber_of_replicas 是数据备份数，如果只有一台机器，设置为0
        //number_of_shards  是数据分片数，默认为5，有时候设置为3
        Settings settings = Settings.builder().put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 0).build();
        PutMappingResponse putMappingResponse = client.admin().indices()
                .preparePutMapping(index)
                .setType(type)// setting
                .setSource(mapping)// type,mapping
                .get();
        return putMappingResponse.isAcknowledged();

    }

    /**
     * 删除索引库
     * @param index
     * @return
     */
    public static boolean delIndex(String index) {
        if(!isIndexExist(index)){
            return true;
        }
        AcknowledgedResponse acknowledgedResponse = client.admin().indices().prepareDelete(index).execute().actionGet();
        return acknowledgedResponse.isAcknowledged();
    }

    /**
     * 索引库是否存在
     * @param index
     * @return
     */
    public static boolean isIndexExist(String index){
        IndicesExistsResponse indicesExistsResponse = client.admin().indices().exists(new IndicesExistsRequest(index)).actionGet();
        return indicesExistsResponse.isExists();
    }

    /**
     * 数据添加，id自定义
     * @param jsonObject
     * @param index
     * @param type
     * @param id
     * @return
     */
    public static String addData(JSONObject jsonObject, String index, String type, String id){
        IndexResponse indexResponse = client.prepareIndex(index, type, id).setSource(jsonObject).get();
        return indexResponse.getId();
    }

    /**
     * 数据添加，id默认
     * @param jsonObject
     * @param index
     * @param type
     * @return
     */
    public static String addData(JSONObject jsonObject, String index, String type){
        IndexResponse indexResponse = client.prepareIndex(index, type).setSource(jsonObject).get();
        return indexResponse.getId();
    }

    /**
     * 通过 index，type,id 删除数据
     * @param index
     * @param type
     * @param id
     */
    public static void delDataById(String index, String type,String id){
        client.prepareDelete(index, type, id).execute().actionGet();
    }

    /**
     * 通过 index，type,id 修改数据
     * @param index
     * @param type
     * @param id
     */
    public static void updateDataById(JSONObject jsonObject,String index, String type,String id){
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(index).type(type).id(id).doc(jsonObject);
        ActionFuture<UpdateResponse> update = client.update(updateRequest);
    }

    /**
     * 通过 index，type,id 查询数据
     * @param index
     * @param type
     * @param id 需要显示的字段，逗号分隔（不传则展示全部字段）
     */
    public static Map<String, Object> queryDataById(String index, String type, String id, String fields){
        GetRequestBuilder getRequestBuilder = client.prepareGet(index, type, id);
        if(!StringUtils.isEmpty(fields)){
            getRequestBuilder.setFetchSource(fields.split(","),null);
        }
        GetResponse documentFields = getRequestBuilder.execute().actionGet();
        return documentFields.getSource();
    }

    /**
     *
     * @param index
     * @param type  类型名称,可传入多个type逗号分隔
     * @param query   查询条件
     * @param size  需要查询出多少条结果
     * @param fields 需要显示的字段，逗号分隔（不传则展示全部字段）
     * @param sortField 排序字段
     * @param sortType 排序方式 asc desc
     * @return
     */
    public static List<Map<String, Object>> searchDataList(String index, String type, QueryBuilder query, Integer size, String fields, String sortField,  SortOrder sortType){
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
        //设置查询的 type
        if (!StringUtils.isEmpty(type)) {
            searchRequestBuilder.setTypes(type.split(","));
        }
        //设置查询条件
        searchRequestBuilder.setQuery(query);
        if (!StringUtils.isEmpty(fields)) {
            searchRequestBuilder.setFetchSource(fields.split(","),null);
        }
        searchRequestBuilder.setFetchSource(true);
        //设置排序
        if (!StringUtils.isEmpty(sortField)) {
            searchRequestBuilder.addSort(sortField, sortType);
        }
        //文档大小限制
        if(size != null && size > 0){
            searchRequestBuilder.setSize(size);
        }

        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

        //共查询的数据数量
        long totalSize = searchResponse.getHits().totalHits;
        //处理的数据数量
        long dealSize = searchResponse.getHits().totalHits;
        System.out.println("共查询到[" + totalSize + "]条数据,处理数据条数[" + dealSize + "]");
        if (searchResponse.status().getStatus() == 200) {
            return setSearchResponse(searchResponse);
        }
        return null;
    }

    /**
     * 将查询结果返回为list<Map>
     * @param searchResponse
     * @return
     */
    private static List<Map<String, Object>> setSearchResponse(SearchResponse searchResponse){
        List<Map<String, Object>> sourceList = new ArrayList<Map<String, Object>>();
        StringBuffer stringBuffer = new StringBuffer();

        for (SearchHit searchHit : searchResponse.getHits().getHits()) {
            searchHit.getSourceAsMap().put("id", searchHit.getId());
            sourceList.add(searchHit.getSourceAsMap());
        }

        return sourceList;
    }
}
