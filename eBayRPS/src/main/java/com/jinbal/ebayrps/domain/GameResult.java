package com.jinbal.ebayrps.domain;

import com.jinbal.ebayrps.domain.players.Player;

import java.util.List;

public class GameResult {
    private static final String LINE_FEED = System.getProperty("line.separator");
    private final Player winner;
    private final List<Player> rankedPlayerList;
    private final boolean tie;

    public GameResult(Player winner, List<Player> rankedPlayerList) {
        this.winner = winner;
        this.rankedPlayerList = rankedPlayerList;
        this.tie = false;
    }

    public GameResult(boolean tie, List<Player> playerList) {
        this.tie = tie;
        this.rankedPlayerList = playerList;
        this.winner = null;
    }

    public Player winner() {
        return winner;
    }

    public boolean isTie() {
        return tie;
    }

    public List<Player> getRankedPlayerList() {
        return rankedPlayerList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameResult that = (GameResult) o;

        if (tie != that.tie) return false;
        if (!rankedPlayerList.equals(that.rankedPlayerList)) return false;
        if (winner != null ? !winner.equals(that.winner) : that.winner != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = winner != null ? winner.hashCode() : 0;
        result = 31 * result + rankedPlayerList.hashCode();
        result = 31 * result + (tie ? 1 : 0);
        return result;
    }

    public String getSummary() {
        if(tie) {
            return "Game Tied!";
        }
        StringBuilder stringBuilder = new StringBuilder("Game Winner: ")
                .append(winner.getDescription()).append(LINE_FEED)
                .append("Scores (Rounds):").append(LINE_FEED);
        for (Player player : rankedPlayerList) {
            stringBuilder.append(player.getDescription()).append(": ").append(player.getScore()).append(LINE_FEED);
        }
        return stringBuilder.toString();
    }
}
