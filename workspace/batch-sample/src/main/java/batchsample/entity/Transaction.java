package batchsample.entity;

import java.math.BigDecimal;

public class Transaction {

	private String transactionId;
	private String accountFrom;
	private String accountTo;
	private BigDecimal amount;
	private String currency;

	public String getTransactionId() {
		return transactionId;
	}

	public String getAccountFrom() {
		return accountFrom;
	}

	public String getAccountTo() {
		return accountTo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getCurrency() {
		return currency;
	}

	public static class Builder {
		private String transactionId;
		private String accountFrom;
		private String accountTo;
		private BigDecimal amount;
		private String currency;

		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}

		public void setAccountFrom(String accountFrom) {
			this.accountFrom = accountFrom;
		}

		public void setAccountTo(String accountTo) {
			this.accountTo = accountTo;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}

		public Transaction build() {
			Transaction transaction = new Transaction();

			transaction.accountFrom = accountFrom;
			transaction.accountTo = accountTo;
			transaction.amount = amount;
			transaction.transactionId = transactionId;
			transaction.currency = currency;

			return transaction;
		}
	}
}
