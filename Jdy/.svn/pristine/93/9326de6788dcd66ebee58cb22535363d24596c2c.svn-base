/**
 * Copyright (c) 2015
 *
 * Licensed under the UCG License, Version 1.0 (the "License");
 */
package com.app.jdy.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * description :
 * 
 * @version 1.0
 * @author Owater
 * @createtime : 2015-1-13 下午5:25:00
 * 
 *             修改历史: 修改人 修改时间 修改内容 --------------- -------------------
 *             ----------------------------------- Owater 2015-1-13 下午5:25:00
 * 
 */
public class HttpUtils {

	private final String TAG = "uploadFile";
	public final static String SUCCESS = "1";
	public final static String FAILURE = "0";
	public static String JSESSIONID = null;
	public static String ERROR_RESULT = "0x110";

	private static final int REQUEST_TIMEOUT = 5 * 1000;// 设置请求超时5秒钟
	private static final int SO_TIMEOUT = 5 * 1000; // 设置等待数据超时时间5秒钟

	/**
	 * 判断网络是否连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 
	 * @param url
	 * @return string
	 */
	public static String request(List<NameValuePair> params, String url) {
		String result = "";
		try {
			HttpPost httpRequest = new HttpPost(url);
			if (Constants.JDY_CODE != null) {
				if (params == null)
					params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("jdycode", Constants.JDY_CODE));
			}
			if (params != null) {
				HttpEntity httpEntity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
				httpRequest.setEntity(httpEntity);
			}

			DefaultHttpClient httpClient = getHttpClient();

			httpRequest.setHeader("Cookie", "JSESSIONID=" + JSESSIONID);
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(httpResponse.getEntity());
				Log.i("commit", "提交成功");
			} else {
				result = ERROR_RESULT;
				Log.i("commit", "提交失败");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @author Owater
	 * @createtime 2015-1-14 上午12:23:06
	 * @Decription 登录和再次登录
	 * 
	 * @param params
	 * @param url
	 * @return
	 */
	public static String login(Context context, List<NameValuePair> params, String url, String jdycode) {
		String result = "";
		if (!isNetworkConnected(context)) {
			return Constants.NOT_NET + "";
		}
		try {
			HttpPost httpRequest = new HttpPost(url);
			if (params != null) {
				HttpEntity httpEntity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
				httpRequest.setEntity(httpEntity);
			}

			DefaultHttpClient httpClient = getHttpClient();
			httpClient.getParams().setParameter("http.socket.timeout", new Integer(30000));
			if (JSESSIONID != null && jdycode != null) {
				httpRequest.setHeader("Cookie", "JSESSIONID=" + JSESSIONID);
			}
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(httpResponse.getEntity());
				CookieStore mCookieStore = httpClient.getCookieStore();
				List<Cookie> cookies = mCookieStore.getCookies();
				// 读取服务器返回的cookie
				for (int i = 0; i < cookies.size(); i++) {
					String cookieName = cookies.get(i).getName();
					if ("JSESSIONID".equals(cookieName)) {
						JSESSIONID = cookies.get(i).getValue();
						break;
					}
				}
				Log.i("commit", "提交成功");
			} else {
				result = Constants.GETFALL + "";
				Log.i("commit", "提交失败");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @author liangdong
	 * @createtime 2015-2-6 上午01:39:06
	 * @Decription 初始化HttpClient，并设置超时
	 * 
	 * @param params
	 * @param url
	 * @return
	 */
	public static DefaultHttpClient getHttpClient() {
		BasicHttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
		DefaultHttpClient client = new DefaultHttpClient(httpParams);
		return client;
	}

	public static Boolean isReqSuccess(String datajson) {
		if (datajson != null && datajson.length() > 0 && !datajson.equals(HttpUtils.ERROR_RESULT)) {
			return true;
		} else {
			return false;
		}
	}

}
