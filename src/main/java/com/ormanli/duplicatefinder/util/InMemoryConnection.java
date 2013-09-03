package com.ormanli.duplicatefinder.util;

import java.sql.Connection;
import java.sql.DriverManager;


public class InMemoryConnection {
	Connection connection;
	
	private InMemoryConnection() { 
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager.getConnection("jdbc:sqlite::memory:");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
    private static class InMemoryConnectionHolder { 
            public static final InMemoryConnection INSTANCE = new InMemoryConnection();
    }

    public static InMemoryConnection getInstance() {
            return InMemoryConnectionHolder.INSTANCE;
    }

	public Connection getConnection() {
		return connection;
	}
    
}
