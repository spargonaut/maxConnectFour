package org.spargonaut.maxConnectFour.players;

import org.junit.Test;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AiPlayerTest {

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
        int[] currentHighestScoringMove = {columnOfCurrentHighestScoringMove, scoreDiffOfCurrentHighestScoringMove};

        int columnToPlayForNextMove = 3;
        int scoreOfNextMove = 4;

        int[] nextMove = {columnToPlayForNextMove, scoreOfNextMove};
        int[] actualHighestScoringMove = aiPlayer.getHighestScoringMove(currentHighestScoringMove, nextMove, columnToPlayForNextMove);

        assertEquals(columnToPlayForNextMove, actualHighestScoringMove[0]);
        assertEquals(scoreOfNextMove, actualHighestScoringMove[1]);
    }

    @Test
    public void shouldKeepTheCurrentMoveAsHighestScoringMoveWHenNextMoveHasTheLowerScoringMove() {
        AiPlayer aiPlayer = new AiPlayer();

        int columnOfCurrentHighestScoringMove = 2;
        int scoreDiffOfCurrentHighestScoringMove = 4;
        int[] currentHighestScoringMove = {columnOfCurrentHighestScoringMove, scoreDiffOfCurrentHighestScoringMove};

        int columnToPlayForNextMove = 3;
        int scoreDiffOfNextMove = 2;

        int[] nextMove = {columnToPlayForNextMove, scoreDiffOfNextMove};
        int[] actualHighestScoringMove = aiPlayer.getHighestScoringMove(currentHighestScoringMove, nextMove, columnToPlayForNextMove);

        assertEquals(columnOfCurrentHighestScoringMove, actualHighestScoringMove[0]);
        assertEquals(scoreDiffOfCurrentHighestScoringMove, actualHighestScoringMove[1]);
    }

    @Test
    public void shouldIndicateTheNextMoveIsDefaultWhenAtMaxDepth() {
        int maxDepth = 99;
        int level = 99;
        int currentPlayer = 2;
        int alpha = 6;
        int beta = 7;

        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.getScoreDifferenceFromPerspectiveOf(currentPlayer)).thenReturn(10);


        AiPlayer aiPlayer = new AiPlayer();
        int[] actualNextMoveForBestMove = aiPlayer.getNextMoveForBestMove(maxDepth, level, currentPlayer, alpha, beta, gameBoard);

        int[] expectedNextMove = new int[]{-456, 10};

        assertEquals(expectedNextMove[0], actualNextMoveForBestMove[0]);
        assertEquals(expectedNextMove[1], actualNextMoveForBestMove[1]);
    }
}