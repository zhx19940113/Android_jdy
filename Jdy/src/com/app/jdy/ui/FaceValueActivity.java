/**
 * Copyright (c) 2015
 *
 * Licensed under the UCG License, Version 1.0 (the "License");
 */
package com.app.jdy.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.app.jdy.R;
import com.app.jdy.adapter.BankAdapter;
import com.app.jdy.adapter.FaceValueAdapter;
import com.app.jdy.entity.BankCard;
import com.app.jdy.entity.FaceValue;
import com.app.jdy.utils.ChineseMoneyUtils;
import com.app.jdy.utils.Constants;
import com.app.jdy.utils.HttpUtils;
import com.app.jdy.utils.URLs;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * description :我的面值的avtivity
 * 
 * @version 1.0
 * @author zhoufeng
 * @createtime : 2015-1-24 下午8:56:14
 * 
 *             修改历史: 修改人 修改时间 修改内容 --------------- -------------------
 *             ----------------------------------- zhoufeng 2015-1-24 下午8:56:14
 * 
 */
public class FaceValueActivity extends Activity implements OnClickListener {
	/**
	 * 返回按钮
	 */
	private ImageView mBackImg;
	/**
	 * 标题
	 */
	private TextView title;
	/**
	 * list组件
	 */
	private ListView face_value_list;
	/**
	 * handler
	 */
	private Handler handler;
	/**
	 * 用户ID号
	 */
	private String ID;
	/**
	 * 提交数据
	 */
	private ArrayList<NameValuePair> params;
	/**
	 * 银行卡对应的list对象
	 */
	private List<FaceValue> list;
	/**
	 * 服务的json数据
	 */
	private String dataJson;
	/**
	 * 我的面值适配器
	 */
	private FaceValueAdapter faceValueAdapter;
	/**
	 * 我的面值的总数
	 */
	private TextView face_vale_sum;
	/**
	 * 元的组件
	 */
	private TextView face_vale_sum_yuan;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_face_value);

		initView();
		
		handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					Toast.makeText(FaceValueActivity.this, Constants.NO_INTENT_TIPS,
							Toast.LENGTH_SHORT).show();
					break;
				case 1:
					list = new ArrayList<FaceValue>();
					try {
						JSONObject jsonObject = new JSONObject(dataJson);
						if (jsonObject.getString("sum").equals("null")) {
							face_vale_sum.setText("0");
							face_vale_sum_yuan.setText("元");
						}else{
							face_vale_sum.setText(ChineseMoneyUtils
									.numWithDigitArray(jsonObject.getDouble("sum"))[0]);
							face_vale_sum_yuan.setText(ChineseMoneyUtils
									.numWithDigitArray(jsonObject.getDouble("sum"))[1]);
						}
						JSONArray jsonArray = jsonObject.getJSONArray("data");
						
						for (int i = 0; i < jsonArray.length(); i++) {
							
							FaceValue faceValue = new FaceValue();
							faceValue.setTime(jsonArray.getJSONObject(i)
									.getString("create_time"));
							faceValue.setName(jsonArray.getJSONObject(i)
									.getString("name"));
							faceValue.setMoney(jsonArray.getJSONObject(i)
									.getString("income"));
							list.add(faceValue);
						}
						faceValueAdapter = new FaceValueAdapter(
								FaceValueActivity.this,
								R.layout.face_value_item, list);
						face_value_list.setAdapter(faceValueAdapter);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					break;
				default:
					break;
				}
			}
		};
		
		getData();
		mBackImg.setOnClickListener(this);
	}

	public void initView() {
		mBackImg = (ImageView) findViewById(R.id.back_img);
		mBackImg.setVisibility(View.VISIBLE);
		title = (TextView) findViewById(R.id.title_tv);
		title.setText("我的面值");

		SharedPreferences userPreferences = getSharedPreferences(
				"umeng_general_config", Context.MODE_PRIVATE);
		ID = userPreferences.getString("ID", "").trim();

		face_value_list = (ListView) findViewById(R.id.face_value_list);
		face_vale_sum = (TextView) findViewById(R.id.face_vale_sum);
		face_vale_sum_yuan = (TextView) findViewById(R.id.face_vale_sum_yuan);
		
	}
	
	public void getData() {
		params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("memberId", ID));
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				Message msg = new Message();
				dataJson = HttpUtils.request(params, URLs.USER_FACE_VALUE);
				if (dataJson.length() != 0 && !dataJson.equals("0x110")) {
					msg.what = 1;
				} else {
					msg.what = 0;
				}
				handler.sendMessage(msg);
			}
		});
		thread.start();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_img:
			finish();
			break;

		default:
			break;
		}
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
