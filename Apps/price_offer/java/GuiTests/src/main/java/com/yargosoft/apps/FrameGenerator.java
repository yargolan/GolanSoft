package com.yargosoft.apps;



import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FrameGenerator {



    public static JFrame generate (File dataFile) {

        JFrame theFrame = new JFrame();


        // Load the properties file.
        Properties properties = new Properties();

        try {
            properties.load (new FileInputStream(dataFile));
        }
        catch (IOException e) {
            return null;
        }

        // Set the frame's title.
        theFrame.setTitle(properties.getProperty("MAIN_GUI_HEADER", "xxx"));



        // Set the size of the created frame.
        theFrame.setSize(
            Integer.valueOf(properties.getProperty("MAIN_GUI_WIDTH")),
            Integer.valueOf(properties.getProperty("MAIN_GUI_HEIGHT"))
        );




        // Place the frame in the middle of the screen.
        theFrame.setLocationRelativeTo(null);



        // Set the resizable options.
        theFrame.setResizable(Boolean.valueOf(properties.getProperty("MAIN_GUI_RESIZABLE").toLowerCase()));



        // Make sure to the exit once the GUI is closed.
        theFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        // Return the frame.
        return theFrame;
    }
}


