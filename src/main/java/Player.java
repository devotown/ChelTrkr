package main.java;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Player implements Serializable {
  private String name;
  private ArrayList<Game> gamesHistory;
  private static final long serialVersionUID = 233456789L;
  private Integer gamesPlayed;
  private Integer goalsFor;
  private Integer goalsAgainst;
  private Integer wins;
  private Integer losses;
  private Double hitsPerGame;
  private Integer totalHits;
  private Double passPercentage;
  private Double passPercentageSum;
  private Integer winStreak;
  private Integer longestWinStreak;
  private Integer totalShots;
  private Double shotsPerGame;
  private String timeOnAttackAvg;
  private Integer timeOnAttackSum;
  private Double FOPercentage;
  private Integer FOTaken;
  private Integer FOWon;
  private String penMinPG;
  private Integer penMinSum;
  private Double powerPlayp;
  private Integer powerPlayWin;
  private Integer powerPlayTaken;
  private Integer shorthadedGoalsTotal;
  private Boolean isKing;
  private Integer gamesAsKing;
  private Integer longestKingStreak;



  public Player(String name) {
    this.name = name;
    gamesPlayed = 0;
    goalsFor = 0;
    goalsAgainst = 0;
    wins = 0;
    losses = 0;
    hitsPerGame = 0.0;
    totalHits = 0;
    passPercentage = 0.0;
    passPercentageSum = 0.0;
    winStreak = 0;
    longestWinStreak = 0;
    totalShots = 0;
    shotsPerGame = 0.0;
    timeOnAttackAvg = "00:00";
    timeOnAttackSum = 0;
    FOPercentage = 0.0;
    FOTaken = 0;
    FOWon = 0;
    penMinPG = "00:00";
    penMinSum = 0;
    powerPlayp = 0.0;
    powerPlayWin = 0;
    powerPlayTaken = 0;
    shorthadedGoalsTotal = 0;
    isKing = false;
    gamesAsKing = 0;
    longestKingStreak = 0;
    gamesHistory = new ArrayList<Game>();
  }

  public static void recordGame(Player player, Player other, Integer goalsFor, Integer goalsAgainst,
      Boolean win, Integer hits, Double passPercentage, Integer shots, String timeOnAttack, Integer faceoffWon, Integer faceoffLost, String penMin, String powerPlay, Integer shGoals, Game game) {
    player.updateStats(goalsFor, goalsAgainst, win, hits, passPercentage, shots, timeOnAttack, faceoffWon, faceoffLost, penMin, powerPlay, shGoals, other, game);
  }

  public void updateStats(Integer goalsFor, Integer goalsAgainst, Boolean win, Integer hits,
      Double passPercentage, Integer shots, String timeOnAttack, Integer faceoffWon, Integer faceoffLost, String penMin, String powerPlay, Integer shGoals, Player other, Game game) {
    this.gamesPlayed += 1;
    this.goalsFor += goalsFor;
    this.goalsAgainst += goalsAgainst;
    if (win) {
      this.wins += 1;
      this.winStreak += 1;
    } else {
      this.losses += 1;
      this.winStreak = 0;
    }

    if (winStreak > longestWinStreak) {
      longestWinStreak = winStreak;
    }

    this.totalHits += hits;


    double hpg = (double) this.totalHits / this.gamesPlayed;
    DecimalFormat decimalFormat = new DecimalFormat("#.#");
    String formattedResult = decimalFormat.format(hpg);
    this.hitsPerGame = Double.parseDouble(formattedResult);

    this.passPercentageSum += passPercentage;

    double pp = ((double) this.passPercentageSum / this.gamesPlayed) ;
    formattedResult = decimalFormat.format(pp);
    this.passPercentage = Double.parseDouble(formattedResult);

    this.totalShots += shots;

    double spg = (double) (this.totalShots / this.gamesPlayed);
    formattedResult = decimalFormat.format(spg);
    this.shotsPerGame = Double.parseDouble(formattedResult);

    // Read in time on attack and add it to sum
    String[] timeParts = timeOnAttack.split(":");
    int minutes = Integer.parseInt(timeParts[0]);
    int seconds = Integer.parseInt(timeParts[1]);

    int totalTimeSeconds = (minutes * 60) + seconds;
    this.timeOnAttackSum += totalTimeSeconds;

    double tAttAvgDbl = (double) timeOnAttackSum / gamesPlayed;
    int tAttAvgInt = (int) Math.round(tAttAvgDbl);

    int minAtt = tAttAvgInt / 60;
    int secAtt = tAttAvgInt % 60;

    this.timeOnAttackAvg = String.format("%02d:%02d", minAtt, secAtt);

    this.FOWon += faceoffWon;
    this.FOTaken += (faceoffWon + faceoffLost);

    double fop = ((double) this.FOWon / this.FOTaken) * 100;
    formattedResult = decimalFormat.format(fop);
    this.FOPercentage = Double.parseDouble(formattedResult);

    String[] penParts = penMin.split(":");
    int minutesPen = Integer.parseInt(penParts[0]);
    int secondsPen = Integer.parseInt(penParts[1]);

    int totalTimeSecondsPen = (minutesPen * 60) + secondsPen;
    this.penMinSum += totalTimeSecondsPen;

    double tPenAvgDbl = (double) penMinSum / gamesPlayed;
    int tPenAvgInt = (int) Math.round(tPenAvgDbl);

    int minPen = tPenAvgInt / 60;
    int secPen = tPenAvgInt % 60;

    this.penMinPG = String.format("%02d:%02d", minPen, secPen);

    String[] powerPlayParts = powerPlay.split("/");
    int ppWon = Integer.parseInt(powerPlayParts[0]);
    int ppTotal = Integer.parseInt(powerPlayParts[1]);

    this.powerPlayWin += ppWon;
    this.powerPlayTaken += ppTotal;

    double ppp = ((double) this.powerPlayWin / powerPlayTaken) * 100;
    formattedResult = decimalFormat.format(ppp);
    this.powerPlayp = Double.parseDouble(formattedResult);

    this.shorthadedGoalsTotal += shGoals;

    if (win && other.isKing()){
      other.setIsKing(false);
      this.isKing = true;
    }

    if (win && isKing){
      gamesAsKing += 1;
    }

    if (gamesAsKing > longestKingStreak){
      longestKingStreak = gamesAsKing;
    }

    if (!win && isKing){
      this.gamesAsKing = 0;
    }

    this.addGame(game);
  }

  public Integer getLongestWinStreak(){
    return this.longestWinStreak;
  }

  public boolean isKing(){
    return this.isKing;
  }
  public void setIsKing(boolean king){
    this.isKing = king;
  }
  public String getName() {
    return this.name;
  }

  public Integer getWins() {
    return this.wins;
  }

  public Integer getLosses() {
    return this.losses;
  }

  public Integer getGoalsFor() {
    return this.goalsFor;
  }

  public Integer getGoalsAgainst() {
    return this.goalsAgainst;
  }

  public Double getHitsPerGame() {
    return this.hitsPerGame;
  }

  public Double getPassPercentage() {
    return this.passPercentage;
  }

  public Integer getWinStreak() {
    return this.winStreak;
  }

  public Integer getShots() {
    return this.totalShots;
  }

  public Double getSPG() {
    return this.shotsPerGame;
  }

  public Integer getHits() {
    return this.totalHits;
  }

  public String getTimeOnAttackAvg() {
    return this.timeOnAttackAvg;
  }

  public Double getFaceoffPercentage() {
    return this.FOPercentage;
  }

  public String getPMPG() {
    return this.penMinPG;
  }

  public Double getPPP() {
    return this.powerPlayp;
  }

  public Integer getShorthandedGoals() {
    return this.shorthadedGoalsTotal;
  }

  public Integer getGamesAsKing() {
    return this.gamesAsKing;
  }

  public Integer longestKingStreak() {
    return longestKingStreak;
  }

  public Integer getGamesPlayed() {
    return this.gamesPlayed;
  }

  public void addGame(Game game) {
    this.gamesHistory.add(game);
  }


}
