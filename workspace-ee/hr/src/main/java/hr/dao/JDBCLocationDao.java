package hr.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import hr.domain.LocationInformation;

public class JDBCLocationDao implements locationDao {

	private static final String INSERT_LOCATION = "insert into locations (latitude,longitude,name,description) values(?,?,?,?)";
	private static final String SELECT_LOCATION = "select * from locations";
	private QueryRunner runner;

	public JDBCLocationDao(DataSource dataSource) {
		this.runner = new QueryRunner(dataSource);
	}

	@Override
	public void addLocation(LocationInformation location) {

		try {
			runner.update(INSERT_LOCATION, location.getLatitude(), location.getLongitude(), location.getName(),
					location.getDescription());
		} catch (SQLException e) {
			throw new DaoException();
		}

	}

	@Override
	public Iterable<LocationInformation> listAllLocation() {
		try {
			return runner.query(SELECT_LOCATION, new BeanListHandler<>(LocationInformation.class));
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

}
