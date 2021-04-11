package com.revature.menu;

import java.util.Scanner;


public interface MenuInterface {

	public MenuInterface advance();
	
	public void displayOptions();
	
	public MenuInterface previousMenu();
	
	public Scanner getScanner();
	
	public void setScanner(Scanner scan);
	
}


