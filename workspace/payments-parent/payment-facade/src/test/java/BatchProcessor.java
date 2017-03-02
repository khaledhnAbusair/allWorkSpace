import java.sql.Connection;
import java.util.concurrent.Callable;

import javax.sql.DataSource;

import com.progressoft.jip.transaction.JDBCTransaction;

import batchsample.entity.Batch;
import batchsample.facade.PaymentFacade;

public class BatchProcessor implements Callable<Batch> {

	private DataSource dataSource;
	private PaymentFacade paymentFacade;
	private Batch batch;

	public BatchProcessor(DataSource dataSource, PaymentFacade paymentFacade, Batch batch) {
		this.dataSource = dataSource;
		this.paymentFacade = paymentFacade;
		this.batch = batch;
	}

	@Override
	public Batch call() throws Exception {

		try (Connection connection = dataSource.getConnection()) {
			JDBCTransaction.beginTransaction(connection);
			try {

				paymentFacade.processPayment(batch);
				connection.commit();
			} catch (Exception e) {
				connection.rollback();
				throw e;
			}

		}
		return null;
	}

}
