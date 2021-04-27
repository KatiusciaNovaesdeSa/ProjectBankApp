package com.revature.test;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;

import com.revature.dao.BankDbConnection;
import com.revature.menu.BankMenu;
import com.revature.model.AccountTypeBalance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;
//	import static org.mockito.Mockito.verify;
//	import static org.mockito.Mockito.when;



	//@ExtendWith(MockitoExtension.class)
public class AccountTest {
	
   private BankDbConnection connection;
	
   AccountTypeBalance account_balance;
   
   BankMenu selection;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
		

	@Test(timeout=1000)  // milliseconds
	   public void test() {
	      while (true) {}
	   }
	
	
	@Test
	@Order(1)
	public void checkingAccountTest() {
		System.out.println("in add test");
		assertEquals(1, account_balance.getAccountType(), "This is the checking account"); 
	}
	
	@Test
	@Order(2)
	public void savingAccountTest() {
		System.out.println("in add test");
		assertEquals(2, account_balance.getAccountType(), "This is the saving account"); 
	}
	
	@Test
	@Order(3)
	public void noAccountTest() {
		System.out.println("in add test");
		assertEquals(3, account_balance.getAccountType(), "This account number doesn't exist"); 
	}

}
