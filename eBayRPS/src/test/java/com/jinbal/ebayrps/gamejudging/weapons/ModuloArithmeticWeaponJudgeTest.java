package com.jinbal.ebayrps.gamejudging.weapons;

import com.jinbal.ebayrps.domain.Weapon;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ModuloArithmeticWeaponJudgeTest {

    private ModuloArithmeticWeaponJudge underTest = new ModuloArithmeticWeaponJudge();

    @Test
    public void paperCoversRock() throws Exception {
        assertThat(underTest.judgeWinningWeapon(Weapon.PAPER, Weapon.ROCK).getWinningWeapon(), is(Weapon.PAPER));
        assertThat(underTest.judgeWinningWeapon(Weapon.ROCK, Weapon.PAPER).getWinningWeapon(), is(Weapon.PAPER));
    }

    @Test
    public void paperDisprovesSpock() throws Exception {
        assertThat(underTest.judgeWinningWeapon(Weapon.PAPER, Weapon.SPOCK).getWinningWeapon(), is(Weapon.PAPER));
        assertThat(underTest.judgeWinningWeapon(Weapon.SPOCK, Weapon.PAPER).getWinningWeapon(), is(Weapon.PAPER));
    }

    @Test
    public void rockBluntsScissors() throws Exception {
        assertThat(underTest.judgeWinningWeapon(Weapon.SCISSORS, Weapon.ROCK).getWinningWeapon(), is(Weapon.ROCK));
        assertThat(underTest.judgeWinningWeapon(Weapon.ROCK, Weapon.SCISSORS).getWinningWeapon(), is(Weapon.ROCK));
    }

    @Test
    public void rockCrushesLizard() throws Exception {
        assertThat(underTest.judgeWinningWeapon(Weapon.LIZARD, Weapon.ROCK).getWinningWeapon(), is(Weapon.ROCK));
        assertThat(underTest.judgeWinningWeapon(Weapon.ROCK, Weapon.LIZARD).getWinningWeapon(), is(Weapon.ROCK));
    }

    @Test
    public void scissorsCutsPaper() throws Exception {
        assertThat(underTest.judgeWinningWeapon(Weapon.SCISSORS, Weapon.PAPER).getWinningWeapon(), is(Weapon.SCISSORS));
        assertThat(underTest.judgeWinningWeapon(Weapon.PAPER, Weapon.SCISSORS).getWinningWeapon(), is(Weapon.SCISSORS));
    }

    @Test
    public void scissorsDecapitatesLizard() throws Exception {
        assertThat(underTest.judgeWinningWeapon(Weapon.SCISSORS, Weapon.LIZARD).getWinningWeapon(), is(Weapon.SCISSORS));
        assertThat(underTest.judgeWinningWeapon(Weapon.LIZARD, Weapon.SCISSORS).getWinningWeapon(), is(Weapon.SCISSORS));

    }

    @Test
    public void spockSmashesScissors() throws Exception {
        assertThat(underTest.judgeWinningWeapon(Weapon.SPOCK, Weapon.SCISSORS).getWinningWeapon(), is(Weapon.SPOCK));
        assertThat(underTest.judgeWinningWeapon(Weapon.SCISSORS, Weapon.SPOCK).getWinningWeapon(), is(Weapon.SPOCK));
    }

    @Test
    public void spockVapourisesRock() throws Exception {
        assertThat(underTest.judgeWinningWeapon(Weapon.ROCK, Weapon.SPOCK).getWinningWeapon(), is(Weapon.SPOCK));
        assertThat(underTest.judgeWinningWeapon(Weapon.SPOCK, Weapon.ROCK).getWinningWeapon(), is(Weapon.SPOCK));
    }

    @Test
    public void lizardPoisonsSpock() throws Exception {
        assertThat(underTest.judgeWinningWeapon(Weapon.LIZARD, Weapon.SPOCK).getWinningWeapon(), is(Weapon.LIZARD));
        assertThat(underTest.judgeWinningWeapon(Weapon.SPOCK, Weapon.LIZARD).getWinningWeapon(), is(Weapon.LIZARD));
    }

    @Test
    public void lizardEatsPaper() throws Exception {
        assertThat(underTest.judgeWinningWeapon(Weapon.LIZARD, Weapon.PAPER).getWinningWeapon(), is(Weapon.LIZARD));
        assertThat(underTest.judgeWinningWeapon(Weapon.PAPER, Weapon.LIZARD).getWinningWeapon(), is(Weapon.LIZARD));
    }

    @Test
    public void shouldTieWhenSameWeapon() throws Exception {
        assertThat(underTest.judgeWinningWeapon(Weapon.PAPER, Weapon.PAPER).isTie(), is(true));
        assertThat(underTest.judgeWinningWeapon(Weapon.SCISSORS, Weapon.SCISSORS).isTie(), is(true));
        assertThat(underTest.judgeWinningWeapon(Weapon.ROCK, Weapon.ROCK).isTie(), is(true));
        assertThat(underTest.judgeWinningWeapon(Weapon.LIZARD, Weapon.LIZARD).isTie(), is(true));
        assertThat(underTest.judgeWinningWeapon(Weapon.SPOCK, Weapon.SPOCK).isTie(), is(true));
    }
}
