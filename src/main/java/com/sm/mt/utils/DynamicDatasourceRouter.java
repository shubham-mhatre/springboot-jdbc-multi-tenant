package com.sm.mt.utils;

import java.sql.ConnectionBuilder;
import java.sql.SQLException;
import java.sql.ShardingKeyBuilder;

import javax.sql.DataSource;

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

	@Override
	public ConnectionBuilder createConnectionBuilder() throws SQLException {
		// TODO Auto-generated method stub
		return super.createConnectionBuilder();
	}

	@Override
	public ShardingKeyBuilder createShardingKeyBuilder() throws SQLException {
		// TODO Auto-generated method stub
		return super.createShardingKeyBuilder();
	}

	@Override
	protected DataSource determineTargetDataSource() {
		// TODO Auto-generated method stub
		return super.determineTargetDataSource();
	}
	
	

}
