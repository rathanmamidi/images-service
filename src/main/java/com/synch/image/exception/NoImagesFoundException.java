package com.synch.image.exception;

public class NoImagesFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NoImagesFoundException(String message) {
		super(message);
	}
}
