package com.ygsoft.apps.camping;

import org.junit.Test;
import static org.junit.Assert.*;



public class TestVehicles {

    @Test
    public void testValidVehicle1() {

        Vehicle vehicle = new Vehicle("Toyota", "Prado", 1);

        assertNotNull (vehicle.getVehicleMake());
        assertNotNull (vehicle.getVehicleModel());
    }



    @Test
    public void testValidVehicle2() {

        Vehicle vehicle = new Vehicle(null, null, 0);

        vehicle.setVehicleMake("Toyota");
        vehicle.setVehicleModel("Prado");
        vehicle.setVehicleIndex(1);

        assertNotNull (vehicle.getVehicleMake());
        assertNotNull (vehicle.getVehicleModel());
    }
}
