/**
 * Copyright (c) 2015
 *
 * Licensed under the UCG License, Version 1.0 (the "License");
 */
package com.app.jdy.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.app.jdy.R;
import com.app.jdy.adapter.CustomManagerAdapter;
import com.app.jdy.entity.ProductEntity;
import com.app.jdy.entity.ProductManager;
import com.app.jdy.utils.HttpUtils;
import com.app.jdy.utils.URLs;

/**
 * description :
 * 
 * @version 1.0
 * @author zhonghuixiong
 * @createtime : 2015-1-26 下午1:55:50
 * 
 *             修改历史: 修改人 修改时间 修改内容 --------------- -------------------
 *             ----------------------------------- zhonghuixiong 2015-1-26
 *             下午1:55:50
 * 
 */
public class CustomSaveMoneyDialog extends Dialog {
	private Context context;
	private CustomManagerAdapter customManagerAdapter;
	private ArrayList<ProductManager> listViewProductManagerList;
	private Map<String, Object> productManagerMap;
	private Handler mHandler;
	private JSONArray jsonArray;
	private JSONObject jsonObject;
	private ListView listView;
	private Map<String, String> map;
	private String coupon;
	private ProductManager productManager;
	/**
	 * 产品经理姓名
	 */
	private String managerName;
	/**
	 * 产品经理电话
	 */
	private String managerPhone;
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 区
	 */
	private String district;
	/**
	 * 街道详细
	 */
	private String address;
	private String detaAddress;
	private TextView promoCodeTextView;
	private TextView addressTextView;

	public CustomSaveMoneyDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CustomSaveMoneyDialog(Context context, int theme,
			Map<String, String> map) {
		super(context, theme);
		this.context = context;
		this.map = map;
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.savemoney_dialog);
		listView = (ListView) findViewById(R.id.savemoney_listView);
		promoCodeTextView = (TextView) findViewById(R.id.PromoCode);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				addressTextView = (TextView) arg1.findViewById(R.id.address);
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
						+ addressTextView.getText()));
				context.startActivity(intent);
			}
		});
		new Thread(ManagerRunnable).start();
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {// 此方法在ui线程运行
				switch (msg.what) {
				case 2:
					customManagerAdapter = new CustomManagerAdapter(context,
							listViewProductManagerList);
					listView.setAdapter(customManagerAdapter);

					break;
				case 3:
					promoCodeTextView.setText(coupon);
					break;
				default:
					break;
				}
			}
		};
	}

	/**
	 * 得到产品经理信息线程
	 */
	/**
	 * GetListJson线程
	 */
	Runnable ManagerRunnable = new Runnable() {
		@Override
		public void run() {
			ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("productId", map.get("ID")));
			params.add(new BasicNameValuePair("memberId", map.get("memberId")));
			try {
				String resultJson = HttpUtils
						.request(params, URLs.WX_GETCOUPON);
				JSONObject jsonObject = new JSONObject(resultJson);
				coupon = jsonObject.getString("couponCode");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			mHandler.sendEmptyMessage(3);
			listViewProductManagerList = GetManagerJson();
			mHandler.sendEmptyMessage(2);
		}
	};

	private ArrayList<ProductManager> GetManagerJson() {
		try {
			jsonArray = new JSONArray(HttpUtils.request(null, URLs.MANAGER_INFO
					+ map.get("ID")));
			listViewProductManagerList = new ArrayList<ProductManager>();
			for (int i = 0; i < jsonArray.length(); i++) {
				jsonObject = jsonArray.getJSONObject(i);
				if (jsonObject.getString("name").equals("")) {
					managerName = "";
				} else {
					managerName = String.valueOf(jsonObject.getString("name")) == "null" ? ""
							: jsonObject.getString("name");
				}
				if (jsonObject.getString("telephone").equals("")) {
					managerPhone = "";
				} else {
					managerPhone = String.valueOf(jsonObject
							.getString("telephone")) == "null" ? ""
							: jsonObject.getString("telephone");
				}
				if (jsonObject.getString("province").equals("")) {
					province = "";
				} else {
					province = jsonObject.getString("province");
				}
				if (jsonObject.getString("city").equals("")) {
					city = "";
				} else {
					city = jsonObject.getString("city");
				}
				if (jsonObject.getString("district").equals("")) {
					district = "";
				} else {
					district = jsonObject.getString("district");
				}
				if (jsonObject.getString("detaAddress").equals("")) {
					detaAddress = "";
				} else {
					detaAddress = jsonObject.getString("detaAddress");
				}
				address = province + city + district + detaAddress;
				productManager = new ProductManager();
				productManager.setManagerName(managerName);
				productManager.setManagerPhone(managerPhone);
				productManager.setAddress(address);
				listViewProductManagerList.add(productManager);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listViewProductManagerList;
	}
}
