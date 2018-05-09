package com.srj.web.datacenter.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.web.datacenter.mapper.AccountChainWalletMapper;
import com.srj.web.datacenter.mapper.AccountWalletMapper;
import com.srj.web.datacenter.model.AccountChainWallet;
import com.srj.web.datacenter.model.AccountWallet;
import com.srj.web.datacenter.model.ChainWallet;
import com.srj.web.sys.model.SysUser;
import com.srj.web.util.HualianUtil;

@Service("accountWalletService")
public class AccountWalletService {

	@Resource
	private AccountWalletMapper accountWalletMapper;
	@Resource
	private AccountChainWalletMapper accountChainWalletMapper;
	
	public List<ChainWallet> getChainWalletList(SysUser u) {
		// TODO Auto-generated method stub
		return null;
	}

	public int addAccountwallet(Map<String, Object> params) {
		JSONObject data = HualianUtil.getNewUserWallet(params);
		int count = 0;
		if(200==(data.getInt("code"))){//返回200代表成功
			data = (JSONObject) data.get("data");
			AccountWallet aw = new AccountWallet();
			aw.setUser_id(data.getString("userId"));
			aw.setWallet_addr(data.getString("walletAddr"));
			aw.setAppid((String)params.get("appid"));
			accountWalletMapper.insertSelective(aw);
			//取得子钱包集合
			JSONArray chainWalletArray = data.getJSONArray("phyWalletList");
			//遍历子钱包，插库
			if(chainWalletArray.size()>0){  
				for(int i=0;i<chainWalletArray.size();i++){  
				//遍历 jsonarray 数组，把每一个对象转成 json 对象  
				JSONObject obj = chainWalletArray.getJSONObject(i);   
				AccountChainWallet acw = new AccountChainWallet();
				acw.setChain_code(obj.getString("chainCode"));
				acw.setAccount_wallet_id(aw.getId());
				acw.setAddr(obj.getString("addr"));
				count = accountChainWalletMapper.insertSelective(acw);
				}  
			}  
		}
		return count;
	}

	//列表显示用户钱包
	public PageInfo<AccountWallet> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<AccountWallet> list = accountWalletMapper.findPageInfo(params);
		return new PageInfo<AccountWallet>(list);
	}

	//查询单条记录
	public AccountWallet getById(Long id) {
		return accountWalletMapper.selectByPrimaryKey(id);
	}



}
