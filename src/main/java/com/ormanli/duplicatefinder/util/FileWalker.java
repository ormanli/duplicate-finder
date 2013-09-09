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

import java.io.File;
import java.util.ArrayList;

public class FileWalker {

	private static ArrayList<String>	fileList	= new ArrayList<String>();

	private FileWalker() {
	}

	private static void walk(String path) {

		File root = new File(path);
		File[] list = root.listFiles();

		if (list == null)
			return;

		for (File f : list) {
			if (f.isDirectory()) {
				walk(f.getAbsolutePath());
			} else {
				fileList.add(f.getAbsoluteFile().toString());
			}
		}
	}

	public ArrayList<String> getFileList(String path) {
		walk(path);
		return fileList;
	}

}
