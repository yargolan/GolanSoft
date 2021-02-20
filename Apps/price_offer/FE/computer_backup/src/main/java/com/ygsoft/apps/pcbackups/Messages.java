package com.ygsoft.apps.pcbackups;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;


public class Messages {


    static final int MESSAGE_ERR = 0;
    static final int MESSAGE_INF = 1;
    private static final Logger logger = LogManager.getLogger(Messages.class);



    static void exitWithError(String errorMessage, Exception e) {
        logger.error(errorMessage);
        if (isUnderDebug()) {
            e.printStackTrace();
        }
        System.exit(1);
    }



    public static void exitWithError (String errorMessage, boolean showGuiMessage, Exception e) {

        if (showGuiMessage) {
            showMessage(MESSAGE_ERR, errorMessage);
        }

        if (isUnderDebug()) {
            e.printStackTrace();
        }

        logger.error(errorMessage);
        System.exit(1);
    }



    public static void showMessage(int messageType, String message) {

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



    private static boolean isUnderDebug() {
        String value = System.getenv("DEBUG");
        return value != null && value.equals("true");
    }
}
