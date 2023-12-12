package main.java;
import java.io.*;
import java.util.ArrayList;

public class PlayerDatabase implements Serializable{
  private ArrayList<Player> playerList;
  private ArrayList<Game> gameList;
  private static final long serialVersionUID = 133456789L;

  public PlayerDatabase(){
    playerList = new ArrayList<Player>();
    gameList = new ArrayList<Game>();
  }
  public void initializePlayerList() {
    if (playerList == null || playerList.isEmpty()) {
      playerList = new ArrayList<Player>();
      gameList = new ArrayList<Game>();
    }
  }
  public ArrayList<Player> getPlayerList(){
    return this.playerList;
  }

  public static void addPlayer(String name, PlayerDatabase database){
    Player player = new Player(name);
    database.getPlayerList().add(player);
  }

  public static void addGame(Game game, PlayerDatabase database){
    database.getGameList().add(game);
    for (Game games: database.getGameList()){
      System.out.println(games.getHomePlayer().getName() + " vs. " + games.getAwayPlayer().getName());
    }
  }
  public ArrayList<Game> getGameList(){
    return this.gameList;
  }

}
