package com.pulselive.demo.footballResult;

import com.pulselive.demo.LeagueTableEntry;
import com.pulselive.demo.Match;

/**
 * Football is an interface containing method `record`
 */
public interface FootballResult {
    /**
     * Football Result modifies home and away leagueTableEntries based a match result
     * @param home - the home team
     * @param away - the away team
     * @param match - the match result
     */
    void record(LeagueTableEntry home, LeagueTableEntry away, Match match);
}
