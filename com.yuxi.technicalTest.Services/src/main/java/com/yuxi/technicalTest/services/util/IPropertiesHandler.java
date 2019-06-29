package com.yuxi.technicalTest.services.util;

import com.yuxi.technicalTest.model.ApplicationException;

public interface IPropertiesHandler {

	/**
	 * Gets the property configured value
	 * 
	 * @param name of the property
	 * @return property value
	 */
	public String getProperty(String name) throws ApplicationException;
	
}
