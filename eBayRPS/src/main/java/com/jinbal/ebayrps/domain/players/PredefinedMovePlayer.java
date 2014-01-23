package com.jinbal.ebayrps.domain.players;

import com.jinbal.ebayrps.domain.Move;
import com.jinbal.ebayrps.domain.Weapon;

import java.util.Iterator;
import java.util.List;

public class PredefinedMovePlayer extends Player {
    private final List<Weapon> masterWeaponList;
    private Iterator<Weapon> predefinedMoveWeaponsIterator;

    public PredefinedMovePlayer(List<Weapon> predefinedMoveWeapons) {
        if (predefinedMoveWeapons == null || predefinedMoveWeapons.isEmpty()) {
            throw new IllegalArgumentException("predefinedMoveWeapons must have at least 1 weapon");
        }
        this.masterWeaponList = predefinedMoveWeapons;
        this.predefinedMoveWeaponsIterator = predefinedMoveWeapons.iterator();
    }

    public Move nextMove() {
        if (masterWeaponList.size() == 1) {
            return new Move(this, masterWeaponList.get(0));
        }
        resetIteratorIfNecessary();
        return new Move(this, predefinedMoveWeaponsIterator.next());
    }

    @Override
    public String getDescription() {
        return "PredefinedMovePlayer";
    }

    private void resetIteratorIfNecessary() {
        if (!predefinedMoveWeaponsIterator.hasNext()) {
            predefinedMoveWeaponsIterator = masterWeaponList.iterator();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PredefinedMovePlayer that = (PredefinedMovePlayer) o;

        if (!masterWeaponList.equals(that.masterWeaponList)) return false;
        if (predefinedMoveWeaponsIterator != null ? !predefinedMoveWeaponsIterator
                .equals(that.predefinedMoveWeaponsIterator) : that.predefinedMoveWeaponsIterator != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return masterWeaponList.hashCode();
    }

    @Override
    public String toString() {
        return "PredefinedMovePlayer{" +
                "masterWeaponList=" + masterWeaponList +
                '}';
    }
}
