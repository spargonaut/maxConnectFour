package org.spargonaut.maxConnectFour.gameboard;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.*;

public class GameBoardTest {

    @Test
    public void shouldRemoveTheSpecifiedPieceFromTheBoard() throws Exception {
        List<List<Integer>> startingGameboard = new ArrayList<List<Integer>>();
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        GameBoard gameboard = new GameBoard(startingGameboard);

        gameboard.removePiece(0);

        List<List<Integer>> expectedGameboard = new ArrayList<List<Integer>>();
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        List<List<Integer>> actualMasterGame = gameboard.getGameBoardAsList();

        assertEquals(expectedGameboard, actualMasterGame);
    }

    @Test
    public void shouldPlayPieceForPlayerOneInTheColumnIndicated() {
        List<List<Integer>> startingGameboard = new ArrayList<List<Integer>>();
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        GameBoard gameboard = new GameBoard(startingGameboard);

        gameboard.playPieceInColumn(0);

        List<List<Integer>> expectedGameboard = new ArrayList<List<Integer>>();
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        List<List<Integer>> actualMasterGame = gameboard.getGameBoardAsList();

        assertEquals(expectedGameboard, actualMasterGame);
    }

    @Test
    public void shouldPlayPieceForPlayerTwoInTheColumnIndicated() {
        List<List<Integer>> startingGameboard = new ArrayList<List<Integer>>();
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        GameBoard gameboard = new GameBoard(startingGameboard);

        gameboard.playPieceInColumn(1);

        List<List<Integer>> expectedGameboard = new ArrayList<List<Integer>>();
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(Arrays.asList(1, 2, 0, 0, 0, 0, 0));

        List<List<Integer>> actualMasterGame = gameboard.getGameBoardAsList();

        assertEquals(expectedGameboard, actualMasterGame);
    }

    @Test
    public void shouldIndicateTheValidColumnsOfPlay() {
        int[][] masterGame = {
                {1, 0, 1, 0, 0, 2, 0},
                {1, 2, 1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1, 2, 1},
        };
        GameBoard gameboard = new GameBoard(masterGame);

        List<Integer> validPlays = gameboard.getColumnsOfValidPlays();
        assertThat(validPlays, hasItems(1, 3, 4, 6));
    }

    @Test
    public void shouldIndicateAnInvalidPlayWhenPlayColumnIsBelowLowestColumnNumber() {
        int[][] masterGame = {
                {1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
        };
        GameBoard gameBoard = new GameBoard(masterGame);
        boolean isValidPlay = gameBoard.isValidPlay(-1);
        assertThat(isValidPlay, is(false));
    }

    @Test
    public void shouldIndicateAnInvalidPlayWhenPlayColumnIsGreaterThanHighestColumnNumber() {
        int[][] masterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
        };
        GameBoard gameBoard = new GameBoard(masterGame);
        boolean isValidPlay = gameBoard.isValidPlay(8);
        assertThat(isValidPlay, is(false));
    }

    @Test
    public void shouldIndicateAnInvalidPlayWhenThePlayColumnsIsFull() {
        int[][] masterGame = {
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
        };
        GameBoard gameBoard = new GameBoard(masterGame);
        boolean isValidPlay = gameBoard.isValidPlay(0);
        assertThat(isValidPlay, is(false));
    }

    @Test
    public void shouldGetTheCountOfThePiecesAlreadyPlayed() {
        int[][] masterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 2},
        };
        GameBoard gameboard = new GameBoard(masterGame);

        int countOfPiecesPlayed = gameboard.getCountOfPiecesPlayed();
        assertThat(countOfPiecesPlayed, is(2));
    }

    @Test
    public void shouldIndicatePlayerOnesTurnWhenPlayCountIsEven() {
        int[][] masterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 2},
        };
        GameBoard gameboard = new GameBoard(masterGame);

        int currentPlayersTurn = gameboard.getCurrentTurnBasedOnNumberOfPlays();
        assertThat(currentPlayersTurn, is(1));
    }

    @Test
    public void shouldIndicatePlayerTwosTurnWhenPlayCountIsOdd() {
        int[][] masterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0},
        };
        GameBoard gameboard = new GameBoard(masterGame);

        int currentPlayersTurn = gameboard.getCurrentTurnBasedOnNumberOfPlays();
        assertThat(currentPlayersTurn, is(2));
    }

    @Test
    public void shouldGetNumberOfPlaysRemaining() {
        int[][] masterGame = {
                {1, 0, 1, 0, 0, 2, 0},
                {1, 2, 1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1, 2, 1},
        };
        GameBoard gameBoard = new GameBoard(masterGame);

        int actualPlaysRemaining = gameBoard.getNumberOfPlaysRemaining();

        int expectedPlaysRemaining = 4;
        assertThat(actualPlaysRemaining, is(expectedPlaysRemaining));
    }

    @Test
    public void shouldIncreaseTheNumberOfPiecesPlayedWhenAPlayerPlaysAPiece() {
        int[][] masterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1},
        };
        GameBoard gameboard = new GameBoard(masterGame);

        gameboard.playPieceInColumn(0);

        int numberOfPiecesPlayed = gameboard.getCountOfPiecesPlayed();
        assertThat(numberOfPiecesPlayed, is(2));
    }

    @Test
    public void shouldRetrieveGameBoardAsListOfLists() {
        int[][] masterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1},
        };

        GameBoard gameboard = new GameBoard(masterGame);

        List<List<Integer>> actualGameboard = gameboard.getGameBoardAsList();

        List<List<Integer>> expectedGameboard = new ArrayList<List<Integer>>();
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1));

        assertEquals(expectedGameboard, actualGameboard);
    }

    @Test
    public void shouldCreateGameBoardFromList() {
        List<List<Integer>> startingGameboard = new ArrayList<List<Integer>>();
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1));

        GameBoard newGameBoard = new GameBoard(startingGameboard);

        List<List<Integer>> expectedGameboard = new ArrayList<List<Integer>>();
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1));

        List<List<Integer>> actualGameBoard = newGameBoard.getGameBoardAsList();

        assertEquals(expectedGameboard, actualGameBoard);
        assertNotSame(expectedGameboard, actualGameBoard);
    }

    private List<Integer> createBlankRow() {
        return Arrays.asList(0, 0, 0, 0, 0, 0, 0);
    }
}