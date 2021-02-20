package com.ygsoft.apps.camping;

import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import com.ygsoft.apps.camping.db.DbWrapper;
import com.ygsoft.apps.camping.enums.Hardcoded;
import com.ygsoft.apps.camping.enums.HebrewTitles;


public class MainUi extends JFrame {

    // Participants lists.
    private ParticipantsData  participantsData = new ParticipantsData();
    private List<Participant> optionalParticipants = new ArrayList<>();
    private List<Participant> predefinedParticipantsList;
    private List<Participant> assignedParticipantsList = new ArrayList<>();



    private String selectedTripName = "";

    private boolean isNewTrip = false;

    private JMenuItem miTripNameExit, miTripNameChoose;
    private JMenuItem miParticipantsAdd, miParticipantsView, miParticipantsDel;
    private JComboBox<String> ddParticipantName;

    private DbWrapper currentTripParticipantsDb;

    private final JFrame fMainUi = new JFrame();
    private final String mainFrameTitle = String.format("%s%s",
            HebrewTitles.F_MAIN_UI.getText(),
            Hardcoded.APPLICATION_VERSION.getText()
    );





    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            MainUi mainUi = new MainUi();
            mainUi.createAndShowGUI();
        });
    }


    void createAndShowGUI() {

        fMainUi.setSize(1000, 500);
        fMainUi.setFont(new Font("Lucida Sans Regular", Font.BOLD, 22));
        fMainUi.setTitle(mainFrameTitle);
        fMainUi.setLocation(500, 200);
        fMainUi.setIconImage(UiFramesGenerator.framesIcon);
        fMainUi.setResizable(false);
        fMainUi.setUndecorated(false);
        fMainUi.setLocationRelativeTo(null);
        fMainUi.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        fMainUi.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JMenuBar menuBar = setMenuBar();

        fMainUi.setJMenuBar(menuBar);

        fMainUi.setVisible(true);


        // Set the action listeners.
        setMenuItemsActionListeners();
    }


    private JMenuBar setMenuBar() {

        // Set the main menu bar.
        JMenuBar mb = new JMenuBar();


        // Set the main menus.
        JMenu mTripName = new JMenu(HebrewTitles.M_TRIP_NAMES.getText());
        JMenu mParticipants = new JMenu(HebrewTitles.M_TRIP_PARTICIPANTS.getText());

        mTripName.setFont(new Font("Arial", Font.BOLD, 14));
        mParticipants.setFont(new Font("Arial", Font.BOLD, 14));


        // Set the menu items for "trip name".
        miTripNameExit = new JMenuItem(HebrewTitles.MI_TRIP_NAMES_EXIT.getText());
        miTripNameChoose = new JMenuItem(HebrewTitles.MI_TRIP_NAMES_SELECT.getText());


        // Set the menu items for "Participants".
        miParticipantsAdd = new JMenuItem(HebrewTitles.MI_PARTICIPANTS_ADD.getText());
        miParticipantsDel = new JMenuItem(HebrewTitles.MI_PARTICIPANTS_DEL.getText());
        miParticipantsView = new JMenuItem(HebrewTitles.MI_PARTICIPANTS_VIEW.getText());
        miParticipantsAdd.setEnabled(false);
        miParticipantsDel.setEnabled(false);
        miParticipantsView.setEnabled(false);


        // Set margins for the menus
        mTripName.setMargin(new Insets(10, 10, 10, 10));


        // Add the menu items for 'trip name'
        mTripName.add(miTripNameChoose);
        mTripName.addSeparator();
        mTripName.add(miTripNameExit);


        // Add the menu items for 'participants'
        mParticipants.add(miParticipantsAdd);
        mParticipants.add(miParticipantsView);
        mParticipants.add(miParticipantsDel);


        // Set the menus in the menu bar.
        mb.add(mTripName);
        mb.add(mParticipants);


        return mb;
    }


    private void setMenuItemsActionListeners() {

        // Trips -> Exit
        miTripNameExit.addActionListener(e -> fMainUi.dispose());


        // Trips -> Select
        miTripNameChoose.addActionListener(e -> chooseTripNameUi());


        // Participants -> Add
        miParticipantsAdd.addActionListener(e -> addParticipantUi());


        // Participants -> View
        miParticipantsView.addActionListener(e -> viewParticipantUi());
    }


    private void viewParticipantUi() {

        // Just to verify - Trip name is selected. Right?
        assert (!selectedTripName.isEmpty());

        // Get the list of participants from the database.
        List<Participant> participantsList;

        try {
            participantsList = currentTripParticipantsDb.getParticipants();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return;
        }


        if (participantsList.isEmpty()) {
            Messages.showMessage(Messages.MESSAGE_INF, HebrewTitles.M_NO_PARTICIPANTS_YET.getText());
            return;
        }

        List<String> participantNames = new ArrayList<>();

        for (Participant p : participantsList) {
            participantNames.add(p.getName());
        }

        // Sort the list.
        Collections.sort(participantNames);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < participantNames.size(); i++) {
            int index = i + 1;
            stringBuilder.append(String.format("%-2d.  %s", index, participantNames.get(i)));
            stringBuilder.append(System.lineSeparator());
        }


        JFrame fViewParticipants = UiFramesGenerator.generate(UiFramesGenerator.FRAME_PARTICIPANT_VIEW);
        JLabel label = new JLabel(stringBuilder.toString());
        label.setBounds(30, 30, 150, 400);

        fViewParticipants.getContentPane().add(label);

        fViewParticipants.setVisible(true);
    }


    private void chooseTripNameUi() {

        // Get the list of existing trips.
        TripName tripName = new TripName();
        File existingTripsFolder = new File(Hardcoded.EXISTING_TRIPS_FOLDER.getText());
        List<File> existingTripsNames = tripName.getExistingTripsNames(existingTripsFolder);


        JFrame fTripNameSelect = UiFramesGenerator.generate(UiFramesGenerator.FRAME_TRIP_NAME);

        JLabel lTripName = new JLabel(HebrewTitles.L_TRIP_NAME.getText());

        JComboBox<String> ddTripName = new JComboBox<>();

        JButton bSelectTrip = new JButton(HebrewTitles.B_TRIP_SELECT.getText());

        lTripName.setBounds(400, 20, 100, 20);
        ddTripName.setBounds(230, 20, 150, 20);
        bSelectTrip.setBounds(130, 20, 80, 20);

        fTripNameSelect.getContentPane().add(lTripName);
        fTripNameSelect.getContentPane().add(ddTripName);
        fTripNameSelect.getContentPane().add(bSelectTrip);


        // Insert the list of existing trips to the drop down list.
        existingTripsNames.sort(Collections.reverseOrder());
        for (File currentTripName : existingTripsNames) {
            ddTripName.addItem(currentTripName.getName().replace(".db", ""));
        }
        // Add the option for a new trip as the last option.
        ddTripName.insertItemAt(HebrewTitles.T_GENERATE_NEW.getText(), existingTripsNames.size());


        // Verify that the "add trip" option is selected by default.
        ddTripName.setSelectedIndex(0);


        fTripNameSelect.setVisible(true);


        bSelectTrip.addActionListener(e -> {

            String selection = (String) ddTripName.getSelectedItem();

            if (selection == null) {
                return;
            }
            else {
                selectedTripName = selection;
            }


            // Handle the request for a new trip name.
            if (selection.equals(HebrewTitles.T_GENERATE_NEW.getText())) {

                isNewTrip = true;

                // todo: move the getters into the trip name
                String guessedTripName = tripName.guessNextTripName(
                        tripName.getCurrentMonth(),
                        tripName.getCurrentYear()
                );

                String result = (String) JOptionPane.showInputDialog(
                        null,
                        "Please set the trip's name",
                        "Trip name",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        null,
                        guessedTripName
                );

                if (result != null) {

                    // Set the selected trip name in the title.
                    selectedTripName = result;
                }
            }


            // Close the UI window.
            fTripNameSelect.dispose();


            // Set the trip's name in the title
            fMainUi.setTitle(String.format("%s    -     %s", mainFrameTitle, selectedTripName));


            // Enable the rest of the menus.
            miParticipantsAdd.setEnabled(true);
            miParticipantsView.setEnabled(true);
            miParticipantsDel.setEnabled(true);


            // Generate a database for the current trip.
            File currentTripDatabase = new File(String.format(
                    "%s%s%s.db",
                    Hardcoded.EXISTING_TRIPS_FOLDER.getText(),
                    File.separatorChar,
                    selectedTripName
            ));

            // Verify that the database exists. Create if initial.
            currentTripParticipantsDb = new DbWrapper(currentTripDatabase);
            if (isNewTrip) {

                try {

                    // Verify the path and the file itself.
                    FilesWrapper.touch(currentTripDatabase);

                    currentTripParticipantsDb.generateTripDatabase();
                }
                catch (Exception ex) {
                    Messages.showMessage(Messages.MESSAGE_ERR, "Cannot create the DB: " + ex.getMessage()
                    );
                    ex.printStackTrace();
                }
            }
            else {
                if (!currentTripDatabase.exists()) {
                    Messages.showMessage(Messages.MESSAGE_ERR, "The DB is missing.");
                }
            }
        });
    }


    private List<Participant> getPredefinedParticipantsList() {

        // Set the participants JSON file.
        File participantsFile = FilesWrapper.getDataFile(FilesWrapper.DATA_FILE_PARTICIPANTS);


        // Parse the participants data from the JSON file.
        participantsData.parseData(participantsFile);


        return participantsData.getPreDefinedParticipant();
    }


    private List<Participant> getOptionalParticipants(List<Participant> fullParticipantsList) {

        // Get the listed participants from the DB.
        List<Participant> alreadyListed;
        try {
            alreadyListed = currentTripParticipantsDb.getParticipants();
        }
        catch (SQLException e) {
            e.printStackTrace();
            Messages.showMessage(Messages.MESSAGE_ERR, e.getMessage());
            return null;
        }


        // Weed out the 'already listed' from the full list.
        if (alreadyListed.size() != 0) {
            for (Participant p : alreadyListed) {
                fullParticipantsList.removeIf(n -> (
                        n.getInitials().equals(p.getInitials())
                ));
            }

        }

        return fullParticipantsList;
    }


    private void addParticipantUi() {

        // Get the pre-defined participants.
        predefinedParticipantsList = getPredefinedParticipantsList();


        // Get the list of optional (not listed yet) participants.
        optionalParticipants = getOptionalParticipants(predefinedParticipantsList);


        // +---------------------------
        // | Generate the UI part
        // +---------------------------
        JFrame fAddParticipant = UiFramesGenerator.generate(UiFramesGenerator.FRAME_PARTICIPANT_ADD);

        // Participant name.
        JLabel lTripName = new JLabel(HebrewTitles.L_PARTICIPANT_NAME.getText());
        JButton bAddParticipant = new JButton(HebrewTitles.B_GEN_SELECT.getText());
        ddParticipantName = new JComboBox<>();


        bAddParticipant.setBounds(130, 20, 80, 20);
        lTripName.setBounds(400, 20, 100, 20);
        ddParticipantName.setBounds(230, 20, 150, 20);

        fAddParticipant.getContentPane().add(lTripName);
        fAddParticipant.getContentPane().add(bAddParticipant);
        fAddParticipant.getContentPane().add(ddParticipantName);


        // Sort the list of optional participants, by the name.
        if (optionalParticipants != null && optionalParticipants.size() > 0) {
            List<String> names = new ArrayList<>();
            for (Participant p : optionalParticipants) {
                names.add(p.getName());
            }
            Collections.sort(names);

            // Insert the list of optional participants to the drop-down list.
            for (String s : names) {
                ddParticipantName.addItem(s);
            }
        }


        // +-------------------------------------------------
        // | Add the action listener to the 'select' button
        // +-------------------------------------------------
        bAddParticipant.addActionListener(e -> alParticipantAdd());

        fAddParticipant.setVisible(true);
    }



    private void alParticipantAdd() {

        // Get the participant's name from the drop-down.
        String readParticipant = (String) ddParticipantName.getSelectedItem();
        if (readParticipant == null || readParticipant.isEmpty()) {
            return;
        }


        // Generate a 'Participant' object from the name.
        Participant participant = participantsData.getParticipantByName(readParticipant);
        if (participant == null) {
            System.err.println("Participants data was not parsed yet?");
        }


        // Add the participant to the 'assigned' list.
        assignedParticipantsList.add(participant);


        // Add the new participant to the DB.
        try {
            currentTripParticipantsDb.addParticipant(participant);
        }
        catch (SQLException ex) {
            Messages.showMessage(Messages.MESSAGE_ERR, ex.getMessage());
            return;
        }


        // Show a confirmation message.
        Messages.showMessage(Messages.MESSAGE_INF, String.format(
                HebrewTitles.M_USER_ADDED_SUCCESSFULLY.getText(),
                readParticipant
        ));


        // Refresh the list of participants, without the newly added participant.
        List<Participant> filteredList = ParticipantsFilter.filter(
                predefinedParticipantsList,assignedParticipantsList
        );


        // Clean previous names.
        ddParticipantName.removeAllItems();


        for (Participant p : filteredList) {

            if (optionalParticipants != null && optionalParticipants.size() > 0) {

                List<String> names = new ArrayList<>();

                for (Participant current : optionalParticipants) {
                    names.add(current.getName());
                }

                Collections.sort(names);

                // Insert the list of optional participants to the drop-down list.
                for (String s : names) {
                    ddParticipantName.addItem(s);
                }
            }
        }
    }
}








        //List<Participant> finalAvailableParticipants = availableParticipants;
//        AtomicReference<List<Participant>> finalAvailableParticipants = new AtomicReference<>();
//        finalAvailableParticipants.set(availableParticipants);
//
//
//        bAddParticipant.addActionListener(ee -> {
//
//            // Get the participant's name from the drop-down.
//            String readParticipant = (String)ddParticipantName.getSelectedItem();
//
//
//            // Generate a 'Participant' object from the name.
//            Participant participant = participantsData.getParticipantByName(readParticipant);
//            if (participant == null) {
//                System.err.println("Participants data was not parsed yet?");
//            }
//
//
//            try {
//                // Add the new participant to the DB.
//                currentTripParticipantsDb.addParticipant(participant);
//            }
//            catch (SQLException ex) {
//                Messages.showMessage(Messages.MESSAGE_ERR, ex.getMessage());
//                return;
//            }
//
//
//            // Add the current participant into the list of listed ones.
//            li
//
//
//
//
//            // Show a confirmation message.
//            Messages.showMessage(Messages.MESSAGE_INF, String.format(
//                    HebrewTitles.M_USER_ADDED_SUCCESSFULLY.getText(),
//                    readParticipant
//            ));
//
//
//            // Clean previous names.
//            ddParticipantName.removeAllItems();
//
//
//            // Refresh the list of participants, without the newly added participant.
//            List<Participant> filteredList = ParticipantsFilter.filter(
//                    finalAvailableParticipants.get(),
//                    participant
//            );
//
//            finalAvailableParticipants.set(filteredList);
//
//
//            // Insert the names from the filtered list.
//            for (Participant p : filteredList) {
//                ddParticipantName.addItem(p.getName());
//            }
//        });

















