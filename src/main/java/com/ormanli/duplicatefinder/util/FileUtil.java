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
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.LazyInitializer;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.io.Files;

public class FileUtil {

	private FileUtil() {
	}

	private static volatile String scanDirectory = "";

	private static volatile LazyInitializer<ConcurrentLinkedQueue<List<String>>> lazyInitializer = new LazyInitializer<ConcurrentLinkedQueue<List<String>>>() {
		@Override
		protected ConcurrentLinkedQueue<List<String>> initialize() throws ConcurrentException {
			return Queues.newConcurrentLinkedQueue(Lists.partition(getFileList(scanDirectory), Runtime.getRuntime().availableProcessors()));
		}
	};

	private static List<String> getFileList(String directory) {
		List<String> filePaths = new LinkedList<String>();

		try {
			FluentIterable<File> files = Files.fileTreeTraverser().breadthFirstTraversal(new File(directory));

			for (File file : files) {
				if (file.isFile()) {
					filePaths.add(file.getCanonicalPath());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return filePaths;
	}

	public static List<String> getEntryList(String directory) throws Exception {
		scanDirectory = directory;
		ConcurrentLinkedQueue<List<String>> f = lazyInitializer.get();

		return f.poll();
	}

	public static String getFileHash(String path) throws Exception {
		return DigestUtils.sha256Hex(Files.toByteArray(new File(path)));
	}

}