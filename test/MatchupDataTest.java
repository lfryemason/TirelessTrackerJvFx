import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchupDataTest {

    @Test
    @DisplayName("makeResult test")
    void makeResult() {
        assertEquals("Win(2/0)", MatchupData.makeResult(2,0,false));
    }

    @Test
    void parseResult() {
    }
}