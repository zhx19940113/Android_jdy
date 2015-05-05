package com.app.jdy.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.app.jdy.R;
import com.app.jdy.utils.BitmapUtils;
import com.app.jdy.utils.UIUtils;

public class BaseActivity extends Activity {
	private int guideResourceId = 0;// 引导页图片资源id
	private int baseLayoutId = R.id.my_content_view;// 基于最上层的layout
	private static final String SHAREDPREFERENCES_NAME = "my_pref";// 偏好文件名
	private static final String KEY_GUIDE_ACTIVITY = "guide_activity";// 引导界面KEY

	@Override
	protected void onStart() {
		super.onStart();
		addGuideImage();
	}

	private void addGuideImage() {

		// 找到setContentView的根布局
		View view = getWindow().getDecorView().findViewById(baseLayoutId);// 查找通过setContentView上的根布局
		if (view == null)
			return;
		if (activityIsGuided(this, this.getClass().getName()))
			return;
		ViewParent viewParent = view.getParent();
		if (viewParent instanceof FrameLayout) {
			final FrameLayout frameLayout = (FrameLayout) viewParent;
			if (guideResourceId != 0) {// 设置了引导图片
				final ImageView guideImage = new ImageView(this);
				FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.MATCH_PARENT);
				guideImage.setLayoutParams(params);
				guideImage.setScaleType(ScaleType.FIT_XY);

				Bitmap guideBitmap = BitmapUtils.readBitMap(this, guideResourceId);
				// guideImage.setImageResource(guideResourceId);
				guideImage.setImageBitmap(guideBitmap);
				guideImage.setAlpha(230);
				// guideImage.setBackgroundResource(guideResourceId);
				// guideImage.getBackground().setAlpha(100);
				// guideImage.setBackgroundColor(Color.TRANSPARENT);
				guideImage.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						frameLayout.removeView(guideImage);
						UIUtils.recycleImageViewDrawable(guideImage);
						setIsGuided(getApplicationContext(), BaseActivity.this.getClass().getName());
					}
				});
				frameLayout.addView(guideImage);
			}
		}
	}

	/**
	 * 判断activity是否引导过
	 * 
	 * @param context
	 * @return 是否已经引导过 true引导过了 false未引导
	 */
	public boolean activityIsGuided(Context context, String className) {
		if (context == null || className == null || "".equalsIgnoreCase(className))
			return false;
		String[] classNames = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE)
				.getString(KEY_GUIDE_ACTIVITY, "").split("\\|");
		for (String str : classNames) {
			if (str.equals(className)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 设置该activity被引导过了。 将类名已 |a|b|c这种形式保存为value，因为偏好中只能保存键值对
	 * 
	 * @param context
	 * @param className
	 */
	public void setIsGuided(Context context, String className) {
		if (context == null || className == null || "".equalsIgnoreCase(className))
			return;
		String classNames = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE).getString(
				KEY_GUIDE_ACTIVITY, "");
		// 添加
		StringBuffer sb = new StringBuffer(classNames).append("|").append(className);
		// 写入
		context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE).edit()
				.putString(KEY_GUIDE_ACTIVITY, sb.toString()).commit();
	}

	/**
	 * 子类在onCreate中调用，设置引导图片的资源id 并在布局xml的根元素上设置android:id="@id/my_content_view"
	 * 
	 * @param resId
	 */
	public void setGuideResourceId(int guideResourceId) {
		this.guideResourceId = guideResourceId;
	}

	public void setBaseLayoutId(int baseLayoutId) {
		this.baseLayoutId = baseLayoutId;
	}

}
