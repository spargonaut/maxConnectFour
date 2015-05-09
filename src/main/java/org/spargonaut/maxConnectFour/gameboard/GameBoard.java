package org.spargonaut.maxConnectFour.gameboard;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    private int pieceCount;

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

        this.pieceCount = 0;

        for( int row = 0; row < 6; row++ ) {
            for( int column = 0; column < 7; column++) {
                newPlayboard.get(row).add(column, masterGame[row][column]);

                if(masterGame[row][column] > 0) {
                    this.pieceCount++;
                }
            }
        }

        scoreKeeper = new ScoreKeeper(newPlayboard);
    }

    public int getNumberOfPlaysRemaining() {
        return MAX_NUMBER_OF_PLAYS - pieceCount;
    }

    public int getScore( int playerNumber ) {
        return scoreKeeper.getScoreForPlayer(playerNumber);
    }

    public int getCurrentTurnBasedOnNumberOfPlays() {
        return ( this.pieceCount % 2 ) + 1 ;
    }

    public int getCountOfPiecesPlayed() {
        return this.pieceCount;
    }

    public int[][] getGameBoard() {
        int[][] playboard = new int[6][7];
        for(int row = 0; row < totalRowCount; row++) {
            for(int column = 0; column < totalColumnCount; column++) {
                playboard[row][column] = newPlayboard.get(row).get(column);
            }
        }
        return playboard;
    }

    public boolean isValidPlay( int column ) {
        if ( !( column >= 0 && column <= 7 ) ) {
            return false;
        } else if( newPlayboard.get(0).get(column) > 0 ) {
            return false;
        } else {
            return true;
        }
    }

    public List<Integer> getColumnsOfValidPlays() {
        List<Integer> validPlays = new ArrayList<Integer>();
        for (int i = 0; i < 7; i++) {
            if (isValidPlay(i)) {
                validPlays.add(i);
            }
        }
        return validPlays;
    }

    public boolean playPieceInColumn( int column ) {

        boolean playMade = false;
        if( this.isValidPlay( column ) ) {
            //starting at the bottom of the board, place the piece into the first empty spot
            for( int row = 5; row >= 0; row-- ) {
                if( newPlayboard.get(row).get(column) == 0 ) {
                    if( this.pieceCount % 2 == 0 ){
                        newPlayboard.get(row).add(column, 1);
                        this.pieceCount++;

                    } else {
                        newPlayboard.get(row).add(column, 2);
                        this.pieceCount++;
                    }

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

    /***************************  solution methods **************************/
    public void removePiece( int column ) {

        // starting looking at the top of the game board,
        // and remove the top piece
        for( int row = 0; row < 6; row++ ) {
            if( newPlayboard.get(row).get(column) > 0 ) {
                newPlayboard.get(row).set(column, 0);
                this.pieceCount--;

                break;
            }
        }
    }
    /************************  end solution methods **************************/
}
