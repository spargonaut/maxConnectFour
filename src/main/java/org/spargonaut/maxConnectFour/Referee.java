package org.spargonaut.maxConnectFour;

import org.spargonaut.maxConnectFour.gameboard.BoardPrinter;
import org.spargonaut.maxConnectFour.gameboard.BoardWriter;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.gameboard.ScoreKeeper;

import java.io.IOException;

public class Referee {

    private GameBoard gameboard;
    private BoardWriter boardWriter;
    private PlayMode playMode;
    private BoardPrinter boardPrinter;
    private ScoreKeeper scoreKeeper;

    public Referee(GameBoard gameboard, PlayMode playmode) {
        this.gameboard = gameboard;
        this.playMode = playmode;

        boardPrinter = new BoardPrinter();
        scoreKeeper = new ScoreKeeper(gameboard.getGameBoardAsList());
    }

    public Referee(GameBoard gameBoard, PlayMode oneMove, BoardWriter boardWriter) {
        this(gameBoard, oneMove);
        this.boardWriter = boardWriter;
    }

    protected void printInitialGameState() {
        System.out.println("--------------------------------------------------------------------------------" +
                "\n\n" +
                "Max Connect Four Client - " + playMode + " Mode\n");

        boardPrinter.printGameBoard(gameboard);

        ScoreKeeper scoreKeeper = new ScoreKeeper(gameboard.getGameBoardAsList());
        scoreKeeper.printCurrentScores();
    }

    public void printGameBoardAndScores() {
        boardPrinter.printGameBoard(gameboard);
        scoreKeeper.printCurrentScores();
    }

    public void saveGameState(String fileName) throws IOException {
        boardWriter.printGameBoardToFile(fileName, gameboard);
    }
}
