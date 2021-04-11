package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import org.apache.log4j.Logger;

public class ConnectionFactoryPostgres {

	//Logger log = Logger.getRootLogger();
	
	public static String URL;
	
	public static String USERNAME;
	
	public static String PASSWORD;
	
	private static ConnectionFactoryPostgres connectionFactory = null;
	
	private ConnectionFactoryPostgres() {
		
		
		 URL =  "jdbc:postgresql://rev-canada-training.cgz20kzsu2zt.us-east-2.rds.amazonaws.com:5432/bankapp";
		 USERNAME = "bank_user";
		 PASSWORD = "password";
		
		
	}
	
	public Connection createConnection() {
		
		// issue with this version of the postgres driver where it doesn't actually load the driver
		// you have to specifically tell it to load this driver into memory
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch (ClassNotFoundException e) {
			System.out.println("Failed to load Driver");
			e.printStackTrace();
		}
		
	//	log.info("URL : " + URL);
		
		try {
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		catch (SQLException e) {
		//	log.error("Failed to connect to DB", e);
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static synchronized Connection getConnection() {
		
		// synchronized keeps program thread safe by making multiple calls wait
		
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactoryPostgres();
		}
		
		return connectionFactory.createConnection();
		
	}
}
