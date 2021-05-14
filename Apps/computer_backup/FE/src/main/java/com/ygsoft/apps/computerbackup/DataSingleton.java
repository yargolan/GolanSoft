package com.ygsoft.apps.computerbackup;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;



public class DataSingleton {

    private static DataSingleton single_instance = null;
    private final HashMap<String, String> data = new HashMap<>();


    private DataSingleton() {

        try {
            File profilesDir = new File(String.format("%s/profiles",
                    new File("..").getCanonicalFile()
            ));
            data.put("profiles_dir", profilesDir.getAbsolutePath());
        }
        catch (IOException e) {
            Messages.showMessage(Messages.MESSAGE_ERR, "Cannot determine the profiles dir.");
        }
    }


    public File getProfilesRootDir() {
        return new File(data.getOrDefault("profiles_dir", "."));
    }


    public static DataSingleton getInstance() {
        if (single_instance == null) {
            single_instance = new DataSingleton();
        }

        return single_instance;
    }
}
