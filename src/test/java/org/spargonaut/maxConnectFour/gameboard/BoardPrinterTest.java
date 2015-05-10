package org.spargonaut.maxConnectFour.gameboard;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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

    @Test
    public void shouldPrintTheGameBoardToAFile() throws IOException {
        List<List<Integer>> setUpGameBoard = new ArrayList<List<Integer>>();
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1));

        GameBoard gameBoard = new GameBoard(setUpGameBoard);

        BufferedWriter bufferedWriter = mock(BufferedWriter.class);
        BoardPrinter boardPrinter = new BoardPrinter(bufferedWriter);

        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);

        boardPrinter.printGameBoardToFile("someFileName", gameBoard);

        verify(bufferedWriter, times(6)).write("\n");
        verify(bufferedWriter, times(41)).write(48);
        verify(bufferedWriter, times(1)).write(49);
    }

    private List<Integer> createBlankRow() {
        return Arrays.asList(0, 0, 0, 0, 0, 0, 0);
    }
}