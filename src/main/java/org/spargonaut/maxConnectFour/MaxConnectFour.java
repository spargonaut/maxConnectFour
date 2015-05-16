package org.spargonaut.maxConnectFour;

import org.spargonaut.maxConnectFour.gameboard.BoardReader;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.parser.ArgumentParser;
import org.spargonaut.maxConnectFour.players.AiPlayer;
import org.spargonaut.maxConnectFour.players.HumanPlayer;
import org.spargonaut.maxConnectFour.players.Player;
import org.spargonaut.maxConnectFour.referees.InteractiveReferee;
import org.spargonaut.maxConnectFour.referees.OneMoveReferee;
import org.spargonaut.maxConnectFour.referees.Referee;

public class MaxConnectFour {

    public static void main(String[] args) {
        MaxConnectFour game = new MaxConnectFour();
        game.play(args);
    }

    public void play(String[] args) {

        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArguments(args);

        BoardReader boardReader = new BoardReader();
        GameBoard currentGame = boardReader.readGame(argumentParser.getInputGameFile());

        Player calculon = new AiPlayer(argumentParser.getSearchDepth());
        Player human = new HumanPlayer();

        Referee referee = null;

        switch(argumentParser.getPlayMode()) {
        case INTERACTIVE:
            referee = new InteractiveReferee(currentGame, human, calculon, argumentParser.getNextPlayer());
            break;

        case ONE_MOVE:
            referee = new OneMoveReferee(currentGame, calculon, argumentParser.getOutputGameFile());
            break;
        }

        referee.play();
    }
}