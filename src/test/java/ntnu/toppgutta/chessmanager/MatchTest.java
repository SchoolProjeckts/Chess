package ntnu.toppgutta.chessmanager;
import ntnu.toppgutta.chessmanager.model.Match;
import ntnu.toppgutta.chessmanager.model.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class that test the class Match.
 * <ul>
 *     <li>Test constructor with valid parameters.</li>
 *     <li>Test constructor with invalid parameters.
 *     <ul>
 *         <li>Player1 set as null.</li>
 *         <li>Player2 set as null.</li>
 *     </ul></li>
 *     <li>Test getMatchesAsString return correct text</li>
 * </ul>
 *
 * @author Ole Kristian
 * @author Harald
 * @version 1.0
 */
public class MatchTest {

    /**
     * Test the constructor.
     * Test with valid parameters.
     */
    @Test
    public void testConstructorWithValidParameter() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Match match = new Match(player1,player2);
        assertEquals(player1, match.getPlayer1());
        assertEquals(player2, match.getPlayer2());
    }

    /**
     * Test constructor.
     * Test with invalid parameter.
     * Player1 is set as null.
     */
    @Test
    public void testConstructorWithPlayer1SetAsNull() {
        Player player2 = new Player("player2");
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    Match match = new Match(null, player2);
                }
        );
        assertEquals("Invalid number of players in match", exception.getMessage());
    }

    /**
     * Test constructor.
     * Test with invalid parameter.
     * Player2 is set as null.
     */
    @Test
    public void testConstructorWithPlayer2SetAsNull() {
        Player player1 = new Player("player1");
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    Match match = new Match(player1, null);
                }
        );
        assertEquals("Invalid number of players in match", exception.getMessage());
    }

    /**
     * Test getMatchAsString is returns correct text.
     */
    @Test
    public void testGetMatchesAsStringReturnsCorrectText() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Match match = new Match(player1, player2);
        assertEquals("Player1 vs Player2", match.getMatchAsString());
    }
}
