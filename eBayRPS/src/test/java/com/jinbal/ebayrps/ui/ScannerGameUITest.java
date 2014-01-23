package com.jinbal.ebayrps.ui;

import com.jinbal.ebayrps.domain.Game;
import com.jinbal.ebayrps.domain.GameResult;
import com.jinbal.ebayrps.domain.GameType;
import com.jinbal.ebayrps.domain.Move;
import com.jinbal.ebayrps.domain.Round;
import com.jinbal.ebayrps.domain.Weapon;
import com.jinbal.ebayrps.domain.players.Player;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static com.jinbal.ebayrps.domain.GameBuilder.gameBuilder;
import static com.jinbal.ebayrps.domain.PredefinedMovePlayerBuilder.playerBuilder;
import static com.jinbal.ebayrps.domain.RoundBuilder.roundBuilder;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class ScannerGameUITest {
    public static final String LINE_FEED = System.getProperty("line.separator");
    @Rule
    public final TextFromStandardInputStream systemIn = emptyStandardInputStream();

    @Rule
    public final StandardOutputStreamLog systemOut = new StandardOutputStreamLog();

    private ScannerGameUI scannerGameUI = new ScannerGameUI();


    @Test
    public void shouldShowGameIntro() throws Exception {
        // When
        scannerGameUI.showGameIntro();

        // Then
        assertThat(systemOut.getLog(), is("***** Jins Rock Paper Scissors Spock Lizard Game ********\n"));

    }

    @Test
    public void shouldRequestGameType() throws Exception {
        // Given
        systemIn.provideText("0");

        // When
        GameType gameType = scannerGameUI.requestGameType();

        // Then
        assertThat(gameType, is(GameType.PLAYER_COMPUTER));

    }

    @Test
    public void shouldKeepRequestingGameTypeWhenFirstInputIsBad() throws Exception {
        // Given
        systemIn.provideText(buildInput("invalid", "0"));

        // When
        GameType gameType = scannerGameUI.requestGameType();

        // Then
        assertThat(gameType, is(GameType.PLAYER_COMPUTER));
    }

    @Test
    public void shouldKeepRequestingGameTypeWhenFirstInputOutOfRange() throws Exception {
        // Given
        systemIn.provideText(buildInput(Integer.toString(GameType.values().length + 2),"1"));

        // When
        GameType gameType = scannerGameUI.requestGameType();

        // Then
        assertThat(gameType, is(GameType.COMPUTER_COMPUTER));
    }


    @Test
    public void shouldShowRoundSummary() throws Exception {
        // Given
        Player player1 = playerBuilder().predefinedWeaponList(asList(Weapon.SCISSORS)).build();
        Player player2 = playerBuilder().predefinedWeaponList(asList(Weapon.PAPER)).build();
        Round round = roundBuilder().players(asList(player1, player2)).build();
        round.playRound();
        Move firstMove = round.getMoves().iterator().next();
        round.awardRoundTo(firstMove);

        // When
        scannerGameUI.showRoundSummary(round);

        // Then
        assertThat(systemOut.getLog(), is(round.getSummary() + "\n"));

    }

    @Test
    public void shouldShowGameSummary() throws Exception {
        // Given
        Player player1 = playerBuilder().predefinedWeaponList(asList(Weapon.SCISSORS)).build();
        Player player2 = playerBuilder().predefinedWeaponList(asList(Weapon.PAPER)).build();
        Game game = gameBuilder().players(player1, player2).numberOfRounds(1).build();
        Round round = game.playNextRound();
        round.awardRoundTo(round.findMoveWithWeapon(Weapon.SCISSORS));
        GameResult gameResult = game.calculateResult();

        // When
        scannerGameUI.showGameSummary(gameResult);

        // Then
        assertThat(systemOut.getLog(), is(gameResult.getSummary()+LINE_FEED));

    }

    @Test
    public void shouldRequestNewGame() throws Exception {
        // Given
        systemIn.provideText("y");

        // When
        assertThat(scannerGameUI.requestNewGame(), is(true));

    }

    @Test
    public void shouldNotRequestNewGame() throws Exception {
        // Given
        systemIn.provideText("n");

        // When
        assertThat(scannerGameUI.requestNewGame(), is(false));
    }


    @Test
    public void shouldKeepRequestingNewGameUntilValidInputReceived() throws Exception {
        // Given
        systemIn.provideText(buildInput("invalid","y"));
        // Then
        assertThat(scannerGameUI.requestNewGame(), is(true));


    }

    private String buildInput(String... lines) {
        StringBuilder inputBuilder = new StringBuilder();
        for (String line : lines) {
            inputBuilder.append(line);
            inputBuilder.append(LINE_FEED);

        }
        return inputBuilder.toString();
    }

    @Test
    public void shouldRequestNextMove() throws Exception {
        // Given
        Player player = playerBuilder().build();
        systemIn.provideText(Integer.toString(Weapon.ROCK.ordinal()));

        // When
        assertThat(scannerGameUI.requestNextMove(player), is(Weapon.ROCK));
    }

    @Test
    public void shouldKeepRequestingNextMoveWhenFirstInputIsInvalid() throws Exception {
        // Given
        Player player = playerBuilder().build();
        systemIn.provideText(buildInput("invalid",Integer.toString(Weapon.ROCK.ordinal())));

        // When
        assertThat(scannerGameUI.requestNextMove(player), is(Weapon.ROCK));
    }


}
