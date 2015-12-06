package org.spargonaut.maxConnectFour;

import org.junit.Test;
import org.spargonaut.maxConnectFour.parser.ArgumentParser;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameBuilderTest {

    @Test
    public void shouldParseTheArgumentsPassedToItDuringConstruction() {

        String[] commandLineArguments = {
                "arg1",
                "arg2",
                "arg3"
        };

        ArgumentParser mockArgumentParser = mock(ArgumentParser.class);

        new GameBuilder(mockArgumentParser, commandLineArguments);

        verify(mockArgumentParser).parseArguments(commandLineArguments);
    }
}