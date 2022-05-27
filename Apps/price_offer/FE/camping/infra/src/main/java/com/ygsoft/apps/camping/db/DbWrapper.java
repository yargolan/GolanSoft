package com.ygsoft.apps.camping.db;

import com.ygsoft.apps.camping.Participant;
import com.ygsoft.apps.camping.enums.Hardcoded;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DbWrapper {

    private String connectionUrl;

    public DbWrapper(File databaseFile) {
        this.connectionUrl = "jdbc:sqlite:" + databaseFile.getAbsolutePath();
    }



    public void generateTripDatabase () throws Exception {

        String sqlCreateTable = String.format(
                "CREATE TABLE %s "
                        + "(%s PRIMARY KEY NOT NULL,"
                        + " %s TEXT NOT NULL, "
                        + " %s TEXT NOT NULL, "
                        + " %s INT  NOT NULL)",
                Hardcoded.TABLE_NAME_PARTICIPANTS.getText(),
                Hardcoded.COLUMN_PARTICIPANT_INITIAL.getText(),
                Hardcoded.COLUMN_PARTICIPANT_NAME.getText(),
                Hardcoded.COLUMN_PARTICIPANT_EMAIL.getText(),
                Hardcoded.COLUMN_PARTICIPANT_V_INDEX.getText()
        );

        Class.forName("org.sqlite.JDBC");

        Connection connection = DriverManager.getConnection(connectionUrl);

        Statement statement = connection.createStatement();

        statement.executeUpdate(sqlCreateTable);

        statement.close();

        connection.close();
    }



    public boolean isUserExists(Participant participant) throws SQLException {

        boolean isExists = false;

        String sqlQuery = String.format(
                "SELECT * FROM %s",
                Hardcoded.TABLE_NAME_PARTICIPANTS.getText()
        );

        Connection connection = DriverManager.getConnection(connectionUrl);

        connection.setAutoCommit(false);

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            String name = resultSet.getString(Hardcoded.COLUMN_PARTICIPANT_INITIAL.getText());
            if (name.equals(participant.getInitials())) {
                isExists = true;
            }
        }

        statement.close();

        connection.close();

        return isExists;
    }



    public void addParticipant(Participant participant) throws SQLException {

        // Verify that the user is not already in the DB.
        if (isUserExists(participant)) {
            throw new SQLException("The user already exist in the DB.");
        }


        String sqlCommand = String.format(
                "INSERT INTO %s (%s,%s,%s,%s) VALUES ('%s','%s','%s',%d)",
                Hardcoded.TABLE_NAME_PARTICIPANTS.getText(),
                Hardcoded.COLUMN_PARTICIPANT_INITIAL.getText(),
                Hardcoded.COLUMN_PARTICIPANT_NAME.getText(),
                Hardcoded.COLUMN_PARTICIPANT_EMAIL.getText(),
                Hardcoded.COLUMN_PARTICIPANT_V_INDEX.getText(),
                participant.getInitials(),
                participant.getName(),
                participant.getEmailAddress(),
                participant.getVehicleIndex()
        );

        Connection connection = DriverManager.getConnection(connectionUrl);

        connection.setAutoCommit(false);

        Statement statement = connection.createStatement();

        statement.executeUpdate(sqlCommand);

        statement.close();

        connection.commit();

        connection.close();
    }



    public List<Participant> getParticipants() throws SQLException {

        List<Participant> list = new ArrayList<>();

        String sqlQuery = String.format(
                "SELECT * FROM %s",
                Hardcoded.TABLE_NAME_PARTICIPANTS.getText()
        );

        Connection connection = DriverManager.getConnection(connectionUrl);

        connection.setAutoCommit(false);

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            Participant p = new Participant(
                    resultSet.getString(Hardcoded.COLUMN_PARTICIPANT_INITIAL.getText()),
                    resultSet.getString(Hardcoded.COLUMN_PARTICIPANT_NAME.getText()),
                    resultSet.getString(Hardcoded.COLUMN_PARTICIPANT_EMAIL.getText()),
                    resultSet.getInt(Hardcoded.COLUMN_PARTICIPANT_V_INDEX.getText())
            );

            list.add(p);
        }

        statement.close();

        connection.close();

        return list;
    }
}















