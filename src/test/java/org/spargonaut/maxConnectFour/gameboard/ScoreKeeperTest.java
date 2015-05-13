package org.spargonaut.maxConnectFour.gameboard;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ScoreKeeperTest {

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

    @Test
    public void shouldPrintThePlayersScoresToTheScreen() {
        List<List<Integer>> gameboard = new ArrayList<List<Integer>>();
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 0, 0, 0, 0, 0, 0));
        gameboard.add(Arrays.asList(1, 2, 2, 2, 2, 0, 0));
        gameboard.add(createBlankRow());

        ScoreKeeper scoreKeeper = new ScoreKeeper(gameboard);
        scoreKeeper.printCurrentScores();

        String expectedOutput = "Scores:\n Player1: 2\n Player2: 1\n\n";


        assertEquals(expectedOutput, outContent.toString());
    }
}