package com.revature.model;

import com.revature.util.AccountType;

public class Account {

	private String username;
	private String password;
	
	private String fullName;
	private String firstname;
	private String lastname;
	
	
	private String fullAddress;
	private String street;
	private String city;

	private int checkingAccountBalance;
	private int savingsAccountBalance;
	private AccountTypeBalance accountTypeBalance;
	
	public Account() {
		super();
	}
	
	
	public Account(String username, String password, String firstname, 
			String lastname, String street, String city, String acctType) {
		super();
		this.username = username;
		this.password = password;
		
		this.firstname = firstname;
		this.lastname = lastname;
		
		setFullName(firstname, lastname);
		
		this.street = street;
		this.city = city;
		setFullAddress(street, city);
		

		//this.checkingAccountBalance = 0;
		//this.savingsAccountBalance = 0;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFullName() {
		return fullName;
	}

		

	public void setFullName(String firstname, String lastname) {

		
		System.out.println(this.fullName = firstname + " " + lastname);

		
		setFirstname(firstname);
		setLastname(lastname);
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getFullAddress() {
		return fullAddress;
	}
	
		
		public void setFullAddress(String street, String city) {
			
			
			this.fullAddress = street + " " + city ;
			
		
		setStreet(street);
		setCity(city);
		
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public int getCheckingAccountBalance() {
		if(getAccountType() == AccountType.CHECKING) {
			return (int)accountTypeBalance.getAccount_balance();
		}
		return checkingAccountBalance;
	}

	public void setCheckingAccountBalance(int checkingAccountBalance) {
		this.checkingAccountBalance = checkingAccountBalance;
		if(getAccountType() == AccountType.CHECKING) {
			this.accountTypeBalance.setAccount_balance(checkingAccountBalance);
		}
	}

	public int getSavingsAccountBalance() {
		if(getAccountType() == AccountType.SAVINGS) {
			return (int)accountTypeBalance.getAccount_balance();
		}
		return savingsAccountBalance;
	}

	public void setSavingsAccountBalance(int savingsAccountBalance) {
		this.savingsAccountBalance = savingsAccountBalance;
		if(getAccountType() == AccountType.SAVINGS) {
			this.accountTypeBalance.setAccount_balance(savingsAccountBalance);
		}
	}

	public AccountTypeBalance getAccountTypeBalance() {
		return accountTypeBalance;
	}

	public void setAccountTypeBalance(AccountTypeBalance accountTypeBalance) {
		this.accountTypeBalance = accountTypeBalance;
		
	}


	public AccountType getAccountType() {
		if(accountTypeBalance != null) {
			return accountTypeBalance.accountType;
		}
		// to be modified if neccessary
		return AccountType.SAVINGS;
	}

	
}
