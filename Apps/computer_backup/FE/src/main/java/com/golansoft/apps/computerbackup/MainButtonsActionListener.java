package com.golansoft.apps.computerbackup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;


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

        JFrame fProfileCreate = uiFramesGenerator.generate(UiFramesGenerator.FRAME_PROFILE_NEW);
        JLabel lProfileName          = new JLabel(HardCoded.L_PROFILE_NAME.getText());
        JLabel lProfileDesc          = new JLabel(HardCoded.L_PROFILE_DESC.getText());
        JLabel lProfileUser          = new JLabel(HardCoded.L_PROFILE_USER.getText());
        JLabel lProfileHost       = new JLabel(HardCoded.L_PROFILE_HOST.getText());
        JLabel lProfileTargetFolder  = new JLabel(HardCoded.B_TARGET_FOLDER.getText());
        JLabel lProfileItemsToBackup = new JLabel(HardCoded.L_PROFILE_ITEMS.getText());


        JTextField tfProfileUser          = new JTextField();
        JTextField tfProfileHost          = new JTextField();
        JTextField tfProfileName          = new JTextField();
        JTextField tfProfileDesc          = new JTextField();
        JTextField tfProfileTargetFolder  = new JTextField();


        JButton bCancel       = new JButton(HardCoded.B_CANCEL.getText());
        JButton bCreate       = new JButton(HardCoded.B_CREATE.getText());
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

        bCancel.setBounds      (80, 550,80,40);
        bCreate.setBounds      (210,550,80,40);
        bItemsAdd.setBounds    (270,230,60,20);
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
                taItemsToBackup.append(item);
                taItemsToBackup.append("\n");
            }
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



    private void action_run_backup() {

    }



    private void action_del() {

        // Read the list of profiles
        File[] profilesList = getProfilesList();
        if (profilesList.length == 0) {
            Messages.showMessage(
                    Messages.MESSAGE_INF,
                    HardCoded.M_NO_PROFILES_FOUND.getText()
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



    private File[] getProfilesList() {

        DataSingleton dataSingleton = DataSingleton.getInstance();

        FileFilter fileFilter = file -> !file.isDirectory() &&
                file.getName().endsWith(".json");

        return dataSingleton.getProfilesRootDir().listFiles(fileFilter);

    }
}
