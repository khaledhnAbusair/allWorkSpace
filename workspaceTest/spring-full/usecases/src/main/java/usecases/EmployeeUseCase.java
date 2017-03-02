package usecases;

import java.io.OutputStream;

import javax.transaction.Transactional;

import domain.Employee;

/**
 * @author khaled
 *
 */

@Transactional
public interface EmployeeUseCase {

	public void addEmployee(Employee employee, String attachmentName, byte[] picture);

	public Iterable<Employee> listAllEmployees();

	public void copyImage(Integer employeeId, OutputStream outputStream);

}
