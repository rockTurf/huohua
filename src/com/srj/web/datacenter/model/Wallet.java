package com.srj.web.datacenter.model;

import java.io.Serializable;

import com.srj.common.base.BaseEntity;

public class Wallet extends BaseEntity implements Serializable{
	
	/**
	 * 企业钱包实体类
	 */
	private static final long serialVersionUID = 1L;
	
	private String addr;
	private String query_pwd;
	private String user_code;
	public String getAddr() {
		return this.getString("addr");
	}
	public void setAddr(String addr) {
		this.set("addr", addr);
	}
	
	public String getQuery_pwd() {
		return this.getString("queryPwd");
	}
	public void setQuery_pwd(String query_pwd) {
		this.set("query_pwd", query_pwd);
	}
	public String getUser_code() {
		return this.getString("userCode");
	}
	public void setUser_code(String user_code) {
		this.set("user_code", user_code);
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
