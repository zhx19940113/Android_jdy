package com.app.jdy.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.jdy.R;

/**
 * 自定义删除对话框，模仿QQ
 * 
 * @author 王磊
 * @date 2015-3-27
 *
 */
public class MyDelDlg extends Dialog {

	public interface DeleteInterface {
		void onDelete();
	}
	
	private DeleteInterface deleteInterface;
	
	private View view;

	public MyDelDlg(Context context, DeleteInterface dif, View v) {
		super(context, R.style.MyDialogStyle);
		deleteInterface = dif;
		view = v;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_dialog);
		
		TextView tv = (TextView) findViewById(R.id.textview_one);
		tv.setText("删除");
		tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				deleteInterface.onDelete();	// 点击按钮执行删除功能
				dismiss();
			}
		});
	}
	
	@Override
	public void dismiss() {
		super.dismiss();
		view.setBackgroundColor(0xffffff);
	}
	
}
