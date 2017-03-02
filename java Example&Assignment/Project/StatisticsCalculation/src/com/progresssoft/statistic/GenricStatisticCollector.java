package com.progresssoft.statistic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.progresssoft.operations.Operations;

/**
 * Created by u621 on 15/10/2016.
 */
public class GenricStatisticCollector<T> implements StatisticsCollector<T, GenricStatistic> {

	private Set<GenricStatistic> statisticsSet;
	private Map<String, Operations<T>> operationMap;

	public GenricStatisticCollector(Map<String, Operations<T>> operationMap) {
		statisticsSet = new HashSet<>();
		this.operationMap = operationMap;
	}

	@Override
	public void visit(T value) {
		Set<String> keySet = operationMap.keySet();
		for (String key : keySet) {
			Operations<T> operation = operationMap.get(key);

			operation.doOpertaions(value);
			statisticsSet.add(new GenricStatistic("Statistic", key, operation.getMap()));
		}

	}

	@Override
	public Iterator<GenricStatistic> calculatedStatistics() {
		return statisticsSet.iterator();
	}

	@Override
	public String getName() {
		return getName();
	}

	@Override
	public String getDescription() {
		return getDescription();
	}

}
