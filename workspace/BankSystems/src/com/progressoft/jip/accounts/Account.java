package com.progressoft.jip.accounts;

import java.util.Currency;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Account")
@XmlAccessorType(XmlAccessType.FIELD)
public class Account {
	private String number;
	private String iban;
	private String name;
	private Currency currecny;
	private String bankSwiftCode;
	private AccountStatus status;
	private double balance;

	public enum AccountStatus {
		ACTIVE, INACTIVE
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Currency getCurrecny() {
		return currecny;
	}

	public void setCurrecny(Currency currecny) {
		this.currecny = currecny;
	}

	public String getBankSwiftCode() {
		return bankSwiftCode;
	}

	public void setBankSwiftCode(String bankSwiftCode) {
		this.bankSwiftCode = bankSwiftCode;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [number=" + number + ", iban=" + iban + ", name=" + name + ", currecny=" + currecny
				+ ", bankSwiftCode=" + bankSwiftCode + ", status=" + status + ", balance=" + balance + "]";
	}

}
