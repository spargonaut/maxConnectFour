package org.spargonaut.maxConnectFour.referees;

import org.spargonaut.maxConnectFour.PlayMode;
import org.spargonaut.maxConnectFour.gameboard.BoardPrinter;
import org.spargonaut.maxConnectFour.gameboard.BoardWriter;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.gameboard.ScoreKeeper;
import org.spargonaut.maxConnectFour.players.AiPlayer;

import java.io.IOException;

public class OneMoveReferee {

    private GameBoard gameboard;
    private BoardWriter boardWriter;
    private PlayMode playMode;
    private BoardPrinter boardPrinter;
    private ScoreKeeper scoreKeeper;
    private AiPlayer aiPlayer;

    public OneMoveReferee(GameBoard gameboard, PlayMode playmode, AiPlayer aiPlayer) {
        this(gameboard, playmode);
        this.aiPlayer = aiPlayer;
    }

    public OneMoveReferee(GameBoard gameboard, PlayMode playmode) {
        this.gameboard = gameboard;
        this.playMode = playmode;

        boardPrinter = new BoardPrinter();
        scoreKeeper = new ScoreKeeper(gameboard.getGameBoardAsList());
    }

    public OneMoveReferee(GameBoard gameBoard, PlayMode oneMove, BoardWriter boardWriter) {
        this(gameBoard, oneMove);
        this.boardWriter = boardWriter;
    }

    public void printInitialGameState() {
        System.out.println("--------------------------------------------------------------------------------" +
                "\n\n" +
                "Max Connect Four Client - " + playMode + " Mode\n");

        boardPrinter.printGameBoard(gameboard);

        ScoreKeeper scoreKeeper = new ScoreKeeper(gameboard.getGameBoardAsList());
        scoreKeeper.printCurrentScores();
    }

    public void printGameBoardAndScores() {
        boardPrinter.printGameBoard(gameboard);
        scoreKeeper.printCurrentScores();
    }

    public void saveGameState(String fileName) throws IOException {
        boardWriter.printGameBoardToFile(fileName, gameboard);
    }

    public void play(int searchDepth) {
        if (gameboard.getCountOfPiecesPlayed() >= 42) {
            System.out.println("The Board is Full\n\nGame Over");
        } else {
            System.out.println( "\n\n I am playing as player: " + gameboard.getCurrentTurnBasedOnNumberOfPlays() + "\n  searching for the best play to depth level: " + aiPlayer.getSearchDepth() );
            int bestPlay = aiPlayer.getBestPlay(gameboard, searchDepth);
            gameboard.playPieceInColumn(bestPlay);
        }
        printGameBoardAndScores();
    }
}
