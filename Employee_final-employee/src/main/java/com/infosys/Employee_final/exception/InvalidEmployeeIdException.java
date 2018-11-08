package com.infosys.Employee_final.exception;


public class InvalidEmployeeIdException extends Exception{
	private static final long serialVersionUID = 1L;
	public InvalidEmployeeIdException(String message) {
		super(message);	
	}
}
