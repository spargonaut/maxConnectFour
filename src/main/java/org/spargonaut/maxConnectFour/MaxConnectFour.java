package org.spargonaut.maxConnectFour;

import org.spargonaut.maxConnectFour.gameboard.BoardPrinter;
import org.spargonaut.maxConnectFour.gameboard.BoardReader;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.gameboard.ScoreKeeper;
import org.spargonaut.maxConnectFour.parser.ArgumentParser;
import org.spargonaut.maxConnectFour.players.AiPlayer;
import org.spargonaut.maxConnectFour.players.HumanPlayer;
import org.spargonaut.maxConnectFour.referees.InteractiveReferee;
import org.spargonaut.maxConnectFour.referees.OneMoveReferee;

import java.io.IOException;

public class MaxConnectFour {

    private PlayMode playMode;
    private String input;
    private int depthLevel;

    private ArgumentParser argumentParser;

    public static void main(String[] args) {
        MaxConnectFour game = new MaxConnectFour();
        try {
            game.play(args);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void play(String[] args) throws IOException {

        parseInputArguments(args);

        BoardReader boardReader = new BoardReader();
        GameBoard currentGame = boardReader.readGame(input);

        // create the Ai Player
        AiPlayer calculon = new AiPlayer(argumentParser.getSearchDepth());
        HumanPlayer human = new HumanPlayer();

        //  variables to keep up with the game
        int playColumn = 99;				//  the players choice of column to play

        switch(playMode) {
        case INTERACTIVE:
            InteractiveReferee referee = new InteractiveReferee(currentGame, human, calculon, argumentParser.getNextPlayer());
            referee.play();
            break;

        case ONE_MOVE:
            AiPlayer aiPlayer = new AiPlayer(argumentParser.getSearchDepth());
            OneMoveReferee oneMoveReferee = new OneMoveReferee(currentGame, PlayMode.ONE_MOVE, aiPlayer);
            oneMoveReferee.printInitialGameState();
            oneMoveReferee.play();
            break;
        }
    }

    protected void printInitialGameBoardState(GameBoard currentGame) {
        System.out.println( "--------------------------------------------------------------------------------");
        System.out.println("\nMax Connect Four Client - " + playMode + " Mode\n");
        printBoardAndScores(currentGame);
    }

    protected void printBoardAndScores(GameBoard currentGame) {
        BoardPrinter boardPrinter = new BoardPrinter();
        boardPrinter.printGameBoard(currentGame);
        ScoreKeeper scoreKeeper = new ScoreKeeper(currentGame.getGameBoardAsList());
        scoreKeeper.printCurrentScores();
    }

    protected void printCurrentGameBoardAndScores(GameBoard currentGame) {
        System.out.println("\n...and now the board looks like this: \n");
        printBoardAndScores(currentGame);
    }

    private void printTheFinalGameState(GameBoard currentGame) {
        System.out.println("Here is the final game state\n");
        printBoardAndScores(currentGame);
    }

    private void parseInputArguments(String[] args) {
        argumentParser = new ArgumentParser();
        argumentParser.parseArguments(args);
        playMode = argumentParser.getPlayMode();
        input = argumentParser.getInputGameFile();
        depthLevel = argumentParser.getSearchDepth();
    }
}