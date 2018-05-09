package com.srj.web.datacenter.model;

import java.io.Serializable;

import javax.persistence.Transient;

import com.srj.common.base.BaseEntity;

public class AccountChainWallet extends BaseEntity implements Serializable{

	/**
	 * 企业公链钱包实体类
	 */
	private static final long serialVersionUID = 1L;
	private String addr;
	private String chain_code;
	private Long account_wallet_id;
	
	@Transient
	private String user_id;
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
	public Long getAccount_wallet_id() {
		return this.getLong("accountWalletId");
	}
	public void setAccount_wallet_id(Long account_wallet_id) {
		this.set("account_wallet_id", account_wallet_id);
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUser_id() {
		return this.getString("userId");
	}
	public String getWallet_addr() {
		return this.getString("walletAddr");
	}
	
	
}
