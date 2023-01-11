package ntnu.toppgutta.chessmanager.ui;

import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ntnu.toppgutta.chessmanager.model.Matchmaker;
import ntnu.toppgutta.chessmanager.model.Player;
import ntnu.toppgutta.chessmanager.model.Tournament;

/**
 * This class is the controller class of the New_Tournament_Page.fxml file.
 * Every function newTournamentPage have is controlled here.
 *
 * @author Ole Kristian
 * @author Jørgen
 */
public class NewTournamentPageController implements Initializable {

    //Elements in the fxml file
    @FXML
    private MFXDatePicker newTournamentDate;
    @FXML
    private TableView<Player> newTournamentPlayerList = new TableView<>();
    @FXML
    private TableColumn<Player, String> newTournamentTaableColumPlayers = new TableColumn<>();
    @FXML
    private TextField playerNameHere;
    @FXML
    private Label newTournamentNumberOfPlayers;
    @FXML
    private TextArea newTournamentInfo;
    @FXML
    private  MFXTextField tournamentName;

    //Controllerse
    private SelectedTournamentPageController selectedTournamentPageController;
    private HomePageController homePageController;

    //Scenes
    private Scene homePageScene;
    private Scene supportPageScene;
    private Scene selectedTournamentPageScene;

    private int numberOfPlayersInt = 0;
    private ArrayList<Tournament> arrayListTournaments = new ArrayList<>();
    private static final String ILLEGALARGUMENT = "Unable to create Tournament";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newTournamentTaableColumPlayers.setCellValueFactory(new PropertyValueFactory<>("Name"));
        newTournamentInfo.setWrapText(true);
    }

    /**
     * Set up the Home_page.fxml as a scene.
     * @param homePageScene the scene that you want set up.
     *
     */
    public void setHomePageScene(Scene homePageScene) {
        this.homePageScene = homePageScene;
    }

    /**
     * Set up the Support_Page.fxml as a scene.
     *
     * @param supportPageScene the scene that you want set up.
     */
    public void setSupportPageScene(Scene supportPageScene) {
        this.supportPageScene = supportPageScene;
    }

    /**
     * Set up the Selected_Tournament_Page.fxml as a scene.
     *
     * @param selectedTournamentPageScene the scene that you want set up.
     */
    public void setSelectedTournamentPageScene(Scene selectedTournamentPageScene) {
        this.selectedTournamentPageScene = selectedTournamentPageScene;
    }

    /**
     * Creats a player and add it to the tournament, and a tabeviewlist.
     *
     * Getting added when Add Player button is pressed, in New_Tournament_Page.
     */
    @FXML
    protected void newTournamentAddPlayerButtonPressed() {
        Player player = new Player(playerNameHere.getText());
        newTournamentPlayerList.getItems().add(player);
        playerNameHere.clear();
        numberOfPlayersInt = newTournamentPlayerList.getItems().size();
        newTournamentNumberOfPlayers.setText(Integer.toString(numberOfPlayersInt));
    }

    /**
     * Removes a player from tournament and tableView.
     *
     * Is removed when Remove button is pressed, in New_Tournament_Page.
     */
    @FXML
    protected void newTournamentRemovePlayerButton() {
        ObservableList<Player> selectedRows;
        ObservableList<Player> allPeople;
        allPeople = newTournamentPlayerList.getItems();
        selectedRows = newTournamentPlayerList.getSelectionModel().getSelectedItems();

        for (Player player : selectedRows) {
            allPeople.remove(player);
            numberOfPlayersInt = newTournamentPlayerList.getItems().size();
            newTournamentNumberOfPlayers.setText(Integer.toString(numberOfPlayersInt));
        }
    }

    /**
     * Creates a new Tournament based on the info from New_Tournament_Page.fxml.
     *
     * @return Returns the tournament created by the matchmaker.
     */
    public Tournament createTournament() {
        Matchmaker matchmaker = new Matchmaker();
        ArrayList<Player> players = new ArrayList<>();

        for(Player player : newTournamentPlayerList.getItems()) {
            players.add(player);
        }
        LocalDate localDate = newTournamentDate.getValue();
        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Tournament tournament = new Tournament(tournamentName.getText()
                ,players
                ,matchmaker.createMatches(players)
                ,date
                ,newTournamentInfo.getText());
        homePageController.addTournamentToTournamentList(tournament);
        arrayListTournaments.add(tournament);
        return tournament;
    }

    /**
     * Checks if the text in the name field have the same text as an already existing tournament.
     * Returns true if it already exists and false if not.
     *
     * @param name the text in the name field.
     * @return boolean statement.
     */
    public boolean checkIfNameAlreadyExists(String name) {
        boolean textExist = false;
        ObservableList<Tournament> allTournaments;
        allTournaments = homePageController.getTournamentList().getItems();
        for(Tournament tournament : allTournaments) {
            if(name.equals(tournament.getTournamentName())) {
                textExist = true;
            }
            else{
                textExist = false;
            }
        }
        return textExist;
    }

    /**
     * Mouse event on the Home pane, that chances scene to Home_Pane.fxml.
     *
     * @param mouseEvent the action you do with your mouse.
     */
    @FXML
    protected void newTournamentHomePagePaneClick(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        this.setScene(primaryStage, this.homePageScene);
    }

    /**
     * Creates a new tournament and change scene to Selected_Tournament_Pane.fxml.
     *
     * @param event when you pressed the button.
     */
    @FXML
    protected void newTournamentFinnishButtonPressed(ActionEvent event) {
        if(newTournamentPlayerList.getItems().size() < 2) {
            notEnoughPlayerAlert();
        }
        else if(tournamentName.getText().isBlank()) {
            noNameFilledAlert();
        }
        else if(newTournamentDate.getValue() == null) {
            noDatePickedAlert();
        }
        else if (checkIfNameAlreadyExists(tournamentName.getText())) {
            alreadyExistingNameAlert();
        }
        else {
            Tournament tournament = createTournament();
            // Sends the user-specified tournament to the selectedTournamentPageController
            selectedTournamentPageController.setLabels(tournament);

            //Clears the New_Tournament_Page fields
            tournamentName.clear();
            newTournamentInfo.clear();
            newTournamentPlayerList.getItems().clear();
            newTournamentPlayerList.refresh();
            newTournamentNumberOfPlayers.setText("0");
            playerNameHere.clear();
            newTournamentDate.clear();
            newTournamentDate.setValue(null);

            //Creates a new scene in the stage.
            //The Scene is selectedTournamentPage.
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            this.setScene(primaryStage, this.selectedTournamentPageScene);
        }
    }

    /**
     * Alert popup when try to crate match with only one player or lese.
     */
    public void notEnoughPlayerAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(ILLEGALARGUMENT);
        alert.setContentText("You need at least two players to crate a match");
        alert.show();
    }

    /**
     * Alert popup when try to crate a match when name is not filled inn.
     */
    public void noNameFilledAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(ILLEGALARGUMENT);
        alert.setContentText("Valid tournament name required to create tournament");
        alert.show();
    }

    /**
     * Alert popup when try to crate a match when it's not picked anny date.
     */
    public void noDatePickedAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(ILLEGALARGUMENT);
        alert.setContentText("Valid date required to create a tournament");
        alert.show();
    }

    /**
     * Alert popup when try to crate a match when the name you put on a tournament, already exists on another tournament.
     */
    public void alreadyExistingNameAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(ILLEGALARGUMENT);
        alert.setContentText("The name you want for your tournament already exist on another tournament");
        alert.show();
    }

    /**
     * Mouse event on the Help pane, that chances scene to New_Tournament_Page.fxml.
     *
     * @param mouseEvent the action you do with your mouse.
     */
    @FXML
    protected void newTournamentPageHelpPaneClick(MouseEvent mouseEvent){
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        this.setScene(primaryStage, this.supportPageScene);
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
     * Method for receive controller-objects from MainWindow.java.
     * Allows this object to access methods and fields stored in.
     * the other controller-objects.
     *
     * @param selectedTournamentPageController SelectedTournamentPageController
     * @param homePageController HomePageController
     */
    public void receiveControllers(SelectedTournamentPageController selectedTournamentPageController, HomePageController homePageController) {
        this.selectedTournamentPageController = selectedTournamentPageController;
        this.homePageController = homePageController;
    }

    /**
     * Returns a list of the tournaments in the program
     *
     * @return Returns a list of the current tournaments.
     */
    public List<Tournament> getArrayListTournaments() {
        return arrayListTournaments;
    }
}
