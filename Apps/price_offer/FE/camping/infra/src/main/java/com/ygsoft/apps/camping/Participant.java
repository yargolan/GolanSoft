package com.ygsoft.apps.camping;

public class Participant {

    private int vehicleIndex;
    private String name, emailAddress, initials;



    public Participant(String initials, String name, String emailAddress, int vehicleIndex) {
        this.name         = name;
        this.initials     = initials;
        this.emailAddress = emailAddress;
        this.vehicleIndex = vehicleIndex;
    }


    public int getVehicleIndex() {
        return vehicleIndex;
    }



    public void setVehicleIndex(int vehicleIndex) {
        this.vehicleIndex = vehicleIndex;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getEmailAddress() {
        return emailAddress;
    }



    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }



    public String getInitials() {
        return initials;
    }



    public void setInitials(String initials) {
        this.initials = initials;
    }
}