package com.srj.web.datacenter.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.web.datacenter.mapper.AccountChainWalletMapper;
import com.srj.web.datacenter.model.AccountChainWallet;


@Service("accountChainWalletService")
public class AccountChainWalletService {
	
	@Resource
	private AccountChainWalletMapper accountChainWalletMapper;
	
	//查询列表
	public PageInfo<AccountChainWallet> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<AccountChainWallet> list = accountChainWalletMapper.findPageInfo(params);
		return new PageInfo<AccountChainWallet>(list);
	}

	//钱包详情
	public AccountChainWallet getItem(Long id) {
		return accountChainWalletMapper.getItem(id);
	}
}
