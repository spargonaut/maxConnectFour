package org.spargonaut.maxConnectFour.gameboard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class BoardReader {

    private int[][] playBoard;

    public BoardReader(int[][] playBoard) {
        this.playBoard = playBoard;
    }

    public BoardReader() {};

    public GameBoard readGame(String inputFile)
        throws IOException {

        this.playBoard = new int[6][7];
        String rowData = null;
        BufferedReader input = new BufferedReader( new FileReader( inputFile ) );

        for(int row = 0; row < 6; row++) {
            rowData = input.readLine();
            for( int column = 0; column < 7; column++ ) {
                markPlayAtPosition(rowData, row, column);
            }
        }
        input.close();
        return new GameBoard(playBoard);
    }

    private void markPlayAtPosition(String gameData, int row, int column) {
        this.playBoard[ row ][ column ] = Integer.parseInt(Character.toString(gameData.charAt(column)));
        checkIfMarkIsValidOrExit(row, column);
    }

    protected void checkIfMarkIsValidOrExit(int row, int column) {
        if( markIsNotValid(row, column) ) {
            System.out.println("\nProblems!\n--The piece read from the input file was not a 1, a 2 or a 0" );
            System.exit(0);
        }
    }

    protected boolean markIsNotValid(int row, int column) {
        return !( ( this.playBoard[ row ][ column ] == 0 ) ||
               ( this.playBoard[ row ][ column ] == 1 ) ||
               ( this.playBoard[ row ][ column ] == 2 ) );
    }
}
