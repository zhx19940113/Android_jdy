package com.app.jdy.utils;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import com.nineoldandroids.animation.FloatEvaluator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.umeng.analytics.MobclickAgent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.jdy.R;
import com.app.jdy.ui.LoginActivity;
import com.app.jdy.widget.CustomForwardDialog;
import com.app.jdy.widget.CustomSaveMoneyDialog;

public class UIUtils {

	/**
	 * 数字增加到指定数额的动画
	 * 
	 * @param target
	 * @param start
	 * @param end
	 * @param duration
	 *            持续时间
	 * @param format
	 *            数字格式化的格式
	 * @param unit
	 *            数字后的单位
	 */
	public static void autoIncrement(final TextView target, final float start, final float end, long duration,
			final String unit) {
		autoIncrement(target, start, end, duration, "####0.00", unit);
	}

	/**
	 * 数字增加到指定数额的动画
	 * 
	 * @param target
	 * @param start
	 * @param end
	 * @param duration
	 *            持续时间
	 * @param format
	 *            数字格式化的格式
	 * @param unit
	 *            数字后的单位
	 */
	public static void autoIncrement(final TextView target, final float start, final float end, long duration,
			final String formatStr, final String unit) {

		ValueAnimator animator = ValueAnimator.ofFloat(start, end);

		animator.addUpdateListener(new AnimatorUpdateListener() {
			private FloatEvaluator evalutor = new FloatEvaluator();
			private DecimalFormat format = new DecimalFormat(formatStr);

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {

				float fraction = animation.getAnimatedFraction();
				float currentValue = evalutor.evaluate(fraction, start, end);
				target.setText(format.format(currentValue) + unit);
			}
		});
		animator.setDuration(duration);
		animator.start();
	}

	/**
	 * 强制回收 ImageView的Drawable
	 * 
	 * @param imageView
	 */
	public static void recycleImageViewDrawable(ImageView imageView) {
		if (imageView == null)
			return;
		Drawable drawable = imageView.getDrawable();
		if (drawable != null && drawable instanceof BitmapDrawable) {
			BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
			Bitmap bitmap = bitmapDrawable.getBitmap();
			if (bitmap != null && !bitmap.isRecycled()) {
				bitmap.recycle();
			}
		}
	}

	/**
	 * 弹出分享Dialog(赚钱)
	 * 
	 * @param context
	 * @param map中参数
	 *            name 产品名称(微信中标题); shareSubject 分享描述(微信中描述); ID 产品ID;
	 *            prodTypeCode 产品类型; memberId 会员ID;
	 * 
	 */
	public static void shareProductDialog(Context context, Map<String, String> map) {
		if (CommonUtils.checkLogin(context)) {
			CustomForwardDialog customForwardDialog = new CustomForwardDialog(context, R.style.ForwardDialog, map,
					false);
			customForwardDialog.show();
		} else {
			context.startActivity(new Intent(context, LoginActivity.class));
		}

	}

	/**
	 * 弹出优惠码Dialog(省钱)
	 * 
	 * @param context
	 * @param map中参数
	 *            prodTypeCode 产品类型; memberId 会员ID;
	 */
	public static void showCouponDialog(Context context, Map<String, String> map) {
		if (CommonUtils.checkLogin(context)) {
			CustomSaveMoneyDialog customSaveMoneyDialog = new CustomSaveMoneyDialog(context, R.style.ForwardDialog, map);
			customSaveMoneyDialog.show();
		} else {
			context.startActivity(new Intent(context, LoginActivity.class));
		}
	}

	/**
	 * 改变RadioButton中图标大小
	 * 
	 * @param radioButton
	 *            radioButton对象
	 * @param direction
	 *            方向(0-左left,1-上top,2-右right,3-下bottom)
	 * @param width
	 *            图标宽度
	 * @param height
	 *            图标高度
	 */
	public static void changeRadioButtonImageSize(TextView radioButton, int direction, int width, int height) {
		changeRadioButtonImageSize(radioButton, direction, 0, 0, width, height);
	}

	public static void changeRadioButtonImageSize(TextView radioButton, int direction, int left, int top, int width,
			int height) {
		Drawable[] drawables = radioButton.getCompoundDrawables();
		drawables[direction].setBounds(left, top, dip2px(radioButton.getContext().getApplicationContext(), width),
				dip2px(radioButton.getContext().getApplicationContext(), height));
		radioButton.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
