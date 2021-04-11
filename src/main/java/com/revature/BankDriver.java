package com.revature;

import com.revature.dao.BankDaoImpl;
import com.revature.menu.LoginMenu;
import com.revature.menu.MainMenu;
import com.revature.menu.MenuInterface;
import com.revature.menu.SignUpMenu;
import com.revature.menu.BankMenu;

import java.util.Scanner;

public class BankDriver {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		BankDaoImpl accountDao = new BankDaoImpl();
		
		MainMenu mainMenu = new MainMenu(accountDao);
		SignUpMenu signupMenu = new SignUpMenu(accountDao, mainMenu);
		LoginMenu loginMenu = new LoginMenu(accountDao, mainMenu);
		BankMenu bankMenu = new BankMenu(loginMenu, signupMenu);
		
		signupMenu.setMainMenu(mainMenu);
		
		loginMenu.setMainMenu(mainMenu);
		loginMenu.setPreviousMenu(bankMenu);
		
		loginMenu.setScanner(scanner);
		signupMenu.setScanner(scanner);
		bankMenu.setScanner(scanner);
		mainMenu.setScanner(scanner);
		
		MenuInterface nextMenu = bankMenu;
		
		do {
			nextMenu.displayOptions();
			
			nextMenu = nextMenu.advance();
			
		} while (nextMenu != null);
		
		scanner.close();

	}
}
