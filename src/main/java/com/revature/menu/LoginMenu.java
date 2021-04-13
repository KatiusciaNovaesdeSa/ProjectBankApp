package com.revature.menu;

import java.util.Scanner;

import com.revature.dao.AccountInterface;
import com.revature.exception.AccountNotFound;
import com.revature.exception.InvalidPassword;
import com.revature.model.Account;

public class LoginMenu implements MenuInterface {
	
	private MainMenu mainMenu;
	
	private MenuInterface nextMenu;
	
	private MenuInterface previousMenu;
	
	private Scanner scanner;
	
	private AccountInterface accountDao;

	@Override
	public MenuInterface advance() {
		return nextMenu;
	}

	@Override
	public void displayOptions() {
		
		String username = "";
		while (username.equals("")) {
			System.out.print("Please enter your username: ");
			username = scanner.nextLine();
			System.out.println();
		}
		
		String password = "";
		while (password.equals("")) {
			System.out.print("Please enter your password: ");
			password = scanner.nextLine();
			System.out.println();
		}
		
		Account account = null;
		
		try {
			account = accountDao.getAccountByUsernameAndPassword(username, password);
		}
		catch (AccountNotFound e) {
			System.out.println("No account found. Please try logging in again or create a new account.");
		}
		catch (InvalidPassword e) {
			System.out.println("Invalid password. Please try again.");
		}
		finally {
			System.out.println();
		}
		
		if (account != null) {
			mainMenu.setAccount(account);
			nextMenu = mainMenu;
		}
		else {
			nextMenu = previousMenu;
		}
		
	}

	@Override
	public MenuInterface previousMenu() {
		return null;
	}

	@Override
	public Scanner getScanner() {
		return this.scanner;
	}

	@Override
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public MenuInterface getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}

	public MenuInterface getNextMenu() {
		return nextMenu;
	}

	public void setNextMenu(MenuInterface nextMenu) {
		this.nextMenu = nextMenu;
	}
	
	

	public MenuInterface getPreviousMenu() {
		return previousMenu;
	}

	public void setPreviousMenu(MenuInterface previousMenu) {
		this.previousMenu = previousMenu;
	}

	public AccountInterface getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountInterface accountDao) {
		this.accountDao = accountDao;
	}

	public LoginMenu() {
		super();
	}

	public LoginMenu(AccountInterface accountDao, MainMenu mainMenu) {
		super();
		this.accountDao = accountDao;
		this.mainMenu = mainMenu;
	}

}
