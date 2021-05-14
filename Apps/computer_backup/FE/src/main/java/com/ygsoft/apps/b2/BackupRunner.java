package com.ygsoft.apps.b2;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



class BackupRunner {

    private int totalFiles       = 0;
    private int amountOfFolders  = 0;
    private int amountOfNewFiles = 0;
    private File targetFolder;

    private static Logger logger = LogManager.getLogger(BackupRunner.class);

    BackupRunner() {}



    public void performBackup(BackupProfile backupProfile) throws IOException {

        if (backupProfile == null) {
            throw new IOException("Cannot b1 with null zzProfile");
        }

        targetFolder = backupProfile.getTargetFolder();

        try {
            // Verify target folder.
            verifyTargetFolder();
        }
        catch (IOException e) {
            throw new IOException(e.getMessage());
        }


        try {

            // Get the list of items.
            logger.info("Get the list of items.");
            List<String> itemsList = backupProfile.getItemsToBackup();
            logger.info("ok.");
            logger.info("");

            for (String currentItem : itemsList) {
                backup(new File(currentItem));
            }
        }
        catch (IOException e) {
            throw new IOException(e.getMessage(), e);
        }

        // Print the summary.
        logger.info("");
        logger.info("+----------------");
        logger.info("| Summary");
        logger.info("+----------------");
        logger.info(String.format ("New Files     : %d", amountOfNewFiles));
        logger.info(String.format ("Total Files   : %d", totalFiles));
        logger.info(String.format ("Total folders : %d", amountOfFolders));
        logger.info("");
    }



    private void verifyTargetFolder() throws IOException {

        logger.info("Verify target folder.");
        if (targetFolder.exists()) {
            logger.debug(String.format(
                    "The target folder (%s) exists.",
                    targetFolder.getName())
            );
        }
        else {
            logger.debug(String.format(
                    "The target folder (%s) does not exist yet. Create.",
                    targetFolder.getName())
            );

            if (targetFolder.mkdirs()) {
                logger.debug(String.format(
                        "The target folder (%s) created successfully.",
                        targetFolder.getName())
                );
            }
            else {
                throw new IOException(String.format(
                        "Cannot generate the target folder (%s).",
                        targetFolder.getName())
                );
            }
        }
        logger.info("ok.");
        logger.info("");
    }



    private void backup(File currentItem) throws IOException {

        if (currentItem == null) {
            throw new IOException("Cannot b1 'null'.");
        }
        else if (currentItem.isDirectory()) {
            logger.info("Running b1 for directory : " + currentItem.getName());
            try {
                backupFolder(currentItem);
            }
            catch (IOException e) {
                throw new IOException(String.format(
                        "Cannot b1 the file '%s'.",
                        currentItem.getAbsolutePath()), e
                );
            }
            logger.info("ok.");
            logger.info("");
        }
        else if (currentItem.isFile()) {
            logger.info("Running b1 for file      : " + currentItem.getName());
            try {
                backupSingleFile(currentItem);
            }
            catch (IOException e) {
                throw new IOException(String.format(
                        "Cannot b1 the file '%s'.",
                        currentItem.getAbsolutePath()), e
                );
            }
            logger.info("ok.");
            logger.info("");
        }
        else {
            throw new IOException("Cannot determine the item : " + currentItem.getName());
        }
    }



    private void backupFolder(File currentDir) throws IOException {

        if (currentDir == null) {
            throw new IOException("Cannot b1 'null' Dir.");
        }

        amountOfFolders++;

        String formattedCurrentDir = currentDir.getAbsolutePath().replace(":", "");

        // Get the full path of the target dir.
        File targetDirFullPath = new File(String.format("%s%s%s",
                targetFolder,
                File.separatorChar,
                formattedCurrentDir
        ));


        if (targetDirFullPath.exists()) {

            File[] subItems = currentDir.listFiles();
            if (subItems == null) {
                logger.error("Found null instead of sub items.");
                return;
            }

            // Walk over the sub items.
            for (File currentSubItem : subItems) {
                if (currentSubItem == null) {
                    logger.error("Found null sub item.");
                }
                else if (currentSubItem.isFile()) {
                    backupSingleFile(currentSubItem);
                }
                else if (currentSubItem.isDirectory()) {
                    backupFolder(currentSubItem);
                }
                else {
                    logger.error("Found an unknown sub item: " + currentSubItem.getAbsolutePath());
                }
            }
        }
        else {
            logger.info("Copy a new dir - " + currentDir.getName());
            amountOfNewFiles++;
            FileUtils.copyDirectory(currentDir, targetDirFullPath);
        }
    }



    private void backupSingleFile(File currentFile) throws IOException {

        if (currentFile == null) {
            throw new IOException("Cannot b1 'null' file.");
        }

        totalFiles++;

        String formattedCurrentFile = currentFile.getAbsolutePath().replace(":", "");

        // Get the full path of the target file.
        File targetFileFullPath = new File(String.format("%s%s%s",
                targetFolder,
                File.separatorChar,
                formattedCurrentFile
        ));


        // Backup the file.
        if (targetFileFullPath.exists()) {
            logger.debug("Found an existing file - " + currentFile.getName());
            if (hasSameMd5Checksum(currentFile, targetFileFullPath)) {
                logger.debug("Same MD5 checksum.");
            }
            else {
                logger.debug("Different MD5 checksum. Update.");
                forceCopyFile(currentFile, targetFileFullPath);
            }
        }
        else {

            logger.info("Found a new file - " + currentFile.getName());

            // Get the target file's parent dir.
            File targetFileParentDir = targetFileFullPath
                    .getParentFile()
                    .getAbsoluteFile()
            ;

            logger.info("Create parent folder.");
            if ( ! targetFileParentDir.exists()) {
                if ( ! targetFileParentDir.mkdirs()) {
                    throw new IOException(
                            "Cannot create the path for : " +
                                    targetFileFullPath.getAbsolutePath()
                    );
                }
            }

            // Copy the file.
            File targetFile = new File (String.format(
                    "%s%s%s",
                    targetFileParentDir,
                    File.separatorChar,
                    currentFile.getName()
            ));
            forceCopyFile(currentFile, targetFile);
            logger.debug("ok.");
        }
    }



    private boolean hasSameMd5Checksum(File currentFile, File targetFileFullPath) throws IOException {

        String sourceFileMd5, targetFileMd5;

        // Check the MD5 checksums of the source file.
        try {
            sourceFileMd5 = MD5Checksum.getMD5Checksum(currentFile);
        }
        catch (Exception e) {
            throw new IOException(String.format(
                    "Cannot calculate the MD5 checksum for '%s'",
                    currentFile.getAbsolutePath()),
                    e
            );
        }


        // Check the MD5 checksums of the target file.
        try {
            targetFileMd5 = MD5Checksum.getMD5Checksum(targetFileFullPath);
        }
        catch (Exception e) {
            throw new IOException(String.format(
                    "Cannot calculate the MD5 checksum for '%s'",
                    targetFileFullPath.getAbsolutePath()),
                    e
            );
        }

        return sourceFileMd5.equals(targetFileMd5);
    }



    private void forceCopyFile(File currentFile, File targetFileFullPath) throws IOException {
        if (currentFile == null || targetFileFullPath == null) {
            throw new IOException("Cannot copy when source or target are null.");
        }

        logger.debug("File : " + currentFile.getAbsolutePath());
        Files.copy(
                Paths.get(currentFile.getAbsolutePath()),
                Paths.get(targetFileFullPath.getAbsolutePath()),
                StandardCopyOption.REPLACE_EXISTING
        );
    }
}






















