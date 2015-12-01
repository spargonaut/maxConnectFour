package org.spargonaut.maxConnectFour;

import org.spargonaut.maxConnectFour.gameboard.BoardReader;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.gameboard.GamePrinter;
import org.spargonaut.maxConnectFour.gameboard.ScoreKeeper;
import org.spargonaut.maxConnectFour.parser.ArgumentParser;
import org.spargonaut.maxConnectFour.players.AiPlayer;
import org.spargonaut.maxConnectFour.players.HumanPlayer;
import org.spargonaut.maxConnectFour.players.Player;
import org.spargonaut.maxConnectFour.referees.InteractiveReferee;
import org.spargonaut.maxConnectFour.referees.OneMoveReferee;
import org.spargonaut.maxConnectFour.referees.Referee;

public class MaxConnectFour {

    public static void main(String[] args) {
        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArguments(args);

        BoardReader boardReader = new BoardReader();
        GameBoard currentGame = boardReader.readGame(argumentParser.getInputGameFile());

        GamePrinter gamePrinter = new GamePrinter(currentGame);
        ScoreKeeper scoreKeeper = new ScoreKeeper(currentGame.getGameBoardAsList());

        PlayMode playMode = argumentParser.getPlayMode();

        gamePrinter.printInitialGameState(playMode);
        scoreKeeper.printCurrentScores();

        Player calculon = new AiPlayer(argumentParser.getSearchDepth());
        Player human = new HumanPlayer();

        Referee referee = null;

        switch(playMode) {
            case INTERACTIVE:
                referee = new InteractiveReferee(currentGame, human, calculon, argumentParser.getNextPlayer());
                break;

            case ONE_MOVE:
                referee = new OneMoveReferee(currentGame, calculon, argumentParser.getOutputGameFile());
                break;
        }

        referee.play();
        gamePrinter.printFinalGameState();
        scoreKeeper.printCurrentScores();
    }
}