/**
 * Copyright (c) 2015
 *
 * Licensed under the UCG License, Version 1.0 (the "License");
 */
package com.app.jdy.ui;

import com.app.jdy.R;
import com.app.jdy.context.MsgService;
import com.app.jdy.utils.HttpUtils;
import com.app.jdy.utils.PollingUtils;
import com.app.jdy.utils.URLs;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * description :
 *
 * @version 1.0
 * @author ccw
 * @createtime : 2015-1-27 下午4:52:57
 * 
 * 修改历史:
 * 修改人                                          修改时间                                                     修改内容
 * --------------- ------------------- -----------------------------------
 * ccw        2015-1-27 下午4:52:57 
 *
 */
public class MyMessageDetail extends Activity{
	
	String msgId;
	String memberId;
	ImageView backBtn;
	TextView title;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_magdetail);
		SharedPreferences userPreferences = getSharedPreferences("umeng_general_config", Context.MODE_PRIVATE);
		memberId = userPreferences.getString("ID", "").trim();
		
		initView();
	}

	private void initView() {
		backBtn = (ImageView) findViewById(R.id.back_img);
		backBtn.setVisibility(View.VISIBLE);
		title = (TextView) findViewById(R.id.title_tv);
		title.setText("我的信息");
		backBtn.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				
				
				finish();
			}
		});
		
		String[] strings = getIntent().getStringArrayExtra("msg");
		msgId = strings[1];
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				HttpUtils.request(null, URLs.READEDMSG_URL+"/"+msgId);
			}
		}).start();
		WebView webView = (WebView) findViewById(R.id.content_html);
		
		webView.loadData(strings[0], "text/html;charset=UTF-8", "UTF-8");
		
		webView.getSettings().setDefaultTextEncodingName("UTF-8");
		webView.getSettings().setBlockNetworkImage(false);
		webView.getSettings().setJavaScriptEnabled(true);
		
		TextView text = (TextView) findViewById(R.id.content_html_txt);
//		text.setText(strings[0]);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("SplashScreen"); 
		MobclickAgent.onResume(this); 
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("SplashScreen"); 
		MobclickAgent.onPause(this);
	}
}

	