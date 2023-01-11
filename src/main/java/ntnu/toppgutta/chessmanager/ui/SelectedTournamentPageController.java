package ntnu.toppgutta.chessmanager.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ntnu.toppgutta.chessmanager.model.Match;
import ntnu.toppgutta.chessmanager.model.Player;
import ntnu.toppgutta.chessmanager.model.Tournament;


/**
 * This class is the controller class of the Selected_Tournament_page.fxml.
 * Every function in selectedTournamentPage is controlled here.
 */
public class SelectedTournamentPageController implements Initializable {

    //Elements in the fxml file
    @FXML
    private Label name = new Label();
    @FXML
    private Label date = new Label();
    @FXML
    private Label numberOfPlayers = new Label();
    @FXML
    private TextArea info = new TextArea();
    @FXML
    private TableView<Match> matches = new TableView<>();
    @FXML
    private TableColumn<Match, String> matchesColum = new TableColumn<>();
    @FXML
    private TableView<Player> scoreBoard = new TableView<>();
    @FXML
    private TableColumn<Player, String> nameColumn = new TableColumn<>();
    @FXML
    private TableColumn<Player, String> matcesPlaydedColum = new TableColumn<>();
    @FXML
    private TableColumn<Player, String> winColumn = new TableColumn<>();
    @FXML
    private TableColumn<Player, String> tieColumn = new TableColumn<>();
    @FXML
    private TableColumn<Player, String> loseColumn = new TableColumn<>();
    @FXML
    private TableColumn<Player, String> pointColumn = new TableColumn<>();

    //Scene
    private Scene homePageScene;
    private Scene setScorePageScene;

    //Controllers
    private SetScoreController setScoreController;
    private HomePageController homePageController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Sett up the different TableViews
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        this.matcesPlaydedColum.setCellValueFactory(new PropertyValueFactory<>("MatchesPlayedAsString"));
        this.winColumn.setCellValueFactory(new PropertyValueFactory<>("WinsAsString"));
        this.tieColumn.setCellValueFactory(new PropertyValueFactory<>("TieAsString"));
        this.loseColumn.setCellValueFactory(new PropertyValueFactory<>("LoseAsString"));
        this.pointColumn.setCellValueFactory(new PropertyValueFactory<>("PointsAsString"));
        this.matchesColum.setCellValueFactory(new PropertyValueFactory<>("MatchAsString"));
        //Wrap the info text
        this.info.setWrapText(true);
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
     * Set up Score_Page.fxml as a scene.
     *
     * @param scorePageScene the scene that you want set up.
     */
    public void setScorePageScene(Scene scorePageScene) {
        this.setScorePageScene = scorePageScene;
    }

    /**
     * Change the scene to Home_Page.fxml.
     *
     * @param event ActionEvent.
     */
    @FXML
    protected void selectedTournamentFinnishButtonPressed(ActionEvent event) {
        scoreBoard.getItems().clear();
        matches.getItems().clear();
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.setScene(primaryStage, this.homePageScene);
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
     * Sets up tha labels in the fxml files with the info from a tournament.
     *
     * @param tournament the tournament you want to have the info from to set up the labels.
     */
    public void setLabels(Tournament tournament) {
        this.name.setText(tournament.getTournamentName());
        this.date.setText(tournament.getDate());
        this.numberOfPlayers.setText(tournament.getNumberOfplayers() + "");
        this.info.setEditable(true);
        this.info.setText(tournament.getInfo());
        this.info.setEditable(false);
        setMatches(tournament);
        setScoreBoard(tournament);
    }

    /**
     * Set players in the scoreBoard.
     *
     * @param tournament the tournament you want to get players from.
     */
    public void setScoreBoard(Tournament tournament){
        for(Player player : tournament.getPlayers()) {
            scoreBoard.getItems().add(player);
        }
    }

    /**
     * Set matches in matches list.
     *
     * @param tournament the tournament you want to get matches from.
     */
    public void setMatches(Tournament tournament) {
        for(Match match : tournament.getTournamentMatches()) {
            matches.getItems().add(match);
        }
    }

    /**
     * Change scene to Set_Score_Page.fxml.
     *
     * @param event ActionEvent.
     */
    @FXML
    protected void setScore(ActionEvent event) {
        if(this.scoreBoard.getSelectionModel().isEmpty()) {
            noSelectedPlayerAlert();
        }
        else {
            setScoreController.getPlayer(this.scoreBoard.getSelectionModel().getSelectedItem());
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            setScene(primaryStage,this.setScorePageScene);
        }
    }

    /**
     * Remove selected match for mach list.
     *
     * Happen when Remove Match button is pressed.
     */
    @FXML
    protected void removeMachButton() {
        if(matches.getItems().size() == 1) {
            cantRemoveLastMatchAlert();
        }
        ObservableList<Match> selectedRows;
        ObservableList<Match> allMatches;
        allMatches = matches.getItems();
        selectedRows = matches.getSelectionModel().getSelectedItems();
        for(Match match : selectedRows) {
            allMatches.remove(match);
        }
        if(homePageController.getTournament() != null) {
            for(Match match : homePageController.getTournament().getTournamentMatches()) {
                Match match1 = matches.getSelectionModel().getSelectedItem();
                if(match1.getMatchAsString().equals(match.getMatchAsString())) {
                    homePageController.getTournament().getTournamentMatches().remove(match);
                }
            }
        }
        else{
            for(Tournament tournament : homePageController.getTournamentList().getItems()) {
                if(tournament.getTournamentName().equals(this.name.getText())) {
                    Match match1 = matches.getSelectionModel().getSelectedItem();
                    for(Match match : tournament.getTournamentMatches()) {
                        if(match1.getMatchAsString().equals(match.getMatchAsString())) {
                            tournament.getTournamentMatches().remove(match);
                        }
                    }
                }
            }
        }
    }

    /**
     * Popup alert that tels the user that they need to select a player form the scoreboard.
     */
    public void noSelectedPlayerAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No player selected");
        alert.setContentText("Please select a player if you wish to edit");
        alert.show();
    }

    /**
     * Popup alert that tels the user that they can't remove the last match.
     */
    public void cantRemoveLastMatchAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Can't remove match");
        alert.setContentText("The tournament must contain at least 1 match.");
        alert.show();
    }

    /**
     * Receive controllers.
     *
     * @param setScoreController setScoreController.
     * @param homePageController homePageController
     */
    public void receiveController(SetScoreController setScoreController, HomePageController homePageController) {
        this.setScoreController = setScoreController;
        this.homePageController = homePageController;
    }

    /**
     * Return the table view.
     *
     * @return TableView.
     */
    public TableView<Player> getTableView() {
        return this.scoreBoard;
    }
}
