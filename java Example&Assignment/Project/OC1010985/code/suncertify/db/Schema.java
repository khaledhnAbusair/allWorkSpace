package suncertify.db;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A descriptor for a database file record structure.
 * 
 * @author Mohammad S. Abdellatif
 */
public class Schema implements Serializable {

	private static final long serialVersionUID = 1L;
	private final short fieldsCount;
	private final String[] fieldsNames;
	private final short[] fieldsSizes;
	private final boolean[] primaryKeyIndicator;
	private final boolean[] acceptNull;
	private final Map<String, Short> fieldsNamesIndexesMap;

	/**
	 * Construct a new descriptor with provided fields names, sizes, and primary
	 * keys.
	 * 
	 * @param fieldsNames
	 *            the names for each field.
	 * @param fieldsSizes
	 *            the size for each field.
	 * @param primaryKeyIndicator
	 *            a boolean to indicate if a field is part of primary key or
	 *            not.
	 */
	public Schema(String[] fieldsNames, short[] fieldsSizes,
			boolean[] primaryKeyIndicator, boolean[] acceptNull) {
		this.fieldsCount = (short) fieldsNames.length;
		this.fieldsNames = fieldsNames;
		this.fieldsSizes = fieldsSizes;
		this.primaryKeyIndicator = primaryKeyIndicator;
		this.acceptNull = acceptNull;

		fieldsNamesIndexesMap = new HashMap<String, Short>();
		for (short i = 0; i < fieldsCount; i++) {
			fieldsNamesIndexesMap.put(fieldsNames[i].toLowerCase(), i);
		}
	}

	/**
	 * Returns total fields count in each record.
	 * 
	 * @return total record fields count.
	 */
	public short getFieldsCount() {
		return fieldsCount;
	}

	/**
	 * Gets field name in index.
	 * 
	 * @param index
	 *            index of field.
	 * @return field name in index.
	 */
	public String getFieldName(short index) {
		return fieldsNames[index];
	}

	/**
	 * Returns the index of field with name <code>name</code>.
	 * 
	 * @param name
	 *            field name to find its index.
	 * @return index index field or <code>-1</code> if this schema does not
	 *         contain such field.
	 */
	public short getFieldIndex(String name) {
		Short index = fieldsNamesIndexesMap.get(name.toLowerCase());
		return index == null ? -1 : index;
	}

	/**
	 * Gets field size in index.
	 * 
	 * @param index
	 *            field index
	 * @return field size.
	 */
	public short getFieldSize(short index) {
		return fieldsSizes[index];
	}

	/**
	 * Returns the size of field with name <code>name</code>.
	 * 
	 * @param name
	 *            field name to find its size.
	 * @return size of field.
	 */
	public short getFieldSize(String name) {
		return getFieldSize(getFieldIndex(name));
	}

	/**
	 * Returns if field in index is part of primary key fields.
	 * 
	 * @param index
	 *            field index.
	 * @return <code>true</code> if field in index is part of primary key
	 *         fields, <code>false</code> otherwise.
	 */
	public boolean isPrimaryKeyField(short index) {
		return primaryKeyIndicator[index];
	}

	/**
	 * Checks if field <code>index</code> accepts <code>null</code>, or empty
	 * string, values.
	 * 
	 * @param index
	 *            field index.
	 * @return <code>true</code> if field accepts <code>null</code>,
	 *         <code>false</code> otherwise.
	 */
	public boolean isNullableField(short index) {
		return acceptNull[index];
	}
}
