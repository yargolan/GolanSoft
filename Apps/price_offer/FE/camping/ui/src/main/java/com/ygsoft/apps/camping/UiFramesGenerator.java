package com.ygsoft.apps.camping;

import java.awt.*;
import javax.swing.*;
import com.ygsoft.apps.camping.enums.HebrewTitles;




public class UiFramesGenerator {

    static final int FRAME_TRIP_NAME        = 1;
    static final int FRAME_PARTICIPANT_ADD  = 2;
    static final int FRAME_PARTICIPANT_VIEW = 3;


    static Image framesIcon = Toolkit
            .getDefaultToolkit()
            .getImage(UiFramesGenerator.class.getResource("/icon.png")
            );


    static JFrame generate(int frameType) {

        JFrame f = new JFrame();

        // Generic frame settings
        f.setLayout(null);
        f.setIconImage(framesIcon);
        f.setResizable(false);
        f.setUndecorated(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        switch (frameType) {

            case FRAME_TRIP_NAME:
                f.setFont(new Font("David", Font.BOLD, 22));
                f.setSize(500, 200);
                f.setTitle(HebrewTitles.F_TRIP_SELECT.getText());
                f.setLocation(600, 300);
                break;

            case FRAME_PARTICIPANT_ADD:
                f.setSize(500,300);
                f.setTitle(HebrewTitles.F_PARTICIPANT_ADD.getText());
                f.setLocation(600,300);
                break;

            case FRAME_PARTICIPANT_VIEW:
                f.setSize(600,300);
                f.setTitle(HebrewTitles.F_PARTICIPANT_VIEW.getText());
                f.setLocation(600,300);
                break;

            default:
                break;
        }

        return f;
    }
}