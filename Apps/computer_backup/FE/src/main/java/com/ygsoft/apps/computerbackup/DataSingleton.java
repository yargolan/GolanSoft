package com.ygsoft.apps.computerbackup;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;



public class DataSingleton {

    private static DataSingleton single_instance = null;
    private final HashMap<String, String> data = new HashMap<>();


    private DataSingleton() {

        try {
            File rootDir     = new File("..").getCanonicalFile();
            File beRootDir   = new File(String.format("%s/BE", rootDir));
            File profilesDir = new File(String.format("%s/profiles", rootDir));
            data.put("root_dir",     rootDir.getAbsolutePath());
            data.put("be_root_dir",  beRootDir.getAbsolutePath());
            data.put("profiles_dir", profilesDir.getAbsolutePath());
        }
        catch (IOException e) {
            Messages.showMessage(Messages.MESSAGE_ERR, "Cannot determine the profiles dir.");
        }
    }


    public File getProfilesRootDir() {
        return new File(data.getOrDefault("profiles_dir", "."));
    }


    public File getRootDir() {
        return new File(data.getOrDefault("root_dir", "."));
    }


    public File getBeRootDir() {
        return new File(data.getOrDefault("be_root_dir", "."));
    }




    public static DataSingleton getInstance() {
        if (single_instance == null) {
            single_instance = new DataSingleton();
        }

        return single_instance;
    }
}
