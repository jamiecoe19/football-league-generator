package com.pulselive.demo.footballResult;

import com.pulselive.demo.Match;

/**
 * FootballResultFactory get a result type based on match
 */
public class FootballResultFactory {

    /**
     * getFootballResult get result based on match
     * @param match - football match
     * @return FootballResult - (e.g. HomeWin, AwayWin, Draw)
     */
    public static FootballResult getFootballResult(Match match) {
        int matchGoalDifference = match.getHomeScore() - match.getAwayScore();
        return matchGoalDifference > 0 ? new HomeWin() : matchGoalDifference < 0 ? new AwayWin() : new Draw();
    }
}
