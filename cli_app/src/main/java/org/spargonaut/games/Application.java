package org.spargonaut.games;

import org.spargonaut.maxConnectFour.PlayMode;
import org.spargonaut.maxConnectFour.gameboard.BoardReader;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.parser.ArgumentParser;
import org.spargonaut.maxConnectFour.players.AiPlayer;
import org.spargonaut.maxConnectFour.players.HumanPlayer;
import org.spargonaut.maxConnectFour.players.Player;
import org.spargonaut.maxConnectFour.referees.InteractiveReferee;
import org.spargonaut.maxConnectFour.referees.OneMoveReferee;
import org.spargonaut.maxConnectFour.referees.Referee;

public class Application {

    public static void main(String[] args) {
        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArguments(args);

        BoardReader boardReader = new BoardReader();
        GameBoard currentGame = boardReader.readGame(argumentParser.getInputGameFile());

        PlayMode playMode = argumentParser.getPlayMode();

        currentGame.printInitialGameState(playMode);

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
        currentGame.printFinalGameState();
        currentGame.printCurrentScores();
    }
}
