package org.spargonaut.maxConnectFour.players;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.spargonaut.maxConnectFour.parser.InputParser;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InputParserTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

    @Test
    public void shouldTellUserThatAPlayIsNotValidWhenTheUserDoesNotPlayADigit() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("C").thenReturn("1");

        InputParser inputParser = new InputParser(bufferedReader);
        inputParser.getNextPlayFromHuman();

        assertEquals(outContent.toString(), "\nChoose a Column ( 1 - 7 ) -->:\nThat was not a valid digit.\n--Try again\n\nChoose a Column ( 1 - 7 ) -->:");
    }

    @Test
    public void shouldAskUserToInputAPlayWhenTheInputIsBlank() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("").thenReturn("1");

        InputParser inputParser = new InputParser(bufferedReader);
        inputParser.getNextPlayFromHuman();

        assertEquals(outContent.toString(), "\nChoose a Column ( 1 - 7 ) -->:\nI didn't understand that input\n--Try again\n\nChoose a Column ( 1 - 7 ) -->:");
    }

    @Test
    public void shouldParseHumanInputToAnInteger() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("1");

        InputParser inputParser = new InputParser(bufferedReader);
        int columnToPlayIn = inputParser.getNextPlayFromHuman();

        assertThat(columnToPlayIn, is(1));
    }
}