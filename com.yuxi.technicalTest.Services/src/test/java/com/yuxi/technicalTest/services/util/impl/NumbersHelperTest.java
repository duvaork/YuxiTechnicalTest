package com.yuxi.technicalTest.services.util.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NumbersHelperTest {

	@InjectMocks
	private NumbersHelper numbersHelper;
	
	@Test
	public void givenANumberThenReturnDigitsAmount() {
		
		// Act
		int digits = numbersHelper.getLength(123);
		
		//Verify
		assertEquals(3, digits);
		
	}
	
	@Test
	public void givenABigNumberThenReturnDigitsAmount() {
		
		// Act
		int digits = numbersHelper.getLength(Integer.MAX_VALUE);
		
		//Verify
		assertEquals(10, digits);
		
	}
	
}
