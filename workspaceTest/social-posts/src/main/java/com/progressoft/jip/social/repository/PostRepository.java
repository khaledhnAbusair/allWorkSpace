/**
 * 
 */
package com.progressoft.jip.social.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progressoft.jip.social.Post;

/**
 * @author PSLPT 147
 *
 */
@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

}
