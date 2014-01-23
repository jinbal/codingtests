package com.jinbal.ebayrps.domain;

import com.jinbal.ebayrps.domain.players.Player;

public class Move {
    private final Player player;
    private final Weapon weapon;

    public Move(Player player, Weapon weapon) {
        this.player = player;
        this.weapon = weapon;
    }

    public Player getPlayer() {
        return player;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (!player.equals(move.player)) return false;
        if (!weapon.equals(move.weapon)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + weapon.hashCode();
        return result;
    }
}
