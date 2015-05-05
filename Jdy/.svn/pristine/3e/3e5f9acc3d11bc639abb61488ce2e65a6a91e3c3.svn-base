package com.app.jdy.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.jdy.R;
import com.app.jdy.adapter.BankAdapter;
import com.app.jdy.entity.BankCard;
import com.app.jdy.utils.Constants;
import com.app.jdy.utils.HttpUtils;
import com.app.jdy.utils.URLs;
import com.app.jdy.widget.ListViewCompat;
import com.app.jdy.widget.MyDelDlg;
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
public class BankCardActivity extends Activity implements OnClickListener {
	/**
	 * 返回按钮
	 */
	private ImageView mBackImg;
	/**
	 * 添加银行卡按钮
	 */
	private ImageView add_bank_card;
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
	 * listview控件
	 */
	private ListViewCompat listView;
	/**
	 * 用户ID号
	 */
	private String ID;
	/**
	 * 提交数据
	 */
	private ArrayList<NameValuePair> params;
	/**
	 * 银行卡对应的list对象
	 */
	private List<BankCard> list;
	/**
	 * 银行卡适配器
	 */
	private BankAdapter bankAdapter;
	
	boolean canClick = true; // 防止快速多次点击打开多个银行卡界面

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bank_card);

		initView();
		getAllBankCard();
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (!HttpUtils.isNetworkConnected(BankCardActivity.this)) {
					Toast.makeText(BankCardActivity.this, Constants.NO_INTENT_TIPS,
							Toast.LENGTH_SHORT).show();
					return;
				}
				// 防止快速多次点击打开多个银行卡界面
				if (!canClick) {
					return;
				}
				canClick = false;
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						canClick = true;
					}
				}, 1000);
				
				//Toast.makeText(BankCardActivity.this, list.get(position).getID(), 2).show();
				
				params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("ID", list.get(position).getID()));
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						Message msg = new Message();
						dataJson = HttpUtils.request(params, URLs.CHANGE_HAND_CARD);
						if (dataJson.length() != 0 && !dataJson.equals("0x110")) {
							msg.what = 2;
						} else {
							msg.what = 0;
						}

						handler.sendMessage(msg);
					}
				});
				thread.start();
			}
		});
		
		// ----增加长按弹出删除对话框功能，作者：王磊，日期：2015-3-27----
		addDelDlg();
		// ----增加长按弹出删除对话框功能，作者：王磊，日期：2015-3-27----
		
		mBackImg.setOnClickListener(this);
		add_bank_card.setOnClickListener(this);
		
	}
	
	/**
	 * 长按listView则弹出删除对话框
	 * 作者：王磊
	 * 日期：2015-3-27 
	 */
	private void addDelDlg() {
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
				View vv = listView.getChildAt(position).findViewById(R.id.bank_card);
				MyDelDlg myDialog = new MyDelDlg(BankCardActivity.this, new MyDelDlg.DeleteInterface() {
					@Override
					public void onDelete() {
						removeItem(position);
					}
				}, vv);
				WindowManager windowManager = (WindowManager) BankCardActivity.this
						.getSystemService(Context.WINDOW_SERVICE);
				DisplayMetrics outMetrics = new DisplayMetrics();
				windowManager.getDefaultDisplay().getMetrics(outMetrics);
				// 获取屏幕高度，用以定位删除对话框的位置
				int screenHeight = outMetrics.heightPixels;
				WindowManager.LayoutParams params = myDialog.getWindow().getAttributes();
				// 定位删除对话框的位置
				params.y = screenHeight - view.getBottom() - 10;
				params.gravity = Gravity.BOTTOM;
				myDialog.getWindow().setAttributes(params);
				myDialog.setCanceledOnTouchOutside(true);
				myDialog.show();
				//vv.setPressed(true);
				vv.setBackgroundColor(0x33C0C0C0);
				return true;
			}
		});
	}
	
	/**
	 * 功能：删除银行卡
	 * 作者：王磊
	 * 日期：2015-3-27
	 * @param position 银行卡列表中的位置
	 */
	private void removeItem(int position) {
		List<BankCard> bankCardItem = bankAdapter.getBankCardItem();
		final BankCard item = bankCardItem.get(position);
		bankAdapter.removeBank(item.getID());
		bankCardItem.remove(position);
		bankAdapter.notifyDataSetChanged();
	}

	/**
	 * 
	 * 初始化组件
	 */
	@SuppressLint("NewApi")
	public void initView() {
		mBackImg = (ImageView) findViewById(R.id.back_img);
		mBackImg.setVisibility(View.VISIBLE);
		title = (TextView) findViewById(R.id.title_tv);
		title.setText("我的银行卡");
		add_bank_card = (ImageView) findViewById(R.id.right_img);
		add_bank_card.setBackgroundResource(R.drawable.add_bank_card);
		add_bank_card.setVisibility(View.VISIBLE);
		listView = (ListViewCompat) findViewById(R.id.all_bank_card);
		/**
		 * 从SharedPreferences里面获取用户ID号
		 */
		SharedPreferences userPreferences = getSharedPreferences(
				"umeng_general_config", Context.MODE_PRIVATE);
		ID = userPreferences.getString("ID", "").trim();

		handler = new Handler() {
			public void handleMessage(Message msg) {
				Log.v("json数据", "=======" + dataJson);
				switch (msg.what) {
				case 0:
					Toast.makeText(BankCardActivity.this, Constants.NO_INTENT_TIPS,
							Toast.LENGTH_LONG).show();
				case 1:
					list = new ArrayList<BankCard>();
					try {
						JSONObject jsonObject = new JSONObject(dataJson);
						JSONArray jsonArray = jsonObject.getJSONArray("data");
						for (int i = 0; i < jsonArray.length(); i++) {

							BankCard bankCard = new BankCard();
							bankCard.setID(jsonArray.getJSONObject(i).getString("ID"));
							 bankCard.setAccountName(jsonArray.getJSONObject(i)
							 .getString("accountName"));;
							bankCard.setBankCode(jsonArray.getJSONObject(i)
									.getString("bankCode"));
							 bankCard.setBankName(jsonArray.getJSONObject(i)
							 .getString("bankName"));
							list.add(bankCard);
						}
						bankAdapter = new BankAdapter(BankCardActivity.this);
						bankAdapter.setmMessageItems(list);
						listView.setAdapter(bankAdapter);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					break;
				case 2:
//					Toast.makeText(BankCardActivity.this, ""+getIntent().getExtras().getString("aa"), 2).show();
					if (getIntent().getExtras().getBoolean("isOk")) {
						startActivity(new Intent(BankCardActivity.this,CashAdvanceActivity.class));
//						setResult(1,new Intent().setAction(""));
						finish();
						
					}else{
						startActivity(new Intent(BankCardActivity.this,CashAdvanceActivity.class));
					} 
					 
					break;
				default:
					break;
				}
			}
		};
	} 

	/**
	 * 获取详情列表的数据
	 */
	public void getAllBankCard() {

		params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("memberId", ID));

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				Message msg = new Message();
				dataJson = HttpUtils.request(params, URLs.ALL_BANK_CARD);
				if (dataJson.length() != 0 && !dataJson.equals("0x110")) {
					msg.what = 1;
				} else {
					msg.what = 0;
				}

				handler.sendMessage(msg);
			}
		});
		thread.start();
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
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 1){
			if (resultCode == 1) {
				getAllBankCard();
			}else if(resultCode == 2){
				finish();
			}
		}
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
		case R.id.right_img:
			Intent intent = new Intent(BankCardActivity.this, AddBankCardActivity.class);
			startActivityForResult(intent, 1);
//			startActivity(new Intent(BankCardActivity.this,
//					AddBankCardActivity.class));
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
