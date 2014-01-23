package com.jinbal.ebayrps.domain;

import com.jinbal.ebayrps.domain.players.Player;
import org.junit.Test;

import static com.jinbal.ebayrps.domain.PredefinedMovePlayerBuilder.playerBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PlayerTest {


    @Test
    public void shouldIncScore() throws Exception {
        // Given
        Player player = playerBuilder().build();

        // When
        player.incScore();

        // Then
        assertThat(player.getScore(),is(1));
    }
}
