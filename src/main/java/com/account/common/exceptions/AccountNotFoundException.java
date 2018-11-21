package com.account.common.exceptions;

public class AccountNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AccountNotFoundException() {
		
	}
	
	public AccountNotFoundException(String msg) {
		super(msg);
	}
}
