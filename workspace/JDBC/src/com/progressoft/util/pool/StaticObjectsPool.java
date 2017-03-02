package com.progressoft.util.pool;
/**
 * 
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author PSLPT 147
 *
 */
public class StaticObjectsPool<T> extends ObjectPool<T> {

	private final List<T> elements = new ArrayList<>();
	private final Iterator<T> iterator;

	public StaticObjectsPool(T first, T... others) {
		super(1 + others.length);
		elements.add(first);
		elements.addAll(Arrays.asList(others));
		iterator = elements.iterator();
	}

	@Override
	protected T newInstance() {
		if (!iterator.hasNext()) {
			throw new IllegalStateException("can't create more");
		}
		return iterator.next();
	}

}
