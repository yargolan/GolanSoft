package com.ygsoft.apps.b1.data;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Scanner;

import com.ygsoft.apps.b1.HardCoded;



public class DataSingleton {

    private static DataSingleton single_instance = null;

    private final HashMap<String, String> data = new HashMap<>();



    private DataSingleton() {

        File profilesRootDir;
        try {
            profilesRootDir = new File(String.format("../%s", HardCoded.PROFILES.getText())).getCanonicalFile();
        }
        catch (IOException e) {
            profilesRootDir = new File(".");
        }

        // User name
        String name = System.getProperty("user.name");
        name = (name != null) ? name : "unknown user name";

        // Host name.
        String host = getHostName();

        data.put(Data.PROFILES_DIR.getText(), profilesRootDir.getAbsolutePath());
        data.put(Data.USER_NAME.getText(), name);
        data.put(Data.HOST_NAME.getText(), host);
    }



    private String getHostName() {

        String h = null;

        try {
            String name = InetAddress.getLocalHost().getHostName();
            if (name != null && !name.isEmpty()) {
                h = name;
            }
        }
        catch (IOException e) {
            try (Scanner s = new Scanner(Runtime.getRuntime().exec("hostname").getInputStream()).useDelimiter("\\A")) {
                h = s.hasNext() ? s.next() : "";
            }
            catch (IOException e1) {
                h = "unknown host name";
            }
        }

        return h;
    }



    public File getProfilesRootDir() {
        return new File(data.getOrDefault(Data.PROFILES_DIR.getText(), "."));
    }



    public static DataSingleton getInstance() {
        if (single_instance == null) {
            single_instance = new DataSingleton();
        }

        return single_instance;
    }



    public String getDataHostName() {
        return data.getOrDefault(Data.HOST_NAME.getText(), "");
    }



    public String getDataUserName() {
        return data.getOrDefault(Data.USER_NAME.getText(), "");
    }
}

