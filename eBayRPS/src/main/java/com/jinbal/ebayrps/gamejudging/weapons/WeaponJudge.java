package com.jinbal.ebayrps.gamejudging.weapons;

import com.jinbal.ebayrps.domain.Weapon;

public interface WeaponJudge {
    WeaponJudgingResult judgeWinningWeapon(Weapon weaponOne, Weapon weaponTwo);
}
