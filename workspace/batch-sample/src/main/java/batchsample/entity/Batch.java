package batchsample.entity;

import java.util.ArrayList;

public class Batch {
	private String batchId;
	private int totalCount;
	private String issuerName;
	private String issuerId;
	private Iterable<Transaction> transactions;

	protected Batch() {
	}

	public String getBatchId() {
		return batchId;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public String getIssuerName() {
		return issuerName;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public Iterable<Transaction> getTransactions() {
		return transactions;
	}

	public static class Builder {
		private String batchId;
		private String issuerName;
		private String issuerId;
		private ArrayList<Transaction> transactions = new ArrayList<>();

		public void setBatchId(String batchId) {
			this.batchId = batchId;
		}

		public void setIssuerName(String issuerName) {
			this.issuerName = issuerName;
		}

		public void setIssuerId(String issuerId) {
			this.issuerId = issuerId;
		}

		public void addTransaction(Transaction transaction) {
			transactions.add(transaction);
		}

		public Batch build() {
			Batch batch = new Batch();

			batch.batchId = batchId;
			batch.issuerId = issuerId;
			batch.issuerName = issuerName;
			batch.totalCount = transactions.size();
			// TODO clone transactions
			batch.transactions = new ArrayList<>(transactions);

			return batch;
		}

	}
}
