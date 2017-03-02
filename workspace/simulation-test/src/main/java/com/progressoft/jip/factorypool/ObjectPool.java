package com.progressoft.jip.factorypool;

import java.util.HashSet;
import java.util.Set;

public abstract class ObjectPool<T> {

	private final Set<T> pooledObjects = new HashSet<T>();
	private final Set<T> takenObjects = new HashSet<T>();
	private final Object monitor = new Object();
	private final int size;

	protected ObjectPool(int size) {
		this.size = size;
	}

	protected abstract T newInstance();

	public T take() {
		T taken;

		synchronized (monitor) {
			waitAvailability();
			if (pooledObjects.isEmpty()) {
				taken = newInstance();
				pooledObjects.add(taken);
			}
			taken = pooledObjects.iterator().next();
			pooledObjects.remove(taken);
			takenObjects.add(taken);
			monitor.notifyAll();
		}
		return taken;
	}

	public void returnObj(T t) {
		synchronized (monitor) {
			checkReturnedObj(t);
			takenObjects.remove(t);
			pooledObjects.add(t);
			monitor.notifyAll();
		}
	}

	private void checkReturnedObj(T t) {
		if (!takenObjects.contains(t)) {
			throw new IllegalArgumentException("Invalid object, not returned by this pool");
		}
	}

	private void waitAvailability() {
		while (pooledObjects.size() == 0 && takenObjects.size() == size) {
			try {
				monitor.wait();
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
		}
	}
}
