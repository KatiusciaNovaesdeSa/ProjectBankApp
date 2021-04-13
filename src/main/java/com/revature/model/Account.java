package com.revature.model;

import com.revature.util.AccountType;

public class Account {

	private String username;
	private String password;
	
	private String fullName;
	private String firstname;
	//private String middlename;
	private String lastname;
	
//	private String email;
//	private String phoneNumber;
	
	private String fullAddress;
	private String street;
	private String city;
//	private String state;
//	private String zipcode;
	AccountType accountType;

	private int checkingAccountBalance;
	private int savingsAccountBalance;
	
	public Account() {
		super();
	}
	
//	public Account(String username, String password, String firstname, String middlename,
//			String lastname, String street, String city,
//			String state, String zipcode, String email, String phoneNumber) {
	
	
	public Account(String username, String password, String firstname, 
			String lastname, String street, String city, String acctType) {
		super();
		this.username = username;
		this.password = password;
		
		this.firstname = firstname;
	//	this.middlename = middlename;
		this.lastname = lastname;
		
	//	setFullName(firstname, middlename, lastname);
		setFullName(firstname, lastname);
		
		this.street = street;
		this.city = city;
	//	this.state = state;
	//	this.zipcode = zipcode;
		
		//setFullAddress(street, city, state, zipcode);

		setFullAddress(street, city);
		
//		this.email = email;
//		this.phoneNumber = phoneNumber;
//		
		// checking and savings accounts balances are initialized to 0
		// user has to deposit money after creating account
		if(acctType.contentEquals("c")) {
			this.accountType = AccountType.CHECKING;
		}
		else if(acctType.contentEquals("s")) {
			this.accountType = AccountType.SAVINGS;
		}
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
		return checkingAccountBalance;
	}

	public void setCheckingAccountBalance(int checkingAccountBalance) {
		this.checkingAccountBalance = checkingAccountBalance;
	}

	public int getSavingsAccountBalance() {
		return savingsAccountBalance;
	}

	public void setSavingsAccountBalance(int savingsAccountBalance) {
		this.savingsAccountBalance = savingsAccountBalance;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	
}
