package org.spargonaut.maxConnectFour.parser;

import org.spargonaut.maxConnectFour.PlayMode;
import org.spargonaut.maxConnectFour.players.PlayerIdentifier;

public class ArgumentParser {

    private PlayMode playMode;
    private String inputGameFile;
    private int searchDepth;
    private PlayerIdentifier nextPlayer;
    private String outputGameFile;

    public void parseArguments(String[] arguments) {
        checkForProperNumberOfArgments(arguments);
        playMode = parsePlayMode(arguments[0]);
        inputGameFile = arguments[1];
        if(playMode == PlayMode.INTERACTIVE) {
            nextPlayer = getFirstPlayer(arguments);
        } else {
            outputGameFile = arguments[2];
        }
        searchDepth = Integer.parseInt(arguments[3]);
    }

    public PlayMode getPlayMode() {
        return playMode;
    }

    private void checkForProperNumberOfArgments(String[] args) {
        if( args.length != 4 ) {
            System.out.println("Four command-line arguments are needed:\n"
                    + "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
                    + " or:  java [program name] one-move [input_file] [output_file] [depth]\n");

            System.exit(0);
        }
    }

    private PlayMode parsePlayMode(String input) {
        validateGameModeArgument(input);
        PlayMode mode = (input.equalsIgnoreCase( "interactive" )) ? PlayMode.INTERACTIVE : PlayMode.ONE_MOVE;
        return mode;
    }

    private void validateGameModeArgument(String playMode) {
        if (!(playMode.equalsIgnoreCase("interactive") || playMode.equalsIgnoreCase( "one-move" ))) {
            throw new IllegalArgumentException(playMode);
        }
    }

    private PlayerIdentifier getFirstPlayer(String[] args) {
        char firstPlayerParamater = args[2].charAt(0);

        validateFirstPlayerParameter(firstPlayerParamater);
        PlayerIdentifier firstPlayer = null;
        if ("c".equalsIgnoreCase(Character.toString(firstPlayerParamater))) {
            firstPlayer = PlayerIdentifier.COMPUTER;
        } else {
            firstPlayer = PlayerIdentifier.HUMAN;
        }
        return firstPlayer;
    }

    private void validateFirstPlayerParameter(char nextTurn) {
        if( !( nextTurn == 'c' || nextTurn == 'C' || nextTurn == 'h' || nextTurn == 'H' ) ) {
            // I don't understand whos turn it is next.
            System.out.println( "!!!!!!!--------->     Houston we have a problem.\n" +
                    "I can't tell whos turn it is next\n\n" +
                    "you're going to have to try again.\n" +
                    "next time, please indicate if it is the human's turn next or the computer's turn\n\n\n" );
            System.exit(0);
        }
    }

    public String getInputGameFile() {
        return inputGameFile;
    }


    public int getSearchDepth() {
        return searchDepth;
    }

    public PlayerIdentifier getNextPlayer() {
        return nextPlayer;
    }

    public String getOutputGameFile() {
        return outputGameFile;
    }
}
