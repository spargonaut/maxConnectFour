package org.spargonaut.maxConnectFour.referees;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.spargonaut.maxConnectFour.gameboard.BoardWriter;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.players.AiPlayer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class OneMoveRefereeTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

    @Test
    public void shouldPrintTheInitialGameStateToTheScreen() {
        GameBoard gameBoard = createGameBoard();

        AiPlayer mockAiPlayer = mock(AiPlayer.class);
        when(mockAiPlayer.getBestPlay(gameBoard)).thenReturn(1);

        String setupFileName = "some/file/name.txt";
        BoardWriter boardWriter = mock(BoardWriter.class);

        OneMoveReferee oneMoveReferee = new OneMoveReferee(gameBoard, mockAiPlayer, boardWriter, setupFileName);

        String expectedInitialGameBoardMessage = "--------------------------------------------------------------------------------" +
                "\n\n" +
                "Max Connect Four Client - One-move Mode" +
                "\n\n" + createBoardAndScoresString();
        oneMoveReferee.printInitialGameState();

        assertThat(outContent.toString(), is(expectedInitialGameBoardMessage));
    }

    @Test
    public void shouldPrintTheGameBoardStateToTheScreen() {
        GameBoard gameBoard = createGameBoard();

        AiPlayer mockAiPlayer = mock(AiPlayer.class);
        when(mockAiPlayer.getBestPlay(gameBoard)).thenReturn(1);

        String setupFileName = "some/file/name.txt";
        BoardWriter boardWriter = mock(BoardWriter.class);

        OneMoveReferee oneMoveReferee = new OneMoveReferee(gameBoard, mockAiPlayer, boardWriter, setupFileName);

        oneMoveReferee.printGameBoardAndScores();

        String expectedMessage = createBoardAndScoresString();

        assertThat(outContent.toString(), is(expectedMessage));
    }

    @Test
    public void shouldSaveTheGameBoardToAFileAfterTheAiPlayerHasPlayed() throws IOException {
        GameBoard gameBoard = createGameBoard();
        String setupFileName = "some/file/name.txt";

        AiPlayer mockAiPlayer = mock(AiPlayer.class);
        when(mockAiPlayer.getBestPlay(gameBoard)).thenReturn(1);

        BoardWriter boardWriter = mock(BoardWriter.class);
        OneMoveReferee oneMoveReferee = new OneMoveReferee(gameBoard, mockAiPlayer, boardWriter, setupFileName);

        oneMoveReferee.saveGameState(setupFileName);

        verify(boardWriter).printGameBoardToFile(setupFileName, gameBoard);
    }

    @Test
    public void shouldTellTheUserThatTheGameBoardIsFullWhenTheMaxNumberOfPlaysHaveBeenMade() {
        List<List<Integer>> playboard = new ArrayList<>();
        playboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        playboard.add(Arrays.asList(2, 1, 2, 1, 2, 1, 2));
        playboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        playboard.add(Arrays.asList(2, 1, 2, 1, 2, 1, 2));
        playboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        playboard.add(Arrays.asList(2, 1, 2, 1, 2, 1, 2));
        GameBoard gameBoard = new GameBoard(playboard);

        String setupFileName = "some/file/name.txt";
        BoardWriter boardWriter = mock(BoardWriter.class);

        AiPlayer mockAiPlayer = mock(AiPlayer.class);

        OneMoveReferee oneMoveReferee = new OneMoveReferee(gameBoard, mockAiPlayer, boardWriter, setupFileName);
        oneMoveReferee.play();

        String expectedOutput = "The Board is Full\n" +
                "\n" +
                "Game Over\n" +
                "   1 2 3 4 5 6 7   <---  Column numbers\n" +
                " -----------------\n" +
                "_| 1 2 1 2 1 2 1 |_\n" +
                "_| 2 1 2 1 2 1 2 |_\n" +
                "_| 1 2 1 2 1 2 1 |_\n" +
                "_| 2 1 2 1 2 1 2 |_\n" +
                "_| 1 2 1 2 1 2 1 |_\n" +
                "_| 2 1 2 1 2 1 2 |_\n" +
                " -----------------\n" +
                "   1 2 3 4 5 6 7   <---Column numbers\n" +
                "Scores:\n" +
                " Player1: 12\n" +
                " Player2: 12\n\n";

        assertThat(outContent.toString(), endsWith(expectedOutput));
    }

    @Test
    public void shouldGetTheAIPlayerToPlayAPieceAndPrintTheGameBoardAndScoresToTheScreenAndSaveTheGameToAFile() {
        List<List<Integer>> playboard = new ArrayList<>();
        playboard.add(Arrays.asList(2, 1, 2, 1, 2, 1, 0));
        playboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        playboard.add(Arrays.asList(2, 1, 2, 1, 2, 1, 2));
        playboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        playboard.add(Arrays.asList(2, 1, 2, 1, 2, 1, 2));
        playboard.add(Arrays.asList(1, 2, 1, 2, 1, 2, 1));
        GameBoard gameBoard = new GameBoard(playboard);

        int searchDepth = 2;

        String setupFileName = "some/file/name.txt";
        BoardWriter boardWriter = mock(BoardWriter.class);

        AiPlayer mockAiPlayer = mock(AiPlayer.class);
        when(mockAiPlayer.getBestPlay(gameBoard)).thenReturn(6);
        when(mockAiPlayer.getSearchDepth()).thenReturn(searchDepth);

        OneMoveReferee oneMoveReferee = new OneMoveReferee(gameBoard, mockAiPlayer, boardWriter, setupFileName);
        oneMoveReferee.play();

        String expectedOutput = "\n\n" +
                " I am playing as player: 2" +
                "\n" +
                "  searching for the best play to depth level: " +
                searchDepth +
                "\n" +
                "   1 2 3 4 5 6 7   <---  Column numbers\n" +
                " -----------------\n" +
                "_| 2 1 2 1 2 1 2 |_\n" +
                "_| 1 2 1 2 1 2 1 |_\n" +
                "_| 2 1 2 1 2 1 2 |_\n" +
                "_| 1 2 1 2 1 2 1 |_\n" +
                "_| 2 1 2 1 2 1 2 |_\n" +
                "_| 1 2 1 2 1 2 1 |_\n" +
                " -----------------\n" +
                "   1 2 3 4 5 6 7   <---Column numbers\n" +
                "Scores:\n" +
                " Player1: 12\n" +
                " Player2: 12\n\n";

        assertThat(outContent.toString(), endsWith(expectedOutput));
    }

    private GameBoard createGameBoard() {
        List<List<Integer>> playboard = new ArrayList<>();
        playboard.add(Arrays.asList(1, 1, 1, 1, 0, 0, 0));
        playboard.add(createBlankRow());
        playboard.add(createBlankRow());
        playboard.add(createBlankRow());
        playboard.add(createBlankRow());
        playboard.add(createBlankRow());
        return new GameBoard(playboard);
    }

    private String createBoardAndScoresString() {
        return "   1 2 3 4 5 6 7   <---  Column numbers\n" +
                " -----------------\n" +
                "_| 1 1 1 1       |_\n" +
                "_|               |_\n" +
                "_|               |_\n" +
                "_|               |_\n" +
                "_|               |_\n" +
                "_|               |_\n" +
                " -----------------\n" +
                "   1 2 3 4 5 6 7   <---Column numbers\n" +
                "Scores:\n" +
                " Player1: 1\n" +
                " Player2: 0\n\n";
    }

    private List<Integer> createBlankRow() {
        return Arrays.asList(0, 0, 0, 0, 0, 0, 0);
    }
}