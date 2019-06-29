package com.yuxi.technicalTest.services.config;

import com.yuxi.technicalTest.model.ApplicationException;
import com.yuxi.technicalTest.services.IPhotosManager;

public interface IPhotosConfig {

	/**
	 * Expose services to be autowired
	 * 
	 * @return
	 * @throws ApplicationException
	 */
	public IPhotosManager photosManagerBean() throws ApplicationException;
	
}
