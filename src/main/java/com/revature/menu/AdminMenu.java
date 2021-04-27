package com.revature.menu;

import com.revature.dao.BankDaoImpl;
import com.revature.exception.AccountNotFound;
import com.revature.exception.InvalidPassword;
import com.revature.model.Account;

import java.util.Scanner;

public class AdminMenu implements MenuInterface {

	private Scanner scanner;
	
	private BankDaoImpl accountDao;
	
	private Account account;

	private AdminMenu nextMenu;
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public AdminMenu() {
		super();
	}
	
	public AdminMenu(BankDaoImpl accountDao) {
		super();
		this.accountDao = accountDao;
	}

	@Override
	public MenuInterface advance() {
		return null;
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
			System.out.println("No account found. Please try logging in again.");
		}
		catch (InvalidPassword e) {
			System.out.println("Invalid password. Please try again.");
		}
		finally {
			System.out.println();
		}
		
		
		System.out.println("Welcome " + account.getFullName() + "!");
		System.out.println();
		
		String selection = "";
		System.out.println();
		
		boolean quit = false;
		
		while (quit == false) {
			
			System.out.println();

			
			System.out.println("*..................................*");
			System.out.println("*	          MENU             *");
			System.out.println("* a. Info                          *");
			System.out.println("* b. Balance                       *");		
			System.out.println("* c. Deposit                       *");
			System.out.println("* d. Transfer                      *");
			System.out.println("* e. Withdraw                      *");
			System.out.println("* f. Update                        *");
			System.out.println("* h. Exit                          *");
			System.out.println("*..................................*");

			System.out.print("Please type in your selection: ");
			selection = scanner.nextLine();
			System.out.println();
			
			if (selection.equals("a") || selection.equals("A")) {
				accountDao.viewAccountDetails(account);
			}
			else if (selection.equals("b") || selection.equals("B")) {
				accountDao.viewAccountBalances(account);
			}
			else if (selection.equals("c") || selection.equals("C")) {
				System.out.println("*............DEPOSIT MONEY.......... *");
				System.out.println("*....................................*");
				System.out.println("* 1. Checking                        *");
				System.out.println("* 2. Saving                          *");
				System.out.println("* 3. Exit                            *");
				System.out.println("*....................................*");
				System.out.println();
			//	accountDao.viewAccountBalances(account);
				System.out.print("You selected: ");
				selection = scanner.nextLine();
				System.out.println();
				
				if (selection.equals("1") || selection.equals("1")) {
					System.out.println("Checking.");
					System.out.print("How much would you like to deposit?: ");
					selection = scanner.nextLine();
					System.out.println();
					
					accountDao.depositIntoChecking(account, selection);
					
				}
				else if (selection.equals("2") || selection.equals("2")) {
					System.out.println("Savings.");
					System.out.print("How much would you like to deposit?: ");
					selection = scanner.nextLine();
					System.out.println();
					
					accountDao.depositIntoSavings(account, selection);
					
				}
				else if(selection.equals("3")) {
					 displayOptions();
				}
				else {
					System.out.println("Invalid input. Please try again.");
					System.out.println();
				}
			}

			else if (selection.equals("f") || selection.equals("F")) {

				
				System.out.println("*...........MANAGE ACCOUNT.......... *");
				System.out.println("*....................................*");
				System.out.println("* 1. Update                          *");
				System.out.println("* 2. Delete                          *");
				System.out.println("* 3. Exit                            *");
				System.out.println("*....................................*");
				
				System.out.print("Your selection: ");
				selection = scanner.nextLine();
				System.out.println();
				
				if (selection.equals("1") || selection.equals("1")) {
				
					
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
						quit = quit(selection, scanner);
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
				else if (selection.equals("2")) {
					
					int checkingBalance = account.getCheckingAccountBalance();
					int savingsBalance = account.getSavingsAccountBalance();
					
					
					if (checkingBalance > 0 || savingsBalance > 0) {
						System.out.println("You can't delete your account. Please withdraw your money first");
						System.out.println();
						accountDao.viewAccountBalances(account);
					}
					else {
						System.out.println("Are you sure you want to remove your account? Plaese enter y to delete your account or n to exit.");

						selection = scanner.nextLine();
						System.out.println();
						
						if (selection.equals("y")) {
							accountDao.deleteAccount(account);
							
							System.out.println("Account has been deleted.");
							System.out.println();
							
							quit = true;
						}
						else {
							System.out.println("Quit");
							System.out.println();
						}
					}
					
				}else if(selection.equals("3")) {
					 displayOptions();
				}
				else {
					System.out.println("Invalid input. Please try again.");
					System.out.println();
				}
			}
			else if(selection.equals("d") || selection.equals("D")) {
				System.out.println("*............TRANSFER MONEY..........*");
				System.out.println("*....................................*");
				System.out.println("* 1. Checking to Savings             *");
				System.out.println("* 2. Saving to Checking              *");
				System.out.println("* 3. Exit                            *");
				System.out.println("*....................................*");
				accountDao.viewAccountBalances(account);
				System.out.print("Your selection: ");
				selection = scanner.nextLine();
				System.out.println();
				
				if (selection.equals("1")) {
					System.out.print("How much would you like to transfer from checking?: ");
					selection = scanner.nextLine();
					System.out.println();
					accountDao.transferFromCheckingToSavings(account, selection);
				}
				else if (selection.equals("2")){
					System.out.print("How much would you like to transfer from savings?: ");
					selection = scanner.nextLine();
					System.out.println();
					accountDao.transferFromSavingsToChecking(account, selection);
				}else if(selection.equals("3")) {
					 displayOptions();
				}
				else {
					System.out.println("Invalid input. Please try again.");
					System.out.println();
				}
			}
			else if(selection.equals("h") || selection.equals("H")) {
				quit = quit(selection, scanner);
			}
			else if (selection.equals("e") || selection.equals("E")) {
				System.out.println("*..........ACCOUNT WITHDRAW..........*");
				System.out.println("*....................................*");
				System.out.println("* 1. Checking                        *");
				System.out.println("* 2. Saving                          *");
				System.out.println("* 3. Exit                            *");
				System.out.println("*....................................*");
			//	accountDao.viewAccountBalances(account);
				System.out.print("Your selection: ");
				selection = scanner.nextLine();
				System.out.println();
				
				if (selection.equals("1")) {
					System.out.println("You selected checking.");
					System.out.print("How much would you like to withdraw?: ");
					selection = scanner.nextLine();
					System.out.println();
					
					accountDao.withdrawFromChecking(account, selection);	
				}
				else if (selection.equals("2")) {
					System.out.println("You selected savings.");
					System.out.print("How much would you like to withdraw?: ");
					selection = scanner.nextLine();
					System.out.println();
					
					accountDao.withdrawFromSavings(account, selection);
				}else if(selection.equals("3")) {
					 displayOptions();
				}
				else {
					System.out.println("Invalid input; please try again.");
					System.out.println();
				}
			}
			else {
				System.out.println("Invalid input; please try again.");
				System.out.println();
			}
			
		}
		
	}

	@Override
	public MenuInterface previousMenu() {
		return null;
	}

	@Override
	public Scanner getScanner() {
		return scanner;
	}

	@Override
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public BankDaoImpl getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(BankDaoImpl accountDao) {
		this.accountDao = accountDao;
	}
	

	public boolean quit(String selection, Scanner scanner) {
		System.out.println("Are you sure you want to quit? (y / n)");
		System.out.print("Your selection: ");
		String confirmationSelection = scanner.nextLine();
		System.out.println();

		if (confirmationSelection.equals("y") || confirmationSelection.equals("Y") || confirmationSelection.equals("yes") || confirmationSelection.equals("Yes") || confirmationSelection.equals("YES")) {
			accountDao.updateAccount(account);
			System.out.println("Thank you and have a great day!");
			return true;
		}
		else if (confirmationSelection.equals("n") || confirmationSelection.equals("N") || confirmationSelection.equals("no") || confirmationSelection.equals("No") || confirmationSelection.equals("NO")) {
			return false;
		}
		else {
			System.out.println("Invalid selection; please try again.");
			System.out.println();
			return false;
		}
	}

	public void setNextMenu(AdminMenu adminMenu) {
		// TODO Auto-generated method stub
		this.nextMenu = adminMenu;
		
	}


}
