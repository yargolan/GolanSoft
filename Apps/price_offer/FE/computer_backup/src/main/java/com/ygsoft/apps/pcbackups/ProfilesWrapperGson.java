package com.ygsoft.apps.pcbackups;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;
import com.ygsoft.apps.pcbackups.enums.HardCoded;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



public class ProfilesWrapperGson {

    private Gson gson;

    private final File profileFile;
    private final Profile profile;
    private JsonArray jaAllProfiles = new JsonArray();

    private static final Logger logger = LogManager.getLogger(ProfilesWrapperGson.class);



    ProfilesWrapperGson(File _profilesFile, Profile _profile) {
        this.profile = _profile;
        this.profileFile = _profilesFile;
    }


    public JsonElement readFromFile() {

        gson = new GsonBuilder().setPrettyPrinting().create();

        JsonElement jeAllData = null;

        // Get the whole data from the file.
        try (Reader reader = new FileReader(profileFile.getAbsolutePath())) {
            jeAllData = gson.fromJson(reader, JsonElement.class).getAsJsonObject().get(HardCoded.PROFILES.getText());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return jeAllData;
    }



    public void addProfile() {

        JsonElement jeAllProfiles;

        if (profileFile.exists()) {
            logger.info(String.format("The profiles file [%s] exists.", profileFile.getName()));
            jeAllProfiles = readFromFile();
            jaAllProfiles = jeAllProfiles.getAsJsonArray();

            // Avoid duplications.
            if (isAlreadyExists(profile)) {
                logger.warn(String.format("The profile [%s] already exists in the file", profile.getProfileName()));
                return;
            }


            // Add the current profile to the list.
            jaAllProfiles.add(jeAllProfiles);
        }
        else {
            logger.info(String.format("The profiles file [%s] does not exist.", profileFile.getName()));
            jeAllProfiles = null; //todo: check null
        }


        // Set the list into the file.
        gson = new Gson();

        JsonElement jeProfile = gson.toJsonTree(profile);

        JsonObject joProfile = new JsonObject();

        jaAllProfiles.add(jeProfile);

        joProfile.add(HardCoded.PROFILES.getText(), jaAllProfiles);

        try {
            writeToFile(profileFile, joProfile);
        }
        catch (IOException e) {
            Messages.exitWithError("Cannot write the profile file: " + e.getMessage(), true, e);
        }
    }



    private boolean isAlreadyExists(Profile profile) {

        for (JsonElement jeCurrentProfile : jaAllProfiles) {

            Profile currentProfile = toProfile(jeCurrentProfile.getAsJsonObject());

            if (currentProfile.compareTo(profile)) {
                return true;
            }
        }
        return false;
    }



    /**
     *
     * @param profileFile The file to write the profile into,
     * @param jsonString The JSON string to be written into the file.
     * @throws IOException In case an error occurs.
     */
    private void writeToFile(File profileFile, JsonObject jsonString) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(profileFile.getAbsolutePath()));
        writer.write(String.valueOf(jsonString));
        writer.close();
    }


    /**
     *
     * @param joProfile A JSON object represents a profile.
     * @return A Profile object, based on the Json object provided.
     */
    private Profile toProfile(JsonObject joProfile) {

        List<String> itemsList = new ArrayList<>();

        JsonArray jaItems = joProfile.get("items").getAsJsonArray();

        for (int i = 0; i < jaItems.size(); i++) {
            itemsList.add(jaItems.get(i).getAsString());
        }

        Profile profile;

        profile = new Profile(
                joProfile.get("profile_name").getAsString(),
                joProfile.get("host_name").getAsString(),
                joProfile.get("target_dir").getAsString(),
                itemsList
        );

        return profile;
    }
}









