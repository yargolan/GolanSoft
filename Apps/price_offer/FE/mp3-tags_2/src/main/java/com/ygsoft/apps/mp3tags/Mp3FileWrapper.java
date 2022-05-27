//package com.ygsoft.apps.mp3tags;
//
//import java.io.File;
//import com.mpatric.mp3agic.*;
//
//
//
//public class Mp3FileWrapper {
//
//    private File fileFullPath;
//    private String name, album, artist;
//
//
//    public Mp3FileWrapper(File fileFullPath, String name, String album, String artist) {
//        this.fileFullPath = fileFullPath;
//        this.name   = name;
//        this.album  = album;
//        this.artist = artist;
//    }
//
//    public Mp3FileWrapper(File fileFullPath) {
//        this.fileFullPath = fileFullPath;
//        parseFile();
//    }
//
//
//
//    public void parseFile() {
//
//        try {
//            Mp3File mp3File = new Mp3File(fileFullPath);
//
//            // Get the ID3v1 tags.
//            ID3v1 id3v1;
//            if (mp3File.hasId3v1Tag()) {
//                id3v1 = mp3File.getId3v1Tag();
//                this.name   = id3v1.getTitle();
//                this.album  = id3v1.getAlbum();
//                this.artist = id3v1.getArtist();
//            }
//            else {
//                this.name = "";
//                this.album = "";
//                this.artist = "";
//            }
//        }
//        catch (Exception e) {
//            this.name   = "";
//            this.album  = "";
//            this.artist = "";
//            this.fileFullPath = null;
//        }
//    }
//
//
//
//    public File getFileFullPath() {
//        return fileFullPath;
//    }
//
//
//
//    public void setFileFullPath(File fileFullPath) {
//        this.fileFullPath = fileFullPath;
//    }
//
//
//
//    public String getName() {
//        return name;
//    }
//
//
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//
//
//    public String getAlbum() {
//        return album;
//    }
//
//
//
//    public void setAlbum(String album) {
//        this.album = album;
//    }
//
//
//
//    public String getArtist() {
//        return artist;
//    }
//
//
//
//    public void setArtist(String artist) {
//        this.artist = artist;
//    }
//}
