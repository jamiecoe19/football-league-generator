package com.pulselive.demo;

import com.pulselive.demo.footballResult.FootballResultFactory;
import com.pulselive.demo.helpers.FootballHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class LeagueTable
{
    private final List<Match> matches;
    public LeagueTable( final List<Match> matches )
    {
        this.matches = matches;
    }

    /**
     * getTableEntries get the ordered list of league table entries for this league table.
     *
     * @return List of leagueTableEntries
     */
    public List<LeagueTableEntry> getTableEntries() {
        List<LeagueTableEntry> leagueTableEntries = new ArrayList<>();
        addTeams(leagueTableEntries);
        recordResults(leagueTableEntries);
        leagueTableEntries.sort(FootballHelper::compareTeams);
        return leagueTableEntries;
    }


    /**
     * addTeams add all distinct teams to a barebones league
     * @param leagueTableEntries - empty league
     */
    private void addTeams(List<LeagueTableEntry> leagueTableEntries) {
        this.matches.forEach((match) -> {
            if (match.getAwayTeam().equals(match.getHomeTeam())) {
                throw new RuntimeException("home team and away team cannot be the same");
            }
            if (leagueTableEntries.stream()
                    .filter((entry) -> match.getHomeTeam().equals(entry.getTeamName()))
                    .findFirst()
                    .isEmpty()) {
                leagueTableEntries.add(new LeagueTableEntry(match.getHomeTeam()));
            }

            if (leagueTableEntries.stream()
                    .filter((entry) -> match.getAwayTeam().equals(entry.getTeamName()))
                    .findFirst()
                    .isEmpty()) {
                leagueTableEntries.add(new LeagueTableEntry(match.getAwayTeam()));
            }

        });
    }


    /**
     * recordResults populates league table with data
     * @param leagueTableEntries league table with teams
     */
    private void recordResults(List<LeagueTableEntry> leagueTableEntries) {
        this.matches.forEach((match) -> {
            LeagueTableEntry homeTeam = leagueTableEntries.stream()
                    .filter((entry) -> match.getHomeTeam().equals(entry.getTeamName()))
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new);
            LeagueTableEntry awayTeam = leagueTableEntries.stream()
                    .filter((entry) -> match.getAwayTeam().equals(entry.getTeamName()))
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new);
            FootballResultFactory.getFootballResult(match).record(homeTeam, awayTeam, match);
        });
    }
// COMMENTED OUT cause this is what I use for testing
//    public static void main(String[] args) {
//        List<Match> matches = new ArrayList();
//        matches.add(new Match("Liverpool", "AC Milan", 3, 2));
//        matches.add(new Match("Atletico Madrid", "FC Porto", 0, 0));
//
//        matches.add(new Match("FC Porto", "Liverpool", 1, 5));
//        matches.add(new Match("AC Milan", "Atletico Madrid", 1, 2));
//
//        matches.add(new Match("Atletico Madrid", "Liverpool", 2, 3));
//        matches.add(new Match("FC Porto", "AC Milan", 1, 0));
//
//
//        matches.add(new Match("Liverpool", "Atletico Madrid", 2, 0));
//        matches.add(new Match("AC Milan", "FC Porto", 1, 1));
//
//
//        matches.add(new Match("Liverpool", "FC Porto", 2, 0));
//        matches.add(new Match("Atletico Madrid", "AC Milan", 0, 1));
//
//
//        matches.add(new Match("AC Milan", "Liverpool", 1, 2));
//        matches.add(new Match("FC Porto", "Atletico Madrid", 1, 3));
//
//        List<LeagueTableEntry> tableEntries = new LeagueTable(matches).getTableEntries();
//        System.out.printf("%15s %5s %5s %5s %5s %5s %5s %5s %5s", "TEAM", "PLAYED", "WON", "DRAW", "LOST", "FOR", "AGAINST", "GD", "POINTS");
//        System.out.print("\n");
//        System.out.println("-----------------------------------------------------------------------------");
//        for (LeagueTableEntry tableEntry: tableEntries) {
//            System.out.printf("%15s %5s %5s %5s %5s %5s %5s %8s %5s", tableEntry.getTeamName(), tableEntry.getPlayed(),
//                    tableEntry.getWon(), tableEntry.getDrawn(), tableEntry.getLost(), tableEntry.getGoalsFor(),
//                    tableEntry.getGoalsAgainst(), tableEntry.getGoalDifference(), tableEntry.getPoints());
//            System.out.print("\n");
//        }
//    }
}
