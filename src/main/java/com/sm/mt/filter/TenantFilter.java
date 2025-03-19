package com.sm.mt.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.sm.mt.utils.DynamicDatasourceRouter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class TenantFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        String tenantId = httpRequest.getHeader("X-Tenant-ID");
        
        if (tenantId != null) {
            DynamicDatasourceRouter.setDataSourceKey(tenantId);
        }
        
        try {
            chain.doFilter(request, response); // Continue processing request
        } finally {
        	DynamicDatasourceRouter.clearDataSourceKey();
        }
		
	}

}
