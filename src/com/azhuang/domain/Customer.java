package com.azhuang.domain;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;


public class Customer {

//	  `cust_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '客户编号(主键)',
//	  `cust_name` varchar(32) NOT NULL COMMENT '客户名称(公司名称)',
//	  `cust_user_id` bigint(32) DEFAULT NULL COMMENT '负责人id',
//	  `cust_create_id` bigint(32) DEFAULT NULL COMMENT '创建人id',
//	  `cust_source` varchar(32) DEFAULT NULL COMMENT '客户信息来源',
//	  `cust_industry` varchar(32) DEFAULT NULL COMMENT '客户所属行业',
//	  `cust_level` varchar(32) DEFAULT NULL COMMENT '客户级别',
//	  `cust_linkman` varchar(64) DEFAULT NULL COMMENT '联系人',
//	  `cust_phone` varchar(64) DEFAULT NULL COMMENT '固定电话',
//	  `cust_mobile` varchar(16) DEFAULT NULL COMMENT '移动电话',
//	  PRIMARY KEY (`cust_id`)

	private Long cust_id;
	private String cust_name;
	private String cust_user_id;
	private String cust_create_id;
	
//	private String cust_source;
//	private String cust_industry;
//	private String cust_level;
	
	private Dict source;
	private Dict industry;
	private Dict level;
	
	private String cust_linkman;
	private String cust_phone;
	private String cust_mobile;
	
	private String filepath;
	// 和联系人配置一对多
	// 默认不把set集合进行json的转换
//	@JSONField(serialize=false)
	private Set<Linkman> linkmans = new HashSet<Linkman>();
	
	public Set<Linkman> getLinkmans() {
		return linkmans;
	}
	public void setLinkmans(Set<Linkman> linkmans) {
		this.linkmans = linkmans;
	}
	public Long getCust_id() {
		return cust_id;
	}
	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_user_id() {
		return cust_user_id;
	}
	public void setCust_user_id(String cust_user_id) {
		this.cust_user_id = cust_user_id;
	}
	public String getCust_create_id() {
		return cust_create_id;
	}
	public void setCust_create_id(String cust_create_id) {
		this.cust_create_id = cust_create_id;
	}
	public Dict getSource() {
		return source;
	}
	public void setSource(Dict source) {
		this.source = source;
	}
	public Dict getIndustry() {
		return industry;
	}
	public void setIndustry(Dict industry) {
		this.industry = industry;
	}
	public Dict getLevel() {
		return level;
	}
	public void setLevel(Dict level) {
		this.level = level;
	}
	public String getCust_linkman() {
		return cust_linkman;
	}
	public void setCust_linkman(String cust_linkman) {
		this.cust_linkman = cust_linkman;
	}
	public String getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}
	public String getCust_mobile() {
		return cust_mobile;
	}
	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	
	
}
