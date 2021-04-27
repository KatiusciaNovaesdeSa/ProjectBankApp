package com.revature;

import com.revature.dao.BankDaoImpl;
import com.revature.menu.LoginMenu;
import com.revature.menu.MainMenu;
import com.revature.menu.MenuInterface;
import com.revature.menu.SignUpMenu;
import com.revature.menu.AccountDetailsMenu;
import com.revature.menu.AdminLogin;
import com.revature.menu.BankMenu;
import com.revature.menu.ClientMenu;
import com.revature.menu.AdminMenu;

import java.util.Scanner;

public class BankDriver {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		BankDaoImpl accountDao = new BankDaoImpl();
		
		MainMenu mainMenu = new MainMenu(accountDao);
		SignUpMenu signupMenu = new SignUpMenu(accountDao, mainMenu);
		LoginMenu loginMenu = new LoginMenu(accountDao, mainMenu);
		ClientMenu clientMenu = new ClientMenu(accountDao);
		AdminMenu adminMenu = new AdminMenu(accountDao);
		AdminLogin adminLogin = new AdminLogin(accountDao, adminMenu);
		AccountDetailsMenu accountDetailsMenu = new AccountDetailsMenu(accountDao);
		BankMenu bankMenu = new BankMenu(loginMenu, signupMenu, clientMenu, adminLogin, adminMenu, accountDetailsMenu);
		

		adminMenu.setNextMenu(adminMenu);
		clientMenu.setNextMenu(bankMenu);
		accountDetailsMenu.setNextMenu(bankMenu);
		signupMenu.setMainMenu(mainMenu);
		
		//adminLogin.setAdminMenu(adminMenu);
		
		loginMenu.setMainMenu(mainMenu);
		loginMenu.setPreviousMenu(bankMenu);
		
		loginMenu.setScanner(scanner);
		signupMenu.setScanner(scanner);
		bankMenu.setScanner(scanner);
		mainMenu.setScanner(scanner);
		clientMenu.setScanner(scanner);
		adminMenu.setScanner(scanner);
		accountDetailsMenu.setScanner(scanner);
		
		MenuInterface nextMenu = bankMenu;
		
		do {
			nextMenu.displayOptions();
			
			nextMenu = nextMenu.advance();
			
		} while (nextMenu != null);
		
		scanner.close();

	}
}
