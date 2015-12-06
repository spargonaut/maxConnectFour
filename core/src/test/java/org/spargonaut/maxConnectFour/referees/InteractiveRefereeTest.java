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

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class InteractiveRefereeTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private GameBoard baseMockGameBoard;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        baseMockGameBoard = createMockGameBoard();
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

    @Test
    public void shouldGetTheNextMoveFromTheHumanPlayerWhenThereArePossiblePlaysToMake() {
        int columnToPlay = 5;
        int searchDepth = 0;

        HumanPlayer humanPlayer = createMockedHumanPlayerThatProducesAColumnToPlay(baseMockGameBoard, columnToPlay, searchDepth);

        InteractiveReferee interactiveReferee = new InteractiveReferee(baseMockGameBoard, humanPlayer);
        interactiveReferee.play();

        verify(humanPlayer).getBestPlay(baseMockGameBoard);
    }

    @Test
    public void shouldApplyTheMoveRetrievedFromTheHumanPlayerWhenThereArePossiblePlaysToMake() {
        int columnToPlay = 5;
        int searchDepth = 0;
        HumanPlayer humanPlayer = createMockedHumanPlayerThatProducesAColumnToPlay(baseMockGameBoard, columnToPlay, searchDepth);

        PlayerIdentifier nextPlayer = PlayerIdentifier.HUMAN;

        InteractiveReferee interactiveReferee = new InteractiveReferee(baseMockGameBoard, humanPlayer, new AiPlayer(), nextPlayer);
        interactiveReferee.play();

        verify(baseMockGameBoard).playPieceInColumn(columnToPlay);
    }

    @Test
    public void shouldSetTheNextTurnToAiPlayerAfterTheHumanPlays() {
        int columnToPlay = 5;
        int searchDepth = 0;
        HumanPlayer humanPlayer = createMockedHumanPlayerThatProducesAColumnToPlay(baseMockGameBoard, columnToPlay, searchDepth);

        InteractiveReferee interactiveReferee = new InteractiveReferee(baseMockGameBoard, humanPlayer);
        interactiveReferee.play();

        PlayerIdentifier expectedNextPlayer = PlayerIdentifier.COMPUTER;

        assertThat(interactiveReferee.getNextPlayer(), is(expectedNextPlayer));
    }

    @Test
    public void shouldGetTheAiPlayerToPlayWhenItIsTheAiPlayersTurn() {
        int columnToPlay = 5;
        int searchDepth = 0;
        HumanPlayer humanPlayer = createMockedHumanPlayerThatProducesAColumnToPlay(baseMockGameBoard, columnToPlay, searchDepth);

        AiPlayer aiPlayer = mock(AiPlayer.class);
        when(aiPlayer.getBestPlay(baseMockGameBoard)).thenReturn(columnToPlay);

        PlayerIdentifier nextPlayer = PlayerIdentifier.COMPUTER;

        InteractiveReferee interactiveReferee = new InteractiveReferee(baseMockGameBoard, humanPlayer, aiPlayer, nextPlayer);
        interactiveReferee.play();

        verify(aiPlayer).getBestPlay(baseMockGameBoard);
    }

    @Test
    public void shouldApplyTheMoveRetrievedFromTheAiPlayerWhenThereArePossiblePlaysToMake() {
        int columnToPlay = 5;

        AiPlayer aiPlayer = mock(AiPlayer.class);
        when(aiPlayer.getBestPlay(baseMockGameBoard)).thenReturn(columnToPlay);

        PlayerIdentifier nextPlayer = PlayerIdentifier.COMPUTER;

        InteractiveReferee interactiveReferee = new InteractiveReferee(baseMockGameBoard, new HumanPlayer(), aiPlayer, nextPlayer);
        interactiveReferee.play();

        verify(baseMockGameBoard).playPieceInColumn(columnToPlay);
    }

    @Test
    public void shouldSetTheNextTurnToHumanPlayerAfterTheAiPlayerPlays() {
        int columnToPlay = 5;

        AiPlayer aiPlayer = mock(AiPlayer.class);
        when(aiPlayer.getBestPlay(baseMockGameBoard)).thenReturn(columnToPlay);

        PlayerIdentifier nextPlayer = PlayerIdentifier.COMPUTER;

        InteractiveReferee interactiveReferee = new InteractiveReferee(baseMockGameBoard, new HumanPlayer(), aiPlayer, nextPlayer);
        interactiveReferee.play();

        PlayerIdentifier expectedNextPlayer = PlayerIdentifier.HUMAN;

        assertThat(interactiveReferee.getNextPlayer(), is(expectedNextPlayer));
    }

    @Test
    public void shouldContinueToAskForPlaysUntilTheGameBoardIsFull() {
        when(baseMockGameBoard.hasPossiblePlays()).thenReturn(true).thenReturn(false);

        int columnToPlay = 5;

        HumanPlayer humanPlayer = mock(HumanPlayer.class);
        when(humanPlayer.getBestPlay(baseMockGameBoard)).thenReturn(columnToPlay);

        AiPlayer aiPlayer = mock(AiPlayer.class);
        when(aiPlayer.getBestPlay(baseMockGameBoard)).thenReturn(columnToPlay);

        PlayerIdentifier nextPlayer = PlayerIdentifier.HUMAN;

        InteractiveReferee interactiveReferee = new InteractiveReferee(baseMockGameBoard, humanPlayer, aiPlayer, nextPlayer);
        interactiveReferee.play();

        verify(baseMockGameBoard, times(2)).hasPossiblePlays();
    }

    @Test
    public void shouldPrintTheCurrentGameStateBetweenPlays() {
        int columnToPlay = 5;
        int searchDepth = 0;
        HumanPlayer humanPlayer = createMockedHumanPlayerThatProducesAColumnToPlay(baseMockGameBoard, columnToPlay, searchDepth);

        InteractiveReferee interactiveReferee = new InteractiveReferee(baseMockGameBoard, humanPlayer);
        interactiveReferee.play();

        String expectedOutput = "...and now the board looks like this:";

        assertThat(outContent.toString(), containsString(expectedOutput));
    }

    private HumanPlayer createMockedHumanPlayerThatProducesAColumnToPlay(GameBoard gameBoard, int columnToPlay, int searchDepth) {
        HumanPlayer humanPlayer = mock(HumanPlayer.class);
        when(humanPlayer.getBestPlay(gameBoard)).thenReturn(columnToPlay);
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

    private GameBoard createMockGameBoard() {
        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.hasPossiblePlays()).thenReturn(true).thenReturn(false);
        when(gameBoard.getGameBoardAsList()).thenReturn(createEmptyGameBoard().getGameBoardAsList());
        when(gameBoard.getCurrentTurnBasedOnNumberOfPlays()).thenReturn(1);
        return gameBoard;
    }
}