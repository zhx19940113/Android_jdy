/**
 * Copyright (c) 2015
 *
 * Licensed under the UCG License, Version 1.0 (the "License");
 */
package com.app.jdy.widget;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

import com.app.jdy.R;
import com.app.jdy.utils.HttpUtils;
import com.app.jdy.utils.URLs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * description :进度条警告框
 *
 * @version 1.0
 * @author zhoufeng
 * @createtime : 2015-3-16 下午7:14:36
 * 
 * 修改历史:
 * 修改人                                          修改时间                                                     修改内容
 * --------------- ------------------- -----------------------------------
 * zhoufeng        2015-3-16 下午7:14:36 
 *
 */
public class DownProgressDialog extends Dialog{
	/**
	 * 上下文
	 */
	private Context context;
	/**
	 * 警告框主题
	 */
	private int theme;
	/**
	 * 更新ui的handler对象
	 */
	private Handler handler;
	/**
	 * 进度条对象
	 */
	private ProgressBar mProgress;
	/**
	 * 判断停止下载
	 */
	private boolean isStopDownload = false;
	/**
	 * 版本描述
	 */
	private String versionDescrition;
	/**
	 * 下载线程
	 */
	private Thread downLoadThread;
	/**
	 * 返回的安装包url
	 */
	private String apkUrl = URLs.GETNEWAPK;
	/**
	 * 下载包安装路径
	 */
	private static final String savePath = "/sdcard/JdyUpdate/";
	private static final String saveFileName = savePath + "JdyUpdate.apk";
	/**
	 * 下载文件大小
	 */
	private String progressSize;
	/**
	 * 格式化数据对象
	 */
	private DecimalFormat df;
	/**
	 * 下载进度
	 */
	private int downProgress;
	/**
	 * 
	 * 描述:构造函数
	 * @param context 上下文
	 * @param theme 主题
	 */
	public DownProgressDialog(Context context,int theme) {
		super(context,theme);
		this.context = context;
		this.theme = theme;
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customdownprogress_dialog);
		initView();
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
	 * @createtime 2015-3-16 下午9:51:11
	 * @Decription 初始化组件和handler对象
	 *
	 */
	protected void initView(){
		df = new DecimalFormat("0.00");
		mProgress = (ProgressBar) findViewById(R.id.downprogress);
		handler = new Handler(){
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					((TextView)findViewById(R.id.progressSize))
					.setText("共" + progressSize + "M");
					break;
				case 1:
					((TextView) findViewById(R.id.progressRate))
					.setText(String.valueOf(downProgress) + "%");
					break;
				case 2:
					mProgress.setProgress(downProgress);
					break;
				case 3:
					dismiss();
					installApk();
					break;
				default:
					break;
				}
			};
		};
	}
	/**
	 * 
	 * @author zhoufeng
	 * @createtime 2015-3-16 下午9:51:44
	 * @Decription 显示下载对话框
	 *
	 */
	public void showDownloadDialog() {
		show();
		((Button) findViewById(R.id.cancleDown))
				.setOnClickListener(new Button.OnClickListener() {
					@Override
					public void onClick(View v) {
						dismiss();
						isStopDownload = true;
					}
				});
		((TextView) findViewById(R.id.progressVersionDescrition))
				.setText(versionDescrition);
		downloadApk();
	}
	/**
	 * 
	 * @author zhoufeng
	 * @createtime 2015-3-16 下午9:52:05
	 * @Decription 下载apk
	 *
	 */
	private void downloadApk() {
		if (HttpUtils.isNetworkConnected(context) == true) {
			downLoadThread = new Thread(mdownApkRunnable);
			downLoadThread.start();
		}
	}
	/**
	 * 下载线程
	 */
	private Runnable mdownApkRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				URL url = new URL(apkUrl);

				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.connect();
				double length = conn.getContentLength();
				progressSize = String
						.valueOf(df.format((length / 1024) / 1024));
				Log.i("mydebug=======", progressSize);
				InputStream is = conn.getInputStream();
				handler.sendEmptyMessage(0);
				File file = new File(savePath);
				if (!file.exists()) {
					file.mkdir();
				}
				String apkFile = saveFileName;
				File ApkFile = new File(apkFile);
				FileOutputStream fos = new FileOutputStream(ApkFile);

				double count = 0;
				byte buf[] = new byte[1024];

				do {
					int numread = is.read(buf);
					count += numread;
					downProgress = (int) (((float) count / length) * 100);
					// 更新进度
					handler.sendEmptyMessage(1);
					handler.sendEmptyMessage(2);
					if (numread <= 0) {
						// 下载完成通知安装
						handler.sendEmptyMessage(3);
						break;
					}
					fos.write(buf, 0, numread);
				} while (!isStopDownload);// 点击取消就停止下载.
				fos.close();
				is.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	};
	/**
	 * 
	 * @author zhoufeng
	 * @createtime 2015-3-16 下午9:52:42
	 * @Decription 安装apk
	 *
	 */
	private void installApk() {
		File apkfile = new File(saveFileName);
		if (!apkfile.exists()) {
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		context.startActivity(i);
	}
}

	