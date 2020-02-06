package com.lichking.datasource.switcher.route;

public class DataSourceContext {

    private static final ThreadLocal<String> dataSourceKey = new ThreadLocal<>();

    public static String get() {
        return dataSourceKey.get();
    }

    public static void set(String key) {
        dataSourceKey.set(key);
    }

}
