package domain.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.Audit;

@Repository
public interface AuditRepository extends CrudRepository<Audit, Integer> {

	@SuppressWarnings("unchecked")
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	Audit save(Audit audit);
}
