package com.ygsoft.nav.db;

import com.ygsoft.nav.enums.HardcodedEnum;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DbWrapper {

    private File dbFile;
    private boolean isExistsFlag;


    public DbWrapper(File databaseFile) {
        this.dbFile = databaseFile;
        this.isExistsFlag = databaseFile.exists();
    }



    public void init () throws SQLException {

        if ( ! isExistsFlag) {

            Connection connection = null;
            Statement  statement  = null;

            try {

                Class.forName("org.sqlite.JDBC");

                connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());

                statement = connection.createStatement();
                statement.closeOnCompletion();

                String stringBuilder = "CREATE TABLE %s (" +
                        "%s TEXT PRIMARY KEY NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s TEXT NOT NULL);";
                String sql = String.format (stringBuilder,
                        HardcodedEnum.TABLE_NAME.getText(),
                        HardcodedEnum.COL_POINT_ID.getText(),
                        HardcodedEnum.COL_POINT_AREA.getText(),
                        HardcodedEnum.COL_POINT_H.getText(),
                        HardcodedEnum.COL_POINT_W.getText(),
                        HardcodedEnum.COL_POINT_DESC.getText(),
                        HardcodedEnum.COL_POINT_ENABLED.getText(),
                        HardcodedEnum.COL_POINT_DATE.getText()
                );

                statement.executeUpdate(sql);
                statement.close();
                connection.close();
            }
            catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            finally {
                if (statement != null && ! statement.isClosed()) {
                    statement.close();
                }
                if (connection != null && ! connection.isClosed()) {
                    connection.close();
                }
            }
        }
    }
}
