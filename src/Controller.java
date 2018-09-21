import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class Controller {
    @FXML
    private TableView<MatchData> m_tableView;
    @FXML
    private TableColumn<MatchData, String> m_deckNameColumn;
    @FXML
    private TableColumn<MatchData, String> m_oppNameColumn;
    @FXML
    private TableColumn<MatchData, String> m_resultColumn;
    @FXML
    private TableColumn<MatchData, LocalDate> m_dateColumn;
    @FXML
    private TableColumn<MatchData, String> m_eventNameColumn;

    @FXML
    private Label m_deckNameLabel;
    @FXML
    private Label m_oppNameLabel;
    @FXML
    private Label m_resultLabel;
    @FXML
    private Label m_dateLabel;
    @FXML
    private Label m_eventNameLabel;

    private Main m_main;

    public Controller()
    {
    }

    @FXML
    private void initialize()
    {
        m_deckNameColumn.setCellValueFactory(cellData -> cellData.getValue().deckNameProperty());
        m_oppNameColumn.setCellValueFactory(cellData -> cellData.getValue().oppNameProperty());
        m_resultColumn.setCellValueFactory(cellData -> cellData.getValue().resultProperty());
        m_dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        m_eventNameColumn.setCellValueFactory(cellData -> cellData.getValue().eventNameProperty());

        showMatchDetails(null);

        m_tableView.getSelectionModel().selectedItemProperty().addListener((obs,old,newM) -> showMatchDetails(newM));
    }

    public void setMain(Main main)
    {
        m_main = main;

        m_tableView.setItems(m_main.getMatches());
    }

    private void setOverallStats()
    {
        Stat overallStats = m_main.getMatchList().getStats();

    }

    private void showMatchDetails(MatchData matchData)
    {
        if ( matchData != null )
        {
            m_deckNameLabel.setText(matchData.getDeckName());
            m_oppNameLabel.setText(matchData.getOppName());
            m_resultLabel.setText(matchData.getResult());
            m_dateLabel.setText(matchData.getDate().toString());
            m_eventNameLabel.setText(matchData.getEventName());
        }
        else
        {
            m_deckNameLabel.setText("");
            m_oppNameLabel.setText("");
            m_resultLabel.setText("");
            m_dateLabel.setText("");
            m_eventNameLabel.setText("");
        }
    }

    @FXML
    private void handleDelete()
    {
        int selectedIndex = m_tableView.getSelectionModel().getSelectedIndex();
        if ( selectedIndex >= 0 )
            m_main.getMatchList().remove(selectedIndex);
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(m_main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Match Selected");
            alert.setContentText("Select a match in the table");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewMatch()
    {
        MatchData tempMatch = new MatchData();
        boolean okClicked = m_main.showMatchEditDialog(tempMatch);
        if (okClicked)
            m_main.getMatchList().add(tempMatch);
    }

    @FXML
    private void handleEditMatch()
    {
        MatchData selectedMatch = m_tableView.getSelectionModel().getSelectedItem();
        int selectedIndex = m_tableView.getSelectionModel().getSelectedIndex();
        if(selectedMatch != null)
        {
            boolean bOkClicked = m_main.showMatchEditDialog(selectedMatch);
            if (bOkClicked)
            {
                m_main.getMatchList().editMatch(selectedIndex, selectedMatch);
                showMatchDetails(selectedMatch);
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(m_main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}
