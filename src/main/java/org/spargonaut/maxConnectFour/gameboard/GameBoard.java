package org.spargonaut.maxConnectFour.gameboard;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    private int[][] playBoard;
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

        this.playBoard = new int[6][7];
        this.pieceCount = 0;

        for( int row = 0; row < 6; row++ ) {
            for( int column = 0; column < 7; column++) {
                this.playBoard[ row ][ column ] = masterGame[ row ][ column ];
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
        return this.playBoard;
    }

    public boolean isValidPlay( int column ) {

        if ( !( column >= 0 && column <= 7 ) ) {
            // check the column bounds
            return false;
        } else if( this.playBoard[0][ column ] > 0 ) {
            // check if column is full
            return false;
        } else {
            // column is NOT full and the column is within bounds
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
        // check if the column choice is a valid play
        if( this.isValidPlay( column ) ) {
            //starting at the bottom of the board, place the piece into the first empty spot
            for( int i = 5; i >= 0; i-- ) {
                if( this.playBoard[i][column] == 0 ) {
                    if( this.pieceCount % 2 == 0 ){
                        this.playBoard[i][column] = 1;
                        this.pieceCount++;

                    } else {
                        this.playBoard[i][column] = 2;
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
        int otherPlayer = getOtherPlayer(currentPlayer);
        return this.getScore( currentPlayer ) - this.getScore(otherPlayer);
    }

    private int getOtherPlayer(int currentPlayer) {
        int otherPlayer;
        if( currentPlayer == 1 )
            otherPlayer = 2;
        else
            otherPlayer = 1;
        return otherPlayer;
    }

    /***************************  solution methods **************************/
    public void removePiece( int column ) {

        // starting looking at the top of the game board,
        // and remove the top piece
        for( int i = 0; i < 6; i++ ) {
            if( this.playBoard[ i ][ column ] > 0 ) {
                this.playBoard[ i ][ column ] = 0;
                this.pieceCount--;

                break;
            }
        }
    }
    /************************  end solution methods **************************/
}
