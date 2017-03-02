package com.progressoft.jip.dao;

public class CapitalizeNameEmployeeDao implements EmployeeDao {

	private EmployeeDao dao;

	public CapitalizeNameEmployeeDao(EmployeeDao dao) {
		this.dao = dao;
	}

	public void create(Employee employee) {
		String firstName = employee.getFirstName();
		employee.setFirstName(firstName.toUpperCase());

		dao.create(employee);
	}

	public void update(Employee employee) {
		dao.update(employee);
	}

	public void delete(Employee employee) {
		dao.delete(employee);
	}

	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

	public Iterable<Employee> listAll() {
		return dao.listAll();
	}

}
