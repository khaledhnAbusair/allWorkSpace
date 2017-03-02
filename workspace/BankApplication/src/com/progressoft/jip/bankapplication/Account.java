/**
 * 
 */
package com.progressoft.jip.bankapplication;

import java.util.Currency;

/**
 * @author u620
 *
 */
public class Account {
	private long accountNumber;
	private String accountType;
	private String accountOwnerName;
	private String IBAN;
	private String swiftCode;
	private Currency currency;

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountType;
	}

	public void setAccountName(String accountName) {
		this.accountType = accountName;
	}

	public String getAccountOwnerName() {
		return accountOwnerName;
	}

	public void setAccountOwnerName(String accountOwnerName) {
		this.accountOwnerName = accountOwnerName;
	}

	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}
