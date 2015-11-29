package org.spargonaut.maxConnectFour.gameboard;

import org.spargonaut.maxConnectFour.PlayMode;

import java.util.List;

public class GamePrinter {

    GameBoard gameBoard;

    public GamePrinter(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void printGameBoard() {
        System.out.println("   1 2 3 4 5 6 7   <---  Column numbers\n -----------------");
        List<List<Integer>> playBoard = gameBoard.getGameBoardAsList();
        for( List<Integer> row : playBoard) {
            System.out.print("_| ");
            for(Integer playPiece : row) {
                if( playPiece == 0 ) {
                    System.out.print("  ");
                } else {
                    System.out.print( playPiece + " " );
                }
            }
            System.out.print("|_\n");
        }
        System.out.println(" -----------------\n   1 2 3 4 5 6 7   <---Column numbers");
    }

    public void printInitialGameState(PlayMode playMode) {
        System.out.println("--------------------------------------------------------------------------------" +
                "\n\n" +
                "Max Connect Four Client - " + playMode.getValue() + " Mode\n");

        printGameBoard();
    }

    public void printCurrentPlayersTurn() {
        System.out.println("\nIt is now Player " + gameBoard.getCurrentTurnBasedOnNumberOfPlays() + "'s Turn");
    }

    public void printCurrentGameState() {
        System.out.println("\n...and now the board looks like this: \n");
        printGameBoard();
    }

    public void printFinalGameState() {
        System.out.println("Here is the final game state\n");
        printGameBoard();
    }
}
