package org.spargonaut.maxConnectFour.gameboard;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameBoardTest {

    @Test
    public void shouldRemoveTheSpecifiedPieceFromTheBoard() throws Exception {
        int[][] masterGame = {
                {1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
        };
        GameBoard gameboard = new GameBoard(masterGame);

        gameboard.removePiece(0);

        int [][] expectedMasterGame = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
        };
        int[][] actualMasterGame = gameboard.getGameBoard();

        assertArrayEquals(expectedMasterGame, actualMasterGame);
    }
}