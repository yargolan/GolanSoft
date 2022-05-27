package com.ygsoft.camping;

import javax.swing.*;
import org.junit.Test;


public class TestAppSplashScreen {

    @Test
    public void testSplashScreen() throws InterruptedException {

        AppSplashScreen appSplashScreen = new AppSplashScreen();
        JFrame f = appSplashScreen.generate();
        f.setVisible(true);
        Thread.sleep(500);
        f.dispose();
    }
}

