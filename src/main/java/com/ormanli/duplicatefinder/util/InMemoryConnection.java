/*******************************************************************************
 * Copyright (c) 2013 Serdar Ormanlı.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Serdar Ormanlı - initial API and implementation
 ******************************************************************************/
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