package com.pulselive.demo.helpers;

import com.pulselive.demo.LeagueTableEntry;

/**
 * FootballHelper contains helper methods relating to LeagueTableEntry modification
 * and leagueTable sorting
 */
public class FootballHelper{

    /**
     * win populates basic win requirements to an leagueTableEntry
     * @param leagueTableEntry - team
     */
    public static void win(LeagueTableEntry leagueTableEntry) {
        leagueTableEntry.setWon(leagueTableEntry.getWon() + 1);
        leagueTableEntry.setPlayed(leagueTableEntry.getPlayed() + 1);
        leagueTableEntry.setPoints(leagueTableEntry.getPoints() + 3);
    }

    /**
     * lose populates basic lose requirements to an leagueTableEntry
     * @param leagueTableEntry - team
     */
    public static void lose(LeagueTableEntry leagueTableEntry) {
        leagueTableEntry.setLost(leagueTableEntry.getLost() + 1);
        leagueTableEntry.setPlayed(leagueTableEntry.getPlayed() + 1);
    }

    /**
     * draw populates basic draw requirements to an leagueTableEntry
     * @param leagueTableEntry - team
     */
    public static void draw(LeagueTableEntry leagueTableEntry) {
        leagueTableEntry.setDrawn(leagueTableEntry.getDrawn() + 1);
        leagueTableEntry.setPlayed(leagueTableEntry.getPlayed() + 1);
        leagueTableEntry.setPoints(leagueTableEntry.getPoints() + 1);
    }

    /**
     * compareTeams compares two team to determine who finishes higher
     * @param t1 - team1
     * @param t2 - team2
     * @return
     */
    public static int compareTeams(LeagueTableEntry t1, LeagueTableEntry t2) {
        if (t1.getPoints() != t2.getPoints()) {
            return t2.getPoints() - t1.getPoints(); // based on points
        } else if (t1.getGoalDifference() != t2.getGoalDifference()) {
            return t2.getGoalDifference() - t1.getGoalDifference(); // based on goal difference
        } else {
            return t1.getGoalsFor() != t1.getGoalsFor() ? t2.getGoalsFor() - t1.getGoalsFor() // based on goals for
                    : t1.getTeamName().compareTo(t2.getTeamName()); // based on alphabetical order
        }
    }

}
