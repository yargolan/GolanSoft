package com.ygsoft.nav;

import com.ygsoft.nav.db.DbWrapper;
import com.ygsoft.nav.enums.TextsEnum;

import javax.swing.*;
import java.io.File;
import java.sql.SQLException;


public class NavGameMain {

    private static JFrame fMainFrame;
    private JMenuItem miPointBankAdd, miPointBankDel, miPointBankEdit;



    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

            NavGameMain navGameMain = new NavGameMain();

            // Init the application.
            navGameMain.initApp();


            // Show the UI.
            navGameMain.showUi();
        }
        catch (Exception e) {
            System.err.println("Couldn't set the LNF. Sorry...");
        }
    }



    private NavGameMain () {}



    private void initApp() throws SQLException {
        DbWrapper dbWrapper = new DbWrapper(new File("d.db"));
        dbWrapper.init();
    }



    private void showUi() {

        UiFramesGenerator uiFramesGenerator = new UiFramesGenerator();

        fMainFrame = uiFramesGenerator.generate(UiFramesGenerator.FRAME_MAIN);

        JMenuBar jMenuBar = generateMenuBar();

        fMainFrame.setJMenuBar(jMenuBar);

        setActionListeners();

        fMainFrame.setVisible(true);
    }



    private JMenuBar generateMenuBar() {

        JMenuBar jMenuBar = new JMenuBar();

        JMenu mPointBank = new JMenu(TextsEnum.M_BANK.getText());

        miPointBankAdd  = UiMenuItemGenerator.generate(TextsEnum.MI_BANK_POINT_ADD.getText());
        miPointBankDel  = new JMenuItem(TextsEnum.MI_BANK_POINT_DEL.getText());
        miPointBankEdit = new JMenuItem(TextsEnum.MI_BANK_POINT_EDIT.getText());

        mPointBank.add(miPointBankAdd);
        mPointBank.add(miPointBankDel);
        mPointBank.add(miPointBankEdit);

        jMenuBar.add(mPointBank);

        return jMenuBar;
    }



    private void setActionListeners() {

        AlPoints alPoints = new AlPoints();

        miPointBankAdd.addActionListener (e -> alPoints.pointAdd());

        miPointBankDel.addActionListener (e -> System.out.println(e.getActionCommand()));

        miPointBankEdit.addActionListener(e -> System.out.println(e.getActionCommand()));

    }
}


















