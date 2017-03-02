/**
 * 
 */
package com.progressoft.jip.bankapplication;

import java.util.Collection;

/**
 * @author u620
 *
 */
public class MergeAccountOption extends Option {

	private Collection<Account> accounts;

	public MergeAccountOption(String description) {
		super(description);
	}

	@Override
	public void doOperation() {
		UI.show("Merge account option");
	}

	public Collection<Account> getAccounts() {
		return accounts;
	}

}
