package com.yuxi.technicalTest.services.util.impl;

import com.yuxi.technicalTest.services.util.INumbersHelper;

public class NumbersHelper implements INumbersHelper{

	public int getLength(int number) {
		int length = 0;
		long temp = 1;
		while (temp <= number) {
		    length++;
		    temp *= 10;
		}
		return length;
	}
	
}
