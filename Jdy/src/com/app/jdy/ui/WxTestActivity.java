package com.app.jdy.ui;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.app.jdy.R;
import com.app.jdy.utils.Constants;
import com.app.jdy.utils.HttpUtils;
import com.app.jdy.utils.URLs;
import com.app.jdy.utils.WxUtil;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXWebpageObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class WxTestActivity extends Activity {
	
	private IWXAPI api;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wx_test);
		
		api = WXAPIFactory.createWXAPI(this,Constants.APP_ID);

		findViewById(R.id.wx_share).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String memberId = "402880e64af1943c014af19eb4f60002";
				String type = "bank";
				String productId = "4028b8814b01ded8014b01e04c770001";
				new MyAsyncTask(productId, memberId, type).execute();
			}
		});
	}
	
	private void sendUrl(String id){
		WXWebpageObject webpage = new WXWebpageObject();
		webpage.webpageUrl = URLs.WX_SHARE_RESULT_URL+"/402880e74b14acdf014b14cd3aa9000d-bank-"+id;
		Log.i("url", URLs.WX_SHARE_RESULT_URL+"/402880e74b14acdf014b14cd3aa9000d-bank-"+id);
		Toast.makeText(WxTestActivity.this, URLs.WX_SHARE_RESULT_URL+"/402880ea4b0b8a12014b0b9039c80007-bank-"+id, Toast.LENGTH_LONG).show();
		WXMediaMessage msg = new WXMediaMessage(webpage);
		msg.title = "产品标题";
		msg.description = "产品描述";
		Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.drawable.wx_test1);
		msg.thumbData = WxUtil.bmpToByteArray(thumb, true);
		
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = buildTransaction("webpage");
		req.message = msg;
//		req.scene = isTimelineCb.isChecked() ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
		req.scene = SendMessageToWX.Req.WXSceneTimeline;
		api.sendReq(req);
	}
	
	private String buildTransaction(final String type) {
		return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
	}
	
	/**
	 * 
	 * description : 异步网络请求
	 *
	 * @version 1.0
	 * @author Owater
	 * @createtime : 2015-1-20 上午4:22:27
	 * 
	 * 修改历史:
	 * 修改人                                          修改时间                                                  修改内容
	 * --------------- ------------------- -----------------------------------
	 * Owater        2015-1-20 上午4:22:27
	 *
	 */
	private class MyAsyncTask extends AsyncTask<Void, Integer, Boolean>{
		
		private ArrayList<NameValuePair> params;
		private String resultid;
		private String memberId;//会员ID
		private String productId;//产品ID
		private String type;//产品类型
		
		public MyAsyncTask(String productId,String memberId,String type){
			this.memberId = memberId;
			this.type = type;
			this.productId = productId;
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {
			params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("parentId", ""));
			params.add(new BasicNameValuePair("productId", productId));
			params.add(new BasicNameValuePair("memberId", memberId));
			resultid = HttpUtils.request(params, URLs.WX_SHARE_URL);
			try {
				JSONObject jsonObject = new JSONObject(resultid);
				resultid = jsonObject.getString("ID");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				Toast.makeText(WxTestActivity.this, "resultid="+resultid, Toast.LENGTH_LONG).show();
				sendUrl(resultid);
			}else {
//	            Toast.makeText(HomeActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();  
	        }
		}
		
	}
}
