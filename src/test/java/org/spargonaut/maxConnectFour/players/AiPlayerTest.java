package org.spargonaut.maxConnectFour.players;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class AiPlayerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

//  These tests being commented out is bad and I feel bad
//	@Test
//	public void shouldReturnAlphaWhenMaximizingAlphaWithAlphaIsGreaterThanTheScoreOfTheBestMove() {
//
//		AiPlayer aiPlayer = new AiPlayer();
//		int[] bestMove = {-1, 1};
//		int expectedAlpha = 999;
//		int actualAlpha = aiPlayer.maximizeAlphaFOrBestMove(expectedAlpha, bestMove);
//		assertEquals(expectedAlpha, actualAlpha);
//	}
//
//	@Test
//	public void shouldReturnTheScoreOfTheBestMoveWhenMaximizingAlphaWithAlphaIsLessThanTheScoreOfTheBestMove() {
//		AiPlayer aiPlayer = new AiPlayer();
//		int[] bestMove = {-1, 999};
//		int smallAlpha = 1;
//		
//		int expectedAlpha = 999;
//		
//		int actualAlpha = aiPlayer.maximizeAlphaFOrBestMove(smallAlpha, bestMove);
//		
//		assertEquals(expectedAlpha, actualAlpha);
//	}
//	
//	@Test
//	public void shouldReturnAlphaWhenMaximizingAlphaWithAlphaEqualToTheScoreOfTheBestMove() {
//		AiPlayer aiPlayer = new AiPlayer();
//		int expectedAlpha = 999;
//		int[] bestMove = {-1, expectedAlpha};
//		int actualAlpha = aiPlayer.maximizeAlphaFOrBestMove(expectedAlpha, bestMove);
//		assertEquals(expectedAlpha, actualAlpha);
//	}

	@Test
	public void shouldKeepTheNextMoveAsLowestScoringMoveWhenNextMoveHasTheLowestScoringMove() {
		AiPlayer aiPlayer = new AiPlayer();

        int columnOfCurrentWorstMove = 2;
        int scoreDiffOfCurrentWorstMove = -1;
        int[] currentWorstMove = {columnOfCurrentWorstMove, scoreDiffOfCurrentWorstMove};

		int columnToPlay = 3;
        int scoreOfNextWorstMove = -3;

		int[] nextMove = {columnToPlay, scoreOfNextWorstMove};
		int[] actualLowestScoringMove = aiPlayer.getLowestScoringMove(currentWorstMove, columnToPlay, nextMove);

		assertEquals(columnToPlay, actualLowestScoringMove[0]);
		assertEquals(scoreOfNextWorstMove, actualLowestScoringMove[1]);
	}

    @Test
    public void shouldKeepTheCurrentLowestScoringMoveWhenNextMoveHasHigerScoreDifference() {
        AiPlayer aiPlayer = new AiPlayer();

        int defaultLowestColumn = -77;
        int defaultLowestMoveScoreDiff = -88;

        int columnToPlayForCurrentWorstMove = 2;
        int scoreDiffOfCurrentWorstMove = -5;
        int[] currentWorstMove = {columnToPlayForCurrentWorstMove, scoreDiffOfCurrentWorstMove};

        int columnToPlayForNextMove = 3;
        int scoreOfNextMove = -3;

        int[] nextMove = {columnToPlayForNextMove, scoreOfNextMove};
        int[] actualLowestScoringMove = aiPlayer.getLowestScoringMove(currentWorstMove, columnToPlayForNextMove, nextMove);

        assertEquals(defaultLowestColumn, actualLowestScoringMove[0]);
        assertEquals(defaultLowestMoveScoreDiff, actualLowestScoringMove[1]);
    }

    @Test
    public void shouldKeepTheNextMoveAsHighestScoringMoveWhenNextMoveHasTheHighestScoringMove() {
        AiPlayer aiPlayer = new AiPlayer();

        int columnOfCurrentHighestScoringMove = 2;
        int scoreDiffOfCurrentHighestScoringMove = -1;
        Play currentHighestScoringPlay = new Play.PlayBuilder()
                .column(columnOfCurrentHighestScoringMove)
                .scoreDifference(scoreDiffOfCurrentHighestScoringMove)
                .build();

        int columnToPlayForNextMove = 3;
        int scoreOfNextMove = 4;
        Play nextPlay = new Play.PlayBuilder()
                .column(columnToPlayForNextMove)
                .scoreDifference(scoreOfNextMove)
                .build();

        Play actualHighestScoringMove = aiPlayer.getHighestScoringMove(currentHighestScoringPlay, nextPlay, columnToPlayForNextMove);

        assertEquals(columnToPlayForNextMove, actualHighestScoringMove.getColumn());
        assertEquals(scoreOfNextMove, actualHighestScoringMove.getScoreDifference());
    }

    @Test
    public void shouldKeepTheCurrentMoveAsHighestScoringMoveWHenNextMoveHasTheLowerScoringMove() {
        AiPlayer aiPlayer = new AiPlayer();

        int columnOfCurrentHighestScoringMove = 2;
        int scoreDiffOfCurrentHighestScoringMove = 4;
        Play currentHighestScoringPlay = new Play.PlayBuilder()
                .column(columnOfCurrentHighestScoringMove)
                .scoreDifference(scoreDiffOfCurrentHighestScoringMove)
                .build();

        int columnToPlayForNextMove = 3;
        int scoreDiffOfNextMove = 2;
        Play nextPlay = new Play.PlayBuilder()
                .column(columnToPlayForNextMove)
                .scoreDifference(scoreDiffOfNextMove)
                .build();

        Play actualHighestScoringMove = aiPlayer.getHighestScoringMove(currentHighestScoringPlay, nextPlay, columnToPlayForNextMove);

        assertEquals(columnOfCurrentHighestScoringMove, actualHighestScoringMove.getColumn());
        assertEquals(scoreDiffOfCurrentHighestScoringMove, actualHighestScoringMove.getScoreDifference());
    }

    @Test
    public void shouldIndicateTheNextMoveIsDefaultWhenAtMaxDepthForBestMove() {
        int maxDepth = 99;
        int level = 99;
        int currentPlayer = 2;
        int alpha = 6;
        int beta = 7;

        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.getScoreDifferenceFromPerspectiveOf(currentPlayer)).thenReturn(10);

        AiPlayer aiPlayer = new AiPlayer();
        Play actualNextMoveForBestMove = aiPlayer.getNextMoveForBestMove(maxDepth, level, currentPlayer, alpha, beta, gameBoard);

        int[] expectedNextMove = new int[]{-456, 10};

        assertEquals(expectedNextMove[0], actualNextMoveForBestMove.getColumn());
        assertEquals(expectedNextMove[1], actualNextMoveForBestMove.getScoreDifference());
    }

    @Test
    public void shouldIndicateTheNextMoveIsTheWorstMoveWhenNotAtMaxDepthForBestMove() {
        int maxDepth = 99;
        int level = 98;
        int currentPlayer = 2;
        int alpha = 6;
        int beta = 7;

        List<List<Integer>> newEmptyGameBoard = new ArrayList<>();
        newEmptyGameBoard.add(createBlankRow());
        newEmptyGameBoard.add(createBlankRow());
        newEmptyGameBoard.add(createBlankRow());
        newEmptyGameBoard.add(createBlankRow());
        newEmptyGameBoard.add(createBlankRow());
        newEmptyGameBoard.add(createBlankRow());

        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.getGameBoardAsList()).thenReturn(newEmptyGameBoard);

        int columnForNextPlay = 2;
        int scoreDiffForNextPlay = 5;

        AiPlayer aiPlayer = spy(new AiPlayer());
        when(aiPlayer.generateWorstMoveRef(maxDepth, level + 1, currentPlayer, gameBoard, alpha, beta))
                .thenReturn(new int[]{columnForNextPlay, scoreDiffForNextPlay});

        Play actualNextMoveForBestMove = aiPlayer.getNextMoveForBestMove(maxDepth, level, currentPlayer, alpha, beta, gameBoard);

        int[] expectedNextMove = new int[]{columnForNextPlay, scoreDiffForNextPlay};

        assertEquals(expectedNextMove[0], actualNextMoveForBestMove.getColumn());
        assertEquals(expectedNextMove[1], actualNextMoveForBestMove.getScoreDifference());
    }

    @Test
    public void shouldGetTheNextMoveAsDefaultWhenAtMaxDepthForWorstMove() {
        int maxDepth = 99;
        int level = 99;
        int currentPlayer = 2;
        int alpha = 6;
        int beta = 7;

        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.getScoreDifferenceFromPerspectiveOf(currentPlayer)).thenReturn(10);

        AiPlayer aiPlayer = new AiPlayer();
        int[] actualNextMoveForBestMove = aiPlayer.getNextMoveForWorstMove(maxDepth, level, currentPlayer, alpha, beta, gameBoard);

        int[] expectedNextMove = new int[]{-4, 10};

        assertEquals(expectedNextMove[0], actualNextMoveForBestMove[0]);
        assertEquals(expectedNextMove[1], actualNextMoveForBestMove[1]);
    }

    @Test
    public void shouldGetTheNextMoveAsTheWorstMoveWhenNotAtMaxDepthForWorstMove() {
        int maxDepth = 99;
        int level = 98;
        int currentPlayer = 2;
        int alpha = 6;
        int beta = 7;

        List<List<Integer>> newEmptyPlayBoard = new ArrayList<>();
        newEmptyPlayBoard.add(createBlankRow());
        newEmptyPlayBoard.add(createBlankRow());
        newEmptyPlayBoard.add(createBlankRow());
        newEmptyPlayBoard.add(createBlankRow());
        newEmptyPlayBoard.add(createBlankRow());
        newEmptyPlayBoard.add(createBlankRow());

        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.getGameBoardAsList()).thenReturn(newEmptyPlayBoard);

        int columnForNextPlay = 2;
        int scoreDiffForNextPlay = 5;

        AiPlayer aiPlayer = spy(new AiPlayer());
        when(aiPlayer.generateBestMoveRef(maxDepth, level + 1, currentPlayer, gameBoard, alpha, beta))
                .thenReturn(new int[]{columnForNextPlay, scoreDiffForNextPlay});

        int[] actualNextMoveForBestMove = aiPlayer.getNextMoveForWorstMove(maxDepth, level, currentPlayer, alpha, beta, gameBoard);

        int[] expectedNextMove = new int[]{columnForNextPlay, scoreDiffForNextPlay};

        assertEquals(expectedNextMove[0], actualNextMoveForBestMove[0]);
        assertEquals(expectedNextMove[1], actualNextMoveForBestMove[1]);
    }

    @Test
    public void shouldReturnTrueWhenAtMaxSearchDepth() {
        int maxDepth = 99;
        int currentLevel = 99;

        AiPlayer aiPlayer = new AiPlayer();
        boolean isAtMaxDepth = aiPlayer.isAtMaxDepth(maxDepth, currentLevel);

        assertEquals(true, isAtMaxDepth);
    }

    @Test
    public void shouldReturnFalseWhenThereAreMorePlaysToSearch() {
        int maxDepth = 99;
        int currentLevel = 98;

        AiPlayer aiPlayer = new AiPlayer();
        boolean isAtMaxDepth = aiPlayer.isAtMaxDepth(maxDepth, currentLevel);

        assertEquals(false, isAtMaxDepth
        );
    }

    @Test
    public void shouldCreateANewAiPlayerWithASearchDepth() {
        int searchDepth = 5;
        AiPlayer aiPlayer = new AiPlayer(searchDepth);

        int expectedSearchDepth = 5;
        assertEquals(aiPlayer.getSearchDepth(), expectedSearchDepth);
    }

    @Test
    public void shouldTellTheUserWhichPlayerItIsFindingTheBestMoveForAndTheSearchDepthItIsUsing() {
        int searchDepthLevel = 0;

        List<List<Integer>> newEmptyPlayBoard = new ArrayList<>();
        newEmptyPlayBoard.add(createBlankRow());
        newEmptyPlayBoard.add(createBlankRow());
        newEmptyPlayBoard.add(createBlankRow());
        newEmptyPlayBoard.add(createBlankRow());
        newEmptyPlayBoard.add(createBlankRow());
        newEmptyPlayBoard.add(createBlankRow());
        GameBoard gameBoard = new GameBoard(newEmptyPlayBoard);

        AiPlayer aiPlayer = new AiPlayer(searchDepthLevel);
        aiPlayer.getBestPlay(gameBoard);

        String expectedString = "\n\n" +
                " I am playing as player: 1" +
                "\n" +
                "  searching for the best play to depth level: " + searchDepthLevel +
                "\n";

        assertThat(outContent.toString(), startsWith(expectedString));
    }

    @Test
    public void shouldTellTheUserWhichColumnTheAiPlayerIsPlayingIn() {
        int searchDepthLevel = 0;

        List<List<Integer>> newEmptyPlayBoard = new ArrayList<>();
        newEmptyPlayBoard.add(createBlankRow());
        newEmptyPlayBoard.add(createBlankRow());
        newEmptyPlayBoard.add(createBlankRow());
        newEmptyPlayBoard.add(createBlankRow());
        newEmptyPlayBoard.add(createBlankRow());
        newEmptyPlayBoard.add(createBlankRow());
        GameBoard gameBoard = new GameBoard(newEmptyPlayBoard);

        AiPlayer aiPlayer = new AiPlayer(searchDepthLevel);
        aiPlayer.getBestPlay(gameBoard);

        String expectedString = "  and I'm playing in column 1\n";

        assertThat(outContent.toString(), endsWith(expectedString));
    }

    private List<Integer> createBlankRow() {
        return Arrays.asList(0, 0, 0, 0, 0, 0, 0);
    }
}