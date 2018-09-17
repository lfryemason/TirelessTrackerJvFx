import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;

public class Main extends Application {

    private BorderPane m_rootLayout;

    private Stage m_primaryStage;

    private MatchList m_matchList = new MatchList();

    public Main()
    {
        m_matchList.add(new MatchupData("D&T", "Tron", 2, 1, false, LocalDate.of(2018,9,9), "FNM"));
        m_matchList.add(new MatchupData("D&T", "Affinity", 0, 2, false, LocalDate.of(2018,9,9), "FNM"));
        m_matchList.add(new MatchupData("D&T", "UW Control", 1, 2, false, LocalDate.of(2018,9,9), "FNM"));
        m_matchList.add(new MatchupData("D&T", "Jund", 2, 0, false, LocalDate.of(2018,9,9), "FNM"));
        m_matchList.add(new MatchupData("D&T", "Burn", 2, 0, false, LocalDate.of(2018,9,9), "FNM"));
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
        if ( m_rootLayout.getPrefHeight() > height )
            m_rootLayout.setMaxHeight(height);

        Scene scene = new Scene(m_rootLayout);
        m_primaryStage.setScene(scene);
        showList();
        primaryStage.show();
    }

    public void showList()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/TrackerMain.fxml"));
            HBox trackerMain = loader.load();

            m_rootLayout.setCenter(trackerMain);

            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            int width = gd.getDisplayMode().getWidth();
            int height = gd.getDisplayMode().getHeight();
            if ( trackerMain.getPrefWidth() > width )
                trackerMain.setMaxWidth(width);
            if ( trackerMain.getPrefHeight() > height )
                trackerMain.setMaxHeight(height - 25);

            Controller controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public boolean showMatchEditDialog(MatchupData match)
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

    public ObservableList<MatchupData> getMatches()
    {
        return m_matchList.getMatches();
    }

    public Stage getPrimaryStage()
    {
        return m_primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
