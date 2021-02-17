package com.ygsoft.apps.pcbackups;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.ygsoft.apps.backup.HardCoded;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



public class MixedProfilesWrapper {

    private final File    profileFile;
    private final Profile zzProfile;

    private final List<String> profileNames = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger(MixedProfilesWrapper.class);



    /**
     * @param _profilesFile The file which holds the user's zzProfile(s).
     * @param _profile The current zzProfile we are dealing with.
     */
    MixedProfilesWrapper(File _profilesFile, Profile _profile) {
        this.zzProfile     = _profile;
        this.profileFile = _profilesFile;
    }



    JSONObject readFromFile() throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        FileReader fileReader = new FileReader(profileFile.getAbsolutePath());

        Object o = parser.parse(fileReader);

        return (JSONObject) o;
    }



    public void addToFile() {

        List<Profile> list = new ArrayList<>();

        if (profileFile.exists()) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try (Reader reader = new FileReader(profileFile.getAbsolutePath())) {

                JsonElement json = gson.fromJson(reader, JsonElement.class);

                // Get the whole data from the file.
                com.google.gson.JsonObject joAllData = json.getAsJsonObject();

                // Get the main element.
                JsonElement jeProfiles = joAllData.get("profiles");

                com.google.gson.JsonArray jaAllProfiles = jeProfiles.getAsJsonArray();

                for (int i = 0; i < jaAllProfiles.size(); i++) {

                    JsonElement jeCurrentProfile = jaAllProfiles.get(i);

                    String profileName                = jeCurrentProfile.getAsJsonObject().get("name").getAsString();
                    String profileHostname            = jeCurrentProfile.getAsJsonObject().get("hostname").getAsString();
                    String profileTargetFolder        = jeCurrentProfile.getAsJsonObject().get("target_folder").getAsString();
                    com.google.gson.JsonArray jaItems = jeCurrentProfile.getAsJsonObject().get("items").getAsJsonArray();

                    List<String> itemsList = new ArrayList<>();
                    for (int j = 0; j < jaItems.size(); j++) {
                        String currentItem = jaItems.get(j).getAsString();
                        itemsList.add(currentItem);
                    }
                    Profile zzProfile = new Profile(profileName, profileHostname, profileTargetFolder, itemsList);
                    if (list.contains(zzProfile)) {
                        logger.info(String.format("The zzProfile [%s] already exist in the list", zzProfile.getProfileName()));
                    }
                    else {
                        list.add(zzProfile);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {

            logger.info("The profiles file does not exist yet.");

            Gson gson = new Gson();

            JsonElement jeProfile = gson.toJsonTree(zzProfile);

            com.google.gson.JsonObject joProfile = new com.google.gson.JsonObject();

            com.google.gson.JsonArray jaProfiles = new com.google.gson.JsonArray();

            jaProfiles.add(jeProfile);

            joProfile.add(HardCoded.PROFILES.getText(), jaProfiles);

            try {
                writeToFile(profileFile, joProfile);
            }
            catch (IOException e) {
                Messages.exitWithError("Cannot write the zzProfile file: " + e.getMessage(), true, e);
            }
        }
    }



//    public void addToFile2() throws IOException {
//
//        // Json object for all the profiles.
//        JsonObject allData = new JsonObject();
//
//
//        // Json array for all the profiles.
//        JsonArray jaProfiles = new JsonArray();
//
//
//        if (profileFile.exists()) {
//
//            logger.info("The profiles file exists.");
//
//            JSONObject joAllData;
//            JSONArray jaAllProfiles = null;
//
//
//            // Read the profiles file.
//            try {
//                joAllData = readFromFile();
//                jaAllProfiles = (JSONArray) joAllData.get("profiles");
//            }
//            catch (IOException | ParseException e) {
//                Messages.exitWithError("Cannot read the profiles file:\n" + e.getMessage(), e);
//            }
//
//            if (jaAllProfiles == null) {
//                throw new IOException("Cannot read the profiles file.");
//            }
//
//            for (Object oCurrentProfile : jaAllProfiles) {
//
//                JSONObject joCurrentProfile = (JSONObject) oCurrentProfile;
//
//                // Read the current zzProfile
//                String name         = (String) joCurrentProfile.get("name");
//                String hostname     = (String) joCurrentProfile.get("hostname");
//                String targetFolder = (String) joCurrentProfile.get("target_folder");
//                JSONArray jaItems   = (JSONArray) joCurrentProfile.get("items");
//
//                if (profileNames.contains(name)) {
//                    logger.info(String.format("The zzProfile [%s] already exists.", name));
//                }
//                else {
//
//                    // Generate a 'Simple JSON' object.
//                    JsonObject joProfile = new JsonObject();
//
//
//                    // Enter the data from current profiles.
//                    joProfile.put("name", name);
//                    joProfile.put("items", jaItems);
//                    joProfile.put("hostname", hostname);
//                    joProfile.put("target_folder", targetFolder);
//
//
//                    // Add the currently read zzProfile to the list.
//                    jaProfiles.add(joProfile);
//                }
//
//                allData.put("profiles", jaProfiles);
//
//                profileNames.add(zzProfile.getProfileName());
//
//                // Generate a 'Simple JSON' object.
//                JsonObject joProfile = new JsonObject();
//
//
//                // Enter the data from current profiles.
//                joProfile.put("name", zzProfile.getProfileName());
//                joProfile.put("items", zzProfile.getItems());
//                joProfile.put("hostname", zzProfile.getHostName());
//                joProfile.put("target_folder", zzProfile.getTargetDir());
//
//
//                // Add the currently read zzProfile to the list.
//                jaProfiles.add(joProfile);
//
//
//                // Write the profiles to the file.
//                allData.put("profiles", jaProfiles);
//            }
//        }
//        else {
//            logger.info("The profiles file does not exist yet.");
//        }
//    }









//    public void addToFile1() throws IOException {
//
//        // Json object for all the profiles.
//        JsonObject allData = new JsonObject();
//
//
//        if (profileFile.exists()) {
//
//            logger.info("The profiles file exists.");
//
//
//            JSONObject joAllData;
//            JSONArray jaAllProfiles = null;
//            JsonArray jaProfiles = new JsonArray();
//
//
//            // Read the profiles file.
//            try {
//                joAllData = readFromFile();
//                jaAllProfiles = (JSONArray) joAllData.get("profiles");
//            }
//            catch (IOException | ParseException e) {
//                Messages.exitWithError("Cannot read the profiles file:\n" + e.getMessage(), e);
//            }
//
//            if (jaAllProfiles == null) {
//                throw new IOException("Cannot read the profiles file.");
//            }
//
//            for (Object oCurrentProfile : jaAllProfiles) {
//
//                JSONObject joCurrentProfile = (JSONObject) oCurrentProfile;
//
//                // Read the current zzProfile
//                String name = (String) joCurrentProfile.get("name");
//                String hostname = (String) joCurrentProfile.get("hostname");
//                String targetFolder = (String) joCurrentProfile.get("target_folder");
//                JSONArray jaItems = (JSONArray) joCurrentProfile.get("items");
//
//                if (profileNames.contains(name)) {
//                    logger.info(String.format("The zzProfile [%s] already exists.", name));
//                }
//                else {
//
//                    // Generate a 'Simple JSON' object.
//                    JsonObject joProfile = new JsonObject();
//
//
//                    // Enter the data from current profiles.
//                    joProfile.put("name", name);
//                    joProfile.put("items", jaItems);
//                    joProfile.put("hostname", hostname);
//                    joProfile.put("target_folder", targetFolder);
//
//
//                    // Add the currently read zzProfile to the list.
//                    jaProfiles.add(joProfile);
//                }
//
//                allData.put("profiles", jaProfiles);
//
//                profileNames.add(zzProfile.getProfileName());
//
//                // Generate a 'Simple JSON' object.
//                JsonObject joProfile = new JsonObject();
//
//
//                // Enter the data from current profiles.
//                joProfile.put("name", zzProfile.getProfileName());
//                joProfile.put("items", zzProfile.getItems());
//                joProfile.put("hostname", zzProfile.getHostName());
//                joProfile.put("target_folder", zzProfile.getTargetDir());
//
//
//                // Add the currently read zzProfile to the list.
//                jaProfiles.add(joProfile);
//
//
//                // Write the profiles to the file.
//                allData.put("profiles", jaProfiles);
//                BufferedWriter writer;
//                try {
//                    writer = Files.newBufferedWriter(Paths.get(this.profileFile.getAbsolutePath()));
//                    Jsoner.serialize(allData, writer);
//                    writer.close();
//                }
//                catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        else {
//
//            logger.info("The profiles file does not exist yet.");
//
//            try {
//
//                JsonArray jaProfiles = new JsonArray();
//
//                // Generate a 'Simple JSON' object for a current zzProfile.
//                JsonObject joProfile = new JsonObject();
//
//
//                // Enter the data from current profiles.
//                joProfile.put("name",          zzProfile.getProfileName());
//                joProfile.put("items",         zzProfile.getItems());
//                joProfile.put("hostname",      zzProfile.getHostName());
//                joProfile.put("target_folder", zzProfile.getTargetDir());
//
//
//                // Add the currently read zzProfile to the list.
//                jaProfiles.add(joProfile);
//
//
//                // Write the profiles to the file.
//                allData.put("profiles", jaProfiles);
//                BufferedWriter writer;
//
//
//                writer = Files.newBufferedWriter(Paths.get(this.profileFile.getAbsolutePath()));
//                Jsoner.serialize(allData, writer);
//                writer.close();
//            }
//            catch (IOException e) {
//                throw new IOException("Cannot write the zzProfile's file.");
//            }
//        }
//    }


    /**
     *
     * @param allData A JsonObject holds all the profiles.
     * @param profileFile The file used to write the zzProfile(s) into.
     * @throws IOException In case of an error.
//     */
//    private void writeToFile(File profileFile, JsonObject allData) throws IOException {
//        BufferedWriter writer = Files.newBufferedWriter(Paths.get(profileFile.getAbsolutePath()));
//        Jsoner.serialize(allData, writer);
//        writer.close();
//    }


    /**
     *
     * @param profileFile The file to write the zzProfile into,
     * @param jsonString The JSON string to be written into the file.
     * @throws IOException In case an error occurs.
     */
    private void writeToFile(File profileFile, com.google.gson.JsonObject jsonString) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(profileFile.getAbsolutePath()));
        writer.write(String.valueOf(jsonString));
        writer.close();
    }
}









