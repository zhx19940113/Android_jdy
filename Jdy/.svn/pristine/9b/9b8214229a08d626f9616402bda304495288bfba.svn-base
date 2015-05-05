/**
 * Copyright (c) 2015
 *
 * Licensed under the UCG License, Version 1.0 (the "License");
 */
package com.app.jdy.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.jdy.R;

public class WithdrawCashDialog extends Dialog {
	@SuppressWarnings("unused")
	private Context context;
	private String money;
	private TextView moneyText;
	private Button button1;
	private Button button2;

	public WithdrawCashDialog(Context context) {
		super(context);
	}

	public WithdrawCashDialog(Context context, int theme,String money) {
		super(context, theme);
		this.context = context;
		this.money= money;
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cashadvance_dialog);

		 button1 = (Button) findViewById(R.id.button1);
		 button2 = (Button) findViewById(R.id.button2);
		 moneyText = (TextView) findViewById(R.id.textView3);
		 
		 moneyText.setText(money);
		 
		 button1.setOnClickListener(new Button.OnClickListener() {

				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setAction("org.jdy.action.WITHDCASH_BROADCAST");
					intent.putExtra("msg", moneyText.getText().toString());
					getContext().sendBroadcast(intent);
					}
				
			});
			
		button2.setOnClickListener(new Button.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {	
					Intent intent = new Intent();
					intent.setAction("org.jdy.action.WITHDCASH_BROADCAST");
					intent.putExtra("msg", "exit");
					getContext().sendBroadcast(intent);
				}	
			});
		
	}
}
