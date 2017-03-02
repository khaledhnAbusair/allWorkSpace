package com.progressoft.jip.dao;

import com.progressoft.jip.framework.SimulationState;

public interface SimualtionDao {
	public void create(SimulationState state);

	public void update(SimulationState state);

	public void delete(SimulationState state);

	public Iterable<SimulationState> listAll();
}
