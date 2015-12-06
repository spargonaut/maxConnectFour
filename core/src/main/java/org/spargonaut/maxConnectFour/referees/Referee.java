package org.spargonaut.maxConnectFour.referees;

import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.gameboard.GamePrinter;
import org.spargonaut.maxConnectFour.gameboard.ScoreKeeper;

public abstract class Referee {
    protected GamePrinter gamePrinter;
    protected GameBoard gameboard;
    protected ScoreKeeper scoreKeeper;

    public Referee(GameBoard gameBoard) {
        this.gamePrinter = new GamePrinter(gameBoard);
        this.gameboard = gameBoard;
        scoreKeeper = new ScoreKeeper(gameboard.getGameBoardAsList());
    }

    public abstract void play();
}
