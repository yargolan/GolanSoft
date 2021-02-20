package org.ygsoft.apps;


import org.junit.Test;



public class TestCli {

    @Test
    public void testNoCli() {
        CliParser cliParser = new CliParser(null);
        cliParser.parse();
        assert (null == cliParser.getArgument("xxx"));
    }



    @Test
    public void testExistsWithoutDash() {

        String[] arguments = {"key=value"};
        CliParser cliParser = new CliParser(arguments);
        cliParser.parse();
        assert (cliParser.isArgumentExists("key"));
    }



    @Test
    public void testExistsWithOneDash() {

        String[] arguments = {"-key=value"};
        CliParser cliParser = new CliParser(arguments);
        cliParser.parse();
        assert (cliParser.isArgumentExists("key"));
    }



    @Test
    public void testExistsWithTwoDashes() {

        String[] arguments = {"--key=value"};
        CliParser cliParser = new CliParser(arguments);
        cliParser.parse();
        assert (cliParser.isArgumentExists("key"));
    }



}
