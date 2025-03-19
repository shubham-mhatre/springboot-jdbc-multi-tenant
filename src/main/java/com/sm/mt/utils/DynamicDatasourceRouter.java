package com.sm.mt.utils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDatasourceRouter extends AbstractRoutingDataSource {
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal();
	
	public static void setDataSourceKey(String key) {
		contextHolder.set(key);
	}
	
	public static void clearDataSourceKey() {
		contextHolder.remove();
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return contextHolder.get();
	}
	

}
