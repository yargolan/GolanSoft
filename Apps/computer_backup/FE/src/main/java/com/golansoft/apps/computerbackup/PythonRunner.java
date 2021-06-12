package com.golansoft.apps.computerbackup;



public class PythonRunner {

    public static final int COMMAND_RUN = 1;
    public static final int COMMAND_DEL = 2;



    public void runCommand(int commandName, Profile profile) {

        System.out.println("Running from the BE - " + commandName);
        System.out.println("Running profile     - " + profile.getName());
    }
}
