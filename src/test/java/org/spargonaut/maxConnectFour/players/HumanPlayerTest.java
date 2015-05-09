package org.spargonaut.maxConnectFour.players;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HumanPlayerTest {

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
    public void shouldReturnAValidColumnToPlayIn() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("1");

        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.isValidPlay(0)).thenReturn(true);

        HumanPlayer humanPlayer = new HumanPlayer(bufferedReader);
        Integer actualValidPlay = humanPlayer.getBestPlay(gameBoard, 2);

        Integer expectedValidPlay = new Integer(0);
        assertThat(actualValidPlay.intValue(), equalTo(expectedValidPlay.intValue()));
    }

    @Test
    public void shouldTellUserThatAPlayIsInvalid() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("0");

        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.isValidPlay(-1)).thenReturn(false).thenReturn(true);

        HumanPlayer humanPlayer = new HumanPlayer(bufferedReader);
        humanPlayer.getBestPlay(gameBoard, 0);

        assertEquals(outContent.toString(), "\nChoose a Column ( 1 - 7 ) -->:That was an invalid play\n--Try again!\n\nChoose a Column ( 1 - 7 ) -->:");
    }

    @Test
    public void shouldTellUserThatAPlayIsNotValidWhenTheUserDoesNotPlayADigit() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("C").thenReturn("1");

        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.isValidPlay(0)).thenReturn(true);

        HumanPlayer humanPlayer = new HumanPlayer(bufferedReader);
        humanPlayer.getBestPlay(gameBoard, 0);

        assertEquals(outContent.toString(), "\nChoose a Column ( 1 - 7 ) -->:\nThat was not a valid digit.\n--Try again\n\nChoose a Column ( 1 - 7 ) -->:");
    }

    @Test
    public void shouldAskUserToInputAPlayWhenTheInputIsBlank() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("").thenReturn("1");

        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.isValidPlay(0)).thenReturn(true);

        HumanPlayer humanPlayer = new HumanPlayer(bufferedReader);
        humanPlayer.getBestPlay(gameBoard, 0);

        assertEquals(outContent.toString(), "\nChoose a Column ( 1 - 7 ) -->:\nI didn't understand that input\n--Try again\n\nChoose a Column ( 1 - 7 ) -->:");
    }
}