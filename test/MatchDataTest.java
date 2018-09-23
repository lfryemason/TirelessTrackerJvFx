import javafx.css.Match;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MatchDataTest {
    @Test
    void createJson()
    {
        {
            MatchData match = new MatchData("D&T", "Tron", 2, 1, false, LocalDate.of(2018, 9, 9), "FNM");
            JSONObject json = match.createJson();
            assertEquals("D&T", json.get("deckName"));
            assertEquals(LocalDate.of(2018, 9, 9), json.get("date"));
            assertEquals(2, json.get("wins"));
        }
        {
            MatchData match = new MatchData("", "", 2, 1, false, LocalDate.of(2018, 9, 9), "");
            JSONObject json = match.createJson();
            assertEquals("", json.get("deckName"));
            assertEquals(LocalDate.of(2018, 9, 9), json.get("date"));
            assertEquals(2, json.get("wins"));
        }
        /** TODO: Add checks when creating json for null or missing fields
        {
            MatchData match = new MatchData("D&T", "Tron", 2, 1, false, null, "FNM");
            JSONObject json = match.createJson();
            assertEquals("D&T", json.get("deckName"));
            assertEquals(null, json.get("date"));
            assertEquals(2, json.get("wins"));
        }**/
    }

    @Test
    void makeMatchFromJson()
    {
        MatchData match = new MatchData("D&T", "Tron", 2, 1, false, LocalDate.of(2018, 9, 9), "FNM");
        JSONObject json = match.createJson();
        MatchData matchTest = MatchData.makeMatchFromJson(json);
        assertEquals(match.getDeckName(), matchTest.getDeckName());
        assertEquals(match.getOppName(), matchTest.getOppName() );
        assertEquals(match.getResult(), matchTest.getResult() );
        assertEquals(match.getDate(), matchTest.getDate() );
        assertEquals(match.getEventName(), matchTest.getEventName() );
    }

    @Test
    @DisplayName("makeResult test")
    void makeResult() {
        assertEquals("Win(2/0)", MatchData.makeResult(2,0,false));
        assertEquals("Draw(2/2)", MatchData.makeResult(2,2,false));
        assertEquals("Loss(1/2)", MatchData.makeResult(1,2,false));
        assertEquals("Draw(1/0)", MatchData.makeResult(1,0,true));
        assertEquals("Draw(0/0)", MatchData.makeResult(0,0,false));
        assertEquals("Loss(0/15)", MatchData.makeResult(0,15,false));
    }

    @Test
    void parseResult() {
        assertArrayEquals(new int[]{2,0,0} , MatchData.parseResult("Win(2/0)"));
        assertArrayEquals(new int[]{2,2,1} , MatchData.parseResult("Draw(2/2)"));
        assertArrayEquals(new int[]{1,2,0} , MatchData.parseResult("Loss(1/2)"));
        assertArrayEquals(new int[]{1,0,1} , MatchData.parseResult("Draw(1/0)"));
        assertArrayEquals(new int[]{0,0,1} , MatchData.parseResult("Draw(0/0)"));
        assertArrayEquals(new int[]{0,15,0} , MatchData.parseResult("Loss(0/15)"));
        assertArrayEquals(null , MatchData.parseResult("gibberish"));
    }
}