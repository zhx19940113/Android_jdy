package com.app.jdy.widget;

import com.app.jdy.R;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

public class WaitingDialog extends Dialog {
	private static Context mContext;
	private static WaitingDialog waitingDialog = null;

	public WaitingDialog(Context context) {
		super(context);
		this.mContext = context;
	}

	public WaitingDialog(Context context, int theme) {
		super(context, theme);
		this.mContext = context;
	}

	public static WaitingDialog createDialog(Context context) {
		return createDialog(context, 0);
	}

	public static WaitingDialog createDialog(Context context, int theme) {
		if (context != mContext) {
			if (theme != 0) {
				waitingDialog = new WaitingDialog(context, theme);
			} else {
				waitingDialog = new WaitingDialog(context, R.style.ForwardDialog);
			}
			waitingDialog.setContentView(R.layout.dialog_waiting);
			waitingDialog.setCancelable(false);
		}
		return waitingDialog;
	}

	public static WaitingDialog showDialog(Context context) {
		return showDialog(context, 0);
	}

	public static WaitingDialog showDialog(Context context, int theme) {
		waitingDialog = createDialog(context, theme);
		waitingDialog.show();
		return waitingDialog;
	}

	/**
	 * 设置提示语
	 * 
	 * @param text
	 * @return
	 */
	public static WaitingDialog setText(String text) {
		TextView txt_loading = (TextView) waitingDialog.findViewById(R.id.txt_loading);
		txt_loading.setText(text);
		return waitingDialog;
	}

	public static void dismissDialog() {
		if (isShow()) {
			waitingDialog.dismiss();
		}
	}

	/**
	 * 判断waitingDialog是否正在显示
	 * 
	 * @return
	 */
	private static Boolean isShow() {
		if (waitingDialog != null && waitingDialog.isShowing())
			return true;
		return false;
	}
}
