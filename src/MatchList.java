import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MatchList {
    private ObservableList<MatchData> m_matches = FXCollections.observableArrayList();

    private Map<MatchupName, List<MatchData>> m_mapMatchups;

    public MatchList()
    {
        m_mapMatchups = new HashMap<>();
    }

    public void add(MatchData match)
    {
        m_matches.add(match);
        MatchupName matchup = new MatchupName(match);
        if ( m_mapMatchups.get(matchup) == null )
            m_mapMatchups.put(matchup, new LinkedList<>());
        m_mapMatchups.get(matchup).add(match);
    }

    public ObservableList<MatchData> getMatches()
    {
        return m_matches;
    }


    public class MatchupName {

        private final String deckName;
        private final String oppName;

        public MatchupName(String deckName, String oppName) {
            this.deckName = deckName;
            this.oppName = oppName;
        }

        public MatchupName(MatchData match)
        {
            this(match.getDeckName(), match.getOppName());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MatchupName)) return false;
            MatchupName matchupName = (MatchupName) o;
            return deckName.equals(matchupName.deckName) && oppName.equals(matchupName.oppName);
        }

        @Override
        public int hashCode() {
            int result = deckName.hashCode();
            result = 31 * result + oppName.hashCode();
            return result;
        }

    }
}