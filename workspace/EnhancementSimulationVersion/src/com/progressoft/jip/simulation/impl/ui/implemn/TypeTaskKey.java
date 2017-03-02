package com.progressoft.jip.simulation.impl.ui.implemn;

import com.progressoft.jip.framework.VehicleCase.Task;
import com.progressoft.jip.framework.VehicleCase.Type;

public class TypeTaskKey {
	public Type type;
	public Task task;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((task == null) ? 0 : task.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TypeTaskKey other = (TypeTaskKey) obj;
		if (task != other.task)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	
}
