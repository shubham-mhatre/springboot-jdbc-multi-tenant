package com.sm.mt.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.sm.mt.entity.TenantMaster;
import com.sm.mt.service.TenantService;
import com.sm.mt.utils.DynamicDatasourceRouter;

@Configuration
public class DataSourceConfig {
	
	private final ObjectProvider<TenantService> tenantServiceProvider;

	
	public DataSourceConfig(ObjectProvider<TenantService> tenantServiceProvider) {
        this.tenantServiceProvider = tenantServiceProvider;
    }
	
	@Bean(name = "masterDataSource")
    public DataSource masterDataSource() {
        DriverManagerDataSource masterDataSource = new DriverManagerDataSource();
        masterDataSource.setUrl("jdbc:mysql://localhost:3306/master_db_tenant");
        masterDataSource.setUsername("root");
        masterDataSource.setPassword("root");
        masterDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return masterDataSource;
    }
	
	@Bean(name = "multiTenantDataSource")
	public DataSource dataSource() {
		DynamicDatasourceRouter router=new DynamicDatasourceRouter();
		
		DataSource  masterDataSource=masterDataSource();
		
		TenantService tenantService = tenantServiceProvider.getIfAvailable();
        List<TenantMaster> tenants = tenantService != null ? tenantService.getAllTenants() : List.of();
        
        Map<Object,Object> dataSources=new HashMap<>();
        for (TenantMaster tenant : tenants) {
            DriverManagerDataSource tenantDataSource = new DriverManagerDataSource();
            tenantDataSource.setUrl(tenant.getUrl());
            tenantDataSource.setUsername(tenant.getUsername());
            tenantDataSource.setPassword(tenant.getPassword());
            tenantDataSource.setDriverClassName(tenant.getDriverClassName());
            dataSources.put(tenant.getTenantId(), tenantDataSource);
        }
		
		
		dataSources.put("master", masterDataSource);
		

		
		router.setDefaultTargetDataSource(masterDataSource);
		router.setTargetDataSources(dataSources);
		
		return router;
	}

}
