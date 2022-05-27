package com.ygsoft.apps.camping;

import org.junit.Before;
import org.junit.Test;
import com.ygsoft.apps.camping.enums.Hardcoded;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class TestParticipantsData {

    private ParticipantsData participantsData = new ParticipantsData();




    @Before
    public void setup() throws IOException {

        File dataFile = new File(String.format(
                "%s%s%s%s%s",
                new File(".").getAbsoluteFile().getCanonicalFile().getParent(),
                File.separatorChar,
                Hardcoded.INITIAL_DATA_FOLDER.getText(),
                File.separatorChar,
                Hardcoded.PARTICIPANTS_DATA_FILE.getText()
        ));
        participantsData.parseData(dataFile);
    }



    @Test
    public void testGetValidParticipant() {
        Participant participant = participantsData.getParticipantByInitials("YG");
        assertEquals("Golan Yaron", participant.getName());
    }



    @Test
    public void testGetInvalidParticipant() {
        Participant participant = participantsData.getParticipantByInitials("MM");
        assertNull(participant);
    }
}






