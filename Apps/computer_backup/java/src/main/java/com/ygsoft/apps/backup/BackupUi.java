package com.ygsoft.apps.backup;

import java.awt.*;
import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import com.ygsoft.apps.backup.data.DataSingleton;
import com.ygsoft.apps.backup.data.Profile;
import javax.swing.filechooser.FileSystemView;



public class BackupUi {

    private static String userName, hostName;
    private static JButton bProfileSelect, bProfileRun, bProfileEdit;
    private final UiFramesGenerator uiFramesGenerator = new UiFramesGenerator();
    private final JTextArea taItemsToBackup = new JTextArea(15, 70);

    private static JComboBox<String> ddProfileName = new JComboBox<>();



    public BackupUi() {}



    public void createUi() {

        // Init the application.
        initApp();


        // Set the main frame.
        UiFramesGenerator uiFramesGenerator = new UiFramesGenerator();
        JFrame fMain = uiFramesGenerator.generate(UiFramesGenerator.FRAME_MAIN);


        Container container = fMain.getContentPane();


        // Host name
        JLabel lHostName = new JLabel("Host name: ");
        lHostName.setBounds(20, 20, 80, 20);
        JTextField tfHostName = new JTextField();
        tfHostName.setEditable(false);
        tfHostName.setText(hostName);
        tfHostName.setBounds(110, 20, 200, 20);


        // User name
        JLabel lUserName = new JLabel("User name: ");
        lUserName.setBounds(20, 50, 80, 20);
        JTextField tfUserName = new JTextField();
        tfUserName.setEditable(false);
        tfUserName.setText(userName);
        tfUserName.setBounds(110, 50, 200, 20);


        // Profile
        JLabel lProfile = new JLabel("Profile: ");
        lProfile.setBounds(20, 80, 80, 20);
        ddProfileName.setBounds(110, 80, 200, 20);
        refresh_profiles();
        ddProfileName.addItem(HardCoded.F_CREATE_NEW_PROFILE.getText());

        bProfileSelect = new JButton(HardCoded.SELECT.getText());
        bProfileSelect.setBounds(330, 80, 100, 20);

        bProfileRun    = new JButton(HardCoded.RUN.getText());
        bProfileEdit   = new JButton(HardCoded.EDIT.getText());

        bProfileEdit.setBounds  (50,  120, 100, 30);
        bProfileRun.setBounds   (170, 120, 100, 30);
        
        bProfileRun.setEnabled(false);
        bProfileEdit.setEnabled(false);


        taItemsToBackup.setEditable(false);
        taItemsToBackup.setLineWrap(false);
        taItemsToBackup.setWrapStyleWord(false);
        taItemsToBackup.setBounds(20, 180, 500, 320);
        JScrollPane scroll = new JScrollPane (taItemsToBackup);
        scroll.setVerticalScrollBarPolicy  (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);


        // +-------------------------------------
        // | Action listeners
        // +-------------------------------------
        addActionListeners();



        // +-------------------------------------
        // | Add the UI parts to the form.
        // +-------------------------------------
        container.add(lHostName);
        container.add(lUserName);
        container.add(lProfile);

        container.add(tfHostName);
        container.add(tfUserName);

        container.add(ddProfileName);

        container.add(taItemsToBackup);

        container.add(bProfileSelect);
        container.add(bProfileRun);
        container.add(bProfileEdit);

        fMain.setVisible(true);
    }



    private void refresh_profiles() {

        Profile profile = new Profile(null);
        List<String> profilesList = profile.getProfiles(hostName, userName);

        for (String s : profilesList) {
            ddProfileName.addItem(s);
        }
    }


    private void addActionListeners() {

        bProfileSelect.addActionListener(e->{

            // Read the selected option from the 'Profiles' drop down.
            String readProfile = (String)ddProfileName.getSelectedItem();

            if (readProfile == null) {
                return;
            }

            if (readProfile.equals(HardCoded.F_CREATE_NEW_PROFILE.getText())) {
                createNewProfile();
                refresh_profiles();
            }
            else {
                bProfileRun.setEnabled(true);
                bProfileEdit.setEnabled(true);
            }
        });


        bProfileRun.addActionListener(e->{

            // Read the selected option from the 'Profiles' drop down.
            String readProfile = (String)ddProfileName.getSelectedItem();

            if (readProfile == null) {
                return;
            }

            Profile profile = new Profile(readProfile);
            List<String> output = profile.runBackup();
            if (output.isEmpty()) {
                Messages.info("Backup ran successfully.");
            }
            else {
                // Show the output log.
                for (String s : output) {
                    taItemsToBackup.append(s);
                    taItemsToBackup.append("\n");
                }
                Messages.error("Errors while running the backup. Please look into the log.");
            }
        });
    }



    private void createNewProfile() {

        JFrame fNewProfile = uiFramesGenerator.generate(UiFramesGenerator.FRAME_NEW_PROFILE);
        fNewProfile.setTitle(HardCoded.TITLE_NEW_PROFILE.getText());

        JLabel     lProfileName    = new JLabel("Profile name :");
        JLabel     lTargetFolder   = new JLabel("target folder :");
        JLabel     lItemToBackup   = new JLabel("Item to backup :");
        JTextField tfProfileName   = new JTextField();
        JTextField tfTargetFolder  = new JTextField();
        JTextField tfItemToBackup  = new JTextField();
        JTextArea  taItemsToBackup = new JTextArea();

        JButton bCreateProfile      = new JButton(HardCoded.CREATE.getText());
        JButton bAddItemToBackup    = new JButton(HardCoded.ADD.getText());
        JButton bBrowseTargetFolder = new JButton(HardCoded.BROWSE.getText());
        JButton bBrowseItemToBackup = new JButton(HardCoded.BROWSE.getText());


        taItemsToBackup.setBackground(new Color(Color.LIGHT_GRAY.getRGB()));


        lProfileName.setBounds ( 20,  20, 100, 20);
        lTargetFolder.setBounds( 20,  60, 100, 20);
        lItemToBackup.setBounds( 20, 100, 120, 20);

        tfProfileName.setBounds (130, 20,  200, 20);
        tfTargetFolder.setBounds(130, 60,  400, 20);
        tfItemToBackup.setBounds(130, 100, 400, 20);

        bCreateProfile.setBounds     (170, 360, 200, 30);
        bAddItemToBackup.setBounds   (20,  140, 80,  30);
        bBrowseTargetFolder.setBounds(550, 60,  90,  20);
        bBrowseItemToBackup.setBounds(550, 100, 90,  20);

        taItemsToBackup.setBounds(20, 180, 510, 160);

        Container container = fNewProfile.getContentPane();

        container.add(lProfileName);
        container.add(lTargetFolder);
        container.add(lItemToBackup);
        container.add(tfProfileName);
        container.add(tfTargetFolder);
        container.add(tfItemToBackup);
        container.add(taItemsToBackup);
        container.add(bCreateProfile);
        container.add(bAddItemToBackup);
        container.add(bBrowseTargetFolder);
        container.add(bBrowseItemToBackup);

        fNewProfile.setVisible(true);

        bBrowseTargetFolder.addActionListener(e->{
            String targetFolder = uiFramesGenerator.selectFolder(
                    FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath()
            );

            if (targetFolder != null && ! targetFolder.isEmpty()) {
                tfTargetFolder.setText(targetFolder);
            }
        });


        bBrowseItemToBackup.addActionListener(e->{
            String itemToBackup = uiFramesGenerator.selectFolderOrFile(
                    FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath()
            );

            if (itemToBackup != null && ! itemToBackup.isEmpty()) {
                tfItemToBackup.setText(itemToBackup);
            }
        });

        bAddItemToBackup.addActionListener(e->{
            String readItem = tfItemToBackup.getText();
            if (readItem != null && ! readItem.isEmpty()) {
                taItemsToBackup.append(readItem);
                taItemsToBackup.append("\n");
                tfItemToBackup.setText("");
            }
        });

        bCreateProfile.addActionListener(e->{

            // read the profile's name.
            String profileName = tfProfileName.getText();

            // Read the profile's target folder.
            String targetFolder = tfTargetFolder.getText();

            // Read the items to backup.
            String items = taItemsToBackup.getText();


            // Validate
            if (profileName == null || profileName.isEmpty()) {
                Messages.error("No profile name provided.");
                return;
            }

            if (targetFolder == null || targetFolder.isEmpty()) {
                Messages.exitWithError("No target folder provided.");
                return;
            }


            if (items == null || items.isEmpty()) {
                Messages.error("No items to backup provided.");
                return;
            }


            List<String> itemsToBackup = Arrays.asList(items.split("\n"));

            Profile profile = new Profile(profileName, targetFolder, itemsToBackup);
            profile.save();
            Messages.info(String.format(
                    "The new profile (%s) created successfully.", profileName
            ));
            fNewProfile.dispose();
        });
    }



    private void initApp() {

        DataSingleton ds = DataSingleton.getInstance();

        // Get the username.
        userName = ds.getDataUserName();


        // Get the host name
        hostName = ds.getDataHostName();


        // Get the profiles.
        refresh_profiles();
    }
}







