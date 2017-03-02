/**
 * 
 */
package usecases;

import java.io.OutputStream;

import javax.transaction.Transactional;

import domain.Employee;

/**
 * @author PSLPT 147
 *
 */
public interface EmployeeUseCase {

	@Transactional
	public void addEmployee(Employee employee, String attachmentName, byte[] picture);

	public Iterable<Employee> listAllEmployees();

	public void copyImage(Integer employeeId, OutputStream os);

}
