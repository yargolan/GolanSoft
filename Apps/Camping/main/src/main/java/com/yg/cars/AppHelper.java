package com.yg.cars;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.yg.cars.InternalErrorException;
import com.yg.cars.Hardcoded;

import java.io.*;


public class AppHelper {


    private final DataSingleton dataSingleton = DataSingleton.getInstance();




    public AppHelper() {
    }



    public static boolean is_under_dev() {
        String value = System.getenv("UNDER_DEV");
        return (value != null && value.toLowerCase().equals("true"));
    }






    public File getRootDir() throws InternalErrorException {

        File rootFolder;

        try {
            rootFolder = new File(".").getAbsoluteFile().getCanonicalFile().getParentFile();
        }
        catch (IOException e) {
            throw new InternalErrorException("Cannot determine the root folder of the application");
        }

        return rootFolder;
    }


    public File getConfigFile() throws InternalErrorException {

        File rootFolder = getRootDir();

        String appConfigFileName = (AppHelper.is_under_dev()) ?
                "app_config_debug.json" : "app_config.json"
        ;


        return new File(String.format("%s%s%s%s%s",
                rootFolder.getAbsolutePath(),
                File.separatorChar,
                Hardcoded.APP_CONFIG_DIR.getText(),
                File.separatorChar,
                appConfigFileName
        ));
    }




}

