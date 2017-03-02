package com.progressoft.jip.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import javax.sql.DataSource;

import com.progressoft.jip.entity.Account;
import com.progressoft.jip.entity.Customer;
import com.progressoft.jip.framework.CustomerOpertaions;

public class JdbcCustomer implements CustomerOpertaions {
	private DataSource dataSource;
	private Customer customer;

	public JdbcCustomer(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void createAccount(Account account) {
		try (Connection connection = dataSource.getConnection()) {
			String sql = "insert into Account (,,,) values(?,?,?,?,?,?)";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setLong(1, account.getAccountNumber());
				statement.setString(2, account.getIbanNumber().toString());
				statement.setString(3, account.getAccountCurrency().toString());
				statement.setString(4, account.getAccountStatus().name());
				statement.setDouble(5, account.getBalance());
				statement.executeUpdate();

				connection.close();
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public void closeAccount(Account account) {
		try (Connection connection = dataSource.getConnection()) {
			String sql = "delete from Account where customerID = ?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, account.getAccountID());
				statement.executeUpdate();

				connection.close();
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public void update(Customer customer) {
		try (Connection connection = dataSource.getConnection()) {
			String sql = "update  Customer set customerName =?,address =? ,phoneNumber=? where customerID=?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, customer.getCustomerName());
				statement.setString(2, customer.getAddress());
				statement.setLong(3, customer.getPhoneNumber());
				statement.setInt(4, customer.getCustomerID());
				statement.executeUpdate();

				connection.close();
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public void deposit(double balance, Account account) {

		try (Connection connection = dataSource.getConnection()) {
			String sql = "update  Account set balance =? where customerID=?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				balance = balance + account.getBalance();
				statement.setDouble(1, account.getBalance());

				statement.setInt(2, customer.getCustomerID());
				statement.executeUpdate();

				connection.close();
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public void withdrawMoney(double balance, Account account) {

		try (Connection connection = dataSource.getConnection()) {
			String sql = "update  Account set balance =? where customerID=?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				balance = balance - account.getBalance();
				statement.setDouble(1, account.getBalance());

				statement.setInt(2, customer.getCustomerID());
				statement.executeUpdate();

				connection.close();
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public Iterator<Customer> query() {
		try (Connection connection = dataSource.getConnection()) {
			String sql = "select user_id u_id,first_name, last_name from employees";
			System.out.println(sql);
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				ResultSet result = statement.executeQuery();
				return (Iterator<Customer>) result;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException();
	}

}
