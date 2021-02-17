package com.ygsoft.apps.utilsandwrappers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileUtilities extends File {

    public FileUtilities(String pathname) {
        super(pathname);
    }



    public List<String> read() throws IOException {

        List<String> theFile = new ArrayList<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(this.getAbsolutePath()));

            String line = br.readLine();

            while (line != null) {
                theFile.add(line);
                line = br.readLine();
            }
        }
        catch (IOException e) {
            throw new IOException(String.format (
                    "Cannot read the file '%s'",
                    this.getName()),
            e);
        }

        return theFile;
    }
}


