package com.ygsoft.nav;

import com.ygsoft.nav.enums.TextsEnum;

import java.net.URL;
import javax.swing.*;




public class UiFramesGenerator {

    static final int FRAME_MAIN       = 1;
    static final int FRAME_POINT_ADD  = 2;
    static final int FRAME_POINT_DEL  = 3;
    static final int FRAME_POINT_EDIT = 4;


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

            case FRAME_MAIN:
                f.setSize(500, 300);
                f.setTitle(TextsEnum.F_MAIN.getText());
                f.setLocation(500, 300);
                f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                break;

            case FRAME_POINT_ADD:
                f.setSize(400, 300);
                f.setTitle(TextsEnum.F_POINT_ADD.getText());
                f.setLocation(600, 400);
                f.setResizable(true);
                break;

            default:
                break;
        }

        return f;
    }
}