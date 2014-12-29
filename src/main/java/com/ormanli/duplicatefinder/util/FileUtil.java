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
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.io.Files;

public class FileUtil {

	private static final Logger logger = LogManager.getLogger(FileUtil.class);
	private volatile ConcurrentLinkedQueue<List<String>> queue;

	public FileUtil(String path) {
		List<String> fileList = getFileList(path);
		queue = Queues.newConcurrentLinkedQueue(Lists.partition(fileList, (int) Math.ceil(fileList.size() / (double) Runtime.getRuntime().availableProcessors())));
	}

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
			logger.error(StringUtils.EMPTY, e);
		}

		return filePaths;
	}

	public List<String> getEntryList() throws Exception {
		logger.info(queue);
		return queue.poll();
	}

	public String getFileHash(String path) throws Exception {
		return DigestUtils.sha256Hex(Files.toByteArray(new File(path)));
	}
}
