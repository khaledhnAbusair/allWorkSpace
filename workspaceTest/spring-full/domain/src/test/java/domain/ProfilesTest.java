package domain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.repository.EmployeeRepository;

public class ProfilesTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/data-beans.xml");
		EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
		// منحدد البروفايل بال
		// run configuration
		// -Dspring.profiles.active=test
		Employee entity = new Employee();
		entity.setFirstName("khaled");
		entity.setLastName("waleed");
		entity.setPosition(".Net");

		employeeRepository.save(entity);
		System.out.println("Done");
	}
}
