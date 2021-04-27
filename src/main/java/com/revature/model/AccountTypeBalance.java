package com.revature.model;

import com.revature.util.AccountType;

public class AccountTypeBalance {

	int accountId;
	double account_balance;

	AccountType accountType;

	public AccountTypeBalance(int accountId, double account_balance, AccountType accountType) {
		super();
		this.accountId = accountId;
		this.account_balance = account_balance;
		this.accountType = accountType;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getAccount_balance() {
		return account_balance;
	}

	public void setAccount_balance(double account_balance) {
		this.account_balance = account_balance;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	
}
