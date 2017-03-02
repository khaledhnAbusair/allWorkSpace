package hr.dao;

import hr.domain.DepartmentsInfo;

public interface DepartmentsDao {

	public Iterable<DepartmentsInfo> listAll();

	public void addDepartment(DepartmentsInfo info) throws DaoException;
}
