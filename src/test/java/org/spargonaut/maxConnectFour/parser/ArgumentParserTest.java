package org.spargonaut.maxConnectFour.parser;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.spargonaut.maxConnectFour.PlayMode;
import org.spargonaut.maxConnectFour.players.PlayerIdentifier;

import static org.junit.Assert.assertEquals;

public class ArgumentParserTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldThrowAnExceptionWhenLessThanFourArgumentsAreGiven() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Four command-line arguments are needed:\n"
                + "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
                + " or:  java [program name] one-move [input_file] [output_file] [depth]\n");

        ArgumentParser argumentParser = new ArgumentParser();
        String[] arguments = new String[3];
        argumentParser.parseArguments(arguments);
    }

    @Test
    public void shouldParseThePlayModeToInteractiveFromTheFirstArgument() {
        String[] arguments = new String[4];
        arguments[0] = "interactive";
        arguments[1] = "path/to/some/file.txt";
        arguments[2] = "Human";
        arguments[3] = "3";

        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArguments(arguments);
        PlayMode actualPlayMode = argumentParser.getPlayMode();

        assertEquals(actualPlayMode, PlayMode.INTERACTIVE);
    }

    @Test
    public void shouldParseThePlayModeToOneMoveFromTheFirstArgument() {
        String[] arguments = new String[4];
        arguments[0] = "one-move";
        arguments[1] = "path/to/some/file.txt";
        arguments[2] = "Human";
        arguments[3] = "3";

        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArguments(arguments);
        PlayMode actualPlayMode = argumentParser.getPlayMode();

        assertEquals(actualPlayMode, PlayMode.ONE_MOVE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAnInvalidPlayModeIsGiven() {
        String[] arguments = new String[4];
        arguments[0] = "floof";
        arguments[1] = "path/to/some/file.txt";
        arguments[2] = "Human";
        arguments[3] = "3";

        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArguments(arguments);
    }

    @Test
    public void shouldParseTheFilnameAndPathForTheInputGameFileAsTheSecondArgument() {
        String[] arguments = new String[4];
        arguments[0] = "one-move";
        arguments[1] = "path/to/some/file.txt";
        arguments[2] = "Human";
        arguments[3] = "3";

        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArguments(arguments);

        String actualFileNameAndPath = argumentParser.getInputGameFile();
        String expectedFileNameAndPath = "path/to/some/file.txt";

        assertEquals(actualFileNameAndPath, expectedFileNameAndPath);
    }

    @Test
    public void shouldParseTheNextPlayerAsHumanFromTheThirdArgumentStartingWithALowercaseLetterWhenInInteractiveMode() {
        String[] arguments = new String[4];
        arguments[0] = "interactive";
        arguments[1] = "someFile.txt";
        arguments[2] = "human";
        arguments[3] = "3";

        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArguments(arguments);
        PlayerIdentifier player = argumentParser.getNextPlayer();

        assertEquals(player, PlayerIdentifier.HUMAN);
    }

    @Test
    public void shouldParseTheNextPlayerAsHumanFromTheThirdArgumentStartingWithACapitalLetterWhenInInteractiveMode() {
        String[] arguments = new String[4];
        arguments[0] = "interactive";
        arguments[1] = "someFile.txt";
        arguments[2] = "Human";
        arguments[3] = "3";

        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArguments(arguments);
        PlayerIdentifier player = argumentParser.getNextPlayer();

        assertEquals(player, PlayerIdentifier.HUMAN);
    }

    @Test
    public void shouldParseTheNextPlayerAsComputerFromTheThirdArgumentStartingWithALowercaseLetterWhenInInteractiveMode() {
        String[] arguments = new String[4];
        arguments[0] = "interactive";
        arguments[1] = "someFile.txt";
        arguments[2] = "computer";
        arguments[3] = "3";

        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArguments(arguments);
        PlayerIdentifier player = argumentParser.getNextPlayer();

        assertEquals(player, PlayerIdentifier.COMPUTER);
    }

    @Test
    public void shouldParseTheNextPlayerAsComputerFromTheThirdArgumentStartingWithACapitalLetterWhenInInteractiveMode() {
        String[] arguments = new String[4];
        arguments[0] = "interactive";
        arguments[1] = "someFile.txt";
        arguments[2] = "Computer";
        arguments[3] = "3";

        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArguments(arguments);
        PlayerIdentifier player = argumentParser.getNextPlayer();

        assertEquals(player, PlayerIdentifier.COMPUTER);
    }

    @Test
    public void shouldParseTheOutputFilenameAndPathForTheGameBoardWhenInOneMoveMode() {
        String[] arguments = new String[4];
        arguments[0] = "one-move";
        arguments[1] = "someFile.txt";
        arguments[2] = "some/output/file.txt";
        arguments[3] = "3";
        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArguments(arguments);

        String outputFile = argumentParser.getOutputGameFile();
        String actualOutputFile = "some/output/file.txt";

        assertEquals(outputFile, actualOutputFile);

    }

    @Test
    public void shouldParseTheSearchDepthLevelFromTheFourthArgument() {
        String[] arguments = new String[4];
        arguments[0] = "one-move";
        arguments[1] = "someFile.txt";
        arguments[2] = "computer";
        arguments[3] = "3";

        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArguments(arguments);

        int actualSearchDepthLevel = argumentParser.getSearchDepth();
        int expectedSearchDepthLevel = 3;

        assertEquals(actualSearchDepthLevel, expectedSearchDepthLevel);
    }
}