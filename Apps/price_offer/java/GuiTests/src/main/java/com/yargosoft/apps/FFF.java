package com.yargosoft.apps;


import javax.swing.*;
import java.io.File;

public class FFF {


    public static void main(String[] args) {

        JFrame f = FrameGenerator.generate(new File("data.properties"));

        JButton b = new JButton("XXX");

        if (null == f) {
            System.exit(1);
        }

        f.add(b);

        f.setVisible(true);
    }
}
