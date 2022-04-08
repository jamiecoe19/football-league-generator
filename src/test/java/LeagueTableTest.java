import com.pulselive.demo.LeagueTable;
import com.pulselive.demo.LeagueTableEntry;
import com.pulselive.demo.Match;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LeagueTableTest {

    @Test
    public void TestLeagueTable_CompareToRealLifeTable_compareByPoints(){
        List<Match> matches = new ArrayList();
        matches.add(new Match("Liverpool", "AC Milan", 3, 2));
        matches.add(new Match("Atletico Madrid", "FC Porto", 0, 0));

        matches.add(new Match("FC Porto", "Liverpool", 1, 5));
        matches.add(new Match("AC Milan", "Atletico Madrid", 1, 2));

        matches.add(new Match("Atletico Madrid", "Liverpool", 2, 3));
        matches.add(new Match("FC Porto", "AC Milan", 1, 0));


        matches.add(new Match("Liverpool", "Atletico Madrid", 2, 0));
        matches.add(new Match("AC Milan", "FC Porto", 1, 1));


        matches.add(new Match("Liverpool", "FC Porto", 2, 0));
        matches.add(new Match("Atletico Madrid", "AC Milan", 0, 1));


        matches.add(new Match("AC Milan", "Liverpool", 1, 2));
        matches.add(new Match("FC Porto", "Atletico Madrid", 1, 3));

        List<LeagueTableEntry> tableEntries = new LeagueTable(matches).getTableEntries();
        assertEquals("Liverpool", tableEntries.get(0).getTeamName());
        assertEquals("Atletico Madrid", tableEntries.get(1).getTeamName());
        assertEquals("FC Porto", tableEntries.get(2).getTeamName());
        assertEquals("AC Milan", tableEntries.get(3).getTeamName());
    }

    @Test
    public void TestLeagueTable_compareByGoalsDifference(){
        List<Match> matches = new ArrayList();
        matches.add(new Match("Spurs", "Norwich", 4, 2));
        matches.add(new Match("Chelsea", "Norwich", 3, 2));


        List<LeagueTableEntry> tableEntries = new LeagueTable(matches).getTableEntries();
        assertEquals("Spurs", tableEntries.get(0).getTeamName());
        assertEquals("Chelsea", tableEntries.get(1).getTeamName());
        assertEquals("Norwich", tableEntries.get(2).getTeamName());
    }

    @Test
    public void TestLeagueTable_compareGoalsFor(){
        List<Match> matches = new ArrayList();
        matches.add(new Match("Liverpool", "Arsenal", 4, 2));
        matches.add(new Match("Manchester City", "Arsenal", 3, 1));


        List<LeagueTableEntry> tableEntries = new LeagueTable(matches).getTableEntries();
        assertEquals("Liverpool", tableEntries.get(0).getTeamName());
        assertEquals("Manchester City", tableEntries.get(1).getTeamName());
        assertEquals("Arsenal", tableEntries.get(2).getTeamName());
    }

    @Test
    public void TestLeagueTable_compareAlphabetical(){
        List<Match> matches = new ArrayList();
        matches.add(new Match("Arsenal", "Bournemouth", 4, 0));
        matches.add(new Match("Manchester City", "Bournemouth", 4, 0));


        List<LeagueTableEntry> tableEntries = new LeagueTable(matches).getTableEntries();
        assertEquals("Arsenal", tableEntries.get(0).getTeamName());
        assertEquals("Manchester City", tableEntries.get(1).getTeamName());
        assertEquals("Bournemouth", tableEntries.get(2).getTeamName());
    }

    @Test
    public void TestLeagueTable_checkTable(){
        List<Match> matches = new ArrayList();
        matches.add(new Match("Arsenal", "Bournemouth", 4, 0));
        matches.add(new Match("Manchester City", "Bournemouth", 4, 0));
        matches.add(new Match("Arsenal", "Manchester City", 1, 1));


        List<LeagueTableEntry> tableEntries = new LeagueTable(matches).getTableEntries();
        assertEquals("Arsenal", tableEntries.get(0).getTeamName());
        assertEquals(2, tableEntries.get(0).getPlayed());
        assertEquals(1, tableEntries.get(0).getWon());
        assertEquals(1, tableEntries.get(0).getDrawn());
        assertEquals(0, tableEntries.get(0).getLost());
        assertEquals(5, tableEntries.get(0).getGoalsFor());
        assertEquals(1, tableEntries.get(0).getGoalsAgainst());
        assertEquals(4, tableEntries.get(0).getGoalDifference());
        assertEquals(4, tableEntries.get(0).getPoints());

        assertEquals("Manchester City", tableEntries.get(1).getTeamName());
        assertEquals(2, tableEntries.get(1).getPlayed());
        assertEquals(1, tableEntries.get(1).getWon());
        assertEquals(1, tableEntries.get(1).getDrawn());
        assertEquals(0, tableEntries.get(1).getLost());
        assertEquals(5, tableEntries.get(1).getGoalsFor());
        assertEquals(1, tableEntries.get(1).getGoalsAgainst());
        assertEquals(4, tableEntries.get(1).getGoalDifference());
        assertEquals(4, tableEntries.get(1).getPoints());

        assertEquals("Bournemouth", tableEntries.get(2).getTeamName());
        assertEquals(2, tableEntries.get(2).getPlayed());
        assertEquals(0, tableEntries.get(2).getWon());
        assertEquals(0, tableEntries.get(2).getDrawn());
        assertEquals(2, tableEntries.get(2).getLost());
        assertEquals(0, tableEntries.get(2).getGoalsFor());
        assertEquals(8, tableEntries.get(2).getGoalsAgainst());
        assertEquals(-8, tableEntries.get(2).getGoalDifference());
        assertEquals(0, tableEntries.get(2).getPoints());

    }

    @Test
    public void TestLeagueTable_homeIsSameAsAway_throwError(){
        List<Match> matches = new ArrayList();
        matches.add(new Match("Arsenal", "Arsenal", 4, 0));
        try {
            new LeagueTable(matches).getTableEntries();
        }catch (RuntimeException e) {
            assertEquals(e.getMessage(), "home team and away team cannot be the same");
        }
    }
}
