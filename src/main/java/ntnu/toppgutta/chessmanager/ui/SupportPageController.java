package ntnu.toppgutta.chessmanager.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * This class is the controller class of the Support_Page.fxml.
 * Every function supportPage have is controlled here.
 *
 * @author Ole Kristian
 * @author Jørgen
 * @version 1.0
 */
public class SupportPageController{

    //Scenes
    private Scene homePageScene;
    private Scene newTournamentPageScene;

    /**
     * Mouse event on the BackButton, that chances scene to Home_Pane.fxml.
     *
     * @param mouseEvent the action you do with your mouse.
     */
    @FXML
    protected void supportPageHomeImageClick(MouseEvent mouseEvent){
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        this.setScene(primaryStage, this.homePageScene);
    }

    /**
     * Change scene to New_Tournament_Page.fxml.
     *
     * @param mouseEvent MouseEvent.
     */
    @FXML
    public void newTournamentPaneButton(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        this.setScene(primaryStage, this.newTournamentPageScene);
    }

    /**
     * Set up the Home_page.fxml as a scene.
     *
     * @param homePageScene the scene that you want set up.
     */
    public void setHomePageScene(Scene homePageScene) {
        this.homePageScene = homePageScene;
    }

    /**
     * Set up New_Tournament_Page as a scene.
     *
     * @param newTournamentPageScene the scene that you want set up.
     */
    public void setNewTournamentPageScene(Scene newTournamentPageScene) {
        this.newTournamentPageScene = newTournamentPageScene;
    }

    /**
     * Changes scene on a stage.
     *
     * @param primaryStage the stage you want scene change on.
     * @param newScene the scene you want the stage change to.
     */
    public void setScene(Stage primaryStage, Scene newScene) {
        primaryStage.hide();
        primaryStage.setScene(newScene);
        primaryStage.show();
    }
}
