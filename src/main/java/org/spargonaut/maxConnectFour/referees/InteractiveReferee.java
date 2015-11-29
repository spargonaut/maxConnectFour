package org.spargonaut.maxConnectFour.referees;

import org.spargonaut.maxConnectFour.PlayMode;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.gameboard.GamePrinter;
import org.spargonaut.maxConnectFour.players.Player;
import org.spargonaut.maxConnectFour.players.PlayerIdentifier;

public class InteractiveReferee extends Referee {

    private Player humanPlayer;
    private Player aiPlayer;
    private PlayerIdentifier nextPlayer = PlayerIdentifier.HUMAN;

    public InteractiveReferee(GameBoard gameBoard, Player humanPlayer) {
        super(gameBoard, PlayMode.INTERACTIVE);
        this.humanPlayer = humanPlayer;
        gamePrinter = new GamePrinter(gameBoard);
    }

    public InteractiveReferee(GameBoard gameBoard, Player humanPlayer, Player aiPlayer, PlayerIdentifier nextPlayer) {
        this(gameBoard, humanPlayer);
        this.aiPlayer = aiPlayer;
        this.nextPlayer = nextPlayer;
    }

    public void play() {
        gamePrinter.printInitialGameState(PlayMode.INTERACTIVE);
        scoreKeeper.printCurrentScores();
        gamePrinter.printCurrentPlayersTurn();

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

            gamePrinter.printCurrentGameState();
            scoreKeeper.printCurrentScores();
        }

        printTheFinalGameState();
    }

    private void playPieceBy(Player player) {
        int columnToPlay = player.getBestPlay(gameboard);
        gameboard.playPieceInColumn(columnToPlay);
    }

    private void printTheFinalGameState() {
        System.out.println("Here is the final game state\n");
        printGameBoardAndScores();
    }

    protected PlayerIdentifier getNextPlayer() {
        return nextPlayer;
    }
}