/**
 * 
 */
package domain.repository;

import org.springframework.stereotype.Repository;

import domain.Department;

/**
 * @author PSLPT 147
 *
 */
@Repository
public interface DepartmetRepository extends org.springframework.data.repository.Repository<Department, String> {

	Department save(Department department);

	Iterable<Department> findAll();

	Department findOne(String id);
}
