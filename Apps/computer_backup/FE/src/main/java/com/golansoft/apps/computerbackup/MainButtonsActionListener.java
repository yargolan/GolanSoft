package com.golansoft.apps.computerbackup;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class MainButtonsActionListener implements ActionListener {

    UiFramesGenerator uiFramesGenerator = new UiFramesGenerator();
    DataSingleton dataSingleton = DataSingleton.getInstance();



    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        System.out.println(actionCommand);

        if (actionCommand.equals(HardCoded.B_PROFILE_DELETE.getText())) {
            action_del();
        }
        else if (actionCommand.equals(HardCoded.B_PROFILE_CREATE.getText())) {
            action_create();
        }
        else if (actionCommand.equals(HardCoded.B_PROFILE_RUN.getText())) {
            action_run_backup();
        }
        else {
            Messages.internalError("Unknown button pressed - " + actionCommand);
        }
    }



    private void action_create() {

        List<String> items = new ArrayList<>();

        JFrame fProfileCreate = uiFramesGenerator.generate(UiFramesGenerator.FRAME_PROFILE_NEW);
        JLabel lProfileName          = new JLabel(HardCoded.L_PROFILE_NAME.getText());
        JLabel lProfileDesc          = new JLabel(HardCoded.L_PROFILE_DESC.getText());
        JLabel lProfileUser          = new JLabel(HardCoded.L_PROFILE_USER.getText());
        JLabel lProfileHost          = new JLabel(HardCoded.L_PROFILE_HOST.getText());
        JLabel lProfileTargetFolder  = new JLabel(HardCoded.B_TARGET_FOLDER.getText());
        JLabel lProfileItemsToBackup = new JLabel(HardCoded.L_PROFILE_ITEMS.getText());


        JTextField tfProfileUser          = new JTextField();
        JTextField tfProfileHost          = new JTextField();
        JTextField tfProfileName          = new JTextField();
        JTextField tfProfileDesc          = new JTextField();
        JTextField tfProfileTargetFolder  = new JTextField();


        JButton bCancel       = new JButton(HardCoded.B_CANCEL.getText());
        JButton bCreate       = new JButton(HardCoded.B_SAVE.getText());
        JButton bItemsAdd     = new JButton(HardCoded.B_ADD.getText());
        JButton bTargetFolder = new JButton(HardCoded.B_SEARCH.getText());


        JTextArea taItemsToBackup = new JTextArea(12, 70);
        taItemsToBackup.setBackground(Color.LIGHT_GRAY);
        taItemsToBackup.setEditable(false);
        taItemsToBackup.setLineWrap(true);
        taItemsToBackup.setWrapStyleWord(false);
        JScrollPane scroll = new JScrollPane (taItemsToBackup);
        scroll.setVerticalScrollBarPolicy  (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);


        // Location
        lProfileName.setBounds         (350,20, 150, 30);
        lProfileDesc.setBounds         (350,60, 150, 30);
        lProfileUser.setBounds         (350,100,150, 30);
        lProfileHost.setBounds         (350,140,150, 30);
        lProfileTargetFolder.setBounds (350,180,150, 30);
        lProfileItemsToBackup.setBounds(350,220,150, 30);

        tfProfileName.setBounds        (140,20, 200, 30);
        tfProfileDesc.setBounds        (140,60, 200, 30);
        tfProfileUser.setBounds        (140,100,200, 30);
        tfProfileHost.setBounds        (140,140,200, 30);
        tfProfileTargetFolder.setBounds(90, 180,250, 30);

        scroll.setBounds               (20, 260, 400, 170);

        bCancel.setBounds      (20, 440,90,40);
        bCreate.setBounds      (170,440,90,40);
        bItemsAdd.setBounds    (350,440,90,40);
        bTargetFolder.setBounds(20, 185,60,20);


        // Initial data
        tfProfileUser.setText(dataSingleton.getUserName());
        tfProfileHost.setText(dataSingleton.getHostName());
        tfProfileUser.setEnabled(false);
        tfProfileUser.setEditable(false);
        tfProfileHost.setEnabled(false);
        tfProfileHost.setEditable(false);

        // Action listeners
        bCancel.addActionListener(al->fProfileCreate.dispose());
        bItemsAdd.addActionListener(al->{
            String startPoint;
            try {
                startPoint = new File(".").getCanonicalPath();
            }
            catch (IOException e) {
                startPoint = "/";
            }

            String item = uiFramesGenerator.selectFolderOrFile(startPoint);
            if (item != null && ! item.isEmpty()) {
                if (items.size() == 0 || ! itemInList(item, items)) {
                    items.add(item);
                    taItemsToBackup.append(item);
                    taItemsToBackup.append("\n");
                }
                else {
                    String message = HardCoded.M_ERR_ALREADY_EXISTS.getText() + "\n" + item;
                    Messages.showMessage(Messages.MESSAGE_INF, message);
                }
            }
        });
        bTargetFolder.addActionListener(al->{
            String startPoint;
            try {
                startPoint = new File(".").getCanonicalPath();
            }
            catch (IOException e) {
                startPoint = "/";
            }
            String targetFolder = uiFramesGenerator.selectFolder(startPoint);
            if (targetFolder != null && ! targetFolder.isEmpty()) {
                tfProfileTargetFolder.setText(targetFolder);
            }
        });
        bCreate.addActionListener(al->{

            // Read the form's data
            String readProfileName     = tfProfileName.getText();
            String readProfileDesc     = tfProfileDesc.getText();
            String readProfileUser     = tfProfileUser.getText();
            String readProfileHost     = tfProfileHost.getText();
            String readTargetFolder    = tfProfileTargetFolder.getText();
            String[] readItemsToBackup = taItemsToBackup.getText().split("\n");

            if (readProfileName == null || readProfileName.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, HardCoded.M_ERR_NO_PROFILE_NAME.getText());
                return;
            }

            if (readProfileDesc == null || readProfileDesc.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, HardCoded.M_ERR_NO_PROFILE_DESC.getText());
                return;
            }

            if (readProfileUser == null || readProfileUser.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, HardCoded.M_ERR_INTERNAL_ERROR.getText());
                return;
            }

            if (readProfileHost == null || readProfileHost.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, HardCoded.M_ERR_INTERNAL_ERROR.getText());
                return;
            }

            if (readTargetFolder == null || readTargetFolder.isEmpty()) {
                System.out.println("readTargetFolder = " + readTargetFolder);
                Messages.showMessage(Messages.MESSAGE_ERR, HardCoded.M_ERR_NO_TARGET_FOLDER.getText());
                return;
            }

            if (readItemsToBackup[0] == null || readItemsToBackup[0].isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, HardCoded.M_ERR_NO_ITEMS_TO_BACKUP.getText());
                return;
            }


            // Close the form.
            fProfileCreate.dispose();

            // Create the profile.
            Profile profile = new Profile(
                    readProfileName,
                    readProfileDesc,
                    readProfileUser,
                    readProfileHost,
                    readTargetFolder,
                    readItemsToBackup
            );

            try {
                profile.save();
            }
            catch (IOException ioe) {
                Messages.showMessage(Messages.MESSAGE_ERR, ioe.getMessage());
            }

            Messages.showMessage(Messages.MESSAGE_INF, HardCoded.M_INF_PROFILE_CREATED.getText());
        });


        Container container = fProfileCreate.getContentPane();

        container.add(lProfileUser);
        container.add(lProfileHost);
        container.add(lProfileName);
        container.add(lProfileDesc);
        container.add(lProfileTargetFolder);
        container.add(lProfileItemsToBackup);

        container.add(tfProfileUser);
        container.add(tfProfileHost);
        container.add(tfProfileName);
        container.add(tfProfileDesc);
        container.add(tfProfileTargetFolder);

        container.add(scroll);

        container.add(bCreate);
        container.add(bCancel);
        container.add(bItemsAdd);
        container.add(bTargetFolder);

        fProfileCreate.setVisible(true);

    }



    private boolean itemInList(String item, List<String>list) {
        for (String s : list) {
            if (s.equals(item)) {
                return true;
            }
        }
        return false;
    }



    private void action_run_backup() {

        // Get the list of profiles.
        Profiles profiles = new Profiles();
        File[] profilesList = profiles.getProfilesList();

        if (profilesList.length == 0) {
            Messages.showMessage(Messages.MESSAGE_INF, HardCoded.M_ERR_NO_PROFILES_FOUND.getText());
            return;
        }

        JFrame fRun = uiFramesGenerator.generate(UiFramesGenerator.FRAME_PROFILE_RUN);

        JButton bCancel    = new JButton(HardCoded.B_CANCEL.getText());
        JButton bRunBackup = new JButton(HardCoded.B_RUN_BACKUP.getText());

        JLabel lProfileName = new JLabel(HardCoded.L_PROFILE_NAME.getText());
        JComboBox<String> ddProfileNames = new JComboBox<>();
        for (File profileName : profilesList) {
            ddProfileNames.addItem(profileName.getName().replace(".json", ""));
        }

        // Locations
        bCancel.setBounds       (20,  80, 90,  40);
        bRunBackup.setBounds    (130, 80, 90,  40);
        lProfileName.setBounds  (220, 20, 100, 30);
        ddProfileNames.setBounds(20,  25, 170, 25);


        Container container = fRun.getContentPane();
        container.add(bCancel);
        container.add(bRunBackup);
        container.add(lProfileName);
        container.add(ddProfileNames);


        // Action listeners
        bCancel.addActionListener(e->fRun.dispose());
        bRunBackup.addActionListener(e->{

            String profileName  = (String) ddProfileNames.getSelectedItem();

            // Close the current window.
            fRun.dispose();


            // Read the list of items to backup.
            File profile = new File(
                    dataSingleton.getProfilesRootDir().getAbsolutePath()
                            + File.separatorChar
                            + profileName
                            + ".json"
            );

            if (profile.exists()) {

                Gson gson = new Gson();
                try {
                    Reader reader = Files.newBufferedReader(Paths.get(profile.getAbsolutePath()));
                    Profile readProfile = gson.fromJson(reader, Profile.class);

                    String[] items      = readProfile.getItemsToBackup();
                    String targetFolder = readProfile.getTargetFolder();


                    if (items == null) {
                        Messages.exitWithError(HardCoded.M_ERR_INTERNAL_ERROR.getText());
                    }
                    else {
                        for (String item : items) {
                            backupItem(new File(item), targetFolder);
                        }
                    }
                }
                catch (IOException ioException) {
                    Messages.showMessage(Messages.MESSAGE_ERR, HardCoded.M_ERR_INTERNAL_ERROR.getText());
                }
            }

            Messages.showMessage(Messages.MESSAGE_INF, HardCoded.M_INF_BACKUP_SUCCEEDED.getText());
            fRun.dispose();
        });

        fRun.setVisible(true);
    }



    private void backupItem(File currentItem, String targetFolder) {

        File[] list = currentItem.listFiles();

        if (list == null) {
            return;
        }

        for (File subItem : list) {
            if (subItem.isFile()) {
                System.out.printf("Copy '%s'%n", subItem.getAbsolutePath());
                File fileOnTargetFolder = new File(String.format("%s%s", targetFolder, subItem));
                if (fileOnTargetFolder.exists()) {

                    try {
                        // Verify checksums
                        String sourceFileChecksum = MD5Checksum.getMD5Checksum(subItem);
                        String targetFileChecksum = MD5Checksum.getMD5Checksum(fileOnTargetFolder);
                        if (!sourceFileChecksum.equals(targetFileChecksum)) {
                            FileUtils.copyFile(subItem, fileOnTargetFolder);
                        }
                    }
                    catch (Exception e) {
                        Messages.showMessage(Messages.MESSAGE_ERR, e.getMessage());
                    }
                }
                else {
                    File parentDir = fileOnTargetFolder.getParentFile();
                    if (!parentDir.exists()) {
                        if (!parentDir.mkdirs()) {
                            Messages.showMessage(Messages.MESSAGE_ERR, HardCoded.M_ERR_INTERNAL_ERROR.getText());
                            return;
                        }
                    }

                    try {
                        FileUtils.copyFile(subItem, fileOnTargetFolder);
                    }
                    catch (IOException e) {
                        Messages.showMessage(Messages.MESSAGE_ERR, e.getMessage());
                    }
                }
            }
            else {
                backupItem(subItem, targetFolder);
            }
        }
    }



    private void action_del() {

        // Read the list of profiles
        Profiles profiles   = new Profiles();
        File[] profilesList = profiles.getProfilesList();
        if (profilesList.length == 0) {
            Messages.showMessage(
                    Messages.MESSAGE_INF,
                    HardCoded.M_ERR_NO_PROFILES_FOUND.getText()
            );
            return;
        }

        JFrame fProfileDelete = uiFramesGenerator.generate(UiFramesGenerator.FRAME_PROFILE_DEL);
        JLabel lProfileName = new JLabel(HardCoded.L_PROFILE_NAME.getText());


        JComboBox<String> ddProfileNames = new JComboBox<>();
        for (File f : profilesList) {
            ddProfileNames.addItem(f.getName().replace(".json", ""));
        }


        JButton bCancel = new JButton(HardCoded.B_CANCEL.getText());
        JButton bDelete = new JButton(HardCoded.B_DELETE.getText());


        lProfileName.setBounds  (300, 30, 100, 25);
        ddProfileNames.setBounds(100, 30, 170, 25);
        bCancel.setBounds       (100, 100, 100, 50);
        bDelete.setBounds       (220, 100, 100, 50);

        fProfileDelete.getContentPane().add(lProfileName);
        fProfileDelete.getContentPane().add(bCancel);
        fProfileDelete.getContentPane().add(bDelete);
        fProfileDelete.getContentPane().add(ddProfileNames);

        bCancel.addActionListener(al->fProfileDelete.dispose());
        bDelete.addActionListener(l->{
            if (Messages.areYouSure(HardCoded.M_ARE_YOU_SURE.getText())) {
                File profileToDelete = new File(
                        String.format("%s%s%s.json",
                                dataSingleton.getProfilesRootDir().getAbsolutePath(),
                                File.separatorChar,
                                ddProfileNames.getSelectedItem()
                        ));

                if(profileToDelete.delete()) {
                    Messages.showMessage(
                            Messages.MESSAGE_INF,
                            HardCoded.M_FILE_DELETED_GOOD.getText()
                    );
                }
                else {
                    Messages.showMessage(Messages.MESSAGE_ERR, HardCoded.M_FILE_DELETED_BAD.getText());
                }

                fProfileDelete.dispose();
            }
        });

        fProfileDelete.setVisible(true);
    }
}
