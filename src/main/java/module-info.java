
/**
 * The TournamentManager module represents all layers of the TournamentManager program.
 *
 */
module TournamentManager {

  requires javafx.controls;
  requires javafx.graphics;
  requires javafx.fxml;
  requires org.kordamp.ikonli.core;
  requires org.kordamp.ikonli.javafx;
  requires org.kordamp.ikonli.elusive;
  requires MaterialFX;
  requires org.json;
  requires com.google.gson;
  requires org.junit.jupiter.api;


  opens ntnu.toppgutta.chessmanager.model to javafx.fxml, com.google.gson;
  opens ntnu.toppgutta.chessmanager.ui to javafx.fxml, com.google.gson;

  exports ntnu.toppgutta.chessmanager.model;
  exports ntnu.toppgutta.chessmanager.ui;
    exports ntnu.toppgutta.chessmanager;
    opens ntnu.toppgutta.chessmanager to javafx.fxml, com.google.gson;

}