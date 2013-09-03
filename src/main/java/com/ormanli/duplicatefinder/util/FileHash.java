package com.ormanli.duplicatefinder.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.security.MessageDigest;

public class FileHash {

	public static String getFileHash(String path) {
		String hash = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			FileChannel in = new FileInputStream(path).getChannel();
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			WritableByteChannel out = Channels.newChannel(byteOut);
			in.transferTo(0, in.size(), out);
			out.close();
			in.close();
			byte[] mdbytes = md.digest(byteOut.toByteArray());

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < mdbytes.length; i++) {
				sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			hash = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hash;
	}
}
