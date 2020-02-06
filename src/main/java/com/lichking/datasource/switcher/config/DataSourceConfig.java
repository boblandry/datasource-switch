package com.lichking.datasource.switcher.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.lichking.datasource.switcher.route.DataSourceBaseRouter;
import com.lichking.datasource.switcher.route.DataSourceMiscRouter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Value("${local.misc.jdbc.url}")
    private String localMiscUrl;

    @Value("${remote.misc.jdbc.url}")
    private String remoteMiscUrl;

    @Value("${local.base.jdbc.url}")
    private String localBaseUrl;

    @Value("${remote.base.jdbc.url}")
    private String remoteBaseUrl;

    @Bean(name = "dataSource_misc")
    public AbstractRoutingDataSource miscDataSource() throws SQLException {
        Map<Object, Object> targetDataSources = new HashMap<>();
        DruidDataSource local = buildDataSource(localMiscUrl);
        targetDataSources.put("local_misc", local);
        DruidDataSource remote = buildDataSource(remoteMiscUrl);
        targetDataSources.put("remote_misc", remote);
        DataSourceMiscRouter miscRouter = new DataSourceMiscRouter();
        miscRouter.setTargetDataSources(targetDataSources);
        return miscRouter;
    }

    @Bean(name = "dataSource_base")
    public AbstractRoutingDataSource baseDataSource() throws SQLException {
        Map<Object, Object> targetDataSources = new HashMap<>();
        DruidDataSource local = buildDataSource(localBaseUrl);
        targetDataSources.put("local_base", local);
        DruidDataSource remote = buildDataSource(remoteBaseUrl);
        targetDataSources.put("remote_base", remote);
        DataSourceBaseRouter baseRouter = new DataSourceBaseRouter();
        baseRouter.setTargetDataSources(targetDataSources);
        return baseRouter;
    }

    private DruidDataSource buildDataSource(String url) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(2000);
        dataSource.setTimeBetweenEvictionRunsMillis(20000);
        dataSource.init();
        return dataSource;
    }

}
