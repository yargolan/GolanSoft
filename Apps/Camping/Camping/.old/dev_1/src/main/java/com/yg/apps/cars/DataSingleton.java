package com.yg.apps.cars;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.yg.apps.cars.data.Garage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DataSingleton {

//    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    // static variable single_instance of type Singleton
    private static DataSingleton single_instance = null;


    // Application data
    public final HashMap<String, String> data = new HashMap<>();


    // Garages
//    private final HashMap<String, Garage> garages = new HashMap<>();



    // private constructor restricted to this class itself
    private DataSingleton() {

        // Parse folders.
        parseFolders();


        // Parse files.
        parseFiles();

    }



    private void parseFolders() {

        File applicationDataDir = new File(".");
        File applicationRootDir = new File(".");


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
        }
        catch (IOException e) {
            if (AppHelper.is_under_dev()) {
                e.printStackTrace();
            }
            Messages.exitWithError("Cannot parse the application folders.", true);
        }

        data.put("data_folder",   applicationDataDir.getAbsolutePath());
        data.put("root_folder",   applicationRootDir.getAbsolutePath());
    }



    private void parseFiles() {

        // Garages
        File garagesDb = new File (String.format("%s%s%s",
                this.getParsedDataFolder().getAbsolutePath(),
                File.separatorChar,
                Hardcoded.DATA_FILE_GARAGES.getText()
        ));
        data.put("db_file_garages", garagesDb.getAbsolutePath());
    }



    // Create instance of the Singleton class.
    public static DataSingleton getInstance() {
        if (single_instance == null) {
            single_instance = new DataSingleton();
        }

        return single_instance;
    }



//    public File getParsedRootFolder() {
//        return new File(data.get("root_folder"));
//    }



    public File getParsedDataFolder() {
        return new File (data.get("data_folder"));
    }



    public File getParsedGaragesFile() {
        return new File (data.get("db_file_garages"));
    }



    public List<Garage> getGaragesList() {

        List<Garage> garagesList = new ArrayList<>();

        File garagesDb = this.getParsedGaragesFile();

        if (garagesDb.exists()) {

            Gson gson = new Gson();

            // Read the file.
            try {
                Reader reader = new FileReader(garagesDb);

                JsonElement jeAll = gson.fromJson(reader, JsonElement.class);

                JsonArray jaAllGarages = jeAll.getAsJsonArray();

                for (int i = 0; i < jaAllGarages.size(); i++) {
                    JsonObject joCurrentGarage = jaAllGarages.get(i).getAsJsonObject();
                    Garage g = new Garage(
                            joCurrentGarage.get("id").getAsInt(),
                            joCurrentGarage.get("name").getAsString(),
                            joCurrentGarage.get("address").getAsString(),
                            joCurrentGarage.get("contact").getAsString(),
                            joCurrentGarage.get("phone_number").getAsString()
                    );

                    garagesList.add(g);
                }
            }
            catch (IOException e) {
                if (AppHelper.is_under_dev()) {
                    e.printStackTrace();
                }
            }
        }

        return garagesList;
    }
}








        /*





        // Get the garages list.
        getGaragesList();
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
*/