package org.ygsoft.apps;


import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CliParser {

    private String[] arguments;
    private Map<String, String> parsedArguments = new HashMap<String, String>();



    public CliParser (String[] args) {
        this.arguments = args;
    }



    public void parse() {

        if (null != arguments && arguments.length > 0) {

            Pattern pattern1 = Pattern.compile(CliParserConstants.CLI_PATTERN_1);
            Pattern pattern2 = Pattern.compile(CliParserConstants.CLI_PATTERN_2);

            for (String currentArgument : arguments) {

                Matcher matcher1 = pattern1.matcher(currentArgument);
                Matcher matcher2 = pattern2.matcher(currentArgument);

                if (matcher1.matches()) {
                    parsedArguments.put(matcher1.group(1), matcher1.group(2));
                }
                else {
                    if (matcher2.matches()) {
                        parsedArguments.put(matcher2.group(1), matcher2.group(2));
                    }
                }
            }
        }
    }



    public boolean isArgumentExists (String argumentName) {
        return parsedArguments.containsKey(argumentName);
    }



    public String getArgument(String argumentName) {
        return (this.isArgumentExists(argumentName)) ? parsedArguments.get(argumentName) : null;
    }
}

















