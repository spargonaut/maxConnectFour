package org.spargonaut.maxConnectFour;

public enum PlayMode {
    INTERACTIVE ("Interactive"),
    ONE_MOVE ("One-move");

    private final String value;

    PlayMode(String value) {
        this.value = value;
    }

    public static PlayMode getEnum(String modeString) {
        for (PlayMode playMode : PlayMode.values()) {
            if (playMode.value.equalsIgnoreCase(modeString)) return playMode;
        }
        throw new IllegalArgumentException("Unable to determine PlayMode\n");
    }
}