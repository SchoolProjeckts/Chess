package ntnu.toppgutta.chessmanager;
import ntnu.toppgutta.chessmanager.model.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class that test the class Player.
 * <ul>
 *     <li>Test constructor with valid parameters.</li>
 *     <li>Test constructor with invalid name parameter.
 *     <ul>
 *         <li>Name is set as null.</li>
 *         <li>Name is empty.</li>
 *     </ul> </li>
 *     <li>Test SetName with valid parameters and set correct name.</li>
 *     <li>Test SetName with invalid parameters.
 *     <ul>
 *         <li>name is set as null.</li>
 *         <li>name is empty</li>
 *     </ul></li>
 *     <li>Test setMatchesPlayed with valid parameter.</li>
 *     <li>Test setMatchesPlayed with invalid parameter. Number can't be below 0. </li>
 *     <li>Test setWins with valid parameter.</li>
 *     <li>Test setWins with invalid parameter. Number can't be below 0.</li>
 *     <li>Test setTie with valid parameter.</li>
 *     <li>Test setTie with invalid parameter. Number can't be below 0.</li>
 *     <li>Test setLose with valid parameter.</li>
 *     <li>Test setLose with invalid parameter. Number can't be below 0.</li>
 *     <li>Test setPoints with valid parameter.</li>
 *     <li>Test setPoints with invalid parameter. Number can't be below 0.</li>
 * </ul>
 *
 * @author Ole Kristian
 * @author Harald
 * @version 1.0
 */
public class PlayerTest {

    /**
     * Test the constructor.
     * Test with valid parameter.
     */
    @Test
    public void testConstructorWithValidParameter() {
        Player player  = new Player("Nils");
        assertEquals("Nils", player.getName());
    }

    /**
     * Test the constructor.
     * Test with invalid parameter.
     * Name is set as null.
     */
    @Test
    public void testConstructorWithNameSetAsNull() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    Player player = new Player(null);
                }
        );
        assertEquals("Illegal argument when creating Player", exception.getMessage());
    }

    /**
     * Test the constructor.
     * Test with invalid parameter.
     * Name is empty.
     */
    @Test
    public void testConstructorWithEmptyName() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    Player player = new Player("");
                }
        );
        assertEquals("Illegal argument when creating Player", exception.getMessage());
    }

    /**
     * Test setMatchPlayed with valid parameter
     */
    @Test
    public void testSetMatchesPlayedIsCorrect() {
        Player player = new Player("Nils");
        player.setMatchesPlayed(1);
        assertEquals("1", player.getMatchesPlayedAsString());
    }

    /**
     * Test setMatchPlayed with invalid input.
     * The number is below 0.
     */
    @Test
    public void testSetMathcesPlayedWihtANegativeNumber() {
        Player player = new Player("Nils");
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    player.setMatchesPlayed(-1);
                }
        );
        assertEquals("Wrong input the number most be 0 or more.", exception.getMessage());
    }

    /**
     * Test setWins with valid parameter.
     */
    @Test
    public void testSetWinsIsCorrect() {
        Player player = new Player("Nils");
        player.setWins(1);
        assertEquals("1", player.getWinsAsString());
    }

    /**
     * Test setWins with invalid parameter.
     * The number is below 0.
     */
    @Test
    public void testSetWinsWithANegativeNumber() {
        Player player = new Player("Nils");
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    player.setWins(-1);
                }
        );
        assertEquals("Wrong input the number most be 0 or more.", exception.getMessage());
    }

    /**
     * Test setTie with valid parameter.
     */
    @Test
    public void testSetTieISCorrect() {
        Player player = new Player("Nils");
        player.setTie(1);
        assertEquals("1", player.getTieAsString());
    }

    /**
     * Test setTie with invalid parameter.
     * The number is below 0.
     */
    @Test
    public void testSetTieWithANegativeNumber() {
        Player player = new Player("Nils");
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    player.setTie(-1);
                }
        );
        assertEquals("Wrong input the number most be 0 or more.", exception.getMessage());
    }

    /**
     * Test setLose with valid parameter.
     */
    @Test
    public void testSetLoseIsCorrect() {
        Player player = new Player("Nils");
        player.setLose(1);
        assertEquals("1", player.getLoseAsString());
    }

    /**
     * Test setLose with invalid parameter.
     * Number is below 0.
     */
    @Test
    public void testSetLoseWithANegativeNumber() {
        Player player = new Player("Nils");
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    player.setLose(-1);
                }
        );
        assertEquals("Wrong input the number most be 0 or more.", exception.getMessage());
    }

    /**
     * Test setPoints with valid parameter.
     */
    @Test
    public void testSetPointIsCorrect() {
        Player player = new Player("Nils");
        player.setPoints(1);
        assertEquals("1.0", player.getPointsAsString());
    }

    /**
     * Test setPoints with invalid parameter.
     * number is below 0.
     */
    @Test
    public void testSetPointsWithANegativeNumber() {
        Player player = new Player("Nils");
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    player.setPoints(-1);
                }
        );
        assertEquals("Wrong input the number most be 0 or more.", exception.getMessage());
    }
}


