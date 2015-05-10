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

    public void printGameBoardToFile(String outputFile, GameBoard gameboard)
        throws IOException {

        if (outputWriter == null) {
            outputWriter = new BufferedWriter( new FileWriter( outputFile ) );
        }

        List<List<Integer>> playBoard = gameboard.getGameBoardAsList();

        for(List<Integer> row : playBoard) {
            for(Integer playPiece : row) {
                outputWriter.write(playPiece + 48);
            }
            outputWriter.write("\n");
        }
        outputWriter.close();
    }
}
