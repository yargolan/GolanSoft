package com.ygsoft.apps.utilsandwrappers;

import java.util.List;

public class ExecutionResults {


    private int exitStatus;
    private List<String> stdout, stderr;


    public ExecutionResults(int exitStatus, List<String> stdout, List<String> stderr) {
        this.exitStatus = exitStatus;
        this.stdout = stdout;
        this.stderr = stderr;
    }


    public int getExitStatus() {
        return exitStatus;
    }



    public List<String> getStdout() {
        return stdout;
    }



    public List<String> getStderr() {
        return stderr;
    }
}
