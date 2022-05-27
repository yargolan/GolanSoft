package com.ygsoft.apps.utilsandwrappers;

import org.junit.Before;
import org.junit.Test;



public class TestShellExecutor {

    private ShellExecutor shellExecutor;
    private ExecutionResults executionResults;


    @Before
    public void initTest() {
        shellExecutor = new ShellExecutor();
    }



    @Test
    public void testEmptyCommandExecution() {
        executionResults = shellExecutor.executeCommand("");
        assert (executionResults.getExitStatus() == 1);
    }



    @Test
    public void testNullCommandExecution() {
        executionResults = shellExecutor.executeCommand(null);
        assert (executionResults.getExitStatus() == 1);
    }



    @Test
    public void testWrongCommandExecution() {
        executionResults = shellExecutor.executeCommand("No_such_command");
        assert (executionResults.getExitStatus() == 2);
    }



    @Test
    public void testValidCommandExecution() {
        if (OperatingSystem.isWindows()) {
            executionResults = shellExecutor.executeCommand("cmd /c \"dir C:\"");
        }
        else {
            executionResults = shellExecutor.executeCommand("ls /");
        }
        assert (executionResults.getExitStatus() == 0);
    }
}
