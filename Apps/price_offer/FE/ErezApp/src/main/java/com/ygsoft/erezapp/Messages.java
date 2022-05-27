package com.ygsoft.erezapp;

import javax.swing.*;

public class Messages {

    public static final String INFO  = "Info";
    public static final String ERROR = "Error";

    public static void show_message(String messageType, String messageText){

        if (messageType.equals(ERROR)) {
            JOptionPane.showMessageDialog(
                    null,
                    messageText,
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        else if (messageType.equals(INFO)) {
            JOptionPane.showMessageDialog(
                    null,
                    messageText,
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        else {
            show_message(INFO, messageType);
        }
    }
}
