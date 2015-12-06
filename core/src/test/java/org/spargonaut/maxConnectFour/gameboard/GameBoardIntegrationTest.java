package org.spargonaut.maxConnectFour.gameboard;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GameBoardIntegrationTest {
    @Test
    public void shouldIndicateAScoreOfOneWhenFourPlaysAreNextToEachOtherHorizontally() {
        List<List<Integer>> startingGameboard = new ArrayList<List<Integer>>();
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(Arrays.asList(1, 1, 1, 1, 0, 0, 0));
        GameBoard gameboard = new GameBoard(startingGameboard);

        int playerOneScore = gameboard.getScore(1);

        assertThat(playerOneScore, is(1));
    }

    @Test
    public void shouldIndicateAScoreOfTwoWhenFourPlaysAreNextToEachOtherHorizontally() {
        List<List<Integer>> startingGameboard = new ArrayList<List<Integer>>();
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(Arrays.asList(1, 1, 1, 1, 1, 0, 0));
        GameBoard gameboard = new GameBoard(startingGameboard);

        int playerOneScore = gameboard.getScore(1);

        assertThat(playerOneScore, is(2));
    }

    private List<Integer> createBlankRow() {
        return Arrays.asList(0, 0, 0, 0, 0, 0, 0);
    }

    @Test
    public void shouldIndicateAScoreOfOneWhenFourPlaysAreNextToEachOtherVertically() {
        List<List<Integer>> startingGameboard = new ArrayList<List<Integer>>();
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        GameBoard gameboard = new GameBoard(startingGameboard);

        int playerOneScore = gameboard.getScore(1);

        assertThat(playerOneScore, is(1));
    }

    @Test
    public void shouldIndicateAScoreOfTwoWhenFourPlaysAreNextToEachOtherVertically() {
        List<List<Integer>> startingGameboard = new ArrayList<List<Integer>>();
        startingGameboard.add(createBlankRow());
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        GameBoard gameboard = new GameBoard(startingGameboard);

        int playerOneScore = gameboard.getScore(1);

        assertThat(playerOneScore, is(2));
    }

    @Test
    public void shouldIndicateAScoreOfOneWhenFourPlaysAreNextToEachOtherDiagnallyGoingUp() {
        List<List<Integer>> startingGameboard = new ArrayList<List<Integer>>();
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(Arrays.asList(0, 0, 0, 1, 0, 0, 0));
        startingGameboard.add(Arrays.asList(0, 0, 1, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(0, 1, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        GameBoard gameboard = new GameBoard(startingGameboard);

        int playerOneScore = gameboard.getScore(1);

        assertThat(playerOneScore, is(1));
    }

    @Test
    public void shouldIndicateAScoreOfTwoWhenFourPlaysAreNextToEachOtherDiagnallyGoingUp() {
        List<List<Integer>> startingGameboard = new ArrayList<List<Integer>>();
        startingGameboard.add(createBlankRow());
        startingGameboard.add(Arrays.asList(0, 0, 0, 0, 1, 0, 0));
        startingGameboard.add(Arrays.asList(0, 0, 0, 1, 0, 0, 0));
        startingGameboard.add(Arrays.asList(0, 0, 1, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(0, 1, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        GameBoard gameboard = new GameBoard(startingGameboard);

        int playerOneScore = gameboard.getScore(1);

        assertThat(playerOneScore, is(2));
    }

    @Test
    public void shouldIndicateAScoreOfOneWhenFourPlaysAreNextToEachOtherDiagnallyGoingDown() {
        List<List<Integer>> startingGameboard = new ArrayList<List<Integer>>();
        startingGameboard.add(createBlankRow());
        startingGameboard.add(createBlankRow());
        startingGameboard.add(Arrays.asList(0, 0, 0, 1, 0, 0, 0));
        startingGameboard.add(Arrays.asList(0, 0, 0, 0, 1, 0, 0));
        startingGameboard.add(Arrays.asList(0, 0, 0, 0, 0, 1, 0));
        startingGameboard.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1));
        GameBoard gameboard = new GameBoard(startingGameboard);

        int playerOneScore = gameboard.getScore(1);

        assertThat(playerOneScore, is(1));
    }

    @Test
    public void shouldIndicateAScoreOfTwoWhenFourPlaysAreNextToEachOtherDiagnallyGoingDown() {
        List<List<Integer>> startingGameboard = new ArrayList<List<Integer>>();
        startingGameboard.add(createBlankRow());
        startingGameboard.add(Arrays.asList(0, 0, 1, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(0, 0, 0, 1, 0, 0, 0));
        startingGameboard.add(Arrays.asList(0, 0, 0, 0, 1, 0, 0));
        startingGameboard.add(Arrays.asList(0, 0, 0, 0, 0, 1, 0));
        startingGameboard.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1));
        GameBoard gameboard = new GameBoard(startingGameboard);

        int playerOneScore = gameboard.getScore(1);

        assertThat(playerOneScore, is(2));
    }

    @Test
    public void shouldIndicateAScoreDifferenceOfOneFromThePerspectiveOfPlayerOne() {
        List<List<Integer>> startingGameboard = new ArrayList<List<Integer>>();
        startingGameboard.add(createBlankRow());
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 2, 2, 2, 2, 0, 0));
        GameBoard gameboard = new GameBoard(startingGameboard);

        int scoreDifference = gameboard.getScoreDifferenceFromPerspectiveOf(1);
        assertThat(scoreDifference, is(1));
    }

    @Test
    public void shouldIndicateAScoreDifferenceOfNegativeOneFromThePerspectiveOfPlayerTwo() {
        List<List<Integer>> startingGameboard = new ArrayList<List<Integer>>();
        startingGameboard.add(createBlankRow());
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        startingGameboard.add(Arrays.asList(1, 2, 2, 2, 2, 0, 0));
        GameBoard gameboard = new GameBoard(startingGameboard);


        int scoreDifference = gameboard.getScoreDifferenceFromPerspectiveOf(2);
        assertThat(scoreDifference, is(-1));
    }
}