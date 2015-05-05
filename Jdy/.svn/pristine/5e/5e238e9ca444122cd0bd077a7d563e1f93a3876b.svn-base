/**
 * Copyright (c) 2015
 *
 * Licensed under the UCG License, Version 1.0 (the "License");
 */
package com.app.jdy.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.jdy.R;
import com.app.jdy.activity.MainActivity;
import com.app.jdy.context.MyApplication;
import com.app.jdy.utils.Constants;
import com.app.jdy.utils.HttpUtils;
import com.app.jdy.utils.StringUtils;
import com.app.jdy.utils.URLs;
import com.app.jdy.utils.WxCustUtil;
import com.app.jdy.widget.WaitingDialog;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.ConstantsAPI;
import com.tencent.mm.sdk.openapi.SendAuth;
import com.umeng.analytics.MobclickAgent;

/**
 * description :
 * 
 * @version 1.0
 * @author zhoufeng
 * @createtime : 2015-1-12 上午10:39:44
 * 
 *             修改历史: 修改人 修改时间 修改内容 --------------- -------------------
 *             ----------------------------------- zhoufeng 2015-1-12 上午10:39:44
 * 
 */
public class LoginActivity extends Activity implements OnClickListener {
	/**
	 * 返回按钮
	 */
	private ImageView mBackImg;
	/**
	 * 标题
	 */
	private TextView title;
	/**
	 * 手机号码的editText组件
	 */
	private EditText username;
	/**
	 * 手机号码的textview组件
	 */
	// private TextView username_message;
	/**
	 * 密码的editText组件
	 */
	private EditText password;
	/**
	 * 密码的textview组件
	 */
	// private TextView password_message;
	/**
	 * 找回密码组件
	 */
	private Button sumbitLogin;
	// private ImageView logoImage;
	private TextView find_password;
	private TextView regirster;
	private TextView detail_product;
	private final int PROGRESS_DIALOG = 0x112;
	private String dataJson;// json数据
	private List<NameValuePair> params=new ArrayList<NameValuePair>();
	private String jdycode;
	private String ID;// 用户ID号
	private MyApplication myApplication;

	private View loginOther, loginWeixin;
	private MyApplication data;
	private String wxUnionId = null;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.activity_login);
		data = (MyApplication) getApplication();
		initView();
		intiAnimation();
		intiClick();

		mBackImg.setOnClickListener(this);
		find_password.setOnClickListener(this);
	}

	/**
	 * 
	 * @author Owater
	 * @createtime 2015-1-13 下午6:04:53
	 * @Decription 初始化界面
	 * 
	 */
	public void initView() {
		mBackImg = (ImageView) findViewById(R.id.back_img);
		mBackImg.setVisibility(View.VISIBLE);
		title = (TextView) findViewById(R.id.title_tv);
		title.setText("登录");
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);

		// username_message = (TextView) findViewById(R.id.username_message);
		// password_message = (TextView) findViewById(R.id.password_message);
		find_password = (TextView) findViewById(R.id.find_password);

		sumbitLogin = (Button) findViewById(R.id.sumbitLogin);
		loginOther = findViewById(R.id.login_other);
		loginWeixin = findViewById(R.id.login_weixin);
		// logoImage = (ImageView) findViewById(R.id.image_login_logo);
		find_password = (TextView) findViewById(R.id.find_password);
		regirster = (TextView) findViewById(R.id.regirster);
	}

	/**
	 * 
	 * @author Owater
	 * @createtime 2015-1-13 下午6:05:11
	 * @Decription 初始化动画集合
	 * 
	 */
	public void intiAnimation() {
		// 实现移动动画
		// Animation translateAnimation = AnimationUtils.loadAnimation(this,
		// R.anim.translate_username);
		Animation translateAnimation1 = new TranslateAnimation(0, 0, 3000, 0);
		translateAnimation1.setDuration(1200);// 设置动画持续时间
		// username_message.startAnimation(translateAnimation1);

		Animation translateAnimation2 = new TranslateAnimation(0, 0, 3000, 0);
		translateAnimation2.setDuration(1400);// 设置动画持续时间
		// password_message.startAnimation(translateAnimation2);

		Animation translateAnimation3 = new TranslateAnimation(0, 0, 3000, 0);
		translateAnimation3.setDuration(1600);// 设置动画持续时间
		sumbitLogin.startAnimation(translateAnimation3);
		loginOther.startAnimation(translateAnimation3);
		loginWeixin.startAnimation(translateAnimation3);

		Animation translateAnimation4 = new TranslateAnimation(0, 0, 3000, 0);
		translateAnimation4.setDuration(1800);// 设置动画持续时间
		find_password.startAnimation(translateAnimation4);
		regirster.startAnimation(translateAnimation4);

		// 实现渐变动画
		// Animation animation =AnimationUtils.loadAnimation(this,
		// R.anim.animation_image_logo);
		Animation animation = new AlphaAnimation(0F, 1.0F);
		animation.setDuration(5000);
		// logoImage.startAnimation(animation);
		animation.startNow();

		/*
		 * Animation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, 4.0f, 1.0f,
		 * Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f);
		 * scaleAnimation.setDuration(2000);
		 * logoImage.startAnimation(scaleAnimation); scaleAnimation.startNow();
		 */
	}

	/**
	 * 
	 * @author Owater
	 * @createtime 2015-1-13 下午6:07:23
	 * @Decription 初始化点击事件
	 * 
	 */
	public void intiClick() {
		loginWeixin.setOnClickListener(this);
		regirster.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivityForResult(new Intent(LoginActivity.this, RegirsterActivity.class), 2);
			}
		});

		sumbitLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// 验证用户名和密码是否未输入
				String un = username.getText().toString().trim();
				String pwd = password.getText().toString().trim();
				if (un == null || un.length() == 0) {
					Toast.makeText(LoginActivity.this, "请输入手机号码！", Toast.LENGTH_SHORT).show();
					return;
				}
				if (pwd == null || pwd.length() == 0) {
					Toast.makeText(LoginActivity.this, "请输入您的密码！", Toast.LENGTH_SHORT).show();
					return;
				}
				if (un.length() != 11) {
					Toast.makeText(LoginActivity.this, "手机号码不正确！", Toast.LENGTH_SHORT).show();
					return;
				}

				WaitingDialog.showDialog(LoginActivity.this);
				LoginAysnTask task = new LoginAysnTask();
				task.execute();
			}
		});
	}

	/**
	 * 通过微信code,获取access_token再获取unionId的任务
	 * 
	 */
	private class LoginAysnTask extends AsyncTask<Void, Integer, String> {

		@Override
		protected String doInBackground(Void... p) {
			params.add(new BasicNameValuePair("userName", username.getText().toString()));
			params.add(new BasicNameValuePair("password", password.getText().toString()));
			params.add(new BasicNameValuePair("wxUnionId", wxUnionId));
			String json = null;
			json = HttpUtils.login(getApplicationContext(), params, URLs.LOGIN_URL, null);
			return json;
		}

		@Override
		protected void onPostExecute(String json) {
			if (StringUtils.isNotEmpty(json)) {
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(json);
					boolean success = jsonObject.optBoolean("success");
					if (success) {
						JSONObject attributesObject = jsonObject.optJSONObject("attributes");
						jdycode = attributesObject.optString("jdycode");
						ID = attributesObject.getString("ID");
						saveUserInfo(jdycode, ID);
						rollLogin();

						Toast.makeText(mContext, "登录成功", Toast.LENGTH_LONG).show();
						Intent intent = new Intent();
						intent.setAction("org.jdy.action.finshActivityReceiver");
						sendBroadcast(intent);
						finish();
					} else {
						Toast.makeText(getApplicationContext(), "您的微信还未绑定注册账号,为您跳到注册界面", Toast.LENGTH_LONG).show();
					}
					WaitingDialog.dismissDialog();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_LONG).show();
			}
		}
	}

	/**
	 * 
	 * @author Owater
	 * @createtime 2015-1-14 上午1:08:51
	 * @Decription 轮询登录，维持Android和后台的SSIONID
	 * 
	 */
	public void rollLogin() {
		final ArrayList<NameValuePair> tmpParams = new ArrayList<NameValuePair>();
		tmpParams.add(new BasicNameValuePair("jdycode", jdycode));
		new Thread(new Runnable() {

			@Override
			public void run() {
				int i = 0;
				while (true) {
					i++;
					try {
						HttpUtils.login(getApplicationContext(), tmpParams, URLs.LOGIN_URL, jdycode);
						Log.i("Thread", "轮询次数  == " + i);
						Thread.sleep(Constants.JSESSIONID_TIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	/**
	 * 
	 * @author Owater
	 * @createtime 2015-1-14 上午12:56:54
	 * @Decription 保存用户信息
	 * 
	 */
	public void saveUserInfo(String jdycode, String ID) {
		SharedPreferences userPreferences;
		userPreferences = getSharedPreferences("umeng_general_config", Context.MODE_PRIVATE);
		Editor editor = userPreferences.edit();
		editor.putString("jdycode", jdycode);
		editor.putString("ID", ID);
		editor.commit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_img:
			finish();
			break;
		case R.id.find_password:
			startActivity(new Intent(LoginActivity.this, CallBackPasswordActivity.class));
			break;
		case R.id.login_weixin:
			WxCustUtil.SendAuth(this);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		SendAuth.Resp resp = (SendAuth.Resp) data.getSendResp();
		if (resp != null && data.getWxFlag()) {
			switch (resp.errCode) {
			case BaseResp.ErrCode.ERR_OK:
				switch (resp.getType()) {
				// 授权登陆
				case ConstantsAPI.COMMAND_SENDAUTH:
					String token = resp.token;
					ReqWXAuthAysnTask task = new ReqWXAuthAysnTask();
					task.execute(token);
					break;
				// 分享
				case ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX:
					break;
				}
				break;
			case BaseResp.ErrCode.ERR_USER_CANCEL:
				Toast.makeText(this, R.string.errcode_cancel, Toast.LENGTH_LONG).show();
				break;
			case BaseResp.ErrCode.ERR_AUTH_DENIED:
				Toast.makeText(this, R.string.errcode_deny, Toast.LENGTH_LONG).show();
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 通过微信code,获取access_token再获取unionId的任务
	 * 
	 */
	private class ReqWXAuthAysnTask extends AsyncTask<String, Integer, String> {
		@Override
		protected String doInBackground(String... params) {
			String code = params[0];
			String ac_url = URLs.WX_ACCESS_TOKEN.replace("CODE", code);
			String jsonString = HttpUtils.request(null, ac_url);
			String access_token = null;
			String openId = null;
			try {
				JSONObject jsonObject = new JSONObject(jsonString);
				access_token = jsonObject.optString("access_token");
				openId = jsonObject.optString("openid");

				String getuser_url = URLs.WX_GETUSERINFO.replace("ACCESS_TOKEN", access_token)
						.replace("OPENID", openId);
				String jsonString2 = HttpUtils.request(null, getuser_url);
				JSONObject jsonObject2 = new JSONObject(jsonString2);
				wxUnionId = jsonObject2.optString("unionid");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return wxUnionId;
		}

		@Override
		protected void onPostExecute(String result) {
			if (StringUtils.isNotEmpty(result)) {
				// 通过微信授权,拿到unionid
				WaitingDialog.showDialog(LoginActivity.this);
				LoginAysnTask task = new LoginAysnTask();
				task.execute();
			} else {
				// 没授权
				Toast.makeText(getApplicationContext(), "微信授权失败", Toast.LENGTH_LONG).show();
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 2) {
			if (resultCode == 2) {
				finish();
			}
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
