package com.srj.web.sys.model;

import java.io.Serializable;

import com.srj.common.base.BaseEntity;
import com.srj.common.constant.Constant;

public class SysUser  extends BaseEntity implements Serializable{
	
	private String appcode;
	private String appname;
	private String cburl;
	private String appid;
	private String appsecret;
	private String delFlag;
	public String getAppcode() {
		return this.getString("appcode");
	}
	public void setAppcode(String appcode) {
		this.set("appcode",appcode);
	}
	public String getAppname() {
		return this.getString("appname");
	}
	public void setAppname(String appname) {
		this.set("appname",appname);
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
}
