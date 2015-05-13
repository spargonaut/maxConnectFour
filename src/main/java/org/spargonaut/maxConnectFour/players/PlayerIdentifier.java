package org.spargonaut.maxConnectFour.players;

public enum PlayerIdentifier {
    HUMAN ("human"),
    COMPUTER ("computer");

    private String value;

    PlayerIdentifier(String value) {
        this.value = value;
    }

    public static PlayerIdentifier getEnum(String inputValue) {
        for (PlayerIdentifier identifier : PlayerIdentifier.values()) {
            if (identifier.value.equalsIgnoreCase(inputValue)) return identifier;
        }
        String errorMessage = "Houston we have a problem!\n" +
                "I can't tell whos turn it is next\n\n" +
                "you're going to have to try again.\n" +
                "next time, please indicate if it is the human's turn next or the computer's turn" +
                "\n\n\n\n";
        throw new IllegalArgumentException(errorMessage);
    }
}