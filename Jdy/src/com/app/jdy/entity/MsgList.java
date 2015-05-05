/**
 * Copyright (c) 2015
 *
 * Licensed under the UCG License, Version 1.0 (the "License");
 */
package com.app.jdy.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * description :
 *
 * @version 1.0
 * @author Owater
 * @createtime : 2015-1-27 下午5:05:21
 * 
 * 修改历史:
 * 修改人                                          修改时间                                                     修改内容
 * --------------- ------------------- -----------------------------------
 * Owater        2015-1-27 下午5:05:21 
 *
 */
public class MsgList implements Serializable {
	
	 /** 
	  * serialVersionUID
	  */
	private static final long serialVersionUID = 1L;
	private String title;//消息标题
	private String content;
	private String memberId;
	private String ID;
	private String memberName;
	private String createUserName;
	private int createUserId;
	private int updateUserId;
	private String updateUserName;
	private String createTime;
	private Date updateTime;
	private int isRead;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public int getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}
	public int getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	
}

	