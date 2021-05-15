package com.ygsoft.apps.computerbackup;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;



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



    public void save(){
        try {
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

            Writer writer = new FileWriter(profileFile.getAbsolutePath());

            // convert map to JSON File
            new Gson().toJson(profileData, writer);

            // close the writer
            writer.close();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public void run() throws Exception{

        File beDir = dataSingleton.getBeRootDir();

        ProcessBuilder pb = new ProcessBuilder("python3", "--version");

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
