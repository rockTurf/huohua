package com.srj.web.datacenter.controller;

import java.util.HashMap;
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
import com.srj.web.datacenter.service.WalletService;
import com.srj.web.sys.model.SysUser;
import com.srj.web.util.StringUtil;

@Controller
@RequestMapping("wallet")
public class WalletController {
	
	@Resource
	private WalletService walletService;
	
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model,Map<String, Object> params){
		//取得session中用户
		SysUser u = (SysUser)SysUserUtil.getSessionLoginUser();
		List<ChainWallet> chainWalletList = walletService.getChainWalletList(u);
		model.addAttribute("params", params);
		return "datacenter/wallet/wallet-manager";
	}
	
	/**
	 * 添加钱包
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/save")
	public @ResponseBody Integer wallletSave(@RequestParam String[] blockChains,Model model,HttpServletRequest request,HttpServletResponse response){
		//取得token
		String accessToken = SysUserUtil.getAccessToken();
		//数组拆分
		String str = StringUtil.Array2String(blockChains);
		//封装
		Map<String,Object> params = new HashMap<>();
		params.put("blockChains", str);
		params.put("accessToken",accessToken);
		int count = walletService.addwallet(params);
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
		PageInfo<ChainWallet> page = walletService.findPageInfo(params);
		model.addAttribute("page", page);
		return "datacenter/wallet/wallet-list";
	}
	

}
