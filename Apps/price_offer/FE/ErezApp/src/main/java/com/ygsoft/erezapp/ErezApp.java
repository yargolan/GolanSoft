package com.ygsoft.erezapp;


import com.google.gson.*;
import java.io.*;
import java.util.HashMap;
import java.util.Set;



public class ErezApp {

    HashMap<String, String> config = new HashMap<>();





    private void initApp() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            Reader reader = new FileReader("config/app_config.json");

            JsonElement jeAll = gson.fromJson(reader, JsonElement.class);

            JsonObject joAll = jeAll.getAsJsonObject();

            Set<String> keys = joAll.keySet();

            for (String key : keys) {
                config.put(key, joAll.get(key).getAsString());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {

        ErezApp erezApp = new ErezApp();

        // init
        erezApp.initApp();


        // Set the main UI window
        AppUi appUi = new AppUi();
        appUi.show();
    }
}
