package com.ygsoft.apps;



import java.io.File;
import java.io.Reader;
import java.util.HashMap;
import com.google.gson.*;
import java.io.FileReader;
import java.io.IOException;



public class VehicleWrapper {

    private final File vehiclesFile = new File("vehicles.json");

    private final HashMap<Integer, String> vehicleVendors = new HashMap<>();
    private final HashMap<Integer, String> vehicleModels  = new HashMap<>();


    VehicleWrapper(){

    }



    public String getVehicleVendorById(int vehicleId) {
        int id = vehicleId / 10 * 10;
        return vehicleVendors.get(id);
    }



    public String getVehicleMakeById(int vehicleId) {
        return vehicleModels.get(vehicleId);
    }



    public String getVehicleNameById(int vehicleId) {
        String vendor = getVehicleVendorById(vehicleId);
        String make   = getVehicleMakeById(vehicleId);
        return String.format("%s %s", vendor, make);
    }



    public void parse() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Reader reader = new FileReader(vehiclesFile.getAbsolutePath())) {

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
    }
}
