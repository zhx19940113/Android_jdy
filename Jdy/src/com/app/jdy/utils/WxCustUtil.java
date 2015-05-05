package com.app.jdy.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendAuth;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import junit.framework.Assert;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.util.Log;

public class WxCustUtil {
	
	
	private static final String TAG = "WxCustUtil";
	private static IWXAPI api;// 微信api
	
	public static void SendAuth(Context context){
		api = WXAPIFactory.createWXAPI(context, Constants.APP_ID, false);
		api.registerApp(Constants.APP_ID);
		// 发送微信授权请求
		final SendAuth.Req req = new SendAuth.Req();
		req.scope = "snsapi_userinfo";
		req.state = "wxLogin";
		api.sendReq(req);
	}
}
