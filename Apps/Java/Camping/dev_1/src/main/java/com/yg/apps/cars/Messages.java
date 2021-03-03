package com.yg.apps.cars;

import javax.swing.*;


public class Messages {


    public static final int MESSAGE_ERR = 0;
    public static final int MESSAGE_INF = 1;



    public static void exitWithError (String errorMessage, boolean showGuiMessage) {

        if (showGuiMessage) {
            showMessage(MESSAGE_ERR, errorMessage);
        }
        System.exit(1);
    }



    public static void showMessage(int messageType, String message) {

        if (messageType == MESSAGE_ERR) {

            JOptionPane.showMessageDialog(
                    null, message,"Error :", JOptionPane.ERROR_MESSAGE
            );
        }
        else if (messageType == MESSAGE_INF) {

            JOptionPane.showMessageDialog(
                    null, message,"Info", JOptionPane.INFORMATION_MESSAGE
            );
        }
        else {
            JOptionPane.showMessageDialog(
                    null, message,"Unknown", JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}


