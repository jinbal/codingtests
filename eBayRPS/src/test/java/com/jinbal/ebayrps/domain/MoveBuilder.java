package com.jinbal.ebayrps.domain;

import com.jinbal.ebayrps.domain.players.Player;
import io.generators.core.Builder;
import io.generators.core.Generators;

import static com.jinbal.ebayrps.domain.PredefinedMovePlayerBuilder.playerBuilder;

public class MoveBuilder implements Builder<Move> {

    private Player player = playerBuilder().build();
    private Weapon weapon = Generators.randomEnum(Weapon.class).next();


    public static MoveBuilder moveBuilder() {
        return new MoveBuilder();
    }

    @Override
    public Move build() {
        return new Move(player, weapon);
    }

    public MoveBuilder player(Player player) {
        this.player = player;
        return this;
    }

    public MoveBuilder weapon(Weapon weapon) {
        this.weapon = weapon;
        return this;
    }

}
