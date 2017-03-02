package com.progressoft.jip.factorypool;

public class FactoryPool<T> extends ObjectPool<T> {


	private final ObjectFactory<T> factory;

	public FactoryPool(int size, ObjectFactory<T> factory) {
		super(size);
		this.factory = factory;
	}

	@Override
	protected T newInstance() {
		return factory.newInstance();
	}

}
