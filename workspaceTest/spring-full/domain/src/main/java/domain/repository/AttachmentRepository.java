package domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.Attachment;

@Repository
public interface AttachmentRepository extends CrudRepository<Attachment, Integer> {

}
