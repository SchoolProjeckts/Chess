package ntnu.toppgutta.chessmanager;
import java.util.ArrayList;
import java.util.List;
import ntnu.toppgutta.chessmanager.model.Match;
import ntnu.toppgutta.chessmanager.model.Matchmaker;
import ntnu.toppgutta.chessmanager.model.Player;
import ntnu.toppgutta.chessmanager.model.Tournament;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class that tests the class Tournament.
 * <ul>
 *   <li>Test constructor with valid parameters.</li>
 *   <li>Test constructor with invalid parameters.
 *   <ul>
 *       <li>tournamentName is set as nul.l</li>
 *       <li>tournamentName is empty.</li>
 *       <li>players is set as null.</li>
 *       <li>tournamentMatches is set as null.</li>
 *       <li>date is set as null.</li>
 *   </ul></li>
 * </ul>
 *
 * @author Ole Kristian
 * @author Harald
 * @version 1.0
 */
public class TournamentTest {

    /**
     * Test the constructor with valid parameters
     */
    @Test
    public void testConstructorWithValidParameters(){
        String name = "Tournament";
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Player player3 = new Player("player3");
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        Matchmaker matchmaker = new Matchmaker();
        List<Match> matches = new ArrayList<>();
        for(Match match : matchmaker.createMatches(players)){
            matches.add(match);
        }
        String info = "info";
        Tournament tournament = new Tournament(name,players,matches,"2020-10-10",info);
        assertEquals("Tournament", tournament.getTournamentName());
        assertEquals(players, tournament.getPlayers());
        assertEquals(matches, tournament.getTournamentMatches());
        assertEquals("2020-10-10", tournament.getDate());
        assertEquals("info", tournament.getInfo());
    }

    /**
     * Test constructor with invalid parameter.
     * Tournament name is set as null.
     */
    @Test
    public void testConstructorWithTournamentNameAsNull() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Player player3 = new Player("player3");
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        Matchmaker matchmaker = new Matchmaker();
        List<Match> matches = new ArrayList<>();
        for(Match match : matchmaker.createMatches(players)){
            matches.add(match);
        }
        String info = "info";
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    Tournament tournament = new Tournament(null,players,matches,"2020-10-10",info);
                }
        );
        assertEquals("Invalid fields in Constructor", exception.getMessage());
    }

    /**
     * Test constructor with invalid parameter.
     * Tournament name is empty.
     */
    @Test
    public void testConstructorWithNameEmpty() {
        String name = "";
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Player player3 = new Player("player3");
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        Matchmaker matchmaker = new Matchmaker();
        List<Match> matches = new ArrayList<>();
        for(Match match : matchmaker.createMatches(players)){
            matches.add(match);
        }
        String info = "info";
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    Tournament tournament = new Tournament(name,players,matches,"2020-10-10",info);
                }
        );
        assertEquals("Invalid fields in Constructor", exception.getMessage());
    }

    /**
     * Test constructor with invalid parameter.
     * Tournament player list is set as null
     */
    @Test
    public void testConstrucotrWithPlayerListAsNull() {
        String name = "Tournament";
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Player player3 = new Player("player3");
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        Matchmaker matchmaker = new Matchmaker();
        List<Match> matches = new ArrayList<>();
        for(Match match : matchmaker.createMatches(players)){
            matches.add(match);
        }
        String info = "info";
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    Tournament tournament = new Tournament(name,null,matches,"2020-10-10",info);
                }
        );
        assertEquals("Invalid fields in Constructor", exception.getMessage());
    }

    /**
     * Test constructor with invalid parameter.
     * Tournament matches is set as null.
     */
    @Test
    public void testConstructorWithMatchesAsNull() {
        String name = "Tournament";
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Player player3 = new Player("player3");
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        String info = "info";
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    Tournament tournament = new Tournament(name,players,null,"2020-10-10",info);
                }
        );
        assertEquals("Invalid fields in Constructor", exception.getMessage());
    }

    /**
     * Test constructor with invalid parameter.
     * Tournament date is set as null.
     */
    @Test
    public void testConstructorWithDateAsNull() {
        String name = "Tournament";
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Player player3 = new Player("player3");
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        Matchmaker matchmaker = new Matchmaker();
        List<Match> matches = new ArrayList<>();
        for(Match match : matchmaker.createMatches(players)){
            matches.add(match);
        }
        String info = "Info";
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    Tournament tournament = new Tournament(name,players,matches,null,info);
                }
        );
        assertEquals("Invalid fields in Constructor", exception.getMessage());
    }
}
