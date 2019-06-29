package com.yuxi.technicalTest.config.impl;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.yuxi.technicalTest.Api.PhotosResource;

@Component
public class RestServices extends ResourceConfig{

	public RestServices() {
		register(PhotosResource.class);
	}
	
}
