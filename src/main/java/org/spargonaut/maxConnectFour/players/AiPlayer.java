package org.spargonaut.maxConnectFour.players;

import org.spargonaut.maxConnectFour.gameboard.GameBoard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * This is the AiPlayer class.  It simulates a minimax player with alpha beta pruning for the max connect four game.
 */

public class AiPlayer implements Player {

    private final String ALPHA = "ALPHA";
    private final String BETA = "BETA";

    private final String COLUMN = "column";
    private final String SCORE_DIFFERENCE = "score_difference";

    public int getRandomPlay( GameBoard currentGame ) {
        Random randy = new Random();
        List<Integer> validPlays = currentGame.getColumnsOfValidPlays();
        int randomPlayIndex = randy.nextInt( validPlays.size() );
        return validPlays.get(randomPlayIndex);
    }

    public Integer getBestPlay( GameBoard currentGame, int depthLevel ) {
        depthLevel = Math.min(depthLevel, currentGame.getNumberOfPlaysRemaining());
        int currentTurn = currentGame.getCurrentTurnBasedOnNumberOfPlays();
        Map<String, Integer> bestPlayMap = new HashMap<String, Integer>();
        bestPlayMap.put(ALPHA, -999);
        bestPlayMap.put(BETA, 99999);
        int[] bestPlay = generateBestMoveRef(depthLevel, 1, currentTurn, currentGame, bestPlayMap.get(ALPHA), bestPlayMap.get(BETA));
        return bestPlay[0];
    }

    protected int[] generateBestMoveRef( int maxDepth, int level, int currentPlayer, GameBoard lastBoard, int alpha, int beta ) {
        // I need to take into account when two moves are equal - i think this is where i need to add in heuristics
        GameBoard testBoard = new GameBoard( lastBoard.getGameBoardAsList() );

        int move[] = { -1, -999 };		// { column, scoreDiff }

        List<Integer> validColumns = testBoard.getColumnsOfValidPlays();

        for (Integer column : validColumns) {
            testBoard.playPieceInColumn(column);

            int[] nextMove = getNextMoveForBestMove(maxDepth, level, currentPlayer, alpha, beta, testBoard);

            move = getHighestScoringMove(move, nextMove, column);

            if( move[ 1 ] >= beta ) {
                break;
            }

            alpha = Math.max(alpha, move[1]);
            testBoard.removePiece( column );
        }

        return move;
    }

    protected int[] getNextMoveForBestMove(int maxDepth, int level, int currentPlayer, int alpha, int beta, GameBoard testBoard) {
        int[] nextMove;
        if (isAtMaxDepth(maxDepth, level)) {
            nextMove = new int[] {-456, testBoard.getScoreDifferenceFromPerspectiveOf(currentPlayer)};
        } else {
            nextMove = generateWorstMoveRef( maxDepth, level + 1, currentPlayer, testBoard, alpha, beta );
        }
        return nextMove;
    }

    protected int[] getHighestScoringMove(int[] bestMove, int[] nextMove, int columnToPlay) {
        Map<String, Integer> currentBestMove = new HashMap<String, Integer>();
        currentBestMove.put(COLUMN, bestMove[0]);
        currentBestMove.put(SCORE_DIFFERENCE, bestMove[1]);

        Map<String, Integer> nextMoveMap = new HashMap<String, Integer>();
        nextMoveMap.put(COLUMN, nextMove[0]);
        nextMoveMap.put(SCORE_DIFFERENCE, nextMove[1]);

        if (nextMoveMap.get(SCORE_DIFFERENCE) > currentBestMove.get(SCORE_DIFFERENCE)) {
            currentBestMove.put(COLUMN, columnToPlay);
            currentBestMove.put(SCORE_DIFFERENCE, nextMoveMap.get(SCORE_DIFFERENCE));
        }

        return new int[]{currentBestMove.get(COLUMN), currentBestMove.get(SCORE_DIFFERENCE)};
    }

    protected int[] generateWorstMoveRef( int maxDepth, int level, int currentPlayer, GameBoard lastBoard, int alpha, int beta ) {
        // I need to take into account when two moves are equal - i think this is where i need to add in heuristics
        GameBoard testBoard = new GameBoard( lastBoard.getGameBoardAsList() );

        int[] move = { 70, 700 };

        List<Integer> validColumns = testBoard.getColumnsOfValidPlays();

        for (Integer column : validColumns) {
            testBoard.playPieceInColumn( column );

            int[] nextMove = getNextMoveForWorstMove(maxDepth, level, currentPlayer, alpha, beta, testBoard);

            if( nextMove[1] < move[ 1 ] ) {
                move = getLowestScoringMove(move, column, nextMove);
                if( move[ 1 ] <= alpha ) {
                    break;
                }
                beta = Math.min(beta, move[1]);
            }
            testBoard.removePiece( column );
        }

        return move;
    }

    protected int[] getNextMoveForWorstMove(int maxDepth, int level, int currentPlayer, int alpha, int beta, GameBoard testBoard) {
        int[] nextMove = {-4, 444};
        if (isAtMaxDepth(maxDepth, level)) {
            nextMove[1] = testBoard.getScoreDifferenceFromPerspectiveOf(currentPlayer);
        } else {
            nextMove = generateBestMoveRef( maxDepth, level + 1, currentPlayer, testBoard, alpha, beta );
        }
        return nextMove;
    }

    protected int[] getLowestScoringMove(int[] worstMove, int columnToPlay, int[] nextMove) {
        Map<String, Integer> worstMoveMap = new HashMap<String, Integer>();
        worstMoveMap.put(COLUMN, -77);
        worstMoveMap.put(SCORE_DIFFERENCE, -88);

        Map<String, Integer> nextMoveMap = new HashMap<String, Integer>();
        nextMoveMap.put(COLUMN, nextMove[0]);
        nextMoveMap.put(SCORE_DIFFERENCE, nextMove[1]);

        if (nextMoveMap.get(SCORE_DIFFERENCE) < worstMove[1]) {
            worstMoveMap.put(COLUMN, columnToPlay);
            worstMoveMap.put(SCORE_DIFFERENCE, nextMoveMap.get(SCORE_DIFFERENCE));
        }

        return new int[]{worstMoveMap.get(COLUMN), worstMoveMap.get(SCORE_DIFFERENCE)};
    }

    public boolean isAtMaxDepth(int maxDepth, int currentLevel) {
        return maxDepth == currentLevel;
    }
}