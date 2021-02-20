package com.yg.apps.cars.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Garage {


    @SerializedName("name")
    @Expose
    private final String name;

    @SerializedName("address")
    @Expose
    private final String address;

    @SerializedName("contact")
    @Expose
    private final String contact;

    @SerializedName("phone_number")
    @Expose
    private final String phoneNumber;

    private int id;


    public Garage(int id, String name, String address, String contact, String phoneNumber) {
        this.id          = id;
        this.name        = name;
        this.address     = address;
        this.contact     = contact;
        this.phoneNumber = phoneNumber;
    }


    public String getName() {
        return name;
    }


    public String getAddress() {
        return address;
    }


    public String getContact() {
        return contact;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public int getId() {
        return id;
    }


    public void addToDb() {
        DbWrapperGarage dbWrapperGarage = new DbWrapperGarage(this);
        dbWrapperGarage.addToDb(this);
    }

    public void setId(int id) {
        this.id = id;
    }
}

















