import java.math.BigDecimal;

import org.apache.commons.dbcp.BasicDataSource;

import batchsample.dao.BatchDao;
import batchsample.dao.JDBCBatchDao;
import batchsample.dao.JDBCTransactionDao;
import batchsample.dao.TransactionDao;
import batchsample.entity.Batch;
import batchsample.entity.Transaction;
import batchsample.facade.PaymentFacade;

public class BatchTest {

	public static void main(String[] args) {
		BasicDataSource bds = new BasicDataSource();

		bds.setUsername("root");
		bds.setPassword("root");
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		bds.setUrl("jdbc:mysql://localhost:3306/batch_sampl");

		BatchDao batchDao = new JDBCBatchDao(bds);
		TransactionDao transactionDao = new JDBCTransactionDao(bds);

		PaymentFacade facade = new PaymentFacade(batchDao, transactionDao);

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
		facade.processPayment(batch);
		System.out.println("done");
	}

}
