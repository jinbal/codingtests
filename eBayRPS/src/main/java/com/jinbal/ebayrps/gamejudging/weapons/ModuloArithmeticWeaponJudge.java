package com.jinbal.ebayrps.gamejudging.weapons;

import com.jinbal.ebayrps.domain.Weapon;

public class ModuloArithmeticWeaponJudge implements WeaponJudge {

    @Override
    public WeaponJudgingResult judgeWinningWeapon(Weapon weaponOne, Weapon weaponTwo) {
        validateNumberOfWeaponsOrFail();

        if (weaponOne.equals(weaponTwo)) {
            return WeaponJudgingResult.tie();
        } else {
            int numWeapons = Weapon.values().length;
            int mod = (numWeapons + weaponOne.ordinal() - weaponTwo.ordinal()) % numWeapons;
            if (mod % 2 == 1) {
                return new WeaponJudgingResult(weaponOne);
            } else {
                return new WeaponJudgingResult(weaponTwo);
            }
        }
    }

    private void validateNumberOfWeaponsOrFail() {
        if (Weapon.values().length < 3 || Weapon.values().length % 2 == 0) {
            throw new UnsupportedOperationException("ModuloArithmeticWeaponJudge is only a valid strategy when there are at least 3 weapons and the number of weapons is an odd number");
        }
    }
}
