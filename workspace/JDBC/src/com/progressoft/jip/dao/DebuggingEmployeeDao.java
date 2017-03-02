/**
 * 
 */
package com.progressoft.jip.dao;

import java.util.Date;

/**
 * @author PSLPT 147
 *
 */
public class DebuggingEmployeeDao implements EmployeeDao {

	private EmployeeDao employeeDao;

	public DebuggingEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void create(Employee employee) {
		System.out.println("call create: " + new Date());
		employeeDao.create(employee);
		System.out.println("end create: " + new Date());
	}

	public void update(Employee employee) {
		employeeDao.update(employee);
	}

	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}

	public void deleteById(Integer id) {
		employeeDao.deleteById(id);
	}

	public Iterable<Employee> listAll() {
		return employeeDao.listAll();
	}

}
