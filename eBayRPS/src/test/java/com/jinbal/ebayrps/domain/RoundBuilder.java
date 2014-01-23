package com.jinbal.ebayrps.domain;

import com.jinbal.ebayrps.domain.players.Player;
import io.generators.core.Builder;
import io.generators.core.FromBuilderGenerator;
import io.generators.core.Generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.jinbal.ebayrps.domain.PredefinedMovePlayerBuilder.playerBuilder;

public class RoundBuilder implements Builder<Round> {
    private List<Player> players = Generators.listFrom(2, new FromBuilderGenerator(playerBuilder()));

    @Override
    public Round build() {
        return new Round(players);
    }

    public static RoundBuilder roundBuilder() {
        return new RoundBuilder();
    }

    public RoundBuilder players(Collection<Player> players) {
        this.players = new ArrayList<>(players);
        return this;
    }

}
