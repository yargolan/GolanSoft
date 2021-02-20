package com.ygsoft.apps;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.Arrays;




public class Checksums {

    static String calculateChecksum(File file) {

        try {
            InputStream fis = new FileInputStream(file);

            byte[] buffer = new byte[8192]; // was 1024 originally.

            MessageDigest complete = MessageDigest.getInstance("MD5");

            int numRead = 0;

            while (numRead != -1) {
                numRead = fis.read(buffer);
                if (numRead > 0) {
                    complete.update(buffer, 0, numRead);
                }
            }

            fis.close();

            return Arrays.toString(complete.digest());
        }
        catch (Exception e) {
            return null;
        }
    }
}
