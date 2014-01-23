package com.jinbal.ebayrps.domain.players;

import io.generators.core.Builder;

public class ComputerPlayerBuilder implements Builder<ComputerPlayer> {

    public static ComputerPlayerBuilder computerPlayerBuilder() {
        return new ComputerPlayerBuilder();
    }

    @Override
    public ComputerPlayer build() {
        return new ComputerPlayer();
    }
}
