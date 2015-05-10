package org.spargonaut.maxConnectFour.gameboard;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

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
}