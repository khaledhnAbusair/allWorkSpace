package com.progressoft.jip.entity;

import java.util.Iterator;

import com.progressoft.jip.framework.CustomerOpertaions;

@SuppressWarnings("unused")
public class Customer {

	private int customerID;
	private String customerName;
	private String address;
	private long phoneNumber;
	private Bank bankID;

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Bank getBankID() {
		return bankID;
	}

	public void setBankID(Bank bankID) {
		this.bankID = bankID;
	}

}
