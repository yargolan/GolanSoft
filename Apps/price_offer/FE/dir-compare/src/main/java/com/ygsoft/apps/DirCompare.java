package com.ygsoft.apps;

import javax.swing.*;



public class DirCompare {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.err.println("Cannot set the look-and-feel ...");
        }

        DirCompareUi dirCompareUi = new DirCompareUi();
        dirCompareUi.setAndShow();
    }
}




