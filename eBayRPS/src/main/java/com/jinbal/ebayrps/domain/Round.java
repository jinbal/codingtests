package com.jinbal.ebayrps.domain;

import com.jinbal.ebayrps.domain.players.Player;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private final List<Player> players;
    private final List<Move> moves = new ArrayList<>();
    private Move winningMove;
    private boolean played = false;
    private boolean tie = false;

    public Round(List<Player> players) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("A Round must have at least 1 player");
        }
        this.players = players;
    }

    public void playRound() {
        for (Player player : players) {
            moves.add(player.nextMove());
        }
        played = true;
    }

    public Move winningMove() {
        return winningMove;
    }

    public Player winner() {
        if (winningMove != null) {
            return winningMove.getPlayer();
        } else {
            return null;
        }
    }

    public List<Move> getMoves() {
        return moves;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isPlayed() {
        return played;
    }

    public void awardRoundTo(Move move) {
        if (!moves.contains(move)) {
            throw new IllegalArgumentException("Illegal attempt to award Round to invalid move");
        }
        this.winningMove = move;
        updateWinnerScore();
    }

    private void updateWinnerScore() {
        this.winner().incScore();
    }

    public void declareTie() {
        if (!isPlayed()) {
            throw new IllegalStateException("cannot declare tie for unplayed Round");
        }
        this.tie = true;
    }

    public boolean isTie() {
        return tie;
    }

    public Move findMoveWithWeapon(Weapon weapon) {
        for (Move move : moves) {
            if (move.getWeapon().equals(weapon)) {
                return move;
            }
        }
        throw new IllegalStateException("No Moves for Weapon:" + weapon);
    }

    public String getSummary() {
        if(isTie()) {
            return "Round TIED";
        }
        StringBuilder stringBuilder = new StringBuilder("Round won by ")
                .append(winner().getDescription())
                .append(" - ")
                .append(winningMove.getWeapon())
                .append(" beats ")
                .append(getLosingMoves());
        return stringBuilder.toString();
    }

    private String getLosingMoves() {
        StringBuilder builder = new StringBuilder();
        for (Move move : moves) {
            if(!move.equals(winningMove)) {
                builder.append(move.getWeapon()).append(", ");
            }
        }
        return builder.toString();
    }
}
