package com.jinbal.ebayrps.gamejudging;

import com.jinbal.ebayrps.domain.Round;
import com.jinbal.ebayrps.domain.Weapon;
import com.jinbal.ebayrps.domain.players.Player;
import com.jinbal.ebayrps.gamejudging.rounds.TwoPlayerRoundJudgingStrategy;
import com.jinbal.ebayrps.gamejudging.weapons.WeaponJudge;
import com.jinbal.ebayrps.gamejudging.weapons.WeaponJudgingResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.jinbal.ebayrps.domain.PredefinedMovePlayerBuilder.playerBuilder;
import static com.jinbal.ebayrps.domain.RoundBuilder.roundBuilder;
import static com.jinbal.ebayrps.gamejudging.weapons.WeaponJudgingResultBuilder.weaponJudgingResultBuilder;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class TwoPlayerRoundJudgingStrategyTest {

    @InjectMocks
    private TwoPlayerRoundJudgingStrategy underTest;

    @Mock
    private WeaponJudge mockWeaponJudge;

    @Test
    public void shouldJudgeRoundWinnerCorrectly() throws Exception {
        // Given
        Player player1 = playerBuilder().predefinedWeaponList(asList(Weapon.SCISSORS)).build();
        Player player2 = playerBuilder().predefinedWeaponList(asList(Weapon.PAPER)).build();
        Round round = roundBuilder().players(asList(player1, player2)).build();
        WeaponJudgingResult weaponJudgingResult = weaponJudgingResultBuilder().winningWeapon(Weapon.SCISSORS).build();
        given(mockWeaponJudge.judgeWinningWeapon(any(Weapon.class), any(Weapon.class))).willReturn(weaponJudgingResult);

        // When
        round.playRound();
        underTest.judgeRound(round);

        // Then
        assertThat(round.winner(), is(player1));
        assertThat(round.winningMove().getWeapon(), is(Weapon.SCISSORS));
    }

    @Test
    public void shouldJudgeATieCorrectly() throws Exception {
        // Given
        Player player1 = playerBuilder().predefinedWeaponList(asList(Weapon.SCISSORS)).build();
        Player player2 = playerBuilder().predefinedWeaponList(asList(Weapon.SCISSORS)).build();
        Round round = roundBuilder().players(asList(player1, player2)).build();
        WeaponJudgingResult weaponJudgingResult = weaponJudgingResultBuilder().tie().build();
        given(mockWeaponJudge.judgeWinningWeapon(any(Weapon.class), any(Weapon.class))).willReturn(weaponJudgingResult);

        // When
        round.playRound();
        underTest.judgeRound(round);

        // Then
        assertThat(round.isPlayed(), is(true));
        assertThat(round.isTie(), is(true));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailWhenRoundToBeJudgedIsUnplayed() throws Exception {
        // Given
        Round unplayedRound = roundBuilder().build();

        // When
        underTest.judgeRound(unplayedRound);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailWhenRoundHasIncorrectNumberOfMoves() throws Exception {
        // Given
        Player onlyPlayer = playerBuilder().predefinedWeaponList(asList(Weapon.SCISSORS)).build();
        Round round = roundBuilder().players(asList( onlyPlayer)).build();

        // When
        round.playRound();
        underTest.judgeRound(round);
    }
}
