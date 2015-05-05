/**
 * Copyright (c) 2015
 *
 * Licensed under the UCG License, Version 1.0 (the "License");
 */
package com.app.jdy.adapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.app.jdy.R;
import com.app.jdy.ui.ImagePagerActivity;
import com.app.jdy.utils.BitmapUtils;
import com.app.jdy.utils.URLs;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
public class DetailAdapter extends BaseAdapter {
	/**
	 * 运行上下文
	 */
	private Context context;
	/**
	 * 视图容器
	 */
	private LayoutInflater listContainer;
	/**
	 * 自定义项视图源
	 */
	private int itemViewResource;
	/**
	 * json数据
	 */
	private String dataJson;
	/**
	 * linearyout布局
	 */
	private LinearLayout detail_linearyout;
	/**
	 * 产品详情title textview控件
	 */
	private TextView detail_item_title;
	/**
	 * 产品详情text textview控件
	 */
	private TextView detail_item_text;

	JSONObject jsonObject;

	JSONObject product;

	JSONObject productChild;
	/**
	 * 判断是否设置颜色的标示
	 */
	private Boolean isSetColor = true;
	/**
	 * 判断的标示
	 */
	private Boolean isFlag = false;
	/**
	 * 图片组件
	 */
	private ImageView detail_image_item_text;

	private ArrayList bankList;

	private ArrayList insuranceList;

	private ArrayList pubFundsList;

	private ArrayList priFundsList;

	private ArrayList trustList;

	private ArrayList captManageList;

	private ArrayList creditList;

	private ArrayList equityList;

	private HashMap<String, String> bankMap;

	private HashMap<String, String> insuranceMap;

	private HashMap<String, String> pubFundsMap;

	private HashMap<String, String> priFundsMap;

	private HashMap<String, String> trustMap;

	private HashMap<String, String> captManageMap;

	private HashMap<String, String> creditMap;

	private HashMap<String, String> equityMap;

	private Bitmap logo;

	private Bitmap prodImage;

	public DetailAdapter(Context context, int resource, String dataJson, Bitmap logo, Bitmap prodImage) {
		this.context = context;
		this.listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文
		this.itemViewResource = resource;
		this.dataJson = dataJson;
		this.logo = logo;
		this.prodImage = prodImage;
		initHashMap();

	}

	@Override
	public boolean isEnabled(int position) {
		return false;
	}

	@Override
	public int getCount() {
		return 35;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null || convertView.getTag() == null) {
			// 获取list_item布局文件的视图
			convertView = listContainer.inflate(this.itemViewResource, null);
			detail_item_title = (TextView) convertView.findViewById(R.id.detail_item_title);
			detail_item_text = (TextView) convertView.findViewById(R.id.detail_item_text);
			detail_linearyout = (LinearLayout) convertView.findViewById(R.id.detail_linearyout);
			try {
				jsonObject = new JSONObject(dataJson);
				product = new JSONObject(jsonObject.getString("product1"));
				productChild = new JSONObject(jsonObject.getString("product2"));
				final String[] urls = { URLs.HTTP + URLs.HOST + product.getString("issuerLogo"),
						URLs.HTTP + URLs.HOST + product.getString("prodImage") };
				

				switch (position) {
				case 0:
					showDetail("产品名称", product.getString("name"));
					break;
				case 1:
					showDetail("发行方", product.getString("issuer"));
					break;
				case 2:
					showDetail("发行方类型", product.getString("issuerTypeName"));
					break;
				case 3:
					convertView = listContainer.inflate(R.layout.detail_image_item, null);
					detail_item_title = (TextView) convertView.findViewById(R.id.detail_image_item_title);
					detail_image_item_text = (ImageView) convertView.findViewById(R.id.detail_image_item_text);
					detail_linearyout = (LinearLayout) convertView.findViewById(R.id.detail_linearyout);
					ImageLoader.getInstance().displayImage(urls[0], detail_image_item_text);
					detail_image_item_text.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							imageBrower(0, urls);
						}
					});
					showDetail("发行方logo", product.getString("issuerLogo"));
					break;
				case 4:
					convertView = listContainer.inflate(R.layout.detail_image_item, null);
					detail_item_title = (TextView) convertView.findViewById(R.id.detail_image_item_title);
					detail_image_item_text = (ImageView) convertView.findViewById(R.id.detail_image_item_text);
					detail_linearyout = (LinearLayout) convertView.findViewById(R.id.detail_linearyout);
					ImageLoader.getInstance().displayImage(urls[1], detail_image_item_text);
					detail_image_item_text.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							imageBrower(1, urls);
						}
					});
					showDetail("产品图片", product.getString("prodImage"));
					break;
				case 5:
					showDetail("期限(天)", product.getString("period"));
					break;
				case 6:
					showDetail("收益类型", product.getString("profitTypeName"));
					break;
				case 7:
					showDetail("币种", product.getString("currencyTypeName"));
					break;
				case 8:
					showDetail("产品状态", product.getString("statusName"));
					break;
				case 9:
					showDetail("参考最高收益(%)", product.getString("topGain"));
					break;
				case 10:
					showDetail("销售开始日期", product.getString("saleStart"));
					break;
				case 11:
					showDetail("销售结束日期", product.getString("saleEnd"));
					break;
				case 12:
					showDetail("收益开始日期", product.getString("profitStart"));
					break;
				case 13:
					showDetail("收益结束日期", product.getString("profitEnd"));
					break;
				case 14:
					showDetail("投资方向", product.getString("investDirection"));
					break;
				case 15:
					showDetail("基金类型", product.getString("fundTypeName"));
					break;
				case 16:
					showDetail("基金管理人", product.getString("fundDirector"));
					break;
				case 17:
					showDetail("保险类型", product.getString("insuranceTypeName"));
					break;
				case 18:
					choiceType(product.getString("prodTypeCode"), "0", 0);
					break;
				case 19:
					choiceType(product.getString("prodTypeCode"), "1", 1);
					break;
				case 20:
					choiceType(product.getString("prodTypeCode"), "2", 2);
					break;
				case 21:
					choiceType(product.getString("prodTypeCode"), "3", 3);
					break;
				case 22:
					choiceType(product.getString("prodTypeCode"), "4", 4);
					break;
				case 23:
					choiceType(product.getString("prodTypeCode"), "5", 5);
					break;
				case 24:
					choiceType(product.getString("prodTypeCode"), "6", 6);
					break;
				case 25:
					choiceType(product.getString("prodTypeCode"), "7", 7);
					break;
				case 26:
					choiceType(product.getString("prodTypeCode"), "8", 8);
					break;
				case 27:
					choiceType(product.getString("prodTypeCode"), "9", 9);
					break;
				case 28:
					choiceType(product.getString("prodTypeCode"), "10", 10);
					break;
				case 29:
					choiceType(product.getString("prodTypeCode"), "11", 11);
					break;
				case 30:
					choiceType(product.getString("prodTypeCode"), "12", 12);
					break;
				case 31:
					choiceType(product.getString("prodTypeCode"), "13", 13);
					break;
				case 32:
					choiceType(product.getString("prodTypeCode"), "14", 14);
					break;
				case 33:
					choiceType(product.getString("prodTypeCode"), "15", 15);
					break;
				case 34:
					choiceType(product.getString("prodTypeCode"), "16", 16);
					break;
				case 35:
					choiceType(product.getString("prodTypeCode"), "17", 17);
					break;
				
				default:
					break;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return convertView;
	}

	public void choiceType(String type, String code, int key) throws JSONException {
		if (type.equals("bank")) {
			selectDetail(bankMap, bankList, code, key, 4);
		} else if (type.equals("insurance")) {
			selectDetail(insuranceMap, insuranceList, code, key, 3);
		} else if (type.equals("pubFunds")) {
			selectDetail(pubFundsMap, pubFundsList, code, key, 17);
		} else if (type.equals("priFunds")) {
			selectDetail(priFundsMap, priFundsList, code, key, 18);
		} else if (type.equals("trust")) {
			selectDetail(trustMap, trustList, code, key, 4);
		} else if (type.equals("captManage")) {
			selectDetail(captManageMap, captManageList, code, key, 4);
		} else if (type.equals("credit")) {
			selectDetail(creditMap, creditList, code, key, 7);
		} else if (type.equals("equity")) {
			selectDetail(equityMap, equityList, code, key, 7);
		}
	}

	public void initHashMap() {
		bankList = new ArrayList();
		bankList.add("subsFee");
		bankList.add("prodType");
		bankList.add("entrustLimit");
		bankList.add("entrustIncrease");
		insuranceList = new ArrayList();
		insuranceList.add("breakEven");
		insuranceList.add("backDate");
		insuranceList.add("suitablePeople");
		pubFundsList = new ArrayList();
		pubFundsList.add("shortName");
		pubFundsList.add("newestWorth");
		pubFundsList.add("totalWorth");
		pubFundsList.add("fundCode");
		pubFundsList.add("expectProfit");
		pubFundsList.add("fundManager");
		pubFundsList.add("createDate");
		pubFundsList.add("fiirstRaise");
		pubFundsList.add("newestRaise");
		pubFundsList.add("applyAtoneStatus");
		pubFundsList.add("applyAtoneTypeName");
		pubFundsList.add("applyRate");
		pubFundsList.add("atoneRate");
		pubFundsList.add("manageRate");
		pubFundsList.add("investTarget");
		pubFundsList.add("captialRate");
		pubFundsList.add("profitPrinciple");
		priFundsList = new ArrayList();
		priFundsList.add("shortName");
		priFundsList.add("unitWorth");
		priFundsList.add("newestWorth");
		priFundsList.add("totalWorth");
		priFundsList.add("createDate");
		priFundsList.add("openDate");
		priFundsList.add("endDate");
		priFundsList.add("closedPeriod");
		priFundsList.add("standClosedPeriod");
		priFundsList.add("subsRate");
		priFundsList.add("manageRate");
		priFundsList.add("redemptionRate");
		priFundsList.add("depositBank");
		priFundsList.add("unstableRate");
		priFundsList.add("limitBuy");
		priFundsList.add("pubArea");
		priFundsList.add("investTactics");
		priFundsList.add("fundScope");
		trustList = new ArrayList();
		trustList.add("trustType");
		trustList.add("saleDate");
		trustList.add("pubArea");
		trustList.add("projectArea");
		captManageList = new ArrayList();
		captManageList.add("capType");
		captManageList.add("saleDate");
		captManageList.add("pubArea");
		captManageList.add("projectArea");
		creditList = new ArrayList();
		creditList.add("borrowPurpose");
		creditList.add("targetTotal");
		creditList.add("repayPeriod");
		creditList.add("ensureType");
		creditList.add("repaymentType");
		creditList.add("borrowProtocol");
		creditList.add("projectArea");
		equityList = new ArrayList();
		equityList.add("borrowPurpose");
		equityList.add("targetTotal");
		equityList.add("repayPeriod");
		equityList.add("ensureType");
		equityList.add("repaymentType");
		equityList.add("borrowProtocol");
		equityList.add("projectArea");

		bankMap = new HashMap<String, String>();
		bankMap.put("0", "认购费(元)");
		bankMap.put("1", "产品类型");
		bankMap.put("2", "委托起始金额(元)");
		bankMap.put("3", "委托金额递增单位(元)");
		insuranceMap = new HashMap<String, String>();
		insuranceMap.put("0", "保本类型");
		insuranceMap.put("1", "赎回到账时间");
		insuranceMap.put("2", "适用人群");
		pubFundsMap = new HashMap<String, String>();
		pubFundsMap.put("0", "基本简称");
		pubFundsMap.put("1", "最新净值(元)");
		pubFundsMap.put("2", "累计净值(元)");
		pubFundsMap.put("3", "基金代码");
		pubFundsMap.put("4", "预期收益");
		pubFundsMap.put("5", "基金经理");
		pubFundsMap.put("6", "成立日期");
		pubFundsMap.put("7", "首次募集规模(元)");
		pubFundsMap.put("8", "最新基金规模(元)");
		pubFundsMap.put("9", "申赎状态");
		pubFundsMap.put("10", "申赎状态名称");
		pubFundsMap.put("11", "申购费率(%)");
		pubFundsMap.put("12", "赎回费率(%)");
		pubFundsMap.put("13", "管理费率(%)");
		pubFundsMap.put("14", "投资目标");
		pubFundsMap.put("15", "资产组合比例");
		pubFundsMap.put("16", "收益分配原则");
		priFundsMap = new HashMap<String, String>();
		priFundsMap.put("0", "基金简称");
		priFundsMap.put("1", "单位净值(元)");
		priFundsMap.put("2", "最新净值(元)");
		priFundsMap.put("3", "累计净值(元)");
		priFundsMap.put("4", "成立日期");
		priFundsMap.put("5", "开放日");
		priFundsMap.put("6", "到期日");
		priFundsMap.put("7", "封闭期");
		priFundsMap.put("8", "准封闭期限(天)");
		priFundsMap.put("9", "认购费率");
		priFundsMap.put("10", "管理费率(%)");
		priFundsMap.put("11", "赎回费率(%)");
		priFundsMap.put("12", "托管银行");
		priFundsMap.put("13", "浮动管理费率(%)");
		priFundsMap.put("14", "追加最低认购(元)");
		priFundsMap.put("15", "发行地区");
		priFundsMap.put("16", "投资策略");
		priFundsMap.put("17", "基金规模");
		trustMap = new HashMap<String, String>();
		trustMap.put("0", "信托类型");
		trustMap.put("1", "发售日期");
		trustMap.put("2", "发行地区");
		trustMap.put("3", "项目所在地");
		captManageMap = new HashMap<String, String>();
		captManageMap.put("0", "资管类型");
		captManageMap.put("1", "发售日期");
		captManageMap.put("2", "发行地区");
		captManageMap.put("3", "项目所在地");
		creditMap = new HashMap<String, String>();
		creditMap.put("0", "借款目的");
		creditMap.put("1", "标的总额(元)");
		creditMap.put("2", "还款期限(天)");
		creditMap.put("3", "还款方式");
		creditMap.put("4", "还款协议");
		creditMap.put("5", "借款协议");
		creditMap.put("6", "项目所在地");
		equityMap = new HashMap<String, String>();
		equityMap.put("0", "借款目的");
		equityMap.put("1", "标的总额(元)");
		equityMap.put("2", "还款期限(天)");
		equityMap.put("3", "保障方式");
		equityMap.put("4", "还款方式");
		equityMap.put("5", "借款协议");
		equityMap.put("6", "项目所在地");
	}

	public void selectDetail(HashMap<String, String> map, ArrayList list, String code, int key, int num)
			throws JSONException {
		if (key < num) {
			showDetail(map.get(code), productChild.getString(list.get(key).toString()));
		} else {
			showDetail(map.get(code), "null");
		}
	}

	public void showDetail(String titleName, String textName) {
		if (!textName.equals("null") && !textName.equals("")) {
			detail_item_title.setText(titleName);

			if (titleName.equals("发行方logo")) {
//				detail_image_item_text.setImageBitmap(logo);
				detail_linearyout.setVisibility(View.GONE);
			}
			if (titleName.equals("产品图片")) {
				detail_linearyout.setVisibility(View.GONE);
//				detail_image_item_text.setImageBitmap(prodImage);
			} else {
				detail_item_text.setText(textName);
			}

			if (isFlag) {
				isSetColor = false;
			} else {
				isSetColor = true;
			}
			isFlag = false;
			if (isSetColor) {
				detail_item_title.setBackgroundResource(R.drawable.item_color);
				if (titleName.equals("发行方logo") || titleName.equals("产品图片")) {
					detail_image_item_text.setBackgroundResource(R.drawable.item_color);
				} else {
					detail_item_text.setBackgroundResource(R.drawable.item_color);
				}

				isSetColor = false;
				isFlag = true;
			}
		} else {
			detail_linearyout.setVisibility(View.GONE);
			if (isSetColor) {
				isSetColor = false;
			} else {
				isSetColor = true;
			}
		}

	}

	private void imageBrower(int position, String[] urls) {
		Intent intent = new Intent(context, ImagePagerActivity.class);
		// 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
		intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, urls);
		intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
		context.startActivity(intent);
	}
}
