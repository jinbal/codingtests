package com.jinbal.ebayrps.domain;

import com.jinbal.ebayrps.domain.players.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static java.util.Collections.reverse;
import static java.util.Collections.sort;

public class Game {
    private final List<Player> players;
    private final int numberOfRounds;
    private final List<Round> roundsPlayed = new ArrayList<>();
    private Round lastPlayedRound;

    public Game(List<Player> players, int numberOfRounds) {
        this.players = players;
        this.numberOfRounds = numberOfRounds;
    }


    public Round playNextRound() {
        failIfNoRoundsLeft();
        Round nextRound = new Round(players);
        nextRound.playRound();
        lastPlayedRound = nextRound;
        roundsPlayed.add(nextRound);
        return nextRound;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int numberOfRoundsLeft() {
        return numberOfRounds - roundsPlayed.size();
    }

    public Round getLastPlayedRound() {
        return lastPlayedRound;
    }


    private void failIfNoRoundsLeft() {
        if (!hasMoreRounds()) {
            throw new IllegalStateException("no more rounds left to play");
        }
    }

    private boolean hasMoreRounds() {
        return numberOfRoundsLeft() > 0;
    }

    public List<Round> getRoundsPlayed() {
        return roundsPlayed;
    }

    private Comparator<Player> playerScoreComparator() {
        return new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Integer.compare(p1.getScore(), p2.getScore());
            }
        };
    }

    public GameResult calculateResult() {
        if (tieDetected()) {
            return new GameResult(true, new ArrayList<>(players));
        }
        List<Player> sortedPlayerList = sortPlayersByScore();
        return new GameResult(sortedPlayerList.get(0), sortedPlayerList);
    }

    private boolean tieDetected() {
        HashSet<Integer> uniqueScores = new HashSet<>();
        for (Player player : players) {
            uniqueScores.add(player.getScore());
        }
        return uniqueScores.size() == 1;
    }

    private List<Player> sortPlayersByScore() {
        List<Player> sortedPlayerList = new ArrayList<>();
        sortedPlayerList.addAll(players);
        sort(sortedPlayerList, playerScoreComparator());
        reverse(sortedPlayerList);
        return sortedPlayerList;
    }

    public boolean isGameEnded() {
        return numberOfRoundsLeft() == 0;
    }
}
