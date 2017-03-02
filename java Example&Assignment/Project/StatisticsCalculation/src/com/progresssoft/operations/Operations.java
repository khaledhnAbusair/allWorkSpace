/**
 * 
 */
package com.progresssoft.operations;

import java.util.Map;

/**
 * @author u621
 *
 */
public interface Operations<T> {
	public void doOpertaions(T t);
	public Map<? extends Object, Integer> getMap();

}
