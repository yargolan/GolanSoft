package com.ygsoft.apps.camping;

public class Driver {

    private Vehicle vehicle;
    private String fullName, emailAddress;


    public Driver(String fullName, String emailAddress, Vehicle vehicle) {
        this.vehicle      = vehicle;
        this.fullName     = fullName;
        this.emailAddress = emailAddress;
    }


    public Vehicle getVehicle() {
        return vehicle;
    }



    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }



    public String getFullName() {
        return fullName;
    }



    public void setFullName(String fullName) {
        this.fullName = fullName;
    }



    public String getEmailAddress() {
        return emailAddress;
    }



    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
