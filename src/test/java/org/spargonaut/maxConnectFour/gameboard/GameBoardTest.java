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
        List<List<Integer>> startingGameboard = createFiveBlankRows();
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        GameBoard gameboard = new GameBoard(startingGameboard);

        gameboard.removePiece(0);

        List<List<Integer>> expectedGameboard = createFiveBlankRows();
        expectedGameboard.add(createBlankRow());
        List<List<Integer>> actualMasterGame = gameboard.getGameBoardAsList();

        assertEquals(expectedGameboard, actualMasterGame);
    }

    @Test
    public void shouldPlayPieceForPlayerOneInTheColumnIndicated() {
        List<List<Integer>> startingGameboard = createFiveBlankRows();
        startingGameboard.add(createBlankRow());
        GameBoard gameboard = new GameBoard(startingGameboard);

        gameboard.playPieceInColumn(0);

        List<List<Integer>> expectedGameboard = createFiveBlankRows();
        expectedGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        List<List<Integer>> actualMasterGame = gameboard.getGameBoardAsList();

        assertEquals(expectedGameboard, actualMasterGame);
    }

    @Test
    public void shouldPlayPieceForPlayerTwoInTheColumnIndicated() {
        List<List<Integer>> startingGameboard = createFiveBlankRows();
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        GameBoard gameboard = new GameBoard(startingGameboard);

        gameboard.playPieceInColumn(1);

        List<List<Integer>> expectedGameboard = createFiveBlankRows();
        expectedGameboard.add(Arrays.asList(1, 2, 0, 0, 0, 0, 0));

        List<List<Integer>> actualMasterGame = gameboard.getGameBoardAsList();

        assertEquals(expectedGameboard, actualMasterGame);
    }

    @Test
    public void shouldIndicateTheValidColumnsOfPlay() {
        List<List<Integer>> setUpGameboard = new ArrayList<List<Integer>>();
        setUpGameboard.add(Arrays.asList(1, 0, 1, 0, 0, 2, 0));
        setUpGameboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        setUpGameboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        setUpGameboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        setUpGameboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        setUpGameboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        GameBoard gameboard = new GameBoard(setUpGameboard);


        List<Integer> validPlays = gameboard.getColumnsOfValidPlays();
        assertThat(validPlays, hasItems(1, 3, 4, 6));
    }

    @Test
    public void shouldIndicateAnInvalidPlayWhenPlayColumnIsBelowLowestColumnNumber() {
        List<List<Integer>> startingGameboard = createFiveBlankRows();
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));

        GameBoard gameboard = new GameBoard(startingGameboard);

        boolean isValidPlay = gameboard.isValidPlay(-1);
        assertThat(isValidPlay, is(false));
    }

    @Test
    public void shouldIndicateAnInvalidPlayWhenPlayColumnIsGreaterThanHighestColumnNumber() {
        List<List<Integer>> startingGameboard = createFiveBlankRows();
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));

        GameBoard gameboard = new GameBoard(startingGameboard);

        boolean isValidPlay = gameboard.isValidPlay(8);
        assertThat(isValidPlay, is(false));
    }

    @Test
    public void shouldIndicateAnInvalidPlayWhenThePlayColumnsIsFull() {
        List<List<Integer>> startingGameboard = new ArrayList<List<Integer>>();
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        GameBoard gameboard = new GameBoard(startingGameboard);

        boolean isValidPlay = gameboard.isValidPlay(0);
        assertThat(isValidPlay, is(false));
    }

    @Test
    public void shouldIndicateThereArePossiblePlaysRemainingWhenTheGameboardIsNotFull() {
        List<List<Integer>> startingGameboard = createFiveBlankRows();
        startingGameboard.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1));

        GameBoard gameBoard = new GameBoard(startingGameboard);

        assertThat(gameBoard.hasPossiblePlays(), is(true));
    }

    @Test
    public void shouldIndicateThereAreNoPossiblePlaysWhenTheGameboardIsFull() {
        List<List<Integer>> startingGameboard = new ArrayList<List<Integer>>();
        startingGameboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        startingGameboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        startingGameboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        startingGameboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        startingGameboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        startingGameboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        GameBoard gameBoard = new GameBoard(startingGameboard);

        assertThat(gameBoard.hasPossiblePlays(), is(false));
    }

    @Test
    public void shouldIndicatePlayerOnesTurnWhenPlayCountIsEven() {
        List<List<Integer>> startingGameboard = createFiveBlankRows();
        startingGameboard.add(Arrays.asList(0, 0, 0, 0, 0, 1, 2));
        GameBoard gameboard = new GameBoard(startingGameboard);

        int currentPlayersTurn = gameboard.getCurrentTurnBasedOnNumberOfPlays();
        assertThat(currentPlayersTurn, is(1));
    }

    @Test
    public void shouldIndicatePlayerTwosTurnWhenPlayCountIsOdd() {
        List<List<Integer>> startingGameboard = createFiveBlankRows();
        startingGameboard.add(Arrays.asList(0, 0, 0, 0, 0, 1, 0));
        GameBoard gameboard = new GameBoard(startingGameboard);

        int currentPlayersTurn = gameboard.getCurrentTurnBasedOnNumberOfPlays();
        assertThat(currentPlayersTurn, is(2));
    }

    @Test
    public void shouldGetNumberOfPlaysRemaining() {
        List<List<Integer>> startingGameboard = new ArrayList<List<Integer>>();
        startingGameboard.add(Arrays.asList(1, 0, 1, 0, 0, 2, 0));
        startingGameboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        startingGameboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        startingGameboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        startingGameboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        startingGameboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        GameBoard gameBoard = new GameBoard(startingGameboard);


        int actualPlaysRemaining = gameBoard.getNumberOfPlaysRemaining();

        int expectedPlaysRemaining = 4;
        assertThat(actualPlaysRemaining, is(expectedPlaysRemaining));
    }

    @Test
    public void shouldRetrieveGameBoardAsListOfLists() {
        List<List<Integer>> startingGameboard = createFiveBlankRows();
        startingGameboard.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1));

        GameBoard gameboard = new GameBoard(startingGameboard);

        List<List<Integer>> actualGameboard = gameboard.getGameBoardAsList();

        List<List<Integer>> expectedGameboard = createFiveBlankRows();
        expectedGameboard.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1));

        assertEquals(expectedGameboard, actualGameboard);
    }

    @Test
    public void shouldCreateGameBoardFromList() {
        List<List<Integer>> startingGameboard = createFiveBlankRows();
        startingGameboard.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1));

        GameBoard newGameBoard = new GameBoard(startingGameboard);

        List<List<Integer>> expectedGameboard = createFiveBlankRows();
        expectedGameboard.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1));

        List<List<Integer>> actualGameBoard = newGameBoard.getGameBoardAsList();

        assertEquals(expectedGameboard, actualGameBoard);
        assertNotSame(expectedGameboard, actualGameBoard);
    }

    private List<List<Integer>> createFiveBlankRows() {
        List<List<Integer>> expectedGameboard = new ArrayList<List<Integer>>();
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        expectedGameboard.add(createBlankRow());
        return expectedGameboard;
    }

    private List<Integer> createBlankRow() {
        return Arrays.asList(0, 0, 0, 0, 0, 0, 0);
    }
}