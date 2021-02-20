package com.ygsoft.apps.backup.data;

import java.util.List;



public class ProfileObject {

    private final String name, targetFolder, hostName;
    private final List<String> items;


    public ProfileObject(String name, String folder, String hostName, List<String> items) {
        this.name         = name;
        this.items        = items;
        this.hostName     = hostName;
        this.targetFolder = folder;
    }



    public String getName() {
        return name;
    }



    public List<String> getItems() {
        return items;
    }



    public String getTargetFolder() {
        return targetFolder;
    }



    public String getHostName() {
        return hostName;
    }
}
