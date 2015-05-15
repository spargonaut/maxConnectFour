package org.spargonaut.maxConnectFour;

import org.spargonaut.maxConnectFour.gameboard.BoardPrinter;
import org.spargonaut.maxConnectFour.gameboard.BoardReader;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.gameboard.ScoreKeeper;
import org.spargonaut.maxConnectFour.parser.ArgumentParser;
import org.spargonaut.maxConnectFour.players.AiPlayer;
import org.spargonaut.maxConnectFour.players.HumanPlayer;
import org.spargonaut.maxConnectFour.players.Player;
import org.spargonaut.maxConnectFour.players.PlayerIdentifier;

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
        Player calculon = new AiPlayer();
        Player human = new HumanPlayer();

        //  variables to keep up with the game
        int playColumn = 99;				//  the players choice of column to play

        switch(playMode) {
        case INTERACTIVE:
            PlayerIdentifier nextTurnEnum = argumentParser.getNextPlayer();

            printInitialGameBoardState(currentGame);

            System.out.println( "\nIt is now Player " + currentGame.getCurrentTurnBasedOnNumberOfPlays() + "'s Turn" );

            while ( currentGame.getCountOfPiecesPlayed() < 42 ) {
                System.out.println("\n--------------------------------------------------------------------------------\n");
                switch ( nextTurnEnum ) {
                case HUMAN:
                    playColumn = human.getBestPlay(currentGame, depthLevel);
                    currentGame.playPieceInColumn(playColumn);
                    nextTurnEnum = PlayerIdentifier.COMPUTER;
                    break;

                case COMPUTER:
                    System.out.println( "\n\n I am playing as player: " + currentGame.getCurrentTurnBasedOnNumberOfPlays() + "\n  searching for the best play to depth level: " + depthLevel );

                    // AI play - solution play
                    playColumn = calculon.getBestPlay( currentGame, depthLevel );

                    System.out.println("  and I'm playing in column " + playColumn);

                    //play the piece
                    currentGame.playPieceInColumn( playColumn );

                    nextTurnEnum = PlayerIdentifier.HUMAN;
                    break;

                //I don't know who's turn it is.  we shouldn't get here.
                default:
                    System.out.println( "what the heck am i doing here?\n I lost track of whose turn it is.\n uh oh" );
                    System.exit(0);
                }

                printCurrentGameBoardAndScores(currentGame);

                //reset playColumn and playMade
                playColumn = 99;

            } // end while

            // the game board is full.
            printTheFinalGameState(currentGame);
            break;

        case ONE_MOVE:
            AiPlayer aiPlayer = new AiPlayer(argumentParser.getSearchDepth());
            Referee referee = new Referee(currentGame, PlayMode.ONE_MOVE, aiPlayer);
            referee.printInitialGameState();
            referee.play(argumentParser.getSearchDepth());
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