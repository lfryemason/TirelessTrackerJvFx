import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class MatchEditDialogController {
    @FXML
    private TextField m_deckNameField;
    @FXML
    private TextField m_oppNameField;
    @FXML
    private TextField m_winsField;
    @FXML
    private TextField m_lossesField;
    @FXML
    private CheckBox m_drawCheckbox;
    @FXML
    private DatePicker m_datePicker;
    @FXML
    private TextField m_eventNameField;

    private Stage m_dialogStage;
    private MatchData m_matchData;
    private boolean m_bOkClicked = false;

    @FXML
    private void initialize()
    {
    }

    public void setDialogStage(Stage dialogStage) {
        m_dialogStage = dialogStage;
    }

    public void setMatchData(MatchData match)
    {
        m_matchData = match;

        m_deckNameField.setText(m_matchData.getDeckName());
        m_oppNameField.setText(m_matchData.getOppName());
        int[] results = MatchData.parseResult(m_matchData.getResult());
        if ( results != null)
        {
            m_winsField.setText(Integer.toString(results[0]));
            m_lossesField.setText(Integer.toString(results[1]));
            m_drawCheckbox.setSelected(results[2] == 1);
        }
        m_datePicker.setValue(m_matchData.getDate());
        m_eventNameField.setText(m_matchData.getEventName());
    }

    public boolean isOkClicked()
    {
        return m_bOkClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            m_matchData.setDeckName(m_deckNameField.getText());
            m_matchData.setOppName(m_oppNameField.getText());
            int iWins = Integer.parseInt(m_winsField.getText());
            int iLosses = Integer.parseInt(m_lossesField.getText());
            boolean bDraw = m_drawCheckbox.isSelected();
            m_matchData.setResult(MatchData.makeResult(iWins, iLosses, bDraw));
            m_matchData.setDate(m_datePicker.getValue());
            m_matchData.setEventName(m_eventNameField.getText());

            m_bOkClicked = true;
            m_dialogStage.close();
        }
    }


    @FXML
    private void handleCancel() {
         m_dialogStage.close();
    }

    private boolean isInputValid()
    {
        String szErrorMessage = "";
        if ( m_deckNameField.getText() == null || m_deckNameField.getText().length() == 0 )
        {
            szErrorMessage += "No Valid deck name\n";
        }
        if ( m_oppNameField.getText() == null || m_oppNameField.getText().length() == 0 )
        {
            szErrorMessage += "No Valid opponent's deck\n";
        }
        try {
            Integer.parseInt(m_winsField.getText());
            Integer.parseInt(m_lossesField.getText());
        } catch (NumberFormatException e)
        {
            szErrorMessage += "Please enter numbers in wins and losses fields";
        }
        if ( m_eventNameField.getText() == null || m_eventNameField.getText().length() == 0 )
        {
            m_eventNameField.setText("");
        }

        if (szErrorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(m_dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(szErrorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
