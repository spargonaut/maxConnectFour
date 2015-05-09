package org.spargonaut.maxConnectFour.gameboard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class BoardPrinter {

    public void printGameBoard(GameBoard gameboard) {
        System.out.println("   1 2 3 4 5 6 7   <---  Column numbers\n -----------------");

        int[][] playBoard = gameboard.getGameBoard();

        for( int i = 0; i < 6; i++ ) {
            System.out.print("_| ");
            for( int j = 0; j < 7; j++ ) {
                if( playBoard[i][j] == 0 ) {
                    System.out.print("  ");
                } else {
                    System.out.print( playBoard[i][j] + " " );
                }
            }
            System.out.print("|_\n");
        }
        System.out.println(" -----------------\n   1 2 3 4 5 6 7   <---Column numbers");
    }

    /**
     * this method prints the GameBoard to an output file to be used for
     * inspection or by another running of the application
     * @param outputFile the path and file name of the file to be written
     */
    public void printGameBoardToFile(String outputFile, GameBoard gameboard)
        throws IOException {

        BufferedWriter output = new BufferedWriter( new FileWriter( outputFile ) );
        int[][] playBoard = gameboard.getGameBoard();

        for( int i = 0; i < 6; i++ ) {
            for( int j = 0; j < 7; j++ ) {
                output.write( playBoard[i][j] + 48 );
            }
            output.write("\n");
        }
        output.close();
    }
}
