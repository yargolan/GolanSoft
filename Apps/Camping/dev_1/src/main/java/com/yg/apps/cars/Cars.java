package com.yg.apps.cars;

import java.io.File;
import com.yg.apps.cars.ui.CarsUi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Cars {

    private static final Logger logger = LogManager.getLogger(Cars.class);



    public Cars() {}



    public static void main(String[] args) {

        Cars cars = new Cars();

        try {
            cars.initApp();

            cars.runApp();
        }
        catch (Exception e) {
            if (AppHelper.is_under_dev()) {
                e.printStackTrace();
            }
            Messages.exitWithError(e.getMessage(), true);
        }
    }



    private void initApp() throws InternalErrorException {

        DataSingleton dataSingleton = DataSingleton.getInstance();

        File dataFolder = dataSingleton.getParsedDataFolder();

        if (dataFolder.exists()) {
            logger.info("Data folder exists.");
        }
        else {
            if (dataFolder.mkdirs()) {
                logger.info("Data folder created successfully.");
            }
            else {
                throw new InternalErrorException("Cannot create the data folder.");
            }
        }
    }



    private void runApp() {
        CarsUi carsUi = new CarsUi();
        carsUi.createUi();
    }
}

