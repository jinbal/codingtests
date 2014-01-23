package com.jinbal.ebayrps.domain.players;

import com.jinbal.ebayrps.domain.Move;
import com.jinbal.ebayrps.ui.GameUI;

public class UIPlayer extends Player {

    private final GameUI gameUI;

    public UIPlayer(GameUI gameUI) {
        this.gameUI = gameUI;
    }

    @Override
    public Move nextMove() {
        return new Move(this, gameUI.requestNextMove(this));
    }

    @Override
    public String getDescription() {
        return "Human Player";
    }
}
