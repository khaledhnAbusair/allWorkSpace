/**
 * 
 */
package batchsample.dao;

import batchsample.entity.Transaction;

/**
 * @author phi01tech
 *
 */
public interface TransactionDao {

	void addTransaction(Transaction transaction, String batchId) throws DaoException;
}
