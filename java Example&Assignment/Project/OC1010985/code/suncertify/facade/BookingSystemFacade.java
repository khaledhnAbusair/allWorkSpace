package suncertify.facade;

import java.util.Set;

import suncertify.model.Contractor;

/**
 * Define the implementer as an interface for booking system business logic.
 * 
 * @author Mohammad S. Abdellatif
 */
public interface BookingSystemFacade {

	/**
	 * Finds contractors according to name and location.
	 * 
	 * @param name
	 *            name to match with.
	 * @param location
	 *            location to match with.
	 * @param matchBoth
	 *            if <code>true</code> records returned must match the name and
	 *            location, otherwise, records must match name or location.
	 * @return list of contractors matching passed criteria.
	 * @throws FindException
	 *             if finding for contractors is failed.
	 */
	public Set<Contractor> findContractors(String name, String location,
			boolean matchBoth) throws FindException;

	/**
	 * Perform the logic for booking <code>contractor</code> to an
	 * <code>owner</code>.
	 * 
	 * @param contractor
	 *            contractor to book.
	 * @param owner
	 *            id for the owner to book contractor with.
	 * @throws BookingException
	 *             if booking is failed or was already booked.
	 */
	public void bookContractor(Contractor contractor, Integer owner)
			throws BookingException;
}
