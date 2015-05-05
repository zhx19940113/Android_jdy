/**
 * Copyright (c) 2015
 *
 * Licensed under the UCG License, Version 1.0 (the "License");
 */
package com.app.jdy.adapter;

import java.util.List;

import com.app.jdy.R;
import com.app.jdy.entity.MsgList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * description :
 *
 * @version 1.0
 * @author Owater
 * @createtime : 2015-1-27 下午5:03:10
 * 
 * 修改历史:
 * 修改人                                          修改时间                                                     修改内容
 * --------------- ------------------- -----------------------------------
 * Owater        2015-1-27 下午5:03:10 
 *
 */
public class MsgListAdapter extends BaseAdapter {
	
	private Context context;//运行上下文
	private LayoutInflater listContainer;//视图容器
	private int itemViewResource;//自定义项视图源
	private List<MsgList> lists;//数据集合
	static class ListItemView{				//自定义控件集合  
        public TextView titleTextView;
	    public TextView contentTextView;
	    public TextView timeTextView;
	}
	
	public MsgListAdapter(Context context, List<MsgList> lists,int resource){
		this.context = context;
		this.listContainer = LayoutInflater.from(context);	//创建视图容器并设置上下文
		this.itemViewResource = resource;
		this.lists = lists;
	}

	@Override
	public int getCount() {
		return lists.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//自定义视图
		ListItemView listItemView = null;
		
		if(convertView == null || convertView.getTag() == null){
			//获取list_item布局文件的视图
			convertView = listContainer.inflate(this.itemViewResource, null);
			listItemView = new ListItemView();
			listItemView.titleTextView = (TextView)convertView.findViewById(R.id.msglist_title);
			listItemView.timeTextView = (TextView)convertView.findViewById(R.id.msglist_time);
		}else {
			listItemView = (ListItemView)convertView.getTag();
		}
		listItemView.titleTextView.setText(lists.get(position).getTitle());
		listItemView.timeTextView.setText(lists.get(position).getCreateTime());
		if (lists.get(position).getIsRead()==1) {
//			listItemView.contentTextView.setText("已读");
			listItemView.titleTextView.setTextColor(Color.rgb(195, 210, 207));
		}else {
//			listItemView.contentTextView.setText("未读");
//			listItemView.titleTextView.setTextColor(Color.rgb(0, 0, 0));
		}
		
		
		return convertView;
	}

}

	