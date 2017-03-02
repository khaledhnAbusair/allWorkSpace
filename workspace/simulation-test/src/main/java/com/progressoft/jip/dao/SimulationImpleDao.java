package com.progressoft.jip.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.progressoft.jip.framework.SimulationState;

public class SimulationImpleDao implements SimualtionDao {

	private DataSource dataSource;

	public SimulationImpleDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void create(SimulationState state) {

		try (Connection connection = dataSource.getConnection()) {
			String sql = "insert into simulation_state (occu_lot,last_update) values(?,?)";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				vehicleParameter(state, statement);
				statement.executeUpdate();
				connection.close();
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void update(SimulationState state) {

		try (Connection connection = dataSource.getConnection()) {
			String sql = "update simulation_state set" + " occu_lot=?,last_update=? where sim_id=?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {

				vehicleParameter(state, statement);

				statement.executeUpdate();

				connection.close();
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}

	}

	@Override
	public void delete(SimulationState state) {
		try (Connection connection = dataSource.getConnection()) {
			String sql = "delete simulation_state  where sim_id=?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {

				statement.executeUpdate();

				connection.close();
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public Iterable<SimulationState> listAll() {
		try (Connection connection = dataSource.getConnection()) {
			String sql = "select * from  simulation_state";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				ResultSet executeQuery = statement.executeQuery(sql);
				List<SimulationState> states = new ArrayList<>();
				ResultSetMetaData metadata = executeQuery.getMetaData();
				int numcols = metadata.getColumnCount();
				while (executeQuery.next()) {
					int i = 1;
					while (i < numcols) {
						// states.add(executeQuery.getString(i++));
					}
				}

				connection.close();

				return (Iterable<SimulationState>) executeQuery;

			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	private void vehicleParameter(SimulationState simulationState, PreparedStatement statement) throws SQLException {
		statement.setInt(1, simulationState.occupiedParkinglots());
		statement.setLong(2, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)).getTime());

	}

}
