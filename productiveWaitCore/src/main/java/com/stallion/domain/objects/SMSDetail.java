package com.stallion.domain.objects;

import java.sql.Timestamp;

public class SMSDetail {
	private int accountId;
	private int userId;
	private String toNumber;
	private String fromNumber;
	private int smsText;
	private Timestamp createDate;

		
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getToNumber() {
		return toNumber;
	}
	public void setToNumber(String toNumber) {
		this.toNumber = toNumber;
	}
	public String getFromNumber() {
		return fromNumber;
	}
	public void setFromNumber(String fromNumber) {
		this.fromNumber = fromNumber;
	}
	public int getSmsText() {
		return smsText;
	}
	public void setSmsText(int smsText) {
		this.smsText = smsText;
	}

}
