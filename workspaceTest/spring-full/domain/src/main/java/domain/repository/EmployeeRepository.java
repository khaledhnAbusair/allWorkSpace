/**
 * 
 */
package domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.Department;
import domain.Employee;

/**
 * @author PSLPT 147
 *
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	Iterable<Employee> findByFirstName(String firstName);

	Iterable<Employee> findByFirstNameLikeAndLastNameLikeOrderByIdDesc(String firstName, String lastName);

	Iterable<Employee> findByIdGreaterThan(Integer id);

	Iterable<Employee> findByIdBetween(Integer minId, Integer maxId);

	Long countByIdBetween(Integer minId, Integer maxId);

	// select obj from Employee obj where obj.department = ?
	Iterable<Employee> findByDepartment(Department dep);

	// select obj from Employee obj where obj.department.id = ?
	Iterable<Employee> findByDepartmentDeptNo(String depId);

	Page<Employee> findAll(Pageable pageable);

}
