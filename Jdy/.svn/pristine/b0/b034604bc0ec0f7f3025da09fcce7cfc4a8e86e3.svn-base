package com.app.jdy.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.app.jdy.R;
import com.app.jdy.adapter.MsgListAdapter;
import com.app.jdy.entity.MsgList;
import com.app.jdy.utils.Constants;
import com.app.jdy.utils.HttpUtils;
import com.app.jdy.utils.URLs;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MsgListActivity extends Activity {
	
	/**
	 * 标题
	 */
	private TextView title;
	/**
	 * 用户的 ID号
	 */
	private String memberId;
	private ListView listView;
	private List<MsgList> lists;
	private ImageView imageView;
	private MsgListAdapter msgListAdapter;
	
	private int requestCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_msg_list);
		initView();
		init();
	}

	/**
	 * 
	 * @author Owater
	 * @createtime 2015-1-27 下午5:30:18
	 * @Decription 初始化界面
	 *
	 */
	private void initView() {
		listView = (ListView)findViewById(R.id.msglist_listview);
		imageView = (ImageView)findViewById(R.id.back_img);
		title = (TextView) findViewById(R.id.title_tv);
		
		imageView.setVisibility(View.VISIBLE);
		title.setText("消息");
		SharedPreferences userPreferences = getSharedPreferences("umeng_general_config", Context.MODE_PRIVATE);
		memberId = userPreferences.getString("ID", "").trim();
		lists = new ArrayList<MsgList>();
	}
	
	/**
	 * 
	 * @author Owater
	 * @createtime 2015-1-27 下午5:31:36
	 * @Decription 初始化
	 *
	 */
	private void init() {
		new MyAsyncTask().execute();
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
				Intent intent = new Intent();
				intent.putExtra("msg",new String[]{lists.get(position).getContent(),lists.get(position).getID()});
				setResult(3, new Intent().setAction(""));
				intent.setClass(MsgListActivity.this, MyMessageDetail.class);
				TextView title = (TextView) arg1.findViewById(R.id.msglist_title);
				title.setTextColor(Color.rgb(195, 210, 207));
				startActivity(intent);
				//解析：MsgList msg = (MsgList)getIntent().getParcelableExtra("msg");
			}
		});
		
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
	
	
	/**
	 * 
	 * @author Owater
	 * @createtime 2015-1-27 下午5:49:25
	 * @Decription 解析字符串
	 *
	 * @param jsonString
	 */
	private void parseJson(String jsonString){
		if (jsonString.length()>10) {
			try {
				JSONArray jsonArray = new JSONArray(jsonString);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = (JSONObject) jsonArray.get(i);
					MsgList msgList = new MsgList();
					msgList.setContent(jsonObject.getString("content"));
					msgList.setTitle(jsonObject.getString("title"));
					msgList.setID(jsonObject.getString("ID"));
					msgList.setIsRead(jsonObject.getInt("isRead"));
					msgList.setCreateTime(changeTime(jsonObject.getString("create_time")));
					lists.add(msgList);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String changeTime(String time){
		String arr[] = time.split(" ");
		return arr[0].replace("-", "/");
	}
	
	
	/**
	 * 
	 * description : 异步网络请求
	 *
	 * @version 1.0
	 * @author Owater
	 * @createtime : 2015-1-27 下午8:46:40
	 * 
	 * 修改历史:
	 * 修改人                                          修改时间                                                  修改内容
	 * --------------- ------------------- -----------------------------------
	 * Owater        2015-1-27 下午8:46:40
	 *
	 */
	private class MyAsyncTask extends AsyncTask<Void, Void, Boolean>{
		
		private String jsonString;

		@Override
		protected Boolean doInBackground(Void... arg0) {
			ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("memberId", memberId));
			jsonString = HttpUtils.request(params, URLs.MSGLIST_URL);
			parseJson(jsonString);
			return true;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				msgListAdapter = new MsgListAdapter(MsgListActivity.this, lists, R.layout.msglist_item);
				listView.setAdapter(msgListAdapter);
			}else {
	            Toast.makeText(MsgListActivity.this, Constants.NO_INTENT_TIPS, Toast.LENGTH_SHORT).show();  
	        }
			super.onPostExecute(result);
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
