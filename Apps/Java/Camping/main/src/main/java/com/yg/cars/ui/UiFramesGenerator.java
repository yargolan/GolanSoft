package com.yg.cars.ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class UiFramesGenerator {

    public static final int F_MAIN       = 1;
    public static final int F_GARAGE_ADD = 2;

    public static final int D_ADD_Driver  = 10;
    public static final int D_DATE_PICKER = 11;


    // Set the frame's icon, get it from the resource folder.
    private final URL url = getClass().getResource("/icon.png");
    private final ImageIcon imageIcon = new ImageIcon(url);


    public UiFramesGenerator() {}



    public JFrame generateFrame(int frameType) {

        // Set the frame.
        JFrame f = new JFrame();


        // Generic frame settings
        f.setLayout(null);
        f.setIconImage(imageIcon.getImage());
        f.setResizable(false);
        f.setUndecorated(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        switch (frameType) {

            case F_MAIN:

                // Get the screen's size
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

                int screenHeight = screenSize.height;
                int screenWidth = screenSize.width;

                f.setSize(screenWidth-20, screenHeight-40);
                f.setLayout(null);
                f.setLocation(10, 10);
                f.setContentPane(new JDesktopPane());
                f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                break;

            case F_GARAGE_ADD:
                f.setSize(300, 220);
                f.setLocation(250, 200);
                f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                break;

            default:
                break;
        }

        return f;
    }

    
    
    public JDialog generateDialog(int frameType) {

        JDialog d = new JDialog();
//        d.setModal(true);
        d.setLayout(null);
        d.setIconImage(imageIcon.getImage());
        d.setResizable(false);
        d.setUndecorated(false);
        d.setLocationRelativeTo(null);
        d.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        switch (frameType) {

            case D_DATE_PICKER:
                d.setBounds(500, 300, 300, 200);
                break;

            case D_ADD_Driver:
                d.setBounds(400, 200, 300, 200);
                d.setIconImage(imageIcon.getImage());
                break;

            default:
                break;
        }

        return d;
    }
}