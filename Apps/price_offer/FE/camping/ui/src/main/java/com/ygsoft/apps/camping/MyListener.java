package com.ygsoft.apps.camping;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("---------------------");
        System.out.println(e.getActionCommand());
        System.out.println(e.paramString());
        System.out.println("---------------------");
    }
}
