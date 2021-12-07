package com.study.hard.config.DoubleDataSourceConfig;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

        /*DynamicDataSource是我们自定义的动态切换数据源的类,该类继承了AbstractRoutingDataSource 类并重写了它的determineCurrentLookupKey()方法
        AbstractRoutingDataSource 类内部维护了一个名为targetDataSources的Map，并提供的setter方法用于设置数据源关键字与数据源的关系，
        实现类被要求实现其determineCurrentLookupKey()方法，由此方法的返回值决定具体从哪个数据源中获取连接。
        同时AbstractRoutingDataSource类提供了程序运行时动态切换数据源的方法，在dao类或方法上标注需要访问数据源的关键字，路由到指定数据源，获取连接*/

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType.DataBaseType dataBaseType = DataSourceType.getDataBaseType();
        return dataBaseType;
    }
}
