package suncertify.facade;

import java.util.Set;
import java.util.TreeSet;

import suncertify.model.Contractor;
import suncertify.model.ContractorCriteria;
import suncertify.model.ContractorModel;
import suncertify.model.ContractorNotFoundException;
import suncertify.model.ModelException;

/**
 * An implementation of {@link BookingSystemFacade} which uses
 * {@link ContractorModel} to retrieve and update contractors information.
 * 
 * @author Mohammad S. Abdellatif
 * @see ContractorModel
 */
public class BookingSystemFacadeImpl implements BookingSystemFacade {

	private ContractorModel contractorModel;

	/**
	 * Construct a new facade using passed contractor model.
	 * 
	 * @param contractorModel
	 *            model for retrieving and updating contractors information
	 *            with.
	 */
	public BookingSystemFacadeImpl(ContractorModel contractorModel) {
		this.contractorModel = contractorModel;
	}

	/*
     *
     */
	@Override
	public Set<Contractor> findContractors(String name, String location,
			boolean matchBoth) throws FindException {
		Set<Contractor> contractors = new TreeSet<Contractor>();
		try {
			ContractorCriteria criteria = new ContractorCriteria();

			// Empty space is the same as null.
			// Append spaces to end of name and location to find the exact match
			// for user entries.
			name =
					name == null || "".equals(name.trim()) ? null : String
							.format("%-" + Contractor.NAME_MAX_LENGTH + "s",
									name);
			location =
					location == null || "".equals(location.trim()) ? null
							: String.format("%-"
									+ Contractor.LOCATION_MAX_LENGTH + "s",
									location);

			if (matchBoth || (name == null && location == null)) {
				// if match both or both of them are null is the same
				criteria.setName(name);
				criteria.setLocation(location);
				contractors.addAll(contractorModel.find(criteria));
			} else {
				if (name != null) {
					// find according to name
					criteria.setName(name);
					contractors.addAll(contractorModel.find(criteria));
				}
				if (location != null) {
					// find again according to location and add them together
					criteria = new ContractorCriteria();
					criteria.setLocation(location);
					contractors.addAll(contractorModel.find(criteria));
				}
			}
		} catch (ModelException ex) {
			throw new FindException(ex.getMessage(), ex);
		}
		return contractors;
	}

	/*
     *
     */
	@Override
	public void bookContractor(Contractor contractor, Integer owner)
			throws BookingException {
		try {
			contractorModel.lock(contractor);
			try {
				Integer currentOwner;

				contractorModel.refresh(contractor);
				currentOwner = contractor.getOwner();

				if (currentOwner != null && !currentOwner.equals("")) {
					throw new BookingException(
							"Contractor already booked by another user, "
									+ "refresh data");
				}
				contractor.setOwner(owner);
				contractorModel.update(contractor);
			} finally {
				contractorModel.unlock(contractor);
			}
		} catch (ContractorNotFoundException ex) {
			throw new BookingException(ex.getMessage(), ex);
		} catch (ModelException ex) {
			throw new BookingException(ex.getMessage(), ex);
		}
	}
}
