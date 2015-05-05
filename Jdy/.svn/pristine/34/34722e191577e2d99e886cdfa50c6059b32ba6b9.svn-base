package com.app.jdy.bugly;

import android.content.Context;

import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.CrashReport.UserStrategy;

/**
 * 配置异常报告器
 * 
 * @author 王磊
 * @date 2015-3-31
 * 
 */
public class BuglyConfig {

	/** 唯一标识一个产品的Id，初始化SDK及配置符号表时需要使用 */
	static final String APP_ID = "900002507";

	/** AppId所对应的秘钥，配置符号表时需要使用 */
	static final String APP_KEY = "R22GD9BQS5RpA12V";

	/** true代表App处于调试阶段，false代表App发布阶段 */
	static final boolean IS_DEBUG = true;

	/** 是否应用策略 */
	static final boolean USE_STRATEGY = false;

	/**
	 * 初始化CrashReport（异常报告器）
	 * 
	 * @param context
	 */
	public static void initCrashReport(Context context) {
		if (USE_STRATEGY) {
			UserStrategy strategy = new UserStrategy(context); // App的策略Bean
			strategy.setAppChannel("豌豆荚"); // 设置渠道
			strategy.setAppVersion("1.0"); // App的版本
			strategy.setAppReportDelay(2000); // 设置SDK处理延时，毫秒
			CrashReport.initCrashReport(context, APP_ID, IS_DEBUG, strategy);// 初始化SDK
		} else {
			CrashReport.initCrashReport(context, APP_ID, IS_DEBUG);
		}
	}

}
