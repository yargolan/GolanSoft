package com.golansoft.apps.computerbackup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import com.google.gson.Gson;



public class Profile {

    static DataSingleton dataSingleton = DataSingleton.getInstance();
    HashMap<String, Object> profileData = new HashMap<>();
    private final String profileName;
    private final String username;
    private final String hostname;
    private final String description;
    private final String targetFolder;
    private final String[] itemsToBackup;


    public Profile(String profileName, String description, String username,
                   String hostname, String targetFolder, String[] itemsToBackup) {
        this.username      = username;
        this.hostname      = hostname;
        this.profileName   = profileName;
        this.description   = description;
        this.targetFolder  = targetFolder;
        this.itemsToBackup = itemsToBackup;
    }



    public void save() throws IOException {

        // create the profile name.
        File profileFile = new File(String.format("%s/%s.json",
                dataSingleton.getProfilesRootDir(),
                this.profileName
        ));

        if (!profileFile.getParentFile().isDirectory()) {
            if (!profileFile.getParentFile().mkdirs()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "Cannot create the profiles dir.");
                return;
            }
        }

        // convert map to JSON File
        try(FileWriter writer = new FileWriter(profileFile.getAbsolutePath())) {
            Gson gson = new Gson();
            gson.toJson(profileData, writer);
        }
        catch(IOException e) {
            throw new IOException("Cannot create the profile: ", e);
        }
    }

    public String[] getItemsToBackup() {
        return itemsToBackup;
    }


    public String getName() {
        return this.profileName;
    }


    public String getDescription() {
        return this.description;
    }


    public String getHostname() {
        return this.hostname;
    }


    public String getUserName() {
        return this.username;
    }


    public String getTargetFolder() {
        return this.targetFolder;
    }
}
