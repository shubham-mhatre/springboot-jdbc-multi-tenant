package com.sm.mt.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sm.mt.entity.TenantMaster;

@Service
public class TenantService {

	private final JdbcTemplate jdbcTemplate;
	//@Qualifier("masterDataSource") //instead of qualifier for masterDatasource, made masterDatasource as primary
	public TenantService(DataSource masterDataSource) {
        this.jdbcTemplate = new JdbcTemplate(masterDataSource);
    }
	
	public List<TenantMaster> getAllTenants(){
		String selectAllQuery ="select * from tenant_master";
		List<TenantMaster> tenants= jdbcTemplate.query(selectAllQuery, new BeanPropertyRowMapper<TenantMaster>(TenantMaster.class));
		return tenants;
	}
}
