package com.progressoft.jip.entity;

import java.time.LocalDateTime;
import java.util.Currency;

public class TransactionRequest {

	private int transactionID; // primary key
	private Customer customerID;// foreign key
	private double balanceValue;
	private Currency currency;
	private String beneficiaryName;
	private String beneficiaryAddress;
	private long beneficiaryAccountNumber;
	private Account orderingAccountNumber;
	private String purposeCode;
	private String purposeName;
	private LocalDateTime pyamentDate;
	private long beneficiaryPhoneNumber;

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public Customer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Customer customerID) {
		this.customerID = customerID;
	}

	public double getBalanceValue() {
		return balanceValue;
	}

	public void setBalanceValue(double balanceValue) {
		this.balanceValue = balanceValue;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getBeneficiaryAddress() {
		return beneficiaryAddress;
	}

	public void setBeneficiaryAddress(String beneficiaryAddress) {
		this.beneficiaryAddress = beneficiaryAddress;
	}

	public long getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}

	public void setBeneficiaryAccountNumber(long beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}

	public Account getOrderingAccountNumber() {
		return orderingAccountNumber;
	}

	public void setOrderingAccountNumber(Account orderingAccountNumber) {
		this.orderingAccountNumber = orderingAccountNumber;
	}

	public String getPurposeCode() {
		return purposeCode;
	}

	public void setPurposeCode(String purposeCode) {
		this.purposeCode = purposeCode;
	}

	public String getPurposeName() {
		return purposeName;
	}

	public void setPurposeName(String purposeName) {
		this.purposeName = purposeName;
	}

	public LocalDateTime getPyamentDate() {
		return pyamentDate;
	}

	public void setPyamentDate(LocalDateTime pyamentDate) {
		this.pyamentDate = pyamentDate;
	}

	public long getBeneficiaryPhoneNumber() {
		return beneficiaryPhoneNumber;
	}

	public void setBeneficiaryPhoneNumber(long beneficiaryPhoneNumber) {
		this.beneficiaryPhoneNumber = beneficiaryPhoneNumber;
	}

}
