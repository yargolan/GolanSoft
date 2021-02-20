package com.ygsoft.camping.infra;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
import com.ygsoft.camping.enums.Hardcoded;



public class PropertiesWrapper {

    private File propertiesFile;
    private String defaultReturnedValue = Hardcoded.NA.getText();
    private HashMap<String, String> data;
    private boolean isParsedFlag = false;




    public PropertiesWrapper(File file) {

        this.propertiesFile = file;

        try {
            parse();
            isParsedFlag = true;
        }
        catch (IOException e) {
            data = new HashMap<>();
        }
    }



    private void parse() throws IOException {

        if (propertiesFile == null) {
            throw new IOException("No valid file name provided.");
        }


        if (propertiesFile.exists()) {

            try (InputStream input = new FileInputStream(propertiesFile)) {

                Properties prop = new Properties();


                // load a properties file
                prop.load(input);


                // Parse the properties file.
                data = new HashMap<>();
                prop.forEach((key, value) -> data.put((String) key, (String) value));
            }
            catch (IOException ex) {
                throw new IOException("Cannot parse the properties file", ex);
            }
        }
        else {
            throw new IOException(String.format("The properties file (%s) is absent",
                    propertiesFile.getName()
            ));
        }
    }



    public String get(String key) {

        if (isParsedFlag) {
            return data.getOrDefault(key, "N/A");
        }
        else {
            return defaultReturnedValue;
        }
    }



    public boolean isParsed() {
        return isParsedFlag;
    }
}
