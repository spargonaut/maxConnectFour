package org.spargonaut.maxConnectFour.players;

import org.spargonaut.maxConnectFour.gameboard.GameBoard;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HumanPlayer implements Player {

    BufferedReader br = null;

    public HumanPlayer() {
        this.br = new BufferedReader( new InputStreamReader( System.in ) );
    }

    public HumanPlayer(BufferedReader bufferedReader) {
        this.br = bufferedReader;
    }

    @Override
    public Integer getBestPlay(GameBoard currentGame, int depthLevel) {
        String userInput = null;
        Character userChar = 'x';
        int playColumn = -1;
        boolean validPlayFound = false;
        do {

            System.out.print("\nChoose a Column ( 1 - 7 ) -->:");
            try {
                userInput = br.readLine();
            } catch (Exception e) {
            }

            while( ( userInput.equals("") ) ) {
                System.out.println("\nI didn't understand that input\n--Try again" );
                System.out.print("\nChoose a Column ( 1 - 7 ) -->:");
                try {
                    userInput = br.readLine();
                } catch (Exception e) {
                }
            }

            userChar = userInput.charAt( 0 );

            if( Character.isDigit( userChar )) {
                playColumn = Character.getNumericValue( userChar ) - 1;

                if( currentGame.isValidPlay( playColumn ) ) {
                    validPlayFound = true;
                } else {
                    System.out.println("That was an invalid play\n--Try again!");
                }


            } else {
                System.out.println("\nThat was not a valid digit.\n--Try again");
            } // end digit check
        } while ( !validPlayFound );

        return playColumn;
    }
}