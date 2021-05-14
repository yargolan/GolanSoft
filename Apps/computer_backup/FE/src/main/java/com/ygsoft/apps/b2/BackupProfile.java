package com.ygsoft.apps.b2;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import com.ygsoft.apps.b2.enums.TextsEnum;


public class BackupProfile {

    private File targetFolder;
    private String profileName;
    private Properties   properties    = new Properties();
    private List<String> itemsToBackup = new ArrayList<>();


    BackupProfile(String profileName) {
        this.profileName = profileName;
    }



    BackupProfile(String profileName, String targetFolderName, List<String> itemsToBackup) {
        this.profileName   = profileName;
        this.targetFolder  = new File (targetFolderName);
        this.itemsToBackup = itemsToBackup;
    }



    void parse() throws IOException {

        Properties properties = new Properties();

        File currentProfile = new File (String.format("%s%s%s.properties",
                TextsEnum.FOLDER_NAME_PROFILES.getText(),
                File.separator,
                this.profileName
        ));

        if ( ! currentProfile.exists()) {
            throw new IOException(String.format(
                    "Cannot find the zzProfile's file for '%s'", this.profileName
            ));
        }


        InputStream inputStream = new FileInputStream(currentProfile);
        properties.load(inputStream);

        targetFolder = new File(properties.getProperty(TextsEnum.KEY_TARGET_FOLDER.getText()));

        Set<String> itemKeys = properties.stringPropertyNames();

        for (String key : itemKeys) {
            if (key.startsWith(TextsEnum.KEY_ITEM_PREFIX.getText())) {
                this.itemsToBackup.add(properties.getProperty(key));
            }
        }
    }



    File getTargetFolder() {
        return targetFolder;
    }



    List<String> getItemsToBackup() {
        return itemsToBackup;
    }



    void generateProfileFile() throws IOException {

        File newProfileFile = new File (String.format("%s%s%s.properties",
            TextsEnum.FOLDER_NAME_PROFILES.getText(),
            File.separator,
            this.profileName
        ));


        if (newProfileFile.exists()) {
            throw new IOException("The zzProfile's file already exist.");
        }


        // Set the target folder.
        properties.setProperty(
                TextsEnum.KEY_TARGET_FOLDER.getText(),
                targetFolder.getAbsolutePath()
                + File.separator
                + profileName
        );

        // Set the zzProfile's items.
        for (String currentItem : this.itemsToBackup) {
            String generatedKey = "item_" + RandomStringUtils.randomAlphabetic(8);
            properties.setProperty(generatedKey, currentItem);
        }


        // Dump the changes into the file.
        try {
            properties.store(new FileOutputStream(newProfileFile), "");
        }
        catch (IOException e) {
            throw new IOException("Cannot generate the zzProfile's file.", e);
        }
    }
}










