package com.progressoft.jip.bankapplication;

import java.util.Date;

/**
 * @author u620
 *
 */
public class TransactionOption extends Option {

	private Account from, To;
	private double amount;
	private Date date;
	private PaymentPurpose paymentPurpose;

	public TransactionOption(String description) {
		super(description);
	}

	@Override
	public void doOperation() {
		UI.show("Transaction option");

	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Account getFrom() {
		return from;
	}

	public Account getTo() {
		return To;
	}

	public Date getDate() {
		return date;
	}

	public PaymentPurpose getPaymentPurpose() {
		return paymentPurpose;
	}

}
