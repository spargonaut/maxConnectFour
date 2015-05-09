package org.spargonaut.maxConnectFour.gameboard;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    private int playedPieceCount;

    private static final int MAX_NUMBER_OF_PLAYS = 42;

    private List<List<Integer>> newPlayboard;
    private int totalColumnCount = 7;
    private int totalRowCount = 6;
    private ScoreKeeper scoreKeeper;

    public GameBoard( int masterGame[][] ) {

        newPlayboard = new ArrayList(totalColumnCount);
        for (int i = 0; i < totalRowCount; i++) {
            newPlayboard.add(new ArrayList<Integer>(totalRowCount));
        }

        playedPieceCount = 0;

        for( int row = 0; row < totalRowCount; row++ ) {
            for( int column = 0; column < totalColumnCount; column++) {
                newPlayboard.get(row).add(column, masterGame[row][column]);

                if(masterGame[row][column] > 0) {
                    playedPieceCount++;
                }
            }
        }

        scoreKeeper = new ScoreKeeper(newPlayboard);
    }

    public int getNumberOfPlaysRemaining() {
        return MAX_NUMBER_OF_PLAYS - playedPieceCount;
    }

    public int getScore( int playerNumber ) {
        return scoreKeeper.getScoreForPlayer(playerNumber);
    }

    public int getCurrentTurnBasedOnNumberOfPlays() {
        return (playedPieceCount % 2) + 1 ;
    }

    public int getCountOfPiecesPlayed() {
        return playedPieceCount;
    }

    public int[][] getGameBoard() {
        int[][] playboard = new int[totalRowCount][totalColumnCount];
        for(int row = 0; row < totalRowCount; row++) {
            for(int column = 0; column < totalColumnCount; column++) {
                playboard[row][column] = newPlayboard.get(row).get(column);
            }
        }
        return playboard;
    }

    public boolean isValidPlay(int column) {
        boolean playIsValid = true;
        if (playIsOutOfBounds(column) || columnIsNotFull(column)) {
            playIsValid = false;
        }
        return playIsValid;
    }

    private boolean columnIsNotFull(int column) {
        return newPlayboard.get(0).get(column) > 0;
    }

    private boolean playIsOutOfBounds(int column) {
        return !( column >= 0 && column <= totalColumnCount );
    }

    public List<Integer> getColumnsOfValidPlays() {
        List<Integer> validPlays = new ArrayList<Integer>();
        for (int i = 0; i < totalColumnCount; i++) {
            if (isValidPlay(i)) {
                validPlays.add(i);
            }
        }
        return validPlays;
    }

    public boolean playPieceInColumn(int column) {
        int currentPlayer = getCurrentTurnBasedOnNumberOfPlays();
        boolean playMade = false;
        if(isValidPlay(column)) {
            for( int row = 5; row >= 0; row-- ) {
                if( newPlayboard.get(row).get(column) == 0 ) {
                    newPlayboard.get(row).add(column, currentPlayer);
                    playedPieceCount++;
                    playMade = true;
                    break;
                }
            }
        }
        return playMade;
    }

    public Integer getScoreDifferenceFromPerspectiveOf(int currentPlayer) {
        return scoreKeeper.getScoreDifferenceFromPerspectiveOf(currentPlayer);
    }

    public void removePiece( int column ) {
        for( int row = 0; row < totalRowCount; row++ ) {
            if( newPlayboard.get(row).get(column) > 0 ) {
                newPlayboard.get(row).set(column, 0);
                playedPieceCount--;
                break;
            }
        }
    }
}