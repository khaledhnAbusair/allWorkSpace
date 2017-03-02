package domain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.repository.EmployeeRepository;

public class SpringDataTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/data-beans.xml");
		EmployeeRepository repository = context.getBean(EmployeeRepository.class);

		Iterable<Employee> employees = repository.findByFirstName("Mohammad");
		for (Employee employee : employees) {
			System.out.println(employee.getFirstName() + " " + employee.getId());
		}
		System.out.println("done");

		employees = repository.findByFirstNameLikeAndLastNameLikeOrderByIdDesc("%m%", "%a%");
		for (Employee employee : employees) {
			System.out.println(employee.getId() + " " + employee.getFirstName() + " " + employee.getLastName());
		}

		employees = repository.findByIdGreaterThan(2);

		System.out.println("by id");
		for (Employee employee : employees) {
			System.out.println(employee.getId() + " " + employee.getFirstName());
		}

		System.out.println("===>>find by id between: " + repository.countByIdBetween(2, 3));
		employees = repository.findByIdBetween(2, 3);
		for (Employee employee : employees) {
			System.out.println(employee.getId() + " " + employee.getFirstName());
		}

	}

}
