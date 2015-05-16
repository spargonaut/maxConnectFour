package org.spargonaut.maxConnectFour.referees;

import org.spargonaut.maxConnectFour.PlayMode;
import org.spargonaut.maxConnectFour.gameboard.BoardWriter;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.players.AiPlayer;

import java.io.IOException;

public class OneMoveReferee extends Referee {

    private BoardWriter boardWriter;
    private AiPlayer aiPlayer;

    public OneMoveReferee(GameBoard gameboard) {
        super(gameboard, PlayMode.ONE_MOVE);
    }

    public OneMoveReferee(GameBoard gameboard, AiPlayer aiPlayer) {
        this(gameboard);
        this.aiPlayer = aiPlayer;
    }

    public OneMoveReferee(GameBoard gameBoard, BoardWriter boardWriter) {
        this(gameBoard);
        this.boardWriter = boardWriter;
    }

    public void saveGameState(String fileName) throws IOException {
        boardWriter.printGameBoardToFile(fileName, gameboard);
    }

    public void play() {
        printInitialGameState();
        if (gameboard.hasPossiblePlays()) {
            System.out.println( "\n\n I am playing as player: " + gameboard.getCurrentTurnBasedOnNumberOfPlays() + "\n  searching for the best play to depth level: " + aiPlayer.getSearchDepth() );
            int bestPlay = aiPlayer.getBestPlay(gameboard);
            gameboard.playPieceInColumn(bestPlay);
        } else {
            System.out.println("The Board is Full\n\nGame Over");
        }
        printGameBoardAndScores();
    }
}
