package org.spargonaut.maxConnectFour.referees;

import org.spargonaut.maxConnectFour.PlayMode;
import org.spargonaut.maxConnectFour.gameboard.BoardPrinter;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.gameboard.ScoreKeeper;
import org.spargonaut.maxConnectFour.players.HumanPlayer;

public class InteractiveReferee {

    private final PlayMode playMode = PlayMode.INTERACTIVE;

    private final GameBoard gameboard;
    private final BoardPrinter boardPrinter;
    private HumanPlayer humanPlayer;

    public InteractiveReferee(GameBoard gameBoard) {
        this.gameboard = gameBoard;

        boardPrinter = new BoardPrinter();
    }

    public InteractiveReferee(GameBoard gameBoard, HumanPlayer humanPlayer) {
        this(gameBoard);
        this.humanPlayer = humanPlayer;
    }

    public void play() {
        int searchDepthZero = 0;

        printInitialGameState();

        if (gameboard.hasPossiblePlays()) {
            System.out.println("\nIt is now Player " + gameboard.getCurrentTurnBasedOnNumberOfPlays() + "'s Turn");

            int columnToPlay = humanPlayer.getBestPlay(gameboard, searchDepthZero);
            gameboard.playPieceInColumn(columnToPlay);
        }

        printTheFinalGameState();
    }

    private void printGameState() {
        boardPrinter.printGameBoard(gameboard);

        ScoreKeeper scoreKeeper = new ScoreKeeper(gameboard.getGameBoardAsList());
        scoreKeeper.printCurrentScores();
    }

    private void printInitialGameState() {
        System.out.println("--------------------------------------------------------------------------------" +
                "\n\n" +
                "Max Connect Four Client - " + playMode + " Mode\n");

        printGameState();
    }

    private void printTheFinalGameState() {
        System.out.println("Here is the final game state\n");
        printGameState();
    }

}
