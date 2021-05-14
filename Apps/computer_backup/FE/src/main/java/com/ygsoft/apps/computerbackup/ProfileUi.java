package com.ygsoft.apps.computerbackup;

import javax.swing.*;
import java.util.List;
import java.util.Arrays;



public class ProfileUi {


    public ProfileUi() {}


    void edit() {

        JFrame f = new JFrame("Create a new profile");
        f.setSize(600, 200);
        f.setLayout(null);
        f.setLocation(600, 250);
        f.setResizable(false);
        f.setUndecorated(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


    void create() {

        JFrame f = new JFrame("Create a new profile");
        f.setSize(500, 400);
        f.setLayout(null);
        f.setLocation(500, 300);
        f.setResizable(false);
        f.setUndecorated(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLabel lProfileName = new JLabel("Profile name");
        JLabel lProfileDesc = new JLabel("Profile Description");

        JTextField tfProfileName = new JTextField();
        JTextField tfProfileDesc = new JTextField();

        JButton bSave    = new JButton("Save profile");
        JButton bAddItem = new JButton("Add item");

        JTextArea taItems = new JTextArea();

        taItems.setBounds      (20,  130, 460, 230);
        bSave.setBounds        (140, 80,  120, 30);
        bAddItem.setBounds     (20,  80,  100, 30);
        lProfileName.setBounds (20,  20,  150, 20);
        lProfileDesc.setBounds (20,  50,  200, 20);
        tfProfileName.setBounds(140, 20,  200, 20);
        tfProfileDesc.setBounds(140, 50,  200, 20);


        f.getContentPane().add(bSave);
        f.getContentPane().add(taItems);
        f.getContentPane().add(bAddItem);
        f.getContentPane().add(lProfileName);
        f.getContentPane().add(lProfileDesc);
        f.getContentPane().add(tfProfileName);
        f.getContentPane().add(tfProfileDesc);


        // Add action listeners for the buttons.
        bSave.addActionListener(e->{

            // Read the profile's name.
            String profileName = tfProfileName.getText();

            // Read the profile's description.
            String profileDesc = tfProfileDesc.getText();

            // Read the list of items to backup.
            String[] profileItems = taItems.getText().split("\\n");


            // Validate.
            if (profileName == null || profileName.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "No profile name provided.");
                return;
            }

            if (profileDesc == null || profileDesc.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "No profile description provided.");
                return;
            }

            if (profileItems.length == 0 || profileItems[0].equals("")) {
                Messages.showMessage(Messages.MESSAGE_ERR, "No items for backup provided.");
                return;
            }

            // Convert the split items into a list.
            List<String> itemsList = Arrays.asList(profileItems);

            Profile p = new Profile();
            p.create(profileName, profileDesc, itemsList);
            p.save();
            f.dispose();
        });

        f.setVisible(true);
    }



    void run() {

        JFrame f = new JFrame("Create a new profile");
        f.setSize(600, 200);
        f.setLayout(null);
        f.setLocation(600, 250);
        f.setResizable(false);
        f.setUndecorated(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }
}
