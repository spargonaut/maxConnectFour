package org.spargonaut.maxConnectFour;

import org.spargonaut.maxConnectFour.parser.ArgumentParser;

public class GameBuilder {

    private final ArgumentParser argumentParser;

    public GameBuilder(ArgumentParser mockArgumentParser, String[] commandLineArguments) {
        this.argumentParser = mockArgumentParser;
        parseInputArguments(commandLineArguments);
    }

    private void parseInputArguments(String[] arguments) {
        argumentParser.parseArguments(arguments);
    }
}
