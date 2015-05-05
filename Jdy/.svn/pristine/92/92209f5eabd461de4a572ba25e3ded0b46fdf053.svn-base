/**
 * Copyright (c) 2015
 *
 * Licensed under the UCG License, Version 1.0 (the "License");
 */
package com.app.jdy.context;

import com.app.jdy.R;
import com.app.jdy.ui.MsgListActivity;
import com.app.jdy.utils.HttpUtils;
import com.app.jdy.utils.URLs;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;


/**
 * description :
 *
 * @version 1.0
 * @author ccw
 * @createtime : 2015-1-27 下午12:02:15
 * 
 * 修改历史:
 * 修改人                                          修改时间                                                     修改内容
 * --------------- ------------------- -----------------------------------
 * ccw        2015-1-27 下午12:02:15 
 *
 */
public class MsgService extends Service{
	public static final String ACTION = "com.jdy.service.MsgService";

	String memberId;
	String titleFlag;
	private Notification mNotification;
	private NotificationManager mManager;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		SharedPreferences userPreferences = getSharedPreferences("umeng_general_config", Context.MODE_PRIVATE);
		memberId = userPreferences.getString("ID", "").trim();
		initNotifiManager();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		new PollingThread().start();
	}

	//初始化通知栏配置
	private void initNotifiManager() {
		mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		int icon = R.drawable.ic_launcher;
		mNotification = new Notification();
		mNotification.icon = icon;
		mNotification.tickerText = "新消息";
		mNotification.defaults |= Notification.DEFAULT_SOUND;
		mNotification.flags = Notification.FLAG_AUTO_CANCEL;
	}

	//弹出Notification
	private void showNotification(String title) {
		mNotification.when = System.currentTimeMillis();
		//Navigator to the new activity when click the notification title
		Intent i = new Intent(this, MsgListActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i,
				Intent.FLAG_ACTIVITY_NEW_TASK);
		mNotification.setLatestEventInfo(this,
				getResources().getString(R.string.app_name), title, pendingIntent);
		mManager.notify(0, mNotification);
	}

	/**
	 * Polling thread
	 * 模拟向Server轮询的异步线程
	 * @Author ccw
	 * 
	 */
	int count = 0;
	class PollingThread extends Thread {
		@Override
		public void run() {
			System.out.println("Polling...");
			count ++;
			//当计数能被5整除时弹出通知
			if (count % 2 == 0) {
				String str = HttpUtils.request(null, URLs.HASMSG_URL+"/"+memberId);
				
				System.out.println("service轮询结果:"+str+"---id:"+memberId);
				
				if (!"false".equals(str) && !str.equals(titleFlag) && !"0x110".equals(str)) {
					titleFlag = str;
					showNotification(str);
					System.out.println("New message!"+str);
				}
				
				
				
				
				
			}
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("Service:onDestroy");
	}
}

	