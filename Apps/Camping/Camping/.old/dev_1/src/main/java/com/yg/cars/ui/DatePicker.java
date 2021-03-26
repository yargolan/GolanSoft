//package com.yg.cars.ui;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//
//public class DatePicker {
//
//    private static String date;
//
//
//    private static List<Integer> getYears() {
//
//        List<Integer> list = new ArrayList<>();
//
//        for (int i = 2019; i <2030 ; i++) {
//            list.add(i);
//        }
//        return list;
//    }
//
//
//
//    private static List<String> getMonths() {
//
//        return Arrays.asList(
//                "Jan", "Feb", "Mar", "Apr", "May", "Jun",
//                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
//        );
//    }
//
//
//
//    private static List<Integer> getDays() {
//
//        List<Integer> list = new ArrayList<>();
//
//        for (int i = 1; i <=31 ; i++) {
//            list.add(i);
//        }
//        return list;
//    }
//
//
//
//    public static String pick() {
//
//        // Get the lists;
//        List<Integer> days   = getDays();
//        List<Integer> years  = getYears();
//        List<String> months = getMonths();
//
//        FramesGenerator uiFramesGenerator = new FramesGenerator();
//        JDialog d =  uiFramesGenerator.generateDialog(FramesGenerator.D_DATE_PICKER);
//        d.setTitle("Pick a date.");
//
//
//        JLabel lDay   = new JLabel("Day");
//        JLabel lYear  = new JLabel("Year");
//        JLabel lMonth = new JLabel("Month");
//
//        JButton bChoose = new JButton("Choose !");
//
//
//
//        JComboBox<Integer> ddDay  = new JComboBox<>();
//        JComboBox<Integer> ddYear = new JComboBox<>();
//        JComboBox<String> ddMonth = new JComboBox<>();
//
//        for (int i : days) {
//            ddDay.addItem(i);
//        }
//
//        for (int i : years) {
//            ddYear.addItem(i);
//        }
//
//        for (String s : months) {
//            ddMonth.addItem(s);
//        }
//
//
//        lDay.setBounds  (20,  20, 40, 20);
//        lYear.setBounds (200, 20, 40, 20);
//        lMonth.setBounds(110, 20, 40, 20);
//
//        ddDay.setBounds  (15,  40, 80, 20);
//        ddYear.setBounds (195, 40, 90, 20);
//        ddMonth.setBounds(105, 40, 80, 20);
//
//        bChoose.setBounds(30, 100, 240, 30);
//
//
//        Container container = d.getContentPane();
//
//        container.add(lDay);
//        container.add(lYear);
//        container.add(lMonth);
//
//        container.add(ddDay);
//        container.add(ddYear);
//        container.add(ddMonth);
//
//        container.add(bChoose);
//
//        bChoose.addActionListener(e-> {
//            date = String.format("%s/%s/%s",
//                    ddDay.getSelectedItem(),
//                    ddMonth.getSelectedItem(),
//                    ddYear.getSelectedItem()
//            );
//            d.dispose();
//        });
//
//        d.setVisible(true);
//
//        return date;
//    }
//}
//
