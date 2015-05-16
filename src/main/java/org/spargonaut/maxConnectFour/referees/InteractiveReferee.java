package org.spargonaut.maxConnectFour.referees;

import org.spargonaut.maxConnectFour.PlayMode;
import org.spargonaut.maxConnectFour.gameboard.BoardPrinter;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.gameboard.ScoreKeeper;
import org.spargonaut.maxConnectFour.players.AiPlayer;
import org.spargonaut.maxConnectFour.players.HumanPlayer;
import org.spargonaut.maxConnectFour.players.PlayerIdentifier;

public class InteractiveReferee {

    private final PlayMode playMode = PlayMode.INTERACTIVE;

    private final GameBoard gameboard;
    private final BoardPrinter boardPrinter;
    private HumanPlayer humanPlayer;
    private AiPlayer aiPlayer;
    private PlayerIdentifier nextPlayer = PlayerIdentifier.HUMAN;

    public InteractiveReferee(GameBoard gameBoard, HumanPlayer humanPlayer) {
        this.humanPlayer = humanPlayer;
        this.gameboard = gameBoard;
        boardPrinter = new BoardPrinter();
    }

    public InteractiveReferee(GameBoard gameBoard, HumanPlayer humanPlayer, AiPlayer aiPlayer, PlayerIdentifier nextPlayer) {
        this(gameBoard, humanPlayer);
        this.aiPlayer = aiPlayer;
        this.nextPlayer = nextPlayer;
    }

    public void play() {
        int searchDepthZero = 0;

        printInitialGameState();
        System.out.println("\nIt is now Player " + gameboard.getCurrentTurnBasedOnNumberOfPlays() + "'s Turn");

        while (gameboard.hasPossiblePlays()) {
            System.out.println("\n--------------------------------------------------------------------------------\n");

            int columnToPlay = -1;
            switch (getNextPlayer()) {
                case HUMAN:
                    columnToPlay = humanPlayer.getBestPlay(gameboard);
                    gameboard.playPieceInColumn(columnToPlay);
                    nextPlayer = PlayerIdentifier.COMPUTER;
                    break;

                case COMPUTER:
                    columnToPlay = aiPlayer.getBestPlay(gameboard);
                    gameboard.playPieceInColumn(columnToPlay);
                    nextPlayer = PlayerIdentifier.HUMAN;
                    break;
            }

            printCurrentGameBoardAndScores();
        }

        printTheFinalGameState();
    }

    private void printCurrentGameBoardAndScores() {
        System.out.println("\n...and now the board looks like this: \n");
        printGameState();
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

    protected PlayerIdentifier getNextPlayer() {
        return nextPlayer;
    }
}
