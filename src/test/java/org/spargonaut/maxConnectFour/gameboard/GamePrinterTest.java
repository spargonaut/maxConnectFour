package org.spargonaut.maxConnectFour.gameboard;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.spargonaut.maxConnectFour.PlayMode;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GamePrinterTest {

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

        List<List<Integer>> setUpGameBoard = new ArrayList<>();
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1));

        GameBoard gameBoard = new GameBoard(setUpGameBoard);

        GamePrinter gamePrinter = new GamePrinter(gameBoard);
        gamePrinter.printGameBoard();

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
    public void shouldPrintTheInitialGameStateToSystemOut() {
        List<List<Integer>> setUpGameBoard = new ArrayList<>();
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1));

        GameBoard gameBoard = new GameBoard(setUpGameBoard);

        PlayMode playMode = PlayMode.ONE_MOVE;

        GamePrinter gamePrinter = new GamePrinter(gameBoard);
        gamePrinter.printInitialGameState(playMode);

        String expectedOutContent = "--------------------------------------------------------------------------------\n" +
                "\n" +
                "Max Connect Four Client - One-move Mode\n" +
                "\n" +
                "   1 2 3 4 5 6 7   <---  Column numbers\n" +
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
    public void shouldPrintTheCurrentPlayersTurn() {
        List<List<Integer>> setUpGameBoard = new ArrayList<>();
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1));

        GameBoard gameBoard = new GameBoard(setUpGameBoard);

        GamePrinter gamePrinter = new GamePrinter(gameBoard);
        gamePrinter.printCurrentPlayersTurn();

        String expectedOutContent = "\nIt is now Player 2's Turn\n";

        assertEquals(expectedOutContent, outContent.toString());
    }

    @Test
    public void shouldPrintTheCurrentGameState() {
        List<List<Integer>> setUpGameBoard = new ArrayList<>();
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1));

        GameBoard gameBoard = new GameBoard(setUpGameBoard);

        GamePrinter gamePrinter = new GamePrinter(gameBoard);
        gamePrinter.printCurrentGameState();

        String expectedOutContent = "\n...and now the board looks like this: \n" +
                "\n" +
                "   1 2 3 4 5 6 7   <---  Column numbers\n" +
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
    public void shouldPrintTheFinalGameState() {
        List<List<Integer>> setUpGameBoard = new ArrayList<>();
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1));

        GameBoard gameBoard = new GameBoard(setUpGameBoard);

        GamePrinter gamePrinter = new GamePrinter(gameBoard);
        gamePrinter.printFinalGameState();

        String expectedOutContent = "Here is the final game state\n" +
                "\n" +
                "   1 2 3 4 5 6 7   <---  Column numbers\n" +
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