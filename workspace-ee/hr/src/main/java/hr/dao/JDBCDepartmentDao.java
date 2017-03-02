package hr.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import hr.domain.DepartmentsInfo;

public class JDBCDepartmentDao implements DepartmentsDao {

	private QueryRunner runner;

	public JDBCDepartmentDao(DataSource dataSource) {
		this.runner = new QueryRunner(dataSource);
	}

	@Override
	public Iterable<DepartmentsInfo> listAll() {
		try {
			String query = "select dept_no as deptNumber,dept_name as deptName from DBEmployees.departments order by dept_no";
			return runner.query(query, new BeanListHandler<>(DepartmentsInfo.class));
		} catch (SQLException e) {
			throw new DaoException(e);

		}
	}

	@Override
	public void addDepartment(DepartmentsInfo info) throws DaoException {
		String sql = "insert into DBEmployees.departments values(?,?)";

		try {
			runner.update(sql, info.getDeptNumber(), info.getDeptName());
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}
