package com.revature.menu;

import com.revature.model.Account;
import java.util.Scanner;



public class BankMenu implements MenuInterface {
		
		private Scanner scanner;
		
		private MenuInterface loginMenu;
		
		private MenuInterface signupMenu;
		
		private MenuInterface nextMenu;
		
	    private Account account;
		
		public void setAccount(Account account) {
			this.account = account;
		}

		@Override
		public MenuInterface advance() {
			return nextMenu;
		}

		@Override
		public void displayOptions() {
			System.out.println();
			
			System.out.println("*..................................*");
			System.out.println("*	 Welcome to Banking App	   *");
			System.out.println("* a. Existing Client               *");
			System.out.println("* b. New Client                    *");
			System.out.println("* c. Quit                          *");
			System.out.println("*..................................*");

			System.out.print("Your selection: ");
			String selection = scanner.nextLine();
			System.out.println();
			
			if (selection.equals("a") || selection.equals("A")) {
				nextMenu = loginMenu;
			}
			else if (selection.equals("b") || selection.equals("B")) {
				nextMenu = signupMenu;
			}
			else if (selection.equals("c") || selection.equals("C")) {
				System.out.println("Exiting! Good Bye!!");
				System.out.println();
				nextMenu = null;
			}
			else {
				System.out.println("Invalid selection; please try again.");
				System.out.println();
				nextMenu = this;
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
		
		public BankMenu() {
			super();
		}

		public BankMenu(MenuInterface loginMenu, MenuInterface signupMenu) {
			super();
			this.loginMenu = loginMenu;
			this.signupMenu = signupMenu;
		}
		
	}
