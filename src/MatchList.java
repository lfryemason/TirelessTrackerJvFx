import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MatchList {
    private ObservableList<MatchData> m_matches = FXCollections.observableArrayList();

    public MatchList()
    {

    }

    public void add(MatchData match)
    {
        m_matches.add(match);
    }

    public ObservableList<MatchData> getMatches()
    {
        return m_matches;
    }
}
