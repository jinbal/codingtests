package com.jinbal.ebayrps.gamejudging.weapons;

import com.jinbal.ebayrps.domain.Weapon;

public class WeaponJudgingResult {
    private Weapon winningWeapon;
    private boolean tie = false;

    public static WeaponJudgingResult tie() {
        return new WeaponJudgingResult(true);
    }

    private WeaponJudgingResult(boolean tie) {
        this.tie = tie;
    }

    public WeaponJudgingResult(Weapon winningWeapon) {
        this.winningWeapon = winningWeapon;
    }

    public Weapon getWinningWeapon() {
        return winningWeapon;
    }

    public boolean isTie() {
        return tie;
    }
}
