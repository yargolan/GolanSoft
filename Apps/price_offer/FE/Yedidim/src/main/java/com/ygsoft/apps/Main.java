package com.ygsoft.apps;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.*;
import com.ygsoft.apps.enums.*;

// TODO: Add radio button for active flag

public class Main {

    private JTextField tfVolNameLast, tfVolNameFirst, tfVolIndex, tfVolTownName, tfVolVehicleType;
    private JTextField tfVolEmailAddress, tfVolTelephoneNumber;
    private JCheckBox cbVolRecoveryGear, cbVolWeaponCarrier, cbVolFirstAider, cbVolHasWinch;
    private JButton    bNext, bPrev, bLast, bFirst, bAdd, bDel, bEdit;
    private int volAmount;
    private final File dbFile = new File(HardcodedEnum.DB_FILE.getText());
    private final DbWrapper dbWrapper = new DbWrapper(dbFile);
    private final RectangleWrapper rw = new RectangleWrapper();
    private final VehicleWrapper vehicleWrapper = new VehicleWrapper();


    public Main(){}

    public static void main(String[] args) {

        Main main = new Main();

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            main.runApp();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }



    private void runApp() throws Exception {

        // Init the app.
        initApp();


        // Get the data from the DB.
        getData();


        // Set the UI part
        JFrame fMain = setUi();


        // Add action listeners
        addActionListeners();


        fMain.setVisible(true);
    }



    private void getData() {
        java.util.List<Volunteer> volunteerList = dbWrapper.getAllVolunteers();
    }



    private void addActionListeners() {

        bLast.addActionListener (this::alButtonLast);
        bFirst.addActionListener(e->alButtonFirst());

    }



    private void alButtonFirst() {
        System.out.println("pressed the [First] button");
    }



    private void alButtonLast(ActionEvent e) {
        System.out.println("pressed the [Last] button");
        System.out.println(e.getActionCommand());
        System.out.println(e.getSource().toString());
    }



    private void initApp() throws Exception {

        // Verify the database.
        dbWrapper.initDatabase();


        // Init the vehicles.
        vehicleWrapper.parse();


        // Get the amount of volunteers from the DB.
        volAmount = 0;

    }



    private void setGlobalComponents() {
        // Text fields
        tfVolIndex           = new JTextField();
        tfVolTownName        = new JTextField();
        tfVolNameLast        = new JTextField();
        tfVolNameFirst       = new JTextField();
        tfVolVehicleType     = new JTextField();
        tfVolEmailAddress    = new JTextField();
        tfVolTelephoneNumber = new JTextField();


        // Check boxes
        cbVolHasWinch      = new JCheckBox();
        cbVolFirstAider    = new JCheckBox();
        cbVolRecoveryGear  = new JCheckBox();
        cbVolWeaponCarrier = new JCheckBox();

        // Buttons
        bPrev  = new JButton(TextsEnum.BTN_PREV.getText());
        bNext  = new JButton(TextsEnum.BTN_NEXT.getText());
        bLast  = new JButton(TextsEnum.BTN_LAST.getText());
        bFirst = new JButton(TextsEnum.BTN_FIRST.getText());

        bAdd   = new JButton(TextsEnum.BTN_VOL_ADD.getText());
        bDel   = new JButton(TextsEnum.BTN_VOL_DEL.getText());
        bEdit  = new JButton(TextsEnum.BTN_VOL_EDIT.getText());

        tfVolIndex.setBounds          (rw.get("TF_VOL_INDEX"));
        tfVolTownName.setBounds       (rw.get("TF_TOWN_NAME"));
        tfVolNameLast.setBounds       (rw.get("TF_VOL_NAME_LAST"));
        tfVolNameFirst.setBounds      (rw.get("TF_VOL_NAME_FIRST"));
        tfVolVehicleType.setBounds    (rw.get("TF_VEHICLE_TYPE"));
        tfVolEmailAddress.setBounds   (rw.get("TF_EMAIL_ADDRESS"));
        tfVolTelephoneNumber.setBounds(rw.get("TF_PHONE_NUMBER"));

        cbVolHasWinch.setBounds     (rw.get("CB_HAS_WINCH"));
        cbVolFirstAider.setBounds   (rw.get("CB_FIRST_AIDER"));
        cbVolRecoveryGear.setBounds (rw.get("CB_RECOVERY_GEAR"));
        cbVolWeaponCarrier.setBounds(rw.get("CB_HAS_WEAPON"));

        bNext.setBounds(rw.get("B_BTN_NEXT"));
        bPrev.setBounds(rw.get("B_BTN_PREV"));
        bLast.setBounds(rw.get("B_BTN_LAST"));
        bFirst.setBounds(rw.get("B_BTN_FIRST"));
    }


    private JFrame setUi() {
        
        JFrame f = new JFrame();
            
        f.setLayout(null);
        f.setBounds(400, 200, 600, 600);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set the global UI components.
        setGlobalComponents();


        // Labels
        JLabel lSeparator1         = new JLabel(TextsEnum.SEPARATOR.getText());
        JLabel lSeparator2         = new JLabel(TextsEnum.SEPARATOR.getText());
        JLabel lVolTownName        = new JLabel(TextsEnum.VOL_TOWN_NAME.getText());
        JLabel lVolNameLast        = new JLabel(TextsEnum.VOL_NAME_LAST.getText());
        JLabel lVolHasWinch        = new JLabel(TextsEnum.VOL_HAS_WINCH.getText());
        JLabel lVolFistAider       = new JLabel(TextsEnum.VOL_FIRST_AIDER.getText());
        JLabel lVolNameFirst       = new JLabel(TextsEnum.VOL_NAME_FIRST.getText());
        JLabel lVolVehicleType     = new JLabel(TextsEnum.VOL_VEHICLE_TYPE.getText());
        JLabel lVolIndexNumber     = new JLabel(TextsEnum.VOL_SERIAL_NO.getText());
        JLabel lVolEmailAddress    = new JLabel(TextsEnum.VOL_EMAIL_ADDRESS.getText());
        JLabel lVolRecoveryGear    = new JLabel(TextsEnum.VOL_RECOVERY_EQUIPMENT.getText());
        JLabel lVolWeaponCarrier   = new JLabel(TextsEnum.VOL_WEAPON_CARRIER.getText());
        JLabel lVolTelephoneNumber = new JLabel(TextsEnum.VOL_TELEPHONE_NUMBER.getText());


        // Position
        lVolTownName.setBounds       (rw.get("L_TOWN_NAME"));
        lVolNameLast.setBounds       (rw.get("L_VOL_NAME_LAST"));
        lVolHasWinch.setBounds       (rw.get("L_HAS_WINCH"));
        lVolFistAider.setBounds      (rw.get("L_FIRST_AIDER"));
        lVolNameFirst.setBounds      (rw.get("L_VOL_NAME_FIRST"));
        lVolVehicleType.setBounds    (rw.get("L_VEHICLE_TYPE"));
        lVolIndexNumber.setBounds    (rw.get("L_VOL_INDEX"));
        lVolEmailAddress.setBounds   (rw.get("L_EMAIL_ADDRESS"));
        lVolRecoveryGear.setBounds   (rw.get("L_RECOVERY_GEAR"));
        lVolWeaponCarrier.setBounds  (rw.get("L_HAS_WEAPON"));
        lVolTelephoneNumber.setBounds(rw.get("L_PHONE_NUMBER"));

        lSeparator1.setBounds         (rw.get("L_SEPARATOR_1"));
        lSeparator2.setBounds         (rw.get("L_SEPARATOR_2"));



        // +------------------------------------
        // | Disable all the UI form's parts
        // +------------------------------------
        tfVolIndex.setEnabled(false);
        tfVolTownName.setEnabled(false);
        tfVolNameLast.setEnabled(false);
        tfVolNameFirst.setEnabled(false);
        tfVolVehicleType.setEnabled(false);
        tfVolEmailAddress.setEnabled(false);
        tfVolTelephoneNumber.setEnabled(false);

        bDel.setEnabled  (false);
        bLast.setEnabled (false);
        bNext.setEnabled (false);
        bPrev.setEnabled (false);
        bEdit.setEnabled (false);
        bFirst.setEnabled(false);

        cbVolHasWinch.setEnabled     (false);
        cbVolFirstAider.setEnabled   (false);
        cbVolRecoveryGear.setEnabled (false);
        cbVolWeaponCarrier.setEnabled(false);



        // +------------------------------------
        // | Add the UI parts to the form
        // +------------------------------------
        Container container = f.getContentPane();

        container.add(lSeparator1);
        container.add(lSeparator2);
        container.add(lVolTownName);
        container.add(lVolNameLast);
        container.add(lVolHasWinch);
        container.add(lVolNameFirst);
        container.add(lVolFistAider);
        container.add(lVolIndexNumber);
        container.add(lVolVehicleType);
        container.add(lVolEmailAddress);
        container.add(lVolRecoveryGear);
        container.add(lVolWeaponCarrier);
        container.add(lVolTelephoneNumber);

        container.add(tfVolIndex);
        container.add(tfVolTownName);
        container.add(tfVolNameLast);
        container.add(tfVolNameFirst);
        container.add(tfVolVehicleType);
        container.add(tfVolEmailAddress);
        container.add(tfVolTelephoneNumber);

        container.add(cbVolHasWinch);
        container.add(cbVolFirstAider);
        container.add(cbVolRecoveryGear);
        container.add(cbVolWeaponCarrier);

        container.add(bPrev);
        container.add(bLast);
        container.add(bNext);
        container.add(bFirst);

        container.add(bAdd);
        container.add(bDel);
        container.add(bEdit);



        // +------------------------------------
        // | Set the initial data into the form
        // +------------------------------------
        Volunteer volunteer = dbWrapper.getById(0);

        tfVolIndex.setText(String.valueOf(volunteer.getVolunteerId()));
        tfVolTownName.setText(volunteer.getTownName());
        tfVolNameLast.setText(volunteer.getNameLast());
        tfVolNameFirst.setText(volunteer.getNameFirst());
        tfVolVehicleType.setText(volunteer.getVehicleName());
        tfVolEmailAddress.setText(volunteer.getEmailAddress());
        tfVolTelephoneNumber.setText(volunteer.getPhoneNumber());


        return f;
    }
}



