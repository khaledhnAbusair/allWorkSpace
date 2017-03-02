/**
 * 
 */
package batchsample.dao;

import batchsample.entity.Batch;

/**
 * @author phi01tech
 *
 */
public interface BatchDao {

	void add(Batch batch)throws DaoException;
}
