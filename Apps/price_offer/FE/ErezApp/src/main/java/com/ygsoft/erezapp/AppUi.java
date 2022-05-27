package com.ygsoft.erezapp;

import javax.swing.*;



public class AppUi {

    JFrame fMainFrame = new JFrame();

    public AppUi() {
        fMainFrame.setTitle("Price offers");
        fMainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fMainFrame.setLayout(null);
        fMainFrame.setBounds(300, 200, 300, 200);

        JMenuBar menuBar = new JMenuBar();

        JMenu mPriceOffer = new JMenu("Price offers");

        JMenuItem miPriceOfferNew = new JMenuItem("New price offer");

        mPriceOffer.add(miPriceOfferNew);

        menuBar.add(mPriceOffer);

        fMainFrame.setJMenuBar(menuBar);

        miPriceOfferNew.addActionListener(e -> priceOfferNew());
    }


    private void priceOfferNew() {
        PriceOfferUi priceOfferUi = new PriceOfferUi();
        priceOfferUi.show();
    }


    public void show() {
        fMainFrame.setVisible(true);
    }

}












