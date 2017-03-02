import com.progressoft.jip.dao.Employee;
import com.progressoft.jip.dao.EmployeeDao;

/**
 * 
 */

/**
 * @author PSLPT 147
 *
 */
public class EmployeeDaoTest {
	private EmployeeDao employeeDao;

	public EmployeeDaoTest(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void test() {
		Employee emp1 = new Employee();
		Employee emp2 = new Employee();

		emp1.setEmpNo(1);
		emp1.setFirstName("Mohamamd");
		emp1.setLastName("Abdellatif");

		emp2.setEmpNo(2);
		emp2.setFirstName("sami");
		emp2.setLastName("Hassan");

		employeeDao.create(emp1);
		employeeDao.create(emp2);
	}
	
	
}
