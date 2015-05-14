package org.spargonaut.maxConnectFour;

import org.spargonaut.maxConnectFour.gameboard.BoardPrinter;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.gameboard.ScoreKeeper;

public class Referee {

    private final GameBoard gameboard;
    private PlayMode playMode;

    public Referee(GameBoard gameboard, PlayMode playmode) {
        this.gameboard = gameboard;
        this.playMode = playmode;
    }

    public void printInitialGameState() {
        System.out.println("--------------------------------------------------------------------------------" +
                "\n\n" +
                "Max Connect Four Client - " + playMode + " Mode\n");

        BoardPrinter boardPrinter = new BoardPrinter();
        boardPrinter.printGameBoard(this.gameboard);

        ScoreKeeper scoreKeeper = new ScoreKeeper(gameboard.getGameBoardAsList());
        scoreKeeper.printCurrentScores();
    }
}
