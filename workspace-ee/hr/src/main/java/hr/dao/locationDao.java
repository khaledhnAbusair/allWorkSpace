package hr.dao;

import hr.domain.LocationInformation;

public interface locationDao {

	public void addLocation(LocationInformation location);

	public Iterable<LocationInformation> listAllLocation();
}
