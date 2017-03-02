/**
 * 
 */
package domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.Attachment;

/**
 * @author PSLPT 147
 *
 */
@Repository
public interface AttachmentRepository extends CrudRepository<Attachment, Integer> {

}
