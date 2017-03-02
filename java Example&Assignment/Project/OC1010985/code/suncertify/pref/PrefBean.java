package suncertify.pref;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * A wrapper for an instance of <code>java.util.pref.Preferneces</code> which
 * can be used as a base implementation for setting and getting user/system
 * preferences in a bean setter/getter style.
 * <p>
 * 
 * Calling {@link #flush()} will flush any changes made to this bean.
 * 
 * @author Mohammad S. Abdellatif
 */
public class PrefBean {
	/*
	 * only required getter and setters is implemented.
	 */

	/**
	 * Preferences node.
	 */
	protected Preferences preferences;

	/**
	 * Construct a new wrapper for <code>preferences</code> instance.
	 * 
	 * @param preferences
	 *            instance to be wrapped.
	 */
	public PrefBean(Preferences preferences) {
		this.preferences = preferences;
	}

	/**
	 * Flush preferences changes to backing store.
	 * 
	 * @throws BackingStoreException
	 *             if persisting for preferences is failed.
	 */
	public void flush() throws BackingStoreException {
		preferences.flush();
	}

	/**
	 * A getter for preference value for node <code>prefNode</code>.
	 * <p>
	 * The call is passed to wrapped <code>java.util.pref.Preferences</code>
	 * node by passing <code>prefNode</code> key and default value to its getter
	 * method.
	 * 
	 * @param prefNode
	 *            preferences node which to get its value.
	 * @return preferences node value.
	 */
	public final String get(PrefNode<? extends Object> prefNode) {
		return preferences.get(prefNode.getKey(),
				(String) prefNode.getDefaultValue());
	}

	/**
	 * A getter for <code>Integer</code> value for preferences node
	 * <code>prefNode</code>.
	 * 
	 * The call is passed to wrapped <code>java.util.pref.Preferences</code>
	 * node by passing <code>prefNode</code> key and default value to its getter
	 * method.
	 * 
	 * @param prefNode
	 *            preferences node which to get its integer value.
	 * @return preferences node integer value.
	 */
	public final Integer getInteger(PrefNode<? extends Object> prefNode) {
		return preferences.getInt(prefNode.getKey(),
				(Integer) prefNode.getDefaultValue());
	}
}
