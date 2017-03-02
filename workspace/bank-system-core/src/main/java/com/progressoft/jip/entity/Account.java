/**
 * 
 */
package com.progressoft.jip.entity;

import java.util.Currency;

import org.iban4j.Iban;

/**
 * @author khaled
 *
 */
public class Account {

	private int accountID;
	private long accountNumber;
	private Iban ibanNumber;
	private Currency accountCurrency;
	private accountStatuses accountStatus;
	private double balance;
	private Customer customerID;

	public enum accountStatuses {
		ACTIVE, INACTIVE;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Iban getIbanNumber() {
		return ibanNumber;
	}

	public void setIbanNumber(Iban ibanNumber) {
		this.ibanNumber = ibanNumber;
	}

	public Currency getAccountCurrency() {
		return accountCurrency;
	}

	public void setAccountCurrency(Currency accountCurrency) {
		this.accountCurrency = accountCurrency;
	}

	public accountStatuses getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(accountStatuses accountStatus) {
		this.accountStatus = accountStatus;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Customer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Customer customerID) {
		this.customerID = customerID;
	}

}
