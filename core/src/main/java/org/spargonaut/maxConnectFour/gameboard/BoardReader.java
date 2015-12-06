package org.spargonaut.maxConnectFour.gameboard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BoardReader {

    private BufferedReader bufferedReader;
    private List<List<Integer>> newPlayBoard;

    public BoardReader(List<List<Integer>> newPlayBoard) {
        this.newPlayBoard = newPlayBoard;
    }

    public BoardReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public BoardReader() {};

    public GameBoard readGame(String inputFile) {

        String rowData;
        try {
            if (bufferedReader == null) {
                bufferedReader = new BufferedReader(new FileReader(inputFile));
            }

            newPlayBoard = new ArrayList<List<Integer>>();
            for (int rowIndex = 0; rowIndex < 6; rowIndex++) {
                rowData = bufferedReader.readLine();
                List<Integer> row = new ArrayList<Integer>();
                newPlayBoard.add(row);
                for (int columnIndex = 0; columnIndex < 7; columnIndex++) {
                    markPlayAtPosition(rowData, rowIndex, columnIndex);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new GameBoard(newPlayBoard);
    }

    protected void markPlayAtPosition(String gameData, int row, int column) {
        int pieceToPlay = Integer.parseInt(Character.toString(gameData.charAt(column)));
        this.newPlayBoard.get(row).add(pieceToPlay);
        checkIfMarkIsValid(row, column);
    }

    protected void checkIfMarkIsValid(int row, int column) {
        if( markIsNotValid(row, column) ) {
            String exceptionMessage = "Problems!\n--The piece read from the input file was not a 1, a 2 or a 0";
            throw new RuntimeException(exceptionMessage);
        }
    }

    protected boolean markIsNotValid(int row, int column) {
        return !( ( newPlayBoard.get(row).get(column) == 0 ) ||
               ( newPlayBoard.get(row).get(column) == 1 ) ||
               ( newPlayBoard.get(row).get(column) == 2 ) );
    }

    public List<List<Integer>> getPlayBoardAsList() {
        return newPlayBoard;
    }
}
