package com.ygsoft.apps.b2;

import com.ygsoft.apps.b2.enums.HardCoded;
import com.ygsoft.apps.b2.enums.Queries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.sql.*;

public class DbWrapper {

    private final String dbFileName = "computer_backup.db";
    private final File   dbFile     = new File(dbFileName);
    private final String connectionString = "jdbc:sqlite:" + dbFile.getName();

    private Statement  statement;
    private Connection connection;

    private boolean isConnected = false;

    private final static Logger logger = LogManager.getLogger(DbWrapper.class);


    DbWrapper() {
        try {
            Class.forName("org.sqlite.JDBC");
        }
        catch (Exception e) {
            logger.error("Cannot set the cool LNF. Sorry...");
        }
    }


    boolean isDbExists() {
        return dbFile.exists();
    }



    public boolean isConnected() {
        return isConnected;
    }



    public void setConnected(boolean connected) {
        isConnected = connected;
    }



    void initDatabase() throws SQLException {

        try {
            connection  = DriverManager.getConnection(connectionString);
            connection.setAutoCommit(false);
            isConnected = true;
        }
        catch (SQLException e) {
            logger.error("Cannot connect to the DB:");
            logger.error(e.getMessage());
            throw new SQLException(e);
        }

        String sqlInitTable = String.format("CREATE TABLE %s (%s TEXT NOT NULL, %s TEXT NOT NULL)",
                HardCoded.TABLE_NAME_USERS.getText(),
                HardCoded.COLUMN_NAME_USERNAME.getText(),
                HardCoded.COLUMN_NAME_PASSWORD.getText()
        );


        String sqlSetInitialUser = String.format("INSERT INTO %s (%s, %s) VALUES ('%s','%s');",
                HardCoded.TABLE_NAME_USERS.getText(),
                HardCoded.COLUMN_NAME_USERNAME.getText(),
                HardCoded.COLUMN_NAME_PASSWORD.getText(),
                HardCoded.DEFAULT_ADMIN_NAME.getText(),
                HardCoded.DEFAULT_ADMIN_PASSWORD.getText()
        );

        try {
            if (connection.isClosed()) {
                connection  = DriverManager.getConnection(connectionString);
            }

            statement = connection.createStatement();
            statement.executeUpdate(sqlInitTable);
            statement.executeUpdate(sqlSetInitialUser);
            statement.close();
            connection.commit();
            connection.close();
        }
        catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException("Cannot init the DB", e);
        }
    }



    boolean isUserExists(String username) throws SQLException{

        boolean exists = false;

        if ( ! dbFile.exists()) {
            return false;
        }

        try {
            connection  = DriverManager.getConnection(connectionString);
            statement = connection.createStatement();
            String sqlQuery = String.format(
                    Queries.Q_GET_USER_NAME.getQuery(),
                    HardCoded.TABLE_NAME_USERS.getText(),
                    HardCoded.COLUMN_NAME_USERNAME.getText(),
                    username
            );

            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (resultSet != null) {
                while (resultSet.next()) {
                    String name = resultSet.getString(HardCoded.COLUMN_NAME_USERNAME.getText());
                    if (name.equals(username)) {
                        exists = true;
                        break;
                    }
                }
            }
        }
        catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }

        statement.close();
        connection.close();

        return exists;
    }



    boolean isPasswordMatches(String username, String password) throws SQLException {

        boolean matches = false;

        if ( ! dbFile.exists()) {
            return false;
        }

        try {
            connection  = DriverManager.getConnection(connectionString);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sqlQuery = String.format(
                    Queries.Q_GET_USER_NAME.getQuery(),
                    HardCoded.TABLE_NAME_USERS.getText(),
                    HardCoded.COLUMN_NAME_USERNAME.getText(),
                    username
            );

            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String name = resultSet.getString(HardCoded.COLUMN_NAME_USERNAME.getText());
                String pass = resultSet.getString(HardCoded.COLUMN_NAME_PASSWORD.getText());
                if (name.equals(username)) {
                    if (pass.equals(password)) {
                        matches = true;
                        break;
                    }
                }
            }
        }
        catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
        statement.close();
        connection.close();

        return matches;
    }
}
