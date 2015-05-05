package com.app.jdy.ui;

import com.app.jdy.R;
import com.app.jdy.utils.BitmapUtils;
import com.umeng.analytics.MobclickAgent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 
 * description :引导界面二
 * 
 * @version 1.0
 * @author zhonghuixiong
 * @createtime : 2015-1-11 下午3:13:43
 * 
 *             修改历史: 修改人 修改时间 修改内容 --------------- -------------------
 *             ----------------------------------- zhonghuixiong 2015-1-11
 *             下午3:13:43
 * 
 */
@SuppressLint("ValidFragment")
public class GuideFragmentOne extends Fragment {

	private Context ctx;
	
	// java.lang.InstantiationException:can't instantiate class com.app.jdy.ui.GuideFragmentOne; no empty constructor
	// 需要一个无参数的构造方法
	public GuideFragmentOne() {
		
	}

	public GuideFragmentOne(Context ctx) {
		super();
		this.ctx = ctx;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = null;
		view = View.inflate(ctx, R.layout.guide_fragment_one, null);
		LinearLayout my_content_view = (LinearLayout) view.findViewById(R.id.my_content_view);
		my_content_view.setBackgroundResource(R.drawable.guide01);
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("MainScreen"); 
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("MainScreen"); 
	}
}
