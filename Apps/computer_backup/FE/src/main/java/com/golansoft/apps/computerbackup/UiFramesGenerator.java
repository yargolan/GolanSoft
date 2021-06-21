package com.golansoft.apps.computerbackup;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;



public class UiFramesGenerator {

    static final int FRAME_MAIN        = 1;
    static final int FRAME_PROFILE_NEW = 2;
    static final int FRAME_PROFILE_DEL = 3;
    static final int FRAME_PROFILE_RUN = 4;

    static final int ALL_ITEMS    = 10;
    static final int FOLDERS_ONLY = 20;



    public UiFramesGenerator() {}



    JFrame generate(int frameType) {

        // Set the frame.
        JFrame f = new JFrame();


        // Set the frame's icon, get it from the resource folder.
        URL url = getClass().getResource("/icon.png");
        if (url != null) {
            ImageIcon imageIcon = new ImageIcon(url);
            f.setIconImage(imageIcon.getImage());
        }


        // Generic frame settings
        f.setLayout(null);
        f.setResizable(false);
        f.setUndecorated(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        InputStream stream = UiFramesGenerator.class.getResourceAsStream( "/icon.png" );
        if (stream != null) {
            try {
                BufferedImage image = ImageIO.read(stream);
                f.setIconImage(image);
            }
            catch (IOException e) {
                System.err.println("Cannot load the icon.");
            }
        }

        switch (frameType) {

            case FRAME_MAIN:
                f.setSize(400, 300);
                f.setTitle(HardCoded.F_MAIN.getText());
                f.setLocation(400, 200);
                f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                break;

            case FRAME_PROFILE_NEW:
                f.setSize(450, 580);
                f.setTitle(HardCoded.F_CREATE_NEW_PROFILE.getText());
                f.setLocation(200, 200);
                f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                break;

            case FRAME_PROFILE_DEL:
                f.setSize(400, 200);
                f.setTitle(HardCoded.B_PROFILE_DELETE.getText());
                f.setLocation(300, 200);
                f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                break;

            case FRAME_PROFILE_RUN:
                f.setSize(300, 200);
                f.setTitle(HardCoded.B_PROFILE_RUN.getText());
                f.setLocation(800, 500);
                f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                break;

            default:
                break;
        }

        return f;
    }



    private File selectItem (int selectionType, String startingFolder){

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
        return (returnValue == JFileChooser.APPROVE_OPTION) ?
            jfc.getSelectedFile() : null
        ;
    }



    public String selectFolder(String startsFrom) {
        File f = selectItem(FOLDERS_ONLY, startsFrom);
        return (f == null) ? null : f.getAbsolutePath();
    }



    public String selectFolderOrFile(String startsFrom) {
        File f = selectItem(ALL_ITEMS, startsFrom);
        return (f == null) ? null : f.getAbsolutePath();
    }
}
