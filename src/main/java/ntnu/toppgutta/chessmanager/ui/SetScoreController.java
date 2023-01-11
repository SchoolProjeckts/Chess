package ntnu.toppgutta.chessmanager.ui;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import ntnu.toppgutta.chessmanager.model.Player;

/**
 * This class is the controller class of the Set_Score_Page.fxml.
 * Every function setSCorePage have is controlled here.
 *
 * @author Ole Kristian
 * @author Jørgen
 */
public class SetScoreController {

    //Elements in the fxml file.
    @FXML
    private MFXTextField matchPlayed = new MFXTextField();
    @FXML
    private MFXTextField wins = new MFXTextField();
    @FXML
    private MFXTextField tie = new MFXTextField();
    @FXML
    private MFXTextField lose = new MFXTextField();
    @FXML
    private MFXTextField points = new MFXTextField();

    //Scenes
    private Scene selectedTournamentPageScene;

    //Controllers
    private SelectedTournamentPageController selectedTournamentPageController;

    private Player player = null;

    /**
     * Set up the Selected_Tournament_Page.fxml as a scene.
     *
     * @param selectedTournamentPageScene the scene that you want set up.
     */
    public void setSelectedTournamentPageScene(Scene selectedTournamentPageScene) {
        this.selectedTournamentPageScene = selectedTournamentPageScene;
    }

    /**
     * Change scene to Selected_Tournament_Page.fxml.
     *
     * @param event ActionEvent.
     */
    @FXML
    protected void cancelButton(ActionEvent event) {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.setScene(primaryStage, this.selectedTournamentPageScene);
    }

    /**
     * Change scene to Selected_Tournament_Page.fxml.
     * Also changes the score of a player.
     *
     * @param event ActionEvent.
     */
    @FXML
    protected void okButton(ActionEvent event) {
        if(this.matchPlayed.getText().contains("-") || this.wins.getText().contains("-") || this.tie.getText().contains("-") || this.lose.getText().contains("-") || this.points.getText().contains("-")) {
            notANumberOrEmptyFileAlert();
        }
        else if(!isNumeric(this.matchPlayed.getText()) || !isNumeric(this.wins.getText()) || !isNumeric(this.tie.getText()) || !isNumeric(this.lose.getText()) || !isNumeric(this.points.getText())) {
            notANumberOrEmptyFileAlert();
        }
        else {
            setPlayerScore();
            this.selectedTournamentPageController.getTableView().refresh();
            clearTextFileds();
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            this.setScene(primaryStage, this.selectedTournamentPageScene);
        }
    }

    /**
     * Changes scene on a stage.
     *
     * @param primaryStage the stage you want scene change on.
     * @param newScene the scene you want the stage change to.
     */
    private void setScene(Stage primaryStage, Scene newScene) {
        primaryStage.hide();
        primaryStage.setScene(newScene);
        primaryStage.show();
    }

    /**
     * Set a new score for a player based on what is written in, in the text fields.
     */
    public void setPlayerScore() {
            this.player.setMatchesPlayed(Integer.parseInt(this.matchPlayed.getText()));
            this.player.setWins(Integer.parseInt(this.wins.getText()));
            this.player.setTie(Integer.parseInt(this.tie.getText()));
            this.player.setLose(Integer.parseInt(this.lose.getText()));
            this.player.setPoints(Double.parseDouble(this.points.getText()));
    }

    /**
     * Popup alert that tels the user that what is and is not correct input in the text fields.
     */
    public void notANumberOrEmptyFileAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Invalid score");
        alert.setContentText("All fields needs to have a valid number value");
        alert.show();
    }

    /**
     * Checks if the String is a number or not.
     *
     * @param str the String you want to check.
     * @return boolean statement.
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * Clare's all the text fields.
     */
    public void clearTextFileds() {
        this.matchPlayed.clear();
        this.wins.clear();
        this.tie.clear();
        this.lose.clear();
        this.points.clear();
    }

    /**
     * Gets a player.
     *
     * @param player the player you want to get.
     */
    public void getPlayer(Player player) {
        this.player = player;
    }

    /**
     * Receive controllers.
     *
     * @param selectedTournamentPageController selectedTournamentController.
     */
    public void receiveController(SelectedTournamentPageController selectedTournamentPageController) {
        this.selectedTournamentPageController = selectedTournamentPageController;
    }
}
