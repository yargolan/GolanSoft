package com.ygsoft.apps.computerbackup;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import com.google.gson.Gson;



public class Profile {

    DataSingleton dataSingleton = DataSingleton.getInstance();
    HashMap<String, Object> profileData = new HashMap<>();
    private final String name, desc;
    private final List<String> items;



    public Profile(String profileName) {
        this.name = profileName;
        this.desc = "N/A";
        this.items = null;
    }


    public Profile(String profileName, String profileDescription, List<String> itemsForBackup) {
        this.name  = profileName;
        this.desc  = profileDescription;
        this.items = itemsForBackup;
    }



    public void create() {
        profileData.put("name",        this.getName());
        profileData.put("items",       this.getItems());
        profileData.put("description", this.getDesc());
    }



    public void save() {

        // create the profile name.
        File profileFile = new File(String.format("%s/%s.json",
                dataSingleton.getProfilesRootDir(),
                this.name
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

            // close the writer
            writer.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }



    public void run() throws Exception{

        File beDir = dataSingleton.getBeRootDir();

        ProcessBuilder pb = new ProcessBuilder("python3", "./computer_backup.py");

        pb.directory(beDir);

        Process p = pb.start();

        p.waitFor();

        if (p.exitValue() != 0) {
             throw new Exception("Please search the log file for further info.");
        }
    }


    public String getName() {
        return name;
    }


    public String getDesc() {
        return desc;
    }


    public List<String> getItems() {
        return items;
    }
}
