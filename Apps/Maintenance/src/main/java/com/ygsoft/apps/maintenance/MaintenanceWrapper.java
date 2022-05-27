package com.ygsoft.apps.maintenance;

import com.google.gson.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MaintenanceWrapper {

    private final Maintenance maintenance;
    private final File dbMaint = new File(HcFiles.FILE_MAINTENANCES.getText());

    MaintenanceWrapper(Maintenance m){
        this.maintenance = m;
    }


    void add() throws GarageInternalException {

        JsonArray currentMaintenances = getCurrentMaintenances();

        Gson gson = new GsonBuilder().create();

        JsonElement jeCurrentMaintenance = gson.toJsonTree(this.maintenance, Maintenance.class);

        currentMaintenances.add(jeCurrentMaintenance);

        // create a writer
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(dbMaint.getAbsolutePath()));

            // write JSON to file
            writer.write(gson.toJson(currentMaintenances));

            //close the writer
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    private JsonArray getCurrentMaintenances() {

        // create a reader
        Reader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(dbMaint.getAbsolutePath()));
        }
        catch (IOException e) {
            return new JsonArray();
        }

        //create JsonObject instance
        return JsonParser.parseReader(reader).getAsJsonArray();
    }
}
