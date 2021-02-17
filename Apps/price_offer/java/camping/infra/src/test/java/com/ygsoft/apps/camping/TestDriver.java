package com.ygsoft.apps.camping;

import org.junit.Test;
import static org.junit.Assert.*;



public class TestDriver {


    Vehicle vehicle = new Vehicle("Toyota", "Prado", 0);


    @Test
    public void testValidDriver1() {

        Driver driver = new Driver ("Golan Yaron", "yargolan@gmail.com", vehicle);

        assertNotNull (driver.getFullName());
        assertNotNull (driver.getEmailAddress());
        assertNotNull (driver.getVehicle().toString());
    }



    @Test
    public void testValidVehicle2() {

        Driver driver = new Driver(null, null, null);

        driver.setVehicle(vehicle);
        driver.setFullName("Golan Yaron");
        driver.setEmailAddress("yargolan@gmail.com");

        assertNotNull (driver.getFullName());
        assertNotNull (driver.getEmailAddress());
        assertNotNull (driver.getVehicle().toString());
    }
}
