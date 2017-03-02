/**
 * 
 */
package batchsample.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import batchsample.entity.Batch;

/**
 * @author phi01tech
 *
 */
public class JDBCBatchDao implements BatchDao {

	private static final String INSERT_SQL = "insert into batch (btch_id,btch_issuer_id,btch_issuer_name,btch_total_cnt) values (?,?,?,?)";
	private QueryRunner runner;

	/**
	 * 
	 */
	public JDBCBatchDao(DataSource dataSource) {
		this.runner = new QueryRunner(dataSource);
	}

	@Override
	public void add(Batch batch) throws DaoException {
		try {
			this.runner.update(INSERT_SQL, batch.getBatchId(), batch.getIssuerId(), batch.getIssuerName(),
					batch.getTotalCount());
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}
