package com.ygsoft.apps;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;


public class DirCompareUi {

    private static final String version = "0.0.1";
    private JButton    bSourceFolder, bTargetFolder, bStartCompare;
    private JTextArea  taResults          = new JTextArea();
    private JTextField tfSourceFolder     = new JTextField();
    private JTextField tfTargetFolder     = new JTextField();
    private List<File> sourceFolderItems  = new ArrayList<>();
    private List<DirStatus> comparedItems = new ArrayList<>();
    private File fTargetFolder;
    private int sourcePathLength = 0;



    DirCompareUi() {}



    void setAndShow() {

        String separator = generateSeparator();

        JFrame fMain = new JFrame();
        fMain.setTitle("Folders compare. Version: " + version);
        fMain.setBounds(200, 200, 600, 500);
        fMain.setLayout(null);
        fMain.setResizable(false);
        fMain.setLocationRelativeTo(null);
        fMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        // Get the folders for comparison.
        JLabel lSourceFolder = new JLabel("Source folder :");
        JLabel lTargetFolder = new JLabel("Target folder :");
        JLabel lSeparator = new JLabel(separator);
        lSeparator.setBackground(Color.BLUE);


        bSourceFolder = new JButton("Select ...");
        bTargetFolder = new JButton("Select ...");
        bStartCompare = new JButton("Compare !");


        JScrollPane scrollPane = new JScrollPane(taResults);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        // Set the UI components position.
        lSourceFolder.setBounds(20, 20, 100, 20);
        lTargetFolder.setBounds(20, 50, 100, 20);
        lSeparator.setBounds(20, 80, 600, 20);
        tfSourceFolder.setBounds(120, 20, 300, 20);
        tfTargetFolder.setBounds(120, 50, 300, 20);
        bSourceFolder.setBounds(430, 20, 100, 20);
        bTargetFolder.setBounds(430, 50, 100, 20);
        bStartCompare.setBounds(250, 420, 100, 30);
        scrollPane.setBounds(20, 120, 545, 260);


        // ************************************
        tfSourceFolder.setText("C:\\YG\\test\\source");
        tfTargetFolder.setText("C:\\YG\\test\\target");
        // ************************************

        // Set the UI components on the frame.
        Container container = fMain.getContentPane();

        container.add(lSourceFolder);
        container.add(lTargetFolder);
        container.add(lSeparator);
        container.add(bSourceFolder);
        container.add(bTargetFolder);
        container.add(tfSourceFolder);
        container.add(tfTargetFolder);
        container.add(bStartCompare);
        container.add(scrollPane);

        fMain.setVisible(true);

        bSourceFolder.addActionListener(e->{

            File rootFolder = getSelectedFolder("source");

            if (rootFolder != null) {
                tfSourceFolder.setText(rootFolder.getAbsolutePath());
            }
        });


        bTargetFolder.addActionListener(e->{

            File rootFolder = getSelectedFolder("target");

            if (rootFolder != null) {
                tfTargetFolder.setText(rootFolder.getAbsolutePath());
            }
        });


        bStartCompare.addActionListener(e-> actionStartCompare());
    }



    private void actionStartCompare() {

        // Read the source and target folders.
        String sourceFolder = tfSourceFolder.getText();
        String targetFolder = tfTargetFolder.getText();


        // Verify source folder.
        if (sourceFolder == null || sourceFolder.isEmpty()) {
            Messages.showMessage(Messages.MESSAGE_ERR, "No source folder provided.");
            return;
        }
        File fSourceFolder = new File (sourceFolder);
        if ( ! fSourceFolder.exists()) {
            Messages.showMessage(Messages.MESSAGE_ERR, "The source folder does not exist.");
            return;
        }


        // Verify target folder.
        if (targetFolder == null || targetFolder.isEmpty()) {
            Messages.showMessage(Messages.MESSAGE_ERR, "No target folder provided.");
            return;
        }
        fTargetFolder = new File (targetFolder);
        if ( ! fTargetFolder.exists()) {
            Messages.showMessage(Messages.MESSAGE_ERR, "The target folder does not exist.");
            return;
        }



        // Calculate the length of the source folder.
        sourcePathLength = sourceFolder.length();


        // Verify that the text area is empty from previous text.
        taResults.setText("");


        // Disable the buttons.
        bSourceFolder.setEnabled(false);
        bTargetFolder.setEnabled(false);
        bStartCompare.setEnabled(false);


        // Insert a message to the user.
        taResults.append("Comparison started.");
        taResults.append("\n");
        taResults.append("Source folder : " + sourceFolder);
        taResults.append("\n");
        taResults.append("Target folder : " + targetFolder);
        taResults.append("\n");


        // Get the source folder's content.
        getSourceFolderItems(fSourceFolder);


        // Start the comparison.
        performComparison();


        // Insert the comparison data into the UI.
        taResults.append("\n");
        taResults.append("\n");
        if (comparedItems.isEmpty()) {
            taResults.append("The folders are same.\n");
        }
        else {
            for (DirStatus ds : comparedItems) {
                taResults.append(ds.getStatus());
                taResults.append("\n");
            }
        }
    }



    private void getSourceFolderItems(File sourceRootFolder) {

        if (sourceRootFolder == null) {
            return;
        }

        File[] subItems = sourceRootFolder.listFiles();
        if (subItems == null) {
            return;
        }

        for (File subItem : subItems) {
            getTree(subItem);
        }
    }



    private void getTree(File currentItem) {

        sourceFolderItems.add(currentItem);

        if (currentItem.isDirectory()) {
            File[] subItems = currentItem.listFiles();
            if (subItems == null) {
                return;
            }

            for (File subItem : subItems) {
                getTree(subItem);
            }
        }
    }



    private void performComparison() {

        DirStatus dirStatus = new DirStatus();

        // Sort the target files' list.
        Collections.sort(sourceFolderItems);

        // walk over the list.
        String lastInspectedItem = "q1w2e3";

        boolean neededToBeCreated = false;

        for (File currentFile : sourceFolderItems) {

            File targetFile;

            String sourceRelativePath = currentFile.getAbsolutePath().substring(sourcePathLength);


            targetFile = new File(String.format(
                    "%s%s%s",
                    fTargetFolder.getAbsolutePath(),
                    File.separatorChar,
                    sourceRelativePath
            ));


            if (currentFile.isDirectory()) {

                boolean isContains = currentFile.getAbsolutePath().contains(lastInspectedItem);

                if (neededToBeCreated || ! isContains) {

                    lastInspectedItem = currentFile.getAbsolutePath();

                    if (targetFile.exists()) {
                        neededToBeCreated = false;
                    }
                    else {
                        neededToBeCreated = true;
                        System.out.println("Need to create : " + targetFile.getAbsolutePath());
                    }
                }
            }
            else if (currentFile.isFile()) {

                if (targetFile.exists()) {
                    String sourceFileChecksum = Checksums.calculateChecksum(currentFile);
                    String targetFileChecksum = Checksums.calculateChecksum(targetFile);
                    if (sourceFileChecksum == null) {
                        System.err.println(String.format(
                                "Cannot calculate the checksum for source file '%s'.",
                                currentFile.getAbsolutePath())
                        );
                        continue;
                    }
                    if (targetFileChecksum == null) {
                        System.err.println(String.format(
                                "Cannot calculate the checksum for target file '%s'.",
                                targetFile.getAbsolutePath())
                        );
                        continue;
                    }


                    if ( ! sourceFileChecksum.equals(targetFileChecksum)) {
                        dirStatus.setCopyExisting(currentFile);
                        comparedItems.add(dirStatus);
                    }
                }
                else {
                    dirStatus.setCopyNew(currentFile);
                    comparedItems.add(dirStatus);
                }
            }
            else {
                System.err.println("WTF ??");
            }
        }
    }



    private File getSelectedFolder(String folderType) {

        File selectedFolder = null;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setDialogTitle(String.format("Choose the %s folder.", folderType));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            selectedFolder = fileChooser.getSelectedFile();
        }

        return selectedFolder;
    }



    private String generateSeparator() {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 68; i++) {
            stringBuilder.append("=");
        }

        return stringBuilder.toString();
    }
}

