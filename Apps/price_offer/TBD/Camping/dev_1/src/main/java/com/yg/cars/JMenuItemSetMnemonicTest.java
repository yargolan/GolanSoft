//package com.yg.cars;
//
///* JMenuItemSetMnemonicTest.java
// * Copyright (c) Yang.com. All Rights Reserved.
// */
//import java.awt.event.*;
//import javax.swing.*;
//import javax.swing.event.*;
//public class JMenuItemSetMnemonicTest implements ActionListener, MenuKeyListener {
//
//    JFrame myFrame = null;
//
//    public static void main(String[] a) {
//        (new JMenuItemSetMnemonicTest()).test();
//    }
//
//    private void test() {
//        myFrame = new JFrame("Menu Item Mnemonic Test");
//        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        myFrame.setBounds(50,50,250,150);
//        myFrame.setContentPane(new JDesktopPane());
//
//        JMenuBar myMenuBar = new JMenuBar();
//        JMenu myMenu = getFileMenu();
//        myMenuBar.add(myMenu);
//        myMenu = getColorMenu();
//        myMenuBar.add(myMenu);
//        myMenu = getOptionMenu();
//        myMenuBar.add(myMenu);
//
//        JMenuItem myItem = new JMenuItem("Help");
//        myItem.setMnemonic(KeyEvent.VK_H);
//        myItem.addActionListener(this);
//        myItem.addMenuKeyListener(this);
//        myMenuBar.add(myItem);
//
//        myFrame.setJMenuBar(myMenuBar);
//        myFrame.setVisible(true);
//    }
//
//
//    private JMenu getFileMenu() {
//        JMenu myMenu = new JMenu("File");
//        JMenuItem myItem = new JMenuItem("Open");
//        myMenu.add(myItem);
//        myItem = new JMenuItem("Close");
//        myMenu.add(myItem);
//        myMenu.addSeparator();
//        myItem = new JMenuItem("Exit");
//        myMenu.add(myItem);
//        return myMenu;
//    }
//
//    private JMenu getColorMenu() {
//        JMenu myMenu = new JMenu("Color");
//        ButtonGroup myGroup = new ButtonGroup();
//
//        JRadioButtonMenuItem myItem = new JRadioButtonMenuItem("Red");
//        myItem.setSelected(true);
//        myItem.setMnemonic(KeyEvent.VK_R);
//        myItem.addActionListener(this);
//        myItem.addMenuKeyListener(this);
//        myGroup.add(myItem);
//        myMenu.add(myItem);
//
//        myItem = new JRadioButtonMenuItem("Green");
//        myItem.setMnemonic(KeyEvent.VK_G);
//        myItem.addActionListener(this);
//        myItem.addMenuKeyListener(this);
//        myGroup.add(myItem);
//        myMenu.add(myItem);
//
//        myItem = new JRadioButtonMenuItem("Blue");
//        myItem.setMnemonic(KeyEvent.VK_B);
//        myItem.addActionListener(this);
//        myItem.addMenuKeyListener(this);
//        myGroup.add(myItem);
//        myMenu.add(myItem);
//
//        return myMenu;
//    }
//    private JMenu getOptionMenu() {
//        JMenu myMenu = new JMenu("Option");
//        JMenuItem myItem = new JMenuItem("Sound");
//        myMenu.add(myItem);
//        myItem = new JMenuItem("Auto save");
//        myMenu.add(myItem);
//        return myMenu;
//    }
//
//
//    public void actionPerformed(ActionEvent e) {
//        System.out.println("Item clicked: "+e.getActionCommand());
//    }
//
//
//
//    public void menuKeyTyped(MenuKeyEvent e) {
//        MenuElement[] path = e.getPath();
//        JMenuItem item = (JMenuItem) path[path.length-1];
//        System.out.println("Key typed: "+e.getKeyChar()
//                + ", "+ KeyEvent.getKeyText(e.getKeyCode())
//                + " on "+item.getText());
//    }
//    public void menuKeyPressed(MenuKeyEvent e) {
//        MenuElement[] path = e.getPath();
//        JMenuItem item = (JMenuItem) path[path.length-1];
//        System.out.println("Key pressed: "+e.getKeyChar()
//                + ", "+ KeyEvent.getKeyText(e.getKeyCode())
//                + " on "+item.getText());
//    }
//    public void menuKeyReleased(MenuKeyEvent e) {
//        MenuElement[] path = e.getPath();
//        JMenuItem item = (JMenuItem) path[path.length-1];
//        System.out.println("Key released: "+e.getKeyChar()
//                + ", "+ KeyEvent.getKeyText(e.getKeyCode())
//                + " on "+item.getText());
//    }
//}
