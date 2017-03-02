package com.progressoft.jip.simulation.impl.ui.swinggenerator;

import com.progressoft.jip.simulation.VehicleCase.Task;
import com.progressoft.jip.simulation.VehicleCase.Type;


/**
 * @author u618
 *
 */
class TypeTaskKey {
	private Type type;
	private Task task;

	public TypeTaskKey(Type type,Task task) {
		this.type = type;
		this.task = task;
	}

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
