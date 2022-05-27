package com.ygsoft.apps;

import java.io.File;

public class PythonActions {

    private File pythonScript;


    PythonActions() {
        try {
            File f = new File("../be/Lotto_main.py").getCanonicalFile();
            pythonScript = f;
        }
        catch (Exception e) {
            pythonScript = null;
        }
        System.out.println("pythonScript = " + pythonScript);
    }


    void update(){

    }



    void guess(){

    }
}
