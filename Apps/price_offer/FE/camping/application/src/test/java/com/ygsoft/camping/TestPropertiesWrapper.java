package com.ygsoft.camping;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.ygsoft.camping.enums.Hardcoded;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import com.ygsoft.camping.infra.PropertiesWrapper;

import static org.junit.Assert.*;


public class TestPropertiesWrapper {

    private File validPropertiesFile, invalidPropertiesFile;
    private URL  validUrl   = TestPropertiesWrapper
            .class
            .getClassLoader()
            .getResource("test.properties")
    ;

    private URL  invalidUrl = TestPropertiesWrapper
            .class
            .getClassLoader()
            .getResource("noSuchFile.properties")
    ;



    @Before
    public void initFiles() {

        if (validUrl != null) {
            validPropertiesFile = new File (validUrl.getFile());
        }

        if (invalidUrl != null) {
            invalidPropertiesFile = new File (invalidUrl.getFile());
        }
    }



    @Test
    public void testInvalidPropertiesFile() {
        PropertiesWrapper propertiesWrapper = new PropertiesWrapper(invalidPropertiesFile);
        assertFalse (propertiesWrapper.isParsed());
    }



    @Test
    public void testValidPropertiesFile() {
        PropertiesWrapper propertiesWrapper = new PropertiesWrapper(validPropertiesFile);
        assert(propertiesWrapper.isParsed());
    }



    @Test
    public void testInvalidProperty() {
        PropertiesWrapper propertiesWrapper = new PropertiesWrapper(validPropertiesFile);
        assertEquals(Hardcoded.NA.getText(), propertiesWrapper.get("NoSuchKey"));
    }



    @Test
    public void testValidProperty() {
        PropertiesWrapper propertiesWrapper = new PropertiesWrapper(validPropertiesFile);
        assertEquals("_value", propertiesWrapper.get("_key"));
    }
}
