package org.spargonaut.maxConnectFour;

import org.spargonaut.maxConnectFour.gameboard.BoardReader;
import org.spargonaut.maxConnectFour.gameboard.GameBoard;
import org.spargonaut.maxConnectFour.parser.ArgumentParser;
import org.spargonaut.maxConnectFour.players.AiPlayer;
import org.spargonaut.maxConnectFour.players.HumanPlayer;
import org.spargonaut.maxConnectFour.players.Player;
import org.spargonaut.maxConnectFour.referees.InteractiveReferee;
import org.spargonaut.maxConnectFour.referees.OneMoveReferee;

import java.io.IOException;

public class MaxConnectFour {

    public static void main(String[] args) {
        MaxConnectFour game = new MaxConnectFour();
        try {
            game.play(args);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void play(String[] args) throws IOException {

        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parseArguments(args);

        BoardReader boardReader = new BoardReader();
        GameBoard currentGame = boardReader.readGame(argumentParser.getInputGameFile());

        Player calculon = new AiPlayer(argumentParser.getSearchDepth());
        Player human = new HumanPlayer();

        switch(argumentParser.getPlayMode()) {
        case INTERACTIVE:
            InteractiveReferee referee = new InteractiveReferee(currentGame, human, calculon, argumentParser.getNextPlayer());
            referee.play();
            break;

        case ONE_MOVE:
            OneMoveReferee oneMoveReferee = new OneMoveReferee(currentGame, calculon);
            oneMoveReferee.play();
            break;
        }
    }
}