package com.ygsoft.apps.maintenance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.*;



public class GarageWrapper {

    public GarageWrapper(){}



    public void addToList(Garage garage) throws GarageInternalException {

        JsonArray currentGarages = getCurrentGarages();

        Gson gson = new GsonBuilder().create();
        JsonElement jeCurrentGarage = gson.toJsonTree(garage, Garage.class);

        currentGarages.add(jeCurrentGarage);

        // create a writer
        File dbGarages = new File(HcFiles.FILE_GARAGES.getText());
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(dbGarages.getAbsolutePath()));

            // write JSON to file
            writer.write(gson.toJson(currentGarages));

            //close the writer
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    private JsonArray getCurrentGarages() {

        // create a reader
        String dbFile = HcFiles.FILE_GARAGES.getText();
        Reader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(dbFile));
        }
        catch (IOException e) {
            return new JsonArray();
        }

        //create JsonObject instance
        return JsonParser.parseReader(reader).getAsJsonArray();
    }
}
