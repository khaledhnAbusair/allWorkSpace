/**
 * 
 */
package com.progressoft.jip.dao;

/**
 * @author PSLPT 147
 *
 */
public interface EmployeeDao {

	public void create(Employee employee);
	
	public void update(Employee employee);
	
	public void delete(Employee employee);
	
	public void deleteById(Integer id);
	
	public Iterable<Employee> listAll();
}
