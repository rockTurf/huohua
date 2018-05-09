package com.srj.web.datacenter.model;

import java.io.Serializable;

import javax.persistence.Transient;

import org.springframework.transaction.annotation.Transactional;

import com.srj.common.base.BaseEntity;

public class ChainWallet extends BaseEntity implements Serializable{

	/**
	 * 企业公链钱包实体类
	 */
	private static final long serialVersionUID = 1L;
	
	private String addr;
	private String chain_code;
	private Long wallet_id;
	
	@Transient
	private String wallet_addr;
	
	public String getAddr() {
		return this.getString("addr");
	}
	public void setAddr(String addr) {
		this.set("addr", addr);
	}
	
	public String getChain_code() {
		return this.getString("chainCode");
	}
	public void setChain_code(String chain_code) {
		this.set("chain_code", chain_code);
	}
	public Long getWallet_id() {
		return this.getLong("walletId");
	}
	public void setWallet_id(Long wallet_id) {
		this.set("wallet_id", wallet_id);
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getWallet_addr() {
		return this.getString("walletAddr");
	}
	
	
	
}
