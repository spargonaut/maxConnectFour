package org.spargonaut.maxConnectFour.gameboard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class BoardPrinter {

    private BufferedWriter outputWriter;

    public BoardPrinter() {}

    public BoardPrinter(BufferedWriter bufferedWriter) {
        outputWriter = bufferedWriter;
    }

    public void printGameBoard(GameBoard gameboard) {
        System.out.println("   1 2 3 4 5 6 7   <---  Column numbers\n -----------------");
        List<List<Integer>> playBoard = gameboard.getGameBoardAsList();
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

    /**
     * this method prints the GameBoard to an output file to be used for
     * inspection or by another running of the application
     * @param outputFile the path and file name of the file to be written
     */
    public void printGameBoardToFile(String outputFile, GameBoard gameboard)
        throws IOException {

        if (outputWriter == null) {
            outputWriter = new BufferedWriter( new FileWriter( outputFile ) );
        }

        int[][] playBoard = gameboard.getGameBoard();

        for( int i = 0; i < 6; i++ ) {
            for( int j = 0; j < 7; j++ ) {
                outputWriter.write(playBoard[i][j] + 48);
            }
            outputWriter.write("\n");
        }
        outputWriter.close();
    }
}
