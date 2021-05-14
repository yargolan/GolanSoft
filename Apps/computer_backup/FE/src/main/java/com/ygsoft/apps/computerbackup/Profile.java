package com.ygsoft.apps.computerbackup;

import java.util.List;

public class Profile {

    private String name, description;
    private List<String> items;

    public Profile() {}


    public void create (String profileName, String profileDescription, List<String> itemsForBackup) {
        System.out.println("profileName = " + profileName);
        System.out.println("profileDescription = " + profileDescription);
    }


    public void save() {

    }
}
