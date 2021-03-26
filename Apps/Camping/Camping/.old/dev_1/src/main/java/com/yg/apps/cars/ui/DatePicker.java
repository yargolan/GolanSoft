package com.yg.apps.cars.ui;

import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;



public class DatePicker {

    private static String chosenDate;


    private static List<Integer> getYears() {

        List<Integer> list = new ArrayList<>();

        for (int i = 2019; i <2030 ; i++) {
            list.add(i);
        }
        return list;
    }



    private static List<String> getMonths() {

        return Arrays.asList(
                "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        );
    }



    private static List<Integer> getDays() {

        List<Integer> list = new ArrayList<>();

        for (int i = 1; i <=31 ; i++) {
            list.add(i);
        }
        return list;
    }



    public static String pick() {

        // Get the lists;
        List<Integer> days   = getDays();
        List<Integer> years  = getYears();
        List<String> months = getMonths();

        FramesGenerator framesGenerator = new FramesGenerator();
        JDialog d =  framesGenerator.generateDialog(FramesGenerator.D_DATE_PICKER);
        d.setTitle("Pick a date.");


        JLabel lDay   = new JLabel("Day");
        JLabel lYear  = new JLabel("Year");
        JLabel lMonth = new JLabel("Month");

        JButton bChoose = new JButton("Select");



        JComboBox<Integer> ddDay  = new JComboBox<>();
        JComboBox<Integer> ddYear = new JComboBox<>();
        JComboBox<String> ddMonth = new JComboBox<>();

        for (int i : days) {
            ddDay.addItem(i);
        }

        for (int i : years) {
            ddYear.addItem(i);
        }

        for (String s : months) {
            ddMonth.addItem(s);
        }


        lDay.setBounds  (200,  20, 40, 20);
        lYear.setBounds (20, 20, 40, 20);
        lMonth.setBounds(110, 20, 40, 20);

        ddDay.setBounds  (195,  40, 80, 20);
        ddYear.setBounds (15, 40, 90, 20);
        ddMonth.setBounds(105, 40, 80, 20);

        bChoose.setBounds(30, 100, 240, 40);


        Container container = d.getContentPane();

        container.add(lDay);
        container.add(lYear);
        container.add(lMonth);

        container.add(ddDay);
        container.add(ddYear);
        container.add(ddMonth);

        container.add(bChoose);

        bChoose.addActionListener(e-> {
            chosenDate = String.format("%s/%s/%s",
                    ddYear.getSelectedItem(),
                    ddMonth.getSelectedItem(),
                    ddDay.getSelectedItem()
            );
            d.dispose();
        });

        d.setVisible(true);

        return chosenDate;
    }


    public static void main(String[] args) {
        String s = DatePicker.pick();
        System.out.println("s = " + s);
    }
}

