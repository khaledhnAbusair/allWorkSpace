package batchsample.dao;

public interface DaoFactory {

	public BatchDao getBatchDao();

	public TransactionDao getTransactionDao();

}
