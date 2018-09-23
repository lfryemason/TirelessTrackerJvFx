import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StatTest {
    @Test
    void generateStats()
    {
    }

    @Test
    void addMatch()
    {
        {
            Stat stat = new Stat();
            stat.addMatch(new MatchData("D&T", "Tron", 2, 1, false, LocalDate.of(2018,9,9), "FNM"));
            assertEquals(1, stat.getMatchesPlayed());
            assertEquals(1, stat.getNumWins());
            assertEquals(100, stat.getWinPerc());
        }
        {
            Stat stat = new Stat();
            assertEquals(0, stat.getMatchesPlayed());
            assertEquals(0, stat.getNumWins());
            assertEquals(0, stat.getWinPerc());
        }
        {
            Stat stat = new Stat();
            for ( int i = 1; i <= 10000; i++ )
            {
                stat.addMatch(new MatchData("D&T", "Tron", i % 3, 1, false, LocalDate.of(2018,9,9), "FNM"));
            }
            assertEquals(10000, stat.getMatchesPlayed());
            assertTrue( stat.getWinPerc() > 0 && stat.getWinPerc() < 100 );
            assertEquals( 10000, stat.getNumGameWins() );
            assertEquals(10000/3, stat.getNumWins() );
        }
    }

    @Test
    void removeMatch()
    {
        {
            Stat stat = new Stat();
            stat.addMatch(new MatchData("D&T", "Tron", 2, 1, false, LocalDate.of(2018,9,9), "FNM"));
            stat.removeMatch(new MatchData("D&T", "Tron", 2, 1, false, LocalDate.of(2018,9,9), "FNM"));
            assertEquals(0, stat.getMatchesPlayed(), "Matches played");
            assertEquals(0, stat.getNumWins(), "Number of wins");
            assertEquals(0, stat.getWinPerc(), "Win percentage");
        }
        {
            Stat stat = new Stat();
            stat.removeMatch(new MatchData("D&T", "Tron", 2, 1, false, LocalDate.of(2018,9,9), "FNM"));
            assertEquals(0, stat.getMatchesPlayed(), "Matches played");
            assertEquals(0, stat.getNumWins(), "Number of wins");
            assertEquals(0, stat.getWinPerc(), "Win percentage");
        }
    }

}