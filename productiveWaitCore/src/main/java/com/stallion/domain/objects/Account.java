package com.stallion.domain.objects;

import java.sql.Timestamp;

public class Account {
	private int accountId;
	private int userId;
	private String firstName;
	private String lastName;
	private String ccNumber;
	private String ccExpiry;
	private String cvv;
	private String ccType;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String state;
	private String country;
	private String zip;
	private Timestamp create_dt;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	public String getCcExpiry() {
		return ccExpiry;
	}
	public void setCcExpiry(String ccExpiry) {
		this.ccExpiry = ccExpiry;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getCcType() {
		return ccType;
	}
	public void setCcType(String ccType) {
		this.ccType = ccType;
	}
	public Timestamp getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(Timestamp create_dt) {
		this.create_dt = create_dt;
	}
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
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", userId=" + userId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", ccNumber=" + ccNumber + ", ccExpiry=" + ccExpiry + ", cvv=" + cvv + ", ccType=" + ccType
				+ ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", addressLine3=" + addressLine3
				+ ", city=" + city + ", state=" + state + ", country=" + country + ", zip=" + zip + ", create_dt="
				+ create_dt + "]";
	}

}
