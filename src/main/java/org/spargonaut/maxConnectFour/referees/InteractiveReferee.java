package org.spargonaut.maxConnectFour.referees;

import org.spargonaut.maxConnectFour.PlayMode;
import org.spargonaut.maxConnectFour.gameboard.BoardPrinter;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.gameboard.ScoreKeeper;

public class InteractiveReferee {

    private final PlayMode playMode = PlayMode.INTERACTIVE;

    private final GameBoard gameboard;
    private final BoardPrinter boardPrinter;

    public InteractiveReferee(GameBoard gameBoard) {
        this.gameboard = gameBoard;

        boardPrinter = new BoardPrinter();
    }

    public void play() {
        printInitialGameState();

    }

    public void printInitialGameState() {
        System.out.println("--------------------------------------------------------------------------------" +
                "\n\n" +
                "Max Connect Four Client - " + playMode + " Mode\n");

        boardPrinter.printGameBoard(gameboard);

        ScoreKeeper scoreKeeper = new ScoreKeeper(gameboard.getGameBoardAsList());
        scoreKeeper.printCurrentScores();
    }

}
