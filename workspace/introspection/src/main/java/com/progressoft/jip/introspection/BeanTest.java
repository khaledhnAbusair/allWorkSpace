package com.progressoft.jip.introspection;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class BeanTest {
	public static void main(String[] args) throws Exception {
		Employee employee = new Employee();
		Map<String, String> values = new HashMap<String, String>();
		values.put("userID", "u621");
		values.put("userName", "Khaled");
		values.put("salary", "200.2");
		values.put("hireDate", "10/5/1994");
		Converter converter = new Converter() {

			@Override
			public <T> T convert(Class<T> type, Object value) {

				if (Date.class.isAssignableFrom(type)) {
					if (value instanceof String) {
						String valueAsString = (String) value;
						DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
						try {
							return (T) dateFormat.parse(valueAsString);
						} catch (ParseException e) {
							throw new IllegalArgumentException();
						}
					}
				}
				throw new IllegalArgumentException("Unable To convert");

			}
		};

		ConvertUtils.register(converter, Date.class);

		BeanUtils.populate(employee, values);

	}
}
