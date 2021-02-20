package com.yg.apps.cars.ui;

import javax.swing.*;

public class MenusGenerator {

    public MenusGenerator() {}



    public JMenu createMenuGarages(JMenuItem miGaragesAdd, JMenuItem miGaragesEdit, JMenuItem miGaragesExit) {

        JMenu menu = new JMenu("Garages");

        menu.add(miGaragesAdd);
        menu.add(miGaragesEdit);
        menu.addSeparator();
        menu.add(miGaragesExit);

        return menu;
    }



    public JMenu createMenuMaintenance(JMenuItem miMaintenanceAdd, JMenuItem miMaintenanceEdit) {

        JMenu menu = new JMenu("Maintenance");

        menu.add(miMaintenanceAdd);
        menu.add(miMaintenanceEdit);

        return menu;
    }



    public JMenu createMenuCarCare(JMenuItem miCarCareAdd, JMenuItem miCarCareEdit) {

        JMenu menu = new JMenu("Car care");

        menu.add(miCarCareAdd);
        menu.add(miCarCareEdit);

        return menu;
    }
}
