package com.golansoft.apps.computerbackup;


import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import com.jcabi.manifests.Manifests;


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
            data.put("user_name",    System.getProperty("user.name"));
            data.put("host_name",    InetAddress.getLocalHost().getHostName());
        }
        catch (IOException e) {
            Messages.showMessage(Messages.MESSAGE_ERR, "Cannot determine the profiles dir.");
        }

        try {
            data.put("version",      Manifests.read("version"));
        }
        catch (Exception e) {
            data.put("version", "Unknown");
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



    public String getUserName() {
        return data.getOrDefault("user_name", "unknown");
    }



    public String getHostName() {
        return data.getOrDefault("host_name", "unknown");
    }



    public String getAppVersion() {
        return data.getOrDefault("version", "Unknown");
    }


    public static DataSingleton getInstance() {
        if (single_instance == null) {
            single_instance = new DataSingleton();
        }

        return single_instance;
    }
}
