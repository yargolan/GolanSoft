package com.golansoft.apps.computerbackup;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

public class ProgressSample {

    public static void main(String[] args) {

        int amount = 91;

        JFrame f = new JFrame("JProgressBar Sample");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container content = f.getContentPane();
        JProgressBar progressBar = new JProgressBar();


        progressBar.setStringPainted(true);
        Border border = BorderFactory.createTitledBorder("Backing up ...");
        progressBar.setBorder(border);
        content.add(progressBar, BorderLayout.NORTH);
        f.setSize(300, 100);
        f.setLocationRelativeTo(null);
        f.setLocation(300, 200);
        f.setVisible(true);

        for (int i = 0; i < amount; i++) {

            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            progressBar.setValue(i);
        }

        f.dispose();
    }
}
