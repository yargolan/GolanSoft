package com.ygsoft.apps.utilsandwrappers;

public class OperatingSystem {

    public static boolean isWindows() {
        return (System
                .getProperty("os.name")
                .toLowerCase()
                .contains("win")
        );
    }
}
