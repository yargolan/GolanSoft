package com.ygsoft.erezapp;

import org.junit.Test;
import static junit.framework.TestCase.assertNotNull;



public class LinesTest {

    @Test
    public void testLine() {
        Lines lines = new Lines();
        String line = lines.generateLine(10);
        assertNotNull(line);
    }
}
