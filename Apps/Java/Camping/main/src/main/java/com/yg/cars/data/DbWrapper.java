package com.yg.cars.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.yg.cars.AppHelper;
import com.yg.cars.DataSingleton;
import com.yg.cars.Garage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


public class DbWrapper {


    private DataSingleton dataSingleton = DataSingleton.getInstance();



    public void addGarage(Garage garage) {

        File garagesDatabase = dataSingleton.getGaragesFile();

        if (garagesDatabase.exists()) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try {
                Reader reader = new FileReader(garagesDatabase);

                JsonElement jeAll = gson.fromJson(reader, JsonElement.class);

                JsonArray jaAllData = jeAll.getAsJsonArray();





            }
            catch (IOException e) {
                if (AppHelper.is_under_dev()) {
                    e.printStackTrace();
                }
            }
        }
    }
}





