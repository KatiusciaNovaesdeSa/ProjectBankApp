package com.revature.menu;


import com.revature.dao.BankDaoImpl;
import com.revature.exception.AccountNotFound;
import com.revature.exception.InvalidPassword;
import com.revature.menu.MainMenu;
import com.revature.model.Account;
import java.util.Scanner;



public class BankMenu implements MenuInterface {
		
		
		private Scanner scanner;
		

		private MenuInterface adiminMenu;
		
		private MenuInterface loginMenu;
		
		private MenuInterface signupMenu;
		
		private MenuInterface nextMenu;
		
		private MenuInterface clientMenu;
		
		private MainMenu mainMenu;

		private MenuInterface adminLogin;

		private MenuInterface adminMenu;

		private MenuInterface accountDetailsMenu;
			
	    private Account account;

		private BankDaoImpl accountDao;
		
		public void setAccount(Account account) {
			this.account = account;
		}
		
		public BankMenu(BankDaoImpl accountDao) {
			super();
			this.accountDao = accountDao;
		}

		@Override
		public MenuInterface advance() {
			return nextMenu;
		}


		@Override
		public void displayOptions() {
			
			System.out.println();
			
			System.out.println("*..................................*");
			System.out.println("*	 WELCOME TO BANKING APP	   *");
			System.out.println("* 1. Customer                      *");
			System.out.println("* 2. Employee                      *");
			System.out.println("* 3. Administrator                 *");	
			System.out.println("* 4. Quit                          *");
			System.out.println("*..................................*");

			System.out.print("Choice: ");
			String selection = scanner.nextLine();
			System.out.println();
			
			if(selection.equals("1")) {
				System.out.println("*......... CLIENT DASHBOARD..........*");
				System.out.println("*....................................*");
				System.out.println("* a. Existing Client                 *");
				System.out.println("* b. New Client                      *");
				System.out.println("* c. Quit                            *");
				System.out.println("*....................................*");
				System.out.print("Choice: ");
				selection = scanner.nextLine();
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
				
			else if(selection.equals("4")) {
				System.out.println("Exiting! Good Bye!!");
				System.out.println();
				nextMenu = null;
			}
			
			if(selection.equals("2")) {
				System.out.println("*......... EMPLOYEE DASHBOARD........*");
				System.out.println("*....................................*");
				System.out.println("* a. Clients Information             *");
				System.out.println("* b. Clients Accounts                *");
				System.out.println("* c. Quit                            *");
				System.out.println("*....................................*");
				System.out.print("Choice: ");
				selection = scanner.nextLine();
				System.out.println();
				
				if (selection.equals("a") || selection.equals("A")) {
					nextMenu = clientMenu;
							
				}
				else if (selection.equals("b") || selection.equals("B")) {
					nextMenu = accountDetailsMenu;
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
			if(selection.equals("3")) {
				System.out.println("*.......ADMINISTRATOR DASHBOARD......*");
				System.out.println("*....................................*");
				System.out.println("* a. Approve Accounts                *");
				System.out.println("* b. View Account                    *");
				System.out.println("* c. Update/Delete Account           *");
				System.out.println("* d. Quit                            *");
				System.out.println("*....................................*");
				System.out.print("Choice: ");
				selection = scanner.nextLine();
				System.out.println();
				
				if (selection.equals("a") || selection.equals("A")) {
					//nextMenu = loginMenu;
//					try {
//						accountDao.getAllAccount(account);
//					} catch (AccountNotFound e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}
				else if (selection.equals("b") || selection.equals("B")) {
					nextMenu = clientMenu;
					 
			}
							
						
				else if (selection.equals("c") || selection.equals("C")) {
					
						String username = "";
						while (username.equals("")) {
							System.out.print("Please enter the username of customer: ");
							username = scanner.nextLine();
							System.out.println();
					
					
					nextMenu = clientMenu;
					System.out.println("*...........UPDATE ACCOUNT.......... *");
					System.out.println("*....................................*");
    				System.out.println("* a. Address                         *");
					System.out.println("* b. Name                            *");
					System.out.println("* c. Username                        *");
					System.out.println("* d. Password                        *");
					System.out.println("* e. Exit                            *");
					System.out.println("*....................................*");
					
					System.out.print("Your selection: ");
					selection = scanner.nextLine();
					
					if (selection.equals("a") || selection.equals("A")) {
						System.out.println("New address");
						System.out.println();
						
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
				account.setFullAddress(street, city);

				System.out.println("Account has been updated.");
						
					}
				
		
					else if (selection.equals("b") || selection.equals("B")) {
						System.out.println("Change your name");
						System.out.println();
						
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
						
						account.setFullName(firstname, lastname);

						System.out.println("Account has been updated.");
					}
					
					else if(selection.equals("e") || selection.equals("E")) {
						System.out.println("Exiting! Good Bye!!");
						System.out.println();
						nextMenu = null;
					}
					else if (selection.equals("c") || selection.equals("C")) {
						System.out.println("Change your username");
						System.out.println();
						
						String newUsername = "";
						
						boolean usernameTaken = true;
						
						while (usernameTaken == true) {

							while (newUsername.equals("")) {
								System.out.print("New username: ");
								newUsername = scanner.nextLine();
								System.out.println();
								
							}
							
							usernameTaken = accountDao.getAccountByUsername(newUsername);
						}
						
						accountDao.updateAccountUsername(account, account.getUsername(), newUsername);
						
						account.setUsername(newUsername);

						System.out.println("Account has been updated.");
						
					}
					else {
						System.out.println("Invalid selection. Please try again");
						System.out.println();
					}
					
					accountDao.updateAccount(account);
					
					System.out.println();
					 
					}

				}
				
				
				else if (selection.equals("d") || selection.equals("D")) {
				//	nextMenu = adminLogin;
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
				
			else if(selection.equals("e") || selection.equals("E")) {
				System.out.println("Exiting! Good Bye!!");
				System.out.println();
				nextMenu = null;
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
		

		

		public BankMenu(MenuInterface loginMenu, MenuInterface signupMenu, MenuInterface clientMenu, MenuInterface adminLogin, MenuInterface adminMenu, MenuInterface accountDetailsMenu ) {
			super();
			this.loginMenu = loginMenu;
			this.signupMenu = signupMenu;
			this.clientMenu = clientMenu;
			this.adminLogin = adminLogin;

			this.adminMenu = adminMenu;
			this.accountDetailsMenu = accountDetailsMenu;
		}

		
	
		
	}
