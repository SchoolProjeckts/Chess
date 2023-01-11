package ntnu.toppgutta.chessmanager.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the handling of json files.
 * Json files is used to save tournaments.
 *
 * @author Ole Kristian
 * @version 1.0
 */
public class JsonFileHandler {

    List<Tournament> tournaments = new ArrayList<>();

    /**
     * Creates a json file with a list of tournaments.
     *
     * @param filename the file name of the json file.
     *
     * @param tournaments list of tournaments in the json file.
     */
    public void createJsonFileWithListOfTournaments(String filename,List<Tournament> tournaments) {
        try(PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(tournaments);
            out.write(jsonString);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Read from a json file and creates a list of tournaments.
     *
     * @param fileName the json file you want to read from.
     */
    public void createTournamentListFromJsonFile(String fileName) {
        Gson gson = new Gson();
        try(Reader reader = new FileReader(fileName)) {
            this.tournaments = gson.fromJson(reader, new TypeToken<ArrayList<Tournament>>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Returns a list of tournaments
     *
     * @return ArrayList of tournaments.
     */
    public List<Tournament> getTournaments() {
        return this.tournaments;
    }
}
