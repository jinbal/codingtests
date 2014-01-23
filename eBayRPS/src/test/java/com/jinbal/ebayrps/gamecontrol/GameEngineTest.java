package com.jinbal.ebayrps.gamecontrol;

import com.jinbal.ebayrps.domain.Game;
import com.jinbal.ebayrps.domain.GameType;
import com.jinbal.ebayrps.domain.Round;
import com.jinbal.ebayrps.domain.players.ComputerPlayer;
import com.jinbal.ebayrps.domain.players.UIPlayer;
import com.jinbal.ebayrps.gamejudging.rounds.RoundJudgingStrategy;
import com.jinbal.ebayrps.ui.GameUI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GameEngineTest {

    @InjectMocks
    private GameEngine underTest;

    @Mock
    private GameUI mockGameUI;

    @Mock
    private RoundJudgingStrategy mockRoundJudgingStrategy;


    @Test
    public void shouldCreateANewPlayerVsComputerGameWhenStarted() throws Exception {
        // Given
        given((mockGameUI.requestNewGame())).willReturn(true, false);
        given(mockGameUI.requestGameType()).willReturn(GameType.PLAYER_COMPUTER);

        // When
        underTest.start();

        // Then
        verify(mockGameUI).requestGameType();
        verify(mockGameUI).showGameIntro();
        Game game = underTest.currentGame();
        assertThat(game, notNullValue());
        assertThat(game.getPlayers(), containsInAnyOrder(instanceOf(ComputerPlayer.class), instanceOf(UIPlayer.class)));
    }

    @Test
    public void shouldCreateANewComputerVsComputerGameWhenStarted() throws Exception {
        // Given
        given((mockGameUI.requestNewGame())).willReturn(true, false);
        given(mockGameUI.requestGameType()).willReturn(GameType.COMPUTER_COMPUTER);

        // When
        underTest.start();

        // Then
        verify(mockGameUI).requestGameType();
        verify(mockGameUI).showGameIntro();
        Game game = underTest.currentGame();
        assertThat(game, notNullValue());
        assertThat(game.getPlayers(), containsInAnyOrder(instanceOf(ComputerPlayer.class), instanceOf(ComputerPlayer.class)));
    }

    @Test
    public void shouldPlayGameUntilEnd() throws Exception {
        // Given
        given((mockGameUI.requestNewGame())).willReturn(true,false);
        given(mockGameUI.requestGameType()).willReturn(GameType.COMPUTER_COMPUTER);

        // When
        underTest.start();

        // Then
        Game currentGame = underTest.currentGame();
        assertThat(currentGame.isGameEnded(), is(true));
        verify(mockGameUI, times(currentGame.getRoundsPlayed().size())).showRoundSummary(any(Round.class));
        verify(mockGameUI).showGameSummary(eq(currentGame.calculateResult()));
    }
}
