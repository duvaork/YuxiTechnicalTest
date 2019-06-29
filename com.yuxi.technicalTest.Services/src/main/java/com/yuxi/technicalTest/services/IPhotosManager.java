package com.yuxi.technicalTest.services;

import com.yuxi.technicalTest.model.PhotosCollection;

public interface IPhotosManager {

	/**
	 * Assigns new names to photos found in repository. The new name corresponds to
	 * location and is number in ascendent date order.
	 * @return object containing list of photos
	 * @throws Exception
	 */
	public PhotosCollection arrange() throws Exception;
	
}
