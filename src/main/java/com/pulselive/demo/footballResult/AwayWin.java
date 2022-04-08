package com.pulselive.demo.footballResult;

import com.pulselive.demo.helpers.FootballHelper;
import com.pulselive.demo.LeagueTableEntry;
import com.pulselive.demo.Match;

/**
 * AwayWin modifies home and away LeagueTableEntry based on away win result
 */
public class AwayWin implements FootballResult{

    public void record(LeagueTableEntry home, LeagueTableEntry away, Match match) {
        FootballHelper.lose(home);
        home.setGoalsFor(home.getGoalsFor() + match.getHomeScore());
        home.setGoalsAgainst(home.getGoalsAgainst() + match.getAwayScore());
        int matchGoalDifference = match.getHomeScore() - match.getAwayScore();
        home.setGoalDifference(home.getGoalDifference() + matchGoalDifference);

        FootballHelper.win(away);
        away.setGoalsFor(away.getGoalsFor() + match.getAwayScore());
        away.setGoalsAgainst(away.getGoalsAgainst() + match.getHomeScore());
        matchGoalDifference = match.getAwayScore() - match.getHomeScore();
        away.setGoalDifference(away.getGoalDifference() + matchGoalDifference);
    }
}
