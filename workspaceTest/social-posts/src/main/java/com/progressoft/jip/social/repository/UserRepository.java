/**
 * 
 */
package com.progressoft.jip.social.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progressoft.jip.social.User;

/**
 * @author PSLPT 147
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	public User findOneByEmail(String email);
}
