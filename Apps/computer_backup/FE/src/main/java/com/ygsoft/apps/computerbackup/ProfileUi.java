package com.ygsoft.apps.computerbackup;

import javax.swing.*;
import java.io.File;
import java.util.List;
import java.util.Arrays;



public class ProfileUi {

    private final DataSingleton dataSingleton = DataSingleton.getInstance();


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
        bAddItem.addActionListener(e->{
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Select item to backup");
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                taItems.append(chooser.getSelectedFile().getAbsolutePath() + "\n");
            }
        });


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

            Profile p = new Profile(profileName, profileDesc, itemsList);
            p.create();
            p.save();
            f.dispose();
            Messages.showMessage(Messages.MESSAGE_INF,
                    String.format("The profile '%s' created successfully.", profileName)
            );
        });

        f.setVisible(true);
    }



    void run() {

        // Get the list of profiles.
        File profilesDir = dataSingleton.getProfilesRootDir();
        File[] list = profilesDir.listFiles((dir, name) -> name.toLowerCase().endsWith("json"));

        if (list == null) {
            Messages.showMessage(Messages.MESSAGE_ERR, "There are no profiles yet. Please create one.");
            return;
        }

        if (list.length == 0) {
            Messages.showMessage(Messages.MESSAGE_ERR, "There are no profiles yet. Please create one.");
            return;
        }

        Arrays.sort(list);

        JFrame f = new JFrame("Choose a profile to run");
        f.setSize(400, 150);
        f.setLayout(null);
        f.setLocation(200, 100);
        f.setResizable(false);
        f.setUndecorated(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLabel lProfileName = new JLabel("Select profile :");
        JButton bSelect = new JButton("Select");
        JComboBox<String> ddProfiles = new JComboBox<>();
        for (File profile : list) {
            ddProfiles.addItem(profile.getName().replace(".json", ""));
        }

        bSelect.setBounds     (20,  50, 100, 30);
        ddProfiles.setBounds  (120, 20, 200, 20);
        lProfileName.setBounds(20,  20, 120, 20);


        f.getContentPane().add(bSelect);
        f.getContentPane().add(ddProfiles);
        f.getContentPane().add(lProfileName);


        bSelect.addActionListener(e->{

            // Read the profile name.
            String name = (String) ddProfiles.getSelectedItem();
            Profile p = new Profile(name);
            try {
                p.run();
                Messages.showMessage(Messages.MESSAGE_INF, "Backup ended successfully.");
            }
            catch (Exception e1) {
                Messages.showMessage(
                        Messages.MESSAGE_ERR,
                        "Error while running the backup:\n" +
                        e1.getMessage()
                );
            }
            f.dispose();
        });

        f.setVisible(true);
    }
}
