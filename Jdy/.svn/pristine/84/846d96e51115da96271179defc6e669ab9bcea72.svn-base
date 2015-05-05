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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.app.jdy.R;
import com.app.jdy.adapter.SelectYearAdapter;
import com.app.jdy.utils.Constants;
import com.app.jdy.utils.DialogCallBackInter;
import com.app.jdy.utils.HttpUtils;
import com.app.jdy.utils.URLs;

/**
 * 
 * description :
 * 
 * @version 1.0
 * @author zhoufeng
 * @createtime : 2015-1-30 下午4:13:02
 * 
 *             修改历史: 修改人 修改时间 修改内容 --------------- -------------------
 *             ----------------------------------- zhoufeng 2015-1-30 下午4:13:02
 * 
 */
public class SelectYearDialog extends Dialog implements android.view.View.OnClickListener{
	private Context context;
	private ListView select_year_list;
	private SelectYearAdapter selectYearAdapter;
	private Button select_year_button;
	private DialogCallBackInter dialogCallBackInter;
	private String[] beans = new String[] { "2015", "2016", "2017", "2018",
			"2019", "2020", "2021", "2022", "2023", "2024", "2025"};

	public SelectYearDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public SelectYearDialog(Context context, int theme,DialogCallBackInter dialogCallBackInter) {
		super(context, theme);
		this.context = context;
		this.dialogCallBackInter = dialogCallBackInter;
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_year_dialog);
		initView();
	}

	public void initView() {
		select_year_list = (ListView) findViewById(R.id.select_year_list);
		selectYearAdapter = new SelectYearAdapter(getContext(), beans);
		select_year_list.setAdapter(selectYearAdapter);
		select_year_list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		select_year_button = (Button) findViewById(R.id.select_year_button);
		select_year_button.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.select_year_button:
			if (Constants.SELECT_YEAR!="0") {
				dialogCallBackInter.refreshActivity(Constants.SELECT_YEAR);
				dismiss();
			}else{
				Toast.makeText(getContext(), "请选择年份", Toast.LENGTH_SHORT).show();
			}
			break;

		default:
			break;
		}
	}

}
