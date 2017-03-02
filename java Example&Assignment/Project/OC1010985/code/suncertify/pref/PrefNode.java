package suncertify.pref;

/**
 * Defines a node in a preferences tree with a key and a default value.
 * 
 * @author Mohammad S. Abdellatif
 * @param <V>
 *            the default value type.
 */
public interface PrefNode<V extends Object> {

	/**
	 * Returns the key for this node.
	 * 
	 * @return the key for this node.
	 */
	public String getKey();

	/**
	 * Returns the default value for this node.
	 * 
	 * @return the default value for this node.
	 */
	public V getDefaultValue();
}
