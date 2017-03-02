package com.progressoft.util.pool;
/**
 * 
 */


import java.util.HashSet;
import java.util.Set;

/**
 * @author PSLPT 147
 *
 */
public abstract class ObjectPool<T> {

	// store objects available for pooling
	private final Set<T> pooledObjects = new HashSet<>();
	// store objects returned by take
	private final Set<T> takenObjects = new HashSet<>();
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
				// I don't know (even care) how the objects are constructed
				taken = newInstance();//
				pooledObjects.add(taken);
			}
			// take object from pool
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
		// this shall be called inside sync
		while (pooledObjects.size() == 0 && takenObjects.size() == size) {
			try {
				monitor.wait();
			} catch (InterruptedException e) {
				// TODO do proper handling
				throw new IllegalStateException(e);
			}
		}
	}
}
