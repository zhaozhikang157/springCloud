package com.elasticsearch.constants;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

@Configuration
public class ElasticsearchContants {

    @Bean
    public TransportClient initElasticsearch() {
        TransportClient transportClient = null;
        try {
            // 配置信息
            Settings esSetting = Settings.builder()
                    .put("cluster.name", "elasticsearch") //集群名字
                    .put("thread_pool.search.size", Integer.parseInt("5"))//增加线程池个数，暂时设为5
                    .build();
            //配置信息Settings自定义
            transportClient = new PreBuiltTransportClient(esSetting);
            TransportAddress transportAddress = new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300);
            transportClient.addTransportAddresses(transportAddress);
        } catch (Exception e) {

        }
        return transportClient;
    }
}
