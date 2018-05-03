package com.srj.web.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.srj.common.base.PasswordEncoder;
import com.srj.common.constant.Constant;
import com.srj.web.sys.mapper.SysUserMapper;
import com.srj.web.sys.model.SysUser;

@Service("sysuserService")
public class SysUserService {
	@Autowired
	public SysUserMapper sysUserMapper;
	
	public SysUser CheckUser(Map<String, Object> params){
		SysUser sysuser = sysUserMapper.CheckSysUser(params);
		return sysuser;
	}

	//校验密码
	public boolean CheckPassword(Map<String, Object> params) {
		boolean b = false;
		params.put("password", PasswordEncoder.Encoding((String)params.get("loginPwd"), (String)params.get("loginName")));
		Long l = sysUserMapper.CheckPassword(params);
		if(l!=null){
			b=true;
		}
		return b;
	}

	//显示用户列表
	public PageInfo<SysUser> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<SysUser> list = sysUserMapper.findPageInfo(params);
		return new PageInfo<SysUser>(list);
	}

	//增加用户
	public int addUser(Map<String, Object> params) {
		SysUser u = new SysUser();
		/*u.setName((String) params.get("name"));
		u.setEmail((String) params.get("email"));
		u.setUsername((String) params.get("loginName"));
		u.setPassword((String) params.get("password"));
		u.setPhone((String) params.get("phone"));
		u.setDelFlag(Constant.DEL_FLAG_NORMAL);*/
		int count = sysUserMapper.insertSelective(u);
		return count;
	}

}
