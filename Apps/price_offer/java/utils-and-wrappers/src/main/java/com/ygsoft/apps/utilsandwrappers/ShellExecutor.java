package com.ygsoft.apps.utilsandwrappers;


import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;



public class ShellExecutor {


    public ShellExecutor(){}



    public ExecutionResults executeCommand (String command) {

        if (command == null || command.isEmpty()) {
            return new ExecutionResults(
                    1,
                    Collections.singletonList("Please look into the STDERR"),
                    Collections.singletonList("No command provided.")
            );
        }


        // Execute the command, parse the STDOUT/STDERR
        List<String> stderrOutput = new ArrayList<>();
        List<String> stdoutOutput = new ArrayList<>();


        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            // Execute the command.
            int exitStatus  = process.exitValue();


            // Parse the output of the command.
            BufferedReader stdOutput = new BufferedReader(new InputStreamReader(
                    process.getInputStream())
            );
            BufferedReader stdError  = new BufferedReader(new InputStreamReader(
                    process.getErrorStream())
            );


            String s;
            while ((s = stdOutput.readLine()) != null) {
                stdoutOutput.add(s);
            }


            // read the error from the command.
            while ((s = stdError.readLine()) != null) {
                stderrOutput.add(s);
            }


            return new ExecutionResults(exitStatus, stdoutOutput, stderrOutput);
        }
        catch (IOException | InterruptedException e) {
            return new ExecutionResults(
                    2,
                    Collections.singletonList("Please look into the STDERR"),
                    Collections.singletonList("An error occurred while running the command.")
            );
        }
    }
}

