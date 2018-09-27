import org.json.JSONArray;

import java.io.*;
import java.nio.Buffer;

public class JSONLoader
{

    public static void exportJson(String fileName, MatchList matchList)
            throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        JSONArray json = matchList.createJsonArray();
        writer.write(json.toString());

        writer.close();
    }

    public static MatchList importMatchList(String fileName)
        throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String fileContents = reader.readLine();
        JSONArray json = new JSONArray(fileContents);
        return MatchList.makeMatchListFromJsonArray(json);
    }

}
