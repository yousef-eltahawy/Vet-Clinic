package com.task.vetclinic.exception;

public class CustomServiceException extends CustomException{

	private static final long serialVersionUID = 1L;
	
	public CustomServiceException(String message, int errorCode) {
		super(message, errorCode);
	}
}