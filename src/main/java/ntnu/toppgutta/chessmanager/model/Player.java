package ntnu.toppgutta.chessmanager.model;

/**
 * Represents a player.
 * The Player haves name and stats.
 *
 * @author Petter
 * @author Mikkel
 * @author Harald
 * @author Ole Kristian
 * @version 19/03/2022
 */
public class Player {
  private String name;
  private int matchesPlayed = 0;
  private int wins = 0;
  private int tie = 0;
  private int lose = 0;
  private double points = 0;
  private static final String ILLIEGALARGUMENT = "Wrong input the number most be 0 or more.";

  /**
   * Creates a new player
   *
   * @param name The name of the player
   * @throws IllegalArgumentException Makes sure every player has a name
   */
  public Player(String name) throws IllegalArgumentException {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Illegal argument when creating Player");
    } else {
      this.name = name;
    }
  }

  /**
   * Returns the players name
   *
   * @return The name of the player
   */
  public String getName() {
    return this.name;
  }

  /**
   * Changes the players name to given name
   *
   * @param name The new name of the player
   */
  public void setName(String name) {
    if (name != null && !name.isBlank()) {
      this.name = name;
    }
  }

  /**
   * Return matches played as a String.
   *
   * @return matchesPlayed
   */
  public String getMatchesPlayedAsString() {
    return this.matchesPlayed + "";
  }

  /**
   * Return wins as a String.
   *
   * @return wins
   */
  public String getWinsAsString() {
    return this.wins + "";
  }

  /**
   * Return loses as a String.
   *
   * @return loses
   */
  public String getLoseAsString() {
    return this.lose + "";
  }

  /**
   * Return points as a String.
   *
   * @return points.
   */
  public String getPointsAsString() {
    return this.points + "";
  }

  /**
   * Return tie as a String.
   *
   * @return tie.
   */
  public String getTieAsString() {
    return this.tie + "";
  }

  /**
   * Set new lose value.
   *
   * @param lose new value.
   */
  public void setLose(int lose) {
    if(lose >=0) {
      this.lose = lose;
    }
    else {
      throw new IllegalArgumentException(ILLIEGALARGUMENT);
    }
  }

  /**
   * Set new matchesPlayed value.
   *
   * @param matchesPlayed new value.
   */
  public void setMatchesPlayed(int matchesPlayed) {
    if(matchesPlayed >=0) {
      this.matchesPlayed = matchesPlayed;
    }
    else {
      throw new IllegalArgumentException(ILLIEGALARGUMENT);
    }
  }

  /**
   * Set new points value.
   *
   * @param points new value.
   */
  public void setPoints(double points) {
    if(points >=0) {
      this.points = points;
    }
    else {
      throw new IllegalArgumentException(ILLIEGALARGUMENT);
    }
  }

  /**
   * Set new tie value.
   *
   * @param tie new value.
   */
  public void setTie(int tie) {
    if(tie >=0) {
      this.tie = tie;
    }
    else {
      throw new IllegalArgumentException(ILLIEGALARGUMENT);
    }
  }

  /**
   * Set new wins value.
   *
   * @param wins new value.
   */
  public void setWins(int wins) {
    if(wins >=0){
      this.wins = wins;
    }
    else {
      throw new IllegalArgumentException(ILLIEGALARGUMENT);
    }
  }
}
