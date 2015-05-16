package org.spargonaut.maxConnectFour.referees;

import org.spargonaut.maxConnectFour.PlayMode;
import org.spargonaut.maxConnectFour.gameboard.BoardPrinter;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.players.AiPlayer;
import org.spargonaut.maxConnectFour.players.HumanPlayer;
import org.spargonaut.maxConnectFour.players.Player;
import org.spargonaut.maxConnectFour.players.PlayerIdentifier;

public class InteractiveReferee extends Referee {

    private Player humanPlayer;
    private Player aiPlayer;
    private PlayerIdentifier nextPlayer = PlayerIdentifier.HUMAN;

    public InteractiveReferee(GameBoard gameBoard, HumanPlayer humanPlayer) {
        super(gameBoard, PlayMode.INTERACTIVE);
        this.humanPlayer = humanPlayer;
        boardPrinter = new BoardPrinter();
    }

    public InteractiveReferee(GameBoard gameBoard, HumanPlayer humanPlayer, AiPlayer aiPlayer, PlayerIdentifier nextPlayer) {
        this(gameBoard, humanPlayer);
        this.aiPlayer = aiPlayer;
        this.nextPlayer = nextPlayer;
    }

    public void play() {
        printInitialGameState();
        System.out.println("\nIt is now Player " + gameboard.getCurrentTurnBasedOnNumberOfPlays() + "'s Turn");

        while (gameboard.hasPossiblePlays()) {
            System.out.println("\n--------------------------------------------------------------------------------\n");

            switch (getNextPlayer()) {
                case HUMAN:
                    playPieceBy(humanPlayer);
                    nextPlayer = PlayerIdentifier.COMPUTER;
                    break;

                case COMPUTER:
                    playPieceBy(aiPlayer);
                    nextPlayer = PlayerIdentifier.HUMAN;
                    break;
            }

            printCurrentGameBoardAndScores();
        }

        printTheFinalGameState();
    }

    private void playPieceBy(Player player) {
        int columnToPlay = player.getBestPlay(gameboard);
        gameboard.playPieceInColumn(columnToPlay);
    }

    private void printCurrentGameBoardAndScores() {
        System.out.println("\n...and now the board looks like this: \n");
        printGameBoardAndScores();
    }

    private void printTheFinalGameState() {
        System.out.println("Here is the final game state\n");
        printGameBoardAndScores();
    }

    protected PlayerIdentifier getNextPlayer() {
        return nextPlayer;
    }
}