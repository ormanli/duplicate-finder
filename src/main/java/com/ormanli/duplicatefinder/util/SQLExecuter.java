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
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import com.ormanli.duplicatefinder.db.public_.tables.Files;

public class SQLExecuter {

	private Connection connection;
	private DSLContext create;

	private SQLExecuter() {
		try {
			Class.forName("org.h2.Driver");
			this.connection = DriverManager.getConnection("jdbc:h2:mem:files");
			this.connection.setAutoCommit(true);
			create = DSL.using(this.connection, SQLDialect.H2);
			// create.createTable(Files.FILES).column(Files.FILES.FILE,
			// SQLDataType.CHAR).column(Files.FILES.PATH,
			// SQLDataType.CHAR).execute();
			create.execute("CREATE TABLE FILES(FILE VARCHAR, PATH VARCHAR);");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class InMemoryConnectionHolder {
		public static final SQLExecuter INSTANCE = new SQLExecuter();
	}

	public static SQLExecuter getInstance() {
		return InMemoryConnectionHolder.INSTANCE;
	}

	public void insertToTables(String file, String path) throws Exception {
		create.insertInto(Files.FILES, Files.FILES.FILE, Files.FILES.PATH).values(file, path).execute();
	}

	public List<String> getHashs() throws Exception {
		return create.select(Files.FILES.FILE).from(Files.FILES).groupBy(Files.FILES.FILE).having(Files.FILES.FILE.count().greaterThan(1)).fetch().getValues(Files.FILES.FILE);
	}

	public List<String> getPaths(String hash) throws Exception {
		return create.select(Files.FILES.PATH).from(Files.FILES).where(Files.FILES.FILE.equal(hash)).fetch().getValues(Files.FILES.PATH);
	}
}
