package org.spargonaut.maxConnectFour.gameboard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BoardReader {

    private int[][] playBoard;
    private BufferedReader bufferedReader;
    private List<List<Integer>> newPlayBoard;

    public BoardReader(int[][] playBoard) {
        this.playBoard = playBoard;
    }

    public BoardReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public BoardReader() {};

    public GameBoard readGame(String inputFile)
        throws IOException {

        this.playBoard = new int[6][7];
        String rowData = null;
        if (bufferedReader == null) {
            bufferedReader = new BufferedReader(new FileReader(inputFile));
        }

        newPlayBoard = new ArrayList<List<Integer>>();
        for(int rowIndex = 0; rowIndex < 6; rowIndex++) {
            rowData = bufferedReader.readLine();
            List<Integer> row = new ArrayList<Integer>();
            newPlayBoard.add(row);
            for( int columnIndex = 0; columnIndex < 7; columnIndex++ ) {
                markPlayAtPosition(rowData, rowIndex, columnIndex);
            }
        }

        bufferedReader.close();
        return new GameBoard(newPlayBoard);
    }

    protected void markPlayAtPosition(String gameData, int row, int column) {
        this.playBoard[ row ][ column ] = Integer.parseInt(Character.toString(gameData.charAt(column)));
        this.newPlayBoard.get(row).add(Integer.parseInt(Character.toString(gameData.charAt(column))));
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

    public int[][] getPlayBoard() {
        return playBoard;
    }
}
