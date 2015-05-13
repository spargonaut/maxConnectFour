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
        playMode = PlayMode.getEnum(arguments[0]);
        inputGameFile = arguments[1];
        if(playMode == PlayMode.INTERACTIVE) {
            nextPlayer = PlayerIdentifier.getEnum(arguments[2]);
        } else {
            outputGameFile = arguments[2];
        }
        searchDepth = Integer.parseInt(arguments[3]);
    }

    private void checkForProperNumberOfArgments(String[] args) {
        if( args.length != 4 ) {
            String usageMessage = "Four command-line arguments are needed:\n"
                    + "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
                    + " or:  java [program name] one-move [input_file] [output_file] [depth]\n";
            throw new IllegalArgumentException(usageMessage);
        }
    }

    public PlayMode getPlayMode() {
        return playMode;
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