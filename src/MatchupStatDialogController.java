import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatchupStatDialogController
{
    @FXML
    private Label m_deckNameLabel;
    @FXML
    private Label m_oppDeckLabel;
    @FXML
    private PieChart m_pieChart;
    @FXML
    private TableView m_table;
    @FXML
    private TableColumn<MatchData, String> m_resultColumn;
    @FXML
    private TableColumn<MatchData, LocalDate> m_dateColumn;
    @FXML
    private TableColumn<MatchData, String> m_eventNameColumn;
    @FXML
    private Label m_matchesPlayedLabel;
    @FXML
    private Label m_numWinsLabel;
    @FXML
    private Label m_numLossesLabel;
    @FXML
    private Label m_numDrawsLabel;
    @FXML
    private Label m_gamesWonLabel;
    @FXML
    private Label m_gamesLostLabel;


    private Stage m_dialogStage;
    private MatchList.MatchupName m_matchup;
    private Stat m_stats;
    private ObservableList<MatchData> m_matches;

    @FXML
    private void initialize()
    {
        m_deckNameLabel.setText("");
        m_oppDeckLabel.setText("");
        m_matchesPlayedLabel.setText("");
        m_numWinsLabel.setText("");
        m_numLossesLabel.setText("");
        m_numDrawsLabel.setText("");
        m_gamesWonLabel.setText("");
        m_gamesLostLabel.setText("");

        m_resultColumn.setCellValueFactory(cellData -> cellData.getValue().resultProperty());
        m_dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        m_eventNameColumn.setCellValueFactory(cellData -> cellData.getValue().eventNameProperty());
    }

    public void setDialogStage(Stage dialogStage) {
        m_dialogStage = dialogStage;
    }

    public void setMatchup(MatchList.MatchupName matchup)
    {
        m_matchup = matchup;
        if ( matchup == null )
            return;
        m_deckNameLabel.setText(matchup.getDeckName());
        m_oppDeckLabel.setText(matchup.getOppName());
    }

    public void setStats(Stat stats)
    {
        m_stats = stats;
        if ( stats == null )
            return;

        m_matchesPlayedLabel.setText(Integer.toString(stats.getMatchesPlayed()));
        m_numWinsLabel.setText(Integer.toString(stats.getNumWins()));
        m_numDrawsLabel.setText(Integer.toString(stats.getNumDraws()));
        m_numLossesLabel.setText(Integer.toString(stats.getNumLosses()));
        m_gamesWonLabel.setText(Integer.toString(stats.getNumGameWins()));
        m_gamesLostLabel.setText(Integer.toString(stats.getNumGameLosses()));

        Controller.showPieChart(m_pieChart, stats);
    }

    public void setMatches (List<MatchData> matches)
    {
        m_matches = FXCollections.observableArrayList();
        m_matches.addAll(matches);

        m_table.setItems(m_matches);

        setStats(new Stat(m_matches));
    }

    @FXML
    private void handleOkButton()
    {
        m_dialogStage.close();
    }
}
