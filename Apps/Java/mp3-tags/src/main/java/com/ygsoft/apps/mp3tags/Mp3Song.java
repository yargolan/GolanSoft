package com.ygsoft.apps.mp3tags;

import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Files;
import com.mpatric.mp3agic.*;
import java.nio.file.StandardCopyOption;
import com.ygsoft.apps.utilsandwrappers.*;



public class Mp3Song extends File {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private File currentSong;
    private String songTitle, songAlbum, songArtist;


    Mp3Song(String songFullPath) {
        super(songFullPath);
        currentSong = this.getAbsoluteFile();
        parseIdv3();
    }



    void parseIdv3() {

        ID3v1 id3v1;

        Mp3File mp3File;
        try {
            mp3File = new Mp3File(currentSong);
        }
        catch (Exception e) {
            setSongTitle("");
            setSongAlbum("");
            setSongArtist("");
            return;
        }


        // Get the ID3v1 tags.
        if (mp3File.hasId3v1Tag()) {
            id3v1 = mp3File.getId3v1Tag();
            setSongTitle(id3v1.getTitle());
            setSongAlbum(id3v1.getAlbum());
            setSongArtist(id3v1.getArtist());
        }
        else {
            setSongTitle("");
            setSongAlbum("");
            setSongArtist("");
        }
    }



    public String getSongTitle() {
        return songTitle;
    }



    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }



    public String getSongAlbum() {
        return songAlbum;
    }



    public void setSongAlbum(String songAlbum) {
        this.songAlbum = songAlbum;
    }



    public String getSongArtist() {
        return songArtist;
    }



    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }



    // Since we cannot change the actual file, we save it as a temporary file.
    // Then, we copy it back to the current folder.
    public void updateTag() {

        try {

            Mp3File mp3File = new Mp3File(currentSong);

            // Verify that the file has a IDv3.
            ID3v1 id3v1;
            if (mp3File.hasId3v1Tag()) {
                id3v1 = mp3File.getId3v1Tag();
            }
            else {
                id3v1 = new ID3v1Tag();
            }


            // Set the new values in it.
            id3v1.setTitle (getSongTitle());
            id3v1.setAlbum (getSongAlbum());
            id3v1.setArtist(getSongArtist());


            // Set the IDv3 tags into the file.
            mp3File.setId3v1Tag(id3v1);


            // Save the file as a temporary file.
            File tempFile = File.createTempFile("temp_", ".mp3");
            tempFile.deleteOnExit();
            mp3File.save(tempFile.getAbsolutePath());


            // Copy the temp file back into the current folder.
            Files.copy(
                Paths.get(tempFile.getAbsolutePath()),
                Paths.get(currentSong.getAbsolutePath()),
                    StandardCopyOption.REPLACE_EXISTING
            );
        }
        catch (Exception e) {
            // TODO: test vs real mp3 file.
            Messages.showMessage(Messages.MESSAGE_TYPE_ERROR, e.getMessage());
        }
    }
}
