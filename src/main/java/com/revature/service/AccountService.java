package com.revature.service;

public class AccountService {
	

	public static boolean isPositiveIntGreaterThanZero(String string) {
			
		if (string == null) {
			return false;
		}
//		
//		if (string.charAt(0) == '$') {
//			string = string.substring(1, string.length());
//		}
//		
		int i;
		
		try { 
			i = Integer.parseInt(string);
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter the right amount.");
			return false;
		}
		
		if (i > 0) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public static int convertStringToInt(String string) {
	
		
//		if (string.charAt(0) == '$') {
//			string = string.substring(1, string.length());
//		}
		
		return Integer.parseInt(string);
		
	}

}
