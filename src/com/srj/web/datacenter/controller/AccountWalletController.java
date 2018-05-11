package com.srj.web.datacenter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.model.AccountChainWallet;
import com.srj.web.datacenter.model.AccountWallet;
import com.srj.web.datacenter.model.ChainWallet;
import com.srj.web.datacenter.service.AccountWalletService;
import com.srj.web.sys.model.SysRole;
import com.srj.web.sys.model.SysUser;
/*
 * 用户钱包控制层
 * */
import com.srj.web.util.HualianUtil;

@Controller
@RequestMapping("accountWallet")
public class AccountWalletController {
	
	@Resource
	private AccountWalletService accountWalletService;
	
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model,Map<String, Object> params){
		//取得session中用户
		SysUser u = (SysUser)SysUserUtil.getSessionLoginUser();
		model.addAttribute("params", params);
		return "datacenter/accountWallet/account-wallet-manager";
	}
	
	/**
	 * 添加钱包
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/save")
	public @ResponseBody Integer AccountwallletSave(@RequestParam Map<String, Object> params,Model model,HttpServletRequest request,HttpServletResponse response){
		//取得token
		String accessToken = SysUserUtil.getAccessToken();
		//取得session中用户
		SysUser u = (SysUser)SysUserUtil.getSessionLoginUser();
		//封装
		params.put("accessToken",accessToken);
		params.put("appid", u.getAppid());
		int count = accountWalletService.addAccountwallet(params);
		return count;
	}
	
	/**
	 * 分页显示列表
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		//取得session中用户
		SysUser u = (SysUser)SysUserUtil.getSessionLoginUser();
		params.put("appid", u.getAppid());
		PageInfo<AccountWallet> page = accountWalletService.findPageInfo(params);
		model.addAttribute("page", page);
		return "datacenter/accountWallet/account-wallet-list";
	}
	
	/**
	 * 跳转详情页
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail", method = RequestMethod.POST)
	public String showDetail(Long id,@RequestParam Map<String, Object> params,Model model){
		SysUser u = SysUserUtil.getSessionLoginUser();
		AccountWallet item = accountWalletService.getById(id);
		model.addAttribute("item", item);
		return "datacenter/accountWallet/account-wallet-detail";
	}
	
	/**
	 * 跳转金额页
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "balances", method = RequestMethod.POST)
	public String showBalances(Long id,@RequestParam Map<String, Object> params,Model model){
		SysUser u = SysUserUtil.getSessionLoginUser();
		AccountWallet item = accountWalletService.getById(id);
		model.addAttribute("item", item);
		return "datacenter/accountWallet/account-wallet-balances";
	}
	
	/**
	 * 查询余额
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getBalances", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getBalances(@RequestParam Map<String, Object> params,Model model){
		//取得token
		String accessToken = SysUserUtil.getAccessToken();
		params.put("accessToken", accessToken);
		//返回结果
		Map<String, Object> msg = new HashMap<String, Object>();
		//发送请求
		JSONObject data = HualianUtil.getWalletBalances(params);
		if(200==(data.getInt("code"))){//返回200代表成功
			data = (JSONObject) data.get("data");
			if(data.getBoolean("success")==true){
				JSONArray array = data.getJSONArray("balances");
				msg.put("list", array);
			}
		}else{
			msg.put("error", data.get("message"));
		}
		return msg;
	}	
	/**
	 * 重置查询密码
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "resetSelPwd", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> resetSelectPwd(@RequestParam Map<String, Object> params,Model model){
		//取得token
		String accessToken = SysUserUtil.getAccessToken();
		params.put("accessToken", accessToken);
		//返回结果
		Map<String, Object> msg = new HashMap<String, Object>();
		//发送请求
		JSONObject data = HualianUtil.resetWalletPassword(params);
		if(200==(data.getInt("code"))){//返回200代表成功
			data = (JSONObject) data.get("data");
			if(data.getBoolean("success")==true){
				msg.put("data",data.getString("newPwd"));
				msg.put("success","修改成功！");
			}
		}else{
			msg.put("error", data.get("message"));
		}
		return msg;
	}	
	/**
	 * 修改查询密码
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "updateSelPwd")
	public @ResponseBody Map<String, Object> updateSelPwd(@RequestParam Map<String, Object> params){
		//取得token
		String accessToken = SysUserUtil.getAccessToken();
		params.put("accessToken", accessToken);
		//返回结果
		Map<String, Object> msg = new HashMap<String, Object>();
		JSONObject data = HualianUtil.modifyPassword(params);
		if(200==(data.getInt("code"))){//返回200代表成功
			data = (JSONObject) data.get("data");
			if(data.getBoolean("success")==true){
				msg.put("msg","修改成功！");
			}
		}else{
			msg.put("msg", data.get("message"));
		}
		return msg;
	}
	
	/**
	 * 设置支付密码
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "setPayPwd")
	public @ResponseBody Map<String, Object> setPayPwd(@RequestParam Map<String, Object> params){
		//取得token
		String accessToken = SysUserUtil.getAccessToken();
		params.put("accessToken", accessToken);
		//返回结果
		Map<String, Object> msg = new HashMap<String, Object>();
		JSONObject data = HualianUtil.setPayPassword(params);
		if(200==(data.getInt("code"))){//返回200代表成功
			data = (JSONObject) data.get("data");
			if(data.getBoolean("success")==true){
				msg.put("msg","设置成功！");
			}
		}else{
			msg.put("msg", data.get("message"));
		}
		return msg;
	}
	
	/**
	 * 修改支付密码
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "updatePayPwd")
	public @ResponseBody Map<String, Object> updatePayPwd(@RequestParam Map<String, Object> params){
		//取得token
		String accessToken = SysUserUtil.getAccessToken();
		params.put("accessToken", accessToken);
		//返回结果
		Map<String, Object> msg = new HashMap<String, Object>();
		JSONObject data = HualianUtil.updatePayPassword(params);
		if(200==(data.getInt("code"))){//返回200代表成功
			data = (JSONObject) data.get("data");
			if(data.getBoolean("success")==true){
				msg.put("msg","设置成功！");
			}
		}else{
			msg.put("msg", data.get("message"));
		}
		return msg;
	}

	/**
	 * 重置查询密码
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "resetPayPwd", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> resetPayPwd(@RequestParam Map<String, Object> params,Model model){
		//取得token
		String accessToken = SysUserUtil.getAccessToken();
		params.put("accessToken", accessToken);
		//返回结果
		Map<String, Object> msg = new HashMap<String, Object>();
		//发送请求
		JSONObject data = HualianUtil.resetPayPassword(params);
		if(200==(data.getInt("code"))){//返回200代表成功
			data = (JSONObject) data.get("data");
			if(data.getBoolean("success")==true){
				msg.put("data",data.getString("newPwd"));
				msg.put("success","修改成功！");
			}
		}else{
			msg.put("error", data.get("message"));
		}
		return msg;
	}	
}
