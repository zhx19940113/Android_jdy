/**
 * Copyright (c) 2015
 *
 * Licensed under the UCG License, Version 1.0 (the "License");
 */
package com.app.jdy.ui;

import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.app.jdy.R;
import com.app.jdy.utils.Constants;
import com.app.jdy.utils.DialogCallBackInter;
import com.app.jdy.utils.HttpUtils;
import com.app.jdy.utils.URLs;
import com.app.jdy.widget.SelectYearDialog;
import com.db.chart.Tools;
import com.db.chart.listener.OnEntryClickListener;
import com.db.chart.model.LineSet;
import com.db.chart.view.LineChartView;
import com.db.chart.view.animation.Animation;
import com.db.chart.view.animation.style.DashAnimation;
import com.umeng.analytics.MobclickAgent;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyCommunityActivity extends Activity implements OnClickListener {

	/**
	 * 返回按钮
	 */
	private ImageView mBackImg;
	/**
	 * 标题
	 */
	private TextView title;
	/**
	 * hander
	 */
	private Handler handler;
	/**
	 * 服务的json数据
	 */
	private String dataJson;
	/**
	 * 用户ID号
	 */
	private String ID;
	/**
	 * 我的圈子的总值组件
	 */
	private TextView my_community_sum;
	/**
	 * 点击选择年份的组件
	 */
	private TextView select_year_click;
	/**
	 * 显示年份的组件
	 */
	private TextView select_year;

	private final TimeInterpolator enterInterpolator = new DecelerateInterpolator(
			1.5f);
	private final TimeInterpolator exitInterpolator = new AccelerateInterpolator();
	private static float mCurrOverlapFactor;
	private static int[] mCurrOverlapOrder;
	private static float mOldOverlapFactor;
	private static int[] mOldOverlapOrder;

	private final Runnable mEnterEndAction = new Runnable() {
		@Override
		public void run() {
			// mPlayBtn.setEnabled(true);
		}
	};

	/**
	 * Line
	 */
	private static int LINE_MAX = 20;
	private final static int LINE_MIN = 0;
	private final static String[] lineLabels = { "", "1月", "2月", "3月", "4月",
			"5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月", "" };
	private final static float[][] lineValues = { { 0f, 0f, 0f, 0f, 0f, 0f, 0f,
			0f, 0f, 0f, 0f, 0f, 0f, 0f } };
	private static LineChartView my_community_linechart;
	private Paint mLineGridPaint;
	private TextView mLineTooltip;

	private final OnEntryClickListener lineEntryListener = new OnEntryClickListener() {
		@Override
		public void onClick(int setIndex, int entryIndex, Rect rect) {
			System.out.println(setIndex);
			System.out.println(entryIndex);
			if (mLineTooltip == null)
				showLineTooltip(setIndex, entryIndex, rect);
			else
				dismissLineTooltip(setIndex, entryIndex, rect);
		}
	};

	private final OnClickListener lineClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (mLineTooltip != null)
				dismissLineTooltip(-1, -1, null);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_community);

		mCurrOverlapFactor = 1;

		mOldOverlapFactor = 1;

		initView();

		getDate();

		initLineChart();

		updateLineChart();

		mBackImg.setOnClickListener(this);
		select_year_click.setOnClickListener(this);

	}

	public void initView() {
		Calendar now = Calendar.getInstance();  
		Constants.SELECT_YEAR = now.get(Calendar.YEAR)+"";
		mBackImg = (ImageView) findViewById(R.id.back_img);
		mBackImg.setVisibility(View.VISIBLE);
		title = (TextView) findViewById(R.id.title_tv);
		title.setText("我的圈子");
		my_community_sum = (TextView) findViewById(R.id.my_community_sum);
		select_year_click = (TextView) findViewById(R.id.select_year_click);
		select_year = (TextView) findViewById(R.id.select_year);

		SharedPreferences userPreferences = getSharedPreferences(
				"umeng_general_config", Context.MODE_PRIVATE);
		ID = userPreferences.getString("ID", "").trim();

		handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					Toast.makeText(MyCommunityActivity.this, Constants.NO_INTENT_TIPS,
							Toast.LENGTH_SHORT).show();
					break;
				case 1:
					Log.v("ppppppppppppppppppppppppppp", dataJson);
					try {
						JSONArray jsonArray = new JSONArray(dataJson);
						for (int i = 0; i < lineValues[0].length; i++) {
							lineValues[0][i] = 0;
						}
						for (int i = 0; i < jsonArray.length(); i++) {
							lineValues[0][jsonArray.getJSONObject(i).getInt(
									"month")] = (float) jsonArray
									.getJSONObject(i).getDouble("total");
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					my_community_sum.setText(getSumValue() + "");
					select_year.setText(Constants.SELECT_YEAR+"年");
					getMaxValue();
					updateLineChart();
					break;
				case 2:
					for (int i = 0; i < lineValues[0].length; i++) {
						lineValues[0][i] = 0;
					}
					LINE_MAX = 20;
					my_community_sum.setText("0");
					select_year.setText(Constants.SELECT_YEAR+"年");
					updateLineChart();
					Toast.makeText(MyCommunityActivity.this, "抱歉，未能找到该年份的数据",
							Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
				}
			}
		};
	}

	public void getMaxValue() {
		int max = (int) lineValues[0][0];
		for (int i = 1; i < lineValues[0].length; i++) {
			if (max < lineValues[0][i]) {
				max = (int) lineValues[0][i];
			}
		}
		if (max<20) {
			LINE_MAX = 20;
		}else{
			LINE_MAX =  max;
		}
	}

	public int getSumValue() {
		int sum = 0;
		for (int i = 0; i < lineValues[0].length; i++) {
			sum += lineValues[0][i];
		}
		return sum;
	}

	private void getDate() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				Message msg = new Message();
				dataJson = HttpUtils.request(null, URLs.MY_COMMUNITY + "memberId="
						+ ID + "&year="+Constants.SELECT_YEAR);
				if (dataJson.length() != 0 && !dataJson.equals("0x110")) {
					if (!dataJson.equals("false")) {
						msg.what = 1;
					} else {
						msg.what = 2;
					}
				} else {
					msg.what = 0;
				}
				handler.sendMessage(msg);
			}
		});
		thread.start();
	}

	/*------------------------------------*
	 *              LINECHART             *
	 *------------------------------------*/

	private void initLineChart() {

		my_community_linechart = (LineChartView) findViewById(R.id.my_community_linechart);
		my_community_linechart.setOnEntryClickListener(lineEntryListener);
		my_community_linechart.setOnClickListener(lineClickListener);

		mLineGridPaint = new Paint();
		mLineGridPaint
				.setColor(this.getResources().getColor(R.color.line_grid));
		mLineGridPaint
				.setPathEffect(new DashPathEffect(new float[] { 5, 5 }, 0));
		mLineGridPaint.setStyle(Paint.Style.STROKE);
		mLineGridPaint.setAntiAlias(true);
		mLineGridPaint.setStrokeWidth(Tools.fromDpToPx(.75f));
	}

	private void updateLineChart() {

		my_community_linechart.reset();

		LineSet dataSet = new LineSet();
		dataSet.addPoints(lineLabels, lineValues[0]);
		dataSet.setDots(true)
				.setDotsColor(this.getResources().getColor(R.color.white))
				.setDotsRadius(Tools.fromDpToPx(5))
				.setDotsStrokeThickness(Tools.fromDpToPx(2))
				.setDotsStrokeColor(this.getResources().getColor(R.color.title))
				.setLineColor(this.getResources().getColor(R.color.title))
				.setLineThickness(Tools.fromDpToPx(3)).beginAt(1)
				.endAt(lineLabels.length - 1);
				// .setSmooth(true)
//				.setDashed(true);
		my_community_linechart.addData(dataSet);

		// dataSet = new LineSet();
		// dataSet.addPoints(lineLabels, lineValues[1]);
		// dataSet.setLineColor(this.getResources().getColor(R.color.line))
		// .setLineThickness(Tools.fromDpToPx(3))
		// .setSmooth(true)
		// .setDashed(true);
		// mLineChart.addData(dataSet);

		my_community_linechart
				.setBorderSpacing(Tools.fromDpToPx(4))
				.setGrid(LineChartView.GridType.HORIZONTAL, mLineGridPaint)
				.setXAxis(false)
				// .setXLabels(XController.LabelPosition.OUTSIDE)
				// .setYAxis(false)
				// .setYLabels(YController.LabelPosition.OUTSIDE)
				.setAxisBorderValues(LINE_MIN, LINE_MAX,
						(LINE_MAX - LINE_MIN) / 5).setLabelsMetric("")
				.setLabelColor(getResources().getColor(R.color.title))
				.show(new Animation().setEndAction(mEnterEndAction));
		// .show();
		my_community_linechart.animateSet(0, new DashAnimation());
	}

	@SuppressLint("NewApi")
	private void showLineTooltip(int setIndex, int entryIndex, Rect rect) {

		mLineTooltip = (TextView) getLayoutInflater().inflate(
				R.layout.circular_tooltip, null);
		mLineTooltip.setText(Integer
				.toString((int) lineValues[setIndex][entryIndex]));

		LayoutParams layoutParams = new LayoutParams(
				(int) Tools.fromDpToPx(35), (int) Tools.fromDpToPx(35));
		layoutParams.leftMargin = rect.centerX() - layoutParams.width / 2;
		layoutParams.topMargin = rect.centerY() - layoutParams.height / 2;
		mLineTooltip.setLayoutParams(layoutParams);

		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
			mLineTooltip.setPivotX(layoutParams.width / 2);
			mLineTooltip.setPivotY(layoutParams.height / 2);
			mLineTooltip.setAlpha(0);
			mLineTooltip.setScaleX(0);
			mLineTooltip.setScaleY(0);
			mLineTooltip.animate().setDuration(150).alpha(1).scaleX(1)
					.scaleY(1).rotation(360).setInterpolator(enterInterpolator);
		}

		my_community_linechart.showTooltip(mLineTooltip);
	}

	@SuppressLint("NewApi")
	private void dismissLineTooltip(final int setIndex, final int entryIndex,
			final Rect rect) {

		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			mLineTooltip.animate().setDuration(100).scaleX(0).scaleY(0)
					.alpha(0).setInterpolator(exitInterpolator)
					.withEndAction(new Runnable() {
						@Override
						public void run() {
							my_community_linechart.removeView(mLineTooltip);
							mLineTooltip = null;
							if (entryIndex != -1)
								showLineTooltip(setIndex, entryIndex, rect);
						}
					});
		} else {
			my_community_linechart.dismissTooltip(mLineTooltip);
			mLineTooltip = null;
			if (entryIndex != -1)
				showLineTooltip(setIndex, entryIndex, rect);
		}
	}
	
	private DialogCallBackInter dialogCallBackInter = new DialogCallBackInter() {
		
		@Override
		public void refreshActivity(String text) {
			getDate();
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_img:
			finish();
			break;
		case R.id.select_year_click:
			Constants.SELECT_YEAR = "0";
			SelectYearDialog selectYearDialog = new SelectYearDialog(
					MyCommunityActivity.this,R.style.ForwardDialog,dialogCallBackInter);
			selectYearDialog.show();
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
