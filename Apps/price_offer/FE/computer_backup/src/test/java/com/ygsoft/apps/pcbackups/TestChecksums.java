package com.ygsoft.apps.pcbackups;

import org.junit.Test;
import java.io.File;
import java.util.Objects;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;



public class TestChecksums {

    File file1 = new File(Objects
            .requireNonNull(TestChecksums.class.getClassLoader()
            .getResource("NothingMuch1.txt"))
            .getFile()
    );

    File file2 = new File(Objects
            .requireNonNull(TestChecksums.class.getClassLoader()
            .getResource("NothingMuch2.txt"))
            .getFile()
    );
    File file3 = new File(Objects
            .requireNonNull(TestChecksums.class.getClassLoader()
            .getResource("NothingMuch3.txt"))
            .getFile()
    );



    @Test
    public void testEqualChecksums() throws Exception {
        assertEquals (MD5Checksum.getMD5Checksum(file1), MD5Checksum.getMD5Checksum(file2));
    }



    @Test
    public void testNonEqualChecksums() throws Exception {
        assertNotEquals (MD5Checksum.getMD5Checksum(file1), MD5Checksum.getMD5Checksum(file3));
    }
}
