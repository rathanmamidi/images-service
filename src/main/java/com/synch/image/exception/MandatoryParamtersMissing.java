package com.synch.image.exception;

public class MandatoryParamtersMissing extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public MandatoryParamtersMissing() {
	}
	
	public MandatoryParamtersMissing(String message) {
		super(message);
	}

}
