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
import com.app.jdy.adapter.MyOrderAdapter;
import com.app.jdy.entity.BankCard;
import com.app.jdy.entity.FaceValue;
import com.app.jdy.entity.MyOrder;
import com.app.jdy.utils.ChineseMoneyUtils;
import com.app.jdy.utils.Constants;
import com.app.jdy.utils.HttpUtils;
import com.app.jdy.utils.URLs;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
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
public class MyOrderActivity extends Activity implements OnClickListener {
	/**
	 * 返回按钮
	 */
	private ImageView back_img;
	/**
	 * 标题
	 */
	private TextView title;
	/**
	 * list组件
	 */
	private ListView my_order_list;
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
	 * 我的订单的实体类
	 */
	private List<MyOrder> list;
	/**
	 * 服务的json数据
	 */
	private String dataJson;
	/**
	 * 我的订单适配器
	 */
	private MyOrderAdapter myOrderAdapter;
	/**
	 * 我的订单金额的总数
	 */
	private TextView my_order_sum;
	/**
	 * 订单反馈
	 */
	private ImageView right_img;
	/**
	 * 元的组件
	 */
	private TextView my_order_sum_yuan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_order);

		initView();

		handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					Toast.makeText(MyOrderActivity.this, Constants.NO_INTENT_TIPS, Toast.LENGTH_SHORT).show();
					break;
				case 1:
					list = new ArrayList<MyOrder>();
					try {
						JSONObject jsonObject = new JSONObject(dataJson);
						if (jsonObject.getString("sum").equals("null")) {
							my_order_sum.setText("0");
							my_order_sum_yuan.setText("元");
						} else {
							my_order_sum.setText(ChineseMoneyUtils.numWithDigitArray(jsonObject.getDouble("sum"))[0]);
							my_order_sum_yuan
									.setText(ChineseMoneyUtils.numWithDigitArray(jsonObject.getDouble("sum"))[1]);
						}
						JSONArray jsonArray = jsonObject.getJSONArray("data");

						for (int i = 0; i < jsonArray.length(); i++) {

							MyOrder myOrder = new MyOrder();
							myOrder.setTime(jsonArray.getJSONObject(i).getString("create_time"));
							myOrder.setName(jsonArray.getJSONObject(i).getString("name"));
							myOrder.setMoney(jsonArray.getJSONObject(i).getString("investTotal"));
							list.add(myOrder);
						}
						myOrderAdapter = new MyOrderAdapter(MyOrderActivity.this, R.layout.my_order_item, list);
						my_order_list.setAdapter(myOrderAdapter);
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

	}

	public void initView() {
		back_img = (ImageView) findViewById(R.id.back_img);
		back_img.setOnClickListener(this);
		title = (TextView) findViewById(R.id.title_tv);
		title.setText("我的订单");
		right_img = (ImageView) findViewById(R.id.right_img);
		right_img.setImageResource(R.drawable.feedback);
		right_img.setVisibility(View.VISIBLE);
		right_img.setOnClickListener(this);
		SharedPreferences userPreferences = getSharedPreferences("umeng_general_config", Context.MODE_PRIVATE);
		ID = userPreferences.getString("ID", "").trim();

		my_order_list = (ListView) findViewById(R.id.my_order_list);
		my_order_sum = (TextView) findViewById(R.id.my_order_sum);
		my_order_sum_yuan = (TextView) findViewById(R.id.my_order_sum_yuan);

	}

	public void getData() {
		params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("memberId", ID));
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				Message msg = new Message();
				dataJson = HttpUtils.request(params, URLs.MY_ORDER);
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
		case R.id.right_img:
			Intent intent = new Intent(MyOrderActivity.this, OrderFeedBackActivity.class);
			startActivity(intent);
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
