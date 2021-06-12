package com.golansoft.apps.computerbackup;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;



public class ProfileParser {

    private File _profileFile, _targetFolder;
    private String _profileName, _profileHost, _profileDesc;
    private List<File> _items;



    public File get_profileFile() {
        return _profileFile;
    }


    public File get_targetFolder() {
        return _targetFolder;
    }


    public String get_profileName() {
        return _profileName;
    }


    public String get_profileHost() {
        return _profileHost;
    }


    public String get_profileDesc() {
        return _profileDesc;
    }


    public List<File> get_items() {
        return _items;
    }


    public ProfileParser(File profileFile, String profileName) {
        this._profileName = profileName;
        this._profileFile = profileFile;
    }


    public void parse() {

        if (_profileFile.exists()) {

            // Parse the profiles file and seek for the given host name.
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Get the whole data from the file.
            boolean profileFound = false;

            try (Reader reader = new FileReader(this._profileFile.getAbsolutePath())) {

                JsonArray jaAllProfiles = gson.fromJson(reader, JsonElement.class).getAsJsonArray();

                for (int i = 0; i < jaAllProfiles.size(); i++) {
                    if (profileFound) {
                        break;
                    }
                    JsonElement jeCurrentProfile = jaAllProfiles.get(i);
                    String currentHost         = jeCurrentProfile.getAsJsonObject().get("host").getAsString();
                    String currentProfileName  = jeCurrentProfile.getAsJsonObject().get("name").getAsString();
                    String currentProfileDesc  = jeCurrentProfile.getAsJsonObject().get("description").getAsString();
                    String currentTargetFolder = jeCurrentProfile.getAsJsonObject().get("target").getAsString();
                    JsonArray jaItems          = jeCurrentProfile.getAsJsonObject().get("items").getAsJsonArray();

                    List<File> list = new ArrayList<>();

                    if (jaItems != null && jaItems.size() > 0) {
                        for (int j = 0; j < jaItems.size(); j++) {
                            list.add(new File(jaItems.get(j).getAsString()));
                        }
                    }

                    if (currentHost.equals(this._profileHost) && currentProfileName.equals(this._profileName)) {
                        profileFound = true;
                        this._items        = list;
                        this._profileDesc  = currentProfileDesc;
                        this._targetFolder = new File(currentTargetFolder);
                    }
                }
            }
            catch (IOException e) {
                this._items        = null;
                this._profileDesc  = "N/A";
                this._targetFolder = null;
            }
        }
        else {
            this._items        = null;
            this._profileDesc  = "N/A";
            this._targetFolder = null;
        }
    }
}

