package com.ygsoft.camping;


import java.awt.*;
import javax.swing.*;
import java.net.URL;



class AppSplashScreen {

    AppSplashScreen(){}



    JFrame generate() {

        // Get the current screen's size.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


        JFrame splashScreen = new JFrame();
        splashScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        splashScreen.setResizable(false);
        splashScreen.setLayout(new BorderLayout());
        splashScreen.setBounds(
                (int)(screenSize.getWidth()/2)  - 300,
                (int)(screenSize.getHeight()/2) - 300,
                800,
                500
        );


        URL url = getClass().getResource("/SplashScreen.jpg");
        Image image = new ImageIcon(url).getImage().getScaledInstance(
                splashScreen.getWidth(),
                splashScreen.getHeight(),
                Image.SCALE_SMOOTH
        );

        ImageIcon icon = new ImageIcon(image);

        JLabel theImage = new JLabel(icon);

        splashScreen.getContentPane().add(theImage);

        return splashScreen;
    }
}

