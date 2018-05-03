package com.srj.common.utils;

import org.junit.Test;

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

        String sr=HttpRequestUtil.sendPost(url,para,false);
        System.out.println(sr);
	}
}
