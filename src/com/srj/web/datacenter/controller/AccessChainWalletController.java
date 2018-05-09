package com.srj.web.datacenter.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

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
import com.srj.web.datacenter.service.AccountChainWalletService;
import com.srj.web.sys.model.SysUser;
import com.srj.web.util.HualianUtil;


@Controller
@RequestMapping("accountChainWallet")
public class AccessChainWalletController {

	@Resource
	private AccountChainWalletService accountChainWalletService;
	
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model,Map<String, Object> params){
		//取得session中用户
		SysUser u = (SysUser)SysUserUtil.getSessionLoginUser();
		model.addAttribute("params", params);
		return "datacenter/accountChainWallet/account-chain-wallet-manager";
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
		PageInfo<AccountChainWallet> page = accountChainWalletService.findPageInfo(params);
		model.addAttribute("page", page);
		return "datacenter/accountChainWallet/account-chain-wallet-list";
	}
	
	
	/**
	 * 跳转修改查询密码页面
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "updateSelectPwdPage", method = RequestMethod.POST)
	public String updateSelectPwdPage(Long id,@RequestParam Map<String, Object> params,Model model){
		AccountChainWallet acw = accountChainWalletService.getItem(id);
		model.addAttribute("detail", acw);
		return "datacenter/accountWallet/account-wallet-update-select-pwd";
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
	 * 跳转重置查询密码页面
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "resetSelectPwdPage", method = RequestMethod.POST)
	public String resetSelectPwdPage(Long id,@RequestParam Map<String, Object> params,Model model){
		AccountChainWallet acw = accountChainWalletService.getItem(id);
		model.addAttribute("detail", acw);
		return "datacenter/accountWallet/account-wallet-update-select-pwd";
	}
}
