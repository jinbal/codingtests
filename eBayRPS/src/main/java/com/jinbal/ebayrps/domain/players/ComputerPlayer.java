package com.jinbal.ebayrps.domain.players;

import com.jinbal.ebayrps.domain.Move;
import com.jinbal.ebayrps.domain.Weapon;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ComputerPlayer extends Player {
    private final Random random = ThreadLocalRandom.current();

    @Override
    public Move nextMove() {
        Weapon weapon = Weapon.values()[random.nextInt(Weapon.values().length)];
        return new Move(this, weapon);
    }

    @Override
    public String getDescription() {
        return "Computer Player";
    }
}
