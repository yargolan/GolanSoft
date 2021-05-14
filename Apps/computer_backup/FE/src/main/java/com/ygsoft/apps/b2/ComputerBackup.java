package com.ygsoft.apps.b2;

import java.awt.*;

import java.io.File;
import java.io.IOException;

import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.ygsoft.apps.b2.enums.TextsEnum;





public class ComputerBackup extends JFrame {

    private File profilesDir;
    private List<String> profileNames;
    private boolean profileSelected = false;
    private JComboBox<String> ddProfileNames;
    private JButton bProfileDelete, bProfileBackup;

    private static final Logger logger = LogManager.getLogger(ComputerBackup.class);





    public static void main(String[] args) {

        try {
            logger.debug("Set system LNF");
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            logger.debug("ok");
            logger.debug("");
        }
        catch (Exception e) {
            System.err.println("Couldn't set the LNF. Sorry...");
        }


        SwingUtilities.invokeLater(() -> {
            ComputerBackup app = new ComputerBackup();
            app.setVisible(true);
        });
    }



    private ComputerBackup() {

        // Init the app
        initApp();


        // Set the GUI part
        showUi();
    }



    private void initApp() {

        // +--------------------------------------------------------
        // |  Set the 'look-and-feel' for the UI.
        // +--------------------------------------------------------
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e) {
            logger.error("Cannot set the look-and-feel");
        }



        // +--------------------------------------------------------
        // |  Verify that the profiles folder exists.
        // +--------------------------------------------------------
        logger.debug("Verify that the profiles folder exists.");
        profilesDir = new File(TextsEnum.FOLDER_NAME_PROFILES.getText());
        if ( ! profilesDir.exists()) {

            if ( ! profilesDir.mkdirs()) {
                Messages.exitWithError("Cannot create the profiles dir.", true);
            }
        }
        logger.debug("ok.");
        logger.debug("");



        // +--------------------------------------------------------
        // |  Get the profiles list.
        // +--------------------------------------------------------
        logger.debug("Get the profiles list.");
        profileNames = getProfileName();
        logger.debug("ok.");
        logger.debug("");
    }



    private void showUi() {

        // Init the zzProfile names.
        List<String> modifiedProfileNames = getProfileName();

        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle(TextsEnum.T_MAIN_FRAME_TITLE.getText());
        this.setBounds(400, 100, 600, 300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        // -------------------------------------------
        // Set the profiles part.
        // -------------------------------------------
        JLabel  lProfileName   = new JLabel (TextsEnum.L_PROFILE_NAME.getText());
        JButton bProfileSelect = new JButton(TextsEnum.L_PROFILE_SELECT.getText());

        bProfileDelete = new JButton(TextsEnum.L_PROFILE_DELETE.getText());
        bProfileBackup = new JButton(TextsEnum.L_PROFILE_RUN_BACKUP.getText());
        ddProfileNames = new JComboBox<>();
        bProfileBackup.setEnabled(false);
        bProfileDelete.setEnabled(false);



        // -------------------------------------------
        // Set the GUI components on the form.
        // -------------------------------------------
        lProfileName.setBounds  ( 20, 20, 100, 20);
        ddProfileNames.setBounds(130, 20, 220, 20);
        bProfileSelect.setBounds(370, 20, 100, 20);
        bProfileBackup.setBounds( 20, 60, 120, 30);
        bProfileDelete.setBounds(150, 60, 120, 30);



        // -------------------------------------------
        // Add the GUI components to the form
        // -------------------------------------------
        Container container = this.getContentPane();
        container.add(lProfileName);
        container.add(bProfileBackup);
        container.add(bProfileSelect);
        container.add(bProfileDelete);
        container.add(ddProfileNames);



        // -------------------------------------------
        // Set initial data into the drop down.
        // -------------------------------------------
        for (String s : modifiedProfileNames) {
            ddProfileNames.addItem(s);
        }



        // -------------------------------------------
        // Add the action listeners
        // -------------------------------------------
        bProfileSelect.addActionListener(e->actionPerformedProfileSelect());

        bProfileDelete.addActionListener(e->actionPerformedProfileDelete(
                (String)ddProfileNames.getSelectedItem())
        );

        bProfileBackup.addActionListener(e->actionPerformedRunBackup(
                (String)ddProfileNames.getSelectedItem())
        );
    }



    private void actionPerformedProfileDelete(String profileName) {

        if (profileName == null || profileName.equals(TextsEnum.T_CREATE_NEW.getText())) {
            Messages.showMessage(Messages.MESSAGE_ERR, TextsEnum.T_NO_PROFILE_YET.getText());
            return;
        }


        // Set the zzProfile's file full path.
        File profileFile = new File (String.format("%s%s%s.properties",
                TextsEnum.FOLDER_NAME_PROFILES.getText(),
                File.separator,
                profileName
        ));


        // Verify if it exists.
        if (profileFile.exists()) {
            if (profileFile.delete()) {
                Messages.showMessage(
                        Messages.MESSAGE_INF,
                        "The zzProfile had been deleted successfully."
                );
            }
            else {
                Messages.showMessage(
                        Messages.MESSAGE_ERR,
                        "Could not delete the zzProfile's file."
                );
            }
        }
        else {
            Messages.showMessage(
                    Messages.MESSAGE_ERR,
                    "The zzProfile's file does not exist."
            );
        }


        // Refresh the zzProfile names.
        profileNames = getProfileName();
        ddProfileNames.removeAllItems();
        for (String name : profileNames) {
            ddProfileNames.addItem(name);
        }


        // Refresh the frame's title.
        this.setTitle(TextsEnum.T_MAIN_FRAME_TITLE.getText());


        // Disable the 'run b1' and 'delete' buttons.
        bProfileDelete.setEnabled(false);
        bProfileBackup.setEnabled(false);
    }



    private void actionPerformedProfileSelect() {

        String readProfileName = (String) ddProfileNames.getSelectedItem();

        if (readProfileName == null) {
            Messages.exitWithError(TextsEnum.M_INTERNAL_ERROR.getText(), true);
        }
        else {
            if (readProfileName.equals(TextsEnum.T_CREATE_NEW.getText())) {
                this.setTitle(TextsEnum.T_MAIN_FRAME_TITLE.getText());
                bProfileBackup.setEnabled(false);
                bProfileDelete.setEnabled(false);
                createNewProfile();
                profileNames = getProfileName();
                return;
            }
        }

        logger.info("Selected zzProfile = " + readProfileName);
        profileSelected = true;


        // Set the zzProfile name in the title.
        this.setTitle(String.format("%s  Active zzProfile : [%s]",
                TextsEnum.T_MAIN_FRAME_TITLE.getText(),
                readProfileName
        ));
        bProfileBackup.setEnabled(true);
        bProfileDelete.setEnabled(true);
    }



    private void actionPerformedRunBackup(String profileName) {

        if (profileSelected) {

            BackupProfile backupProfile = new BackupProfile(profileName);
            try {
                backupProfile.parse();
            }
            catch (IOException e) {
                Messages.showMessage(Messages.MESSAGE_ERR, "Cannot parse the zzProfile's file.");
                return;
            }


            // Run the b1.
            BackupRunner backupRunner = new BackupRunner();
            try {
                backupRunner.performBackup(backupProfile);
            }
            catch (IOException e) {
                logger.error(e.getStackTrace());
                e.printStackTrace();
                Messages.exitWithError(e.getMessage(), true);
            }
            Messages.showMessage(Messages.MESSAGE_INF, "The b1 ended successfully.");
        }
        else {
            Messages.showMessage(Messages.MESSAGE_ERR, TextsEnum.T_NO_PROFILE_YET.getText());
        }
    }



    private void createNewProfile() {

        JFrame fNewProfile = new JFrame(TextsEnum.T_PROFILE_CREATE.getText());
        fNewProfile.setLayout(null);
        fNewProfile.setResizable(false);
        fNewProfile.setLocationRelativeTo(null);
        fNewProfile.setBounds(500, 200, 600, 450);
        fNewProfile.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLabel lProfileName  = new JLabel(TextsEnum.L_PROFILE_NAME.getText());
        JLabel lTargetFolder = new JLabel(TextsEnum.L_TARGET_FOLDER.getText());

        JTextField tfProfileName  = new JTextField();
        JTextField tfTargetFolder = new JTextField();

        JButton bSelectTargetFolder  = new JButton(TextsEnum.L_SELECT_FOLDER.getText());
        JButton bAddItem = new JButton(TextsEnum.L_ADD_ITEM.getText());
        JButton bCancel  = new JButton(TextsEnum.L_CANCEL.getText());
        JButton bConfirm = new JButton(TextsEnum.L_CONFIRM.getText());

        JTextArea taItemsToBackup = new JTextArea(15, 70);
        taItemsToBackup.setBackground(Color.LIGHT_GRAY);
        taItemsToBackup.setEditable(false);
        taItemsToBackup.setLineWrap(true);
        taItemsToBackup.setWrapStyleWord(false);
        JScrollPane scroll = new JScrollPane (taItemsToBackup);
        scroll.setVerticalScrollBarPolicy  (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);


        lProfileName.setBounds (20, 20, 100, 20);
        lTargetFolder.setBounds(20, 50, 100, 20);

        tfProfileName.setBounds (130, 20, 100, 20);
        tfTargetFolder.setBounds(130, 50, 300, 20);

        bSelectTargetFolder.setBounds ( 440, 50, 120, 20);
        bAddItem.setBounds( 20,  90, 100, 25);
        bCancel.setBounds (230, 340, 100, 25);
        bConfirm.setBounds(100, 340, 100, 25);

        scroll.setBounds(20, 125, 400, 200);



        fNewProfile.getContentPane().add(lProfileName);
        fNewProfile.getContentPane().add(lTargetFolder);
        fNewProfile.getContentPane().add(tfProfileName);
        fNewProfile.getContentPane().add(tfTargetFolder);
        fNewProfile.getContentPane().add(bSelectTargetFolder);
        fNewProfile.getContentPane().add(bAddItem);
        fNewProfile.getContentPane().add(bCancel);
        fNewProfile.getContentPane().add(bConfirm);
        fNewProfile.getContentPane().add(scroll, BorderLayout.CENTER);


        // Action listeners
        bCancel.addActionListener(e->fNewProfile.dispose());


        bSelectTargetFolder.addActionListener(e->{
            String targetFolder = selectTargetFolder();
            if (targetFolder != null) {
                tfTargetFolder.setText(targetFolder);
            }
        });


        bConfirm.addActionListener(e->{

            // Read and validate the form.
            String readProfileName   = tfProfileName.getText();
            String readTargetFolder  = tfTargetFolder.getText();
            String readItemsToBackup = taItemsToBackup.getText();

            if (readProfileName == null || readProfileName.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, TextsEnum.T_NO_PROFILE_YET.getText());
                return;
            }


            if (readTargetFolder == null || readTargetFolder.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "No target folder defined.");
                return;
            }


            if (readItemsToBackup == null || readItemsToBackup.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "No items to b1 defined.");
                return;
            }


            // Create a new zzProfile.
            try {
                createProfile(readProfileName, readTargetFolder, readItemsToBackup);
            }
            catch (IOException ioe) {
                Messages.showMessage(Messages.MESSAGE_ERR, ioe.getMessage());
                return;
            }


            // Send a confirmation message to the user.
            Messages.showMessage(
                    Messages.MESSAGE_INF,
                    String.format ("The zzProfile '%s' created successfully.", readProfileName
            ));


            // Close the new zzProfile's form
            fNewProfile.dispose();


            // Set the new zzProfile to the list.
            ddProfileNames.removeAllItems();

            List<String> modifiedProfileNames = getProfileName();
            for (String s : modifiedProfileNames) {
                ddProfileNames.addItem(s);
            }
        });


        bAddItem.addActionListener(e->{

            String currentItem = getItem();

            if (currentItem != null) {

                // Verify that it is not duplicated.
                String[] currentItems = taItemsToBackup.getText().split("\n");
                for (String item : currentItems) {
                    if (item.equals(currentItem)) {
                        Messages.showMessage(Messages.MESSAGE_ERR, "Item already exists.");
                        return;
                    }
                }

                taItemsToBackup.append(currentItem);
                taItemsToBackup.append("\n");
            }
        });


        fNewProfile.setVisible(true);
    }



    private String selectTargetFolder() {

        String targetFolder = null;

        JFileChooser jfc = new JFileChooser(
                FileSystemView.getFileSystemView().getHomeDirectory()
        );
        jfc.setDialogTitle("Choose the target folder");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            targetFolder = selectedFile.getAbsolutePath();
        }

        return targetFolder;
    }



    private String getItem() {

        String itemToBackup = null;

        JFileChooser jfc = new JFileChooser(
                FileSystemView.getFileSystemView().getHomeDirectory()
        );
        jfc.setDialogTitle("Choose item to b1.");
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            itemToBackup = selectedFile.getAbsolutePath();
        }

        return itemToBackup;
    }



    private void createProfile(String theProfileName, String theTargetFolder, String theItems) throws IOException {

        BackupProfile backupProfile = new BackupProfile(
                theProfileName,
                theTargetFolder,
                Arrays.asList(theItems.split("\n"))
        );

        try {
            backupProfile.generateProfileFile();
        }
        catch (IOException e) {
            throw new IOException("Cannot create the zzProfile: " + e.getMessage());
        }
    }



    private List<String> getProfileName() {

        List<String> list = new ArrayList<>();

        File[] filesList = profilesDir.listFiles();

        if (filesList != null) {

            for (File f : filesList) {

                if (f.getName().endsWith(".properties")) {
                    list.add(f.getName().replace(".properties", ""));
                }
            }

            // Add the 'create new' option
            list.add(TextsEnum.T_CREATE_NEW.getText());
        }

        return list;
    }
}





