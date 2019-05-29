package com.synch.image.exception;

import java.util.Date;

public class ErrorMessage {

	private Date date = new Date();
	private String message;
	private String developerMessage;
	
	public ErrorMessage(String message, String developerMessage) {
		super();
		this.message = message;
		this.developerMessage = developerMessage;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDeveloperMessage() {
		return developerMessage;
	}
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
}
