package com.capgemini.bankapp.exception;

public class CustomerNotFoundException extends RuntimeException{
     
	public CustomerNotFoundException(String message) {
			super(message);
		}

	}
