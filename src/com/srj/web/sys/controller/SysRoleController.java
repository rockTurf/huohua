package com.srj.web.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.srj.common.constant.Constant;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.sys.model.SysResource;
import com.srj.web.sys.model.SysRole;
import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysResourceService;
import com.srj.web.sys.service.SysRoleService;
import com.srj.web.sys.service.SysUserService;

@Controller
@RequestMapping("userRole")
public class SysRoleController {

	@Resource
	private SysRoleService sysRoleService;
	
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model,Map<String, Object> params){
		SysUser u = SysUserUtil.getSessionLoginUser();
		return "sys/role/role-manager";
	}
	
	/**
	 * 分页显示角色列表
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		PageInfo<SysRole> page = sysRoleService.findPageInfo(params);
		model.addAttribute("page", page);
		return "sys/role/role-list";
	}
	
	/**
	 * 新增角色
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/save")
	public @ResponseBody Integer roleSave(@ModelAttribute SysRole record,Model model,HttpServletRequest request,HttpServletResponse response){
		int count = sysRoleService.addRole(record);
		return count;
	}
	
	/**
	 * 修改角色
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public @ResponseBody Integer roleEdit(@ModelAttribute SysRole record,Model model,HttpServletRequest request,HttpServletResponse response){
		int count = sysRoleService.editRole(record);
		return count;
	}
	
	/**
	 * 跳转编辑角色页面
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail", method = RequestMethod.POST)
	public String showDetail(Long id,@RequestParam Map<String, Object> params,Model model){
		SysUser u = SysUserUtil.getSessionLoginUser();
		SysRole role = sysRoleService.getRoleById(id);
		model.addAttribute("role", role);
		return "sys/role/role-detail";
	}
}
