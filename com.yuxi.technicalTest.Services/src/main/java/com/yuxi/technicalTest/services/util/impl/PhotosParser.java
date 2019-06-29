package com.yuxi.technicalTest.services.util.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yuxi.technicalTest.model.ApplicationException;
import com.yuxi.technicalTest.model.Photo;
import com.yuxi.technicalTest.services.util.IPhotosParser;

public class PhotosParser implements IPhotosParser {

	private DateTimeFormatter dateFormat;

	public Photo[] readPhotos(List<String> rawPhotos) throws ApplicationException {

		ArrayList<Photo> photos = new ArrayList<Photo>();
		String rawPhoto;

		for (int i = 0; i < rawPhotos.size(); i++) {
			rawPhoto = rawPhotos.get(i);
			if (!rawPhoto.trim().isEmpty()) {
				photos.add(readPhoto(rawPhoto));
			}
		}

		Object[] photosArray = photos.toArray();
		
		return Arrays.copyOf(photosArray, photosArray.length, Photo[].class);

	}

	private Photo readPhoto(String rawPhoto) throws ApplicationException {

		String[] parts = rawPhoto.split(",");
		String[] nameParts = parts[0].split("\\.");

		Photo photo = new Photo();
		photo.setName(nameParts[0].trim());
		photo.setExtension(nameParts[1].trim());
		photo.setLocation(parts[1].trim());
		photo.setTakenOn(LocalDateTime.parse(parts[2].trim(), dateFormat));

		return photo;
	}

	public void setDateFormat(DateTimeFormatter dateFormat) {
		this.dateFormat = dateFormat;
	}

}
