package com.ygsoft.apps.mp3tags;

import javax.swing.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;


public class FileRename {

    private static final Logger logger = LogManager.getLogger(Mp3FilesTags.class);
    private final List<String> list = new ArrayList<>();
    private int currentFileIndex = 0;
    private final JTextField tfCurrentName = new JTextField();
    private final JTextField tfSuggestedName = new JTextField();



    private FileRename() {
    }


    public static void main(String[] args) {

        // +--------------------------------------------------------
        // |  Set the 'look-and-feel' for the UI.
        // +--------------------------------------------------------
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e) {
            logger.error("Cannot set the look-and-feel");
        }

        FileRename fileRename = new FileRename();
        fileRename.runApp();
    }


    private void runApp() {

        JFrame fMain = new JFrame("Rename mp3 files.");
        fMain.setLayout(null);
        fMain.setBounds(200, 100, 800, 250);
        fMain.setResizable(false);
        fMain.setLocationRelativeTo(null);
        fMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JLabel lRootFolder = new JLabel("Root dir");
        JLabel lCurrentName = new JLabel("Current name");
        JLabel lSuggestedName = new JLabel("Suggested name");

        JTextField tfRootDir = new JTextField();

        JButton bNext = new JButton("Next");
        JButton bPrev = new JButton("Previous");
        JButton bChoose = new JButton("Choose");
        JButton bUpdate = new JButton("Update !");
        JButton bBrowse = new JButton("Browse ...");


        // Set on the form
        lRootFolder.setBounds(20, 20, 120, 20);
        lCurrentName.setBounds(20, 50, 120, 20);
        lSuggestedName.setBounds(20, 80, 120, 20);

        tfRootDir.setBounds(130, 20, 400, 20);
        tfCurrentName.setBounds(130, 50, 600, 20);
        tfSuggestedName.setBounds(130, 80, 600, 20);

        bPrev.setBounds  (130, 120, 100, 40);
        bNext.setBounds  (240, 120, 100, 40);
        bUpdate.setBounds(380, 120, 100, 40);
        bBrowse.setBounds(540, 20, 90, 20);
        bChoose.setBounds(640, 20, 90, 20);


        Container container = fMain.getContentPane();
        container.add(lRootFolder);
        container.add(lCurrentName);
        container.add(lSuggestedName);
        container.add(tfRootDir);
        container.add(tfCurrentName);
        container.add(tfSuggestedName);
        container.add(bNext);
        container.add(bPrev);
        container.add(bUpdate);
        container.add(bChoose);
        container.add(bBrowse);

        /////////////////////////////////
        // TODO: Remove after debug
        tfRootDir.setText("/Users/yg356h/Music");
        /////////////////////////////////



        // +---------------------------------------
        // | Add action listeners to the buttons.
        // +---------------------------------------
        bBrowse.addActionListener(e -> {

            String readRootDir = tfRootDir.getText();

            String rootFolder = (readRootDir == null || readRootDir.isEmpty()) ?
                    System.getProperty("user.home") :
                    readRootDir
            ;

            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setCurrentDirectory(new File(rootFolder));

            fileChooser.setDialogTitle("Choose MP3 root folder");

            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                String chosenDir = fileChooser.getSelectedFile().getAbsolutePath();
                tfRootDir.setText(chosenDir);
            }
            else {
                tfRootDir.setText("");
            }
        });



        bChoose.addActionListener(e->{
            bBrowse.setEnabled(false);
            tfRootDir.setEditable(false);

            String readRootDir = tfRootDir.getText();

            File rootDir = new File(readRootDir);

            if (rootDir.exists() && rootDir.isDirectory()) {

                // Get all files.
                getAllFiles(rootDir);

                if (list.size() > 1) {
                    setFileDetails(0);
                }
            }
        });



        bNext.addActionListener(e->{

            // Verify files list length
            if (currentFileIndex < list.size() - 1) {
                currentFileIndex++;
                setFileDetails(currentFileIndex);
            }
        });



        bPrev.addActionListener(e->{

            // Verify files list length
            if (currentFileIndex > 0) {
                currentFileIndex--;
                setFileDetails(currentFileIndex);
            }
        });



        bUpdate.addActionListener(e->{
            String readCurrentFileName   = tfCurrentName.getText();
            String readSuggestedFileName = tfSuggestedName.getText();

            if (readCurrentFileName == null || readCurrentFileName.isEmpty()) {
                return;
            }

            if (readSuggestedFileName == null || readSuggestedFileName.isEmpty()) {
                return;
            }


            File currentFileName   = new File(readCurrentFileName);
            File suggestedFileName = new File(readSuggestedFileName);


            if (currentFileName.renameTo(suggestedFileName)) {

                // Set the new name in the GUI
                tfCurrentName.setText(readSuggestedFileName);

                // Update the list.
                list.set(currentFileIndex, readSuggestedFileName);
            }
            else {
                JOptionPane.showMessageDialog(
                        null,
                        "Cannot rename.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });



        fMain.setVisible(true);
    }



    private void setFileDetails(int index) {

        String currentFile = list.get(index);

        String suggestedName = getSuggestedName(currentFile);

        tfCurrentName.setText(currentFile);
        tfSuggestedName.setText(suggestedName);
    }



    private void getAllFiles(File rootDir) {

        File[] subItems = rootDir.listFiles();

        if (subItems != null) {

            for (File subItem : subItems) {
                if (subItem.isDirectory()) {
                    getAllFiles(subItem);
                }
                else {
                    if (subItem.getAbsolutePath().endsWith(".mp3")) {
                        list.add(subItem.getAbsolutePath());
                    }
                }
            }
        }
    }



    private String getSuggestedName(String fileFullPath) {

        File currentItem = new File(fileFullPath);

        File parentDir = currentItem.getParentFile();

        String fileName = currentItem.getName();

        // Lower case
        fileName = fileName.toLowerCase();

        // Remove white spaces
        fileName = fileName.replaceAll("\\s+", "_");

        return String.format("%s%s%s",
                parentDir.getAbsolutePath(),
                File.separator,
                fileName
        );
    }
}



















