package com.app.jdy.ui;

import java.io.File;
import java.util.UUID;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.jdy.R;
import com.app.jdy.utils.BitmapUtils;
import com.app.jdy.utils.URLs;
import com.umeng.analytics.MobclickAgent;

/**
 * @author spring sky<br>
 *         Email :vipa1888@163.com<br>
 *         QQ: 840950105<br>
 * @version 创建时间：2012-11-22 上午9:20:03 说明：主要用于选择文件操作
 */

public class AboutUsActivity extends Activity implements OnClickListener {

	private WebView company_describe;
	private TextView title_tv;
	private ImageView back_img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_us_layout);
		initView();
	}

	/**
	 * 初始化加载View
	 */
	private void initView() {
		title_tv = (TextView) findViewById(R.id.title_tv);
		back_img = (ImageView) findViewById(R.id.back_img);
		back_img.setOnClickListener(this);
		title_tv.setText("关于");
		company_describe = (WebView) findViewById(R.id.company_describe);
		WebSettings settings = company_describe.getSettings(); 
		
		settings.setSupportZoom(true);
		settings.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
		company_describe.setBackgroundColor(0);
//		company_describe.getBackground().setAlpha(0);
		company_describe.loadUrl(URLs.COMPANY_DESCRIBE);
		company_describe.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				return true;
			}
		});
		// company_describe.setText(R.string.about_us_content);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_img:
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
