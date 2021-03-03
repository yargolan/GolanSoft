package com.ygsoft.camping.init;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import com.ygsoft.camping.Messages;
import com.ygsoft.camping.enums.AppData;
import com.ygsoft.camping.enums.FileSystem;



public class CampingAppInit {

    private File applicativeRootFolder;
    private HashMap<String, String> applicationData = new HashMap<>();



    public static void main(String[] args) {
        CampingAppInit campingAppInit = new CampingAppInit();
        try {
            campingAppInit.initApp();
        }
        catch (IOException e) {
            e.printStackTrace();
            Messages.exitWithError(e.getMessage(), true);
        }
    }



    private void initApp() throws IOException {

        // Init root folder.
        initRootFolder();


        // Verify needed folders.
        try {

            initFolders();

            initFiles();

            //dump
//            applicationData.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " + value));
        }
        catch (IOException e) {
            throw new IOException("Cannot init the application", e);
        }
    }



    private void initRootFolder() {
        applicativeRootFolder = (isUnderDev()) ?
            applicativeRootFolder = new File(FileSystem.INITIAL_DATA_FOLDER.getText())
            : new File ("must_change_it") // todo: must_change_it !!!
        ;

        applicationData.put(AppData.KEY_ROOT_FOLDER.getText(), applicativeRootFolder.getAbsolutePath());
    }



    public boolean isUnderDev() {

        String value = System.getenv("UNDER_DEVELOPMENT");

        if (value == null) {
            applicationData.put(AppData.KEY_IS_UNDER_DEV.getText(), "false");
        }
        else {

            if (value.equals("true")) {
                applicationData.put(AppData.KEY_IS_UNDER_DEV.getText(), "true");
            }
            else {
                applicationData.put(AppData.KEY_IS_UNDER_DEV.getText(), "false");
            }
        }

        return (applicationData.get(AppData.KEY_IS_UNDER_DEV.getText()).equals("true"));
    }



    private void initFolders() throws IOException {

        File dataFolder = new File (String.format(
                "%s%s%s",
                applicativeRootFolder,
                File.separatorChar,
                FileSystem.DATA_FOLDER_NAME.getText()
        ));
        applicationData.put(AppData.KEY_DATA_FOLDER.getText(), dataFolder.getAbsolutePath());


        File tripsFolder = new File (String.format(
                "%s%s%s",
                dataFolder,
                File.separatorChar,
                FileSystem.EXISTING_TRIPS_FOLDER.getText()
        ));

        if ( ! tripsFolder.exists()) {
            if ( ! tripsFolder.mkdirs()) {
                throw new IOException("Cannot create the trips folder.");
            }
        }
        applicationData.put(AppData.KEY_TRIPS_FOLDER.getText(), tripsFolder.getAbsolutePath());
    }



    private void initFiles() throws IOException {

        File jsonVehicles = new File(String.format(
                "%s%s%s%s%s",
                applicativeRootFolder.getAbsolutePath(),
                File.separatorChar,
                FileSystem.DATA_FOLDER_NAME.getText(),
                File.separatorChar,
                FileSystem.VEHICLES_DATA_FILE.getText()
        ));
        if (jsonVehicles.exists()) {
            applicationData.put(AppData.KEY_JSON_VEHICLES.getText(), jsonVehicles.getAbsolutePath());
        }
        else {
            throw new IOException(String.format("The '%s' file is missing.", jsonVehicles.getAbsolutePath()));
        }


        File jsonParticipants = new File(String.format(
                "%s%s%s%s%s",
                applicativeRootFolder.getAbsolutePath(),
                File.separatorChar,
                FileSystem.DATA_FOLDER_NAME.getText(),
                File.separatorChar,
                FileSystem.PARTICIPANTS_DATA_FILE.getText()
        ));
        if (jsonVehicles.exists()) {
            applicationData.put(AppData.KEY_JSON_PARTICIPANTS.getText(), jsonParticipants.getAbsolutePath());
        }
        else {
            throw new IOException(String.format("The '%s' file is missing.", jsonParticipants.getAbsolutePath()));
        }
    }
}













//
//import java.io.File;
//import java.io.IOException;
//import java.lang.reflect.Array;
//import java.net.URL;
//import java.util.HashMap;
//
//import com.ygsoft.camping.enums.Hardcoded;
//import com.ygsoft.camping.infra.PropertiesWrapper;
//
//
//
//
//
//    private File initialDataFile;
//    private PropertiesWrapper propertiesWrapper;
//
//
//
//
//
//    private File getInitialDataFile() {
//
//        File file;
//
//        if (isUnderDev()) {
//
//            File initialDataFolder = new File (Hardcoded.INITIAL_DATA_FOLDER.getText());
//
//            file = new File (String.format(
//                    "%s%s%s",
//                    initialDataFolder,
//                    File.separatorChar,
//                    Hardcoded.INITIAL_DATA_FILE.getText()
//            ));
//        }
//        else {
//
//            //todo: fill the block
//            file = null;
//        }
//
//        return file;
//    }
//
//
//
//
//
//
//
//
//    private void initFolders() {
//
//        File dataFolder = new File (String.format(
//                "%s%s%s",
//                ini
//        ))
//    }
//}
//
//
//
//
//
//
//    /*
//
//    public CampingAppInit() {
//
//    }
//
//
//
//    public CampingAppInit3() throws IOException{
//
//        // Set the initial data file.
//        URL url = CampingAppInit
//                .class
//                .getClassLoader()
//                .getResource(Hardcoded.INITIAL_DATA_FILE.getText())
//        ;
//
//        if (url == null) {
//            throw new IOException("Cannot get the initial app data.");
//        }
//
//        File campingAppInitialDataFile = new File(url.getFile());
//
//        propertiesWrapper = new PropertiesWrapper(campingAppInitialDataFile);
//
//        propertiesWrapper.getAllData();
//    }
//
//
//
//    public void appInit() throws IOException {
//
//        // Parse the initial data from the installation.
//        HashMap<String, String> data = readInitialData();
//
//        // Get root folder.
//        File rootFolder = getRootFolder();
//    }
//
//
//
//    private HashMap<String, String> readInitialData() {
//
//        HashMap<String, String> hash = new HashMap<>();
//
//
//
//        return hash;
//    }
//
//
//    private File getRootFolder() {
//
//    }
//
//
//
//
//
//
//
//
//
//
////
////        // Load the initial data from the 'resource' folder.
////        File campingAppInitialDataFile;
////
////        try {
////            URL url = CampingAppInit
////                    .class
////                    .getClassLoader()
////                    .getResource("campingApp.properties");
////
////            if (url == null) {
////                throw new IOException("Cannot get the initial app data.");
////            }
////
////            campingAppInitialDataFile = new File(url.getFile());
////        }
////        catch (IOException e) {
////            throw new IOException("Cannot get the initial data file.", e);
////        }
////
////
////        PropertiesWrapper initialData = new PropertiesWrapper(campingAppInitialDataFile);
////
////        String appRootFolder = initialData.get(Hardcoded.KEY_APP_ROOT_FOLDER.getText());
////
////
////        private URL validUrl   = TestPropertiesWrapper.class.getClassLoader().getResource("campingApp.properties");
////
////
////
////        // Parse the application initial info from the installation path.
////        String initialDataFolder = isUnderDev() ?
////                Hardcoded.INITIAL_DATA_FOLDER.getText()
////                : new File (".").getAbsolutePath();
////        ;
////
////        File initialDataFile = new File (String.format("%s%s%s",
////                initialDataFolder,
////                File.separatorChar,
////                "initialData.properties"
////        ));
////
////
////        String appRootFolder = null;
////        PropertiesWrapper pInitialData = new PropertiesWrapper(initialDataFile);
////        try {
////            pInitialData.parse();
////            appRootFolder = pInitialData.get(Hardcoded.KEY_APP_ROOT_FOLDER.getText());
////        }
////        catch (IOException e) {
////            e.printStackTrace();
////        }
////
////
////        // Verify initial data folders and files.
////        verifyFolders(new File (appRootFolder));
////
////        verifyFiles(appRootFolder);
////    }
//
//
//
//    private void verifyFolders(File rootDir) throws IOException {
//
//    }
//
//
//
//    private void verifyFiles(File rootDir) throws IOException {
//
//    }
//
//
//
//}
//*/
