package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;


import com.revature.dao.AccountInterface;
import com.revature.exception.AccountNotFound;
import com.revature.exception.InvalidPassword;
import com.revature.model.Account;
import com.revature.model.AccountTypeBalance;
import com.revature.service.AccountService;
import com.revature.util.AccountType;
import com.revature.util.ConnectionBank;
import com.revature.dao.BankDbConnection;


public class BankDaoImpl implements AccountInterface {
	
  private Logger log = Logger.getRootLogger();
	
	Connection connection = ConnectionBank.getConnection();

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean getAccountByUsername(String username) {

		
		Connection connection = ConnectionBank.getConnection();
		
		try {
			
			String sql = "select * from accounts where user_name = '" + username + "';";
			
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			connection.close();
			
			if (rs.next()) {
				log.warn("Username already exists");
				return true;
			}
			else {
				log.info("Username available");
				return false;
			}
		
		}
		catch (SQLException e) { 
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	
	

	
	

	@Override
	public Account getAccountByUsernameAndPassword(String username, String password) throws AccountNotFound, InvalidPassword {
	
		
		Connection connection = ConnectionBank.getConnection();
		
		Account account = null;
		
		try {
			
			String sql = "select * from accounts where user_name = '" + username + "';";
			
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				
				log.info("Username found");
				
				if (rs.getString("pass_word").equals(password)) {
					log.info("Password matches");
				}
				else {
	     			log.warn("Invalid password");
					throw new InvalidPassword();
				}
				
				account = new Account();
				
				account.setUsername(rs.getString("user_name"));
				account.setPassword(rs.getString("pass_word"));
				
				account.setFirstname(rs.getString("first_name"));
				account.setLastname(rs.getString("last_name"));

			    account.setFullName(account.getFirstname(), account.getLastname());
				
				account.setStreet(rs.getString("street"));
				account.setCity(rs.getString("city"));
				
				account.setFullAddress(account.getStreet(), account.getCity());
				
				
				//account.setCheckingAccountBalance(rs.getInt("checking_account_balance"));
				//account.setSavingsAccountBalance(rs.getInt("savings_account_balance"));
				
				// fetch the customer's account type
				int accountId = rs.getInt("account_id");
				AccountTypeBalance accType = getAccountTypeBalance(accountId);
				account.setAccountTypeBalance(accType);
				connection.close();
				
				return account;
			}
			
			log.warn("Account not found");
			throw new AccountNotFound();
			
		}
		catch (SQLException e) { 
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void createAccount(Account account) {

		
		String sql = "insert into accounts "
				+ "(user_name, pass_word, first_name, last_name, street, city) "
				+ "values (?, ?, ?, ?, ?, ?);";
		
		PreparedStatement preparedStatement;
		
		Connection connection = ConnectionBank.getConnection();
		
		log.info("Creating a new account");
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, account.getUsername());
			preparedStatement.setString(2, account.getPassword());
			preparedStatement.setString(3, account.getFirstname());
			preparedStatement.setString(4, account.getLastname());
			preparedStatement.setString(5, account.getStreet());
			preparedStatement.setString(6, account.getCity());
//			//preparedStatement.setInt(9, account.getCheckingAccountBalance());
			//preparedStatement.setInt(10, account.getSavingsAccountBalance());
			preparedStatement.execute();
			
			int generatedID = -1;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select lastval()");
			if (rs.next()) {
			  System.out.println("Generated ID is: " + rs.getInt(1));
			  generatedID = rs.getInt(1);
			  createAccountTB(generatedID, account);
			}
			
			connection.close();
			log.info("New account created");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void createAccountTB(int accountRowId, Account account) {
		
		String sql = "insert into ACCOUNT_TB "
				+ "(ACC_ID, ACC_TYPE, BALANCE)"
				+ "values (?, ?, ?);";
		
		PreparedStatement preparedStatement;
		
		Connection connection = ConnectionBank.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountRowId);
			preparedStatement.setInt(2, (account.getAccountType() == AccountType.CHECKING ? 1 : 2));
			preparedStatement.setDouble(3, 0.0);
			preparedStatement.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateAccountUsername(Account account, String oldUsername, String newUsername) {
		
		String sql = "update accounts set user_name = ? where user_name = ?";
		
		log.info("Updating account username");
		
		Connection connection = ConnectionBank.getConnection();
		
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newUsername);
			preparedStatement.setString(2, oldUsername);
			preparedStatement.execute();
			
			connection.close();
			
			log.info("Username updated");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateAccount(Account account) {

		
		String sql = "update accounts set "
				+ "user_name = ?, "
				+ "pass_word = ?, "
				+ "first_name = ?, "
				+ "last_name = ?, "
				+ "street = ?, "
				+ "city = ? "
			//	+ "checking_account_balance = ?, "
			//	+ "savings_account_balance = ? "
				+ "where user_name = ?";
		
	 log.info("Updating account");
		
		Connection connection = ConnectionBank.getConnection();
		
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, account.getUsername());
			preparedStatement.setString(2, account.getPassword());
			preparedStatement.setString(3, account.getFirstname());
			preparedStatement.setString(4, account.getLastname());
			preparedStatement.setString(5, account.getStreet());
			preparedStatement.setString(6, account.getCity());
		//	preparedStatement.setInt(7, account.getCheckingAccountBalance());
		//	preparedStatement.setInt(8, account.getSavingsAccountBalance());
			preparedStatement.setString(7, account.getUsername());
			preparedStatement.execute();
			
			connection.close();
			updateAccountDetails(account.getAccountTypeBalance());
			
			log.info("Account updated");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void updateAccountDetails(AccountTypeBalance atb) {
		String sql = "update account_tb set "
				+ "balance = ? "
				+ "where acc_id = ?";
		
		log.info("Updating account balance");
		
		Connection connection = ConnectionBank.getConnection();
		
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, atb.getAccount_balance());
			preparedStatement.setInt(2, atb.getAccountId());
			preparedStatement.executeUpdate();
			
			connection.close();
			
			log.info("Account balance updated");
		}
		catch (SQLException e) {
			log.error("Error updating balance", e);
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAccount(Account account) {
	
		log.info("Deleting account");
		
		Connection connection = ConnectionBank.getConnection();
		
		String sql = "delete from accounts where user_name = ?;";
		
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, account.getUsername());
			preparedStatement.execute();
			
			connection.close();
			log.info("account deleted");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void viewAccountDetails(Account account) {
		
		log.info("Getting account details");
		
		System.out.println("Username: " + account.getUsername());
		System.out.println("Password: " + account.getPassword());
		System.out.println();
		System.out.println("Name: " + account.getFullName());
		System.out.println("Address: " + account.getFullAddress());
		System.out.println();
		//System.out.println("Checking account balance: " + account.getCheckingAccountBalance());
		//System.out.println("Savings account balance: " + account.getSavingsAccountBalance());
		System.out.println();

	}
	
	
	@Override
	public void viewAccountBalances(Account account) {
		log.info("Getting account balances");
		System.out.println("Account type: " + account.getAccountTypeBalance().getAccountType());
		System.out.println("Balance: " + account.getAccountTypeBalance().getAccount_balance());
		

//		int accountType = 0;
//		if(accountType == 1) {
//			System.out.println("Checking account balance: " + account.getCheckingAccountBalance());
//		}
//		else if((accountType == 2)) {
//			System.out.println("Savings account balance: " + account.getSavingsAccountBalance());
//		}else {
//			System.out.println("error");
//		}
//		
//		System.out.println("Checking account balance: " + account.getCheckingAccountBalance());
//		System.out.println("Savings account balance: " + account.getSavingsAccountBalance());
//		System.out.println();

	}

	@Override
	public void depositIntoChecking(Account account, String amount) {
	
		
		log.info("Deposit into checking");
		
		boolean isMoney = false;
		try {
			isMoney = AccountService.isPositiveIntGreaterThanZero(amount);
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a valid amount.");
			System.out.println();
		}
		
		if (isMoney == true) {
			System.out.println("You would like to deposit " + amount);
			System.out.println();
			int deposit = AccountService.convertStringToInt(amount);
			int currentBalance = account.getCheckingAccountBalance();
			account.setCheckingAccountBalance(currentBalance + deposit);
			System.out.println(deposit + " has been successfully deposited into your checking account.");
			System.out.println("Current checking account balance: " + account.getCheckingAccountBalance());
			System.out.println();
			
			updateAccount(account);
		}
		else {
			System.out.println("Invalid input. Please enter a valid amount.");
			System.out.println();
		}

	}

	@Override
	public void depositIntoSavings(Account account, String amount) {
		
		
		log.info("Depositing into savings");
		
		boolean isMoney = false;
		try {
			isMoney = AccountService.isPositiveIntGreaterThanZero(amount);
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a valid amount.");
			System.out.println();
		}
		
		if (isMoney == true) {
			System.out.println("You would like to deposit " + amount);
			System.out.println();
			int deposit = AccountService.convertStringToInt(amount);
			int currentBalance = account.getSavingsAccountBalance();
			account.setSavingsAccountBalance(currentBalance + deposit);
			System.out.println(deposit + " has been successfully deposited into your savings account.");
			System.out.println("Current savings account balance: " + account.getSavingsAccountBalance());
			System.out.println();
			
			updateAccount(account);
		}
		else {
			System.out.println("Invalid input. Please enter a valid positive amount.");
			System.out.println();
		}
		
	}

	@Override
	public void withdrawFromChecking(Account account, String amount) {
		
		log.info("Withdrawing from checking");
		
		boolean isMoney = false;
		try {
			isMoney = AccountService.isPositiveIntGreaterThanZero(amount);
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a valid amount.");
			System.out.println();
		}
		
		if (isMoney == true) {
			System.out.println("You would like to withdraw " + amount);
			System.out.println();
			int withdraw = AccountService.convertStringToInt(amount);
			int currentBalance = account.getCheckingAccountBalance();
			
			if (withdraw > currentBalance) {
				System.out.println("You don't have enough funds.");
				System.out.println("Nothing has been withdrawn.");
				System.out.println();
			}
			else {
				account.setCheckingAccountBalance(currentBalance - withdraw);
				System.out.println(withdraw + " has been successfully withdrawn from your checking account.");
				System.out.println("Current checking account balance: " + account.getCheckingAccountBalance());
				System.out.println();
				updateAccount(account);
			}
		}
		else {
			System.out.println("Invalid input; please enter a valid amount.");
			System.out.println();
		}

	}

	@Override
	public void withdrawFromSavings(Account account, String amount) {
	
		
		log.info("Withdrawing from savings");
		
		boolean isMoney = false;
		try {
			isMoney = AccountService.isPositiveIntGreaterThanZero(amount);
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a valid amount.");
			System.out.println();
		}
		
		if (isMoney == true) {
			System.out.println("You would like to withdraw " + amount);
			System.out.println();
			int withdraw = AccountService.convertStringToInt(amount);
			int currentBalance = account.getSavingsAccountBalance();
			
			if (withdraw > currentBalance) {
				System.out.println("You don't have enough funds.");
				System.out.println();
			}
			else {
				account.setSavingsAccountBalance(currentBalance - withdraw);
				System.out.println(withdraw + " has been successfully withdrawn from your savings account.");
				System.out.println("Current savings account balance: " + account.getSavingsAccountBalance());
				System.out.println();
				
				updateAccount(account);
			}
		}
		else {
			System.out.println("Invalid input; please enter a valid positive USD value.");
			System.out.println();
		}
		
	}

	@Override
	public void transferFromCheckingToSavings(Account account, String amount) {
	
		
		log.info("Transfering money from checking to savings");
		
		boolean isMoney = false;
		try {
			isMoney = AccountService.isPositiveIntGreaterThanZero(amount);
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid input; please enter a valid amount.");
			System.out.println();
		}
		
		if (isMoney == true) {
			System.out.println("You would like to transfer " + amount);
			System.out.println();
			int transfer = AccountService.convertStringToInt(amount);
			int checkingBalance = account.getCheckingAccountBalance();
			int savingsBalance = account.getSavingsAccountBalance();
			
			if (transfer > checkingBalance) {
				System.out.println("You don't have enough funds.");
				System.out.println();
			}
			else {
				account.setCheckingAccountBalance(checkingBalance - transfer);
				account.setSavingsAccountBalance(savingsBalance + transfer);
				System.out.println(transfer + " has been successfully transfered from checking to savings.");
				System.out.println();
				viewAccountBalances(account);
				
				updateAccount(account);
			}
		}
		else {
			System.out.println("Invalid input. Please enter a valid amount.");
			System.out.println();
		}
		
	}

	@Override
	public void transferFromSavingsToChecking(Account account, String amount) {
		
		log.info("Transfering money from savings to checking");
		
		boolean isMoney = false;
		try {
			isMoney = AccountService.isPositiveIntGreaterThanZero(amount);
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a valid amount.");
			System.out.println();
		}
		
		if (isMoney == true) {
			System.out.println("You would like to transfer " + amount);
			System.out.println();
			int transfer = AccountService.convertStringToInt(amount);
			int checkingBalance = account.getCheckingAccountBalance();
			int savingsBalance = account.getSavingsAccountBalance();
			
			if (transfer > savingsBalance) {
				System.out.println("You don't have enough funds.");
				System.out.println("Nothing has been transfered.");
				System.out.println();
			}
			else {
				account.setCheckingAccountBalance(checkingBalance + transfer);
				account.setSavingsAccountBalance(savingsBalance - transfer);
				System.out.println(transfer + " has been successfully transfered from savings to checking.");
				System.out.println();
				viewAccountBalances(account);
				
				updateAccount(account);
			}
		}
		else {
			System.out.println("Invalid input. Please enter a valid amount.");
			System.out.println();
		}
		
	}
	

//	@Override
//	public void getAllAccount(Account account)throws AccountNotFound {
//	
//
//		String sql = "select * from accounts ";
//		
//	 //log.info("Updating account");
//		
//		Connection connection = ConnectionBank.getConnection();
//		
//		PreparedStatement preparedStatement;
//		
//		try {
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1, account.getUsername());
//			preparedStatement.setString(2, account.getPassword());
//			preparedStatement.setString(3, account.getFirstname());
//			preparedStatement.setString(4, account.getLastname());
//			preparedStatement.setString(5, account.getStreet());
//			preparedStatement.setString(6, account.getCity());
//		//	preparedStatement.setInt(7, account.getCheckingAccountBalance());
//		//	preparedStatement.setInt(8, account.getSavingsAccountBalance());
//			preparedStatement.setString(7, account.getUsername());
//			preparedStatement.execute();
//			
//			connection.close();
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	
	@Override
	public Account getAccountByUsernameForEmployee(String username) throws AccountNotFound {
	
		
		Connection connection = ConnectionBank.getConnection();
		
		Account account = null;
		
		try {
			
			log.info("Connecting to the database");
			
			String sql = "select * from accounts where user_name = '" + username + "';";
			
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				
				log.info("Username found");
				account = new Account();
				int accountId = rs.getInt("account_id");
				
				account.setUsername(rs.getString("user_name"));
				account.setPassword(rs.getString("pass_word"));
				
				account.setFirstname(rs.getString("first_name"));
				account.setLastname(rs.getString("last_name"));
				

			    account.setFullName(account.getFirstname(), account.getLastname());
				
				account.setStreet(rs.getString("street"));
				account.setCity(rs.getString("city"));
				account.setFullAddress(account.getStreet(), account.getCity());
			
				// fetch the customer's account type
				AccountTypeBalance accType = getAccountTypeBalance(accountId);
				account.setAccountTypeBalance(accType);
			
				connection.close();
				
				return account;
			}
			
			log.warn("Account not found.");
			throw new AccountNotFound();
			
		}
		catch (SQLException e) { 
     		log.error("Unable to connect", e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	public AccountTypeBalance getAccountTypeBalance(int accountId) {
		
		Connection connection = ConnectionBank.getConnection();
		
		AccountTypeBalance accountTypeBalance = null;
		
		try {
			
			log.info("Connected to the database");
			
			String sql = "select * from account_tb where acc_id = " + accountId + ";";
			
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			if (rs.next()) {
				
				int acc_Id = rs.getInt("acc_id");
				int acc_type = rs.getInt("acc_type");
				double balance = rs.getInt("balance");
				AccountType accountType = null;
				
				if(acc_type == 1) {
					accountType = AccountType.CHECKING;
				}
				else if((acc_type == 2)) {
					accountType = AccountType.SAVINGS;
				}
				accountTypeBalance = new AccountTypeBalance(acc_Id, balance, accountType);
			}
		}
		catch (SQLException e) { 
    		log.error("Unable to connect", e);
			e.printStackTrace();
		}
		
		return accountTypeBalance;		
	}

	@Override
	public void getAllAccount(Account account) throws AccountNotFound {
		// TODO Auto-generated method stub
		
	}


}




























