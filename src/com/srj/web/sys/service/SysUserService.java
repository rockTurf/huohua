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
	
	//查看是否有该用户
	public SysUser CheckUser(Map<String, Object> params){
		SysUser sysuser = sysUserMapper.CheckSysUser(params);
		return sysuser;
	}
	//校验用户id和秘钥
	public SysUser AccessLogin(Map<String, Object> params){
		SysUser sysuser = sysUserMapper.AccessLogin(params);
		return sysuser;
	}

	

	//显示用户列表
	public PageInfo<SysUser> findPageInfo(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<SysUser> list = sysUserMapper.findPageInfo(params);
		return new PageInfo<SysUser>(list);
	}

	//增加用户
	public int addUser(SysUser u) {
		return sysUserMapper.insertSelective(u);
	}

}
