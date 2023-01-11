package ntnu.toppgutta.chessmanager.model;

import java.util.List;


/**
 *
 *  Represents a Tournament
 *  There are name, players, matches, date of the tournament og info of the tournament in a tournament.
 *
 * @author Petter
 * @author Mikkel
 * @author Harald
 * @author Ole Kristian
 * @author Jørgen
 * @version 31/03/2022 
 */
public class Tournament {

  private String tournamentName;
  private List<Player> players;
  private List<Match> tournamentMatches;
  private String date;
  private String info;

  /**
   * Creates a Tournament.
   *
   * @param tournamentName The name of the tournament
   * @param players A list of the players in the tournament
   * @param tournamentMatches A list of the matches to be played in the tournament
   * @param date the date of the tournament.
   * @param info The info for the tournament
   */
  public Tournament(String tournamentName, List<Player> players, List<Match> tournamentMatches, String date, String info){
    if(tournamentName != null && !tournamentName.isBlank() && players != null && tournamentMatches != null && players.size() >= 2 && date !=null){
      this.tournamentName = tournamentName;
      this.players = players;
      this.tournamentMatches = tournamentMatches;
      this.date = date;
      this.info = info;
    }
    else{
      throw new IllegalArgumentException("Invalid fields in Constructor");
    }
  }

  /**
   * Returns teh date of the tournament.
   *
   * @return Date.
   */
  public String getDate() {
    return this.date;
  }

  /**
   * Returns the info of the tournament.
   *
   * @return the info.
   */
  public String getInfo() {
    return this.info;
  }

  /**
   * Returns the matches being played in the tournament
   *
   * @return {@code List<Match>} a List of the current matches in the tournament
   */
  public List<Match> getTournamentMatches() {
    return tournamentMatches;
  }

  /**
   * Returns the name of the Tournament
   *
   * @return The name of the Tournament
   */
  public String getTournamentName() {
    return this.tournamentName;
  }

  /**
   * Returns the players playing in the tournament
   *
   * @return The players playing in the tournament
   */
  public List<Player> getPlayers() {
    return players;
  }


  /**
   * Returns the size of the playerlist.
   *
   * @return size of playerlist.
   */
 public int getNumberOfplayers() {
    return this.players.size();
 }
}