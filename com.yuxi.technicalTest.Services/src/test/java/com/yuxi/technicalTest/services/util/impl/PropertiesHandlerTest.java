package com.yuxi.technicalTest.services.util.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.yuxi.technicalTest.model.ApplicationException;
import com.yuxi.technicalTest.services.util.IResourceLoader;

@RunWith(MockitoJUnitRunner.class)
public class PropertiesHandlerTest {

	@InjectMocks
	private PropertiesHandler propertiesHandler;

	@Mock
	private IResourceLoader resourceLoader;

	@Mock
	private List<String> rawProperties;

	@Mock
	private Iterator<String> iterator;

	@Test
	public void whenPropertiesRequiredThenGetProperties() throws ApplicationException {

		// Set up
		when(resourceLoader.readLines(anyString())).thenReturn(rawProperties);
		when(rawProperties.iterator()).thenReturn(iterator);
		when(iterator.hasNext()).thenReturn(true, true, false);
		when(iterator.next()).thenReturn("photos_file_uri=anyLocation", "date_format=anyDateFormat");
		
		// Act
		String uri = propertiesHandler.getProperty("photos_file_uri");
		String dateFormat = propertiesHandler.getProperty("date_format");

		// Verify
		assertEquals("anyLocation", uri);
		assertEquals("anyDateFormat", dateFormat);
		
		//First time loads resource, second time uses cache.;
		verify(resourceLoader, times(1)).readLines(anyString());

	}

}