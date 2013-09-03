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
