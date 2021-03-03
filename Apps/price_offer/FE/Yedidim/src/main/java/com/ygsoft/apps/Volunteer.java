package com.ygsoft.apps;


public class Volunteer {

    private int volunteerId, id, activeFlag, vehicleIndex;
    private String townName, nameLast, nameFirst, emailAddress, cellularPhoneNumber, vehicleName;
    private boolean isFirstAider, isHasRecoveryGear, isHasWeapon, isHasWinch;


    public Volunteer(int volunteerId) {
        this.volunteerId = volunteerId;
    }


    public int getVolunteerId() {
        return volunteerId;
    }

    public int getId() {
        return id;
    }

    public String getTownName() {
        return townName;
    }

    public String getNameLast() {
        return nameLast;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return cellularPhoneNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public boolean isFirstAider() {
        return isFirstAider;
    }

    public boolean isHasRecoveryGear() {
        return isHasRecoveryGear;
    }

    public boolean isHasWeapon() {
        return isHasWeapon;
    }

    public boolean isHasWinch() {
        return isHasWinch;
    }


    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String cellularPhoneNumber) {
        this.cellularPhoneNumber = cellularPhoneNumber;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public void setFirstAider(boolean firstAider) {
        isFirstAider = firstAider;
    }

    public void setHasRecoveryGear(boolean hasRecoveryGear) {
        isHasRecoveryGear = hasRecoveryGear;
    }

    public void setHasWeapon(boolean hasWeapon) {
        isHasWeapon = hasWeapon;
    }

    public void setHasWinch(boolean hasWinch) {
        isHasWinch = hasWinch;
    }

    public int getVehicleIndex() {
        return vehicleIndex;
    }

    public void setVehicleIndex(int vehicleIndex) {
        this.vehicleIndex = vehicleIndex;
    }

    public int getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(int activeFlag) {
        this.activeFlag = activeFlag;
    }
}





