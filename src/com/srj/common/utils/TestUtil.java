package com.srj.common.utils;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;

import com.srj.web.util.HualianUtil;
import com.srj.web.util.StringUtil;

public class TestUtil {
	
	@Test
	public void test1(){
		System.out.println(StringUtil.getPercent(1, 45,2));
	}

	@Test
	public void test2(){
		 //demo:代理访问
        String url = "http://118.31.38.181:8081/v1/app/init";
        String para = "appcode=huohuaTest_srj&appname=测试程序srj";
        String accessToken = "72209f79-16ef-4ab5-81a0-bbf1507b2320";
        Map<String,Object> map = new HashedMap();
        map.put("appid", "992562277334908928");
        map.put("appsecret", "3e69bdc4-d605-4108-bd66-a2b57a89f803");

        //String sr=HttpRequestUtil.sendPost(url,map,"UTF-8");
        JSONObject sr = HualianUtil.getAccess(map);
        System.out.println(sr);
	}
}
