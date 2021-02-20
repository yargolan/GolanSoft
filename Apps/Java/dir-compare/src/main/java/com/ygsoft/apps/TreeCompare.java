package com.ygsoft.apps;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

import java.security.MessageDigest;

import javax.swing.*;

import java.awt.*;

import java.io.File;

import java.util.*;
import java.util.List;




public class TreeCompare extends JFrame {

    private static final String version = "1.1";
    private List<DirStatus> comparedItems = new ArrayList<>();
    private JTextArea taResults = new JTextArea();


    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.err.println("Cannot set the look-and-feel ...");
        }


        SwingUtilities.invokeLater(() -> {
            TreeCompare treeCompare = new TreeCompare();
            treeCompare.setVisible(true);
        });
    }



    private TreeCompare() {

        String separator = generateSeparator();

        this.setTitle("Folders compare. Version: " + version);
        this.setBounds(200, 200, 600, 500);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        // Get the folders for comparison.
        JLabel lSourceFolder = new JLabel("Source folder :");
        JLabel lTargetFolder = new JLabel("Target folder :");
        JLabel lSeparator    = new JLabel(separator);
        lSeparator.setBackground(Color.BLUE);

        JTextField tfSourceFolder = new JTextField();
        JTextField tfTargetFolder = new JTextField();

        JButton bSourceFolder = new JButton("Select ...");
        JButton bTargetFolder = new JButton("Select ...");
        JButton bStartCompare = new JButton("Compare !");

        JScrollPane scrollPane = new JScrollPane(taResults);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy  (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);



        // Set the UI components position.
        lSourceFolder.setBounds ( 20,  20, 100, 20);
        lTargetFolder.setBounds ( 20,  50, 100, 20);
        lSeparator.setBounds    ( 20,  80, 600, 20);
        tfSourceFolder.setBounds(120,  20, 300, 20);
        tfTargetFolder.setBounds(120,  50, 300, 20);
        bSourceFolder.setBounds (430,  20, 100, 20);
        bTargetFolder.setBounds (430,  50, 100, 20);
        bStartCompare.setBounds (250, 420, 100, 30);
        scrollPane.setBounds    ( 20, 120, 545, 260);



        // Set the UI components on the frame.
        Container container = this.getContentPane();

        container.add(lSourceFolder);
        container.add(lTargetFolder);
        container.add(lSeparator);
        container.add(bSourceFolder);
        container.add(bTargetFolder);
        container.add(tfSourceFolder);
        container.add(tfTargetFolder);
        container.add(bStartCompare);
        container.add(scrollPane);

        this.setVisible(true);

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



        bStartCompare.addActionListener(e->{

            // Read the source and target folders.
            String sourceFolder = tfSourceFolder.getText();
            String targetFolder = tfTargetFolder.getText();

            if (sourceFolder == null || sourceFolder.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "No source folder provided.");
                return;
            }

            if (targetFolder == null || targetFolder.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "No source folder provided.");
                return;
            }

            // Disable the 'compare' button.
            bStartCompare.setEnabled(false);


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
            taResults.append("\n");


            try {
                treeCompare (new File (sourceFolder), new File(targetFolder));
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }


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
        });
    }



    private void treeCompare (File sourceFolder, File targetFolder) throws IOException {

        if (sourceFolder == null || targetFolder == null) {
            return;
        }

        File[] sourceFiles = sourceFolder.listFiles();
        File[] targetFiles = targetFolder.listFiles();
        if (sourceFiles == null || targetFiles == null) {
            return;
        }



        // Sort the lists.
        Arrays.sort(sourceFiles);
        Arrays.sort(targetFiles);


        HashMap<String, File> map1;

        if(sourceFiles.length < targetFiles.length)
        {
            map1 = new HashMap<>();
            for (File file : sourceFiles) {
                map1.put(file.getName(), file);
            }

            compareNow(targetFiles, map1);
        }
        else
        {
            map1 = new HashMap<>();
            for (File file : targetFiles) {
                map1.put(file.getName(), file);
            }
            compareNow(sourceFiles, map1);
        }
    }



    private void compareNow(File[] fileArr, HashMap<String, File> map) throws IOException {
        for (File file : fileArr) {
            String fName = file.getName();
            File fComp = map.get(fName);
            map.remove(fName);
            if (fComp != null) {
                if (fComp.isDirectory()) {
                    treeCompare(file, fComp);
                }
                else {
                    String cSum1 = checksum(file);
                    String cSum2 = checksum(fComp);
                    if (cSum1 != null && cSum2 != null) {
                        if (!cSum1.equals(cSum2)) {
                            DirStatus dirStatus = new DirStatus();
                            dirStatus.setDifferent(file, fComp);
                            comparedItems.add(dirStatus);
                        }
                    }
                }
            }
            else {
                if (file.isDirectory()) {
                    traverseDirectory(file);
                }
                else {
                    DirStatus dirStatus = new DirStatus();
                    dirStatus.setOnlyAt(file);
                    comparedItems.add(dirStatus);
                }
            }
        }
        Set<String> set = map.keySet();
        for (String n : set) {
            File fileFrmMap = map.get(n);
            map.remove(n);
            if (fileFrmMap.isDirectory()) {
                traverseDirectory(fileFrmMap);
            }
            else {
                DirStatus dirStatus = new DirStatus();
                dirStatus.setOnlyAt(fileFrmMap);
                comparedItems.add(dirStatus);
            }
        }
    }



    private String checksum(File file) {

        try {
            InputStream fis = new FileInputStream(file);

            byte[] buffer = new byte[8192]; // was 1024 originally.

            MessageDigest complete = MessageDigest.getInstance("MD5");

            int numRead;

            do {
                numRead = fis.read(buffer);
                if (numRead > 0) {
                    complete.update(buffer, 0, numRead);
                }
            }
            while (numRead != -1);

            fis.close();

            return Arrays.toString(complete.digest());
        }
        catch (Exception e) {
            return null;
        }
    }



    private void traverseDirectory(File dir) {
        File[] list = dir.listFiles();
        if (list == null) {
            System.err.println("NULL");
            return;
        }


        for (File file : list) {
            if (file.isDirectory()) {
                traverseDirectory(file);
            }
            else {
                System.out.println(file.getName() + "\t\t" + "only in " + file.getParent());
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













