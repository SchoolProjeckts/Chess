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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ntnu.toppgutta.chessmanager.model.Tournament;


/**
 * This class is the controller class of the Home_Page.fxml file.
 * Every function homePage have is controlled here.
 *
 * @author Ole Kristian
 * @author Jørgen
 * @version 1.0
 */
public class HomePageController implements Initializable {

    //Elements in fxml file
    @FXML
    private TableView<Tournament> tournamentList = new TableView<>();
    @FXML
    private TableColumn<Tournament, String> tournamentColumn = new TableColumn<>();

    //Scenes
    private Scene supportPageScene;
    private Scene newTournamentPageScene;
    private Scene selectedTournamentPageScene;

    //Controllers
    private SelectedTournamentPageController selectedTournamentPageController;
    private NewTournamentPageController newTournamentPageController;

    private Tournament tournament;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialize the tableView
        tournamentColumn.setCellValueFactory(new PropertyValueFactory<>("tournamentName"));
    }

    /**
     * Adds a tourmanet to the tournament list.
     *
     * @param tournament the tournament you want to add.
     */
    public void addTournamentToTournamentList(Tournament tournament) {
        tournamentList.getItems().add(tournament);
    }

    /**
     * Removes selected tournament form the tournament list rows.
     */
    public void removeTournamentFromTournamentList() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("REMOVE TOURNAMENT");
        alert.setHeaderText("YOU ARE ABOUT TO DELETE A TOURNAMENT!!!");
        alert.setContentText("If you delete the tournament you cant get it back, are you sure you want to delete it?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            ObservableList<Tournament> selectedRows;
            ObservableList<Tournament> allTournament;
            allTournament = tournamentList.getItems();
            selectedRows = tournamentList.getSelectionModel().getSelectedItems();

            for (Tournament tournament : selectedRows) {
                allTournament.remove(tournament);
                this.newTournamentPageController.getArrayListTournaments().remove(tournament);
            }
        }
    }

    /**
     * Set up the New_Tournament_Page.fxml as a scene.
     *
     * @param newTournamentPageScene the scene that you want set up.
     */
    public void setNewTournamentScene(Scene newTournamentPageScene) {
        this.newTournamentPageScene = newTournamentPageScene;
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
     * Set up the Support_Page.fxml as a scene.
     *
     * @param supportPageScene the scene that you want set up.
     */
    public void setHelpPageScene(Scene supportPageScene) {
        this.supportPageScene = supportPageScene;
    }

    /**
     * Mouse event on the Help pane, that chances scene to Support_Page.fxml.
     *
     * @param mouseEvent the action you do with your mouse.
     */
    @FXML
    protected void homePageHelpPaneClick(MouseEvent mouseEvent){
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        this.setScene(primaryStage, this.supportPageScene);
    }

    /**
     * Mouse event on the New Tournament pane, that chances scene to New_Tournament_Page.fxml.
     *
     * @param mouseEvent the action you do with your mouse.
     */
    @FXML
    protected void homePageNewTournamentPaneClick(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        this.setScene(primaryStage, this.newTournamentPageScene);
    }

    /**
     * Changes scene on a stage.
     *
     * @param primeryStage the stage you want scene change on.
     * @param newScene the scene you want the stage change to.
     */
    private void setScene(Stage primeryStage, Scene newScene) {
        primeryStage.hide();
        primeryStage.setScene(newScene);
        primeryStage.show();
    }

    /**
     * Open a tournament from the tournament list that is labeled when you press Open Match button.
     *
     * @param event ActionEvent.
     */
    @FXML
    protected void openTournament(ActionEvent event) {
        if(tournamentList.getSelectionModel().isEmpty()) {
            noSelectedTournamentMessageAlert();
        }
        this.tournament = tournamentList.getSelectionModel().getSelectedItem();
        selectedTournamentPageController.setLabels(tournamentList.getSelectionModel().getSelectedItem());
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.setScene(primaryStage, this.selectedTournamentPageScene );
    }

    /**
     * Creates a popup alert that informe that the user need to select a tournament or crate one.
     */
    public void noSelectedTournamentMessageAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ops something went wrong");
        alert.setHeaderText("No tournament was selected or the list is empty");
        alert.setContentText("You need to select a tournament from the tournament list, if you want to see a tournament. If there is non, the you need to crate one.");
        alert.show();
    }

    /**
     * Receive controllers from the other controller-classes.
     *
     * @param selectedTournamentPageController the selectedTournamentPageController that you receive.
     * @param newTournamentPageController The controller for the the newTournamentPage
     */
    public void receiveControllers(SelectedTournamentPageController selectedTournamentPageController, NewTournamentPageController newTournamentPageController){
        this.selectedTournamentPageController = selectedTournamentPageController;
        this.newTournamentPageController = newTournamentPageController;
    }

    /**
     * Returns the TableView that have the tournaments.
     *
     * @return TableView.
     */
    public TableView<Tournament> getTournamentList() {
        return this.tournamentList;
    }

    /**
     * Returns a tournament.
     *
     * @return tournament.
     */
    public Tournament getTournament() {
        return this.tournament;
    }
}
