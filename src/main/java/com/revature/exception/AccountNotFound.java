package com.revature.exception;

public class AccountNotFound  extends Exception{
	
	private static final long serialVersionUID = 1L;

		public AccountNotFound() {
			super("Inavlid Account Type, account not found!");

  }

}
