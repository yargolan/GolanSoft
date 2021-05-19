package com.ygsoft.apps.computerbackup;


import javax.swing.*;



public class BackupMain {


    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e) {
            System.err.println("Cannot set the LNF. Sorry...");
        }


        // Set the UI
        BackupUi backupUi = new BackupUi();
        backupUi.setUi();
    }
}


