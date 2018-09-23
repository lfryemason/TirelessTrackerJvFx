import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MatchListTest {
    @Test
    void createJsonArray()
    {
        {
            MatchList matchList = new MatchList();
            matchList.add(new MatchData("D&T", "Tron", 2, 1, false, LocalDate.of(2018, 9, 9), "FNM"));
            JSONArray json = matchList.createJsonArray();
            assertEquals("D&T", json.getJSONObject(0).get("deckName"));
            assertEquals(LocalDate.of(2018, 9, 9), json.getJSONObject(0).get("date"));
            assertEquals(2, json.getJSONObject(0).get("wins"));
            assertTrue( json.isNull(1) == true );
        }
    }

    @Test
    void makeMatchListFromJsonArray()
    {
        MatchList matchList = new MatchList();
        matchList.add(new MatchData("D&T", "Tron", 2, 1, false, LocalDate.of(2018,9,9), "FNM"));
        matchList.add(new MatchData("D&T", "Affinity", 0, 2, false, LocalDate.of(2018,9,9), "FNM"));
        matchList.add(new MatchData("D&T", "UW Control", 1, 2, false, LocalDate.of(2018,9,9), "FNM"));
        matchList.add(new MatchData("D&T", "Jund", 2, 0, false, LocalDate.of(2018,9,9), "FNM"));
        matchList.add(new MatchData("D&T", "Tron", 2, 0, false, LocalDate.of(2018,9,9), "FNM"));
        JSONArray json = matchList.createJsonArray();
        MatchList matchTest = MatchList.makeMatchListFromJsonArray(json);
        Stat stat = matchList.getStats();
        Stat statTest = matchTest.getStats();
        assertEquals(stat.getMatchesPlayed(), statTest.getMatchesPlayed() );
        assertEquals(stat.getWinPerc(), statTest.getWinPerc() );
        assertEquals(stat.getNumWins(), statTest.getNumWins() );
        assertEquals(stat.getGameWinPerc(), statTest.getGameWinPerc() );
        assertEquals(stat.getNumGamesPlayed(), statTest.getNumGamesPlayed() );
        assertEquals(matchList.getMatches().size(), matchTest.getMatches().size());
    }

}