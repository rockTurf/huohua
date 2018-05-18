package com.srj.web.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.srj.common.base.PasswordEncoder;
import com.srj.common.constant.Constant;
import com.srj.common.utils.SysUserUtil;
import com.srj.web.sys.model.SysResource;
import com.srj.web.sys.model.SysRole;
import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysResourceService;
import com.srj.web.sys.service.SysRoleService;
import com.srj.web.sys.service.SysUserService;
import com.srj.web.util.DateUtils;
import com.srj.web.util.HualianUtil;

@Controller
@RequestMapping("userCenter")
public class UserCenterController {

	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;
	
	/**
	 * 跳转到页面
	 */
	@RequestMapping
	public String toPage(Model model,Map<String, Object> params){
		SysUser u = SysUserUtil.getSessionLoginUser();
		//角色列表
		List<SysRole> roleList = sysRoleService.getAllRole();
		params.put("roleList", roleList);
		model.addAttribute("params", params);
		return "sys/user/user-manager";
	}
	
	/**
	 * 分页显示用户列表
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(@RequestParam Map<String, Object> params, Model model) {
		PageInfo<SysUser> page = sysUserService.findPageInfo(params);
		model.addAttribute("page", page);
		return "sys/user/user-list";
	}
	/**
	 * 注册用户
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userRegist")
	public @ResponseBody Map<String, Object> userRegist(@RequestParam Map<String,Object> params,Model model,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> msg = new HashMap<String, Object>();
		SysUser user= sysUserService.CheckUser(params);
		//数据库取到对应的用户信息不为空 判断
		if(user==null){
			//调用注册接口
			String appcode = (String)params.get("username");
			String appname = (String)params.get("realname");
			params.put("appcode", appcode);
			params.put("appname", appname);
			
			JSONObject data = HualianUtil.getInit(params);
			if(200==(data.getInt("code"))){//返回200代表成功
				data = (JSONObject) data.get("data");
				//密码加密（md5,username为盐）
				String password = PasswordEncoder.Encoding((String) params.get("password"), (String) params.get("username"));
				//存入库
				SysUser u = new SysUser();
				u.setUsername((String) params.get("username"));
				u.setPassword(password);
				u.setRealname((String) params.get("realname"));
				u.setCburl((String) params.get("cburl"));
				u.setAppid(data.optString("appid"));
				u.setUser_type(Constant.SYS_USER_TYPE_COMPANY);
				u.setCreate_time(DateUtils.formatDateTime(new Date()));
				u.setAppsecret(data.getString("appsecret"));
				u.setDelFlag(Constant.DEL_FLAG_NORMAL);
				int count = sysUserService.addUser(u);
				if(count>0){
					msg.put("appid", data.optString("appid"));
					msg.put("appsecret", data.getString("appsecret"));
					msg.put("success", "注册成功！");
				}else{
					msg.put("error", "未知错误！");
				}
			}else{
				msg.put("error", data.get("message"));
			}
		}else{
			msg.put("error", "用户名已存在！");
		}
		return msg;
	}
	/**
	 * 添加用户
	 * 
	 * @param params
	 * @param model
	 * @return
	 *//*
	@RequestMapping(value = "/save")
	public @ResponseBody Integer userSave(@RequestParam Map<String,Object> params,Model model,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> msg = new HashMap<String, Object>();
		int count = sysUserService.addUser(params);
		return count;
	}
	*/
	/**
	 * 检验用户名
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/checkUser")
	public @ResponseBody Integer checkUser(@RequestParam Map<String,Object> params,Model model,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> msg = new HashMap<String, Object>();
		SysUser user= sysUserService.CheckUser(params);
		int count = 1;
		//数据库取到对应的用户信息不为空 判断
		if(user==null){
			count = 0;
		}
		return count;
	}
}
