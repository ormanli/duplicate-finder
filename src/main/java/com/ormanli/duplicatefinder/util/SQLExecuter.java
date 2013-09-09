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

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLExecuter {

	private SQLExecuter() {
	}

	public static void createTables() {

		Statement statement = null;
		try {
			statement = InMemoryConnection.getInstance().getConnection().createStatement();

			statement.executeUpdate("drop table if exists person");
			statement.executeUpdate("create table files (file string, path string)");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void insertToTables(String file, String path) throws Exception {
		Statement statement = null;
		try {
			statement = InMemoryConnection.getInstance().getConnection().createStatement();

			statement.executeUpdate("insert into files values('" + file + "', '" + path + "')");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static List<String> getFileList() throws Exception {
		Statement statement = null;
		ArrayList<String> fileList = new ArrayList<String>();
		try {
			statement = InMemoryConnection.getInstance().getConnection().createStatement();

			ResultSet rs = statement.executeQuery("select file from files group by file having count(file)>1");

			while (rs.next()) {
				fileList.add(rs.getString("file"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return fileList;
	}

	public static List<String> getPathList(String file) throws Exception {
		Statement statement = null;
		ArrayList<String> pathList = new ArrayList<String>();
		try {
			statement = InMemoryConnection.getInstance().getConnection().createStatement();

			ResultSet rs = statement.executeQuery("select path from files where file='" + file + "'");

			while (rs.next()) {
				pathList.add(rs.getString("path"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return pathList;
	}
}
