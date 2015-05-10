package org.spargonaut.maxConnectFour.gameboard;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GameBoardIntegrationTest {
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

    @Test
    public void shouldIndicateAScoreDifferenceOfOneFromThePerspectiveOfPlayerOne() {
        int[][] masterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 2, 2, 2, 2, 0, 0},
        };
        GameBoard gameboard = new GameBoard(masterGame);

        int scoreDifference = gameboard.getScoreDifferenceFromPerspectiveOf(1);
        assertThat(scoreDifference, is(1));
    }

    @Test
    public void shouldIndicateAScoreDifferenceOfNegativeOneFromThePerspectiveOfPlayerTwo() {
        int[][] masterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {1, 2, 2, 2, 2, 0, 0},
        };
        GameBoard gameboard = new GameBoard(masterGame);

        int scoreDifference = gameboard.getScoreDifferenceFromPerspectiveOf(2);
        assertThat(scoreDifference, is(-1));
    }

}