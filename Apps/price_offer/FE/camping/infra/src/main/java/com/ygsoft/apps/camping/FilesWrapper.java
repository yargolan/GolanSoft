package com.ygsoft.apps.camping;

import com.ygsoft.apps.camping.enums.Hardcoded;

import java.io.File;
import java.io.IOException;



public class FilesWrapper {

    public static final int DATA_FILE_VEHICLES = 1;
    public static final int DATA_FILE_PARTICIPANTS = 2;


    public static void touch(File file) throws IOException {

        if (file == null) {
            throw new IOException("No valid file provided.");
        }

        if (!file.exists()) {

            // Verify the parent(s) path.
            File parent = file.getParentFile();
            if (!parent.exists()) {
                if (!parent.mkdirs()) {
                    throw new IOException(String.format(
                            "Cannot generate the path '%s'",
                            parent.getAbsolutePath())
                    );
                }
            }


            if (!file.createNewFile()) {
                throw new IOException(String.format(
                        "Cannot create the new file '%s'.",
                        file.getAbsolutePath()
                ));
            }
        }
    }


    public static File getDataFile(int fileIndex) {

        String dataFilesDir;
        File dataFile = null;

        try {
            dataFilesDir = String.format(
                    "%s%s%s",
                    new File(".").getCanonicalPath(),
                    File.separatorChar,
                    Hardcoded.INITIAL_DATA_FOLDER.getText()
            );

            switch (fileIndex) {
                case DATA_FILE_VEHICLES:
                    dataFile = new File(String.format(
                            "%s%s%s",
                            dataFilesDir,
                            File.separatorChar,
                            Hardcoded.VEHICLES_DATA_FILE.getText()
                    ));
                    break;

                case DATA_FILE_PARTICIPANTS:
                    dataFile = new File(String.format(
                            "%s%s%s",
                            dataFilesDir,
                            File.separatorChar,
                            Hardcoded.PARTICIPANTS_DATA_FILE.getText()
                    ));
                    break;

                default:
                    break;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return dataFile;
    }
}



