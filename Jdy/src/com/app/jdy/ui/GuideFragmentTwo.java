package com.app.jdy.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.jdy.R;
import com.app.jdy.activity.MainActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * 
 * description :第二个引导界面
 * 
 * @version 1.0
 * @author zhoufeng
 * @createtime : 2015-1-22 下午12:21:08
 * 
 *             修改历史: 修改人 修改时间 修改内容 --------------- -------------------
 *             ----------------------------------- zhoufeng 2015-1-22 下午12:21:08
 * 
 */
@SuppressLint("ValidFragment")
public class GuideFragmentTwo extends Fragment {

	private Context ctx;
	private Boolean isOK;
	
	// java.lang.InstantiationException:can't instantiate class com.app.jdy.ui.GuideFragmentTwo; no empty constructor
	// 需要一个无参数的构造方法
	public GuideFragmentTwo() {
		
	}
	public GuideFragmentTwo(Context ctx, Boolean isOK) {
		super();
		this.ctx = ctx;
		this.isOK = isOK;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = null;
		view = View.inflate(ctx, R.layout.guide_fragment_two, null);
		RelativeLayout my_content_view = (RelativeLayout) view.findViewById(R.id.my_content_view);
		my_content_view.setBackgroundResource(R.drawable.guide02);
		ImageView mBtn = (ImageView) view.findViewById(R.id.new_strat_img);
		ImageView show = (ImageView) view.findViewById(R.id.show_guide_img);
		if (isOK) {
			mBtn.setVisibility(View.GONE);
			show.setVisibility(View.VISIBLE);
		} else {
			mBtn.setVisibility(View.VISIBLE);
			show.setVisibility(View.GONE);
		}
		mBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ctx, MainActivity.class);
				ctx.startActivity(intent);
				getActivity().finish();
			}

		});
		show.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().finish();
			}

		});
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
