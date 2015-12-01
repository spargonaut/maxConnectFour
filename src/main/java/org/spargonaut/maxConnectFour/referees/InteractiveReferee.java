package org.spargonaut.maxConnectFour.referees;

import org.spargonaut.maxConnectFour.PlayMode;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.players.Player;
import org.spargonaut.maxConnectFour.players.PlayerIdentifier;

public class InteractiveReferee extends Referee {

    private Player humanPlayer;
    private Player aiPlayer;
    private PlayerIdentifier nextPlayer = PlayerIdentifier.HUMAN;

    public InteractiveReferee(GameBoard gameBoard, Player humanPlayer) {
        super(gameBoard, PlayMode.INTERACTIVE);
        this.humanPlayer = humanPlayer;
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
            gamePrinter.printMoveSeparator();

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

        gamePrinter.printFinalGameState();
        scoreKeeper.printCurrentScores();
    }

    private void playPieceBy(Player player) {
        int columnToPlay = player.getBestPlay(gameboard);
        gameboard.playPieceInColumn(columnToPlay);
    }

    protected PlayerIdentifier getNextPlayer() {
        return nextPlayer;
    }
}