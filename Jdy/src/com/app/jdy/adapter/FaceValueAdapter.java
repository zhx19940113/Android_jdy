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
public class FaceValueAdapter extends BaseAdapter {

	private Context context;// 运行上下文
	private LayoutInflater listContainer;// 视图容器
	private int itemViewResource;// 自定义项视图源
	private List<FaceValue> list;

	public FaceValueAdapter(Context context, int resource, List<FaceValue> list) {
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
			TextView face_user_time = (TextView) convertView
					.findViewById(R.id.face_user_time);
			TextView face_user_name = (TextView) convertView
					.findViewById(R.id.face_user_name);
			TextView face_user_momey = (TextView) convertView
					.findViewById(R.id.face_user_momey);
			if (position%2.0==0) {
				face_user_time.setBackgroundResource(R.drawable.item_color);
				face_user_name.setBackgroundResource(R.drawable.item_color);
				face_user_momey.setBackgroundResource(R.drawable.item_color);
			}
			face_user_name.setText(list.get(position).getName());
			face_user_momey.setText("+"+list.get(position).getMoney()); 
			face_user_time.setText(changeTime(list.get(position).getTime()));
		}
		return convertView;
	}
	
	public String changeTime(String time){
		String arr[] = time.split(" ");
		return arr[0].replace("-", "/");
	}

}
