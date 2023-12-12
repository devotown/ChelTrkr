package main.java;
import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Game implements Serializable {
  private static final long serialVersionUID = 333456789L;
  private Player homePlayer;
  private String homeTeam;
  private Integer homeGoals;
  private Integer homeShots;
  private Integer homeHits;
  private String homeTimeOnAttack;
  private Double homePassingPercentage;
  private Integer homeFaceoffs;
  private String homePenaltyMinutes;
  private String homePowerPlays;
  private String homePowerPlayMinutes;
  private Integer homeShorthandedGoals;
  private Player awayPlayer;
  private String awayTeam;
  private Integer awayGoals;
  private Integer awayShots;
  private Integer awayHits;
  private String awayTimeOnAttack;
  private Double awayPassingPercentage;
  private Integer awayFaceoffs;
  private String awayPenaltyMinutes;
  private String awayPowerPlays;
  private String awayPowerPlayMinutes;
  private Integer awayShorthandedGoals;
  private String timeAndDate;
  private ConfirmationScreen scorePanel;

  public Game (Player homePlayer, Player awayPlayer, String homeTeam, String awayTeam, Integer homeGoals, Integer awayGoals, Integer homeHits, Integer awayHits, String homeTimeOnAttack, String awayTimeOnAttack, Double homePassingPercentage, Double awayPassingPercentage, Integer homeFaceoffs, Integer awayFaceoffs, String homePenaltyMinutes, String awayPenaltyMinutes, String homePowerPlayMinutes, String awayPowerPlayMinutes, String homePowerPlays, String awayPowerPlays, Integer homeShorthandedGoals, Integer awayShorthandedGoals, PlayerDatabase database, Integer homeShots, Integer awayShots, ConfirmationScreen confirm) {
    this.homePlayer = homePlayer;
    this.awayPlayer = awayPlayer;
    updateTimeAndDate();

    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;

    this.homeGoals = homeGoals;
    this.awayGoals = awayGoals;

    this.homeHits = homeHits;
    this.awayHits = awayHits;

    this.homeTimeOnAttack = homeTimeOnAttack;
    this.awayTimeOnAttack = awayTimeOnAttack;

    this.homePassingPercentage = homePassingPercentage;
    this.awayPassingPercentage = awayPassingPercentage;

    this.homeFaceoffs = homeFaceoffs;
    this.awayFaceoffs = awayFaceoffs;

    this.homePenaltyMinutes = homePenaltyMinutes;
    this.awayPenaltyMinutes = awayPenaltyMinutes;

    this.homePowerPlayMinutes = homePowerPlayMinutes;
    this.awayPowerPlayMinutes = awayPowerPlayMinutes;

    this.homePowerPlays = homePowerPlays;
    this.awayPowerPlays = awayPowerPlays;

    this.homeShorthandedGoals = homeShorthandedGoals;
    this.awayShorthandedGoals = awayShorthandedGoals;

    this.homeShots = homeShots;
    this.awayShots = awayShots;

    updatePlayerStats(homePlayer, awayPlayer, homeGoals, awayGoals, homeHits, homePassingPercentage, homeShots, homeTimeOnAttack, homeFaceoffs, awayFaceoffs, homePenaltyMinutes, homePowerPlays, homeShorthandedGoals, this);
    updatePlayerStats(awayPlayer, homePlayer, awayGoals, homeGoals, awayHits, awayPassingPercentage, awayShots, awayTimeOnAttack, awayFaceoffs, homeFaceoffs, awayPenaltyMinutes, awayPowerPlays, awayShorthandedGoals, this);


    PlayerDatabase.addGame(this, database);

    scorePanel = confirm;
  }
  public void updateTimeAndDate() {
    // Get the current time and date
    LocalDateTime currentDateTime = LocalDateTime.now();

    // Define a custom date and time format
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy @ hh:mm a");

    // Format the current date and time using the defined format
    timeAndDate = currentDateTime.format(formatter);
  }
  public void print(){
    System.out.println(this.homePlayer.getName() + " " + this.awayPlayer.getName());
    System.out.println(this.timeAndDate);
    System.out.println(homeTeam + " vs. " + awayTeam);
  }

  public ConfirmationScreen getScorePanel() {
    return this.scorePanel;
  }

  public Player getHomePlayer() {
    return homePlayer;
  }

  public void updatePlayerStats(Player playing, Player other, Integer goalsFor, Integer goalsAgainst, Integer hits, Double passPercentage, Integer shots, String timeOnAttack, Integer faceoffWon, Integer faceoffLost, String penMin, String powerPlay, Integer shGoals, Game game){
    boolean win = goalsFor > goalsAgainst;
    Player.recordGame(playing, other, goalsFor, goalsAgainst, win, hits, passPercentage, shots, timeOnAttack, faceoffWon, faceoffLost, penMin, powerPlay, shGoals, game);
  }

  public String getHomeTeam() {
    return homeTeam;
  }

  public Integer getHomeGoals() {
    return homeGoals;
  }

  public Integer getHomeHits() {
    return homeHits;
  }

  public String getHomeTimeOnAttack() {
    return homeTimeOnAttack;
  }

  public Double getHomePassingPercentage() {
    return homePassingPercentage;
  }

  public Integer getHomeFaceoffs() {
    return homeFaceoffs;
  }

  public String getHomePenaltyMinutes() {
    return homePenaltyMinutes;
  }

  public String getHomePowerPlays() {
    return homePowerPlays;
  }

  public String getHomePowerPlayMinutes() {
    return homePowerPlayMinutes;
  }

  public Integer getHomeShorthandedGoals() {
    return homeShorthandedGoals;
  }

  public Player getAwayPlayer() {
    return awayPlayer;
  }

  public String getAwayTeam() {
    return awayTeam;
  }

  public Integer getAwayGoals() {
    return awayGoals;
  }

  public Integer getAwayHits() {
    return awayHits;
  }

  public String getAwayTimeOnAttack() {
    return awayTimeOnAttack;
  }

  public Double getAwayPassingPercentage() {
    return awayPassingPercentage;
  }

  public Integer getAwayFaceoffs() {
    return awayFaceoffs;
  }

  public String getAwayPenaltyMinutes() {
    return awayPenaltyMinutes;
  }

  public String getAwayPowerPlays() {
    return awayPowerPlays;
  }

  public String getAwayPowerPlayMinutes() {
    return awayPowerPlayMinutes;
  }

  public Integer getAwayShorthandedGoals() {
    return awayShorthandedGoals;
  }
  public String isKingMatch(){
    return  "FIX";
  }

  public String getTimeAndDate() {
    return timeAndDate;
  }

  public ConfirmationScreen bestIdeaEver(){

    ConfirmationScreen confirmationScreen = new ConfirmationScreen(homePlayer, awayPlayer, homeTeam, awayTeam,
        homeGoals, awayGoals, homeHits, awayHits, homeTimeOnAttack, awayTimeOnAttack, homePassingPercentage,
        awayPassingPercentage, homeFaceoffs, awayFaceoffs, homePenaltyMinutes, awayPenaltyMinutes, homePowerPlayMinutes,
        awayPowerPlayMinutes, homePowerPlays, awayPowerPlays, homeShorthandedGoals, awayShorthandedGoals,
        homeShots, awayShots);

        return confirmationScreen;
  }
}

