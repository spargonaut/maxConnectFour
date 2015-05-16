package org.spargonaut.maxConnectFour.referees;

import org.spargonaut.maxConnectFour.PlayMode;
import org.spargonaut.maxConnectFour.gameboard.BoardPrinter;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.gameboard.ScoreKeeper;

public abstract class Referee {
    protected BoardPrinter boardPrinter;
    protected GameBoard gameboard;
    private PlayMode playMode;
    protected ScoreKeeper scoreKeeper;

    public Referee(GameBoard gameBoard, PlayMode playMode) {
        this.boardPrinter = new BoardPrinter();
        this.gameboard = gameBoard;
        this.playMode = playMode;
        scoreKeeper = new ScoreKeeper(gameboard.getGameBoardAsList());
    }

    public void printInitialGameState() {
        System.out.println("--------------------------------------------------------------------------------" +
                "\n\n" +
                "Max Connect Four Client - " + playMode.getValue() + " Mode\n");

        boardPrinter.printGameBoard(gameboard);

        ScoreKeeper scoreKeeper = new ScoreKeeper(gameboard.getGameBoardAsList());
        scoreKeeper.printCurrentScores();
    }

    public void printGameBoardAndScores() {
        boardPrinter.printGameBoard(gameboard);
        scoreKeeper.printCurrentScores();
    }
}
