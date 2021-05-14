package com.ygsoft.apps.computerbackup;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import com.google.gson.*;


public class Profile {

    public Profile() {}



    public void create (String profileName, String profileDescription, List<String> itemsForBackup) {
        try {
            // create a map
            HashMap<String, Object> map = new HashMap<>();
            map.put("name", profileName);
            map.put("description", profileDescription);
            map.put("items", itemsForBackup);

            // create the profile.
            File profileFile = new File (String.format("%s/profiles/%s.json",
                    new File("..").getCanonicalFile(),
                    profileName
            ));

            if (! profileFile.getParentFile().isDirectory()) {
                if (! profileFile.getParentFile().mkdirs()) {
                    Messages.showMessage(Messages.MESSAGE_ERR, "Cannot create the profiles dir.");
                    return;
                }
            }


            Writer writer = new FileWriter(profileFile.getAbsolutePath());

            // convert map to JSON File
            new Gson().toJson(map, writer);

            // close the writer
            writer.close();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void save() {

    }
}
