package com.progressoft.jip.paymentsproject.payments;

import java.time.LocalDateTime;

import com.progressoft.jip.paymentsproject.accounts.Account;

public abstract class Transaction {
	protected double paymentAmount;
	protected Account issuingAccount;
	protected Account beneficiaryAccount;
	protected String beneficiaryName;
	protected Currency currency;
	protected LocalDateTime date;
	protected PaymentPurpose paymentPurpose;
	
	protected abstract void setIssuingAccount(Account issuingAccount);
	protected abstract void setBeneficiaryAccount(Account beneficiaryAccount);
}
