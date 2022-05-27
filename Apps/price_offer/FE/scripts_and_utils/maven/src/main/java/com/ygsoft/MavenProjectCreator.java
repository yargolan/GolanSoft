package com.ygsoft;

import java.io.*;
import javax.swing.*;



public class MavenProjectCreator {

    File   src, res, test;
    String readGroupId, readVersion, readArtifactId, readPackageName;



    public MavenProjectCreator(){}



    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e) {
            System.err.println("Cannot set the LNF.");
        }


        MavenProjectCreator mavenProjectCreator = new MavenProjectCreator();
        mavenProjectCreator.create();
    }



    private static String ucFirst (String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }



    private void generatePom () throws IOException {

        FileWriter writer = new FileWriter(String.format("%s%s%s", readArtifactId, File.separatorChar, "pom.xml"));

        // Read the template pom.
        InputStream in = getClass().getResourceAsStream("/template.xml");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String currentLine;

        while (((currentLine = reader.readLine()) != null)) {

            currentLine = currentLine.replace("__GID__", readGroupId);
            currentLine = currentLine.replace("__AID__", readArtifactId);
            currentLine = currentLine.replace("__VER__", readVersion);

            writer.write(currentLine + System.lineSeparator());
        }

        writer.close();
    }



    private void generateClass () throws IOException {

        String generatedClassName = ucFirst (src.getName());

        String generatedClass = String.format("%s%s%s.%s", src, File.separatorChar, generatedClassName, "java");
        FileWriter writer = new FileWriter(generatedClass);

        // Read the template pom.
        InputStream in = getClass().getResourceAsStream("/template.java");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        // Verify that the class name starts with a capital letter
        String className = ucFirst(readArtifactId);

        String currentLine;

        while (((currentLine = reader.readLine()) != null)) {

            currentLine = currentLine.replace("__PKG__", readPackageName.toLowerCase());
            currentLine = currentLine.replace("__CLS__", className);

            writer.write(currentLine + System.lineSeparator());
        }

        writer.close();
    }



    private void createTreeStructure() throws IOException {


        String groupIdWithSlashes = readGroupId.replaceAll("\\.", File.separator);

        res  = new File (String.format("%s%s%s",     readArtifactId, File.separatorChar, "src/main/resources"));
        src  = new File (String.format("%s%s%s%s%s", readArtifactId, File.separatorChar, "src/main/java/", File.separatorChar, groupIdWithSlashes));
        test = new File (String.format("%s%s%s%s%s", readArtifactId, File.separatorChar, "src/test/java/", File.separatorChar, groupIdWithSlashes));

        if ( ! res.mkdirs()) {
            throw new IOException(String.format("Cannot create the path '%s'", res));
        }

        if ( ! src.mkdirs()) {
            throw new IOException(String.format("Cannot create the path '%s'", src));
        }

        if ( ! test.mkdirs()) {
            throw new IOException(String.format("Cannot create the path '%s'", test));
        }
    }



    private void create() {

        JFrame f = new JFrame("Create a new Maven project");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setBounds(50, 100, 400, 300);
        f.setResizable(false);
        f.setLocationRelativeTo(null);

        JLabel lVersion     = new JLabel("Version");
        JLabel lGroupId     = new JLabel("Group ID");
        JLabel lArtifactId  = new JLabel("Artifact ID");

        JTextField tfVersion     = new JTextField();
        JTextField tfGroupId     = new JTextField();
        JTextField tfArtifactId  = new JTextField();

        JButton bGenerate = new JButton("Generate");


        lGroupId.setBounds    (20, 20,  100, 20);
        lVersion.setBounds    (20, 80,  100, 20);
        lArtifactId.setBounds (20, 50,  100, 20);

        tfGroupId.setBounds    (120, 20,  200, 20);
        tfVersion.setBounds    (120, 80,  200, 20);
        tfArtifactId.setBounds (120, 50,  200, 20);

        bGenerate.setBounds    (140, 180, 150, 40);


        f.getContentPane().add(lGroupId);
        f.getContentPane().add(lVersion);
        f.getContentPane().add(lArtifactId);

        f.getContentPane().add(tfGroupId);
        f.getContentPane().add(tfVersion);
        f.getContentPane().add(tfArtifactId);

        f.getContentPane().add(bGenerate);

        f.setVisible(true);

        bGenerate.addActionListener(e -> {

            // Read the form
            readGroupId     = tfGroupId.getText();
            readVersion     = tfVersion.getText();
            readArtifactId  = tfArtifactId.getText();

            // Validate.
            if (readGroupId == null || readGroupId.isEmpty()) {
                JOptionPane.showConfirmDialog(null, "No group ID provided.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                return;
            }


            if (readArtifactId == null || readArtifactId.isEmpty()) {
                JOptionPane.showConfirmDialog(null, "No artifact ID provided.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                return;
            }


            if (readVersion == null || readVersion.isEmpty()) {
                JOptionPane.showConfirmDialog(null, "No version ID provided.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                return;
            }


            // Set the package name.
            readPackageName = String.format("%s%s%s", readGroupId, ".", readArtifactId).toLowerCase();


            // Generate the tree structure
            try {

                // Generate the tree structure.
                createTreeStructure();


                // Write the generated pom file.
                generatePom();


                // Generate the first class from template.
                generateClass();
            }
            catch (IOException ioe) {
                JOptionPane.showConfirmDialog(
                        null,
                        "Cannot create the tree structure:\n" + ioe.getMessage(),
                        "Error",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE
                );
            }

            f.dispose();
        });
    }
}













