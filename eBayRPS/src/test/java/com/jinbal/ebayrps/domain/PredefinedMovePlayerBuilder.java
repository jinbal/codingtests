package com.jinbal.ebayrps.domain;

import com.jinbal.ebayrps.domain.players.Player;
import com.jinbal.ebayrps.domain.players.PredefinedMovePlayer;
import io.generators.core.Builder;
import io.generators.core.Generators;

import java.util.List;

import static java.util.Arrays.asList;

public class PredefinedMovePlayerBuilder implements Builder<Player> {
    private List<Weapon> masterWeaponList = asList(Generators.randomEnum(Weapon.class).next());

    @Override
    public Player build() {
        return new PredefinedMovePlayer( masterWeaponList);
    }

    public PredefinedMovePlayerBuilder predefinedWeaponList(List<Weapon> masterWeaponList) {
        this.masterWeaponList = masterWeaponList;
        return this;
    }

    public PredefinedMovePlayerBuilder weapon(Weapon... weapon) {
        this.masterWeaponList = asList(weapon);
        return this;
    }


    public static PredefinedMovePlayerBuilder playerBuilder() {
        return new PredefinedMovePlayerBuilder();
    }
}