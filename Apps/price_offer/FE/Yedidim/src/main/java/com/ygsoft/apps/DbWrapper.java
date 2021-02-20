package com.ygsoft.apps;

import java.sql.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class DbWrapper {

    File dbFile;
    String connectionString;



    DbWrapper(File databaseFile) {
        this.dbFile = databaseFile;
        this.connectionString = "jdbc:sqlite:" + dbFile.getAbsolutePath();
    }



    void initDatabase() throws Exception {

        Class.forName("org.sqlite.JDBC");

        if (dbFile.exists()) {

            System.out.println("DB already exists.");
        }
        else {

            // Create the volunteers table.
            createVolunteersTable();
        }
    }



    private void createVolunteersTable() throws Exception {


        Connection connection = DriverManager.getConnection(connectionString);

        // Init the volunteers table
        Statement statement = connection.createStatement();

        String sql = "CREATE TABLE tblVolunteers " +
                "(cVolId            INT PRIMARY KEY NOT NULL," +
                " cId               INT  NOT NULL," +
                " cVehicleType      INT  NOT NULL," +
                " cActiveFlag       INT  NOT NULL," +
                " cTownName         TEXT NOT NULL," +
                " cNameLast         TEXT NOT NULL," +
                " cNameFirst        TEXT NOT NULL," +
                " cPhoneNumber      TEXT NOT NULL," +
                " cEmailAddress     TEXT NOT NULL," +
                " cIsFirstAider     TEXT NOT NULL," +
                " cHasRecoveryGear  TEXT NOT NULL," +
                " cIsWeaponCarrier  TEXT NOT NULL," +
                " cHasWinchAttached TEXT NOT NULL)"
        ;

        statement.executeUpdate(sql);

        statement.close();

        connection.close();
    }



    void add(Volunteer volunteer) {
//        connection.setAutoCommit(false);
//
//        statement = connection.createStatement();
//
//        sql = "INSERT INTO tblVolunteers (cVolId,cId,cVehicleType,cActiveFlag,cTownName,cNameLast,cNameFirst,cPhoneNumber,cEmailAddress," +
//            "cIsFirstAider,cHasRecoveryGear,cIsWeaponCarrier,cHasWinchAttached) " +
//            "VALUES (0,123456789,101,1,'Modiin','Israeli','Israel','0541234567','israel.israeli@gmail.com'," +
//            "'Yes','Yes','Yes','Yes');"
//        ;
//
//        statement.executeUpdate(sql);
//
//        statement.close();
//
//        connection.commit();
//
    }



    public Volunteer getById(int volunteerId) {

        Volunteer v = new Volunteer(volunteerId);

        try {

            Connection connection = DriverManager.getConnection(connectionString);

            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();

            String sqlQuery = String.format("SELECT * FROM tblVolunteers WHERE cVolId='%d';", volunteerId);

            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {

                int id        = resultSet.getInt("cId");
                int vehicleId = resultSet.getInt("cVehicleType");

                String town      = resultSet.getString("cTownName");
                String phone     = resultSet.getString("cPhoneNumber");
                String email     = resultSet.getString("cEmailAddress");
                String nameLast  = resultSet.getString("cNameLast");
                String nameFirst = resultSet.getString("cNameFirst");

                boolean isWeapon       = resultSet.getString("cIsWeaponCarrier").equals("Yes");
                boolean isHasWinch     = resultSet.getString("cHasWinchAttached").equals("Yes");
                boolean isFirstAider   = resultSet.getString("cIsFirstAider").equals("Yes");
                boolean isRecoveryGear = resultSet.getString("cHasRecoveryGear").equals("Yes");

                v.setId(id);
                v.setTownName(town);
                v.setNameLast(nameLast);
                v.setNameFirst(nameFirst);
                v.setPhoneNumber(phone);
                v.setEmailAddress(email);
                v.setFirstAider(isFirstAider);
                v.setHasWeapon(isWeapon);
                v.setHasWinch(isHasWinch);
                v.setHasRecoveryGear(isRecoveryGear);
                v.setVehicleName(getVehicleById(vehicleId));
            }

            resultSet.close();

            statement.close();

            connection.close();
        }
        catch (SQLException e) {
            v = null;
        }

        return v;
    }



    private String getVehicleById(int vehicleId) {
        VehicleWrapper vehicleWrapper = new VehicleWrapper();
        vehicleWrapper.parse();
        return vehicleWrapper.getVehicleNameById(vehicleId);
    }



    List<Volunteer> getAllVolunteers() {

        List<Volunteer> list = new ArrayList<>();

        try {

            Connection connection = DriverManager.getConnection(connectionString);

            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();

            String sqlQuery = "SELECT * FROM tblVolunteers WHERE cActiveFlag=1;";

            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {

                Volunteer v = new Volunteer(resultSet.getInt("cVolId"));

                v.setId          (resultSet.getInt("cId"));
                v.setActiveFlag  (resultSet.getInt("cActiveFlag"));
                v.setVehicleIndex(resultSet.getInt("cVehicleType"));

                v.setTownName     (resultSet.getString("cTownName"));
                v.setNameLast     (resultSet.getString("cNameLast"));
                v.setNameFirst    (resultSet.getString("cNameFirst"));
                v.setPhoneNumber  (resultSet.getString("cPhoneNumber"));
                v.setEmailAddress (resultSet.getString("cEmailAddress"));

                v.setHasWinch       (resultSet.getBoolean("cHasWinchAttached"));
                v.setHasWeapon      (resultSet.getBoolean("cIsWeaponCarrier"));
                v.setFirstAider     (resultSet.getBoolean("cIsFirstAider"));
                v.setHasRecoveryGear(resultSet.getBoolean("cHasRecoveryGear"));

                list.add(v);
            }
        }
        catch (SQLException e) {
            list = null;
        }

        return list;
    }
}











