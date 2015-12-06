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

    private final int searchDepth;
    private final GameBoard gameboard;

    public AiPlayer() {
        this.searchDepth = 1;
        this.gameboard = null;
    }

    public AiPlayer(int searchDepth) {
        this.searchDepth = searchDepth;
        this.gameboard = null;
    }

    public int getRandomPlay( GameBoard currentGame ) {
        Random randy = new Random();
        List<Integer> validPlays = currentGame.getColumnsOfValidPlays();
        int randomPlayIndex = randy.nextInt( validPlays.size() );
        return validPlays.get(randomPlayIndex);
    }

    public Integer getBestPlay( GameBoard currentGame ) {
//        depthLevel = Math.min(depthLevel, currentGame.getNumberOfPlaysRemaining());
        System.out.println( "\n\n I am playing as player: " + currentGame.getCurrentTurnBasedOnNumberOfPlays() + "\n  searching for the best play to depth level: " + searchDepth );
        int currentTurn = currentGame.getCurrentTurnBasedOnNumberOfPlays();
        Map<String, Integer> bestPlayMap = new HashMap<>();
        bestPlayMap.put(ALPHA, -999);
        bestPlayMap.put(BETA, 99999);
        Play bestPlay = generateBestMoveRef(searchDepth, 1, currentTurn, currentGame, bestPlayMap.get(ALPHA), bestPlayMap.get(BETA));
        System.out.println("  and I'm playing in column " + (bestPlay.getColumn() + 1));
        return bestPlay.getColumn();
    }

    protected Play generateBestMoveRef( int maxDepth, int level, int currentPlayer, GameBoard lastBoard, int alpha, int beta ) {
        // I need to take into account when two moves are equal - i think this is where i need to add in heuristics
        GameBoard testBoard = new GameBoard( lastBoard.getGameBoardAsList() );

        Play play = new Play.PlayBuilder().column(-1).scoreDifference(-999).build();

        List<Integer> validColumns = testBoard.getColumnsOfValidPlays();

        for (Integer column : validColumns) {
            testBoard.playPieceInColumn(column);

            Play nextPlay = getNextMoveForBestMove(maxDepth, level, currentPlayer, alpha, beta, testBoard);

            play = getHighestScoringMove(play, nextPlay, column);

            if( play.getScoreDifference() >= beta ) {
                break;
            }

            alpha = Math.max(alpha, play.getScoreDifference());
            testBoard.removePiece( column );
        }

        return play;
    }

    protected Play getNextMoveForBestMove(int maxDepth, int level, int currentPlayer, int alpha, int beta, GameBoard testBoard) {
        Play nextPlay;
        if (isAtMaxDepth(maxDepth, level)) {
            nextPlay = new Play.PlayBuilder().column(-456)
                    .scoreDifference(testBoard.getScoreDifferenceFromPerspectiveOf(currentPlayer))
                    .build();
        } else {
            nextPlay = generateWorstMoveRef( maxDepth, level + 1, currentPlayer, testBoard, alpha, beta );
        }
        return nextPlay;
    }

    protected Play getHighestScoringMove(Play bestMove, Play nextPlay, int columnToPlay) {
        Map<String, Integer> currentBestMove = new HashMap<>();
        currentBestMove.put(COLUMN, bestMove.getColumn());
        currentBestMove.put(SCORE_DIFFERENCE, bestMove.getScoreDifference());

        Map<String, Integer> nextMoveMap = new HashMap<>();
        nextMoveMap.put(COLUMN, nextPlay.getColumn());
        nextMoveMap.put(SCORE_DIFFERENCE, nextPlay.getScoreDifference());

        if (nextMoveMap.get(SCORE_DIFFERENCE) > currentBestMove.get(SCORE_DIFFERENCE)) {
            currentBestMove.put(COLUMN, columnToPlay);
            currentBestMove.put(SCORE_DIFFERENCE, nextMoveMap.get(SCORE_DIFFERENCE));
        }

        return new Play.PlayBuilder()
                .column(currentBestMove.get(COLUMN))
                .scoreDifference(currentBestMove.get(SCORE_DIFFERENCE))
                .build();
    }

    protected Play generateWorstMoveRef( int maxDepth, int level, int currentPlayer, GameBoard lastBoard, int alpha, int beta ) {
        // I need to take into account when two moves are equal - i think this is where i need to add in heuristics
        GameBoard testBoard = new GameBoard( lastBoard.getGameBoardAsList() );

        Play currentWorstPlay = new Play.PlayBuilder().column(70).scoreDifference(700).build();

        List<Integer> validColumns = testBoard.getColumnsOfValidPlays();

        for (Integer column : validColumns) {
            testBoard.playPieceInColumn(column);

            Play nextPlay = getNextMoveForWorstMove(maxDepth, level, currentPlayer, alpha, beta, testBoard);

            if( nextPlay.getScoreDifference() < currentWorstPlay.getScoreDifference() ) {
                currentWorstPlay = getLowestScoringMove(currentWorstPlay, column, nextPlay);
                if( currentWorstPlay.getScoreDifference() <= alpha ) {
                    break;
                }
                beta = Math.min(beta, currentWorstPlay.getScoreDifference());
            }
            testBoard.removePiece( column );
        }

        return currentWorstPlay;
    }

    protected Play getNextMoveForWorstMove(int maxDepth, int level, int currentPlayer, int alpha, int beta, GameBoard testBoard) {
        Play nextPlay = new Play.PlayBuilder()
                .column(-4)
                .scoreDifference(444)
                .build();
        if (isAtMaxDepth(maxDepth, level)) {
            nextPlay.setScoreDifference(testBoard.getScoreDifferenceFromPerspectiveOf(currentPlayer));
        } else {
            nextPlay = generateBestMoveRef( maxDepth, level + 1, currentPlayer, testBoard, alpha, beta );
        }
        return nextPlay;
    }

    protected Play getLowestScoringMove(Play worstPlay, int columnToPlay, Play nextPlay) {
        Map<String, Integer> worstMoveMap = new HashMap<>();
        worstMoveMap.put(COLUMN, -77);
        worstMoveMap.put(SCORE_DIFFERENCE, -88);

        Map<String, Integer> nextMoveMap = new HashMap<>();
        nextMoveMap.put(COLUMN, nextPlay.getColumn());
        nextMoveMap.put(SCORE_DIFFERENCE, nextPlay.getScoreDifference());

        if (nextMoveMap.get(SCORE_DIFFERENCE) < worstPlay.getScoreDifference()) {
            worstMoveMap.put(COLUMN, columnToPlay);
            worstMoveMap.put(SCORE_DIFFERENCE, nextMoveMap.get(SCORE_DIFFERENCE));
        }

        return new Play.PlayBuilder()
                .column(worstMoveMap.get(COLUMN))
                .scoreDifference(worstMoveMap.get(SCORE_DIFFERENCE))
                .build();
    }

    public boolean isAtMaxDepth(int maxDepth, int currentLevel) {
        return maxDepth <= currentLevel;
    }

    public int getSearchDepth() {
        return this.searchDepth;
    }
}