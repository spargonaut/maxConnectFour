package org.spargonaut.maxConnectFour.gameboard;

import org.spargonaut.maxConnectFour.PlayMode;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private int playedPieceCount;
    private List<List<Integer>> playboard;
    private ScoreKeeper scoreKeeper;
    private GamePrinter gamePrinter;

    private int totalColumnCount = 7;
    private int totalRowCount = 6;
    private final int MAX_NUMBER_OF_PLAYS = totalColumnCount * totalRowCount;

    public GameBoard() {
        playedPieceCount = 0;
        playboard = new ArrayList<>();
        for (int row = 0; row < totalRowCount; row++) {
            List<Integer> newRow = new ArrayList<>();
            for(int column = 0; column < totalColumnCount; column++) {
                newRow.add(0);
            }
            playboard.add(newRow);
        }
        scoreKeeper = new ScoreKeeper(playboard);
        gamePrinter = new GamePrinter(this);
    }

    public GameBoard(List<List<Integer>> startingGameboard) {
        playedPieceCount = 0;
        playboard = new ArrayList<>();
        for(List<Integer> row : startingGameboard) {
            List<Integer> newRow = new ArrayList<>();
            for(Integer playPiece : row) {
                newRow.add(playPiece);
                if(playPiece > 0) {
                    playedPieceCount++;
                }
            }
            playboard.add(newRow);
        }
        scoreKeeper = new ScoreKeeper(playboard);
        gamePrinter = new GamePrinter(this);
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

    public boolean isValidPlay(int column) {
        boolean playIsValid = true;
        if (playIsOutOfBounds(column) || columnIsNotFull(column)) {
            playIsValid = false;
        }
        return playIsValid;
    }

    private boolean columnIsNotFull(int column) {
        return playboard.get(0).get(column) > 0;
    }

    private boolean playIsOutOfBounds(int column) {
        return !( column >= 0 && column <= totalColumnCount );
    }

    public List<Integer> getColumnsOfValidPlays() {
        List<Integer> validPlays = new ArrayList<>();
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
                if( playboard.get(row).get(column) == 0 ) {
                    playboard.get(row).set(column, currentPlayer);
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
            if( playboard.get(row).get(column) > 0 ) {
                playboard.get(row).set(column, 0);
                playedPieceCount--;
                break;
            }
        }
    }

    public List<List<Integer>> getGameBoardAsList() {
        return playboard;
    }

    public boolean hasPossiblePlays() {
        return playedPieceCount < MAX_NUMBER_OF_PLAYS;
    }

    public void printCurrentScores() {
        scoreKeeper.printCurrentScores();
    }

    public void printCurrentPlayersTurn() {
        gamePrinter.printCurrentPlayersTurn();
    }

    public void printFinalGameState() {
        gamePrinter.printFinalGameState();
    }

    public void printInitialGameState(PlayMode playMode) {
        gamePrinter.printInitialGameState(playMode);
        printCurrentScores();
        printCurrentPlayersTurn();
    }
}