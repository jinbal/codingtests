package com.jinbal.ebayrps.domain.players;

import com.jinbal.ebayrps.domain.Move;
import org.junit.Test;

import static com.jinbal.ebayrps.domain.players.ComputerPlayerBuilder.computerPlayerBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ComputerPlayerTest {
    @Test
    public void shouldNextGenerateRandomMove() throws Exception {
        // Given
        Player computerPlayer = computerPlayerBuilder().build();

        // When
        Move move = computerPlayer.nextMove();

        // Then
        assertThat(move, notNullValue());
        assertThat(move.getWeapon(),notNullValue());
        assertThat(move.getPlayer(),is(computerPlayer));
    }
}
