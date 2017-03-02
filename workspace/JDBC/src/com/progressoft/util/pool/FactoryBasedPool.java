package com.progressoft.util.pool;
/**
 * 
 */


/**
 * @author PSLPT 147
 *
 */
public class FactoryBasedPool<T> extends ObjectPool<T> {

	private final ObjectFactory<T> factory;

	public FactoryBasedPool(int size, ObjectFactory<T> factory) {
		super(size);
		this.factory = factory;
	}

	@Override
	protected T newInstance() {
		// check size constraints
		return factory.newInstance();
	}

}
