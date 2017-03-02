package batchsample.dao;

import javax.sql.DataSource;

public class JDBCDaoFactory implements DaoFactory {
	private DataSource dataSource;

	public JDBCDaoFactory(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public BatchDao getBatchDao() {
		return new JDBCBatchDao(dataSource);
	}

	@Override
	public TransactionDao getTransactionDao() {
		return new JDBCTransactionDao(dataSource);
	}

}
