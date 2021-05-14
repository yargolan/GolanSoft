package com.ygsoft.apps.b3;

import com.ygsoft.apps.b3.enums.HardCoded;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Arrays;




public class ProfilesGenerator {


    JButton bBrowseRootDir, bAddItems, bGenerate;
    JFrame     fMain;
    JTextArea  taItemsToBackup = new JTextArea();
    JTextField tfTargetFolder  = new JTextField();
    JTextField tfProfileName   = new JTextField();
    JTextField tfProfileHost   = new JTextField();


    ProfilesGenerator(){}



    void generate() {

        UiFramesGenerator uiFramesGenerator = new UiFramesGenerator();
        fMain = uiFramesGenerator.generate(UiFramesGenerator.FRAME_NEW_PROFILE);

        // Get the hostname
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        }
        catch (UnknownHostException e) {
            hostname = "Unknown";
        }


        JLabel lHostName      = new JLabel("Computer name:");
        JLabel lProfileName   = new JLabel("ZzProfile name:");
        JLabel lTargetFolder  = new JLabel("Target folder:");
        JLabel lItemsToBackup = new JLabel("Items to b1:");

        tfProfileHost.setEditable(false);
        tfProfileHost.setText(hostname);




        bBrowseRootDir = new JButton("Browse ...");
        bAddItems      = new JButton("Add Items");
        bGenerate      = new JButton("Generate zzProfile");


        // Position
        lHostName.setBounds     (20, 50,  110, 20);
        lProfileName.setBounds  (20, 20,  110, 20);
        lTargetFolder.setBounds (20, 80,  110, 20);
        lItemsToBackup.setBounds(20, 110, 110, 20);

        tfProfileName.setBounds (135, 20, 200, 20);
        tfProfileHost.setBounds (135, 50, 200, 20);
        tfTargetFolder.setBounds(135, 80, 395, 20);
        taItemsToBackup.setBounds(20, 135, 400, 150);

        bBrowseRootDir.setBounds(430, 110,  100, 20);
        bAddItems.setBounds     (430, 250, 100, 30);
        bGenerate.setBounds     (180, 370, 200, 30);

        Container container = fMain.getContentPane();
        container.add(lHostName);
        container.add(lProfileName);
        container.add(lTargetFolder);
        container.add(lItemsToBackup);

        container.add(tfProfileName);
        container.add(tfProfileHost);
        container.add(tfTargetFolder);
        container.add(taItemsToBackup);

        container.add(bAddItems);
        container.add(bGenerate);
        container.add(bBrowseRootDir);

        // Add the action listeners
        addActionListeners();


        fMain.setVisible(true);

    }



    private void addActionListeners() {

        bBrowseRootDir.addActionListener(e->{
            String targetFolder = selectTargetFolder(tfTargetFolder.getText());
            if (targetFolder == null) {
                tfTargetFolder.setText("");
            }
            else {
                tfTargetFolder.setText(targetFolder);
            }
        });


        bAddItems.addActionListener(e->{

            String item = selectItemToBackup();

            if (item != null) {
                taItemsToBackup.append(item);
                taItemsToBackup.append("\n");
            }
        });

        bGenerate.addActionListener(e->{

            // Read the form
            String readProfileName   = tfProfileName.getText();
            String readProfileHost   = tfProfileHost.getText();
            String readTargetFolder  = tfTargetFolder.getText();
            String readItemsToBackup = taItemsToBackup.getText();

            // Verify
            if (readProfileName == null || readProfileName.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "Invalid zzProfile name provided.");
                return;
            }

            if (readProfileHost == null || readProfileHost.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "Invalid hoist name provided.");
                return;
            }

            if (readTargetFolder == null || readTargetFolder.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "Invalid target folder provided.");
                return;
            }

            if (readItemsToBackup == null || readItemsToBackup.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "No items to b1. Invalid zzProfile.");
                return;
            }


            // Set the info into a 'zzProfile' object
            List<String> itemsToBackup = Arrays.asList(readItemsToBackup.split("\n"));
            Profile zzProfile = new Profile(readProfileName, readProfileHost, readTargetFolder, itemsToBackup);

            File userProfilesFile = new File(String.format("\"%s%s%s.json",
                    HardCoded.DIR_NAME_PROFILES.getText(),
                    File.separatorChar,
                    readProfileName
            ));

            MixedProfilesWrapper mixedProfilesWrapper = new MixedProfilesWrapper(userProfilesFile, zzProfile);
            try {
                mixedProfilesWrapper.addToFile();
                fMain.dispose();
            }
            catch (Exception ex) {
                Messages.showMessage(Messages.MESSAGE_ERR, "Cannot add the zzProfile: " + ex.getMessage());
                return;
            }
            Messages.showMessage(Messages.MESSAGE_INF, String.format(
                    "The zzProfile '%s' was successfully created.", readProfileName
            ));
        });
    }



    private String selectTargetFolder(String startsFrom) {

        File rootDir = (startsFrom == null || startsFrom.isEmpty()) ?
                FileSystemView.getFileSystemView().getHomeDirectory() : new File (startsFrom);

        String targetFolder = null;

        JFileChooser jfc = new JFileChooser(rootDir);

        jfc.setDialogTitle("Choose the target folder :");

        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            targetFolder = selectedFile.getAbsolutePath();
        }

        return targetFolder;
    }



    private String selectItemToBackup() {

        String item = null;

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        jfc.setDialogTitle("Choose item to b1 :");

        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            item = selectedFile.getAbsolutePath();
        }

        return item;
    }
}
