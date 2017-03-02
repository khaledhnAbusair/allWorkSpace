package com.progressoft.jip.accounts;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class Accounts {

	@XmlElement(name = "Account")
	List<Account> accounts = new ArrayList<>();

	public List<Account> getAccounts() {
		return accounts;
	}

	public List<Account> setAccounts() {
		return accounts;
	}
}
