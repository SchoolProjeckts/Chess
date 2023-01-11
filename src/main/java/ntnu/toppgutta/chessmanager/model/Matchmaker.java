package ntnu.toppgutta.chessmanager.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class sets upp the matches between two players in a tournament.
 *
 * @author Mikkel
 * @author Peter
 * @author Harald
 * @version 19/03/2022
 */
public class Matchmaker {
  private int counter = 0;
  private int index = 0;

  /**
   * Creates a list of matches for a tournament
   *
   * @param players The list of players to be matched against each other
   * @return A list of the new matches
   */
  public List<Match> createMatches(List<Player> players) {
    if (players == null) {
      throw new IllegalArgumentException("List can not be null");
    } else {

      ArrayList<Match> newMatches = new ArrayList<>();

      while (counter != players.size() - 1) {
        newMatches.add(new Match(players.get(counter), players.get(this.index + 1)));

        index++;
        if (index == players.size() - 1) {
          counter++;
          this.index = counter;
        }
        Collections.shuffle(newMatches);
      }
      return newMatches;
    }
  }
}
