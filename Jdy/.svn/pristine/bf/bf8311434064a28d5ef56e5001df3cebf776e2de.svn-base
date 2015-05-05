/**
 * Copyright (c) 2015
 *
 * Licensed under the UCG License, Version 1.0 (the "License");
 */
package com.app.jdy.widget;

import com.app.jdy.R;
import com.app.jdy.utils.Constants;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendAuth;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * description :
 *
 * @version 1.0
 * @author Owater
 * @createtime : 2015-2-6 下午4:09:36
 * 
 * 修改历史:
 * 修改人                                          修改时间                                                     修改内容
 * --------------- ------------------- -----------------------------------
 * Owater        2015-2-6 下午4:09:36 
 *
 */
public class BindWechatDialog extends Dialog {
	
	private IWXAPI api;
	private Button wxloginBtn;
	private Context context;

	public BindWechatDialog(Context context) {
		super(context);
	}
	
	public BindWechatDialog(Context context,int theme) {
		super(context, theme);
		this.context = context;
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bindwechat);
		wxloginBtn = (Button)findViewById(R.id.wxlogin_btn);
		api = WXAPIFactory.createWXAPI(context, Constants.APP_ID, false);
		
		wxloginBtn.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Constants.WX_LOGIN_OR_SHARE = true;
//				((Activity)context).finish();
				wxLogin();
				dismiss();
				
			}
		});
	}
	
	private void wxLogin(){
    	// 将该app注册到微信
    	 api.registerApp(Constants.APP_ID);
		 // send oauth request
		final SendAuth.Req req = new SendAuth.Req();
		req.scope = "snsapi_userinfo";
		req.state = "wxLogin";
		api.sendReq(req);
	}

}

	