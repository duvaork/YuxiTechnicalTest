package com.yuxi.technicalTest.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApplicationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6091278315696166915L;
	
	public ApplicationException(Exception e) {
		super(e);
	}

}
