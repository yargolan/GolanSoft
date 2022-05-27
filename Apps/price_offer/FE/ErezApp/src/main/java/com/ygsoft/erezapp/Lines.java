package com.ygsoft.erezapp;

public class Lines {

    public Lines(){}



    public String generateLine(int amount) {

        StringBuilder line = new StringBuilder("-");

        for (int i = 0; i < amount; i++) {
            line.append("-");
        }

        return line.toString();
    }
}
