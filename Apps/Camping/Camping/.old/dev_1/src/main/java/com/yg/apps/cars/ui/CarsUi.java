package com.yg.apps.cars.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarsUi implements ActionListener {


    private JFrame fMainUi;
    
    private JMenuItem miGaragesAdd, miGaragesEdit,  miGaragesExit;
    private JMenuItem miMaintenanceAdd, miMaintenanceEdit;
    private JMenuItem miCarCareAdd, miCarCareEdit;


    private final MenusGenerator   menusGenerator    = new MenusGenerator();
    private final FramesGenerator  framesGenerator   = new FramesGenerator();
    private final UiActionListener uiActionListener  = new UiActionListener();



    public CarsUi(){}



    public void createUi() {
        
        // Set the menu items
        setMenuItems();

        fMainUi = framesGenerator.generateFrame(FramesGenerator.F_MAIN);


        // Create the menu bar
        JMenuBar mb = createMenuBar();


        // Add the menu bar to the frame
        fMainUi.setJMenuBar(mb);


        // Show the UI
        fMainUi.setVisible(true);
    }



    private void setMenuItems() {

        miMaintenanceAdd  = new JMenuItem("Add maintenance");
        miMaintenanceEdit = new JMenuItem("Edit maintenance");

        miGaragesAdd  = new JMenuItem("Add garage details");
        miGaragesEdit = new JMenuItem("Edit garage details");
        miGaragesExit = new JMenuItem("Exit");

        miCarCareAdd  = new JMenuItem("Add car care");
        miCarCareEdit = new JMenuItem("Edit car care");


        miMaintenanceAdd.addActionListener     (this);
        miMaintenanceEdit.addActionListener    (this);
        miCarCareAdd.addActionListener (this);
        miCarCareEdit.addActionListener(this);
        miGaragesAdd.addActionListener (this);
        miGaragesEdit.addActionListener(this);
        miGaragesExit.addActionListener(this);
    }



    private JMenuBar createMenuBar() {

        JMenuBar menuBar = new JMenuBar();

        JMenu mMaintenance   = menusGenerator.createMenuMaintenance(miMaintenanceAdd, miMaintenanceEdit);
        JMenu mGarages = menusGenerator.createMenuGarages(miGaragesAdd, miGaragesEdit, miGaragesExit);
        JMenu mCarCare = menusGenerator.createMenuCarCare(miCarCareAdd, miCarCareEdit);

        menuBar.add(mGarages);
        menuBar.add(mMaintenance);
        menuBar.add(mCarCare);

        return menuBar;
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == miGaragesExit) {
            fMainUi.dispose();
        }


        if (e.getSource().equals(miGaragesAdd)) {
            uiActionListener.alGarageAdd();
        }


        if (e.getSource().equals(miGaragesEdit)) {
            uiActionListener.alGarageEdit();
        }


        if (e.getSource().equals(miMaintenanceAdd)) {
            uiActionListener.alMaintenanceAdd();
        }


        if (e.getSource().equals(miMaintenanceEdit)) {
            uiActionListener.alMaintenanceEdit();
        }
    }
}





