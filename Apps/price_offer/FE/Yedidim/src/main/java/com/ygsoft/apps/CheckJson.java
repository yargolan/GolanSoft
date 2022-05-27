package com.ygsoft.apps;

import com.google.gson.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;



public class CheckJson {



    public static void main(String[] args) {

        HashMap<Integer, String> vehicleVendors = new HashMap<>();
        HashMap<Integer, String> vehicleModels  = new HashMap<>();


        File dataFile = new File("/opt/ws/YG/GitHub/repositories/__NEW__/DB_apps/Yedidim/vehicles.json");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Reader reader = new FileReader(dataFile.getAbsolutePath())) {

            JsonElement json = gson.fromJson(reader, JsonElement.class);

            JsonObject joAllData = json.getAsJsonObject();

            JsonArray jaAllVehicles = joAllData.getAsJsonArray("vehicles");

            for (int i=0; i<jaAllVehicles.size(); i++) {

                JsonElement jeCurrentVehicle = jaAllVehicles.get(i);

                JsonObject joCurrentMake = jeCurrentVehicle.getAsJsonObject();

                // Get the current vendor's models
                JsonArray jaModels = joCurrentMake.getAsJsonArray("models");

                int    index  = joCurrentMake.get("make_index").getAsInt();
                String vendor = joCurrentMake.get("vendor_name").getAsString();

                for (int j=0; j<jaModels.size(); j++) {
                    JsonElement jeCurrentModel = jaModels.get(j);
                    JsonObject joCurrentModel  = jeCurrentModel.getAsJsonObject();

                    // Get the current model index
                    int    currentModelIndex = joCurrentModel.get("index").getAsInt();
                    String currentModelName  = joCurrentModel.get("name").getAsString();
                    vehicleModels.put(currentModelIndex, currentModelName);
                }

                vehicleVendors.put(index, vendor);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        for (int key : vehicleVendors.keySet()) {
            System.out.println("key = " + key);
            System.out.println("val = " + vehicleVendors.get(key));
            System.out.println();
        }

        System.out.println();
        System.out.println();

        for (int key1 : vehicleModels.keySet()) {
            System.out.println("key = " + key1);
            System.out.println("val = " + vehicleModels.get(key1));
            System.out.println();
        }
    }
}
