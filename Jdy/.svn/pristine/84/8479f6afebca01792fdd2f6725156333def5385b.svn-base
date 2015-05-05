/**
 * Copyright (c) 2015
 *
 * Licensed under the UCG License, Version 1.0 (the "License");
 */
package com.app.jdy.adapter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.app.jdy.R;
import com.app.jdy.entity.BankCard;
import com.app.jdy.entity.FaceValue;
import com.app.jdy.entity.Grade;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * description :
 * 
 * @version 1.0
 * @author zhoufeng
 * @createtime : 2015-1-10 下午9:25:04
 * 
 *             修改历史: 修改人 修改时间 修改内容 --------------- -------------------
 *             ----------------------------------- zhoufeng 2015-1-10 下午9:25:04
 * 
 */
public class GradeAdapter extends BaseAdapter {

	private Context context;// 运行上下文
	private LayoutInflater listContainer;// 视图容器
	private int itemViewResource;// 自定义项视图源
	private List<Grade> list;

	public GradeAdapter(Context context, int resource, List<Grade> list) {
		this.context = context;
		this.listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文
		this.itemViewResource = resource;
		this.list = list;
	}

	@Override
	public boolean isEnabled(int position) {
		return false;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null || convertView.getTag() == null) {
			// 获取list_item布局文件的视图
			convertView = listContainer.inflate(this.itemViewResource, null);
			TextView grade_item = (TextView) convertView
					.findViewById(R.id.grade_item);
			TextView score_item = (TextView) convertView
					.findViewById(R.id.score_item);
			grade_item.setText(list.get(position).getGrade());
			score_item.setText(list.get(position).getScore());
		}
		return convertView;
	}

	public String changeTime(String time) {
		String arr[] = time.split(" ");
		return arr[0].replace("-", "/");
	}

}
