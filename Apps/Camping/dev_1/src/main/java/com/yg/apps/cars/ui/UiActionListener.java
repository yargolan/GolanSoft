package com.yg.apps.cars.ui;

import java.awt.*;
import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.yg.apps.cars.ListsWrapper;
import com.yg.apps.cars.Messages;
import com.yg.apps.cars.DataSingleton;
import com.yg.apps.cars.data.DbWrapperGarage;
import com.yg.apps.cars.data.Garage;
import com.yg.apps.cars.data.Maintenance;


public class UiActionListener {

    private final int FORM_GARAGES = 1;

    private final ListsWrapper    listsWrapper      = new ListsWrapper();
    private final FramesGenerator uiFramesGenerator = new FramesGenerator();

    private final JTextField tfGarageId          = new JTextField();
    private final JTextField tfGarageName        = new JTextField();
    private final JTextField tfGarageContact     = new JTextField();
    private final JTextField tfGarageAddress     = new JTextField();
    private final JTextField tfGaragePhoneNumber = new JTextField();


    public void alGarageAdd() {

        JFrame fGarageAdd = uiFramesGenerator.generateFrame(FramesGenerator.F_GARAGE_ADD);

        JLabel lGarageName        = new JLabel("Name");
        JLabel lGarageAddress     = new JLabel("Address");
        JLabel lGarageContact     = new JLabel("Contact name");
        JLabel lGaragePhoneNumber = new JLabel("Phone number");


        JTextField tfGarageName        = new JTextField();
        JTextField tfGarageContact     = new JTextField();
        JTextField tfGarageAddress     = new JTextField();
        JTextField tfGaragePhoneNumber = new JTextField();


        JButton bAdd = new JButton("Add");



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

            // Validate the form.
            String readGarageName        = tfGarageName.getText();
            String readGarageAddress     = tfGarageAddress.getText();
            String readGarageContact     = tfGarageContact.getText();
            String readGaragePhoneNumber = tfGaragePhoneNumber.getText();

            if (readGarageName == null || readGarageName.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "Invalid garage name.");
                return;
            }

            if (readGarageAddress == null || readGarageAddress.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "Invalid garage address.");
                return;
            }

            if (readGarageContact == null || readGarageContact.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "Invalid garage contact.");
                return;
            }

            if (readGaragePhoneNumber == null || readGaragePhoneNumber.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "Invalid phone number.");
                return;
            }


            Garage garage = new Garage(
                    0,
                    readGarageName,
                    readGarageAddress,
                    readGarageContact,
                    readGaragePhoneNumber
            );

            garage.addToDb();

            fGarageAdd.dispose();
        });

        fGarageAdd.setVisible(true);

    }



    public void alGarageEdit() {

        AtomicInteger index = new AtomicInteger();
        
        // get the garages list.
        DataSingleton dataSingleton = DataSingleton.getInstance();

        List<Garage> garagesList = dataSingleton.getGaragesList();

        if (garagesList.size() == 0) {
            Messages.showMessage(
                    Messages.MESSAGE_INF,
                    "There are no garages set yet.\nPlease add one first."
            );
            return;
        }

        JFrame fGarageEdit = uiFramesGenerator.generateFrame(FramesGenerator.F_GARAGE_EDIT);

        JLabel lGarageId          = new JLabel("Id");
        JLabel lGarageName        = new JLabel("Name");
        JLabel lGarageAddress     = new JLabel("Address");
        JLabel lGarageContact     = new JLabel("Contact name");
        JLabel lGaragePhoneNumber = new JLabel("Phone number");


        JButton bFirst  = new JButton("<<--");
        JButton bPrev   = new JButton("<--");
        JButton bNext   = new JButton("-->");
        JButton bLast   = new JButton("-->>");
        JButton bEdit   = new JButton("Edit details");
        JButton bUpdate = new JButton("Update details");


        bUpdate.setEnabled            (false);
        tfGarageId.setEnabled         (false);
        tfGarageName.setEnabled       (false);
        tfGarageContact.setEnabled    (false);
        tfGarageAddress.setEnabled    (false);
        tfGaragePhoneNumber.setEnabled(false);


        lGarageId.setBounds          (20, 20, 100, 20);
        lGarageName.setBounds        (20, 50, 100, 20);
        lGarageAddress.setBounds     (20, 80, 100, 20);
        lGarageContact.setBounds     (20, 110,100, 20);
        lGaragePhoneNumber.setBounds (20, 140,100, 20);

        tfGarageId.setBounds         (120, 20,  170, 20);
        tfGarageName.setBounds       (120, 50,  170, 20);
        tfGarageAddress.setBounds    (120, 80,  170, 20);
        tfGarageContact.setBounds    (120, 110, 170, 20);
        tfGaragePhoneNumber.setBounds(120, 140, 170, 20);

        bFirst.setBounds (20,  170, 60,  20);
        bPrev.setBounds  (85,  170, 60,  20);
        bNext.setBounds  (160, 170, 60,  20);
        bLast.setBounds  (225, 170, 60,  20);
        bEdit.setBounds  (20,  220, 130, 40);
        bUpdate.setBounds(160, 220, 130, 40);

        Container container = fGarageEdit.getContentPane();

        container.add(lGarageId);
        container.add(lGarageName);
        container.add(lGarageAddress);
        container.add(lGarageContact);
        container.add(lGaragePhoneNumber);

        container.add(tfGarageId);
        container.add(tfGarageName);
        container.add(tfGarageAddress);
        container.add(tfGarageContact);
        container.add(tfGaragePhoneNumber);

        container.add(bFirst);
        container.add(bPrev);
        container.add(bNext);
        container.add(bLast);
        container.add(bEdit);
        container.add(bUpdate);

        // Insert the first garage, if exists.
        if (garagesList.size() > 0) {

            Garage garage = listsWrapper.getGarageByIndex(garagesList, 0);

            if (garage != null) {
                tfGarageId.setText(String.valueOf(garage.getId()));
                tfGarageName.setText(garage.getName());
                tfGarageAddress.setText(garage.getAddress());
                tfGarageContact.setText(garage.getContact());
                tfGaragePhoneNumber.setText(garage.getPhoneNumber());
            }
        }

        // +------------------------------------
        // | Action listeners
        // +------------------------------------
        bFirst.addActionListener(e->{

            Garage garage = listsWrapper.getGarageByIndex(garagesList, 0);

            if (garage != null) {
                tfGarageId.setText(String.valueOf(garage.getId()));
                tfGarageName.setText(garage.getName());
                tfGarageAddress.setText(garage.getAddress());
                tfGarageContact.setText(garage.getContact());
                tfGaragePhoneNumber.setText(garage.getPhoneNumber());
            }
        });
        
        
        bPrev.addActionListener(e->{

            if (index.get() > 0) {
                index.getAndDecrement();
            }

            Garage garage = listsWrapper.getGarageByIndex(garagesList, index.get());

            if (garage != null) {
                tfGarageId.setText(String.valueOf(garage.getId()));
                tfGarageName.setText(garage.getName());
                tfGarageAddress.setText(garage.getAddress());
                tfGarageContact.setText(garage.getContact());
                tfGaragePhoneNumber.setText(garage.getPhoneNumber());
            }
        });


        bNext.addActionListener(e->{
            
            index.getAndIncrement();

            Garage garage = listsWrapper.getGarageByIndex(garagesList, index.get());

            if (garage != null) {
                tfGarageId.setText(String.valueOf(garage.getId()));
                tfGarageName.setText(garage.getName());
                tfGarageAddress.setText(garage.getAddress());
                tfGarageContact.setText(garage.getContact());
                tfGaragePhoneNumber.setText(garage.getPhoneNumber());
            }
        });


        bLast.addActionListener(e->{

            index.getAndSet(garagesList.size()-1);

            Garage garage = listsWrapper.getGarageByIndex(garagesList, index.get());

            if (garage != null) {
                tfGarageId.setText(String.valueOf(garage.getId()));
                tfGarageName.setText(garage.getName());
                tfGarageAddress.setText(garage.getAddress());
                tfGarageContact.setText(garage.getContact());
                tfGaragePhoneNumber.setText(garage.getPhoneNumber());
            }
        });


        bEdit.addActionListener(e->{

            // Enable the garage details part
            enableForm(FORM_GARAGES);

            // Disable the 'edit' button
            bEdit.setEnabled(false);

            bUpdate.setEnabled(true);
        });


        bUpdate.addActionListener(e->{

            // Validate the form.
            int    readGarageId          = Integer.parseInt(tfGarageId.getText());
            String readGarageName        = tfGarageName.getText();
            String readGarageAddress     = tfGarageAddress.getText();
            String readGarageContact     = tfGarageContact.getText();
            String readGaragePhoneNumber = tfGaragePhoneNumber.getText();

            if (readGarageName == null || readGarageName.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "Invalid garage name.");
                return;
            }

            if (readGarageAddress == null || readGarageAddress.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "Invalid garage address.");
                return;
            }

            if (readGarageContact == null || readGarageContact.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "Invalid garage contact.");
                return;
            }

            if (readGaragePhoneNumber == null || readGaragePhoneNumber.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "Invalid phone number.");
                return;
            }


            Garage garage = new Garage(
                    readGarageId,
                    readGarageName,
                    readGarageAddress,
                    readGarageContact,
                    readGaragePhoneNumber
            );


            // Update the garages file.
            DbWrapperGarage dbWrapperGarage = new DbWrapperGarage(garage);
            dbWrapperGarage.updateDb(garage);


            // Show confirmation message.
            Messages.showMessage(Messages.MESSAGE_INF, "Garage details were updated successfully.");


            // Close the form.
            fGarageEdit.dispose();
        });


        fGarageEdit.setVisible(true);
    }



    private void enableForm(int formIndex) {

        if (formIndex == FORM_GARAGES) {
            tfGarageName.setEnabled(true);
            tfGarageAddress.setEnabled(true);
            tfGarageContact.setEnabled(true);
            tfGaragePhoneNumber.setEnabled(true);
        }
    }


    public void alMaintenanceAdd() {

        JFrame fMaintenanceAdd = uiFramesGenerator.generateFrame(FramesGenerator.F_MAINTENANCE_ADD);

        JLabel lDate               = new JLabel("Date");
        JLabel lGarageName         = new JLabel("Garage name");
        JLabel lMaintenanceType    = new JLabel("Maintenance type");
        JLabel lMaintenanceDetails = new JLabel("Details");

        JTextField tfDate            = new JTextField();
        JTextField tfGarageName      = new JTextField();
        JTextField tfMaintenanceType = new JTextField();
        tfDate.setEnabled(false);

        JButton bAdd      = new JButton("Add");
        JButton bDatePick = new JButton("Choose");

        JTextArea   taDetails  = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(taDetails);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        lDate.setBounds              (20, 20,  150, 20);
        lGarageName.setBounds        (20, 50,  150, 20);
        lMaintenanceType.setBounds   (20, 80,  150, 20);
        lMaintenanceDetails.setBounds(20, 110, 150, 20);

        tfDate.setBounds           (140, 20, 100, 20);
        tfGarageName.setBounds     (140, 50, 100, 20);
        tfMaintenanceType.setBounds(140, 80, 100, 20);

        bAdd.setBounds     (50, 170, 200, 40);
        bDatePick.setBounds(250, 20, 80, 20);

        scrollPane.setBounds(20, 130, 300, 200);




        Container container = fMaintenanceAdd.getContentPane();

        container.add(lDate);
        container.add(lGarageName);
        container.add(lMaintenanceType);
        container.add(lMaintenanceDetails);

        container.add(tfDate);
        container.add(tfGarageName);
        container.add(tfMaintenanceType);

        container.add(bAdd);
        container.add(bDatePick);

        container.add(scrollPane);


        bAdd.addActionListener(e->{

            // Read the form
            String readDate    = tfDate.getText();
            String readType    = tfMaintenanceType.getText();
            String readGarage  = tfGarageName.getText();
            String readDetails = tfGarageName.getText();

            // Validate
            if (readDate == null || readDate.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "Invalid or no date provided.");
                return;
            }
            if (readType == null || readType.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "Invalid or no maintenance type provided.");
                return;
            }
            if (readGarage == null || readGarage.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "Invalid or no garage name provided.");
                return;
            }
            if (readDetails == null || readDetails.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, "Invalid or no details provided.");
                return;
            }


            Maintenance maintenance = new Maintenance(
                    readDate, readGarage, readType, Arrays.asList(readDetails.split("\n"))
            );
            maintenance.addToDb(maintenance);


        });


        bDatePick.addActionListener(e->{
            String date = DatePicker.pick();
            if (date != null) {
                tfDate.setText(date);
            }
        });

        fMaintenanceAdd.setVisible(true);
    }



    public void alMaintenanceEdit() {
        
    }
}




