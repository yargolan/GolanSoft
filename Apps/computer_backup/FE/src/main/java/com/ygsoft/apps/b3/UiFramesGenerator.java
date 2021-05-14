package com.ygsoft.apps.b3;

import com.ygsoft.apps.b3.enums.HardCoded;

import java.net.URL;
import javax.swing.*;




public class UiFramesGenerator {

    static final int FRAME_NEW_PROFILE = 1;


    public UiFramesGenerator() {}



    JFrame generate(int frameType) {

        // Set the frame.
        JFrame f = new JFrame();


        // Set the frame's icon, get it from the resource folder.
        URL url = getClass().getResource("/icon.png");
        ImageIcon imageIcon = new ImageIcon(url);


        // Generic frame settings
        f.setLayout(null);
        f.setIconImage(imageIcon.getImage());
        f.setResizable(false);
        f.setUndecorated(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        switch (frameType) {

            case FRAME_NEW_PROFILE:
                f.setSize(550, 460);
                f.setTitle(HardCoded.F_CREATE_NEW_PROFILE.getText());
                f.setLocation(500, 300);
                f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                break;

            default:
                break;
        }

        return f;
    }
}