package org.spargonaut.maxConnectFour.players;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;

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
        InputParser inputParser = mock(InputParser.class);
        when(inputParser.getNextPlayFromHuman()).thenReturn(1);

        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.isValidPlay(0)).thenReturn(true);

        HumanPlayer humanPlayer = new HumanPlayer(inputParser);
        Integer actualValidPlay = humanPlayer.getBestPlay(gameBoard, 2);

        int expectedValidPlay = 0;
        assertThat(actualValidPlay.intValue(), equalTo(expectedValidPlay));
    }

    @Test
    public void shouldTellUserThatAPlayIsInvalid() throws IOException {
        InputParser inputParser = mock(InputParser.class);
        when(inputParser.getNextPlayFromHuman()).thenReturn(0);

        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.isValidPlay(-1)).thenReturn(false).thenReturn(true);

        HumanPlayer humanPlayer = new HumanPlayer(inputParser);
        humanPlayer.getBestPlay(gameBoard, 0);

        assertEquals(outContent.toString(), "That was an invalid play\n--Try again!\n");
    }
}