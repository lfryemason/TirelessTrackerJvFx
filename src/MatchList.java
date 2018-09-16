import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MatchList {
    private ObservableList<MatchupData> m_matches = FXCollections.observableArrayList();

    public MatchList()
    {

    }

    public void add(MatchupData match)
    {
        m_matches.add(match);
    }

    public ObservableList<MatchupData> getMatches()
    {
        return m_matches;
    }
}
