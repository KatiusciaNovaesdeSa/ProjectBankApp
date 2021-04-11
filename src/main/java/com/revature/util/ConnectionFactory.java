package com.revature.util;

import java.sql.Connection;

public interface ConnectionFactory {
	
	public Connection getConnection();
	
}
