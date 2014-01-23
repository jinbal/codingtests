package com.jinbal.ebayrps.domain;

import com.jinbal.ebayrps.domain.players.Player;
import org.junit.Test;

import static com.jinbal.ebayrps.domain.MoveBuilder.moveBuilder;
import static com.jinbal.ebayrps.domain.RoundBuilder.roundBuilder;
import static com.jinbal.ebayrps.domain.PredefinedMovePlayerBuilder.playerBuilder;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class RoundTest {

    @Test
    public void shouldReturnCorrectNumberOfMoves() throws Exception {
        // Given
        Round round = roundBuilder().build();

        // When
        round.playRound();

        // Then
        assertThat(round.isPlayed(), is(true));
        assertThat(round.getMoves(), hasSize(round.getPlayers().size()));

    }

    @Test
    public void shouldPlayRoundWithCorrectMoves() throws Exception {
        // Given
        Player player1 = playerBuilder().predefinedWeaponList(asList(Weapon.SCISSORS)).build();
        Move player1ExpectedMove = moveBuilder().player(player1).weapon(Weapon.SCISSORS).build();
        Player player2 = playerBuilder().predefinedWeaponList(asList(Weapon.PAPER)).build();
        Move player2ExpectedMove = moveBuilder().player(player2).weapon(Weapon.PAPER).build();

        Round round = roundBuilder().players(asList(player1, player2)).build();

        // When
        round.playRound();

        // Then
        assertThat(round.isPlayed(), is(true));
        assertThat(round.getMoves(), hasSize(round.getPlayers().size()));
        assertThat(round.getMoves(), containsInAnyOrder(player1ExpectedMove, player2ExpectedMove));
    }

    @Test
    public void shouldAwardWinnerCorrectly() throws Exception {
        // Given
        Round round = roundBuilder().build();
        round.playRound();
        Move firstMove = round.getMoves().iterator().next();

        // When
        round.awardRoundTo(firstMove);

        // Then
        assertThat(round.winningMove(), is(firstMove));
        assertThat(round.winner(), is(firstMove.getPlayer()));
    }

    @Test
    public void shouldIncrementScoreForWinningPlayer() throws Exception {
        // Given
        Player player1 = playerBuilder().predefinedWeaponList(asList(Weapon.SCISSORS)).build();
        Player player2 = playerBuilder().predefinedWeaponList(asList(Weapon.PAPER)).build();
        Round round = roundBuilder().players(asList(player1, player2)).build();
        round.playRound();
        Move firstMove = round.getMoves().iterator().next();

        // When
        round.awardRoundTo(firstMove);

        // Then
        assertThat(round.winner(), is(firstMove.getPlayer()));
        assertThat(firstMove.getPlayer().getScore(), is(1));
    }

    @Test
    public void shouldDeclareTie() throws Exception {
        // Given
        Round round = roundBuilder().build();
        round.playRound();

        // When
        round.declareTie();

        // Then
        assertThat(round.isTie(),is(true));
    }

    @Test
    public void shouldFindMoveForWeapon() throws Exception {
        // Given
        Round round = roundBuilder().build();
        round.playRound();
        Move firstMove = round.getMoves().iterator().next();

        // When
        Move foundMove = round.findMoveWithWeapon(firstMove.getWeapon());

        // Then
        assertThat(foundMove, is(firstMove));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailWhenFindMoveForWeaponCalledWithInvalidWeapon() throws Exception {
        // Given
        Player player1 = playerBuilder().predefinedWeaponList(asList(Weapon.SCISSORS)).build();
        Player player2 = playerBuilder().predefinedWeaponList(asList(Weapon.PAPER)).build();
        Round round = roundBuilder().players(asList(player1, player2)).build();

        // When
        round.findMoveWithWeapon(Weapon.SPOCK);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailWhenDeclaringTieOnUnPlayedRound() throws Exception {
        // Given
        Round round = roundBuilder().build();

        // When
        round.declareTie();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenAwardingRoundToNonExistentMove() throws Exception {
        // Given
        Round round = roundBuilder().build();
        round.playRound();

        // When
        round.awardRoundTo(moveBuilder().build());
    }


}
