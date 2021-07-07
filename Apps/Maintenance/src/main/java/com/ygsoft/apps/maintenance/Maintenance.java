package com.ygsoft.apps.maintenance;

import com.ygsoft.apps.DateAndTime;
import com.ygsoft.apps.Messages;
import com.ygsoft.apps.ui.UiWrapper;
import javax.swing.*;
import java.awt.*;


public class Maintenance {

    private final UiWrapper   uiWrapper = new UiWrapper();
    private final InitialData data      = new InitialData();

    private final JComboBox<String> ddGarageName = new JComboBox<>();




    Maintenance(){}



    protected void maintenanceAdd() {

        JFrame f = uiWrapper.generateFrame(
                UiWrapper.FRAME_SIZE_M,
                WindowConstants.DISPOSE_ON_CLOSE,
                HcGeneral.FRAME_TITLE_MAINT_ADD.getText()
        );


        Container container = f.getContentPane();


        // Date
        JLabel lDate           = new JLabel(HcMaint.L_MAINTENANCE_DATE.getText());
        JTextField tfDateDay   = new JTextField();
        JTextField tfDateYear  = new JTextField();
        JTextField tfDateMonth = new JTextField();
        JButton bToday         = new JButton(HcMaint.B_TODAY.getText());
        tfDateDay.setBounds  (130, 20, 50, 25);
        tfDateYear.setBounds (230, 20, 50, 25);
        tfDateMonth.setBounds(180, 20, 50, 25);
        lDate.setBounds      (300, 20, 80, 25);
        bToday.setBounds     (30,  20, 90, 20);

        tfDateDay.setText  (HcMaint.T_DAY.getText());
        tfDateYear.setText (HcMaint.T_YEAR.getText());
        tfDateMonth.setText(HcMaint.T_MONTH.getText());

        container.add(lDate);
        container.add(tfDateDay);
        container.add(tfDateYear);
        container.add(tfDateMonth);
        container.add(bToday);

        bToday.addActionListener(e-> {
            tfDateDay.setText(DateAndTime.getTodayDay());
            tfDateYear.setText(DateAndTime.getTodayYear());
            tfDateMonth.setText(DateAndTime.getTodayMonth());
        });


        // Garage name
        JLabel lGarageName             = new JLabel(HcMaint.L_GARAGE_NAME.getText());
        JButton bNewGarage = new JButton(HcMaint.B_ADD_NEW.getText());

        bNewGarage.setBounds  (30, 60, 90, 20);
        lGarageName.setBounds (300,60, 80, 25);
        ddGarageName.setBounds(130,60, 150,25);

        container.add(lGarageName);
        container.add(ddGarageName);
        for(String name : data.getGarageNames()) {
            ddGarageName.addItem(name);
        }
        container.add(bNewGarage);
        bNewGarage.addActionListener(e->addNewGarage());


        f.setVisible(true);
    }



    private void addNewGarage() {


        JFrame f = uiWrapper.generateFrame(
                UiWrapper.FRAME_SIZE_S,
                WindowConstants.DISPOSE_ON_CLOSE,
                HcMaint.F_TITLE_GARAGE_NEW.getText()
        );


        Container container = f.getContentPane();


        JLabel lGarageName      = new JLabel(HcMaint.L_GARAGE_NAME.getText());
        JLabel lGarageContact   = new JLabel(HcMaint.L_GARAGE_CONTACT.getText());
        JLabel lGarageLocation  = new JLabel(HcMaint.L_GARAGE_LOCATION.getText());
        JLabel lGarageTelephone = new JLabel(HcMaint.L_GARAGE_TELEPHONE.getText());

        JTextField tfGarageName      = new JTextField();
        JTextField tfGarageContact   = new JTextField();
        JTextField tfGarageLocation  = new JTextField();
        JTextField tfGarageTelephone = new JTextField();

        JButton bApprove = new JButton(HcGeneral.B_APPROVE.getText());


        // Locations
        lGarageName.setBounds     (200, 20,  100, 20);
        lGarageLocation.setBounds (200, 50,  100, 20);
        lGarageContact.setBounds  (200, 80,  100, 20);
        lGarageTelephone.setBounds(200, 110, 100, 20);

        tfGarageName.setBounds     (50, 20, 130, 20);
        tfGarageLocation.setBounds (50, 50, 130, 20);
        tfGarageContact.setBounds  (50, 80, 130, 20);
        tfGarageTelephone.setBounds(50, 110,130, 20);

        bApprove.setBounds(80, 160, 100, 40);

        container.add(lGarageName);
        container.add(lGarageContact);
        container.add(lGarageLocation);
        container.add(lGarageTelephone);

        container.add(tfGarageName);
        container.add(tfGarageContact);
        container.add(tfGarageLocation);
        container.add(tfGarageTelephone);

        container.add(bApprove);

        bApprove.addActionListener(e->{
            String readGarageName     = tfGarageName.getText();
            String readGaragePhone    = tfGarageTelephone.getText();
            String readGarageContact  = tfGarageContact.getText();
            String readGarageLocation = tfGarageLocation.getText();

            // Validate.
            if (readGarageName == null || readGarageName.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, HcMessages.E_NO_GARAGE_NAME.getText());
                return;
            }

            if (readGarageLocation == null || readGarageLocation.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, HcMessages.E_NO_GARAGE_LOCATION.getText());
                return;
            }

            if (readGarageContact == null || readGarageContact.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, HcMessages.E_NO_GARAGE_CONTACT.getText());
                return;
            }

            if (readGaragePhone == null || readGaragePhone.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, HcMessages.E_NO_GARAGE_PHONE.getText());
                return;
            }

            f.dispose();

            Garage garage = new Garage(readGarageName, readGarageLocation, readGaragePhone, readGarageContact);

            GarageWrapper garageWrapper = new GarageWrapper();

            try {
                garageWrapper.addToList(garage);
            }
            catch (GarageInternalException gie) {
                Messages.showMessage(Messages.MESSAGE_ERR, gie.getMessage());
                return;
            }

            Messages.showMessage(
                    Messages.MESSAGE_INF,
                    readGarageName + " " + HcMessages.I_ADDED_SUCCESSFULLY.getText()
            );

            ddGarageName.addItem(readGarageName);
        });

        f.setVisible(true);
    }
}
