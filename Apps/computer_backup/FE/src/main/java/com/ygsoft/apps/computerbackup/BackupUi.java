package com.ygsoft.apps.computerbackup;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;



public class BackupUi implements ActionListener  {

    JButton bProfileRun, bProfileEdit, bProfileCreate;

    public BackupUi(){}



    public void setUi() {

        String version = "1.0"; // TODO: Extract to config file.

        // Set the main frame.
        JFrame fMain = new JFrame();
        fMain.setTitle(String.format("Computer backup, version %s", version));
        fMain.setSize(700, 200);
        fMain.setLayout(null);
        fMain.setLocation(400, 100);
        fMain.setResizable(false);
        fMain.setUndecorated(false);
        fMain.setLocationRelativeTo(null);
        fMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        // Set the frame's icon, get it from the resource folder.
        URL url = getClass().getResource("/icon.png");
        InputStream stream = BackupMain.class.getResourceAsStream( "/icon.png" );
        if (url != null && stream != null) {
            ImageIcon imageIcon = new ImageIcon(url);
            fMain.setIconImage(imageIcon.getImage());
            try {
                BufferedImage image = ImageIO.read( stream );
                fMain.setIconImage(image);
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
        fMain.getContentPane().add(bProfileRun);
        fMain.getContentPane().add(bProfileEdit);
        fMain.getContentPane().add(bProfileCreate);

        fMain.setVisible(true);
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
