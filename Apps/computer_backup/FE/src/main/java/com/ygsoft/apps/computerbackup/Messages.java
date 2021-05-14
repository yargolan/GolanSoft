package com.ygsoft.apps.computerbackup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;


public class Messages {


    private static final Logger logger = LogManager.getLogger(Messages.class);
    static final int MESSAGE_ERR = 0;
    static final int MESSAGE_INF = 1;



    static void exitWithError(String errorMessage) {
        logger.error(errorMessage);
        System.exit(1);
    }



    public static void exitWithError (String errorMessage, boolean showGuiMessage) {

        if (showGuiMessage) {
            showMessage(MESSAGE_ERR, errorMessage);
        }
        exitWithError(errorMessage);
    }



    public static void info(String message) {
        showMessage(MESSAGE_INF, message);
    }



    public static void error(String message) {
        message = (message == null || message.isEmpty()) ? "Internal error" : message;
        showMessage(MESSAGE_ERR, message);
    }



    static void showMessage(int messageType, String message) {

        if (messageType == MESSAGE_ERR) {
            logger.error(message);
            JOptionPane.showMessageDialog(
                    null, message,"Error :", JOptionPane.ERROR_MESSAGE
            );
        }
        else if (messageType == MESSAGE_INF) {
            logger.info(message);
            JOptionPane.showMessageDialog(
                    null, message,"Info", JOptionPane.INFORMATION_MESSAGE
            );
        }
        else {
            logger.debug("Unknown type : " + message);
            JOptionPane.showMessageDialog(
                    null, message,"Unknown", JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}
