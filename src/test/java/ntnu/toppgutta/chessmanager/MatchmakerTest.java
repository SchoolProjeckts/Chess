package ntnu.toppgutta.chessmanager;
import ntnu.toppgutta.chessmanager.model.Matchmaker;
import ntnu.toppgutta.chessmanager.model.Player;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class that test the class Matchmaker.
 * <ul>
 *     <li>Test createMatches creates correct matches.</li>
 *     <li>Test createMatches with invalid parameter.</li>
 * </ul>
 *
 * @author Ole Kristian
 * @author Harald
 * @version 1.0
 */
public class MatchmakerTest {

    /**
     * Test createMatches creates correct matches.
     */
    @Test
    public void testCreateMatchesCreatesCorrectMatches() {

        List<Player> newList = new ArrayList<>();

        newList.add(new Player("Petter"));
        newList.add(new Player("Jørgen"));
        newList.add(new Player("Ole"));
        newList.add(new Player("Mikkel"));
        newList.add(new Player("Harald"));
        newList.add(new Player("Kjell"));

        Matchmaker matchmaker = new Matchmaker();
        assertEquals(15, matchmaker.createMatches(newList).size());
    }

    /**
     * Test createMatches with invalid parameter.
     * players is set as null.
     */
    @Test
    public void testCreateMatchesWithInvalidParameter() {
        Matchmaker matchmaker = new Matchmaker();
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    matchmaker.createMatches(null);
                }
        );
        assertEquals("List can not be null", exception.getMessage());
    }
}
