package com.ygsoft.apps.b1;

import javax.swing.*;



public class BackupMain {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e) {
            System.err.println("Cannot set the LNF. Sorry...");
        }

        BackupUi ui = new BackupUi();
        try {
            ui.createUi();
        }
        catch (Exception e) {
            Messages.exitWithError(e.getMessage(), true);
        }
    }
}
