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
package com.ormanli.duplicatefinder.main;

import java.util.ArrayList;

import com.ormanli.duplicatefinder.util.FileHash;
import com.ormanli.duplicatefinder.util.FileWalker;
import com.ormanli.duplicatefinder.util.SQLExecuter;

public class Worker implements Runnable {

	private String path;

	public Worker(String path) {
		super();
		this.path = path;
	}

	@Override
	public void run() {
		ArrayList<String> fileList = FileWalker.getFileList(path);

		for (String filePath : fileList) {
			String fileHash = FileHash.getFileHash(filePath);
			try {
				SQLExecuter.insertToTables(fileHash, filePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
