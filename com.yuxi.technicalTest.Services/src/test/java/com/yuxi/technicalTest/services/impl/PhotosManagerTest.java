package com.yuxi.technicalTest.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.yuxi.technicalTest.model.Photo;
import com.yuxi.technicalTest.model.PhotosCollection;
import com.yuxi.technicalTest.services.util.INumbersHelper;
import com.yuxi.technicalTest.services.util.IPhotosParser;
import com.yuxi.technicalTest.services.util.IPropertiesHandler;
import com.yuxi.technicalTest.services.util.IResourceLoader;

@RunWith(MockitoJUnitRunner.class)
public class PhotosManagerTest {

	@InjectMocks
	private PhotosManager photosManager;

	@Mock
	private IPhotosParser parser;

	@Mock
	private IResourceLoader loader;

	@Mock
	private IPropertiesHandler properties;

	@Mock
	private INumbersHelper numbers;

	@Mock
	private List<String> lines;

	@Test
	public void whenArrangeInvokedThenReturnArrangedPhotos() throws Exception {

		String warsaw = "warsaw";
		String london = "london";
		String paris = "paris";

		String photos_uri = "any_uri";
		Photo[] photos = new Photo[20];

		LocalDateTime date = LocalDateTime.of(2019, 05, 28, 0, 0, 0);

		for (int i = 0; i < 12; i++) {
			photos[i] = createPhoto("Photo_" + i, "png", warsaw, date.plusDays(i * 17 % 50));
		}

		for (int i = 12; i < 19; i++) {
			photos[i] = createPhoto("Photo_" + i, "jpg", london, date.minusHours(i));
		}

		photos[19] = createPhoto("Photo_" + 19, "jpeg", paris, date);

		// Set up
		photosManager.setFilePath(photos_uri);
		when(loader.readLines(photos_uri)).thenReturn(lines);
		when(parser.readPhotos(lines)).thenReturn(photos);
		when(numbers.getLength(12)).thenReturn(2);
		when(numbers.getLength(7)).thenReturn(1);
		when(numbers.getLength(1)).thenReturn(1);

		// Act
		PhotosCollection collection = photosManager.arrange();
		
		// Verify
		Photo[] newPhotos = collection.getPhotos();
		
		assertEquals(20, newPhotos.length);
		
		// Warsaw photos
		assertEquals("warsaw01.png", newPhotos[0].getNewName());
		assertEquals("warsaw02.png", newPhotos[3].getNewName());
		assertEquals("warsaw03.png", newPhotos[6].getNewName());
		assertEquals("warsaw04.png", newPhotos[9].getNewName());
		assertEquals("warsaw05.png", newPhotos[1].getNewName());
		
		// London photos
		assertEquals("london1.jpg", newPhotos[18].getNewName());
		assertEquals("london2.jpg", newPhotos[17].getNewName());
		assertEquals("london7.jpg", newPhotos[12].getNewName());
		
		// Paris photo
		assertEquals("paris1.jpeg", newPhotos[19].getNewName());

	}

	private Photo createPhoto(String name, String extension, String location, LocalDateTime date) {

		Photo photo = new Photo();

		// Generates no consecutive dates.
		photo.setName(name);
		photo.setExtension(extension);
		photo.setLocation(location);
		photo.setTakenOn(date);
		return photo;
	}

}
