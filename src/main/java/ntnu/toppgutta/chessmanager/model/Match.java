package ntnu.toppgutta.chessmanager.model;

/**
 * Represents a match between two players.
 *
 * @author Petter
 * @author Mikkel
 * @author Harald
 * @version 1.0
 */
public class Match {

  private Player player1;
  private Player player2;


  /**
   * Set up a match of between two players.
   *
   * @param player1 fist player of the match.
   * @param player2 player of a match.
   * @throws IllegalArgumentException Throws an exception if no players in parameters
   */
  public Match(Player player1, Player player2) throws IllegalArgumentException {
    if (player1 != null && player2 != null) {
      this.player1 = player1;
      this.player2 = player2;
    } else {
      throw new IllegalArgumentException("Invalid number of players in match");
    }
  }

  /**
   * Creates a text representation of the match
   *
   * @return Lists both players playing in the match
   */
  @Override
  public String toString() {
    return "Match{" +
        "player1=" + player1 +
        ", player2=" + player2 +
        '}';
  }

  /**
   * Returns the player1 in the match
   *
   * @return The first player
   */
  public Player getPlayer1() {
    return player1;
  }

  /**
   * Returns the player2 in the match
   *
   * @return The second player
   */
  public Player getPlayer2() {
    return player2;
  }

  /**
   * Return match as a string.
   *
   * @return match.
   */
  public String getMatchAsString() {
    return player1.getName() + " vs " + player2.getName();
  }
}