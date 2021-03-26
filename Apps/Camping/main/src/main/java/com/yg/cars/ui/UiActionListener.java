package com.yg.cars.ui;

import com.yg.cars.Garage;
import com.yg.cars.InternalErrorException;
import com.yg.cars.Messages;
import com.yg.cars.data.DbWrapper;

import javax.swing.*;
import java.awt.*;



public class UiActionListener {


    UiFramesGenerator uiFramesGenerator = new UiFramesGenerator();



    public void alGarageAdd() {


        JFrame fGarageAdd = uiFramesGenerator.generateFrame(UiFramesGenerator.F_GARAGE_ADD);

        JLabel lGarageName        = new JLabel("Name");
        JLabel lGarageAddress     = new JLabel("Address");
        JLabel lGarageContact     = new JLabel("Contact name");
        JLabel lGaragePhoneNumber = new JLabel("Phone number");


        JTextField tfGarageName        = new JTextField();
        JTextField tfGarageContact     = new JTextField();
        JTextField tfGarageAddress     = new JTextField();
        JTextField tfGaragePhoneNumber = new JTextField();


        JButton bAdd = new JButton("Add");



        // Location
        lGarageName.setBounds(20, 20, 100, 20);
        lGarageAddress.setBounds(20, 50, 100, 20);
        lGarageContact.setBounds(20, 80, 100, 20);
        lGaragePhoneNumber.setBounds(20, 110, 100, 20);

        tfGarageName.setBounds(120, 20, 150, 20);
        tfGarageAddress.setBounds(120, 50, 150, 20);
        tfGarageContact.setBounds(120, 80, 150, 20);
        tfGaragePhoneNumber.setBounds(120, 110, 150, 20);

        bAdd.setBounds(30, 150, 240, 40);


        Container container = fGarageAdd.getContentPane();

        container.add(lGarageName);
        container.add(lGarageAddress);
        container.add(lGarageContact);
        container.add(lGaragePhoneNumber);

        container.add(tfGarageName);
        container.add(tfGarageAddress);
        container.add(tfGarageContact);
        container.add(tfGaragePhoneNumber);

        container.add(bAdd);


        bAdd.addActionListener(e->{

            Garage garage = new Garage(
                    tfGarageName.getText(),
                    tfGarageAddress.getText(),
                    tfGarageContact.getText(),
                    tfGaragePhoneNumber.getText()
            );

            try {
                garage.validate();
            }
            catch (InternalErrorException ex) {
                Messages.showMessage(Messages.MESSAGE_ERR, ex.getMessage());
            }

            // Add the garage to the list.
            DbWrapper dbWrapper = new DbWrapper();
            dbWrapper.addGarage(garage);
        });

        fGarageAdd.setVisible(true);
    }


    public void alTreatmentAdd() {

    }

    public void alTreatmentEdit() {

    }
}
