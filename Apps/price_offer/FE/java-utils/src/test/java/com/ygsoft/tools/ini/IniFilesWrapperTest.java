package com.ygsoft.tools.ini;

import org.junit.Test;
import org.junit.Before;

import java.io.File;
import static org.junit.Assert.*;


public class IniFilesWrapperTest {

    IniFilesWrapper iniFilesWrapper;

    @Before
    public void setUp() throws Exception {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test.ini").getFile());

        iniFilesWrapper = new IniFilesWrapper(file);
    }



    @Test
    public void testGetSectionName() {

        for (String s : iniFilesWrapper.getSectionNames()) {
            System.out.println(s);
        }

        assertTrue(true);
    }



    @Test
    public void testDumpIni() {

        for (IniSection section : iniFilesWrapper.getSections()) {

            System.out.println("[" + section.getSectionName() + "]");

            for (String key : section.getKeys()) {

                System.out.println(String.format("%s=%s", key, section.getValue(key)));
            }
        }

        assertTrue(true);
    }
}