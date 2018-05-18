package com.srj.web.datacenter.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.srj.common.utils.SysUserUtil;
import com.srj.web.datacenter.service.TransferService;
import com.srj.web.sys.model.SysUser;

@Controller
@RequestMapping("transfer")
public class TransferController {

	/*@Resource
	private TransferService transferService;
	*/
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model,Map<String, Object> params){
		//取得session中用户
		SysUser u = (SysUser)SysUserUtil.getSessionLoginUser();
		model.addAttribute("params", params);
		return "datacenter/transfer/transfer-manager";
	}
}
