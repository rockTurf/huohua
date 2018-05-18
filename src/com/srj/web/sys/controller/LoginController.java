package com.srj.web.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srj.common.utils.SysConstant;
import com.srj.common.utils.SysUserUtil;
import com.srj.common.base.PasswordEncoder;
import com.srj.common.constant.Constant;
import com.srj.web.sys.model.SysUser;
import com.srj.web.sys.service.SysResourceService;
import com.srj.web.sys.service.SysUserService;
import com.srj.web.util.HualianUtil;

/**
 * @登陆页
 * 
 */
@Controller
public class LoginController {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysResourceService sysResourceService;
	
	/**
	 * 管理主页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String toIndex(Model model, HttpServletRequest request) {
		SysUser u = SysUserUtil.getSessionLoginUser();
		//拥有的资源列表
		model.addAttribute("menuList", SysUserUtil.getUserMenus());
		model.addAttribute("user", u);
		return "index";
	}
	
	
	@RequestMapping(value = "/loginCheck")
	public @ResponseBody Map<String, Object> checkLogin(@RequestParam Map<String,Object> params,Model model,HttpServletRequest request,HttpServletResponse response){
		String username = params.get("username").toString();
		String password = params.get("password").toString();
		//密码加密
		password = PasswordEncoder.Encoding(password, username);
		params.put("password", password);
		
		Map<String, Object> msg = new HashMap<String, Object>();
		SysUser user= sysUserService.AccessLogin(params);
		//数据库取到对应的用户信息不为空 判断
		if(user!=null){
			//调用校验接口
			JSONObject data = HualianUtil.getAccess(params);
			if(200==(data.getInt("code"))){//返回200代表成功
				data = (JSONObject) data.get("data");
				String token = data.getString("accessToken");
				SysUserUtil.setSessionToken(token);
				msg.put("success", "登录成功！");
				//将用户信息 存储到session中;
				SysUserUtil.setSessionLoginUser(user);
			}else{
				msg.put("error", data.get("message"));
			}
		}else{
			msg.put("error", "找不到用户！");
		}
		return msg;
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin(@RequestParam Map<String, Object> params,HttpServletRequest request,HttpServletResponse response,Model model ) {
		if (SysUserUtil.getSessionLoginUser() != null) {
			return "redirect:/index";
		}
		
		return "login";
	}
	
	/**
	 * 用户退出
	 * 
	 * @return 跳转到登录页面
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		try{
		SysUserUtil.clearCacheUser(SysUserUtil.getSessionLoginUser().getId());
		}catch(Exception e){}
		request.getSession().invalidate();
		return "redirect:/login";
	}
	
	/**
	 * 注册
	 * 
	 * @return 跳转到注册页面
	 */
	@RequestMapping("register")
	public String register(HttpServletRequest request) {
		return "register";
	}
	
}
