package com.jinbal.ebayrps.gamejudging.weapons;

import com.jinbal.ebayrps.domain.Weapon;
import io.generators.core.Builder;
import io.generators.core.Generators;

public class WeaponJudgingResultBuilder implements Builder<WeaponJudgingResult> {

    private Weapon winningWeapon = Generators.randomEnum(Weapon.class).next();
    private boolean tie = false;

    public static WeaponJudgingResultBuilder weaponJudgingResultBuilder() {
        return new WeaponJudgingResultBuilder();
    }

    @Override
    public WeaponJudgingResult build() {
        if(tie) {
            return WeaponJudgingResult.tie();
        } else {
            return new WeaponJudgingResult(winningWeapon);
        }
    }

    public WeaponJudgingResultBuilder winningWeapon(Weapon winningWeapon) {
        this.winningWeapon = winningWeapon;
        return this;
    }


    public WeaponJudgingResultBuilder tie() {
        this.tie = true;
        return this;
    }
}
