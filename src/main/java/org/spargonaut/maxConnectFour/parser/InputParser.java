package org.spargonaut.maxConnectFour.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputParser {

    BufferedReader bufferedReader;

    public InputParser() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public InputParser(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public int getNextPlayFromHuman() {
        boolean playIsInvalid = true;
        int columnToPlay = -1;
        while(playIsInvalid) {
            String userInput = null;
            tellUserToChooseAColumn();
            userInput = readInput(userInput);

            while( ( userInput.equals("") ) ) {
                System.out.println("\nI didn't understand that input");
                tellUserToTryAgain();
                tellUserToChooseAColumn();
                userInput = readInput(userInput);
            }

            Character userChar = userInput.charAt(0);

            if( Character.isDigit( userChar )) {
                columnToPlay = Character.getNumericValue(userChar);
                playIsInvalid = false;
            } else {
                System.out.println("\nThat was not a valid digit.");
                tellUserToTryAgain();
            }
        }
        return columnToPlay;
    }

    private void tellUserToTryAgain() {
        System.out.println("--Try again");
    }

    private void tellUserToChooseAColumn() {
        System.out.print("\nChoose a Column ( 1 - 7 ) -->:");
    }

    private String readInput(String userInput) {
        try {
            userInput = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInput;
    }
}
