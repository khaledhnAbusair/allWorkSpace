package com.progressoft.jip.dao;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class TestDao {
	public static void main(String[] args) {
		String password = "root";
		String username = "root";
		String url = "jdbc:mysql://localhost:3306/employees";
		BasicDataSource basicDataSource = new BasicDataSource();
		// database connection settings
		basicDataSource.setUsername("root");
		basicDataSource.setPassword("root");
		basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		basicDataSource.setUrl("jdbc:mysql://localhost:3306/employees");
		// pool settings
		basicDataSource.setMaxTotal(10);
		basicDataSource.setMaxIdle(5);
		basicDataSource.setInitialSize(2);
		basicDataSource.setMaxWaitMillis(3000);
		
		
		
		DataSource ds = basicDataSource;
		// do real CRUD
		EmployeeDao dao = new JDBCEmployeeDao(ds);
		// capitalize name
		dao = new CapitalizeNameEmployeeDao(dao);
		dao = new DebuggingEmployeeDao(dao);
		EmployeeDaoTest daoTest = new EmployeeDaoTest(dao);
		daoTest.test();

		System.out.println("done");
	}
}
