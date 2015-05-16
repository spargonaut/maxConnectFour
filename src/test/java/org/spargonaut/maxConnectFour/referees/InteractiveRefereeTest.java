package org.spargonaut.maxConnectFour.referees;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.players.AiPlayer;
import org.spargonaut.maxConnectFour.players.HumanPlayer;
import org.spargonaut.maxConnectFour.players.PlayerIdentifier;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.*;
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
        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.hasPossiblePlays()).thenReturn(true).thenReturn(false);
        when(gameBoard.getGameBoardAsList()).thenReturn(createEmptyGameBoard().getGameBoardAsList());
        when(gameBoard.getCurrentTurnBasedOnNumberOfPlays()).thenReturn(1);

        int columnToPlay = 5;
        int searchDepth = 0;
        HumanPlayer humanPlayer = createMockedHumanPlayerThatProducesAColumnToPlay(gameBoard, columnToPlay, searchDepth);

        InteractiveReferee interactiveReferee = new InteractiveReferee(gameBoard, humanPlayer);

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
        InteractiveReferee interactiveReferee = new InteractiveReferee(gameBoard, new HumanPlayer());

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
        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.hasPossiblePlays()).thenReturn(true).thenReturn(false);
        when(gameBoard.getGameBoardAsList()).thenReturn(createEmptyGameBoard().getGameBoardAsList());

        int columnToPlay = 5;
        int searchDepth = 0;

        HumanPlayer humanPlayer = createMockedHumanPlayerThatProducesAColumnToPlay(gameBoard, columnToPlay, searchDepth);

        InteractiveReferee interactiveReferee = new InteractiveReferee(gameBoard, humanPlayer);
        interactiveReferee.play();

        verify(humanPlayer).getBestPlay(gameBoard, searchDepth);
    }

    @Test
    public void shouldApplyTheMoveRetrievedFromTheHumanPlayerWhenThereArePossiblePlaysToMake() {
        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.hasPossiblePlays()).thenReturn(true).thenReturn(false);
        when(gameBoard.getGameBoardAsList()).thenReturn(createEmptyGameBoard().getGameBoardAsList());

        int columnToPlay = 5;
        int searchDepth = 0;
        HumanPlayer humanPlayer = createMockedHumanPlayerThatProducesAColumnToPlay(gameBoard, columnToPlay, searchDepth);

        PlayerIdentifier nextPlayer = PlayerIdentifier.HUMAN;
        InteractiveReferee interactiveReferee = new InteractiveReferee(gameBoard, humanPlayer, new AiPlayer(), nextPlayer);
        interactiveReferee.play();

        verify(gameBoard).playPieceInColumn(columnToPlay);
    }

    @Test
    public void shouldSetTheNextTurnToAiPlayerAfterTheHumanPlays() {
        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.hasPossiblePlays()).thenReturn(true).thenReturn(false);
        when(gameBoard.getGameBoardAsList()).thenReturn(createEmptyGameBoard().getGameBoardAsList());

        int columnToPlay = 5;
        int searchDepth = 0;
        HumanPlayer humanPlayer = createMockedHumanPlayerThatProducesAColumnToPlay(gameBoard, columnToPlay, searchDepth);

        InteractiveReferee interactiveReferee = new InteractiveReferee(gameBoard, humanPlayer);

        PlayerIdentifier expectedNextPlayer = PlayerIdentifier.COMPUTER;

        interactiveReferee.play();
        PlayerIdentifier actualPlayerIdentifier = interactiveReferee.getNextPlayer();

        assertThat(actualPlayerIdentifier, is(expectedNextPlayer));
    }

    @Test
    public void shouldGetTheAiPlayerToPlayWhenItIsTheAiPlayersTurn() {
        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.hasPossiblePlays()).thenReturn(true).thenReturn(false);
        when(gameBoard.getGameBoardAsList()).thenReturn(createEmptyGameBoard().getGameBoardAsList());

        int columnToPlay = 5;
        int searchDepth = 0;
        HumanPlayer humanPlayer = createMockedHumanPlayerThatProducesAColumnToPlay(gameBoard, columnToPlay, searchDepth);

        AiPlayer aiPlayer = mock(AiPlayer.class);
        when(aiPlayer.getBestPlay(gameBoard, searchDepth)).thenReturn(columnToPlay);

        PlayerIdentifier nextPlayer = PlayerIdentifier.COMPUTER;

        InteractiveReferee interactiveReferee = new InteractiveReferee(gameBoard, humanPlayer, aiPlayer, nextPlayer);
        interactiveReferee.play();

        verify(aiPlayer).getBestPlay(gameBoard, searchDepth);
    }

    @Test
    public void shouldApplyTheMoveRetrievedFromTheAiPlayerWhenThereArePossiblePlaysToMake() {
        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.hasPossiblePlays()).thenReturn(true).thenReturn(false);
        when(gameBoard.getGameBoardAsList()).thenReturn(createEmptyGameBoard().getGameBoardAsList());

        int columnToPlay = 5;
        int searchDepth = 0;
        AiPlayer aiPlayer = mock(AiPlayer.class);
        when(aiPlayer.getBestPlay(gameBoard, searchDepth)).thenReturn(columnToPlay);

        PlayerIdentifier nextPlayer = PlayerIdentifier.COMPUTER;
        InteractiveReferee interactiveReferee = new InteractiveReferee(gameBoard, new HumanPlayer(), aiPlayer, nextPlayer);
        interactiveReferee.play();

        verify(gameBoard).playPieceInColumn(columnToPlay);
    }

    @Test
    public void shouldSetTheNextTurnToHumanPlayerAfterTheAiPlayerPlays() {
        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.hasPossiblePlays()).thenReturn(true).thenReturn(false);
        when(gameBoard.getGameBoardAsList()).thenReturn(createEmptyGameBoard().getGameBoardAsList());

        int columnToPlay = 5;
        int searchDepth = 0;
        AiPlayer aiPlayer = mock(AiPlayer.class);
        when(aiPlayer.getBestPlay(gameBoard, searchDepth)).thenReturn(columnToPlay);

        PlayerIdentifier nextPlayer = PlayerIdentifier.COMPUTER;
        InteractiveReferee interactiveReferee = new InteractiveReferee(gameBoard, new HumanPlayer(), aiPlayer, nextPlayer);

        PlayerIdentifier expectedNextPlayer = PlayerIdentifier.HUMAN;

        interactiveReferee.play();
        PlayerIdentifier actualPlayerIdentifier = interactiveReferee.getNextPlayer();

        assertThat(actualPlayerIdentifier, is(expectedNextPlayer));
    }

    @Test
    public void shouldContinueToAskForPlaysUntilTheGameBoardIsFull() {
        int searchDepth = 0;
        int columnToPlay = 5;
        PlayerIdentifier nextPlayer = PlayerIdentifier.HUMAN;

        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.getGameBoardAsList()).thenReturn(createEmptyGameBoard().getGameBoardAsList());

        HumanPlayer humanPlayer = mock(HumanPlayer.class);
        when(humanPlayer.getBestPlay(gameBoard, searchDepth)).thenReturn(columnToPlay);

        AiPlayer aiPlayer = mock(AiPlayer.class);
        when(aiPlayer.getBestPlay(gameBoard, searchDepth)).thenReturn(columnToPlay);

        when(gameBoard.hasPossiblePlays()).thenReturn(true).thenReturn(false);

        InteractiveReferee interactiveReferee = new InteractiveReferee(gameBoard, humanPlayer, aiPlayer, nextPlayer);
        interactiveReferee.play();

        verify(gameBoard, times(2)).hasPossiblePlays();
    }

    private HumanPlayer createMockedHumanPlayerThatProducesAColumnToPlay(GameBoard gameBoard, int columnToPlay, int searchDepth) {
        HumanPlayer humanPlayer = mock(HumanPlayer.class);
        when(humanPlayer.getBestPlay(gameBoard, searchDepth)).thenReturn(columnToPlay);
        return  humanPlayer;
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