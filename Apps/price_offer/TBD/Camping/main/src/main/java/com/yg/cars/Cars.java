package com.yg.cars;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.yg.cars.ui.UiActionListener;
import com.yg.cars.ui.UiFramesGenerator;


public class Cars implements ActionListener {


    private JFrame frame;
    UiFramesGenerator uiFramesGenerator = new UiFramesGenerator();

    private final JMenuItem miGaragesAdd  = new JMenuItem("Add");
    private final JMenuItem miGaragesExit = new JMenuItem("Exit");

    private final JMenuItem miTreatmentAdd  = new JMenuItem("Add");
    private final JMenuItem miTreatmentEdit = new JMenuItem("Edit");




    public Cars() {}



    public static void main(String[] args) {

        Cars cars = new Cars();

        cars.runApp();
    }



    private void runApp() {

        initApplication();

        generateUi();

        setActionListeners();
    }




    private void initApplication() {

    }



    private void generateUi() {

        frame = uiFramesGenerator.generateFrame(UiFramesGenerator.F_MAIN);

        JMenuBar mb = new JMenuBar();


        // Set the 'Garages' menu
        JMenu mGarages = getMenuGarages();

        // Set the 'Treatments' menu
        JMenu mTreatments = getMenuTreatments();


        // Add the menus to the bar
        mb.add(mGarages);
        mb.add(mTreatments);


        // Set the bar in the frame
        frame.setJMenuBar(mb);

        frame.setVisible(true);
    }



    private JMenu getMenuGarages() {
        JMenu mGarages = new JMenu("Garages");
        mGarages.add(miGaragesAdd);
        mGarages.addSeparator();
        mGarages.add(miGaragesExit);

        return mGarages;
    }



    private JMenu getMenuTreatments() {
        JMenu mGarages = new JMenu("Treatments");
        mGarages.add(miTreatmentAdd);
        mGarages.add(miTreatmentEdit);

        return mGarages;
    }



    private void setActionListeners() {

        miGaragesExit.addActionListener(e->frame.dispose());

        miGaragesAdd.addActionListener(this);
    }




    @Override
    public void actionPerformed(ActionEvent e) {

        UiActionListener uiActionListener = new UiActionListener();

        if (e.getSource().equals(miGaragesAdd)) {
            uiActionListener.alGarageAdd();
        }

        if (e.getSource().equals(miTreatmentAdd)) {
            uiActionListener.alTreatmentAdd();
        }

        if (e.getSource().equals(miTreatmentEdit)) {
            uiActionListener.alTreatmentEdit();
        }
    }
}














