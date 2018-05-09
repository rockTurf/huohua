package com.srj.web.util;

import java.util.Map;

import com.srj.common.utils.HttpRequestUtil;
import com.srj.common.utils.SysConstant;

import net.sf.json.JSONObject;

public class HualianUtil {
	
	//调用初始化注册接口
	private static String HUALIAN_INIT = "/v1/app/init";
	//安全验证
	private static String HUALIAN_ACCESS = "/v1/app/access";
	//创建企业钱包
	private static String HUALIAN_NEW_APP_WALLET = "/v1/app/wallet/new";
	//企业钱包充值同步
	private static String HUALIAN_APP_WALLET_SYNC = "/v1/wallet/sync";
	//新增用户钱包
	private static String HUALIAN_NEW_USER_WALLET = "/v1/wallet/new";
	//修改查询密码
	private static String HUALIAN_MODIFY_PASSWORD = "/v1/wallet/password/modify";
	//设置支付密码
	private static String HUALIAN_SET_PAY_PASSWORD = "/v1/wallet/pay/password";
	//修改支付密码
	private static String HUALIAN_UPDATE_PAY_PASSWORD = "/v1/wallet/pay/password/update";
	//重置支付密码
	private static String HUALIAN_RESET_PAY_PASSWORD = "/v1/wallet/pay/password/reset";
	//重置查询密码
	private static String HUALIAN_RESET_WALLET_PASSWORD = "/v1/wallet/password/reset";
	//获取钱包余额
	private static String HUALIAN_GET_WALLET_BALANCES = "/v1/wallet/balances";
	//获取链上钱包余额
	private static String HUALIAN_GET_WALLET_BALANCES_INCHIAN = "/v1/wallet/balance/inchain";
	
	//调用初始化注册接口
	public static JSONObject getInit(Map<String,Object> map){
		String url = SysConstant.getHualianBaseUrl()+HUALIAN_INIT;
		return getHttpPost(map, url);
	}
	//安全验证
	public static JSONObject getAccess(Map<String,Object> map){
		String url = SysConstant.getHualianBaseUrl()+HUALIAN_ACCESS;
		return getHttpPost(map, url);
	}
	//创建企业钱包
	public static JSONObject getNewAPPWallet(Map<String,Object> map){
		String url = SysConstant.getHualianBaseUrl()+HUALIAN_NEW_APP_WALLET;
		return getHttpPost(map, url);
	}
	//企业钱包充值同步
	public static JSONObject AppwalletSync(Map<String,Object> map){
		String url = SysConstant.getHualianBaseUrl()+HUALIAN_APP_WALLET_SYNC;
		return getHttpPost(map, url);
	}
	//创建用户钱包
	public static JSONObject getNewUserWallet(Map<String,Object> map){
		String url = SysConstant.getHualianBaseUrl()+HUALIAN_NEW_USER_WALLET;
		return getHttpPost(map, url);
	}
	//修改查询密码
	public static JSONObject modifyPassword(Map<String,Object> map){
		String url = SysConstant.getHualianBaseUrl()+HUALIAN_MODIFY_PASSWORD;
		return getHttpPost(map, url);
	}
	//设置用户钱包支付密码
	public static JSONObject setPayPassword(Map<String,Object> map){
		String url = SysConstant.getHualianBaseUrl()+HUALIAN_SET_PAY_PASSWORD;
		return getHttpPost(map, url);
	}
	//修改用户钱包支付密码
	public static JSONObject updatePayPassword(Map<String,Object> map){
		String url = SysConstant.getHualianBaseUrl()+HUALIAN_UPDATE_PAY_PASSWORD;
		return getHttpPost(map, url);
	}
	//重置用户钱包支付密码
	public static JSONObject resetPayPassword(Map<String,Object> map){
		String url = SysConstant.getHualianBaseUrl()+HUALIAN_RESET_PAY_PASSWORD;
		return getHttpPost(map, url);
	}
	//重置用户钱包查询密码
	public static JSONObject resetWalletPassword(Map<String,Object> map){
		String url = SysConstant.getHualianBaseUrl()+HUALIAN_RESET_WALLET_PASSWORD;
		return getHttpPost(map, url);
	}
	//获取钱包余额
	public static JSONObject getWalletBalances(Map<String,Object> map){
		String url = SysConstant.getHualianBaseUrl()+HUALIAN_GET_WALLET_BALANCES;
		return getHttpPost(map, url);
	}	
	//获取链上钱包余额
	public static JSONObject getWalletBalancesInChain(Map<String,Object> map){
		String url = SysConstant.getHualianBaseUrl()+HUALIAN_GET_WALLET_BALANCES_INCHIAN;
		return getHttpPost(map, url);
	}	
	
	
	private static JSONObject getHttpPost(Map<String, Object> map, String url) {
		//取得返回值
		String result = HttpRequestUtil.sendPost(url, map,"UTF-8");
		System.out.println("url="+url+",params="+map.toString()+",result="+result);
		//转json
		JSONObject obj = JSONObject.fromObject(result);
		return obj;
	}

}
