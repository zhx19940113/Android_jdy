/**
 * Copyright (c) 2015
 *
 * Licensed under the UCG License, Version 1.0 (the "License");
 */
package com.app.jdy.widget;

import com.app.jdy.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * description : app新版本下载提示框
 *
 * @version 1.0
 * @author zhoufeng
 * @createtime : 2015-3-16 下午9:11:39
 * 
 * 修改历史:
 * 修改人                                          修改时间                                                     修改内容
 * --------------- ------------------- -----------------------------------
 * zhoufeng        2015-3-16 下午9:11:39 
 *
 */
public class DownAppDialog extends Dialog{
	/**
	 * 上下文
	 */
	private Context context;
	/**
	 * 警告框主题
	 */
	private int theme;
	/**
	 * 下载进度警告框
	 */
	private DownProgressDialog downProgressDialog;
	/**
	 * 版本描述
	 */
	private String versionDescrition;
	/**
	 * 
	 * 描述:构造方法
	 * @param context 上下文
	 * @param theme 警告框主题
	 */
	public DownAppDialog(Context context,int theme) {
		super(context,theme);
		this.context = context;
		this.theme = theme;
		//实例化进度条警告框
		downProgressDialog = new DownProgressDialog(context, theme);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customdown_dialog);
		
	}
	/**
	 * 
	 * @author zhoufeng
	 * @createtime 2015-3-16 下午9:41:45
	 * @Decription 获取新版本描述信息
	 *
	 * @return string 新版本信息
	 */
	public String getVersionDescrition() {
		return versionDescrition;
	}
	/**
	 * 
	 * @author zhoufeng
	 * @createtime 2015-3-16 下午9:42:09
	 * @Decription 设置新版本信息
	 *
	 * @param versionDescrition 新版本信息
	 */
	public void setVersionDescrition(String versionDescrition) {
		this.versionDescrition = versionDescrition;
	}
	/**
	 * 
	 * @author zhoufeng
	 * @createtime 2015-3-16 下午9:42:48
	 * @Decription 显示新版本的警告框
	 *
	 */
	public void showDownMessage() {
		setCanceledOnTouchOutside(true);
		show();
		/**
		 * 下载按钮事件
		 */
		((Button) findViewById(R.id.downbutton))
				.setOnClickListener(new Button.OnClickListener() {
					@Override
					public void onClick(View v) {
						//为进度条警告框添加新版本信息
						downProgressDialog.setVersionDescrition(versionDescrition);
						//显示进度条警告框
						downProgressDialog.showDownloadDialog();
						//关闭新版本提示警告框
						dismiss();
					}
				});
		/**
		 * 取消按钮事件
		 */
		((ImageView) findViewById(R.id.customcancle))
				.setOnClickListener(new ImageView.OnClickListener() {

					@Override
					public void onClick(View v) {
						//关闭新版本提示警告框
						dismiss();
					}
				});
		((TextView) findViewById(R.id.versionDescrition))
				.setText(versionDescrition);
	}
}

	