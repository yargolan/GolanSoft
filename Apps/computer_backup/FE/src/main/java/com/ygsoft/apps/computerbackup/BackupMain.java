package com.ygsoft.apps.computerbackup;

import java.io.File;
import java.net.URL;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;



public class BackupMain implements ActionListener {

    JButton bProfileRun, bProfileEdit, bProfileCreate;


    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e) {
            System.err.println("Cannot set the LNF. Sorry...");
        }


        // Set the UI
        BackupMain main = new BackupMain();
        main.setUi();
    }




    private void setUi() {

        String version = "1.0"; // TODO: Extract to config file.

        // Set the main frame.
        JFrame f = new JFrame(String.format("Computer backup, version %s", version));
        f.setSize(700, 200);
        f.setLayout(null);
        f.setLocation(400, 100);
        f.setResizable(false);
        f.setUndecorated(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        // Set the frame's icon, get it from the resource folder.
        URL url = getClass().getResource("/icon.png");
        InputStream stream = BackupMain.class.getResourceAsStream( "/icon.png" );
        if (url != null && stream != null) {
            ImageIcon imageIcon = new ImageIcon(url);
            f.setIconImage(imageIcon.getImage());
            try {
                BufferedImage image = ImageIO.read( stream );
                f.setIconImage(image);
            }
            catch (IOException e) {
                System.err.println("Cannot load the icon.");
            }
        }


        // Set the action buttons.
        bProfileRun    = new JButton("Run profile");
        bProfileEdit   = new JButton("Edit profile");
        bProfileCreate = new JButton("Create profile");

        bProfileRun.addActionListener   (this);
        bProfileEdit.addActionListener  (this);
        bProfileCreate.addActionListener(this);

        bProfileRun.setBounds   (20,  20, 200, 40);
        bProfileEdit.setBounds  (240, 20, 200, 40);
        bProfileCreate.setBounds(460, 20, 200, 40);


        // Add buttons to the frame
        f.getContentPane().add(bProfileRun);
        f.getContentPane().add(bProfileEdit);
        f.getContentPane().add(bProfileCreate);


        f.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        ProfileUi profileUi = new ProfileUi();

        if (actionCommand.equals(bProfileRun.getText())) {
            profileUi.run();
        }
        else if (actionCommand.equals(bProfileEdit.getText())) {
            profileUi.edit();
        }
        else if (actionCommand.equals(bProfileCreate.getText())) {
            profileUi.create();
        }
        else {
            System.out.printf("Internal error: Cannot determine action for '%s'.%n", actionCommand);
        }
    }
}


