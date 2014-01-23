package com.jinbal.ebayrps.ui;

import com.jinbal.ebayrps.domain.GameResult;
import com.jinbal.ebayrps.domain.GameType;
import com.jinbal.ebayrps.domain.Round;
import com.jinbal.ebayrps.domain.Weapon;
import com.jinbal.ebayrps.domain.players.Player;

import java.util.Scanner;

public class ScannerGameUI implements GameUI {
    public static final String JINS_ROCK_PAPER_SCISSORS_SPOCK_LIZARD_GAME = "***** Jins Rock Paper Scissors Spock Lizard Game ********";

    @Override
    public void showGameIntro() {
        System.out.println(JINS_ROCK_PAPER_SCISSORS_SPOCK_LIZARD_GAME);
    }

    @Override
    public GameType requestGameType() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Game Type:");
        GameType[] gameTypes = GameType.values();
        for (GameType gameType : gameTypes) {
            StringBuilder promptBuilder = new StringBuilder();
            promptBuilder.append(gameType.getDescription()).append(" : ").append(gameType.ordinal());
            System.out.println(promptBuilder.toString());
        }
        GameType selectedGameType = null;
        while (selectedGameType == null) {
            if (scanner.hasNextInt()) {
                int ordinal = scanner.nextInt();
                if (ordinal >= 0 && ordinal < gameTypes.length) {
                    selectedGameType = gameTypes[ordinal];
                } else {
                    System.out.println("Error please re-enter [0-" + (gameTypes.length - 1) + "]");
                }
            } else {
                scanner.next();
                System.out.println("Error please re-enter [0-" + (gameTypes.length - 1) + "]");

            }
        }
        return selectedGameType;
    }

    @Override
    public void showRoundSummary(Round round) {
        System.out.println(round.getSummary());
    }

    @Override
    public void showGameSummary(GameResult currentGameResult) {
        System.out.println(currentGameResult.getSummary());
    }

    @Override
    public boolean requestNewGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("New Game? (y/n)");
        Boolean newGame = null;
        while (newGame == null) {
            if (scanner.hasNext()) {
                String selection = scanner.next();
                if (!selection.equals("y") && !selection.equals("n")) {
                    System.out.println("Error - please enter y or n");
                } else {
                    newGame = selection.equals("y") ? true : false;
                }
            } else {
                System.out.println("Error - please enter y or n");
                scanner.next();
            }

        }
        return newGame;
    }

    @Override
    public Weapon requestNextMove(Player player) {
        Scanner scanner = new Scanner(System.in);
        Weapon nextMove = null;
        System.out.println(player.getDescription() + ", select next move:");
        Weapon[] weapons = Weapon.values();
        for (Weapon weapon : weapons) {
            StringBuilder promptBuilder = new StringBuilder();
            promptBuilder.append(weapon.name()).append(" : ").append(weapon.ordinal());
            System.out.println(promptBuilder.toString());
        }
        while (nextMove == null) {
            if (scanner.hasNextInt()) {
                int ordinal = scanner.nextInt();
                if (ordinal >= 0 && ordinal < weapons.length) {
                    nextMove = weapons[ordinal];
                } else {
                    System.out.println("Error please re-enter [0-" + (weapons.length - 1) + "]");
                }
            } else {
                scanner.next();
                System.out.println("Error please re-enter [0-" + (weapons.length - 1) + "]");
            }

        }
        return nextMove;
    }
}
