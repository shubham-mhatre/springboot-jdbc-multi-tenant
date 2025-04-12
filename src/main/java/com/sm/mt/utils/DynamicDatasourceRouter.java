package com.sm.mt.utils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;

public class DynamicDatasourceRouter extends AbstractRoutingDataSource {

    private Map<Object, Object> targetDataSources;

    public static void setTenantId(String tenantId) {
        RequestContextHolder.getRequestAttributes().setAttribute("TENANT_ID", tenantId, RequestAttributes.SCOPE_REQUEST);
    }

    public static String getTenantId() {
        Object tenantId = RequestContextHolder.getRequestAttributes().getAttribute("TENANT_ID", RequestAttributes.SCOPE_REQUEST);
        return tenantId != null ? tenantId.toString() : null;
    }

    public static void clearTenantId() {
        RequestContextHolder.getRequestAttributes().removeAttribute("TENANT_ID", RequestAttributes.SCOPE_REQUEST);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String tenantId = getTenantId();

        if (tenantId == null || !targetDataSources.containsKey(tenantId)) {
            System.out.println("Tenant ID not found: " + tenantId + ". Using default tenant database.");
            return "default";  // Fallback to the default datasource
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
