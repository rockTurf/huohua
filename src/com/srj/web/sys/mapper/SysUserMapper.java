package com.srj.web.sys.mapper;

import java.util.List;
import java.util.Map;

import com.github.abel533.mapper.Mapper;
import com.srj.web.sys.model.SysUser;



public interface SysUserMapper extends Mapper<SysUser>{
	
	public SysUser CheckSysUser(Map<String,Object> map);

	public SysUser AccessLogin(Map<String,Object> map);

	public List<SysUser> findPageInfo(Map<String, Object> params);
	

	
}