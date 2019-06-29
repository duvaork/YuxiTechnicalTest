package com.yuxi.technicalTest.services.util.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuxi.technicalTest.model.ApplicationException;
import com.yuxi.technicalTest.services.util.IPropertiesHandler;
import com.yuxi.technicalTest.services.util.IResourceLoader;

public class PropertiesHandler implements IPropertiesHandler {

	private final String ENV_PATH = "YUXI_PHOTOS";
	private final String PROPERTIES_FILE = "conf.properties";

	private IResourceLoader resourceLoader;
	private Map<String, String> properties;

	public String getProperty(String name) throws ApplicationException {
		if (properties == null) {
			String filePath;
			try {
				filePath = String.format("%s\\%s", System.getenv(ENV_PATH), PROPERTIES_FILE);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException(e);
			}
			List<String> rawProperties = resourceLoader.readLines(filePath);
			String[] parts;
			properties = new HashMap<String, String>();

			for (String rawProperty : rawProperties) {
				parts = rawProperty.split("=");
				properties.put(parts[0].trim(), parts[1].trim());
			}
		}

		return properties.get(name);

	}

	public void setResourceLoader(IResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

}
