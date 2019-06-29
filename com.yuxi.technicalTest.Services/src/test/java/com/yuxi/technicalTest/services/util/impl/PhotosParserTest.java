package com.yuxi.technicalTest.services.util.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.yuxi.technicalTest.model.ApplicationException;
import com.yuxi.technicalTest.model.Photo;

@RunWith(MockitoJUnitRunner.class)
public class PhotosParserTest {

	@InjectMocks
	private PhotosParser parser;

	@Mock
	private List<String> rawPhotos;

	@Test
	public void GivenRawPhotosInputThenParseToListOfPhotosModel() throws ApplicationException {

		// Set up
		when(rawPhotos.size()).thenReturn(2);
		when(rawPhotos.get(0)).thenReturn("photo.jpg, Warsaw, 2013/09/05 14:08:15");
		when(rawPhotos.get(1)).thenReturn("jhon.png, London, 2015/06/20 15:13:22");

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		parser.setDateFormat(dateFormat);

		LocalDateTime date = LocalDateTime.of(2013, 9, 5, 14, 8, 15);

		// Act
		Photo[] photos = parser.readPhotos(rawPhotos);

		// Verify
		assertEquals(2, photos.length);
		assertEquals("photo", photos[0].getName());
		assertEquals("png", photos[1].getExtension());
		assertEquals("Warsaw", photos[0].getLocation());
		assertEquals(date, photos[0].getTakenOn());
		assertNull(photos[0].getNewName());

	}
	
	@Test
	public void GivenEmptyThenSkipTheseLines() throws ApplicationException {

		// Set up
		when(rawPhotos.size()).thenReturn(6);
		when(rawPhotos.get(0)).thenReturn("  ");
		when(rawPhotos.get(1)).thenReturn("jhon.png, London, 2015-06-20 15:13:22");
		when(rawPhotos.get(2)).thenReturn("jhon.png, London, 2015-06-20 15:13:22");
		when(rawPhotos.get(3)).thenReturn("");
		when(rawPhotos.get(4)).thenReturn("jhon.png, London, 2015-06-20 15:13:22");
		when(rawPhotos.get(5)).thenReturn("\n");

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		parser.setDateFormat(dateFormat);
		
		// Act
		Photo[] photos = parser.readPhotos(rawPhotos);

		// Verify
		assertEquals(3, photos.length);

	}

}
