package com.infosys.Employee_final.exception;


public class InvalidAddressIdException extends Exception{
	private static final long serialVersionUID = 1L;
	public InvalidAddressIdException(String message) {
		super(message);	
	}
}
