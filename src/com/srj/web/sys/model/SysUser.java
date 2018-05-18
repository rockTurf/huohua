package com.srj.web.sys.model;

import java.io.Serializable;

import com.srj.common.base.BaseEntity;
import com.srj.common.constant.Constant;

public class SysUser  extends BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;//用户名
	private String password;//密码
	private String realname;//应用名或用户真实姓名
	private String cburl;//回调地址
	private String appid;//应用Id
	private String appsecret;//应用秘钥
	private String user_type;//账号性质（企业，用户）
	private String create_time;//创建时间
	private String delFlag;//删除标识
	
	public String getUsername() {
		return this.getString("username");
	}
	public void setUsername(String username) {
		this.set("username",username);
	}
	public String getPassword() {
		return this.getString("password");
	}
	public void setPassword(String password) {
		this.set("password",password);
	}
	public String getUser_type() {
		return this.getString("userType");
	}
	public void setUser_type(String user_type) {
		this.set("user_type",user_type);
	}
	public String getCreate_time() {
		return this.getString("createTime");
	}
	public void setCreate_time(String create_time) {
		this.set("create_time",create_time);
	}
	
	public String getRealname() {
		return this.getString("realname");
	}
	public void setRealname(String realname) {
		this.set("realname",realname);
	}
	public String getCburl() {
		return this.getString("cburl");
	}
	public void setCburl(String cburl) {
		this.set("cburl",cburl);
	}
	public String getAppid() {
		return this.getString("appid");
	}
	public void setAppid(String appid) {
		this.set("appid",appid);
	}
	public String getAppsecret() {
		return this.getString("appsecret");
	}
	public void setAppsecret(String appsecret) {
		this.set("appsecret",appsecret);
	}
	public String getDelFlag() {
		return this.getString("delFlag");
	}
	public void setDelFlag(String delFlag) {
		this.set("delFlag",delFlag);
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
