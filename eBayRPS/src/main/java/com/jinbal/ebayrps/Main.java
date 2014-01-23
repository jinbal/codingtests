package com.jinbal.ebayrps;

import com.jinbal.ebayrps.gamecontrol.GameEngine;
import com.jinbal.ebayrps.gamejudging.rounds.TwoPlayerRoundJudgingStrategy;
import com.jinbal.ebayrps.gamejudging.weapons.ModuloArithmeticWeaponJudge;
import com.jinbal.ebayrps.ui.ScannerGameUI;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine(
                new TwoPlayerRoundJudgingStrategy(new ModuloArithmeticWeaponJudge()),
                new ScannerGameUI());

        gameEngine.start();
    }
}
