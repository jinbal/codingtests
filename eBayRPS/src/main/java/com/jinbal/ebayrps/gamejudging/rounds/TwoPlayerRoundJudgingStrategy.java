package com.jinbal.ebayrps.gamejudging.rounds;

import com.jinbal.ebayrps.domain.Move;
import com.jinbal.ebayrps.domain.Round;
import com.jinbal.ebayrps.domain.Weapon;
import com.jinbal.ebayrps.gamejudging.weapons.WeaponJudge;
import com.jinbal.ebayrps.gamejudging.weapons.WeaponJudgingResult;

import java.util.Iterator;

public class TwoPlayerRoundJudgingStrategy implements RoundJudgingStrategy {

    private final WeaponJudge weaponJudge;

    public TwoPlayerRoundJudgingStrategy(WeaponJudge weaponJudge) {
        this.weaponJudge = weaponJudge;
    }

    @Override
    public void judgeRound(Round round) {
        failIfRoundUnplayed(round);
        failIfInvalidNumberOfMoves(round);

        WeaponJudgingResult weaponJudgingResult = getWeaponJudgingResult(round);

        if(weaponJudgingResult.isTie()) {
            round.declareTie();
        } else {
            Weapon winningWeapon = weaponJudgingResult.getWinningWeapon();
            round.awardRoundTo(round.findMoveWithWeapon(winningWeapon));
        }

    }

    private WeaponJudgingResult getWeaponJudgingResult(Round round) {
        Iterator<Move> movesIt = round.getMoves().iterator();
        Move player1Move = movesIt.next();
        Move player2Move = movesIt.next();
        return weaponJudge.judgeWinningWeapon(player1Move.getWeapon(), player2Move.getWeapon());
    }

    private void failIfInvalidNumberOfMoves(Round round) {
        int numMoves = round.getMoves().size();
        if (numMoves != 2) {
            throw new IllegalStateException("TwoPlayerRoundJudgingStrategy requires 2 players/moves not:" + numMoves);
        }
    }

    private void failIfRoundUnplayed(Round round) {
        if (!round.isPlayed()) {
            throw new IllegalStateException("Round has not been played");
        }
    }
}
