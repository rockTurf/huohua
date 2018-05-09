package com.srj.web.datacenter.model;

import java.io.Serializable;

import com.srj.common.base.BaseEntity;

public class AccountWallet extends BaseEntity implements Serializable{
	
	/**
	 * 用户钱包实体类
	 */
	private static final long serialVersionUID = 1L;
	
	private String wallet_addr;
	private String user_id;
	private String appid;
	
	
	
	public String getWallet_addr() {
		return this.getString("walletAddr");
	}
	public void setWallet_addr(String wallet_addr) {
		this.set("wallet_addr", wallet_addr);
	}
	public String getUser_id() {
		return this.getString("userId");
	}
	public void setUser_id(String user_id) {
		this.set("user_id", user_id);
	}
	public String getAppid() {
		return this.getString("appid");
	}
	public void setAppid(String appid) {
		this.set("appid", appid);
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
