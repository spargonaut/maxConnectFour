package org.spargonaut.maxConnectFour.referees;

import org.spargonaut.maxConnectFour.PlayMode;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.gameboard.GamePrinter;
import org.spargonaut.maxConnectFour.gameboard.ScoreKeeper;

public abstract class Referee {
    protected GamePrinter gamePrinter;
    protected GameBoard gameboard;
    private PlayMode playMode;
    protected ScoreKeeper scoreKeeper;

    public Referee(GameBoard gameBoard, PlayMode playMode) {
        this.gamePrinter = new GamePrinter(gameBoard);
        this.gameboard = gameBoard;
        this.playMode = playMode;
        scoreKeeper = new ScoreKeeper(gameboard.getGameBoardAsList());
    }

    public void printInitialGameState() {
        gamePrinter.printInitialGameState(playMode);
        scoreKeeper.printCurrentScores();
    }

    protected void printGameBoardAndScores() {
        gamePrinter.printGameBoard();
        scoreKeeper.printCurrentScores();
    }

    public abstract void play();
}
