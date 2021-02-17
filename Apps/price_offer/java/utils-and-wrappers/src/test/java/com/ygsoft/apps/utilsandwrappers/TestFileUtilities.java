package com.ygsoft.apps.utilsandwrappers;

import org.junit.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.fail;



public class TestFileUtilities {

    @Test
    public void testReadNoSuchFile() {
        FileUtilities fileUtilities = new FileUtilities("No_such_file.txt");
        try {
            List<String> readFile = fileUtilities.read();
            for (String line : readFile) {
                System.out.println(line);
            }
            fail ("How can you read a non-existing file ???");
        }
        catch (IOException e) {
            assert (true);
        }
    }
}
