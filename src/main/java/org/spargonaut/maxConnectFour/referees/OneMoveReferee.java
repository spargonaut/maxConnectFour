package org.spargonaut.maxConnectFour.referees;

import org.spargonaut.maxConnectFour.gameboard.BoardWriter;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.players.AiPlayer;
import org.spargonaut.maxConnectFour.players.Player;

import java.io.IOException;

public class OneMoveReferee extends Referee {

    private BoardWriter boardWriter;
    private Player aiPlayer;
    private String fileToSave;

    public OneMoveReferee(GameBoard gameboard, Player aiPlayer, String fileToSave) {
        super(gameboard);
        this.aiPlayer = aiPlayer;
        this.fileToSave = fileToSave;
        boardWriter = new BoardWriter();
    }

    public OneMoveReferee(GameBoard gameBoard, AiPlayer aiPlayer, BoardWriter boardWriter, String fileToSave) {
        this(gameBoard, aiPlayer, fileToSave);
        this.boardWriter = boardWriter;
    }

    private void saveGameState() {
        try {
            boardWriter.printGameBoardToFile(fileToSave, gameboard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (gameboard.hasPossiblePlays()) {
            System.out.println( "\n\n I am playing as player: " + gameboard.getCurrentTurnBasedOnNumberOfPlays() + "\n  searching for the best play to depth level: " + aiPlayer.getSearchDepth() );
            int bestPlay = aiPlayer.getBestPlay(gameboard);
            gameboard.playPieceInColumn(bestPlay);
        } else {
            System.out.println("The Board is Full\n\nGame Over");
        }
        printGameBoardAndScores();
        saveGameState();
    }
}