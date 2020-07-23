/*
package com.sharding.second.config;


import com.alibaba.druid.pool.DruidDataSource;
import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.InlineShardingStrategyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class ShardingConfig {

    @Autowired
    private JdbcConfig jdbcConfig;

    @Bean
    public DataSource dataSource() throws SQLException {
        // 配置真实数据源
        Map<String,DataSource> dataSourceMap = new HashMap();
        dataSourceMap.put("ds0", createDb());
        dataSourceMap.put("ds1", createDb1());
        dataSourceMap.put("ds2", createDb2());
        dataSourceMap.put("ds3", createDb3());

        //分片规则
        TableRuleConfiguration tableRuleConfiguration = userRuleConfig();

        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.getTableRuleConfigs().add(tableRuleConfiguration);//可以有多个
        Properties p = new Properties();
        p.setProperty("sql_show",Boolean.TRUE.toString());

        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap,shardingRuleConfiguration,new ConcurrentHashMap(),p);
        return dataSource;
    }

    private TableRuleConfiguration userRuleConfig(){
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration();
        // 配置user表规则
        tableRuleConfiguration.setLogicTable("t_user");
        tableRuleConfiguration.setActualDataNodes("ds${0..3}.t_user${0..1}");
        tableRuleConfiguration.setKeyGeneratorColumnName("id");
        // 配置分库 + 分表策略
        tableRuleConfiguration.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("id","ds${id % 4}"));
        tableRuleConfiguration.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("sex","t_user${sex % 2}"));
        return tableRuleConfiguration;
    }

    private DruidDataSource createDb(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcConfig.getClassName());
        dataSource.setUrl(jdbcConfig.getUrl());
        dataSource.setUsername(jdbcConfig.getUser());
        dataSource.setPassword(jdbcConfig.getPassword());
        return dataSource;
    }

    private DruidDataSource createDb1(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcConfig.getClassName1());
        dataSource.setUrl(jdbcConfig.getUrl1());
        dataSource.setUsername(jdbcConfig.getUser1());
        dataSource.setPassword(jdbcConfig.getPassword1());
        return dataSource;
    }

    private DruidDataSource createDb2(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcConfig.getClassName2());
        dataSource.setUrl(jdbcConfig.getUrl2());
        dataSource.setUsername(jdbcConfig.getUser2());
        dataSource.setPassword(jdbcConfig.getPassword2());
        return dataSource;
    }

    private DruidDataSource createDb3(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcConfig.getClassName3());
        dataSource.setUrl(jdbcConfig.getUrl3());
        dataSource.setUsername(jdbcConfig.getUser3());
        dataSource.setPassword(jdbcConfig.getPassword3());
        return dataSource;
    }
}
*/
