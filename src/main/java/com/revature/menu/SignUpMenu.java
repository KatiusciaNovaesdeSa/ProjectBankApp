package com.revature.menu;

import java.util.Scanner;

import com.revature.dao.AccountInterface;
import com.revature.exception.AccountNotFound;
import com.revature.exception.InvalidPassword;
import com.revature.model.Account;

public class SignUpMenu implements MenuInterface {
	
	private MainMenu mainMenu;

	private MenuInterface nextMenu;
	
	private Scanner scanner;
	
	private AccountInterface accountDao;
	
	private Account account;

	public SignUpMenu() {
		super();
	}

	public SignUpMenu(AccountInterface accountDao, MainMenu mainMenu) {
		super();
		this.mainMenu = mainMenu;
		this.accountDao = accountDao;
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public MenuInterface advance() {
		return nextMenu;
	}

	@Override
	public void displayOptions() {
		
		String username = "";
		
		boolean usernameTaken = true;
		
		while (usernameTaken == true) {
			
			username = "";
			
			while (username.equals("")) {
				System.out.print("Username: ");
				username = scanner.nextLine();
				System.out.println();
				
			}
			
			usernameTaken = accountDao.getAccountByUsername(username);
			
		}
		
		String password = "";
		while (password.equals("")) {
			System.out.print("Password: ");
			password = scanner.nextLine();
			System.out.println();
		}

		String firstname = "";
		while (firstname.equals("")) {
			System.out.print("First name: ");
			firstname = scanner.nextLine();
			System.out.println();
		}
		
		
		String lastname = "";
		while (lastname.equals("")) {
			System.out.print("Last name: ");
			lastname = scanner.nextLine();
			System.out.println();
		}
		
		String street = "";
		while (street.equals("")) {
			System.out.print("Street: ");
			street = scanner.nextLine();
			System.out.println();
		}
		
		String city = "";
		while (city.equals("")) {
			System.out.print("City: ");
			city = scanner.nextLine();
			System.out.println();
		}
		
		
		String accountType = "";
		while (accountType.equals("") && notValid(accountType)) {
			System.out.print("Account type(s for SAVINGS, c for CHECKING): ");
			accountType = scanner.nextLine();
			System.out.println();
		}
		
		Account newAccount = new Account(username, password, firstname, lastname,
				street, city, accountType);
		
		accountDao.createAccount(newAccount);
		
		try {
			newAccount = accountDao.getAccountByUsernameAndPassword(username, password);
			
			mainMenu.setAccount(newAccount);
			
			nextMenu = mainMenu;
		} catch (AccountNotFound | InvalidPassword e) {
			e.printStackTrace();
		}
	}

	private boolean notValid(String accountType) {
		if(accountType.contentEquals("s") || accountType.contentEquals("c")) {
			return false;
		}
		return true;
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


}
