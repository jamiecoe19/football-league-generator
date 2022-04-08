package com.pulselive.demo.footballResult;

import com.pulselive.demo.helpers.FootballHelper;
import com.pulselive.demo.LeagueTableEntry;
import com.pulselive.demo.Match;

/**
 * Draw modifies home and away LeagueTableEntries based on draw result
 */
public class Draw implements FootballResult {
    public void record(LeagueTableEntry home, LeagueTableEntry away, Match match) {
        FootballHelper.draw(home);
        home.setGoalsFor(home.getGoalsFor() + match.getHomeScore());
        home.setGoalsAgainst(home.getGoalsAgainst() + match.getAwayScore());

        FootballHelper.draw(away);
        away.setGoalsFor(away.getGoalsFor() + match.getAwayScore());
        away.setGoalsAgainst(away.getGoalsAgainst() + match.getHomeScore());
    }
}
