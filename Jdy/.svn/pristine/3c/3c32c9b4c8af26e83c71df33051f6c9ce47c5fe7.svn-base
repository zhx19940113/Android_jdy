/**
 * Copyright (c) 2015
 *
 * Licensed under the UCG License, Version 1.0 (the "License");
 */
package com.app.jdy.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * description :
 *
 * @version 1.0
 * @author ccw
 * @createtime : 2015-1-27 下午2:46:16
 * 
 * 修改历史:
 * 修改人                                          修改时间                                                     修改内容
 * --------------- ------------------- -----------------------------------
 * ccw        2015-1-27 下午2:46:16 
 *
 */
public class PollingUtils {

	//开启轮询服务
	public static void startPollingService(Context context, int seconds, Class<?> cls,String action) {
		//获取AlarmManager系统服务
		AlarmManager manager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		
		//包装需要执行Service的Intent
		Intent intent = new Intent(context, cls);
		intent.setAction(action);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		//触发服务的起始时间
		long triggerAtTime = SystemClock.elapsedRealtime();
		
		//使用AlarmManger的setRepeating方法设置定期执行的时间间隔（seconds秒）和需要执行的Service
		manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime,
				seconds * 1000, pendingIntent);
	}

	//停止轮询服务
	public static void stopPollingService(Context context, Class<?> cls,String action) {
		AlarmManager manager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, cls);
		intent.setAction(action);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		//取消正在执行的服务
		manager.cancel(pendingIntent);
	}
}

	