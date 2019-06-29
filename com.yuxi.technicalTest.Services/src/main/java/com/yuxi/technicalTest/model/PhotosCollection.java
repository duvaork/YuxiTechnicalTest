package com.yuxi.technicalTest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PhotosCollection {

	@JsonProperty(value = "names")
	private Photo[] photos;

	public Photo[] getPhotos() {
		return photos;
	}

	public void setPhotos(Photo[] photos) {
		this.photos = photos;
	}
	
}
