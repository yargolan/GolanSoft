package com.ygsoft.apps.pcbackups;

import java.io.File;
import java.util.Arrays;
import com.google.gson.JsonElement;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import com.ygsoft.apps.pcbackups.enums.HardCoded;



public class TestProfile {

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
    public void testAddToFile() {
        ProfilesWrapperGson profilesWrapperGson = new ProfilesWrapperGson(userProfilesFile, profile);
        profilesWrapperGson.addProfile();
        assertNotNull(profilesWrapperGson);
    }



    @Test
    public void testReadFromFile() {
        ProfilesWrapperGson profilesWrapperGson = new ProfilesWrapperGson(userProfilesFile, profile);

        if (userProfilesFile.exists()) {
            JsonElement je = profilesWrapperGson.readFromFile();
            assertNotNull(je);
        }
    }
}
