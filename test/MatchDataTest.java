import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchDataTest {

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