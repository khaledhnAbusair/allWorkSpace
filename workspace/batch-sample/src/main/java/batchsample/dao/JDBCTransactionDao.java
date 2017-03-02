/**
 * 
 */
package batchsample.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import batchsample.entity.Transaction;

/**
 * @author phi01tech
 *
 */
public class JDBCTransactionDao implements TransactionDao {

	private static final String INSERT_SQL = "insert into transaction (trns_id,trns_amnt,trns_curr,trns_acct_from,trns_acct_acct_to,btch_id) values (?,?,?,?,?,?)";

	private QueryRunner runner;

	/**
	 * 
	 */
	public JDBCTransactionDao(DataSource dataSource) {
		this.runner = new QueryRunner(dataSource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see batchsample.dao.TransactionDao#addTransaction(batchsample.entity.
	 * Transaction, java.lang.String)
	 */
	@Override
	public void addTransaction(Transaction transaction, String batchId) throws DaoException {
		try {
			runner.update(INSERT_SQL, transaction.getTransactionId(), transaction.getAmount(),
					transaction.getCurrency(), transaction.getAccountFrom(), transaction.getAccountTo(), batchId);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}
