package ntnu.toppgutta.chessmanager.ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import ntnu.toppgutta.chessmanager.model.JsonFileHandler;
import ntnu.toppgutta.chessmanager.model.Tournament;


/**
 * This class sets up all the different scenes with the different fxml files.
 * The controller to the different fxml files is also set up.
 *
 * @author Ole Kristian
 * @author Jørgen
 */
public class MainWindow extends Application {

    //Scenes
    private Scene homePageScene;
    private Scene newTournamentPageScene;
    private Scene selectedTournamentPageScene;
    private Scene supportPageScene;
    private Scene setScorePageScene;

    //Controllers
    private NewTournamentPageController newTournamentPageController;
    private SelectedTournamentPageController selectedTournamentPageController;
    private SupportPageController supportPageController;
    private HomePageController homePageController;
    private SetScoreController setScoreController;

    /**
     * Returns the newTournamentPageController.
     *
     * @return newTournamentPageController.
     */
    public NewTournamentPageController getNewTournamentPageController() {
        return this.newTournamentPageController;
    }

    /**
     * Returns the selectedTournamentPageController.
     *
     * @return selectedTournamentPageController.
     */
    public SelectedTournamentPageController getSelectedTournamentPageController() {
        return this.selectedTournamentPageController;
    }

    /**
     * Returns the supportPageController.
     *
     * @return supportPageController.
     */
    public SupportPageController getSupportPageController() {
        return this.supportPageController;
    }

    /**
     * Returns the homePageController.
     *
     * @return homePageController.
     */
    public HomePageController getHomePageController() {
        return this.homePageController;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Loder for Home_Page.fxml
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("Home_Page.fxml"));
        Parent homePagePane = homePageLoader.load();
        this.homePageScene = new Scene(homePagePane, 1440, 800);

        //Loder for New_Tournament_Page.fxml
        FXMLLoader newTournamentPageLoader = new FXMLLoader(getClass().getResource("New_Tournament_Page.fxml"));
        Parent newTournamentPagePane = newTournamentPageLoader.load();
        this.newTournamentPageScene = new Scene(newTournamentPagePane, 1440, 800);
        newTournamentPageController = newTournamentPageLoader.getController();
        newTournamentPageController.setHomePageScene(this.homePageScene);

        //Loder for Selected_Tournament_Page.fxml
        FXMLLoader selectedTournamentPageLoader = new FXMLLoader(getClass().getResource("Selected_Tournament_Page.fxml"));
        Parent selectedTournamentPageParent = selectedTournamentPageLoader.load();
        this.selectedTournamentPageScene = new Scene(selectedTournamentPageParent, 1440, 800);
        selectedTournamentPageController = selectedTournamentPageLoader.getController();
        selectedTournamentPageController.setHomePageScene(this.homePageScene);

        //Loder for Support_Page.fxml
        FXMLLoader supportPageLoader = new FXMLLoader(getClass().getResource("Support_Page.fxml"));
        Parent supportPageParent = supportPageLoader.load();
        this.supportPageScene = new Scene(supportPageParent, 1440,800);
        supportPageController = supportPageLoader.getController();
        supportPageController.setHomePageScene(this.homePageScene);
        supportPageController.setNewTournamentPageScene(this.newTournamentPageScene);

        //Lder for Set_Score_Page.fxml
        FXMLLoader setScorePageLoder = new FXMLLoader(getClass().getResource("Set_Score_Page.fxml"));
        Parent setScorePageParent = setScorePageLoder.load();
        this.setScorePageScene = new Scene(setScorePageParent, 600,400);
        setScoreController = setScorePageLoder.getController();

        //Gets the different controllers in homePageController class
        homePageController = homePageLoader.getController();
        homePageController.setNewTournamentScene(this.newTournamentPageScene);
        homePageController.setHelpPageScene(this.supportPageScene);
        homePageController.setSelectedTournamentPageScene(this.selectedTournamentPageScene);

        //Sets up the different controllers in newTournamentPageController class
        newTournamentPageController.setSupportPageScene(this.supportPageScene);
        newTournamentPageController.setSelectedTournamentPageScene(this.selectedTournamentPageScene);

        //Sets up the different controllers in selectedTournamentPageController class
        selectedTournamentPageController.setScorePageScene(this.setScorePageScene);

        //Sets up the different controllers in setScorePageController class
        setScoreController.setSelectedTournamentPageScene(this.selectedTournamentPageScene);

        //Sends the controller-objects to the newTournamentPageController object, which
        //allows the controller to access the other controllers methods. It initializes
        //NewTournamentPageControllers fields representing each controller.
        newTournamentPageController.receiveControllers(this.selectedTournamentPageController, this.homePageController);
        homePageController.receiveControllers(this.selectedTournamentPageController, this.newTournamentPageController);
        selectedTournamentPageController.receiveController(this.setScoreController, this.homePageController);
        setScoreController.receiveController(this.selectedTournamentPageController);

        //Sets up the first scene homePageScene that is shown in the stage.
        populateTournamentListWithSavedTournaments();
        primaryStage.setResizable(false);
        primaryStage.setScene(this.homePageScene);
        primaryStage.show();

        //Sets up what happens when the exit button is pressed
        primaryStage.setOnCloseRequest(event ->{
            event.consume();
            saveTournaments();
            exitApplication(primaryStage);
        });
    }

    /**
     * Close the application when the x button is pressed.
     * Creates a popup that asks if you want to close the application or not.
     *
     * @param stage the stage where the scenes are.
     */
    @FXML
    protected void exitApplication(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to exit Tournament Manager!");
        alert.setContentText("Are you sure you want to exit?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }

    /**
     * Saves the tournaments when the app closes.
     */
    public void saveTournaments() {
        JsonFileHandler jsonFileHandler = new JsonFileHandler();
        jsonFileHandler.createJsonFileWithListOfTournaments("Tournaments.json", this.newTournamentPageController.getArrayListTournaments());
    }

    /**
     * Create and populate the tournament list with tournaments that was saved before the app closed.
     */
    public void populateTournamentListWithSavedTournaments() {
        JsonFileHandler jsonFileHandler = new JsonFileHandler();
        jsonFileHandler.createTournamentListFromJsonFile("Tournaments.json");
        if(jsonFileHandler.getTournaments() != null) {
            for (Tournament tournament : jsonFileHandler.getTournaments()) {
                this.homePageController.getTournamentList().getItems().add(tournament);
                this.newTournamentPageController.getArrayListTournaments().add(tournament);
            }
        }
    }

    /**
     * Starts the application.
     *
     * @param args The arguments to be run by the program.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
