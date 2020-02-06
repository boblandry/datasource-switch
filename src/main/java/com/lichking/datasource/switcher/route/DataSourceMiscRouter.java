package com.lichking.datasource.switcher.route;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSourceMiscRouter extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContext.get() + "_misc";
    }
}
