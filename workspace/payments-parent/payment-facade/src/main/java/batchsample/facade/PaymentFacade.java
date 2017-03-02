/**
 * 
 */
package batchsample.facade;

import batchsample.dao.BatchDao;
import batchsample.dao.DaoFactory;
import batchsample.dao.TransactionDao;
import batchsample.entity.Batch;
import batchsample.entity.Transaction;

/**
 * @author phi01tech
 *
 */
public class PaymentFacade {
	// Facade same business layer
	private BatchDao batchDao;
	private TransactionDao transactionDao;

	public PaymentFacade(DaoFactory daoFactory) {
		this.batchDao = daoFactory.getBatchDao();
		this.transactionDao = daoFactory.getTransactionDao();
	}

	public void processPayment(Batch batch) {
		Iterable<Transaction> transactions;
		batchDao.add(batch);

		transactions = batch.getTransactions();
		transactions.forEach(t -> transactionDao.addTransaction(t, batch.getBatchId()));
	}
}
