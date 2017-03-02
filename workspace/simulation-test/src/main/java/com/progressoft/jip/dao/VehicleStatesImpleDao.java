package com.progressoft.jip.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.sql.DataSource;

import org.w3c.dom.DOMException;

import com.progressoft.jip.framework.VehicleStatus;
import com.progressoft.jip.framework.VehicleStatus.Status;

public class VehicleStatesImpleDao implements VehicleStatusDoa {

	private VehicleStatus vehicleStatus;
	private DataSource dataSource;

	public VehicleStatesImpleDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void create(VehicleStatus vehicleStatus) {

		try (Connection connection = dataSource.getConnection()) {
			String sql = "insert into VehicleStates (veh_id,arrive_time,park_time,left_time,current_status,type,task,arrive_after) values(?,?,?,?,?,?)";
			excuteStatment(sql, s -> {
				java.sql.Date sqlArrivalDate = arrivalTime(vehicleStatus);

				java.sql.Date sqlParkedDate = parkedTime(vehicleStatus);

				java.sql.Date sqlLeftDate = leftTime(vehicleStatus);

				s.setString(1, vehicleStatus.vehicle().identifier());
				s.setDate(2, sqlArrivalDate);
				s.setDate(3, sqlParkedDate);
				s.setDate(4, sqlLeftDate);
				s.setString(5, vehicleStatus.status().name());
				s.setString(6, vehicleStatus.vehicle().type().name());
				s.setString(7, vehicleStatus.vehicle().task().name());
				s.setLong(8, vehicleStatus.vehicle().arriveAfter().toMillis());
			});

			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				vehicleParameter(vehicleStatus, statement);
				statement.executeUpdate();
				connection.close();
			}
		} catch (SQLException e) {
			throw new DaoException("Field while insert Vehicle ", e);
		}
	}

	@Override
	public void update(VehicleStatus vehicleStatus) {

		try (Connection connection = dataSource.getConnection()) {
			String sql = "update VehicleStates set" + " veh_id=?,arrive_time=?,park_time=?,left_time=?,"
					+ "current_status=?,type=?,task=?,arrive_after=?" + " where veh_id=?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {

				vehicleParameter(vehicleStatus, statement);

				statement.executeUpdate();

				connection.close();
			}
		} catch (SQLException e) {
			throw new DaoException("Field while update Vehicle ", e);
		}

	}

	@Override
	public void delete(VehicleStatus vehicleStatus) {
		try (Connection connection = dataSource.getConnection()) {
			String sql = "delete VehicleStates  where veh_id=?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {

				statement.executeUpdate();

				connection.close();
			}
		} catch (SQLException e) {
			throw new DaoException("Field while delete Vehicle ", e);
		}
	}

	@Override
	public Iterable<VehicleStatus> listAll() {
		try (Connection connection = dataSource.getConnection()) {
			String sql = "select * from  VehicleStates  where veh_id=?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, vehicleStatus.vehicle().identifier());
				ResultSet executeQuery = statement.executeQuery(sql);

				connection.close();

				return (Iterable<VehicleStatus>) executeQuery;

			}
		} catch (SQLException e) {
			throw new DaoException("Field while query Vehicle ", e);
		}

	}

	private java.sql.Date leftTime(VehicleStatus vehicleStatus) {
		LocalDateTime leftTime = vehicleStatus.statusTime(Status.LEFT);
		java.util.Date from3 = Date.from(leftTime.toInstant(ZoneOffset.UTC));
		java.sql.Date sqlLeftDate = new java.sql.Date(from3.getTime());
		return sqlLeftDate;
	}

	private java.sql.Date parkedTime(VehicleStatus vehicleStatus) {
		LocalDateTime parkedTime = vehicleStatus.statusTime(Status.PARKED);
		java.util.Date from2 = Date.from(parkedTime.toInstant(ZoneOffset.UTC));
		java.sql.Date sqlParkedDate = new java.sql.Date(from2.getTime());
		return sqlParkedDate;
	}

	private java.sql.Date arrivalTime(VehicleStatus vehicleStatus) {
		LocalDateTime arrivalTime = vehicleStatus.arrivalTime();
		java.util.Date from1 = Date.from(arrivalTime.toInstant(ZoneOffset.UTC));
		java.sql.Date sqlArrivalDate = new java.sql.Date(from1.getTime());
		return sqlArrivalDate;
	}

	private void vehicleParameter(VehicleStatus vehicleStatus, PreparedStatement statement) throws SQLException {
		java.sql.Date sqlArrivalDate = arrivalTime(vehicleStatus);

		java.sql.Date sqlParkedDate = parkedTime(vehicleStatus);

		java.sql.Date sqlLeftDate = leftTime(vehicleStatus);

		statement.setString(1, vehicleStatus.vehicle().identifier());
		statement.setDate(2, sqlArrivalDate);
		statement.setDate(3, sqlParkedDate);
		statement.setDate(4, sqlLeftDate);
		statement.setString(5, vehicleStatus.status().name());
		statement.setString(6, vehicleStatus.vehicle().type().name());
		statement.setString(7, vehicleStatus.vehicle().task().name());
		statement.setLong(8, vehicleStatus.vehicle().arriveAfter().toMillis());
	}

	private void excuteStatment(String sql, FillingParmeters fillingParmeters) {
		try (Connection connection = dataSource.getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				fillingParmeters.fillParameters(statement);
				statement.executeUpdate();
				connection.close();
			}
		} catch (SQLException e) {
			throw new DaoException("Field while insert Vehicle ", e);
		}
	}

	private interface FillingParmeters {
		public void fillParameters(PreparedStatement statement) throws SQLException;
	}
}
