package com.ygsoft.apps.camping;

import java.io.*;
import java.util.HashMap;

import com.google.gson.*;



class VehiclesData {

    private HashMap<String, Vehicle> data = new HashMap<>();

    VehiclesData(){}



    public Vehicle getVehicleByIndex (int index) {
        return data.getOrDefault(String.valueOf(index), null);
    }



    void parseData(File dataFile) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Reader reader = new FileReader(dataFile.getAbsolutePath())) {

            // Convert JSON to JsonElement, and later to String
            JsonElement json = gson.fromJson(reader, JsonElement.class);

            JsonObject joAllData = json.getAsJsonObject();
            JsonArray jaAllVehicles = joAllData.getAsJsonArray("vehicles");
            for (int i = 0; i < jaAllVehicles.size(); i++) {

                JsonElement jeCurrentVehicle = jaAllVehicles.get(i);

                JsonObject joCurrentMake = jeCurrentVehicle.getAsJsonObject();

                // Get the current vehicle's make.
                String make = joCurrentMake.get("make").getAsString();


                // get the current make's models.
                JsonArray ja = (JsonArray) joCurrentMake.get("models");

                for (int j = 0; j < ja.size(); j++) {

                    JsonObject joCurrentModel = (JsonObject) ja.get(j);

                    int    index = Integer.parseInt(joCurrentModel.get("index").getAsString());
                    String name  = joCurrentModel.get("name").getAsString();

                    Vehicle currentVehicle = new Vehicle(make, name, index);

                    // Add the current vehicle to the map.
                    data.put(String.valueOf(index), currentVehicle);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
