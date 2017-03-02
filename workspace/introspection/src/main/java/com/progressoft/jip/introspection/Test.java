package com.progressoft.jip.introspection;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;

public class Test {

	public static void main(String[] args) throws Exception {
		Employee employee =new Employee();
		employee.setUserName("Khaled");
		employee.setUserID("u621");
		BeanInfo beanInfo = Introspector.getBeanInfo(Employee.class);
		printProparties(employee, beanInfo);
		MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
		BeanInfo[] additionalBeanInfo = beanInfo.getAdditionalBeanInfo();
		
	
		

	}

	private static void printProparties(Employee employee, BeanInfo beanInfo)
			throws IllegalAccessException, InvocationTargetException {
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			System.out.println(propertyDescriptor.getPropertyType() + " : " + propertyDescriptor.getDisplayName());
			System.out.println("-------------------------------------------");

			Method readMethod = propertyDescriptor.getReadMethod();

			Method writeMethod = propertyDescriptor.getWriteMethod();

			if (writeMethod != null) {
				System.out.println(writeMethod.getName());
			
			}
			System.out.println("------------");
			if (readMethod != null) {
				System.out.println(readMethod.getName());
				System.out.println(readMethod.invoke(employee));
			}

			
			
			
		}
	}

}
