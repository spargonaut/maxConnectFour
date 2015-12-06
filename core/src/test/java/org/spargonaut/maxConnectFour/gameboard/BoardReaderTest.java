package org.spargonaut.maxConnectFour.gameboard;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BoardReaderTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

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

        List<List<Integer>> setUpPlayboard = new ArrayList<List<Integer>>();
        List<Integer> rowList = new ArrayList<Integer>();
        rowList.add(valueAtPosition);
        setUpPlayboard.add(rowList);

        BoardReader boardReader = new BoardReader(setUpPlayboard);
        boolean actualMarkIsNotValid = boardReader.markIsNotValid(row, column);

        assertEquals(true, actualMarkIsNotValid);
    }

    @Test
    public void shouldIndicateAValidMarkWhenPlayIsZero() {
        int row = 0;
        int column = 0;
        int valueAtPosition = 0;

        List<List<Integer>> setUpPlayboard = new ArrayList<List<Integer>>();
        List<Integer> rowList = new ArrayList<Integer>();
        rowList.add(valueAtPosition);
        setUpPlayboard.add(rowList);

        BoardReader boardReader = new BoardReader(setUpPlayboard);
        boolean actualMarkIsNotValid = boardReader.markIsNotValid(row, column);

        assertEquals(false, actualMarkIsNotValid);
    }

    @Test
    public void shouldIndicateAValidMarkWhenPlayIsOne() {
        int row = 0;
        int column = 0;
        int valueAtPosition = 1;

        List<List<Integer>> setUpPlayboard = new ArrayList<List<Integer>>();
        List<Integer> rowList = new ArrayList<Integer>();
        rowList.add(valueAtPosition);
        setUpPlayboard.add(rowList);

        BoardReader boardReader = new BoardReader(setUpPlayboard);
        boolean actualMarkIsNotValid = boardReader.markIsNotValid(row, column);

        assertEquals(false, actualMarkIsNotValid);
    }

    @Test
    public void shouldIndicateAValidMarkWhenPlayIsTwo() {
        int row = 0;
        int column = 0;
        int valueAtPosition = 2;

        List<List<Integer>> setUpPlayboard = new ArrayList<List<Integer>>();
        List<Integer> rowList = new ArrayList<Integer>();
        rowList.add(valueAtPosition);
        setUpPlayboard.add(rowList);

        BoardReader boardReader = new BoardReader(setUpPlayboard);
        boolean actualMarkIsNotValid = boardReader.markIsNotValid(row, column);

        assertEquals(false, actualMarkIsNotValid);
    }

    @Test
    public void shouldThrowAnExceptionWhenReadingInAnInvalidMark() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Problems!\n--The piece read from the input file was not a 1, a 2 or a 0");

        int row = 0;
        int column = 0;
        int valueAtPosition = 4;

        List<List<Integer>> setUpPlayboard = new ArrayList<List<Integer>>();
        List<Integer> rowList = new ArrayList<Integer>();
        rowList.add(valueAtPosition);
        setUpPlayboard.add(rowList);

        BoardReader boardReader = new BoardReader(setUpPlayboard);
        boardReader.checkIfMarkIsValid(row, column);
    }

    @Test
    public void shouldMarkThePlayAtTheGivenPosition() {
        String gameData = "1";
        int row = 0;
        int column = 0;

        List<List<Integer>> playboard = new ArrayList<List<Integer>>();
        List<Integer> rowList = new ArrayList<Integer>();
        playboard.add(rowList);

        BoardReader boardReader = new BoardReader(playboard);

        boardReader.markPlayAtPosition(gameData, row, column);

        List<List<Integer>> expectedPlayBoard = new ArrayList<List<Integer>>();
        expectedPlayBoard.add(Arrays.asList(1));

        List<List<Integer>> actualPlayBoard = boardReader.getPlayBoardAsList();

        assertEquals(expectedPlayBoard, actualPlayBoard);
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