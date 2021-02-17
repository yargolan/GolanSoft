package com.yg.apps.cars;



public class AppHelper {


    public AppHelper() { }



    public static boolean is_under_dev() {
        String value = System.getenv("UNDER_DEV");
        return (value != null && value.toLowerCase().equals("true"));
    }
}

