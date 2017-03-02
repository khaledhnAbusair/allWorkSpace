package com.progressoft.jip.accounts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Predicate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class AccountsReaderImpl implements AccountsReader {
	private static final String ACCOUNTS_FILE = "AccountsDef.xml";
	private static final String COMMA_REGEX = ",";

	@Override
	public boolean accountExists(String number) {
		try (BufferedReader br = new BufferedReader(new FileReader(new File(ACCOUNTS_FILE)))) {
			return br.lines().filter(containsAccountNumber(number)).findAny().isPresent();
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Account getAccountFromNumber(String number) {
		Account accountObj = null;
		if (accountExists(number))
			accountObj = readAccountFromXml(number, accountObj);
		return accountObj;
	}

	private Account readAccountFromXml(String number, Account accountObj) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Account.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Accounts accounts = (Accounts) jaxbUnmarshaller.unmarshal(new File(ACCOUNTS_FILE));
			accountObj = accounts.getAccounts().stream().filter(o -> o.getNumber().equals(number)).findAny().get();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return accountObj;
	}

	private Predicate<String> containsAccountNumber(String accountNumber) {
		return l -> l.split(COMMA_REGEX)[0].equals(accountNumber);
	}
}
