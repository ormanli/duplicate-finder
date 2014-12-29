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

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ormanli.duplicatefinder.util.FileUtil;
import com.ormanli.duplicatefinder.util.SQLExecuter;

public class Worker implements Runnable {

	private static final Logger logger = LogManager.getLogger(Worker.class);

	private FileUtil fileUtil;

	public Worker(FileUtil fileUtil) {
		this.fileUtil = fileUtil;
	}

	@Override
	public void run() {
		try {
			List<String> fileList = fileUtil.getEntryList();

			if (fileList != null) {
				logger.info("Worker " + this.hashCode() + " list length " + fileList.size());
				for (String filePath : fileList) {
					String fileHash = fileUtil.getFileHash(filePath);
					logger.info("File: " + filePath + " Hash: " + fileHash);
					SQLExecuter.getInstance().insertToTables(fileHash, filePath);
				}
			}
		} catch (Exception e) {
			logger.error(StringUtils.EMPTY, e);
		}
	}

}
