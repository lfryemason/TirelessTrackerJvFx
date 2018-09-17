import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchDataTest {

    @Test
    @DisplayName("makeResult test")
    void makeResult() {
        assertEquals("Win(2/0)", MatchData.makeResult(2,0,false));
    }

    @Test
    void parseResult() {
    }
}