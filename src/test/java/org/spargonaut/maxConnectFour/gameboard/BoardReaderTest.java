package org.spargonaut.maxConnectFour.gameboard;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BoardReaderTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

    @Test
    public void shouldIndicateAnInvalidMarkWhenNotAZeroOneOrTwo() {
        int row = 0;
        int column = 0;
        int valueAtPosition = 4;

        int[][] playboard = {{valueAtPosition}, {0}};

        BoardReader boardReader = new BoardReader(playboard);
        boolean actualMarkIsNotValid = boardReader.markIsNotValid(row, column);

        boolean expectedMarkIsNotValid = true;
        assertEquals(expectedMarkIsNotValid, actualMarkIsNotValid);
    }

    @Test
    public void shouldIndicateAValidMarkWhenPlayIsZero() {
        int row = 0;
        int column = 0;
        int valueAtPosition = 0;

        int[][] playboard = {{valueAtPosition}, {0}};

        BoardReader boardReader = new BoardReader(playboard);
        boolean actualMarkIsNotValid = boardReader.markIsNotValid(row, column);

        boolean expectedMarkIsNotValid = false;
        assertEquals(expectedMarkIsNotValid, actualMarkIsNotValid);
    }

    @Test
    public void shouldIndicateAValidMarkWhenPlayIsOne() {
        int row = 0;
        int column = 0;
        int valueAtPosition = 1;

        int[][] playboard = {{valueAtPosition}, {0}};

        BoardReader boardReader = new BoardReader(playboard);
        boolean actualMarkIsNotValid = boardReader.markIsNotValid(row, column);

        boolean expectedMarkIsNotValid = false;
        assertEquals(expectedMarkIsNotValid, actualMarkIsNotValid);
    }

    @Test
    public void shouldIndicateAValidMarkWhenPlayIsTwo() {
        int row = 0;
        int column = 0;
        int valueAtPosition = 2;

        int[][] playboard = {{valueAtPosition}, {0}};

        BoardReader boardReader = new BoardReader(playboard);
        boolean actualMarkIsNotValid = boardReader.markIsNotValid(row, column);

        boolean expectedMarkIsNotValid = false;
        assertEquals(expectedMarkIsNotValid, actualMarkIsNotValid);
    }

    @Test
    public void shouldExitWithMessageWhenMarkIsInvalid() {
        int row = 0;
        int column = 0;
        int valueAtPosition = 4;

        int[][] playboard = {{valueAtPosition}, {0}};

        BoardReader boardReader = new BoardReader(playboard);

        exit.expectSystemExitWithStatus(0);

        exit.checkAssertionAfterwards(new Assertion() {
            @Override
            public void checkAssertion() throws Exception {
                assertEquals(outContent.toString(), "\nProblems!\n--The piece read from the input file was not a 1, a 2 or a 0\n");
            }
        });

        boardReader.checkIfMarkIsValidOrExit(row, column);
    }

    @Test
    public void shouldMarkThePlayAtTheGivenPosition() {
        String gameData = "1";
        int row = 0;
        int column = 0;

        int[][] playboard = {{0}, {0}};

        BoardReader boardReader = new BoardReader(playboard);

        boardReader.markPlayAtPosition(gameData, row, column);
        int[][] actualPlayBoard = boardReader.getPlayBoard();
        int[][] expectedPlayBoard = {{1}, {0}};

        assertArrayEquals(expectedPlayBoard, actualPlayBoard);
    }

    @Test
    public void shouldReadTheGameInFromAFile() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("0000000")
                .thenReturn("0000000")
                .thenReturn("0000000")
                .thenReturn("0000000")
                .thenReturn("0000000")
                .thenReturn("0000001");

        BoardReader boardReader = new BoardReader(bufferedReader);
        GameBoard gameBoard = boardReader.readGame("foo");

        List<List<Integer>> expectedGameboard = new ArrayList<List<Integer>>();
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1));

        List<List<Integer>> actualPlayBoard = gameBoard.getGameBoardAsList();

        assertEquals(expectedGameboard, actualPlayBoard);
    }

    private List<Integer> createBlankRow() {
        return Arrays.asList(0, 0, 0, 0, 0, 0, 0);
    }
}