package com.jinbal.ebayrps.gamecontrol;

import com.jinbal.ebayrps.domain.Game;
import com.jinbal.ebayrps.domain.GameType;
import com.jinbal.ebayrps.domain.Round;
import com.jinbal.ebayrps.domain.players.ComputerPlayer;
import com.jinbal.ebayrps.domain.players.Player;
import com.jinbal.ebayrps.domain.players.UIPlayer;
import com.jinbal.ebayrps.gamejudging.rounds.RoundJudgingStrategy;
import com.jinbal.ebayrps.ui.GameUI;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class GameEngine {
    private static final int DEFAULT_NUMBER_OF_ROUNDS = 1;

    private RoundJudgingStrategy roundJudgingStrategy;
    private GameUI gameUI;
    private Game currentGame;

    public GameEngine(RoundJudgingStrategy roundJudgingStrategy, GameUI gameUI) {
        this.roundJudgingStrategy = roundJudgingStrategy;
        this.gameUI = gameUI;
    }

    public void start() {
        gameUI.showGameIntro();
        playGames();
    }

    private void playGames() {
        while (shouldStartNewGame()) {
            newGame();
            playRounds();
            showGameSummary();
        }
    }

    private void showGameSummary() {
        gameUI.showGameSummary(currentGame.calculateResult());
    }

    private void playRounds() {
        while (!currentGame.isGameEnded()) {
            Round round = currentGame.playNextRound();
            roundJudgingStrategy.judgeRound(round);
            gameUI.showRoundSummary(round);
        }
    }

    private boolean shouldStartNewGame() {
        return gameUI.requestNewGame();
    }

    private void newGame() {
        GameType gameType = gameUI.requestGameType();
        List<Player> players = createPlayersForGameType(gameType);
        this.currentGame = new Game(players, DEFAULT_NUMBER_OF_ROUNDS);
    }

    private List<Player> createPlayersForGameType(GameType gameType) {
        List<Player> players = new ArrayList<>();
        switch (gameType) {
            case PLAYER_COMPUTER:
                players.addAll(asList(new UIPlayer(gameUI), new ComputerPlayer()));
                break;
            case COMPUTER_COMPUTER:
                players.addAll(asList(new ComputerPlayer(), new ComputerPlayer()));
                break;
        }
        return players;
    }

    public Game currentGame() {
        return currentGame;
    }
}
