package com.ygsoft.apps.b3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import com.google.gson.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.ygsoft.apps.b3.enums.HardCoded;




public class PcBackupsMain {

    private String userName;
    private File profilesDir = new File(".");
    private List<Profile> profilesList;
    private boolean isUnderDebug = false;


    private static final Logger logger = LogManager.getLogger(PcBackupsMain.class);



    public static void main(String[] args) {

        PcBackupsMain pcBackupsMain = new PcBackupsMain();
        try {
            pcBackupsMain.runApp();
        }
        catch (Exception e) {
            Messages.exitWithError(e.getMessage(), e);
        }
    }



    private void runApp() throws IOException {

        logger.info("Application started.");


        // Init the application.
        logger.debug("Init the application.");
        try {
            init_application();
        }
        catch (IOException e) {
            throw new IOException("Cannot init the app", e);
        }


        // Get the user name.
        logger.debug("Get the user name.");
        userName = getUserName();
        if (userName == null) {
            logger.info("Canceled by the user.");
            return;
        }
        logger.info(String.format("User logged in as '%s'", userName));


        // Read the profiles list for the user.
        logger.debug(" Read the profiles list for the user.");
        profilesList = getProfilesList();


        if (profilesList.size() == 0) {
            ProfilesGenerator profilesGenerator = new ProfilesGenerator();
            profilesGenerator.generate();
            profilesList = getProfilesList();
        }


        // Show UI
        show_ui();
    }



    private List<Profile> getProfilesList() {

        List<Profile> list = new ArrayList<>();

        File userFile = new File (String.format("%s%s%s.json", profilesDir, File.separatorChar, userName));
        if (userFile.exists()) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try (Reader reader = new FileReader(userFile.getAbsolutePath())) {

                JsonElement json = gson.fromJson(reader, JsonElement.class);

                // Get the whole data from the file.
                JsonObject joAllData = json.getAsJsonObject();

                // Get the main element.
                JsonElement jeProfiles = joAllData.get("profiles");

                JsonArray jaAllProfiles = jeProfiles.getAsJsonArray();

                for (int i = 0; i < jaAllProfiles.size(); i++) {

                    JsonElement jeCurrentProfile = jaAllProfiles.get(i);

                    String profileName         = jeCurrentProfile.getAsJsonObject().get("name").getAsString();
                    String profileHostname     = jeCurrentProfile.getAsJsonObject().get("hostname").getAsString();
                    String profileTargetFolder = jeCurrentProfile.getAsJsonObject().get("target_folder").getAsString();
                    JsonArray jaItems          = jeCurrentProfile.getAsJsonObject().get("items").getAsJsonArray();

                    List<String> itemsList = new ArrayList<>();
                    for (int j = 0; j < jaItems.size(); j++) {
                        String currentItem = jaItems.get(j).getAsString();
                        itemsList.add(currentItem);
                    }
                    Profile zzProfile = new Profile(profileName, profileHostname, profileTargetFolder, itemsList);
                    list.add(zzProfile);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return list;
    }



    private void init_application() throws IOException {

        // +--------------------------------------------------------
        // |  Set the 'look-and-feel' for the UI.
        // +--------------------------------------------------------
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            logger.error("Cannot set the look-and-feel");
        }



        // +--------------------------------------------------------
        // |  Verify that the profiles folder exists.
        // +--------------------------------------------------------
        logger.debug("Verify that the profiles folder exists.");
        profilesDir = new File(HardCoded.DIR_NAME_PROFILES.getText());
        if ( ! profilesDir.exists()) {

            if ( ! profilesDir.mkdirs()) {
                throw new IOException("Cannot create the profiles dir.");
            }
        }
        logger.debug("ok.");
        logger.debug("");


        // +--------------------------------------------------------
        // |  Verify if the code is running under debug mode.
        // +--------------------------------------------------------
        String value = System.getenv("DEBUG");
        isUnderDebug = value != null && value.equals("true");
    }



    private String getUserName() {
        if (isUnderDebug) {
            return "yg";
        }
        else {

            // Get the user name
            String username = System.getProperty("user.name");

            return JOptionPane.showInputDialog(
                    null,
                    "Enter your name: ",
                    "Log-in",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    null,
                    username
            ).toString();
        }
    }


    private void show_ui() {


    }
}















