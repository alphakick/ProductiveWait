package com.stallion.domain.objects;

import java.sql.Timestamp;

public class PlanActivity {
	private int accountId;
	private int userId;
	private int netUnsentSMS;
	private Timestamp createDate;
	private int smsSent;
		
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
	public int getNetUnsentSMS() {
		return netUnsentSMS;
	}
	public void setNetUnsentSMS(int netUnsentSMS) {
		this.netUnsentSMS = netUnsentSMS;
	}

	public int getSmsSent() {
		return smsSent;
	}
	public void setSmsSent(int smsSent) {
		this.smsSent = smsSent;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
}
