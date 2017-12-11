package com.stallion.domain.objects;

public class Plan {
	private String planName;
	private String planDescription;
	private String planType;
	private int accountId;
	private int smsIncluded;
	private int rollOverSms;
	private float perSMSCharge;
	private float recurringCharge;
	private int billingFrequency;
	
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanDescription() {
		return planDescription;
	}
	public void setPlanDescription(String planDescription) {
		this.planDescription = planDescription;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getSmsIncluded() {
		return smsIncluded;
	}
	public void setSmsIncluded(int smsIncluded) {
		this.smsIncluded = smsIncluded;
	}
	public int getRollOverSms() {
		return rollOverSms;
	}
	public void setRollOverSms(int rollOverSms) {
		this.rollOverSms = rollOverSms;
	}
	public float getPerSMSCharge() {
		return perSMSCharge;
	}
	public void setPerSMSCharge(float perSMSCharge) {
		this.perSMSCharge = perSMSCharge;
	}
	public float getRecurringCharge() {
		return recurringCharge;
	}
	public void setRecurringCharge(float recurringCharge) {
		this.recurringCharge = recurringCharge;
	}
	public int getBillingFrequency() {
		return billingFrequency;
	}
	public void setBillingFrequency(int billingFrequency) {
		this.billingFrequency = billingFrequency;
	}
}
