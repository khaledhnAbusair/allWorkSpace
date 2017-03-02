package com.progresssoft.statistic;

/**
 * Created by u621 on 15/10/2016.
 */
public class GenricStatistic implements Statistic {

	private String details;
	private Object value;
	private String key;

	public GenricStatistic(String details, String key, Object value) {
		this.details = details;
		this.value = value;
		this.key = key;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public String getDetails() {
		return details;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		GenricStatistic other = (GenricStatistic) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("GenricStatistic [details=%s, key=%s ,value=%s ]", details, key, value);
	}

}
