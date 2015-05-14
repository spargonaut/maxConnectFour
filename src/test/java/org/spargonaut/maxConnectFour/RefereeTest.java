package org.spargonaut.maxConnectFour;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.spargonaut.maxConnectFour.gameboard.BoardWriter;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RefereeTest {

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
    public void shouldPrintTheInitialGameStateToTheScreen() {
        GameBoard gameboard = createGameBoard();
        PlayMode playmode = PlayMode.ONE_MOVE;
        Referee referee = new Referee(gameboard, playmode);

        String expectedInitialGameBoardMessage = "--------------------------------------------------------------------------------" +
                "\n\n" +
                "Max Connect Four Client - " + playmode + " Mode" +
                "\n\n" + createBoardAndScoresString();
        referee.printInitialGameState();

        assertThat(outContent.toString(), is(expectedInitialGameBoardMessage));
    }

    @Test
    public void shouldPrintTheGameBoardStateToTheScreen() {
        GameBoard gameboard = createGameBoard();
        PlayMode playmode = PlayMode.ONE_MOVE;
        Referee referee = new Referee(gameboard, playmode);

        referee.printGameBoardAndScores();

        String expectedMessage = createBoardAndScoresString();

        assertThat(outContent.toString(), is(expectedMessage));
    }

    @Test
    public void shouldSaveTheGameBoardToAFile() throws IOException {
        List<List<Integer>> setUpGameBoard = new ArrayList<List<Integer>>();
        setUpGameBoard.add(Arrays.asList(1, 1, 1, 1, 0, 0, 0));
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());

        GameBoard gameBoard = new GameBoard(setUpGameBoard);

        BoardWriter boardWriter = mock(BoardWriter.class);

        Referee referee = new Referee(gameBoard, PlayMode.ONE_MOVE, boardWriter);
        String setupFileName = "some/file/name.txt";
        referee.saveGameState(setupFileName);

        verify(boardWriter).printGameBoardToFile(setupFileName, gameBoard);
    }

    private GameBoard createGameBoard() {
        List<List<Integer>> playboard = new ArrayList<List<Integer>>();
        playboard.add(Arrays.asList(1, 1, 1, 1, 0, 0, 0));
        playboard.add(createBlankRow());
        playboard.add(createBlankRow());
        playboard.add(createBlankRow());
        playboard.add(createBlankRow());
        playboard.add(createBlankRow());
        return new GameBoard(playboard);
    }

    private String createBoardAndScoresString() {
        return "   1 2 3 4 5 6 7   <---  Column numbers\n" +
                " -----------------\n" +
                "_| 1 1 1 1       |_\n" +
                "_|               |_\n" +
                "_|               |_\n" +
                "_|               |_\n" +
                "_|               |_\n" +
                "_|               |_\n" +
                " -----------------\n" +
                "   1 2 3 4 5 6 7   <---Column numbers\n" +
                "Scores:\n" +
                " Player1: 1\n" +
                " Player2: 0\n\n";
    }

    private List<Integer> createBlankRow() {
        return Arrays.asList(0, 0, 0, 0, 0, 0, 0);
    }
}