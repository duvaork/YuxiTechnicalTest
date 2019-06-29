package com.yuxi.technicalTest.services.util;

import java.util.List;

import com.yuxi.technicalTest.model.ApplicationException;

public interface IResourceLoader {
	
	/**
	 * Reads
	 * 
	 * @param resourceUri resource location
	 * @return A list List<String> which contains resource's content line by line
	 * @throws ApplicationException
	 */
	List<String> readLines(String resourceUri) throws ApplicationException;

}
