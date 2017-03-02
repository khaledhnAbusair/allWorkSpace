package com.progressoft.jip.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.progressoft.jip.framework.VehicleCase;
import com.progressoft.jip.framework.VehicleCase.Task;
import com.progressoft.jip.framework.VehicleCase.Type;
import com.progressoft.jip.framework.VehicleStatus;
import com.progressoft.jip.framework.VehicleStatus.Status;
import com.progressoft.jip.framework.impl.VehicleCaseImplemntaion;
import com.progressoft.jip.simulation.implemntation.VehicleStatusImple;

public class DBUtilVehiclsStatueDao implements VehicleStatusDoa {
	// java Api to easy connection with database and prepared statment

	private DataSource dataSource;

	public DBUtilVehiclsStatueDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void create(VehicleStatus vehicleStatus) {
		QueryRunner runner = new QueryRunner(dataSource);
		String sql = "insert into VehicleStates (veh_id,arrive_time,park_time,left_time,current_status,type,task,arrive_after) values(?,?,?,?,?,?)";

		VehicleCase vehicleCase = vehicleStatus.vehicle();
		try {
			java.sql.Date sqlArrivalDate = arrivalTime(vehicleStatus);
			java.sql.Date sqlParkedDate = parkedTime(vehicleStatus);
			java.sql.Date sqlLeftDate = leftTime(vehicleStatus);

			runner.update(sql, vehicleCase.identifier(), sqlArrivalDate, sqlParkedDate, sqlLeftDate,
					vehicleStatus.status().name(), vehicleCase.type(), vehicleCase.task(), vehicleCase.arriveAfter());
		} catch (SQLException e) {
			throw new DaoException("Faild While doing insert");
		}
	}

	@Override
	public void update(VehicleStatus vehicleStatus) {

	}

	@Override
	public void delete(VehicleStatus vehicleStatus) {

	}

	@Override
	public Iterable<VehicleStatus> listAll() {
		String sql = "select veh_id , currrent_stats from vehicle_status";
		QueryRunner queryRunner = new QueryRunner(dataSource);
		try {
			List<VehicleStatus> statues = queryRunner.query(sql, new ResultSetHandler<List<VehicleStatus>>() {

				@Override
				public List<VehicleStatus> handle(ResultSet rs) throws SQLException {
					while (rs.next()) {
						String id = rs.getString("veh_id");
						String status = rs.getString("current_status");
						VehicleCaseImplemntaion vehicleCase = new VehicleCaseImplemntaion(Type.TURCK, Task.LOAD,
								Duration.ofSeconds(2));

						VehicleStatusImple statusImple = new VehicleStatusImple(vehicleCase, LocalDateTime.now(),
								Status.PARKED);
						statues.add(statusImple);
					}
					return statues;
				}

			});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statues;
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
}
