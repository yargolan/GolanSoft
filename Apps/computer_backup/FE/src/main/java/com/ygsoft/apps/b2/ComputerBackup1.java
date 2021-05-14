package com.ygsoft.apps.b2;

import javax.swing.*;
import java.sql.SQLException;



public class ComputerBackup1 {

    private final DbWrapper dbWrapper = new DbWrapper();


    public static void main(String[] args) throws Exception {

        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

        ComputerBackup1 computerBackup1 = new ComputerBackup1();

        computerBackup1.runApp();
    }



    public ComputerBackup1() {}



    private void runApp() throws SQLException{

        if (dbWrapper.isDbExists()) {
            System.out.println("DB file exists.");
        }
        else {
            try {
                dbWrapper.initDatabase();
            }
            catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException(e);
            }
        }


//        // Try to login.
//        Login login = new Login();
//
//        login.performLogin();
//
//        if (login.isLoginSuccessful()) {
//            System.out.println("YES");
//        }
//        else {
//            System.out.println("NO");
//        }
    }
}

