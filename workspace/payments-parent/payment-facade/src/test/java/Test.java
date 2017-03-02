import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

import com.progressoft.jip.transaction.JDBCTransaction;
import com.progressoft.jip.transaction.TransactedDataSource;

import batchsample.dao.JDBCDaoFactory;
import batchsample.entity.Batch;
import batchsample.entity.Transaction;
import batchsample.facade.PaymentFacade;

public class Test {

	public static void main(String[] args) throws SQLException {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:mysql://172.20.201.100:3306/batch_sample");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");

		JDBCDaoFactory factory = new JDBCDaoFactory(new TransactedDataSource(dataSource));
		PaymentFacade facade = new PaymentFacade(factory);

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
		 try (Connection connection = dataSource.getConnection()){
			 
		JDBCTransaction.beginTransaction(connection);

		facade.processPayment(batch);

		connection.commit();
		connection.rollback();

		}
		System.out.println("done");
	}
}
