import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;

public class Main extends Application {

    private BorderPane m_rootLayout;

    private Stage m_primaryStage;

    private MatchList m_matchList = new MatchList();

    private final static String G_SZMATCHLISTOUTPUTFILE = "res/data/matches.json";

    public Main()
    {
        try
        {
            m_matchList = JSONLoader.importMatchList(G_SZMATCHLISTOUTPUTFILE);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        m_primaryStage = primaryStage;
        m_primaryStage.setTitle("Tireless Tracker - Match tracker");

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
        m_rootLayout = (BorderPane) loader.load();

        if ( m_rootLayout.getPrefWidth() > width )
            m_rootLayout.setMaxWidth(width);
        if ( m_rootLayout.getPrefHeight() >= height )
            m_rootLayout.setPrefHeight(height - 100);

        Scene scene = new Scene(m_rootLayout);
        m_primaryStage.setScene(scene);
        showList();
        primaryStage.show();
    }

    @Override
    public void stop(){
        try
        {
            JSONLoader.exportJson(G_SZMATCHLISTOUTPUTFILE, m_matchList);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        // Save file
    }

    public void showList()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/TrackerMain.fxml"));
            SplitPane trackerMain = loader.load();

            m_rootLayout.setCenter(trackerMain);

            Controller controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public boolean showMatchEditDialog(MatchData match)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/MatchEditDialog.fxml"));
            BorderPane dialog = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Match");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(m_primaryStage);
            Scene scene = new Scene(dialog);
            dialogStage.setScene(scene);

            MatchEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMatchData(match);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public ObservableList<MatchData> getMatches()
    {
        return m_matchList.getMatches();
    }

    public MatchList getMatchList()
    {
        return m_matchList;
    }

    public Stage getPrimaryStage()
    {
        return m_primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
