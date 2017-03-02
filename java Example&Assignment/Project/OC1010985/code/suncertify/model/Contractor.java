package suncertify.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

/**
 * Encapsulates single contractor entity information to be persisted or
 * currently resides in a persistence storage.
 * <p>
 * 
 * This class supports properties changes notification by using
 * <code>PropertyChangeListener</code>.Property names are:
 * <ul>
 * <li>name</li>
 * <li>location</li>
 * <li>specialties</li>
 * <li>size</li>
 * <li>rate</li>
 * <li>owner</li>
 * </ul>
 * 
 * @author Mohammad S. Abdellatif
 */
public class Contractor implements Serializable, Comparable<Contractor> {

	/**
	 * Number format to be used when parsing or formatting <code>rate</code>
	 * property value.
	 */
	public static final NumberFormat RATE_FORMAT = NumberFormat
			.getCurrencyInstance(Locale.US);
	/**
	 * Holds the maximum length for contractor <code>name</code>.
	 */
	public static final int NAME_MAX_LENGTH = 32;
	/**
	 * Holds the maximum length for contractor <code>location</code>.
	 */
	public static final int LOCATION_MAX_LENGTH = 64;

	/**
	 * Holds the maximum length for contractor <code>specialties</code>.
	 */
	public static final int SPECIALTIES_MAX_LENGTH = 64;

	/**
	 * Holds the maximum length for contractor <code>size</code>.
	 */
	public static final int SIZE_MAX_LENGTH = 6;

	/**
	 * Holds the maximum length for contractor <code>rate</code>.
	 */
	public static final int RATE_MAX_LENGTH = 8;

	/**
	 * Holds the maximum length for contractor <code>owner</code>.
	 */
	public static final int OWNER_MAX_LENGTH = 8;

	private String name = "";
	private String location = "";
	private String[] specialties;
	private Integer size;
	private BigDecimal rate;
	private Integer owner;
	private PropertyChangeSupport pcs;

	/**
	 * Constructs new contractor entity.
	 */
	public Contractor() {
		pcs = new PropertyChangeSupport(this);
	}

	/**
	 * Gets contractor location property current value.
	 * 
	 * @return contractor location property current value.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets contractor location property and notify registered
	 * <code>PropertyChangeListener</code>s the property has changed.
	 * 
	 * @param location
	 *            the new value for location property.
	 */
	public void setLocation(String location) {
		pcs.firePropertyChange("location", this.location, (this.location =
				location));
	}

	/**
	 * Gets contractor name property current vlaue.
	 * 
	 * @return name property current value.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets contractor name property and notify registered
	 * <code>PropertyChangeListener</code>s the property has changed.
	 * 
	 * @param name
	 *            the new value for name property
	 */
	public void setName(String name) {
		pcs.firePropertyChange("name", this.name, (this.name = name));
	}

	/**
	 * Gets contractor owner property current value.
	 * 
	 * @return owner property current value.
	 */
	public Integer getOwner() {
		return owner;
	}

	/**
	 * Sets contractor owner property and notify registered
	 * <code>PropertyChangeListener</code>s the property has changed.
	 * 
	 * @param owner
	 *            the new value for owner property
	 */
	public void setOwner(Integer owner) {
		pcs.firePropertyChange("owner", this.owner, (this.owner = owner));
	}

	/**
	 * Gets contractor rate property current value.
	 * 
	 * @return rate property current value.
	 */
	public BigDecimal getRate() {
		return rate;
	}

	/**
	 * Sets contractor rate property and notify registered
	 * <code>PropertyChangeListener</code>s the property has changed.
	 * 
	 * @param rate
	 *            the new value for rate property.
	 */
	public void setRate(BigDecimal rate) {
		pcs.firePropertyChange("rate", this.rate, (this.rate = rate));
	}

	/**
	 * Gets contractor size property current value.
	 * 
	 * @return size property current value.
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * Sets contractor size property and notify registered
	 * <code>PropertyChangeListener</code>s the property has changed.
	 * 
	 * @param size
	 *            the new value for size property.
	 */
	public void setSize(Integer size) {
		pcs.firePropertyChange("size", this.size, (this.size = size));
	}

	/**
	 * Gets contractor specialties property current value.
	 * 
	 * @return specialties property current value.
	 */
	public String[] getSpecialties() {
		return specialties;
	}

	/**
	 * Sets contractor specialties property and notify registered
	 * <code>PropertyChangeListener</code>s the property has changed.
	 * 
	 * @param specialties
	 *            the new value for specialties property.
	 */
	public void setSpecialties(String[] specialties) {
		pcs.firePropertyChange("specialties", this.specialties,
				(this.specialties = specialties));
	}

	/**
	 * Adds listener <code>listener</code> for this bean properties changes.
	 * 
	 * @param listener
	 *            properties change listener.
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Unregister listener <code>listener</code> as property change listener
	 * from this instance.
	 * 
	 * @param listener
	 *            listener to be removed.
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	/**
	 * Add listener <code>listener</code> as property change listener to this
	 * bean only for property with name <code>propertyName</code>.
	 * 
	 * @param propertyName
	 *            name of property for which changes to be observed.
	 * @param listener
	 *            the listener to this instance property changes.
	 */
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * Removes listener <code>listener</code> as property change listener to
	 * this bean property <code>propertyName</code>.
	 * 
	 * @param propertyName
	 *            observed property name.
	 * @param listener
	 *            listener to be removed.
	 */
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(propertyName, listener);
	}

	/**
	 * Compare this instance with passed <code>contractor</code>.
	 * <p>
	 * Compare is based on <code>name</code> and <code>location</code>.
	 * 
	 * @param other
	 *            instance to compare with.
	 * @return a negative integer, zero, or a positive integer as this object is
	 *         less than, equal to, or greater than the specified object.
	 */
	@Override
	public int compareTo(Contractor other) {
		String myName = (name == null ? "" : name).trim();
		String myLocation = (location == null ? "" : location).trim();
		String otherName = (other.name == null ? "" : other.name).trim();
		String otherLocation =
				(other.location == null ? "" : other.location).trim();
		String myNameLocationCombination =
				(myName + "/" + myLocation).toLowerCase();
		String otherNameLocationCombination =
				(otherName + "/" + otherLocation).toLowerCase();

		return myNameLocationCombination
				.compareTo(otherNameLocationCombination);
	}

	/**
	 * Checks equality of this instance with passed instance.
	 * 
	 * @return Returns <code>true</code> if passed <code>obj</code> is instance
	 *         of {@link Contractor} and its name and location are equals to
	 *         this instance name and location, otherwise <code>false</code>.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Contractor)) {
			return false;
		} else {
			Contractor other = (Contractor) obj;
			String myName = (name == null ? "" : name).trim();
			String myLocation = (location == null ? "" : location).trim();
			String otherName = (other.name == null ? "" : other.name).trim();
			String otherLocation =
					(other.location == null ? "" : other.location).trim();

			return myName.equalsIgnoreCase(otherName)
					&& myLocation.equalsIgnoreCase(otherLocation);
		}
	}

	/**
	 * Returns a hash passed on this instance name and location.
	 * 
	 * @return instance hash code.
	 */
	@Override
	public int hashCode() {
		String nameLocationCombination =
				(name == null ? "" : name).trim() + "/"
						+ (location == null ? "" : location).trim();
		return nameLocationCombination.hashCode();
	}

	/**
	 * Returns a description about this contractor properties values.
	 * 
	 * @return this contractor properties values description.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[name:").append(name).append("],[location:")
				.append(location).append("],[specialties:")
				.append(Arrays.toString(specialties)).append("],[size:")
				.append(size).append("],[rate:").append(rate)
				.append("],[owner:").append(owner).append("]");
		return sb.toString();
	}
}
