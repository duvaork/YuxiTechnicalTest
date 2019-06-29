package com.yuxi.technicalTest.services.config.impl;

import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yuxi.technicalTest.model.ApplicationException;
import com.yuxi.technicalTest.services.IPhotosManager;
import com.yuxi.technicalTest.services.config.IPhotosConfig;
import com.yuxi.technicalTest.services.impl.PhotosManager;
import com.yuxi.technicalTest.services.util.INumbersHelper;
import com.yuxi.technicalTest.services.util.IPhotosParser;
import com.yuxi.technicalTest.services.util.IPropertiesHandler;
import com.yuxi.technicalTest.services.util.IResourceLoader;
import com.yuxi.technicalTest.services.util.impl.NumbersHelper;
import com.yuxi.technicalTest.services.util.impl.PhotosParser;
import com.yuxi.technicalTest.services.util.impl.PropertiesHandler;
import com.yuxi.technicalTest.services.util.impl.ResourceLoader;

@Configuration
public class PhotosConfig implements IPhotosConfig{

	private final String PHOTOS_FILE_URI = "photos_file_uri";
	private final String DATE_FORMAT = "date_format";
	
	@Bean
	public IPhotosManager photosManagerBean() throws ApplicationException {
		PhotosManager manager = new PhotosManager();

		manager.setLoader(resourcesLoaderBean());
		manager.setNumbers(numbersHelperBean());
		manager.setParser(photosParserBean());
		manager.setFilePath(propertiesHandlerBean().getProperty(PHOTOS_FILE_URI));
		
		return manager;
	}

	@Bean
	public IPropertiesHandler propertiesHandlerBean() {
		PropertiesHandler handler = new PropertiesHandler();
		handler.setResourceLoader(resourcesLoaderBean());
		return handler;
	}

	@Bean
	public IPhotosParser photosParserBean() throws ApplicationException {
		PhotosParser parser = new PhotosParser();
		String pattern = propertiesHandlerBean().getProperty(DATE_FORMAT);
		parser.setDateFormat(DateTimeFormatter.ofPattern(pattern));
		return parser;
	}

	@Bean
	public IResourceLoader resourcesLoaderBean() {
		return new ResourceLoader();
	}

	@Bean
	public INumbersHelper numbersHelperBean() {
		return new NumbersHelper();
	}
	
}
