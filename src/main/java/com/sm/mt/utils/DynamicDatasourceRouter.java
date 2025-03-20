package com.sm.mt.utils;

import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDatasourceRouter extends AbstractRoutingDataSource {
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal();
	private Map<Object, Object> targetDataSources;
	
	public static void setDataSourceKey(String key) {
		contextHolder.set(key);
	}
	
	public static void clearDataSourceKey() {
		contextHolder.remove();
	}

	 @Override
	    protected Object determineCurrentLookupKey() {
	        String tenantId = contextHolder.get();
	        
	        // Check if tenantId exists in targetDataSources
	        if (tenantId == null || !targetDataSources.containsKey(tenantId)) {
	            System.out.println("Tenant ID not found: " + tenantId + "  Using defatult tenant database.");
	            return "defatult"; // Always fallback to defatult
	        }
	        
	        System.out.println("Switching to tenant: " + tenantId);
	        return tenantId;
	    }
	
	@Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        this.targetDataSources = targetDataSources; 
    }
	

}
