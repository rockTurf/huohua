package com.srj.web.datacenter.model;

import java.io.Serializable;

import com.srj.common.base.BaseEntity;

public class Transfer extends BaseEntity implements Serializable{


	/**
	 * 交易实体类
	 */
	private static final long serialVersionUID = 1L;
	private String user_id;
	private String token_code;
	private String src_wallet_addr;
	private String dest_wallet_addr;
	private String amount;
	private String memo;
	private String gas_fee;
	private String freezed;
	private String hash;
	private String create_time;
	private String transfer_time;
	private String issuer;
	private String status;
	public String getUser_id() {
		return this.getString("userId");
	}
	public void setUser_id(String user_id) {
		this.set("user_id", user_id);
	}
	public String getToken_code() {
		return this.getString("tokenCode");
	}
	public void setToken_code(String token_code) {
		this.set("token_code", token_code);
	}
	public String getSrc_wallet_addr() {
		return this.getString("srcWalletAddr");
	}
	public void setSrc_wallet_addr(String src_wallet_addr) {
		this.set("src_wallet_addr", src_wallet_addr);
	}
	public String getDest_wallet_addr() {
		return this.getString("destWalletAddr");
	}
	public void setDest_wallet_addr(String dest_wallet_addr) {
		this.set("dest_wallet_addr", dest_wallet_addr);
	}
	public String getAmount() {
		return this.getString("amount");
	}
	public void setAmount(String amount) {
		this.set("amount", amount);
	}
	public String getMemo() {
		return this.getString("memo");
	}
	public void setMemo(String memo) {
		this.set("memo", memo);
	}
	public String getGas_fee() {
		return this.getString("gasFee");
	}
	public void setGas_fee(String gas_fee) {
		this.set("gas_fee", gas_fee);
	}
	public String getFreezed() {
		return this.getString("freezed");
	}
	public void setFreezed(String freezed) {
		this.set("freezed", freezed);
	}
	public String getHash() {
		return this.getString("hash");
	}
	public void setHash(String hash) {
		this.set("hash", hash);
	}
	public String getCreate_time() {
		return this.getString("createTime");
	}
	public void setCreate_time(String create_time) {
		this.set("create_time", create_time);
	}
	public String getTransfer_time() {
		return this.getString("transferTime");
	}
	public void setTransfer_time(String transfer_time) {
		this.set("transfer_time", transfer_time);
	}
	public String getIssuer() {
		return this.getString("issuer");
	}
	public void setIssuer(String issuer) {
		this.set("issuer", issuer);
	}
	public String getStatus() {
		return this.getString("status");
	}
	public void setStatus(String status) {
		this.set("status", status);
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
