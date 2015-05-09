package org.spargonaut.maxConnectFour.gameboard;

import java.util.List;

public class ScoreKeeper {

    private List<List<Integer>> gameboard;

    public ScoreKeeper(List<List<Integer>> gameboard) {
        this.gameboard = gameboard;
    }

    public int getScoreForPlayer(int playerNumber) {
        int playerScore = 0;
        playerScore += checkHorizontalScores(playerNumber);
        playerScore += checkVerticalScores(playerNumber);
        playerScore += checkBackwardDiagnalScores(playerNumber);
        playerScore += checkForwardDiagnalScores(playerNumber);
        return playerScore;
    }

    private int checkHorizontalScores(int player) {
        int score = 0;
        for( int row = 0; row < 6; row++ ) {
            for( int column = 0; column < 4; column++ ) {
                if( ( gameboard.get(row).get(column) == player ) &&
                        ( gameboard.get(row).get(column + 1) == player ) &&
                        ( gameboard.get(row).get(column + 2) == player ) &&
                        ( gameboard.get(row).get(column + 3) == player ) ) {
                    score++;

                }
            }
        }
        return score;
    }

    private int checkVerticalScores(int player) {
        int score = 0;
        for( int row = 0; row < 3; row++ ) {
            for( int column = 0; column < 7; column++ ) {
                if( ( gameboard.get(row).get(column) == player ) &&
                        ( gameboard.get(row + 1).get(column) == player ) &&
                        ( gameboard.get(row + 2).get(column) == player ) &&
                        ( gameboard.get(row + 3).get(column) == player ) ) {
                    score++;
                }
            }
        }
        return score;
    }

    private int checkBackwardDiagnalScores(int player) {
        int score = 0;
        for( int row = 0; row < 3; row++ ){
            for( int column = 0; column < 4; column++ ) {
                if( (gameboard.get(row).get(column) == player ) &&
                        ( gameboard.get(row + 1).get(column + 1) == player ) &&
                        ( gameboard.get(row + 2).get(column + 2) == player ) &&
                        ( gameboard.get(row + 3).get(column + 3) == player ) ) {
                    score++;
                }
            }
        }
        return score;
    }

    private int checkForwardDiagnalScores(int player) {
        int score = 0;
        for( int row = 0; row < 3; row++ ){
            for( int column = 0; column < 4; column++ ) {
                if( ( gameboard.get(row + 3).get(column) == player ) &&
                        ( gameboard.get(row + 2).get(column + 1) == player ) &&
                        ( gameboard.get(row + 1).get(column + 2) == player ) &&
                        ( gameboard.get(row).get(column + 3) == player ) ) {
                    score++;
                }
            }
        }
        return score;
    }

    public Integer getScoreDifferenceFromPerspectiveOf(int currentPlayer) {
        int otherPlayer = getOtherPlayer(currentPlayer);
        return getScoreForPlayer(currentPlayer) - getScoreForPlayer(otherPlayer);
    }

    private int getOtherPlayer(int currentPlayer) {
        int otherPlayer;
        if( currentPlayer == 1 )
            otherPlayer = 2;
        else
            otherPlayer = 1;
        return otherPlayer;
    }
}
