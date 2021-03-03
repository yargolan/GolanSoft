package com.ygsoft.apps.pcbackups;

import com.ygsoft.apps.pcbackups.enums.HardCoded;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;


public class TestMixedProfile {

    Profile profile = new Profile(
            "test_profile",
            "profile_host",
            "/target/folder",
            Arrays.asList("item_1", "item_2")
    );
    File userProfilesFile = new File(String.format("%s%s%s.json",
            HardCoded.DIR_NAME_PROFILES.getText(),
            File.separatorChar,
            "test_profile"
    ));


    @Test
    public void testPrintProfile() {
        System.out.println(profile._dump());
    }



    @Test
    public void testReadFromFile() {

        MixedProfilesWrapper mixedProfilesWrapper = new MixedProfilesWrapper(userProfilesFile, profile);

        JSONObject jsonObject = null;

        try {
            jsonObject = mixedProfilesWrapper.readFromFile();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(jsonObject);

    }



    @Test
    public void testAddToFile() throws IOException {

        MixedProfilesWrapper mixedProfilesWrapper = new MixedProfilesWrapper(userProfilesFile, profile);

        mixedProfilesWrapper.addToFile();

        assertNotNull(mixedProfilesWrapper);

    }
}
