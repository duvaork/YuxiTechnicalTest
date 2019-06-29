package com.yuxi.technicalTest.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import com.yuxi.technicalTest.model.ApplicationException;
import com.yuxi.technicalTest.model.Photo;
import com.yuxi.technicalTest.model.PhotosCollection;
import com.yuxi.technicalTest.services.IPhotosManager;
import com.yuxi.technicalTest.services.util.INumbersHelper;
import com.yuxi.technicalTest.services.util.IPhotosParser;
import com.yuxi.technicalTest.services.util.IResourceLoader;

public class PhotosManager implements IPhotosManager {

	private IPhotosParser parser;
	private IResourceLoader loader;
	private INumbersHelper numbers;
	private String filePath;

	public PhotosCollection arrange() throws Exception {

		PhotosCollection collection = null;

		try {
			collection = new PhotosCollection();

			List<String> rawPhotos = loader.readLines(filePath);
			Photo[] photos = parser.readPhotos(rawPhotos);

			Map<String, NavigableSet<Photo>> photoGroups = groupByLocation(photos);

			for (Map.Entry<String, NavigableSet<Photo>> entry : photoGroups.entrySet()) {
				renamePhotosInGroup(entry.getKey(), entry.getValue());
			}

			collection.setPhotos(photos);
		} catch (ApplicationException e) {
			throw e;
		} catch (Exception ex) {
			throw new ApplicationException(ex);
		}

		return collection;

	}

	private Map<String, NavigableSet<Photo>> groupByLocation(Photo[] photos) {

		Map<String, NavigableSet<Photo>> groups = new HashMap<String, NavigableSet<Photo>>();

		for (int i = 0; i < photos.length; i++) {
			String location = photos[i].getLocation();
			if (!groups.containsKey(location)) {
				TreeSet<Photo> photosSet = new TreeSet<Photo>();
				groups.put(location, photosSet);
			}
			groups.get(location).add(photos[i]);
		}

		return groups;

	}

	private void renamePhotosInGroup(String location, NavigableSet<Photo> photos) {

		String nameFormat = "%s%s.%s";
		int indexLength = numbers.getLength(photos.size());
		int index = 1;
		String newName;
		String indexText;

		for (Photo photo : photos) {
			indexText = StringUtils.leftPad(String.valueOf(index), indexLength, "0");
			newName = String.format(nameFormat, location, indexText, photo.getExtension());
			photo.setNewName(newName);
			index++;
		}
	}

	public void setParser(IPhotosParser parser) {
		this.parser = parser;
	}

	public void setLoader(IResourceLoader loader) {
		this.loader = loader;
	}

	public void setNumbers(INumbersHelper numbers) {
		this.numbers = numbers;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}