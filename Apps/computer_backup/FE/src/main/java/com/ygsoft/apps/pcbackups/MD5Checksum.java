package com.ygsoft.apps.pcbackups;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

class MD5Checksum {

    static String getMD5Checksum (File filename) throws Exception {

        byte[] b = createChecksum(filename.getAbsolutePath());

		StringBuilder result = new StringBuilder();

		for (byte b1 : b) {
			result.append(Integer.toString((b1 & 0xff) + 0x100, 16).substring(1));
		}

		return result.toString();
    }



    private static byte[] createChecksum(String filename) throws Exception {

        InputStream fis =  new FileInputStream(filename);

		byte[] buffer = new byte[8192]; // was 1024 originally.

		MessageDigest complete = MessageDigest.getInstance("MD5");

		int numRead;

		do {
			numRead = fis.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		}
		while (numRead != -1);

		fis.close();

		return complete.digest();
    }
}
