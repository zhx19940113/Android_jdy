package com.app.jdy.ui;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.jdy.R;
import com.app.jdy.utils.Constants;
import com.app.jdy.utils.HttpUtils;
import com.app.jdy.utils.URLs;
import com.umeng.analytics.MobclickAgent;

/**
 * 
 * description :我的银行卡activity
 * 
 * @version 1.0
 * @author zhoufeng
 * @createtime : 2015-1-19 下午6:27:46
 * 
 *             修改历史: 修改人 修改时间 修改内容 --------------- -------------------
 *             ----------------------------------- zhoufeng 2015-1-19 下午6:27:46
 * 
 */
public class AddBankCardActivity extends Activity implements OnClickListener {
	/**
	 * 返回按钮
	 */
	private ImageView mBackImg;
	/**
	 * 标题
	 */
	private TextView title;
	/**
	 * 产品详情的json数据
	 */
	private String dataJson;
	/**
	 * handler
	 */
	private Handler handler;
	/**
	 * 进度条的标示
	 */
	private final int PROGRESS_DIALOG = 0x112;
	/**
	 * 进度条
	 */
	private ProgressDialog pd;
	/**
	 * 用户的ID号
	 */
	private String ID;
	/**
	 * 添加银行卡持卡人的编辑框
	 */
	private EditText add_bank_username;
	/**
	 * 添加银行卡号码的编辑框
	 */
	private EditText add_bank_member;
	/**
	 * 添加银行卡的银行机构的编辑框
	 */
	private EditText add_bank_name;
	/**
	 * 提交添加银行卡的确定按钮
	 */
	private Button sumbit_add_bank_card;
	/**
	 * 提交数据
	 */
	private ArrayList<NameValuePair> params;
	/**
	 * 解析服务器的json数据
	 */
	private JSONObject jsonObject;
	/**
	 * 未绑定银行卡的界面
	 */
	private LinearLayout bank_no_linear;
	/**
	 * 绑定银行卡成功界面
	 */
	private LinearLayout bank_ok_linear;

	private TextView bank_ok_message;
	/**
	 * 立即提现按钮
	 */
	private Button bank_ok_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_bank_card);

		initView();

		// SharedPreferences userPreferences = getSharedPreferences(
		// "umeng_general_config", Context.MODE_PRIVATE);
		// String string = userPreferences.getString("jdycode", "");
		// if (string.length() > 10) {
		// return true;
		// }
		// return false;

		mBackImg.setOnClickListener(this);
		sumbit_add_bank_card.setOnClickListener(this);
		bank_ok_button.setOnClickListener(this);
	}

	/**
	 * 初始化组件
	 */
	@SuppressLint("NewApi")
	public void initView() {
		mBackImg = (ImageView) findViewById(R.id.back_img);
		mBackImg.setVisibility(View.VISIBLE);
		title = (TextView) findViewById(R.id.title_tv);
		title.setText("绑定银行卡");
		pd = new ProgressDialog(this);
		add_bank_username = (EditText) findViewById(R.id.add_bank_username);
		add_bank_name = (EditText) findViewById(R.id.add_bank_name);
		add_bank_member = (EditText) findViewById(R.id.add_bank_member);
		sumbit_add_bank_card = (Button) findViewById(R.id.sumbit_add_bank_card);
		bank_no_linear = (LinearLayout) findViewById(R.id.bank_no_linear);
		bank_ok_linear = (LinearLayout) findViewById(R.id.bank_ok_linear);
		bank_ok_message = (TextView) findViewById(R.id.bank_ok_message);
		bank_ok_button = (Button) findViewById(R.id.bank_ok_button);

		SharedPreferences userPreferences = getSharedPreferences(
				"umeng_general_config", Context.MODE_PRIVATE);
		ID = userPreferences.getString("ID", "").trim();

		handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
//					pd.dismiss();
					Toast.makeText(AddBankCardActivity.this, Constants.NO_INTENT_TIPS,
							Toast.LENGTH_LONG).show();
					break;
				case 1:
//					pd.dismiss();
					try {
						Toast.makeText(AddBankCardActivity.this,
								jsonObject.getString("msg"), Toast.LENGTH_SHORT)
								.show();
					} catch (JSONException e) {
						e.printStackTrace();
					}
					bank_no_linear.setVisibility(View.GONE);
					int len = add_bank_member.getText().toString().length();
					bank_ok_message.setText("已经成功绑定尾数为"
							+ add_bank_member.getText().toString()
									.substring(len - 4, len) + "的银行卡");
					bank_ok_linear.setVisibility(View.VISIBLE);
					setResult(1, new Intent().setAction(""));
					break;
				case 2:
//					pd.dismiss();
					try {
						Toast.makeText(AddBankCardActivity.this,
								jsonObject.getString("msg"), Toast.LENGTH_SHORT)
								.show();
					} catch (JSONException e) {
						e.printStackTrace();
					}
					break;

				default:
					break;
				}
			}
		};
	}

	/**
	 * 
	 * 重写方法: onCreateDialog|描述:进度条的框
	 * 
	 * @param id
	 * @param status
	 * @return
	 * @see android.app.Activity#onCreateDialog(int, android.os.Bundle)
	 */
	protected Dialog onCreateDialog(int id, Bundle status) {
		if (id == PROGRESS_DIALOG) {
			pd.setMessage("正在提交...");
		}
		return pd;
	}

	/**
	 * 
	 * @author zhoufeng
	 * @createtime 2015-1-20 上午10:21:28
	 * @Decription 添加银行卡
	 * 
	 */
	public void addBankCard() {
//		pd.show();
		String cardName = add_bank_username.getText().toString().trim();
		String cardMember = add_bank_member.getText().toString().trim();
		String bankName = add_bank_name.getText().toString().trim();
		if (cardName.equals("") || cardMember.equals("") || bankName.equals("")) {
			pd.dismiss();
			Toast.makeText(AddBankCardActivity.this, "银行卡填写信息有误，请重新检查！",
					Toast.LENGTH_LONG).show();
		} else if (cardMember.length() < 16) {
			pd.dismiss();
			Toast.makeText(AddBankCardActivity.this, "填写有误，银行卡号小于16位！",
					Toast.LENGTH_LONG).show();
		} else {
			params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("memberId", ID));
			params.add(new BasicNameValuePair("accountName", cardName));
			params.add(new BasicNameValuePair("bankCode", cardMember));
			params.add(new BasicNameValuePair("bankName", bankName));

			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					Message msg = new Message();
					dataJson = HttpUtils.request(params, URLs.ADD_BANK_CARD);
					if (dataJson.length() != 0 && !dataJson.equals("0x110")) {
						try {
							jsonObject = new JSONObject(dataJson);
							if (jsonObject.getString("success").equals("true")) {
								msg.what = 1;
							} else if (jsonObject.getString("success").equals(
									"false")) {
								msg.what = 2;
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					} else {
						msg.what = 0;
					}
					handler.sendMessage(msg);
				}
			});
			thread.start();
		}
		// pd.dismiss();
	}

	/**
	 * 
	 * 重写方法: onClick|描述: 点击事件
	 * 
	 * @param v
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_img:
			// 返回按钮
			this.finish();
			break;
		case R.id.sumbit_add_bank_card:
			addBankCard();
			break;
		case R.id.bank_ok_button:
			startActivity(new Intent(AddBankCardActivity.this,
					CashAdvanceActivity.class));
			setResult(2, new Intent().setAction(""));
			finish();
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
