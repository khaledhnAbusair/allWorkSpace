/**
 * 
 */
package domain.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.Audit;

/**
 * @author PSLPT 147
 *
 */
@Repository
public interface AuditRepository extends CrudRepository<Audit, Integer> {

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	Audit save(Audit entity);
}
