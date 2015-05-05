/**
 * Copyright (c) 2015
 *
 * Licensed under the UCG License, Version 1.0 (the "License");
 */
package com.app.jdy.adapter;

import java.util.HashMap;
import java.util.List;

import com.app.jdy.R;
import com.app.jdy.utils.Constants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * 
 * description :
 * 
 * @version 1.0
 * @author zhoufeng
 * @createtime : 2015-1-30 下午4:26:55
 * 
 *             修改历史: 修改人 修改时间 修改内容 --------------- -------------------
 *             ----------------------------------- zhoufeng 2015-1-30 下午4:26:55
 * 
 */
public class SelectYearAdapter extends BaseAdapter {

	private Context context;
	private String[] beans;
	// 用于记录每个RadioButton的状态，并保证只可选一个
	HashMap<String, Boolean> states = new HashMap<String, Boolean>();

	class ViewHolder {

		TextView tvName;
		RadioButton rb_state;
		LinearLayout y_linear;
	}

	public SelectYearAdapter(Context context, String[] beans) {
		// TODO Auto-generated constructor stub
		this.beans = beans;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return beans.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return beans[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// 页面
		ViewHolder holder;
		String bean = beans[position];
		LayoutInflater inflater = LayoutInflater.from(context);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.select_year_item, null);
			holder = new ViewHolder();
			// holder.rb_state = (RadioButton) convertView
			// .findViewById(R.id.rb_light);
			holder.tvName = (TextView) convertView
					.findViewById(R.id.year_number);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvName.setText(bean);
		LinearLayout select_year_linearlayout = (LinearLayout) convertView
				.findViewById(R.id.select_year_linearlayout);
		holder.y_linear = select_year_linearlayout;
		final RadioButton radio = (RadioButton) convertView
				.findViewById(R.id.select_year_radio);
		holder.rb_state = radio;
		holder.rb_state.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				// 重置，确保最多只有一项被选中
				for (String key : states.keySet()) {
					states.put(key, false);

				}
				Constants.SELECT_YEAR = beans[position];
				states.put(String.valueOf(position), radio.isChecked());
				SelectYearAdapter.this.notifyDataSetChanged();
			}
		});
		holder.y_linear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 重置，确保最多只有一项被选中
				for (String key : states.keySet()) {
					states.put(key, false);

				}
				Constants.SELECT_YEAR = beans[position];
				radio.setChecked(true);
				states.put(String.valueOf(position), radio.isChecked());
				SelectYearAdapter.this.notifyDataSetChanged();
			}
		});

		boolean res = false;
		if (states.get(String.valueOf(position)) == null
				|| states.get(String.valueOf(position)) == false) {
			res = false;
			states.put(String.valueOf(position), false);
		} else {
			res = true;
		}
		holder.rb_state.setChecked(res);
		return convertView;
	}
}
