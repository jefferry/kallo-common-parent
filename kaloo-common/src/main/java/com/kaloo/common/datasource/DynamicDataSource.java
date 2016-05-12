package com.kaloo.common.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * DynamicDataSource是利用spring在应用层实现的 适用场景：
 * (1)一个读数据源一个读写数据源，若有多读且是mysql可以使用mysql驱动机制实现或者使用mycat读写中间件 (2)不分读写的多个数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceHolder.get();
	}

}
