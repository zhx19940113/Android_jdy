/**
 * Copyright (c) 2015
 *
 * Licensed under the UCG License, Version 1.0 (the "License");
 */
package com.app.jdy.entity;

import java.io.Serializable;

/**
 * description :
 *
 * @version 1.0
 * @author zhoufeng
 * @createtime : 2015-1-25 下午12:19:32
 * 
 * 修改历史:
 * 修改人                                          修改时间                                                     修改内容
 * --------------- ------------------- -----------------------------------
 * zhoufeng        2015-1-25 下午12:19:32 
 *
 */
public class FaceValue implements Serializable{
	private String time;
	private String name;
	private String money;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	
}

	