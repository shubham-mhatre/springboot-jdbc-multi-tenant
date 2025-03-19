package com.sm.mt.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.sm.mt.utils.DynamicDatasourceRouter;

@Configuration
public class DataSourceConfig {
	
	@Bean
	public DataSource dataSource() {
		DynamicDatasourceRouter router=new DynamicDatasourceRouter();
		
		DriverManagerDataSource masterDataSource=new DriverManagerDataSource();
		masterDataSource.setUrl("jdbc:mysql://localhost:3306/master_db_tenant");
		masterDataSource.setUsername("root");
		masterDataSource.setPassword("root");
		masterDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		
		DriverManagerDataSource tenant1DataSource=new DriverManagerDataSource();
		tenant1DataSource.setUrl("jdbc:mysql://localhost:3306/tenant_1");
		tenant1DataSource.setUsername("root");
		tenant1DataSource.setPassword("root");
		tenant1DataSource.setDriverClassName("com.mysql.jdbc.Driver");
		
		
		DriverManagerDataSource tenant2DataSource=new DriverManagerDataSource();
		tenant2DataSource.setUrl("jdbc:mysql://localhost:3306/tenant_2");
		tenant2DataSource.setUsername("root");
		tenant2DataSource.setPassword("root");
		tenant2DataSource.setDriverClassName("com.mysql.jdbc.Driver");
		
		Map<Object,Object> dataSources=new HashMap<>();
		dataSources.put("master", masterDataSource);
		dataSources.put("tenant1", tenant1DataSource);
		dataSources.put("tenant2", tenant2DataSource);
		
		router.setDefaultTargetDataSource(masterDataSource);
		router.setTargetDataSources(dataSources);
		
		return router;
	}

}
