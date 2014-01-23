package com.jinbal.ebayrps.domain;

import com.jinbal.ebayrps.domain.players.Player;
import io.generators.core.Builder;
import io.generators.core.FromBuilderGenerator;
import io.generators.core.Generators;

import java.util.List;

import static com.jinbal.ebayrps.domain.PredefinedMovePlayerBuilder.playerBuilder;
import static java.util.Arrays.asList;

public class GameBuilder implements Builder<Game> {

    private List<Player> players = Generators.listFrom(2, new FromBuilderGenerator<>(playerBuilder()));
    private int numberOfRounds = Generators.positiveInts(1, 5).next();


    public static GameBuilder gameBuilder() {
        return new GameBuilder();
    }

    @Override
    public Game build() {
        return new Game(players, numberOfRounds);
    }

    public GameBuilder players(List<Player> players) {
        this.players = players;
        return this;
    }

    public GameBuilder players(Player... players) {
        this.players = asList(players);
        return this;
    }

    public GameBuilder numberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
        return this;
    }

}
