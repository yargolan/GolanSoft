package com.golansoft.apps.computerbackup;

import java.io.File;
import java.io.FileFilter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;



public class Profiles {

    public Profiles() {}



    File[] getProfilesList() {

        DataSingleton dataSingleton = DataSingleton.getInstance();

        FileFilter fileFilter = file -> !file.isDirectory() &&
                file.getName().endsWith(".json");

        return dataSingleton.getProfilesRootDir().listFiles(fileFilter);
    }


    int calculateFilesAmount(String profileName) {

        int amount = 0;

        DataSingleton dataSingleton = DataSingleton.getInstance();

        File profile = new File(
                dataSingleton.getProfilesRootDir().getAbsolutePath()
                + File.separatorChar
                + profileName
                + ".json"
        );

        if (profile.exists()) {

            Gson gson = new Gson();
            try {
                Reader reader = Files.newBufferedReader(Paths.get(profile.getAbsolutePath()));
                Profile readProfile = gson.fromJson(reader, Profile.class);
                String[] items = readProfile.getItemsToBackup();
                if (items == null) {
                    Messages.exitWithError(HardCoded.M_ERR_INTERNAL_ERROR.getText());
                }
                else {
                    for (String item : items) {
                        int currentAmount = calculateAmount(new File(item));
                        amount += currentAmount;
                    }
                }
            }
            catch (Exception e) {
                amount = 1;
            }
        }
        else {
            amount = 2;
        }

        return amount;
    }


    private static int calculateAmount(File item) {

        int amount = 0;

        File[] list = item.listFiles();
        if (list == null) {
            amount = 1;
        }
        else {
            for (File f : list) {
                if (f.isFile()) {
                    amount++;
                }
                if (f.isDirectory()) {
                    amount += calculateAmount(f);
                }
            }
        }
        return amount;
    }
}

