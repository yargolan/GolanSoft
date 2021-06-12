package com.golansoft.apps.computerbackup;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



public class Profiles {

    DataSingleton dataSingleton = DataSingleton.getInstance();
    private static final Logger logger = LogManager.getLogger(Profiles.class);


    public Profiles() {}

    List<String> getProfiles(String username, String hostname) {

        List<String> list = new ArrayList<>();

        if (username == null || username.isEmpty()) {
            return list;
        }

        if (hostname == null || hostname.isEmpty()) {
            return list;
        }

        File profilesDir = dataSingleton.getProfilesRootDir();
        logger.info("Searching for profiles at: " + profilesDir);


        File[] currentProfiles = profilesDir.listFiles();
        if (currentProfiles == null || currentProfiles.length == 0) {
            logger.info("No profiles were found.");
            return list;
        }
        for (File f : currentProfiles) {
            if (f.getName().toLowerCase().equals(username.toLowerCase() + ".json")) {

                // Parse the profiles file and seek for the given host name.
                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                // Get the whole data from the file.
                try (Reader reader = new FileReader(f.getAbsolutePath())) {
                    JsonArray jaAllProfiles = gson.fromJson(reader, JsonElement.class).getAsJsonArray();
                    for (int i = 0; i < jaAllProfiles.size(); i++) {
                        JsonElement jeCurrentProfile = jaAllProfiles.get(i);
                        String currentHost        = jeCurrentProfile.getAsJsonObject().get("host").getAsString();
                        String currentProfileName = jeCurrentProfile.getAsJsonObject().get("name").getAsString();
                        if (currentHost.equals(hostname)) {
                            list.add(currentProfileName);
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }
}
