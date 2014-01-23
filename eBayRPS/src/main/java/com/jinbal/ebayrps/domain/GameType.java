package com.jinbal.ebayrps.domain;

public enum GameType {
    PLAYER_COMPUTER("Player v Computer"),
    COMPUTER_COMPUTER("Computer V Computer");

    private final String description;

    GameType(String s) {
        this.description = s;
    }

    public String getDescription() {
        return description;
    }
}
