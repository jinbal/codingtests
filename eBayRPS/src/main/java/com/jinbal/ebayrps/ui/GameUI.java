package com.jinbal.ebayrps.ui;

import com.jinbal.ebayrps.domain.GameResult;
import com.jinbal.ebayrps.domain.GameType;
import com.jinbal.ebayrps.domain.Round;
import com.jinbal.ebayrps.domain.Weapon;
import com.jinbal.ebayrps.domain.players.Player;

public interface GameUI {
    void showGameIntro();

    GameType requestGameType();

    void showRoundSummary(Round round);

    void showGameSummary(GameResult currentGame);

    boolean requestNewGame();

    Weapon requestNextMove(Player player);
}
