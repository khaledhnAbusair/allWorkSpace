/**
 * 
 */
package com.progressoft.jip.paymentsproject.ui.impl;

import com.progressoft.jip.paymentsproject.ui.Displayer;

/**
 * @author u620
 *
 */
public class TransactionOption extends AbstractOption {

//	private Account from;
//	private Account to;
//	private double amount;
//	private Date date;
//	private PaymentPurpose paymentPurpose;
	private Displayer displayer;
	
	public TransactionOption(String description, Displayer displayer) {
		super(description);
		this.displayer = displayer;
		
	}

	@Override
	public void doOperation() {
	displayer.display("Transaction option");
		
	}
	
}
