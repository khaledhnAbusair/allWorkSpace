import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.dbcp2.BasicDataSource;

import com.progressoft.jip.transaction.TransactedDataSource;

import batchsample.dao.JDBCDaoFactory;
import batchsample.entity.Batch;
import batchsample.entity.Transaction;
import batchsample.facade.PaymentFacade;

public class CallacleTest {

	public static void main(String[] args) throws SQLException {
		BasicDataSource dataSource = constractDataSource();

		JDBCDaoFactory factory = new JDBCDaoFactory(new TransactedDataSource(dataSource));
		PaymentFacade facade = new PaymentFacade(factory);

		Batch batch = buildBatch1();

		ExecutorService service = Executors.newCachedThreadPool();
		BatchProcessor processor = new BatchProcessor(dataSource, facade, batch);

		Future<Batch> future = service.submit(processor);
		try {
			//Black until the callable (Thread) finish excecution return 
			Batch result = future.get();
			future.isDone();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

	private static Batch buildBatch1() {
		Transaction.Builder tb = new Transaction.Builder();
		tb.setAccountFrom("808980");
		tb.setAccountTo("98089");
		tb.setAmount(new BigDecimal(1200));
		tb.setCurrency("JOD");
		tb.setTransactionId("TRNS-001");

		Batch.Builder builder = new Batch.Builder();
		builder.setBatchId("BTCH-001");
		builder.setIssuerId("ARBK");
		builder.setIssuerName("Arab Bank");
		builder.addTransaction(tb.build());

		Batch batch = builder.build();
		return batch;
	}

	private static BasicDataSource constractDataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:mysql://172.20.201.100:3306/batch_sample");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return dataSource;
	}
}
