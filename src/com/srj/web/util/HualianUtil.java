package com.srj.web.util;

import java.util.Map;

import com.srj.common.utils.HttpRequestUtil;
import com.srj.common.utils.SysConstant;

import net.sf.json.JSONObject;

public class HualianUtil {
	
	//调用初始化注册接口
	private static String HUALIAN_INIT = "/v1/app/init";
	
	public static JSONObject getInit(Map<String,Object> map){
		String url = SysConstant.getHualianBaseUrl()+HUALIAN_INIT;
		String para = StringUtil.map2String(map);
		//取得返回值
		String result = HttpRequestUtil.sendPost(url, para, false);
		//转json
		JSONObject obj = JSONObject.fromObject(result);
		JSONObject data = new JSONObject();
		
		if(200==(obj.getInt("code"))){
			data = (JSONObject) obj.get("data");
		}
		
		return data;
	}
	

}
