/**
 * 
 */
package com.progressoft.jip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * @author PSLPT 147
 *
 */
public class JDBCEmployeeDao implements EmployeeDao {

	private DataSource dataSource;

	public JDBCEmployeeDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void create(Employee employee) {
		try (Connection connection = dataSource.getConnection()) {
			String sql = "insert into employees (emp_no,first_name,last_name,gender,birth_Date,hire_date) values(?,?,?,?,?,?)";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, employee.getEmpNo());
				statement.setString(2, employee.getFirstName());
				statement.setString(3, employee.getLastName());
				statement.setString(4, employee.getGender().name());
				statement.setDate(5, new java.sql.Date(employee.getBirthDate().toEpochDay()));
				statement.setDate(6, new java.sql.Date(employee.getHireDate().toEpochDay()));

				statement.executeUpdate();
				
				connection.close();
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void update(Employee employee) {

	}

	@Override
	public void delete(Employee employee) {

	}

	@Override
	public void deleteById(Integer id) {

	}

	@Override
	public Iterable<Employee> listAll() {
		return null;
	}

}
