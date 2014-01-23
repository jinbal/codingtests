package com.jinbal.ebayrps.domain.players;

import com.jinbal.ebayrps.domain.Move;

public abstract class Player {

    private int score = 0;

    public abstract Move nextMove();

    public abstract String getDescription();

    public int getScore() {
        return score;
    }

    public void incScore() {
        this.score++;
    }

}
