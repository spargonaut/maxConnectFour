package org.spargonaut.maxConnectFour.players;

import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.Assert.*;

import org.spargonaut.maxConnectFour.gameboard.GameBoard;

public class HumanPlayerTest {

    @Test
    public void shouldReturnAValidColumnToPlayIn() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("1");

        GameBoard gameBoard = mock(GameBoard.class);
        when(gameBoard.isValidPlay(0)).thenReturn(true);

        HumanPlayer humanPlayer = new HumanPlayer(bufferedReader);
        Integer actualValidPlay = humanPlayer.getBestPlay(gameBoard, 2);

        Integer expectedValidPlay = new Integer(0);
        assertThat(actualValidPlay.intValue(), equalTo(expectedValidPlay.intValue()));
    }

}
