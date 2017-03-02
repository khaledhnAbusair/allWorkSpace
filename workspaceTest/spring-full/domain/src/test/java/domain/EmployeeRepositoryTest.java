package domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.repository.EmployeeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/data-beans.xml" })
@ActiveProfiles(value = { "test" })
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void testSaveRepository() {
		Employee entity = new Employee();
		entity.setFirstName("samer");
		entity.setLastName("ames");
		entity.setPosition("frontEndDeveloper");
		employeeRepository.save(entity);
	}
}
