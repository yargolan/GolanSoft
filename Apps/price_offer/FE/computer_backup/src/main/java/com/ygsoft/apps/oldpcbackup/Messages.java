package com.ygsoft.apps.oldpcbackup;

import javax.swing.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



public class Messages {


    private static Logger logger = LogManager.getLogger(Messages.class);
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
}
