/**
 * 
 */
package com.progressoft.jip.paymentsproject.ui.impl;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;

import com.progressoft.jip.paymentsproject.accounts.Account;
import com.progressoft.jip.paymentsproject.ui.Displayer;

/**
 * @author u620
 *
 */
public class MergeAccountOption extends AbstractOption {
	private final static String ACCOUNTS_FILEPATH = "...";
	//private TreeSet<Account> accounts = new TreeSet<>((f, s) -> f.getAccountNumber().compareTo(s.getAccountNumber()));
	private Displayer displayer;

	public MergeAccountOption(String description, Displayer displayer) {
		super(description);
		this.displayer = displayer;
	}

	@Override
	public void doOperation() {
		// accounts2.get(0);
		displayer.display("Merge account option");
	}

	private void loadAccounts() throws IOException, ClassNotFoundException {
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(ACCOUNTS_FILEPATH))) {
			ObjectInputStream ois = new ObjectInputStream(bis);

		}
	}
}