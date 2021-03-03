package com.ygsoft.apps.mp3tags;

import javax.swing.*;
import java.awt.Container;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



public class Mp3FilesTags extends JFrame {

    private List<Mp3Song> songsList;


    private int currentSongIndex = -1;
    private final JButton btnLast, btnPrev, btnFirst, btnUpdate, btnNext;
    private final JTextField tfRootFolder = new JTextField();
    private final JTextField tfFileName   = new JTextField();
    private final JTextField tfSongTitle  = new JTextField();
    private final JTextField tfAlbumName  = new JTextField();
    private final JTextField tfSongArtist = new JTextField();


    private static final Logger logger = LogManager.getLogger(Mp3FilesTags.class);



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Mp3FilesTags app = new Mp3FilesTags();
            app.setVisible(true);
        });
    }



    private Mp3FilesTags() {

        // +--------------------------------------------------------
        // |  Set the 'look-and-feel' for the UI.
        // +--------------------------------------------------------
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e) {
            logger.error("Cannot set the look-and-feel");
        }


        int buttonWidth        = 80;
        int buttonHeight       = 30;
        int textFieldLocationX = 130;


        this.setTitle("MP3 tags updater");
        this.setLayout(null);
        this.setBounds(200, 100, 800, 400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // GUI components definitions.
        JLabel lFileName        = new JLabel("File name");
        JLabel lRootFolder      = new JLabel("Root folder");
        JLabel lSongTitle       = new JLabel("Title");
        JLabel lSongArtist      = new JLabel("Artist name");
        JLabel lSongAlbumName   = new JLabel("Album name");

        btnLast   = new JButton(eHc.SIGN_LAST.getText());
        btnNext   = new JButton(eHc.SIGN_NEXT.getText());
        btnPrev   = new JButton(eHc.SIGN_PREVIOUS.getText());
        btnFirst  = new JButton(eHc.SIGN_FIRST.getText());
        btnUpdate = new JButton(eHc.ACTION_UPDATE.getText());

        JButton bBrowse = new JButton("Browse ...");


        // Set the GUI component locations.
        lFileName.setBounds     (30, 110, 90, 20);
        lSongTitle.setBounds    (30, 140, 90, 20);
        lRootFolder.setBounds   (30,  30, 90, 20);
        lSongArtist.setBounds   (30, 170, 90, 20);
        lSongAlbumName.setBounds(30, 200, 90, 20);

        tfFileName.setBounds  (textFieldLocationX, 110, 330, 20);
        tfSongTitle.setBounds (textFieldLocationX, 140, 330, 20);
        tfAlbumName.setBounds (textFieldLocationX, 200, 330, 20);
        tfRootFolder.setBounds(textFieldLocationX,  30, 330, 20);
        tfSongArtist.setBounds(textFieldLocationX, 170, 330, 20);

        bBrowse.setBounds(480, 30, 110, 20);

        btnFirst.setBounds (130, 225, buttonWidth, buttonHeight);
        btnPrev.setBounds  (210, 225, buttonWidth, buttonHeight);
        btnNext.setBounds  (300, 225, buttonWidth, buttonHeight);
        btnLast.setBounds  (380, 225, buttonWidth, buttonHeight);
        btnUpdate.setBounds(130, 280, buttonWidth*4+10, buttonHeight);



        // Set special behaviours.
        tfFileName.setEditable(false);
        btnPrev.setEnabled    (false);
        btnNext.setEnabled    (false);
        btnLast.setEnabled    (false);
        btnFirst.setEnabled   (false);
        btnUpdate.setEnabled  (false);



        // +---------------------------------------
        // | Add the components onto the form
        // +---------------------------------------
        Container content = this.getContentPane();

        content.add(lFileName);
        content.add(lRootFolder);
        content.add(lSongTitle);
        content.add(lSongArtist);
        content.add(lSongAlbumName);

        content.add(tfFileName);
        content.add(tfSongTitle);
        content.add(tfAlbumName);
        content.add(tfSongArtist);
        content.add(tfRootFolder);

        content.add(bBrowse);
        content.add(btnFirst);
        content.add(btnPrev);
        content.add(btnLast);
        content.add(btnNext);
        content.add(btnUpdate);



        // +---------------------------------------
        // | Add action listeners to the buttons.
        // +---------------------------------------
        bBrowse.addActionListener(e->actionPerformedBrowse());

        btnNext.addActionListener(e->actionPerformedNext());

        btnLast.addActionListener(e->actionPerformedLast());

        btnPrev.addActionListener(e->actionPerformedPrev());

        btnFirst.addActionListener(e->actionPerformedFirst());

        btnUpdate.addActionListener(e->actionPerformedUpdate());
    }



    private void actionPerformedUpdate() {

        // Read the form.
        String rootDir    = tfRootFolder.getText();
        String fileName   = tfFileName.getText();
        String songTitle  = tfSongTitle.getText();
        String songAlbum  = tfAlbumName.getText();
        String songArtist = tfSongArtist.getText();

        Mp3Song currentSong = new Mp3Song(String.format("%s%s%s",
                rootDir,
                File.separator,
                fileName
        ));

        currentSong.setSongTitle(songTitle);
        currentSong.setSongAlbum(songAlbum);
        currentSong.setSongArtist(songArtist);

        currentSong.updateTag();

        Messages.showMessage(Messages.MESSAGE_INF, "Updated successfully.");
    }



    private void actionPerformedBrowse() {

        File rootFolder = selectSongsFolder();

        if (rootFolder != null) {

            tfRootFolder.setText(rootFolder.getAbsolutePath());


            // Read the songs list from rhe current folder.
            songsList = getSongsFromFolder(rootFolder);

            if (songsList.size() > 0) {

                // Set the first song's properties into the form.
                currentSongIndex = 0;
                Mp3Song currentSong = songsList.get(0);


                // Set the current song's details.
                setSongDetails(currentSong);


                // Enable the 'update' button.
                btnUpdate.setEnabled(true);


                // Enable the 'next' and 'last' buttons.
                if (songsList.size() > 1) {
                    btnNext.setEnabled(true);
                    btnLast.setEnabled(true);
                }
            }
        }
    }



    private void actionPerformedNext() {

        // Set the current song's details in the form.
        ++currentSongIndex;
        Mp3Song currentSong = songsList.get(currentSongIndex);
        setSongDetails(currentSong);


        // Enable the 'First' and the 'Previous' buttons.
        btnPrev.setEnabled (true);
        btnFirst.setEnabled(true);


        // Verify if this is the last song in the list.
        if (currentSongIndex == songsList.size() - 1) {
            btnNext.setEnabled(false);
            btnLast.setEnabled(false);
        }
    }



    private void actionPerformedLast() {

        // Set the current song's details in the form.
        currentSongIndex = songsList.size() - 1;
        Mp3Song currentSong = songsList.get(currentSongIndex);
        setSongDetails(currentSong);


        // Enable the 'First' and the 'Previous' buttons.
        btnPrev.setEnabled (true);
        btnFirst.setEnabled(true);

        // Disable the 'Next' and 'Last' buttons.
        btnNext.setEnabled(false);
        btnLast.setEnabled(false);
    }



    private void actionPerformedFirst() {

        // Set the current song's details in the form.
        currentSongIndex = 0;
        Mp3Song currentSong = songsList.get(currentSongIndex);
        setSongDetails(currentSong);


        // Disable the 'First' and the 'Previous' buttons.
        btnPrev.setEnabled (false);
        btnFirst.setEnabled(false);

        // Enable the 'Next' and 'Last' buttons.
        btnNext.setEnabled(true);
        btnLast.setEnabled(true);
    }



    private void actionPerformedPrev() {

        // Set the current song's details in the form.
        --currentSongIndex;
        Mp3Song currentSong = songsList.get(currentSongIndex);
        setSongDetails(currentSong);


        // Enable the 'Next' and the 'Last' buttons.
        btnNext.setEnabled(true);
        btnLast.setEnabled(true);


        // Verify if this is the first song in the list.
        if (currentSongIndex == 0) {
            btnPrev.setEnabled (false);
            btnFirst.setEnabled(false);
        }
    }



    private void setSongDetails (Mp3Song currentSong) {
        tfFileName.setText  (currentSong.getName());
        tfSongTitle.setText (currentSong.getSongTitle());
        tfAlbumName.setText (currentSong.getSongAlbum());
        tfSongArtist.setText(currentSong.getSongArtist());
    }



    private List<Mp3Song> getSongsFromFolder(File songsFolder) {

        List<Mp3Song> list = new ArrayList<>();

        for (File optionalFile : Objects.requireNonNull(songsFolder.listFiles())) {

            if (optionalFile.getName().toLowerCase().endsWith(".mp3")) {

                // Verify that this is the real file and not the descriptor.
                if ( ! optionalFile.getName().contains("\\._")) {
                    Mp3Song mp3Song = new Mp3Song(optionalFile.getAbsolutePath());
                    list.add(mp3Song);
                }
            }
        }

        Collections.sort(list);

        return list;
    }



    private File selectSongsFolder() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setDialogTitle("Choose MP3 root folder");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }
}


