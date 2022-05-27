package com.golansoft.apps.computerbackup;

import javax.swing.*;
import java.awt.*;


public class ComputerBackup {

    UiFramesGenerator uiFramesGenerator = new UiFramesGenerator();
    JButton bRunBackup, bProfileCreate, bProfileDelete;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(ComputerBackup::createGUI);
    }



    private static void createGUI() {
        ComputerBackup computerBackup = new ComputerBackup();
        computerBackup.createAndShowGUI();
    }



    private void createAndShowGUI() {
        JFrame fMain = createMainFrameUI();
        fMain.setVisible(true);
    }



    private JFrame createMainFrameUI() {

        JFrame f = uiFramesGenerator.generate(UiFramesGenerator.FRAME_MAIN);

        // Labels
        JLabel lAuthor  = new JLabel(HardCoded.ME.getText());
        JLabel lVersion = new JLabel(HardCoded.CURRENT_VERSION.getText());


        // Buttons
        bRunBackup     = new JButton(HardCoded.B_PROFILE_RUN.getText());
        bProfileDelete = new JButton(HardCoded.B_PROFILE_DELETE.getText());
        bProfileCreate = new JButton(HardCoded.B_PROFILE_CREATE.getText());


        // Locations
        lAuthor.setBounds       (110, 240, 300, 20);
        lVersion.setBounds      (10,  240, 300, 20);
        bRunBackup.setBounds    (110, 20,  200, 40);
        bProfileCreate.setBounds(110, 80,  200, 40);
        bProfileDelete.setBounds(110, 140, 200, 40);


        // Add to the UI
        Container container = f.getContentPane();

        container.add(lAuthor);
        container.add(lVersion);
        container.add(bRunBackup);
        container.add(bProfileCreate);
        container.add(bProfileDelete);

        // Add action listeners
        bRunBackup.addActionListener    (new MainButtonsActionListener());
        bProfileCreate.addActionListener(new MainButtonsActionListener());
        bProfileDelete.addActionListener(new MainButtonsActionListener());

        return f;
    }
}















