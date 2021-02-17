package com.ygsoft.apps.backup;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;



public class UiFramesGenerator {

    static final int FRAME_MAIN        = 1;
    static final int FRAME_NEW_PROFILE = 2;

    static final int ALL_ITEMS    = 10;
    static final int FOLDERS_ONLY = 20;



    public UiFramesGenerator() {}



    JFrame generate(int frameType) {

        // Set the frame.
        JFrame f = new JFrame();


        // Set the frame's icon, get it from the resource folder.
        URL url = getClass().getResource("/icon.png");
        ImageIcon imageIcon = new ImageIcon(url);


        // Generic frame settings
        f.setLayout(null);
        f.setIconImage(imageIcon.getImage());
        f.setResizable(false);
        f.setUndecorated(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        InputStream stream = UiFramesGenerator.class.getResourceAsStream( "/icon.png" );
        try {
            BufferedImage image = ImageIO.read( stream );
            f.setIconImage(image);
        }
        catch (IOException e) {
            System.err.println("Cannot load the icon.");
        }

        switch (frameType) {

            case FRAME_MAIN:
                f.setSize(600, 600);
                f.setTitle(HardCoded.F_MAIN.getText());
                f.setLocation(400, 200);
                f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                break;

            case FRAME_NEW_PROFILE:
                f.setSize(700, 450);
                f.setTitle(HardCoded.F_CREATE_NEW_PROFILE.getText());
                f.setLocation(400, 300);
                f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                break;

            default:
                break;
        }

        return f;
    }



    private File selectItem (int selectionType, String startingFolder){

        File selectedItem = new File(".");

        JFileChooser jfc = new JFileChooser(new File(startingFolder));

        if (selectionType == FOLDERS_ONLY) {
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jfc.setDialogTitle("Choose folder :");
        }
        else {
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jfc.setDialogTitle("Choose item :");
        }

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedItem = jfc.getSelectedFile();
        }

        return selectedItem;
    }



    public String selectFolder(String startsFrom) {
        return selectItem(FOLDERS_ONLY, startsFrom).getAbsolutePath();
    }



    public String selectFolderOrFile(String startsFrom) {
        return selectItem(ALL_ITEMS, startsFrom).getAbsolutePath();
    }
}
