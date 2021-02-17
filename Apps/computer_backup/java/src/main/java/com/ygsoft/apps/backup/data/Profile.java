package com.ygsoft.apps.backup.data;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



public class Profile {

    private String name;
    private File profileFullPath;
    private String hostName, userName, targetDir;
    private List<String> items = new ArrayList<>();
    DataSingleton ds = DataSingleton.getInstance();


    private static final Logger logger = LogManager.getLogger(Profile.class);



    public Profile(String profileName) {

        if (profileName != null) {

            // Init the name of the profile.
            initName(profileName);

            // Parse the profile's data
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try (Reader reader = new FileReader(profileFullPath)) {

                // Convert the file into jsonElement, and later to String
                JsonElement json = gson.fromJson(reader, JsonElement.class);

                if (json == null) {
                    items = new ArrayList<>();
                    return;
                }

                // Convert the file into a JSON object.
                JsonObject joAll = json.getAsJsonObject();

                // get the items for backup.
                JsonArray jaItems = joAll.get(Data.ITEMS.getText()).getAsJsonArray();
                for (int i = 0; i < jaItems.size(); i++) {
                    String s = jaItems.get(i).getAsString();
                    items.add(s);
                }


                // Get the host name.
                this.hostName = joAll.get(Data.HOST_NAME.getText()).getAsString();


                // get the user name.
                this.userName = joAll.get(Data.USER_NAME.getText()).getAsString();


                // get the target directory.
                this.targetDir = joAll.get(Data.TARGET_DIR.getText()).getAsString();
            }
            catch (IOException e) {
                this.hostName = "Unknown";
                this.userName = "Unknown";
                items = new ArrayList<>();
            }
        }
    }


    public Profile(String profileName, String targetFolder, List<String> itemsToBackup) {
        this.name = profileName;
        this.items = itemsToBackup;
        this.targetDir = targetFolder;

        this.hostName = ds.getDataHostName();
        this.userName = ds.getDataUserName();

        // Init the name of the profile.
        initName(profileName);
    }



    private void initName(String profileName) {

        this.name = (profileName.endsWith(".json")) ?
                profileName : profileName + ".json"
        ;

        File profilesRootDir = ds.getProfilesRootDir();
        profileFullPath = new File(String.format(
                "%s%s%s",
                profilesRootDir,
                File.separatorChar,
                this.name
        ));
    }


    public String getName() {
        return name;
    }



    public String getHostName() {
        return hostName;
    }



    public String getTargetDir() {
        return targetDir;
    }



    public String getUserName() {
        return userName;
    }



    public List<String> getItems() {
        return items;
    }



    public List<String> runBackup() {

        List<String> output = new ArrayList<>();
        List<String> items = getItems();

        logger.info(String.format("Running backup for profile '%s' ... ", this.getName()));

        for (String item : items) {

            logger.info("");
            logger.info(item);

            File target = new File(String.format("%s%s%s",
                    getTargetDir(),
                    File.separator,
                    item
            ));

            try {
                copyItem(new File(item), target);
                logger.info("ok.");
                logger.info("");
            }
            catch (IOException e) {
                String message =  String.format("Cannot copy the item '%s': %s", item, e.getMessage());
                logger.info(message);
                logger.info("");
                output.add(message);
            }
        }

        return output;
    }



    private void copyItem(File source, File target) throws IOException {

        if (source.isFile()) {
            FileUtils.copyFile(source, target);
        }
        else if (source.isFile()) {
            FileUtils.copyDirectory(source, target);
        }
        else {
            String message = String.format("The item '%s' cannot be found.", source);
            logger.error(message);
            throw new IOException(message);
        }
        logger.info("ok.");
        logger.info("");
    }


    public void save() {

        ProfileObject profileObject = new ProfileObject(
                this.name,
                this.targetDir,
                this.hostName,
                this.items
        );

        try {

            // create Gson instance
            Gson gson = new Gson();

            // create a writer
            Path p = Paths.get(profileFullPath.getAbsolutePath());
            Writer writer = Files.newBufferedWriter(p);

            // convert user object to JSON file
            gson.toJson(profileObject, writer);

            // close writer
            writer.close();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public List<String> getProfiles(String hostName, String userName) {

        DataSingleton ds = DataSingleton.getInstance();

        File here = ds.getProfilesRootDir();


        // Get the list of profiles from the folder.
        File[] files = here.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));


        // Go over the list and determine which are relevant.
        List<String> list = new ArrayList<>();

        if (files != null && files.length > 0) {

            for (File f : files) {

                Profile p = new Profile(f.getName());

                if (p.getUserName().equals(userName) && p.getHostName().equals(hostName)) {
                    list.add(f.getName().replace(".json", ""));
                }
            }
        }

        return list;
    }
}












