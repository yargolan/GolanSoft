package com.ygsoft.apps.mp3tags;

import java.io.File;

public class Mp3FilesRename {

    private int lineLength = 0;


    public static void main(String[] args) {
        Mp3FilesRename mp3FilesRename = new Mp3FilesRename();
        if (args.length == 1) {
            mp3FilesRename.rename(new File (args[0]));
        }
        else {
            System.err.println("Usage: Mp3FilesRename  <root_dir>");
            System.exit(1);
        }
    }

    Mp3FilesRename(){}



    private void rename(File rootDir) {

        if (rootDir.isDirectory()) {

            File[] files = rootDir.listFiles();

            if (files != null) {
                for (File file : files) {
                    rename(file);
                }
            }
        }
        else if (rootDir.isFile()) {
            if (isMp3File(rootDir)) {

                lineLength += 1;

                System.out.print(".");

                if (lineLength == 60) {
                    lineLength = 0;
                    System.out.println();
                }
                if (rootDir.getName().contains("_")) {
                    String newName = rootDir.getName().replaceAll("_", " ");
                    File newFile = new File (
                            rootDir.getParent() +
                                    File.separator +
                                    newName
                    );

                    if ( ! rootDir.renameTo(newFile)) {
                        System.err.printf(
                                "Cannot rename the file.\nOriginal file : %sNew file name: %s%n",
                                rootDir.getAbsolutePath(),
                                newFile.getAbsolutePath()
                        );
                    }
                }
            }
        }
        else {
            System.err.printf(
                    "Cannot determine the type of [%s]%n", rootDir.getAbsolutePath()
            );
        }
    }



    private boolean isMp3File(File mp3File) {
        return mp3File.getName().toLowerCase().endsWith(".mp3");
    }
}













