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
}
