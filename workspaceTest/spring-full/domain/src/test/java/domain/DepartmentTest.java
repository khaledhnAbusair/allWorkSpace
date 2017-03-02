package domain;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import domain.repository.DepartmetRepository;
import domain.repository.EmployeeRepository;

public class DepartmentTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/data-beans.xml");
		DepartmetRepository repository = context.getBean(DepartmetRepository.class);
		EmployeeRepository empRepository = context.getBean(EmployeeRepository.class);

		// Employee employee = empRepository.findOne(1);
		// Department department = repository.findOne("d001");
		//
		// employee.setDepartment(department);
		// empRepository.save(employee);

		// Iterable<Employee> employees =
		// empRepository.findByDepartmentDeptNo("d001");
		// for (Employee employee : employees) {
		// System.out.println(
		// employee.getId() + " " + employee.getFirstName() + " " +
		// employee.getDepartment().getDeptNo() + "");
		// }
		PageRequest request = new PageRequest(0, 2);
		Page<Employee> page = empRepository.findAll(request);

		System.out.println("total pages: " + page.getTotalPages());
		System.out.println("total records count: " + page.getNumberOfElements());
		List<Employee> content = page.getContent();

		for (Employee employee : content) {
			System.out.println(employee.getId() + " " + employee.getFirstName());
		}
		
		System.out.println("second page");
		page = empRepository.findAll(request.next());
		content = page.getContent();
		for (Employee employee : content) {
			System.out.println(employee.getId() + " " + employee.getFirstName());
		}

		System.out.println("done");

	}

}
