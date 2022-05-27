package com.ygsoft.apps.maintenance;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.ygsoft.apps.Messages;



public class InitialData {

    private Garage[] garagesList;



    InitialData() {

        File dbGarages = new File(HcFiles.FILE_GARAGES.getText());

        if (dbGarages.exists()) {

            try {
                Reader reader = Files.newBufferedReader(Paths.get(dbGarages.getAbsolutePath()));

                Gson gson = new Gson();
                garagesList = gson.fromJson(reader, Garage[].class);
            }
            catch (IOException e) {
                Messages.showMessage(Messages.MESSAGE_INF, e.getMessage());
            }
        }
    }



    List<String> getGarageNames() {

        List<String> list = new ArrayList<>();

        if (garagesList != null && garagesList.length > 0) {
            for (Garage g : garagesList) {
                list.add(g.getName());
            }
        }

        return list;
    }



    String getLocation(String garageName) {

        String location = "N/A";

        for (Garage g : garagesList) {
            if(g.getName().equals(garageName)) {
                location = g.getLocation();
                break;
            }
        }

        return location;
    }
}
