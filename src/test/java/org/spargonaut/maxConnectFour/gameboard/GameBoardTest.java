package org.spargonaut.maxConnectFour.gameboard;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class GameBoardTest {

    @Test
    public void shouldRemoveTheSpecifiedPieceFromTheBoard() throws Exception {
        int[][] masterGame = {
                {1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
        };
        GameBoard gameboard = new GameBoard(masterGame);

        gameboard.removePiece(0);

        int [][] expectedMasterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
        };
        int[][] actualMasterGame = gameboard.getGameBoard();

        assertArrayEquals(expectedMasterGame, actualMasterGame);
    }

    @Test
    public void shouldPlayPieceForPlayerOneInTheColumnIndicated() {
        int[][] masterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
        };
        GameBoard gameboard = new GameBoard(masterGame);

        gameboard.playPieceInColumn(0);

        int [][] expectedMasterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
        };
        int[][] actualMasterGame = gameboard.getGameBoard();

        assertArrayEquals(expectedMasterGame, actualMasterGame);
    }

    @Test
    public void shouldPlayPieceForPlayerTwoInTheColumnIndicated() {
        int[][] masterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
        };
        GameBoard gameboard = new GameBoard(masterGame);

        gameboard.playPieceInColumn(1);

        int [][] expectedMasterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 2, 0, 0, 0, 0, 0},
        };
        int[][] actualMasterGame = gameboard.getGameBoard();

        assertArrayEquals(expectedMasterGame, actualMasterGame);
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
    public void shouldIndicateAScoreOfOneWhenFourPlaysAreNextToEachOtherHorizontally() {
        int[][] masterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0},
        };
        GameBoard gameboard = new GameBoard(masterGame);

        int playerOneScore = gameboard.getScore(1);

        assertThat(playerOneScore, is(1));
    }

    @Test
    public void shouldIndicateAScoreOfTwoWhenFourPlaysAreNextToEachOtherHorizontally() {
        int[][] masterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 0, 0},
        };
        GameBoard gameboard = new GameBoard(masterGame);

        int playerOneScore = gameboard.getScore(1);

        assertThat(playerOneScore, is(2));
    }

    @Test
    public void shouldIndicateAScoreOfOneWhenFourPlaysAreNextToEachOtherVertically() {
        int[][] masterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
        };
        GameBoard gameboard = new GameBoard(masterGame);

        int playerOneScore = gameboard.getScore(1);

        assertThat(playerOneScore, is(1));
    }

    @Test
    public void shouldIndicateAScoreOfTwoWhenFourPlaysAreNextToEachOtherVertically() {
        int[][] masterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
        };
        GameBoard gameboard = new GameBoard(masterGame);

        int playerOneScore = gameboard.getScore(1);

        assertThat(playerOneScore, is(2));
    }

    @Test
    public void shouldIndicateAScoreOfOneWhenFourPlaysAreNextToEachOtherDiagnallyGoingUp() {
        int[][] masterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
        };
        GameBoard gameboard = new GameBoard(masterGame);

        int playerOneScore = gameboard.getScore(1);

        assertThat(playerOneScore, is(1));
    }

    @Test
    public void shouldIndicateAScoreOfTwoWhenFourPlaysAreNextToEachOtherDiagnallyGoingUp() {
        int[][] masterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
        };
        GameBoard gameboard = new GameBoard(masterGame);

        int playerOneScore = gameboard.getScore(1);

        assertThat(playerOneScore, is(2));
    }

    @Test
    public void shouldIndicateAScoreOfOneWhenFourPlaysAreNextToEachOtherDiagnallyGoingDown() {
        int[][] masterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 1},
        };
        GameBoard gameboard = new GameBoard(masterGame);

        int playerOneScore = gameboard.getScore(1);

        assertThat(playerOneScore, is(1));
    }

    @Test
    public void shouldIndicateAScoreOfTwoWhenFourPlaysAreNextToEachOtherDiagnallyGoingDown() {
        int[][] masterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 1},
        };
        GameBoard gameboard = new GameBoard(masterGame);

        int playerOneScore = gameboard.getScore(1);

        assertThat(playerOneScore, is(2));
    }
}