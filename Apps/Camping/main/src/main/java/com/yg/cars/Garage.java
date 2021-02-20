package com.yg.cars;

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


    public Garage (String name, String address, String contact, String phoneNumber) {
        this.name        = name;
        this.address     = address;
        this.contact     = contact;
        this.phoneNumber = phoneNumber;
    }



    public void validate() throws InternalErrorException{
        if (name == null || name.isEmpty()) {
            throw new InternalErrorException("No garage name provided.");
        }

        if (address == null || address.isEmpty()) {
            throw new InternalErrorException("No garage address provided.");
        }

        if (contact == null || contact.isEmpty()) {
            throw new InternalErrorException("No garage contact provided.");
        }

        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new InternalErrorException("No garage phoneNumber provided.");
        }
    }
}
