package org.spargonaut.maxConnectFour.referees;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.players.HumanPlayer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class InteractiveRefereeTest {

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
    public void shouldPrintOutTheIntialGameStateAtTheBeginningOfTheGame() {

        GameBoard gameBoard = createEmptyGameBoard();
        InteractiveReferee interactiveReferee = new InteractiveReferee(gameBoard);

        String expectedOutput = "--------------------------------------------------------------------------------\n" +
                "\n" +
                "Max Connect Four Client - INTERACTIVE Mode\n" +
                "\n" +
                "   1 2 3 4 5 6 7   <---  Column numbers\n" +
                " -----------------\n" +
                "_|               |_\n" +
                "_|               |_\n" +
                "_|               |_\n" +
                "_|               |_\n" +
                "_|               |_\n" +
                "_|               |_\n" +
                " -----------------\n" +
                "   1 2 3 4 5 6 7   <---Column numbers\n" +
                "Scores:\n" +
                " Player1: 0\n" +
                " Player2: 0" +
                "\n\n\n" +
                "It is now Player 1's Turn";

        interactiveReferee.play();

        assertThat(outContent.toString(), startsWith(expectedOutput));
    }

    @Test
    public void shouldPrintTheFinalGameStateWhenTheGameBoardIsFull() {
        List<List<Integer>> playboard = new ArrayList<>();
        playboard.add(Arrays.asList(2, 2, 1, 2, 2, 2, 2));
        playboard.add(Arrays.asList(1, 1, 2, 1, 1, 1, 1));
        playboard.add(Arrays.asList(2, 2, 1, 2, 2, 2, 2));
        playboard.add(Arrays.asList(1, 1, 2, 1, 1, 1, 1));
        playboard.add(Arrays.asList(2, 2, 1, 2, 2, 2, 2));
        playboard.add(Arrays.asList(1, 1, 1, 2, 1, 1, 1));
        GameBoard gameBoard = new GameBoard(playboard);
        InteractiveReferee interactiveReferee = new InteractiveReferee(gameBoard);

        String expectedOutput = "Here is the final game state" +
                "\n\n" +
                "   1 2 3 4 5 6 7   <---  Column numbers\n" +
                " -----------------\n" +
                "_| 2 2 1 2 2 2 2 |_\n" +
                "_| 1 1 2 1 1 1 1 |_\n" +
                "_| 2 2 1 2 2 2 2 |_\n" +
                "_| 1 1 2 1 1 1 1 |_\n" +
                "_| 2 2 1 2 2 2 2 |_\n" +
                "_| 1 1 1 2 1 1 1 |_\n" +
                " -----------------\n" +
                "   1 2 3 4 5 6 7   <---Column numbers\n" +
                "Scores:\n" +
                " Player1: 2\n" +
                " Player2: 3\n\n";
        interactiveReferee.play();
        assertThat(outContent.toString(), endsWith(expectedOutput));
    }

    @Test
    public void shouldGetTheNextMoveFromTheHumanPlayerWhenThereArePossiblePlaysToMake() {
        int searchDepth = 0;
        GameBoard gameBoard = createEmptyGameBoard();

        HumanPlayer humanPlayer = mock(HumanPlayer.class);
        when(humanPlayer.getBestPlay(gameBoard, searchDepth)).thenReturn(5);


        InteractiveReferee interactiveReferee = new InteractiveReferee(gameBoard, humanPlayer);
        interactiveReferee.play();

        verify(humanPlayer).getBestPlay(gameBoard, searchDepth);
    }



    private GameBoard createEmptyGameBoard() {
        List<List<Integer>> playboard = new ArrayList<>();
        playboard.add(createBlankRow());
        playboard.add(createBlankRow());
        playboard.add(createBlankRow());
        playboard.add(createBlankRow());
        playboard.add(createBlankRow());
        playboard.add(createBlankRow());
        return new GameBoard(playboard);
    }

    private List<Integer> createBlankRow() {
        return Arrays.asList(0, 0, 0, 0, 0, 0, 0);
    }
}