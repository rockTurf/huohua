package com.srj.web.datacenter.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.web.datacenter.mapper.ChainWalletMapper;
import com.srj.web.datacenter.mapper.WalletMapper;
import com.srj.web.datacenter.model.ChainWallet;
import com.srj.web.datacenter.model.Wallet;
import com.srj.web.sys.model.SysUser;
import com.srj.web.util.HualianUtil;

@Service("walletService")
public class WalletService {

	@Resource
	private WalletMapper walletMapper;
	@Resource
	private ChainWalletMapper chainWalletMapper;
	
	public List<ChainWallet> getChainWalletList(SysUser u) {
		// TODO Auto-generated method stub
		return null;
	}

	public int addwallet(Map<String, Object> params) {
		JSONObject data = HualianUtil.getNewAPPWallet(params);
		int count = 0;
		if(200==(data.getInt("code"))){//返回200代表成功
			data = (JSONObject) data.get("data");
			Wallet wallet = new Wallet();
			wallet.setQuery_pwd(data.getString("queryPwd"));
			wallet.setUser_code(data.getString("userCode"));
			wallet.setAddr(data.getString("addr"));
			walletMapper.insertSelective(wallet);
			//取得子钱包集合
			JSONArray chainWalletArray = data.getJSONArray("chainWallets");
			//遍历子钱包，插库
			if(chainWalletArray.size()>0){  
				for(int i=0;i<chainWalletArray.size();i++){  
				//遍历 jsonarray 数组，把每一个对象转成 json 对象  
				JSONObject obj = chainWalletArray.getJSONObject(i);   
				ChainWallet cw = new ChainWallet();
				cw.setChain_code(obj.getString("chainCode"));
				cw.setWallet_id(wallet.getId());
				cw.setAddr(obj.getString("addr"));
				count = chainWalletMapper.insertSelective(cw);
				}  
			}  
		}
		return count;
	}

	//查询列表
	public PageInfo<ChainWallet> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<ChainWallet> list = chainWalletMapper.findPageInfo(params);
		return new PageInfo<ChainWallet>(list);
	}

}
