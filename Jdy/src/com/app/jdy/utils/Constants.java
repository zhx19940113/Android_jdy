/**
 * 
 */
package com.app.jdy.utils;

/**
 * 
 * description :常量配置
 * 
 * @version 1.0
 * @author zhonghuixiong
 * @createtime : 2015-1-11 下午9:49:49
 * 
 *             修改历史: 修改人 修改时间 修改内容 --------------- -------------------
 *             ----------------------------------- zhonghuixiong 2015-1-11
 *             下午9:49:49
 * 
 */
public class Constants {
	public static final int JSESSIONID_TIME = 200000;// SSIONID请求时间
	public static final int GETSUCC = 111;// 获取数据成功
	public static final int GETFALL = 110;// 网络数据请求失败
	public static final int NOT_NET = 500;// 网络请求失败
	public static final String APP_ID = "wxec989c50bc00f55c";// 微信APPID
	public static final String APPSECRET = "e5b63856a619bbc49606520dd2a719ee";// 微信Secret
	public static final String GZ_APP_ID = "wx1e8cb82eae36a522";// 微信公众号APPID
	public static final String GZ_APP_SECRET = "97c88c807237ffa7c9c5819a9e2e8096";// 微信公众号secret
//	public static final String APP_ID = "wxe7c746e74d97ec4a";// 微信APPID
//	public static final String APPSECRET = "5d36506da50fbfb219e9b08483cd72ed";// 微信Secret
//	public static final String GZ_APP_ID = "wx1e8cb82eae36a522";// 微信公众号APPID
//	public static final String GZ_APP_SECRET = "97c88c807237ffa7c9c5819a9e2e8096";// 微信公众号secret

	public static boolean WX_LOGIN_OR_SHARE = false;// false为分享状态，true为登录状态
	public static String WX_UNIONID = null;// 微信union
	public static String WX_OPENID = null;// 微信openid
	public static String TMP_MEMBERID = null;// 临时memberId
	public static String TMP_PRODUCTID = null;// 临时productId
	public static String JDY_CODE = null;// 金斗云code

	public static String SELECT_YEAR = "0";// 选择年份标识

	public static String NO_INTENT_TIPS = "与服务器断开连接，请重新检查一下网络";

	public static final String NO_CONTENT_N = "--";
	public static final String NO_CONTENT_S = "暂无";

}
