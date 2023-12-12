package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class ConfirmationScreen extends JPanel {

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
  private static final String[] nhlTeams =
      {"Anaheim Ducks", "Arizona Coyotes", "Boston Bruins", "Buffalo Sabres", "Calgary Flames",
          "Carolina Hurricanes", "Chicago Blackhawks", "Colorado Avalanche", "Columbus Blue Jackets", "Dallas Stars", "Detroit Red Wings", "Edmonton Oilers",
          "Florida Panthers", "Los Angeles Kings", "Minnesota Wild", "Montreal Canadiens",
          "Nashville Predators", "New Jersey Devils", "New York Islanders", "New York Rangers",
          "Ottawa Senators", "Philadelphia Flyers", "Pittsburgh Penguins", "San Jose Sharks",
          "Seattle Kraken", "St. Louis Blues", "Tampa Bay Lightning", "Toronto Maple Leafs",
          "Vancouver Canucks", "Vegas Golden Knights", "Washington Capitals", "Winnipeg Jets"};

  public ConfirmationScreen(Player homePlayer, Player awayPlayer, String homeTeam, String awayTeam,
      Integer homeGoals, Integer awayGoals, Integer homeHits, Integer awayHits, String homeTimeOnAttack, String awayTimeOnAttack, Double homePassingPercentage,
      Double awayPassingPercentage, Integer homeFaceoffs, Integer awayFaceoffs, String homePenaltyMinutes, String awayPenaltyMinutes, String homePowerPlayMinutes,
      String awayPowerPlayMinutes, String homePowerPlays, String awayPowerPlays, Integer homeShorthandedGoals, Integer awayShorthandedGoals,
      Integer homeShots, Integer awayShots) {
    this.homePlayer = homePlayer;
    this.awayPlayer = awayPlayer;

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
    // Set the background color to gray
    setBackground(Color.GRAY);

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    ArrayList<JLabel> leftLabels = new ArrayList<>();
    ArrayList<JLabel> middleLabels = new ArrayList<>();
    ArrayList<JLabel> rightLabels = new ArrayList<>();

    // Create labels for each row based on the value of i
    for (int i = 1; i < 10; i++) {
      switch (i) {
        case 0:
          leftLabels.add(createCustomLabel("TMN"));
          middleLabels.add(createCustomLabel("Score"));
          rightLabels.add(createCustomLabel("TMN"));
          break;
        case 1:
          leftLabels.add(createCustomLabel(Integer.toString(awayShots)));
          middleLabels.add(createCustomLabel2("TOTAL SHOTS"));
          rightLabels.add(createCustomLabel(Integer.toString(homeShots)));
          break;
        case 2:
          leftLabels.add(createCustomLabel(Integer.toString(awayHits)));
          middleLabels.add(createCustomLabel2("HITS"));
          rightLabels.add(createCustomLabel(Integer.toString(homeHits)));
          break;
        case 3:
          leftLabels.add(createCustomLabel(awayTimeOnAttack));
          middleLabels.add(createCustomLabel2("TIME ON ATTACK"));
          rightLabels.add(createCustomLabel(homeTimeOnAttack));
          break;
        case 4:
          leftLabels.add(createCustomLabel(Double.toString(awayPassingPercentage)));
          middleLabels.add(createCustomLabel2("PASSING"));
          rightLabels.add(createCustomLabel(Double.toString(homePassingPercentage)));
          break;
        case 5:
          leftLabels.add(createCustomLabel(Integer.toString(awayFaceoffs)));
          middleLabels.add(createCustomLabel2("FACEOFFS WON"));
          rightLabels.add(createCustomLabel(Integer.toString(homeFaceoffs)));
          break;
        case 6:
          leftLabels.add(createCustomLabel(awayPenaltyMinutes));
          middleLabels.add(createCustomLabel2("PENALTY MINUTES"));
          rightLabels.add(createCustomLabel(homePenaltyMinutes));
          break;
        case 7:
          leftLabels.add(createCustomLabel(awayPowerPlays));
          middleLabels.add(createCustomLabel2("POWERPLAYS"));
          rightLabels.add(createCustomLabel(homePowerPlays));
          break;
        case 8:
          leftLabels.add(createCustomLabel(awayPowerPlayMinutes));
          middleLabels.add(createCustomLabel2("POWERPLAY MINUTES"));
          rightLabels.add(createCustomLabel(homePowerPlayMinutes));
          break;
        case 9:
          leftLabels.add(createCustomLabel(Integer.toString(awayShorthandedGoals)));
          middleLabels.add(createCustomLabel2("SHORTHANDED GOALS"));
          rightLabels.add(createCustomLabel(Integer.toString(homeShorthandedGoals)));
          break;
      }
    }

    JPanel playerRow = new JPanel();
    playerRow.setBackground(Color.GRAY);
    playerRow.setLayout(new BoxLayout(playerRow, BoxLayout.X_AXIS));

    JLabel awayPlayerL = new JLabel(awayPlayer.getName());
    JLabel homePlayerL = new JLabel(homePlayer.getName());

    awayPlayerL.setFont(new Font("Impact", Font.BOLD, 25));

    homePlayerL.setFont(new Font("Impact", Font.BOLD, 25));

    playerRow.add(Box.createHorizontalGlue());
    playerRow.add(awayPlayerL);
    playerRow.add(Box.createHorizontalGlue());
    playerRow.add(homePlayerL);
    playerRow.add(Box.createHorizontalGlue());

    add(playerRow);

    JPanel topRow = topRow(awayTeam, homeTeam);
    add(topRow);

    for (int i = 0; i < 9; i++) {
      JPanel rowPanel = new JPanel();
      rowPanel.setBackground(Color.GRAY);
      rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));

      rowPanel.add(Box.createRigidArea(new Dimension(10, 0)));
      rowPanel.add(leftLabels.get(i));
      rowPanel.add(Box.createHorizontalGlue()); // This will expand the space between labels
      rowPanel.add(middleLabels.get(i));
      rowPanel.add(Box.createHorizontalGlue()); // This will expand the space between labels
      rowPanel.add(rightLabels.get(i));
      rowPanel.add(Box.createRigidArea(new Dimension(10, 0)));

      add(rowPanel); // Add the row to the main panel
    }

  }


  public static JLabel createCustomLabel(String text) {
    JLabel label = new JLabel(text);
    label.setFont(new Font("Impact", Font.BOLD, 25));
    label.setOpaque(false); // Make the background transparent
    return label;
  }

  public JPanel topRow(String awayTeamName, String homeTeamName) {
    JPanel topRow = new JPanel();
    topRow.setBackground(Color.GRAY);
    topRow.setLayout(new BoxLayout(topRow, BoxLayout.X_AXIS));
    NHLteams away = new NHLteams(awayTeamName);
    NHLteams home = new NHLteams(homeTeamName);


    // Create an image or use a placeholder
    topRow.add(Box.createHorizontalGlue());
    JLabel imageLabel = new JLabel(away.getSelectedImage());
    topRow.add(imageLabel);
    topRow.add(Box.createHorizontalGlue());


    // Create a panel for the "Team" label and set its layout to FlowLayout with left alignment
    JLabel awayTeam = new JLabel(away.getSelectedTeam());
    awayTeam.setFont(new Font("Impact", Font.BOLD, 40));
    topRow.add(awayTeam);
    topRow.add(Box.createHorizontalGlue());

    String stringScoreAway = String.valueOf(this.awayGoals);
    String stringScoreHome = String.valueOf(this.homeGoals);

    JLabel score = new JLabel("  " + stringScoreAway + " - " + stringScoreHome + "  ");
    score.setFont(new Font("Impact", Font.BOLD, 40));
    score.setForeground(Color.white);
    score.setOpaque(true);
    score.setBackground(Color.BLACK);
    topRow.add(score);
    topRow.add(Box.createHorizontalGlue());

    JLabel homeTeam = new JLabel(home.getSelectedTeam());
    homeTeam.setFont(new Font("Impact", Font.BOLD, 40));
    topRow.add(homeTeam);
    topRow.add(Box.createHorizontalGlue());

    // Add another image or use a placeholder
    JLabel imageLabel2 = new JLabel(home.getSelectedImage());
    topRow.add(imageLabel2);
    topRow.add(Box.createHorizontalGlue());

    return topRow;
  }

  public static JLabel createCustomLabel2(String text) {
    JLabel label = new JLabel(text);
    label.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
    label.setOpaque(false); // Make the background transparent
    return label;
  }
}



