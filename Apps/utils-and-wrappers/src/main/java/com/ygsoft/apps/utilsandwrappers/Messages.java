package com.ygsoft.apps.utilsandwrappers;

import javax.swing.*;



public class Messages {

	public static final int MESSAGE_TYPE_INFO = 1;
	public static final int MESSAGE_TYPE_ERROR = 2;


	public static void showMessage(int messageType, String messageText) {

		if (messageType == MESSAGE_TYPE_INFO) {
			JOptionPane.showMessageDialog(
					null,
					messageText,
					"Info",
					JOptionPane.INFORMATION_MESSAGE
			);
		}
		else if (messageType == MESSAGE_TYPE_ERROR) {
			JOptionPane.showMessageDialog(
					null,
					messageText,
					"Error",
					JOptionPane.ERROR_MESSAGE
			);
		}
		else {
			JOptionPane.showMessageDialog(
					null,
					messageText,
					"Unknown message type",
					JOptionPane.ERROR_MESSAGE
			);
		}
	}


	public static void exitWithError(String errorMessage, boolean showGuiMessage) {
		if (showGuiMessage) {
			showMessage(MESSAGE_TYPE_ERROR, errorMessage);
		}

		System.err.println(errorMessage);

		System.exit(1);
	}
}