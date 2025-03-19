package com.sm.mt.entity;

import lombok.Data;

@Data
public class TenantMaster {

	private Integer id;
	private String tenantId;
	private String dbName;
	private String url;
	private String username;
	private String password;
	private Integer isActive;
}
