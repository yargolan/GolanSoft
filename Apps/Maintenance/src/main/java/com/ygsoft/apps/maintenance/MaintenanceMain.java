package com.ygsoft.apps.maintenance;

import javax.swing.*;

import com.ygsoft.apps.Messages;
import com.ygsoft.apps.ui.UiWrapper;

import java.io.File;


public class MaintenanceMain {

    private final UiWrapper uiWrapper = new UiWrapper();
    private JFrame mainFrame;
    private JMenu mMaintenance, mReports;
    private JMenuItem miMaintAdd, miMaintExit;
    private JMenuItem miReportCreate, miReportGet;

    private MaintenanceMain() {}



    public static void main(String[] args) {
        MaintenanceMain m = new MaintenanceMain();
        m.initApp();
        m.setUi();
    }



    private void initApp() {
        File data = new File("Data");
        if (!data.exists()) {
            if(!data.mkdirs()) {
                Messages.exitWithError("Cannot create the 'data' folder. Abort", true);
            }
        }
    }



    private void alMaintExit() {
        boolean answer = uiWrapper.areYouSure(HcGeneral.MESSAGE_R_U_SURE.getText());
        if (answer) {
            mainFrame.dispose();
        }
    }



    private void alMaintAdd() {
        MaintenanceUi m = new MaintenanceUi();
        m.maintenanceAdd();
    }



    private void alReportNew() {
        System.out.println("Reports --> add");
    }



    private void alReportGet() {
        System.out.println("Reports --> add");
    }



    private void setUi() {
        mainFrame = uiWrapper.generateFrame(
                UiWrapper.FRAME_SIZE_XL,
                WindowConstants.EXIT_ON_CLOSE,
                HcGeneral.FRAME_TITLE_MAIN.getText()
        );

        JMenuBar mb = setMenuBar();

        mainFrame.setJMenuBar(mb);


        // Set action listeners
        miMaintAdd.addActionListener(e->alMaintAdd());
        miMaintExit.addActionListener(e->alMaintExit());
        miReportGet.addActionListener(e->alReportGet());
        miReportCreate.addActionListener(e->alReportNew());


        mainFrame.setVisible(true);
    }



    private JMenuBar setMenuBar() {

        JMenuBar mb = new JMenuBar();

        // Set the first level menus
        mReports     = new JMenu(HcGeneral.MENU_TITLE_REPORTS.getText());
        mMaintenance = new JMenu(HcGeneral.MENU_TITLE_MAINTENANCE.getText());


        // Set the menu items - MaintenanceMain
        miMaintAdd  = new JMenuItem(HcGeneral.MENU_ITEM_TITLE_NEW.getText());
        miMaintExit = new JMenuItem(HcGeneral.MENU_ITEM_TITLE_EXIT.getText());
        mMaintenance.add(miMaintAdd);
        mMaintenance.addSeparator();
        mMaintenance.add(miMaintExit);



        // Set the menu items - Reports
        miReportGet    = new JMenuItem(HcGeneral.MENU_ITEM_TITLE_GET.getText());
        miReportCreate = new JMenuItem(HcGeneral.MENU_ITEM_TITLE_NEW.getText());
        mReports.add(miReportCreate);
        mReports.add(miReportGet);

        mb.add(mMaintenance);
        mb.add(mReports);

        return mb;
    }
}
