package com.ygsoft.apps.camping;

import java.util.List;
import java.util.ArrayList;



public class Trip {

    private String name;
    private List<Driver> driversList = new ArrayList<>();



    public Trip(String name) {
        this.name = name;
    }



    public Trip(String name, List<Driver> driversList) {
        this.name = name;
        this.driversList = driversList;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public List<Driver> getDriversList() {
        return driversList;
    }
}
