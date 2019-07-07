package com.bank.app.exception;

public class CustomerException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public CustomerException(String exception)
	{
		super(exception);
	}

}
