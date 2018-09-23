import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.json.JSONObject;

import java.time.LocalDate;

public class MatchData
{

    private final StringProperty m_deckName;
    private final StringProperty m_oppName;
    private final StringProperty m_result;
    private final ObjectProperty<LocalDate> m_date;
    private final StringProperty m_eventName;

    public MatchData()
    {
        this(null,null,0,0,false,null,null);
    }

    public MatchData(String deckName, String oppName, int wins, int losses, boolean draw, LocalDate date, String eventName)
    {
        m_deckName = new SimpleStringProperty(deckName);
        m_oppName = new SimpleStringProperty(oppName);
        m_result = new SimpleStringProperty(makeResult(wins, losses, draw));
        m_date = new SimpleObjectProperty(date);
        m_eventName = new SimpleStringProperty(eventName);
    }

    public MatchData(MatchData match)
    {
        m_deckName = new SimpleStringProperty(match.getDeckName());
        m_oppName = new SimpleStringProperty(match.getOppName());
        m_result = new SimpleStringProperty(match.getResult());
        m_date = new SimpleObjectProperty(match.getDate());
        m_eventName = new SimpleStringProperty(match.getEventName());
    }

    public static String makeResult(int wins, int losses, boolean draw)
    {
        String result = "";
        if ( draw )
            result = "Draw";
        else if ( wins > losses )
            result = "Win";
        else if ( losses > wins )
            result = "Loss";
        else
            result = "Draw";

        return result + "(" + wins + "/" + losses + ")";
    }

    public JSONObject createJson()
    {
        JSONObject result = new JSONObject();
        result.put("deckName", getDeckName());
        result.put("oppName", getOppName());
        int[] res = parseResult(getResult());
        result.put("wins", res[0]);
        result.put("losses", res[1]);
        result.put("draw", res[2] == 1);
        result.put("date", getDate());
        result.put("eventName", getEventName());
        return result;
    }

    public String getDeckName()
    {
        return m_deckName.get();
    }

    public void setDeckName(String deckName)
    {
        m_deckName.set(deckName);
    }

    public StringProperty deckNameProperty()
    {
        return m_deckName;
    }

    public String getOppName()
    {
        return m_oppName.get();
    }

    public void setOppName(String oppName)
    {
        m_oppName.set(oppName);
    }

    public StringProperty oppNameProperty()
    {
        return m_oppName;
    }

    public String getResult()
    {
        return m_result.get();
    }

    public void setResult(String result)
    {
        m_result.set(result);
    }

    public StringProperty resultProperty()
    {
        return m_result;
    }

    public LocalDate getDate()
    {
        return m_date.get();
    }

    public void setDate(LocalDate date)
    {
        m_date.set(date);
    }

    public ObjectProperty<LocalDate> dateProperty()
    {
        return m_date;
    }

    public String getEventName()
    {
        return m_eventName.get();
    }

    public void setEventName(String eventName)
    {
        m_eventName.set(eventName);
    }

    public StringProperty eventNameProperty()
    {
        return m_eventName;
    }

    public static int[] parseResult(String resultString)
    {
        String delims = "[(/)]";
        String[] splitString = resultString.split(delims);

        if ( splitString.length != 3 || ! (
                splitString[0].equals("Draw")
             || splitString[0].equals("Win")
             || splitString[0].equals("Loss")) )
            return null;

        int[] result = new int[3];
        try {
            result[0] = Integer.parseInt(splitString[1]);
            result[1] = Integer.parseInt(splitString[2]);
        }
        catch (NumberFormatException e)
        {
            return null;
        }

        if ( splitString[0].equals("Draw") )
            result[2] = 1;
        else
            result[2] = 0;

        return result;
    }

}
