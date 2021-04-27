package com.revature.menu;

import java.util.Scanner;

import com.revature.dao.AccountInterface;
import com.revature.exception.AccountNotFound;
import com.revature.model.Account;

public class ClientMenu implements MenuInterface{

	private MainMenu mainMenu;
	
	private MenuInterface nextMenu;
	
	private MenuInterface previousMenu;
	
	private Scanner scanner;
	
	private AccountInterface accountDao;

	@Override
	public MenuInterface advance() {
		// TODO Auto-generated method stub
		return nextMenu;
	}

	@Override
	public void displayOptions() {
		// request the account username
		boolean error = false;
		
		do {
			String username = "";
			while (username.equals("")) {
				System.out.print("Please enter the username of customer: ");
				username = scanner.nextLine();
				System.out.println();
			}
		// check database if such username exist and if does load that account
		
			try {
				Account acc = accountDao.getAccountByUsernameForEmployee(username);
				// print the account details to console
			
				System.out.println("Username: " + acc.getUsername());
				System.out.println("First name: " + acc.getFirstname());
				System.out.println("Last name: " + acc.getLastname());
				System.out.println("Full address: " + acc.getFullAddress());
				System.out.println("Street: " + acc.getStreet());
				System.out.println("City: " + acc.getCity());
				
			
			} catch (AccountNotFound e) {
				System.out.printf("Account with username %s does not exist", username);
				error = true;
			}
		} while(error == true);
		 

		
	}

	@Override
	public MenuInterface previousMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scanner getScanner() {
		// TODO Auto-generated method stub
		return scanner;
	}

	@Override
	public void setScanner(Scanner scan) {
		this.scanner = scan;
		
	}
	
	public void setNextMenu(MenuInterface bankMenu) {
		this.nextMenu = bankMenu;
	}
	
	public ClientMenu() {
		super();
	}

	public ClientMenu(AccountInterface accountDao) {
		super();
		this.accountDao = accountDao;
	}

	

}
