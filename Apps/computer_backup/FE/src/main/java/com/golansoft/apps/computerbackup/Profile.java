package com.golansoft.apps.computerbackup;

import java.io.File;
import java.util.List;


public class Profile {

    private String _file, _name, _host, _desc;
    private List<String> _items;


    public String getFile() {
        return _file;
    }

    public String getName() {
        return _name;
    }

    public String getHost() {
        return _host;
    }

    public String getDesc() {
        return _desc;
    }

    public List<String> getItems() {
        return _items;
    }

    public Profile(String profileFile, String profileHost, String profileName) {
        this._file = profileFile;
        this._host = profileHost;
        this._name = profileName;
    }


    public static String getProfileFullName(String rootDir, String profileName) {
        return rootDir + File.separatorChar + profileName + ".json";
    }
}
