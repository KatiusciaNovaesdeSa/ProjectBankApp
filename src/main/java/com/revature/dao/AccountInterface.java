package com.revature.dao;

import com.revature.exception.AccountNotFound;
import com.revature.exception.InvalidPassword;
import com.revature.model.Account;

public interface AccountInterface {

		public boolean getAccountByUsername(String username);
		
		public Account getAccountByUsernameAndPassword(String username, String password) throws AccountNotFound, InvalidPassword;
		
		public void createAccount(Account account);
		
		public void updateAccount(Account account);
		
		public void deleteAccount(Account account);
		
		public void viewAccountDetails(Account account);
		
		public void viewAccountBalances(Account account);
		
		public void depositIntoChecking(Account account, String amount);
		
		public void depositIntoSavings(Account account, String amount);
		
		public void withdrawFromChecking(Account account, String amount);
		
		public void withdrawFromSavings(Account account, String amount);
		
		public void transferFromCheckingToSavings(Account account, String amount);
		
		public void transferFromSavingsToChecking(Account account, String amount);
		
}

