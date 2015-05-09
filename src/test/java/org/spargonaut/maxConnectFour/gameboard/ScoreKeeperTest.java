package org.spargonaut.maxConnectFour.gameboard;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ScoreKeeperTest {

    private int totalColumnCount = 7;
    private int totalRowCount = 6;

    @Test
    public void shouldIndicateAScoreOfOneWhenFourPlaysAreNextToEachOtherHorizontally() {
        List<List<Integer>> gameboard = new ArrayList<List<Integer>>();
        gameboard.add(Arrays.asList(1, 1, 1, 1, 0, 0, 0));
        gameboard.add(createBlankRow());
        gameboard.add(createBlankRow());
        gameboard.add(createBlankRow());
        gameboard.add(createBlankRow());
        gameboard.add(createBlankRow());

        ScoreKeeper scoreKeeper = new ScoreKeeper(gameboard);

        int playerOneScore = scoreKeeper.getScoreForPlayer(1);
        assertThat(playerOneScore, is(1));
    }

    private List<Integer> createBlankRow() {
        return Arrays.asList(0, 0, 0, 0, 0, 0, 0);
    }

    @Test
    public void shouldIndicateAScoreOfTwoWhenFourPlaysAreNextToEachOtherHorizontally() {
        List<List<Integer>> gameboard = new ArrayList<List<Integer>>();
        gameboard.add(Arrays.asList(1, 1, 1, 1, 1, 0, 0));
        gameboard.add(createBlankRow());
        gameboard.add(createBlankRow());
        gameboard.add(createBlankRow());
        gameboard.add(createBlankRow());
        gameboard.add(createBlankRow());

        ScoreKeeper scoreKeeper = new ScoreKeeper(gameboard);

        int playerOneScore = scoreKeeper.getScoreForPlayer(1);
        assertThat(playerOneScore, is(2));
    }

    @Test
    public void shouldIndicateAScoreOfOneWhenFourPlaysAreNextToEachOtherVertically() {
        List<List<Integer>> gameboard = new ArrayList<List<Integer>>();

        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(createBlankRow());
        gameboard.add(createBlankRow());

        ScoreKeeper scoreKeeper = new ScoreKeeper(gameboard);

        int playerOneScore = scoreKeeper.getScoreForPlayer(1);
        assertThat(playerOneScore, is(1));
    }

    @Test
    public void shouldIndicateAScoreOfTwoWhenFourPlaysAreNextToEachOtherVertically() {
        List<List<Integer>> gameboard = new ArrayList<List<Integer>>();

        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(createBlankRow());

        ScoreKeeper scoreKeeper = new ScoreKeeper(gameboard);

        int playerOneScore = scoreKeeper.getScoreForPlayer(1);
        assertThat(playerOneScore, is(2));
    }

    @Test
    public void shouldIndicateAScoreOfOneWhenFourPlaysAreNextToEachOtherDiagnallyGoingUp() {
        List<List<Integer>> gameboard = new ArrayList<List<Integer>>();

        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(0, 1, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(0, 0, 1, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(0, 0, 0, 1, 0, 0, 0));
        gameboard.add(createBlankRow());
        gameboard.add(createBlankRow());

        ScoreKeeper scoreKeeper = new ScoreKeeper(gameboard);

        int playerOneScore = scoreKeeper.getScoreForPlayer(1);
        assertThat(playerOneScore, is(1));
    }

    @Test
    public void shouldIndicateAScoreOfTwoWhenFourPlaysAreNextToEachOtherDiagnallyGoingUp() {
        List<List<Integer>> gameboard = new ArrayList<List<Integer>>();

        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(0, 1, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(0, 0, 1, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(0, 0, 0, 1, 0, 0, 0));
        gameboard.add(Arrays.asList(0, 0, 0, 0, 1, 0, 0));
        gameboard.add(createBlankRow());

        ScoreKeeper scoreKeeper = new ScoreKeeper(gameboard);

        int playerOneScore = scoreKeeper.getScoreForPlayer(1);

        assertThat(playerOneScore, is(2));
    }

    @Test
    public void shouldIndicateAScoreOfOneWhenFourPlaysAreNextToEachOtherDiagnallyGoingDown() {
        List<List<Integer>> gameboard = new ArrayList<List<Integer>>();
        gameboard.add(Arrays.asList(0, 0, 0, 1, 0, 0, 0));
        gameboard.add(Arrays.asList(0, 0, 1, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(0, 1, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(createBlankRow());
        gameboard.add(createBlankRow());

        ScoreKeeper scoreKeeper = new ScoreKeeper(gameboard);

        int playerOneScore = scoreKeeper.getScoreForPlayer(1);
        assertThat(playerOneScore, is(1));
    }

    @Test
    public void shouldIndicateAScoreOfTwoWhenFourPlaysAreNextToEachOtherDiagnallyGoingDown() {
        List<List<Integer>> gameboard = new ArrayList<List<Integer>>();
        gameboard.add(Arrays.asList(0, 0, 0, 0, 1, 0, 0));
        gameboard.add(Arrays.asList(0, 0, 0, 1, 0, 0, 0));
        gameboard.add(Arrays.asList(0, 0, 1, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(0, 1, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(createBlankRow());

        ScoreKeeper scoreKeeper = new ScoreKeeper(gameboard);

        int playerOneScore = scoreKeeper.getScoreForPlayer(1);
        assertThat(playerOneScore, is(2));
    }

    @Test
    public void shouldIndicateAScoreDifferenceOfOneFromThePerspectiveOfPlayerOne() {
        List<List<Integer>> gameboard = new ArrayList<List<Integer>>();
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 2, 2, 2, 2, 0, 0));
        gameboard.add(createBlankRow());

        ScoreKeeper scoreKeeper = new ScoreKeeper(gameboard);

        int scoreDifference = scoreKeeper.getScoreDifferenceFromPerspectiveOf(1);
        assertThat(scoreDifference, is(1));
    }

    @Test
    public void shouldIndicateAScoreDifferenceOfNegativeOneFromThePerspectiveOfPlayerTwo() {
        List<List<Integer>> gameboard = new ArrayList<List<Integer>>();
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 2, 2, 2, 2, 0, 0));
        gameboard.add(createBlankRow());

        ScoreKeeper scoreKeeper = new ScoreKeeper(gameboard);

        int scoreDifference = scoreKeeper.getScoreDifferenceFromPerspectiveOf(2);
        assertThat(scoreDifference, is(-1));
    }
}