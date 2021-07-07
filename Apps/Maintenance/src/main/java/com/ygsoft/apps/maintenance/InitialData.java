package com.ygsoft.apps.maintenance;

import com.google.gson.Gson;
import com.ygsoft.apps.Messages;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InitialData {

    Gson gson = new Gson();

    InitialData(){}


    List<String> getGarageNames() {

        List<String> list = new ArrayList<>();

        File dbGarages = new File(HcFiles.FILE_GARAGES.getText());

        if (dbGarages.exists()) {

            try {
                Reader reader = Files.newBufferedReader(Paths.get(dbGarages.getAbsolutePath()));

                Garage[] garagesList = gson.fromJson(reader, Garage[].class);

                if (garagesList == null || garagesList.length == 0) {
                    return list;
                }

                for (Garage g : garagesList) {
                    list.add(g.getName());
                }
            }
            catch (IOException e) {
                Messages.showMessage(Messages.MESSAGE_INF, e.getMessage());
            }
        }

        return list;
    }



    String getLocation(String garageName) {

        HashMap<String, String> locations = new HashMap<>();
        locations.put("MAREE",  "KKK");
        locations.put("OTOPIA", "MMM");
        return locations.getOrDefault(garageName, "");
    }
}
