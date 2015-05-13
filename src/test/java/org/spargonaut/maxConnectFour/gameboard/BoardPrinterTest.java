package org.spargonaut.maxConnectFour.gameboard;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoardPrinterTest {

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
    public void shouldPrintTheGameBoardToSystemOut() {

        List<List<Integer>> setUpGameBoard = new ArrayList<List<Integer>>();
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1));

        GameBoard gameBoard = new GameBoard(setUpGameBoard);

        BoardPrinter boardPrinter = new BoardPrinter();
        boardPrinter.printGameBoard(gameBoard);

        String expectedOutContent = "   1 2 3 4 5 6 7   <---  Column numbers\n" +
                " -----------------\n" +
                "_|               |_\n" +
                "_|               |_\n" +
                "_|               |_\n" +
                "_|               |_\n" +
                "_|               |_\n" +
                "_|             1 |_\n" +
                " -----------------\n" +
                "   1 2 3 4 5 6 7   <---Column numbers\n";

        assertEquals(expectedOutContent, outContent.toString());
    }

    private List<Integer> createBlankRow() {
        return Arrays.asList(0, 0, 0, 0, 0, 0, 0);
    }
}