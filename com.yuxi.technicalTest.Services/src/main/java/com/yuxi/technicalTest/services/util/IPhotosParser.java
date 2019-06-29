package com.yuxi.technicalTest.services.util;

import java.util.List;

import com.yuxi.technicalTest.model.ApplicationException;
import com.yuxi.technicalTest.model.Photo;

public interface IPhotosParser {

	/**
	 * Converts a list of string that represents photos into a list of Photo entity
	 * 
	 * @param rawPhotos list of photos in string
	 * @return List of Photo entity
	 * @throws ApplicationException
	 */
	public Photo[] readPhotos(List<String> rawPhotos) throws ApplicationException;
	
}
