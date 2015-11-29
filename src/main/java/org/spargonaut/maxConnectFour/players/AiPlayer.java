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
        Map<String, Integer> bestPlayMap = new HashMap<String, Integer>();
        bestPlayMap.put(ALPHA, -999);
        bestPlayMap.put(BETA, 99999);
        int[] bestPlay = generateBestMoveRef(searchDepth, 1, currentTurn, currentGame, bestPlayMap.get(ALPHA), bestPlayMap.get(BETA));
        System.out.println("  and I'm playing in column " + (bestPlay[0] + 1));
        return bestPlay[0];
    }

    protected int[] generateBestMoveRef( int maxDepth, int level, int currentPlayer, GameBoard lastBoard, int alpha, int beta ) {
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

        return new int[]{play.getColumn(), play.getScoreDifference()};
    }

    protected Play getNextMoveForBestMove(int maxDepth, int level, int currentPlayer, int alpha, int beta, GameBoard testBoard) {
        Play nextPlay;
        if (isAtMaxDepth(maxDepth, level)) {
            nextPlay = new Play.PlayBuilder().column(-456)
                    .scoreDifference(testBoard.getScoreDifferenceFromPerspectiveOf(currentPlayer))
                    .build();
        } else {
            int nextMove[] = generateWorstMoveRef( maxDepth, level + 1, currentPlayer, testBoard, alpha, beta );
            nextPlay = new Play.PlayBuilder()
                    .column(nextMove[0])
                    .scoreDifference(nextMove[1])
                    .build();
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

    protected int[] generateWorstMoveRef( int maxDepth, int level, int currentPlayer, GameBoard lastBoard, int alpha, int beta ) {
        // I need to take into account when two moves are equal - i think this is where i need to add in heuristics
        GameBoard testBoard = new GameBoard( lastBoard.getGameBoardAsList() );

        Play currentWorstPlay = new Play.PlayBuilder().column(70).scoreDifference(700).build();

        List<Integer> validColumns = testBoard.getColumnsOfValidPlays();

        for (Integer column : validColumns) {
            testBoard.playPieceInColumn( column );

            int[] nextMove = getNextMoveForWorstMove(maxDepth, level, currentPlayer, alpha, beta, testBoard);
            Play nextPlay = new Play.PlayBuilder()
                    .column(nextMove[0])
                    .scoreDifference(nextMove[1])
                    .build();

            if( nextPlay.getScoreDifference() < currentWorstPlay.getScoreDifference() ) {
                int currentWorstMove[] = getLowestScoringMove(new int[]{currentWorstPlay.getColumn(), currentWorstPlay.getScoreDifference()}, column, nextMove);
                currentWorstPlay.setColumn(currentWorstMove[0]);
                currentWorstPlay.setScoreDifference(currentWorstMove[1]);
                if( currentWorstPlay.getScoreDifference() <= alpha ) {
                    break;
                }
                beta = Math.min(beta, currentWorstPlay.getScoreDifference());
            }
            testBoard.removePiece( column );
        }

        return new int[]{currentWorstPlay.getColumn(), currentWorstPlay.getScoreDifference()};
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
        return maxDepth <= currentLevel;
    }

    public int getSearchDepth() {
        return this.searchDepth;
    }
}