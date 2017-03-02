/**
 * 
 */
package batchsample.facade;

import batchsample.dao.BatchDao;
import batchsample.dao.TransactionDao;
import batchsample.entity.Batch;
import batchsample.entity.Transaction;

/**
 * @author phi01tech
 *
 */
public class PaymentFacade {

	private BatchDao batchDao;
	private TransactionDao transactionDao;

	public PaymentFacade(BatchDao batchDao, TransactionDao transactionDao) {
		this.batchDao = batchDao;
		this.transactionDao = transactionDao;
	}

	public void processPayment(Batch batch) {
		Iterable<Transaction> transactions;

		batchDao.add(batch);

		transactions = batch.getTransactions();
		transactions.forEach(t -> transactionDao.addTransaction(t, batch.getBatchId()));
	}
}
