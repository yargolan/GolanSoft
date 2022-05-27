package com.ygsoft.apps.maintenance;

public class Garage {
    private String name, location, phone, contact;

    public Garage(String name, String location, String phone, String contact) {
        this.name     = name;
        this.phone    = phone;
        this.contact  = contact;
        this.location = location;
    }


    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public String getContact() {
        return contact;
    }
}
