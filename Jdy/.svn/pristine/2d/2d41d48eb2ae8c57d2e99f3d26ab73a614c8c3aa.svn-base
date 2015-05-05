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
import com.app.jdy.adapter.GradeAdapter;
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
public class GradeDialog extends Dialog{
	private Context context;
	private ListView grade_listview;
	private GradeAdapter gradeAdapter;

	public GradeDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public GradeDialog(Context context, int theme,GradeAdapter gradeAdapter) {
		super(context, theme);
		this.context = context;
		this.gradeAdapter = gradeAdapter;
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grade_dialog);
		initView();
	}

	public void initView() {
		grade_listview = (ListView) findViewById(R.id.grade_listview);
		grade_listview.setAdapter(gradeAdapter);
	}

}
