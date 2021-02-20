package com.ygsoft.apps.camping;

import org.junit.Test;
import static org.junit.Assert.*;


public class TestParticipants {

    @Test
    public void testValidParticipant() {

        Participant participant = new Participant(
                "YG",
                "Golan Yaron",
                "yargolan@gmail.com",
                51
        );

        assertNotNull (participant.getName());
        assertNotNull (participant.getEmailAddress());

        assertEquals (51,                   participant.getVehicleIndex());
        assertEquals ("Golan Yaron",        participant.getName());
        assertEquals ("yargolan@gmail.com", participant.getEmailAddress());
    }
}
