package org.spargonaut.maxConnectFour.gameboard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BoardWriter {

    private BufferedWriter outputWriter;

    public BoardWriter() {}

    public BoardWriter(BufferedWriter bufferedWriter) {
        this.outputWriter = bufferedWriter;
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
