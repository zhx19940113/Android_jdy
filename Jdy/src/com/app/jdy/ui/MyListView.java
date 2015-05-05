package com.app.jdy.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class MyListView extends ListView{
	public MyListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyListView(Context context, AttributeSet as) {
		super(context, as);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}