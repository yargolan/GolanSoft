package com.yg.cars;

import com.google.gson.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;



public class DataSingleton {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();



    // static variable single_instance of type Singleton
    private static DataSingleton single_instance = null;



    // Application data
    public HashMap<String, String> data = new HashMap<>();

    // Garages
    private HashMap<String, Garage> garages = new HashMap<>();



    // private constructor restricted to this class itself
    private DataSingleton() {

        File applicationDataDir   = new File(".");
        File applicationRootDir   = new File(".");
        File applicationConfigDir = new File(".");


        try {
            applicationRootDir = applicationRootDir
                    .getAbsoluteFile()
                    .getParentFile()
                    .getCanonicalFile()
            ;

            applicationDataDir = new File(String.format("%s%s%s",
                    applicationRootDir.getAbsolutePath(),
                    File.separatorChar,
                    Hardcoded.APP_DATA_DIR.getText()
            )).getCanonicalFile();

            applicationConfigDir = new File(String.format("%s%s%s",
                    applicationRootDir.getAbsolutePath(),
                    File.separatorChar,
                    Hardcoded.APP_CONFIG_DIR.getText()
            )).getCanonicalFile();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        data.put("data_folder",   applicationDataDir.getAbsolutePath());
        data.put("root_folder",   applicationRootDir.getAbsolutePath());
        data.put("config_folder", applicationConfigDir.getAbsolutePath());


        // Parse the config file
        parseConfigFile();


        // Get the garages list.
        getGaragesList();
    }



    public String getRootFolder() {
        return data.get("root_folder");
    }



    public String getDataFolder() {
        return data.get("data_folder");
    }


    
    public String getConfigFolder() {
        return data.get("config_folder");
    }


    
    private void getGaragesList() {

        File garagesDbFile = new File (String.format("%s%s%s",
                this.getDataFolder(),
                File.separatorChar,
                data.get("db_name_garages")
        ));

        try {
            Reader reader = new FileReader(garagesDbFile);

            JsonElement jeAll = gson.fromJson(reader, JsonElement.class);

            JsonArray jaAllData = jeAll.getAsJsonArray();

            for (int i = 0; i < jaAllData.size(); i++) {

                JsonElement jeCurrentGarage = jaAllData.get(i);

                String name = jeCurrentGarage.getAsJsonObject().get("name").getAsString();

                Garage g = new Garage(
                        name,
                        jeCurrentGarage.getAsJsonObject().get("address").getAsString(),
                        jeCurrentGarage.getAsJsonObject().get("contact").getAsString(),
                        jeCurrentGarage.getAsJsonObject().get("phone_number").getAsString()
                );

                garages.put(name, g);
            }
        }
        catch (IOException e) {
            if (AppHelper.is_under_dev()) {
                e.printStackTrace();
            }
        }
    }



    private void parseConfigFile() {
        String configFileName = AppHelper.is_under_dev() ?
            Hardcoded.CONFIG_FILE_NAME_DEBUG.getText() :
            Hardcoded.CONFIG_FILE_NAME_PROD.getText()
        ;

        File configFile = new File (String.format("%s%s%s",
                this.getConfigFolder(),
                File.separatorChar,
                configFileName
        ));


        try {
            Reader reader = new FileReader(configFile);

            JsonElement jeAll = gson.fromJson(reader, JsonElement.class);

            JsonObject joAllData = jeAll.getAsJsonObject();

            data.put("db_name_trips",    joAllData.get("db_name_trips").getAsString());
            data.put("db_name_drivers",  joAllData.get("db_name_drivers").getAsString());
            data.put("db_name_vehicles", joAllData.get("db_name_vehicles").getAsString());
        }
        catch (IOException e) {
            if (AppHelper.is_under_dev()) {
                e.printStackTrace();
            }
        }
    }

    
    
    // static method to create instance of Singleton class
    public static DataSingleton getInstance() {
        if (single_instance == null) {
            single_instance = new DataSingleton();
        }

        return single_instance;
    }



    public void _dump() {
        data.forEach((key, val)->System.out.println(key + " : " + val));
    }



    public File getGaragesFile() {
        return new File(String.format("%s%s%s",
                this.getDataFolder(),
                File.separatorChar,
                this.data.get("db_name_garages")
        ));
    }
}
