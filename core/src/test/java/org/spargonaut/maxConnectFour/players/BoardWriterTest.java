package org.spargonaut.maxConnectFour.players;

import org.junit.Test;
import org.spargonaut.maxConnectFour.gameboard.BoardWriter;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class BoardWriterTest {

    @Test
    public void shouldPrintTheGameBoardToAFile() throws IOException {
        List<List<Integer>> setUpGameBoard = new ArrayList<List<Integer>>();
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(createBlankRow());
        setUpGameBoard.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1));

        GameBoard gameBoard = new GameBoard(setUpGameBoard);

        BufferedWriter bufferedWriter = mock(BufferedWriter.class);
        BoardWriter boardPrinter = new BoardWriter(bufferedWriter);

        boardPrinter.printGameBoardToFile("someFileName", gameBoard);

        verify(bufferedWriter, times(6)).write("\n");
        verify(bufferedWriter, times(41)).write(48);
        verify(bufferedWriter, times(1)).write(49);
    }

    private List<Integer> createBlankRow() {
        return Arrays.asList(0, 0, 0, 0, 0, 0, 0);
    }
}
