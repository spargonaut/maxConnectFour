package org.spargonaut.maxConnectFour.players;

import org.spargonaut.maxConnectFour.gameboard.GameBoard;

public class HumanPlayer implements Player {

    private InputParser inputParser;

    public HumanPlayer() {
        inputParser = new InputParser();
    }

    public HumanPlayer(InputParser inputParser) {
        this.inputParser = inputParser;
    }

    @Override
    public Integer getBestPlay(GameBoard currentGame, int depthLevel) {
        int playColumn = -1;
        boolean validPlayFound = false;

        do {
            playColumn = inputParser.getNextPlayFromHuman();
            playColumn--;

            if( currentGame.isValidPlay( playColumn ) ) {
                validPlayFound = true;
            } else {
                System.out.println("That was an invalid play\n--Try again!");
            }
        } while(!validPlayFound);

        return playColumn;
    }
}