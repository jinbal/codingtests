package com.jinbal.ebayrps.domain;

import com.jinbal.ebayrps.domain.players.Player;
import com.jinbal.ebayrps.gamejudging.rounds.RoundJudgingStrategy;
import io.generators.core.FromBuilderGenerator;
import io.generators.core.Generators;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.jinbal.ebayrps.domain.GameBuilder.gameBuilder;
import static com.jinbal.ebayrps.domain.PredefinedMovePlayerBuilder.playerBuilder;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    @Mock
    private RoundJudgingStrategy mockRoundJudgingStrategy;


    @Test
    public void shouldCreateValidGame() throws Exception {
        // Given
        List<Player> players =Generators.listFrom(2, new FromBuilderGenerator<>(playerBuilder()));
        int numberOfRounds = 1;

        // When
        Game game = new Game(players, numberOfRounds);

        // Then
        assertThat(game.numberOfRoundsLeft(), is(1));
        assertThat(game.getPlayers(), containsInAnyOrder(players.toArray()));
    }


    @Test
    public void shouldPlayNextRound() throws Exception {
        // Given
        Game game = gameBuilder().build();
        int numberOfRounds = game.numberOfRoundsLeft();

        // When
        Round round = game.playNextRound();

        // Then
        assertThat(game.numberOfRoundsLeft(), is(numberOfRounds - 1));
        assertThat(round, notNullValue());
        assertThat(round.isPlayed(), is(true));
        assertThat(round.getPlayers(), containsInAnyOrder(game.getPlayers().toArray()));

    }

    @Test
    public void shouldReturnLastPlayedRound() throws Exception {
        // Given
        Game game = gameBuilder().build();

        // When
        Round round = game.playNextRound();
        Round lastRound = game.getLastPlayedRound();

        // Then
        assertThat(round, is(lastRound));
    }

    @Test
    public void shouldReturnAllPlayedRounds() throws Exception {
        // Given
        Game game = gameBuilder().numberOfRounds(2).build();

        // When
        Round round1 = game.playNextRound();
        Round round2 = game.playNextRound();

        // Then
        assertThat(game.getRoundsPlayed().size(), is(2));
        assertThat(game.getRoundsPlayed(), contains(round1, round2));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailWhenNoMoreRoundsLeftToPlay() throws Exception {
        // Givens
        Game game = gameBuilder().numberOfRounds(1).build();

        // When
        game.playNextRound();
        game.playNextRound();
    }

    @Test
    public void shouldIndicatedWhenGameIsFinished() throws Exception {
        // Given
        Game game = gameBuilder().numberOfRounds(1).build();

        // When
        game.playNextRound();

        // Then
        assertThat(game.isGameEnded(), is(true));
    }

    @Test
    public void shouldCalculateWinningPlayer() throws Exception {
        // Given
        Player player1 = playerBuilder().predefinedWeaponList(asList(Weapon.SCISSORS)).build();
        Player player2 = playerBuilder().predefinedWeaponList(asList(Weapon.PAPER)).build();
        Game game = gameBuilder().players(player1, player2).numberOfRounds(1).build();

        // When
        Round round = game.playNextRound();
        round.awardRoundTo(round.findMoveWithWeapon(Weapon.SCISSORS));
        GameResult gameResult = game.calculateResult();

        // Then
        assertThat(gameResult.winner(), is(player1));

    }

    @Test
    public void shouldDetectTie() throws Exception {
        // Given
        Player player1 = playerBuilder().weapon(Weapon.SCISSORS).build();
        Player player2 = playerBuilder().weapon(Weapon.SCISSORS).build();
        Game game = gameBuilder().players(player1, player2).numberOfRounds(1).build();

        // When
        Round round = game.playNextRound();
        round.declareTie();
        GameResult gameResult = game.calculateResult();

        // Then
        assertThat(gameResult.isTie(), is(true));
    }
}
