package com.srj.web.datacenter.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.model.ChainWallet;
import com.srj.web.datacenter.service.AccountWalletService;
import com.srj.web.sys.model.SysUser;
/*
 * 用户钱包控制层
 * */

@Controller
@RequestMapping("accountWallet")
public class AccessWalletController {
	
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
		PageInfo<ChainWallet> list = accountWalletService.findPageInfo(params);
		return null;
	}
	

}
