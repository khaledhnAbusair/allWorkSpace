package com.progresssoft.statistic;

import java.util.Iterator;

/**
 * @author u621
 *
 * @param <T>
 * @param <S>
 */
public interface StatisticsCollector<T extends Object, S extends Statistic> {
	public String getName();

	public String getDescription();

	public void visit(T object);

	public Iterator<S> calculatedStatistics();
}
