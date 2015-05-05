/**
 * Copyright (c) 2015
 *
 * Licensed under the UCG License, Version 1.0 (the "License");
 */
package com.app.jdy.adapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.jdy.R;
import com.app.jdy.entity.ProductEntity;
import com.app.jdy.utils.ChineseMoneyUtils;
import com.app.jdy.utils.CommonUtils;
import com.app.jdy.utils.Constants;
import com.app.jdy.utils.DateUtils;
import com.app.jdy.utils.StringUtils;

/**
 * description :自定义优化ListViewAdapter
 * 
 * @version 1.0
 * @author zhonghuixiong
 * @createtime : 2015-1-22 下午5:45:48
 * 
 *             修改历史: 修改人 修改时间 修改内容 --------------- -------------------
 *             ----------------------------------- zhonghuixiong 2015-1-22
 *             下午5:45:48
 * 
 */
public class ListViewAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private Context context;
	/**
	 * 产品List数据源
	 */
	private List<ProductEntity> productList = new ArrayList<ProductEntity>();

	public ListViewAdapter(Context context, List<ProductEntity> productList) {
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.productList = productList;
	}

	@Override
	public int getCount() {
		return productList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		if (convertView == null || convertView.getTag() == null) {
			convertView = inflater.inflate(R.layout.listview_item, null);
			final TextView type_textview = (TextView) convertView.findViewById(R.id.type_textview);
			final TextView textView1 = (TextView) convertView.findViewById(R.id.textView1);
			final TextView textView2 = (TextView) convertView.findViewById(R.id.textView2);
			final TextView textView3 = (TextView) convertView.findViewById(R.id.textView3);
			final TextView textView4 = (TextView) convertView.findViewById(R.id.textView4);
			final TextView textView5 = (TextView) convertView.findViewById(R.id.textView5);
			final TextView textView6 = (TextView) convertView.findViewById(R.id.textView6);
			final TextView textView7 = (TextView) convertView.findViewById(R.id.textView7);
			final TextView textView8 = (TextView) convertView.findViewById(R.id.textView8);

			ProductEntity product = productList.get(position);
			// 产品描述
			textView1.setText(StringUtils.isEmpty(product.getProductSubject(), ""));
			// 产品名称
			textView8.setText(StringUtils.isEmpty(product.getName(), Constants.NO_CONTENT_N));
			type_textview.setText(CommonUtils.TypeforCode(product.getProdTypeCode()));
			if ("bank".equals(product.getProdTypeCode())) {
				textView5.setText("起购金额");
				textView2.setText(ChineseMoneyUtils.isNumberNull(product.getMinSubsAmount(),
						ChineseMoneyUtils.numWithDigit(product.getMinSubsAmount()), Constants.NO_CONTENT_N));
				textView6.setText("预期收益");
				textView3.setText(ChineseMoneyUtils.isNumberNull(product.getExpeAnnuRevnue(),
						product.getExpeAnnuRevnue() + "%", Constants.NO_CONTENT_N));
				textView7.setText("期限");
				textView4.setText(ChineseMoneyUtils.isNumberNull(product.getPeriod(),
						DateUtils.parseDayToStr(product.getPeriod()), Constants.NO_CONTENT_N));
			} else if ("insurance".equals(product.getProdTypeCode())) {
				textView5.setText("保险类型");
				textView2.setText(StringUtils.isEmpty(product.getInsuranceType(), Constants.NO_CONTENT_N));
				textView6.setText("期限");
				textView3.setText(ChineseMoneyUtils.isNumberNull(product.getPeriod(),
						DateUtils.parseDayToStr(product.getPeriod()), Constants.NO_CONTENT_N));
				textView7.setText("发行公司");
				textView4.setText(StringUtils.isEmpty(product.getIssuer(), Constants.NO_CONTENT_N));
			} else if ("pubFunds".equals(product.getProdTypeCode())) {
				textView5.setText("起购金额");
				textView2.setText(ChineseMoneyUtils.isNumberNull(product.getMinSubsAmount(),
						ChineseMoneyUtils.numWithDigit(product.getMinSubsAmount()), Constants.NO_CONTENT_N));
				textView6.setText("基金管理人");
				textView3.setText(StringUtils.isEmpty(product.getFundDirector(), Constants.NO_CONTENT_N));
				textView7.setText("基金类型");
				textView4.setText(StringUtils.isEmpty(product.getIssuer(), Constants.NO_CONTENT_N));
			} else if ("priFunds".equals(product.getProdTypeCode())) {
				textView5.setText("起购金额");
				textView2.setText(ChineseMoneyUtils.isNumberNull(product.getMinSubsAmount(),
						ChineseMoneyUtils.numWithDigit(product.getMinSubsAmount()), Constants.NO_CONTENT_N));
				textView6.setText("期限");
				textView3.setText(ChineseMoneyUtils.isNumberNull(product.getPeriod(),
						DateUtils.parseDayToStr(product.getPeriod()), Constants.NO_CONTENT_N));
				textView7.setText("管理人");
				textView4.setText(StringUtils.isEmpty(product.getFundDirector(), Constants.NO_CONTENT_N));
			} else if ("trust".equals(product.getProdTypeCode()) || "captManage".equals(product.getProdTypeCode())) {
				textView5.setText("投资方向");
				textView2.setText(StringUtils.isEmpty(product.getInvestDirection(), Constants.NO_CONTENT_N));
				textView6.setText("预期收益");
				textView3.setText(ChineseMoneyUtils.isNumberNull(product.getExpeAnnuRevnue(),
						product.getExpeAnnuRevnue() + "%", Constants.NO_CONTENT_N));
				textView7.setText("期限");
				textView4.setText(ChineseMoneyUtils.isNumberNull(product.getPeriod(),
						DateUtils.parseDayToStr(product.getPeriod()), Constants.NO_CONTENT_N));
			} else if ("credit".equals(product.getProdTypeCode())) {
				textView5.setText("预期收益");
				textView2.setText(ChineseMoneyUtils.isNumberNull(product.getExpeAnnuRevnue(),
						product.getExpeAnnuRevnue() + "%", Constants.NO_CONTENT_N));
				textView6.setText("期限");
				textView3.setText(ChineseMoneyUtils.isNumberNull(product.getPeriod(),
						DateUtils.parseDayToStr(product.getPeriod()), Constants.NO_CONTENT_N));
				textView7.setText("管理人");
				textView4.setText(StringUtils.isEmpty(product.getFundDirector(), Constants.NO_CONTENT_N));
			} else if ("equity".equals(product.getProdTypeCode())) {
				textView5.setText("管理人");
				textView2.setText(StringUtils.isEmpty(product.getFundDirector(), Constants.NO_CONTENT_N));
				textView6.setText("投资方向");
				textView3.setText(StringUtils.isEmpty(product.getFundDirector(), Constants.NO_CONTENT_N));
				textView7.setText("起购金额");
				textView4.setText(StringUtils.isEmpty(product.getMinSubsAmount().toString(), Constants.NO_CONTENT_N));
			}
		}
		return convertView;
	}
}