package suncertify.model;

import java.util.Set;

/**
 * Defines a model for persisting contractor entities in a persistence storage.
 * Contractor information are encapsulated in a bean of type {@link Contractor}.
 * <p>
 * 
 * This interface defines basic CRUD operations (Create, Read, Update, and
 * Delete) and a {@link #discard() } to clean up this model when disposing it
 * from use, clean up might be free resources and unlock records.
 * 
 * The read operation is performed by a finder method
 * {@link #find(suncertify.model.ContractorCriteria) } which returns a list of
 * {@link Contractor} instances which matches specific filtering criteria.
 * 
 * 
 * @author Mohammad S. Abdellatif
 * @see Contractor
 * @see ContractorCriteria
 */
public interface ContractorModel {

	/**
	 * Finds contractors resides in wrapped persistence storage which matches
	 * filtering criteria <code>criteria</code> and returns a list of matches
	 * found encapsulated in instances of {@link Contractor}.
	 * <p>
	 * 
	 * The matching for passed criteria is implementation dependent
	 * 
	 * @param criteria
	 *            filtering criteria.
	 * @return a set (not duplicated) of matches found in wrapped persistence
	 *         storage.
	 * @throws ModelException
	 *             if operation failed.
	 */
	public Set<Contractor> find(ContractorCriteria criteria)
			throws ModelException;

	/**
	 * Update contractor in persistence storage its current property values.
	 * 
	 * @param contractor
	 *            contractor entity.
	 * @throws ModelException
	 *             if updating was failed.
	 * @throws ContractorNotFoundException
	 *             if contractor is not found in database.
	 */
	public void update(Contractor contractor) throws ModelException,
			ContractorNotFoundException;

	/**
	 * Inserts a contractor to the persistence storage.
	 * 
	 * @param contractor
	 *            contractor to be inserted to persistence storage.
	 * @throws ModelException
	 *             if insertion is failed.
	 * @throws ModelException
	 *             is contractor to be inserted already exist in persistence
	 *             storage.
	 */
	public void create(Contractor contractor) throws ModelException,
			DuplicateContractorException;

	/**
	 * Deletes a contractor from persistence storage.
	 * 
	 * @param contractor
	 *            contractor to be deleted.
	 * @throws ModelException
	 *             if deletion is failed.
	 * @throws ContractorNotFoundException
	 *             if contractor to be deleted was not found in persistence
	 *             storage.
	 */
	public void delete(Contractor contractor) throws ModelException,
			ContractorNotFoundException;

	/**
	 * Reload <code>contractor</code> information from persistence storage then
	 * update it.
	 * 
	 * @param contractor
	 *            contractor to be refreshed.
	 * @throws ModelException
	 *             if reloading for contractor information is failed.
	 * @throws ContractorNotFoundException
	 *             if contractor does not exist in persistence storage or
	 *             deleted.
	 */
	public void refresh(Contractor contractor) throws ModelException,
			ContractorNotFoundException;

	/**
	 * Lock <code>contractor</code> so no other threads except the caller can
	 * modify it.
	 * 
	 * @param contractor
	 *            contractor to lock.
	 * @throws ModelException
	 *             if locking for contractor is failed.
	 * @throws ContractorNotFoundException
	 *             if contractor does not exist in persistence storage or
	 *             deleted.
	 */
	public void lock(Contractor contractor) throws ModelException,
			ContractorNotFoundException;

	/**
	 * Unlock <code>contractor</code> so other waiting threads can lock it.
	 * 
	 * @param contractor
	 *            contractor to unlock.
	 * @throws ModelException
	 *             if unlocking for contractor is failed.
	 * @throws ContractorNotFoundException
	 *             if contractor does not exist in persistence storage or
	 *             deleted.
	 */
	public void unlock(Contractor contractor) throws ModelException,
			ContractorNotFoundException;

	/**
	 * Discard this model from use and release any resources locked by it.
	 */
	public void discard();
}
