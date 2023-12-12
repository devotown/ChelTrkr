package main.java;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class Display extends JFrame {
  static PlayerDatabase database;
  private static final String[] nhlTeams = {
      "Anaheim Ducks", "Arizona Coyotes", "Boston Bruins", "Buffalo Sabres",
      "Calgary Flames", "Carolina Hurricanes", "Chicago Blackhawks", "Colorado Avalanche",
      "Columbus Blue Jackets", "Dallas Stars", "Detroit Red Wings", "Edmonton Oilers",
      "Florida Panthers", "Los Angeles Kings", "Minnesota Wild", "Montreal Canadiens",
      "Nashville Predators", "New Jersey Devils", "New York Islanders", "New York Rangers",
      "Ottawa Senators", "Philadelphia Flyers", "Pittsburgh Penguins", "San Jose Sharks",
      "Seattle Kraken", "St. Louis Blues", "Tampa Bay Lightning", "Toronto Maple Leafs",
      "Vancouver Canucks", "Vegas Golden Knights", "Washington Capitals", "Winnipeg Jets"
  };

  public static Player homePlayer;
  public static String homeTeam;
  public static Integer homeGoals;
  public static Integer homeShots;
  public static Integer homeHits;
  public static String homeTimeOnAttack;
  public static Double homePassingPercentage;
  public static Integer homeFaceoffs;
  public static String homePenaltyMinutes;
  public static String homePowerPlays;
  public static String homePowerPlayMinutes;
  public static Integer homeShorthandedGoals;
  public static Player awayPlayer;
  public static String awayTeam;
  public static Integer awayGoals;
  public static Integer awayShots;
  public static Integer awayHits;
  public static String awayTimeOnAttack;
  public static Double awayPassingPercentage;
  public static Integer awayFaceoffs;
  public static String awayPenaltyMinutes;
  public static String awayPowerPlays;
  public static String awayPowerPlayMinutes;
  public static Integer awayShorthandedGoals;

  Color iceBlue = new Color(40, 155, 209);
  Color red = new Color(130, 26, 35);

  public Display() {
    // Set the title of the JFrame
    super("ChelTrkr 1.0.2");


    database = new PlayerDatabase();

    loadGame();
    // Set the default close operation to exit when the window is closed
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Set the minimum size to a medium window size (adjust as needed)
    setMinimumSize(new Dimension(800, 600));
    // Maximize the JFrame to full screen
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    // Make the JFrame visible
    setVisible(true);
    // Add Main Menu Panel
    addMainMenu();

    // If the database is still null or empty, create a new on

  }

  public static void loadGame(){
    try {
      FileInputStream fis = new FileInputStream("PlayerDatabase.sav");
      ObjectInputStream ois = new ObjectInputStream(fis);
      database = (PlayerDatabase) ois.readObject();
      ois.close();
      fis.close();
    } catch (Exception e) {
      System.out.println(e.getClass() + ": " + e.getMessage());
    }
  }

  @Override
  public void setDefaultCloseOperation(int operation) {
    if (operation == EXIT_ON_CLOSE) {
      saveDatabase();
    }
    super.setDefaultCloseOperation(operation);
  }

  private void addMainMenu() {
    // Create a new JPanel with GridBagLayout
    MyPanel redPanel = new MyPanel("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/chelTrkrMainMenu.jpeg");

    redPanel.setLayout(new GridBagLayout());
    // Set the background color of the JPanel to red

    // Add the red panel to the JFrame
    add(redPanel);

    // Add Panel For title
    GridBagConstraints titleConstraints = new GridBagConstraints();
    titleConstraints.gridx = 0;
    titleConstraints.gridy = 0;
    titleConstraints.weightx = 1.0;
    titleConstraints.anchor = GridBagConstraints.NORTH;
    titleConstraints.insets = new Insets(10, 0, 10, 0); // Add padding
    redPanel.add(titlePanel(), titleConstraints);

    // Add Panel for main menu
    GridBagConstraints mainMenuConstraints = new GridBagConstraints();
    mainMenuConstraints.gridx = 0;
    mainMenuConstraints.gridy = 1;
    mainMenuConstraints.weightx = 1.0;
    mainMenuConstraints.anchor = GridBagConstraints.CENTER;
    redPanel.add(mainMenu(), mainMenuConstraints);
  }

  public JPanel titlePanel() {
    // Create a new JLabel with the title text "Chel Trkr"
    OutlinedTextLabel titleLabel = new OutlinedTextLabel("Chel Trkr 1.0.2", iceBlue, red, 4);

    // Set the font to be big and bold
    titleLabel.setFont(new Font("Impact", Font.BOLD, 200)); // Larger font

    // Set the background of the label and panel to be transparent
    titleLabel.setOpaque(false);

    JPanel titlePanel = new JPanel();
    titlePanel.add(titleLabel);

    // Make the panel itself transparent
    titlePanel.setOpaque(false);

    return titlePanel;
  }

  public JPanel mainMenu() {
    JPanel mainMenu = new JPanel(new GridBagLayout());
    mainMenu.setOpaque(false); // Make the mainMenu panel transparent

    // Create and customize buttons
    JButton newGameButton = createButtonA("New Game");
    JButton viewStatsButton = createButtonA("View Stats");
    JButton addPlayerButton = createButtonA("Add Player"); // Create and customize the "Add Player" button

    newGameButton.addActionListener(e -> {
      performNewGameAction();
      saveDatabase();
    });

    viewStatsButton.addActionListener(e -> {
      for (Game game : database.getGameList()) {
        System.out.println(game.getHomeTeam() + game.getAwayTeam());
      }
      saveDatabase();
      viewStatsFunction();
    });

    addPlayerButton.addActionListener(e -> {
      // Concatenate the generated characters to create the player name.
      String playerName = JOptionPane.showInputDialog("Enter Name:");

      if (playerName != null && !playerName.isEmpty()) {
        database.addPlayer(playerName, database);
        saveDatabase();
      }
    });

    // Make buttons white with bold text
    Color buttonTextColor = red;
    Font buttonFont = new Font("Bookman Old Style", Font.BOLD, 40);

    newGameButton.setForeground(buttonTextColor);
    newGameButton.setFont(buttonFont);

    viewStatsButton.setForeground(buttonTextColor);
    viewStatsButton.setFont(buttonFont);

    addPlayerButton.setForeground(buttonTextColor);
    addPlayerButton.setFont(buttonFont);

    // Make buttons wider and taller
    Dimension buttonSize = new Dimension(400, 60); // Adjust width and height here

    newGameButton.setPreferredSize(buttonSize);
    viewStatsButton.setPreferredSize(buttonSize);
    addPlayerButton.setPreferredSize(buttonSize);

    // Add mouse listeners to handle hover events
    newGameButton.addMouseListener(new HoverListener(newGameButton, buttonFont));
    viewStatsButton.addMouseListener(new HoverListener(viewStatsButton, buttonFont));
    addPlayerButton.addMouseListener(new HoverListener(addPlayerButton, buttonFont));

    GridBagConstraints buttonConstraints = new GridBagConstraints();
    buttonConstraints.gridx = 0;
    buttonConstraints.gridy = GridBagConstraints.RELATIVE;
    buttonConstraints.insets = new Insets(10, 0, 10, 0);

    mainMenu.add(newGameButton, buttonConstraints);
    mainMenu.add(viewStatsButton, buttonConstraints);
    mainMenu.add(addPlayerButton, buttonConstraints);

    return mainMenu;
  }

  private JButton createButtonA(String text) {
    JButton button = new JButton(text);
    button.setOpaque(false); // Make the button transparent
    button.setContentAreaFilled(false); // Remove the default background
    button.setBorderPainted(false); // Remove the border
    return button;
  }

  public void confirmNewGameAction(Player selectedHomePlayer, Player selectedAwayPlayer, String selectedHomeTeam, String selectedAwayTeam){
    JFrame confirmFrame = new JFrame();
    confirmFrame.setSize(400, 550);
    confirmFrame.setResizable(false);
    confirmFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    confirmFrame.setLocationRelativeTo(null);

    ConfirmationScreen confirmation = new ConfirmationScreen(selectedHomePlayer, selectedAwayPlayer, selectedHomeTeam, selectedAwayTeam,
        homeGoals, awayGoals, homeHits, awayHits, homeTimeOnAttack, awayTimeOnAttack,
        homePassingPercentage, awayPassingPercentage, homeFaceoffs, awayFaceoffs,
        homePenaltyMinutes, awayPenaltyMinutes, homePowerPlayMinutes, awayPowerPlayMinutes,
        homePowerPlays, awayPowerPlays, homeShorthandedGoals, awayShorthandedGoals, homeShots, awayShots);

    confirmFrame.add(confirmation, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.GRAY);
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

    JButton goBackButton = new JButton("Go Back");
    JButton confirmButton = new JButton("Confirm");

    // Add action listeners to the buttons
    goBackButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Handle goBackButton action here (e.g., close the confirmation dialog)
        confirmFrame.dispose();
      }
    });

    confirmButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          Game newGame =
              new Game(selectedHomePlayer, selectedAwayPlayer, selectedHomeTeam, selectedAwayTeam,
                  homeGoals, awayGoals, homeHits, awayHits, homeTimeOnAttack, awayTimeOnAttack,
                  homePassingPercentage, awayPassingPercentage, homeFaceoffs, awayFaceoffs,
                  homePenaltyMinutes, awayPenaltyMinutes, homePowerPlayMinutes, awayPowerPlayMinutes,
                  homePowerPlays, awayPowerPlays, homeShorthandedGoals, awayShorthandedGoals, database, homeShots, awayShots, confirmation);
          newGame.print();
          confirmFrame.dispose();
        } catch (Exception f){
          f.printStackTrace();
          System.out.print("I went wrong");
        }
      }
    });

    buttonPanel.add(Box.createHorizontalGlue());
    buttonPanel.add(goBackButton);
    buttonPanel.add(Box.createHorizontalStrut(10)); // Add 10 pixels of spacing
    buttonPanel.add(confirmButton);
    buttonPanel.add(Box.createHorizontalGlue());

    confirmFrame.add(buttonPanel, BorderLayout.SOUTH);



    confirmFrame.setVisible(true);
  }



  public void performNewGameAction() {


    // Remove all components from the JFrame
    getContentPane().removeAll();

    // Create a new JPanel to hold both the red and blue panels
    JPanel splitPanel = new JPanel(new GridBagLayout());

    // Create a new JPanel with a blue background for the left (blue) panel
    JPanel bluePanel = new JPanel(new GridBagLayout());
    bluePanel.setBackground(Color.BLUE);

    // Create a new JPanel with a red background for the right (red) panel
    JPanel redPanel = new JPanel(new GridBagLayout());
    redPanel.setBackground(Color.RED);

    // Create labels for both panels
    JLabel awayLabel = createLabel("Away");
    JLabel homeLabel = createLabel("Home");

    // Create buttons for the blue panel
    // Assuming database is your database object
    ArrayList<Player> players = database.getPlayerList();
    DefaultComboBoxModel<String> playerModelAway = new DefaultComboBoxModel<>();

    // Populate the combo box model with player names
    for (Player player : players) {
      playerModelAway.addElement(player.getName());
    }

    DefaultComboBoxModel<String> playerModelHome = new DefaultComboBoxModel<>();

    // Populate the combo box model with player names
    for (Player player : players) {
      playerModelHome.addElement(player.getName());
    }

    // Create the JComboBox with the populated model
    JComboBox<String> awayPlayer = new JComboBox<>(playerModelAway);
    JComboBox<String> awayTeam = new JComboBox<>(nhlTeams);
    JButton blueScoreButton = createButton("Score");

    // Create buttons for the red panel
    JComboBox<String> homePlayer = new JComboBox<>(playerModelHome);
    JComboBox<String> homeTeam = new JComboBox<>(nhlTeams);
    JButton redScoreButton = createButton("Score");

    blueScoreButton.addActionListener(e -> {
      saveDatabase();
      showScoreDialogAway();
    });

    redScoreButton.addActionListener(e -> {
      saveDatabase();
      showScoreDialogHome();
    });


    // Create the "Back to Main Menu" button
    JButton backMainMenuButton = backMainMenu();

    // Create a "Finish" button
    JButton finishButton = createButton("Finish");

    // Add action listener to the "Finish" button
    finishButton.addActionListener(e ->  {

      saveDatabase();
      String selectedAwayPlayerName = (String) awayPlayer.getSelectedItem();
      Player selectedAwayPlayer = null;
      for (Player player : database.getPlayerList()) {
        if (player.getName().equals(selectedAwayPlayerName)) {
          selectedAwayPlayer = player;
          break; // Exit the loop once the player is found
        }
      }

      String selectedHomePlayerName = (String) homePlayer.getSelectedItem();
      Player selectedHomePlayer = null;
      for (Player away : database.getPlayerList()) {
        if (away.getName().equals(selectedHomePlayerName)) {
          selectedHomePlayer = away;
          break; // Exit the loop once the player is found
        }
      }

      String selectedHomeTeam = (String) homeTeam.getSelectedItem();
      String selectedAwayTeam = (String) awayTeam.getSelectedItem();


      confirmNewGameAction(selectedHomePlayer, selectedAwayPlayer, selectedHomeTeam, selectedAwayTeam);

    });


    // Create GridBagConstraints for buttons
    GridBagConstraints buttonConstraints = new GridBagConstraints();
    buttonConstraints.gridx = 0;
    buttonConstraints.gridy = GridBagConstraints.RELATIVE;
    buttonConstraints.insets = new Insets(10, 0, 10, 0);

    // Add components to the blue panel
    GridBagConstraints blueConstraints = new GridBagConstraints();
    blueConstraints.gridx = 0;
    blueConstraints.gridy = 0;
    bluePanel.add(awayLabel, blueConstraints);

    blueConstraints.gridy = 1;
    bluePanel.add(awayPlayer, buttonConstraints);

    blueConstraints.gridy = 2;
    bluePanel.add(awayTeam, buttonConstraints);

    blueConstraints.gridy = 3;
    bluePanel.add(blueScoreButton, buttonConstraints);

    // Add "Back to Main Menu" button to the blue panel
    blueConstraints.gridy = 4;
    blueConstraints.anchor = GridBagConstraints.SOUTHWEST;
    bluePanel.add(backMainMenuButton, blueConstraints);

    // Create GridBagConstraints for the red panel
    GridBagConstraints redConstraints = new GridBagConstraints();
    redConstraints.gridx = 0;
    redConstraints.gridy = 0;
    redPanel.add(homeLabel, redConstraints);

    redConstraints.gridy = 1;
    redPanel.add(homePlayer, buttonConstraints);

    redConstraints.gridy = 2;
    redPanel.add(homeTeam, buttonConstraints);

    redConstraints.gridy = 3;
    redPanel.add(redScoreButton, buttonConstraints);

    finishButton.setPreferredSize(new Dimension(80, 30)); // Adjust the size as needed

    // Set anchor to SOUTHEAST for the "Finish" button in the red panel
    redConstraints.gridx = 0;
    redConstraints.gridy = 4; // You may adjust the row position as needed
    redConstraints.anchor = GridBagConstraints.SOUTHEAST; // Anchor to the bottom right
    redConstraints.insets = new Insets(10, 0, 10, 0); // Set insets to zero for tight positioning
    redPanel.add(finishButton, redConstraints);

    // Create GridBagConstraints for splitPanel
    GridBagConstraints splitConstraints = new GridBagConstraints();
    splitConstraints.gridx = 0;
    splitConstraints.gridy = 0;
    splitConstraints.fill = GridBagConstraints.BOTH;
    splitConstraints.weightx = 0.5;
    splitConstraints.weighty = 1.0;

    // Add the red panel to the left and the blue panel to the right
    splitPanel.add(bluePanel, splitConstraints);
    splitConstraints.gridx = 1;
    splitPanel.add(redPanel, splitConstraints);

    // Add the split panel to the JFrame
    add(splitPanel);

    // Repaint the JFrame to reflect the changes
    revalidate();
    repaint();
  }

  public void showScoreDialogHome() {
    JPanel panel = new JPanel(new GridLayout(10, 2)); // Adjust rows and columns as needed

    // Create input fields for data entry
    JTextField goalsField = new JTextField(10);
    JTextField shotsField = new JTextField(10);
    JTextField hitsField = new JTextField(10);
    JTextField timeOnAttackField = new JTextField(10);
    JTextField passingPercentageField = new JTextField(10);
    JTextField faceoffsWonField = new JTextField(10);
    JTextField penaltyMinutesField = new JTextField(10);
    JTextField powerplaysField = new JTextField(10);
    JTextField powerplayMinutesField = new JTextField(10);
    JTextField shorthandedGoalsField = new JTextField(10);

    // Add labels and input fields to the panel
    panel.add(new JLabel("Goals:"));
    panel.add(goalsField);
    panel.add(new JLabel("Total Shots:"));
    panel.add(shotsField);
    panel.add(new JLabel("Hits:"));
    panel.add(hitsField);
    panel.add(new JLabel("Time on Attack:"));
    panel.add(timeOnAttackField);
    panel.add(new JLabel("Passing %:"));
    panel.add(passingPercentageField);
    panel.add(new JLabel("Faceoffs Won:"));
    panel.add(faceoffsWonField);
    panel.add(new JLabel("Penalty minutes:"));
    panel.add(penaltyMinutesField);
    panel.add(new JLabel("Powerplays:"));
    panel.add(powerplaysField);
    panel.add(new JLabel("Powerplay Minutes:"));
    panel.add(powerplayMinutesField);
    panel.add(new JLabel("Shorthanded Goals:"));
    panel.add(shorthandedGoalsField);

    // Show the data entry dialog using JOptionPane
    int result = JOptionPane.showConfirmDialog(this, panel, "Enter Home Data As Shows",
        JOptionPane.OK_CANCEL_OPTION);

    // Check if the user clicked "OK"
    if (result == JOptionPane.OK_OPTION) {
      try {
        // Parse the input data
        int goals = Integer.parseInt(goalsField.getText());
        int shots = Integer.parseInt(shotsField.getText());
        int hits = Integer.parseInt(hitsField.getText());
        String timeOnAttack = timeOnAttackField.getText();
        double passingPercentage = Double.parseDouble(passingPercentageField.getText());
        int faceoffsWon = Integer.parseInt(faceoffsWonField.getText());
        String penaltyMinutes = penaltyMinutesField.getText();
        String powerplays = powerplaysField.getText();
        String powerplayMinutes = powerplayMinutesField.getText();
        int shorthandedGoals = Integer.parseInt(shorthandedGoalsField.getText());

        // Update the relevant variables based on the team (home or away)
          // Update home team variables here
          homeGoals = goals;
          homeShots = shots;
          homeHits = hits;
          homeTimeOnAttack = timeOnAttack;
          homePassingPercentage = passingPercentage;
          homeFaceoffs = faceoffsWon;
          homePenaltyMinutes = penaltyMinutes;
          homePowerPlays = powerplays;
          homePowerPlayMinutes = powerplayMinutes;
          homeShorthandedGoals = shorthandedGoals;
      } catch (NumberFormatException e) {
        // Handle invalid input (e.g., non-numeric values)
        JOptionPane.showMessageDialog(this, "Invalid input.", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  public void showScoreDialogAway() {
    JPanel panel = new JPanel(new GridLayout(10, 2)); // Adjust rows and columns as needed

    // Create input fields for data entry
    JTextField goalsField = new JTextField(10);
    JTextField shotsField = new JTextField(10);
    JTextField hitsField = new JTextField(10);
    JTextField timeOnAttackField = new JTextField(10);
    JTextField passingPercentageField = new JTextField(10);
    JTextField faceoffsWonField = new JTextField(10);
    JTextField penaltyMinutesField = new JTextField(10);
    JTextField powerplaysField = new JTextField(10);
    JTextField powerplayMinutesField = new JTextField(10);
    JTextField shorthandedGoalsField = new JTextField(10);

    // Add labels and input fields to the panel
    panel.add(new JLabel("Goals:"));
    panel.add(goalsField);
    panel.add(new JLabel("Total Shots:"));
    panel.add(shotsField);
    panel.add(new JLabel("Hits:"));
    panel.add(hitsField);
    panel.add(new JLabel("Time on Attack:"));
    panel.add(timeOnAttackField);
    panel.add(new JLabel("Passing %:"));
    panel.add(passingPercentageField);
    panel.add(new JLabel("Faceoffs Won:"));
    panel.add(faceoffsWonField);
    panel.add(new JLabel("Penalty minutes:"));
    panel.add(penaltyMinutesField);
    panel.add(new JLabel("Powerplays:"));
    panel.add(powerplaysField);
    panel.add(new JLabel("Powerplay Minutes:"));
    panel.add(powerplayMinutesField);
    panel.add(new JLabel("Shorthanded Goals:"));
    panel.add(shorthandedGoalsField);

    // Show the data entry dialog using JOptionPane
    int result = JOptionPane.showConfirmDialog(this, panel, "Enter Away Data as Shows",
        JOptionPane.OK_CANCEL_OPTION);

    // Check if the user clicked "OK"
    if (result == JOptionPane.OK_OPTION) {
      try {
        // Parse the input data
        int goals = Integer.parseInt(goalsField.getText());
        int shots = Integer.parseInt(shotsField.getText());
        int hits = Integer.parseInt(hitsField.getText());
        String timeOnAttack = timeOnAttackField.getText();
        double passingPercentage = Double.parseDouble(passingPercentageField.getText());
        int faceoffsWon = Integer.parseInt(faceoffsWonField.getText());
        String penaltyMinutes = penaltyMinutesField.getText();
        String powerplays = powerplaysField.getText();
        String powerplayMinutes = powerplayMinutesField.getText();
        int shorthandedGoals = Integer.parseInt(shorthandedGoalsField.getText());

        awayGoals = goals;
        awayShots = shots;
        awayHits = hits;
        awayTimeOnAttack = timeOnAttack;
        awayPassingPercentage = passingPercentage;
        awayFaceoffs = faceoffsWon;
        awayPenaltyMinutes = penaltyMinutes;
        awayPowerPlays = powerplays;
        awayPowerPlayMinutes = powerplayMinutes;
        awayShorthandedGoals = shorthandedGoals;

        System.out.println(awayGoals);
      } catch (NumberFormatException e) {
        // Handle invalid input (e.g., non-numeric values)
        JOptionPane.showMessageDialog(this, "Invalid input.", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    }
  }
  private JLabel createLabel(String text) {
    JLabel label = new JLabel(text);
    label.setFont(new Font("Serif", Font.BOLD, 36)); // Adjust font size as needed
    return label;
  }

  private JButton createButton(String text) {
    JButton button = new JButton(text);
    button.setPreferredSize(new Dimension(200, 50)); // Adjust button size as needed
    return button;
  }

  public JButton backMainMenu(){
    JButton back = new JButton("Back");

    back.addActionListener(e -> {
      saveDatabase();
      mainMenuFunction();
    });

    return back;
  }

  public void mainMenuFunction(){
    // Remove all components from the JFrame
    getContentPane().removeAll();

    addMainMenu();

    revalidate();
    repaint();
  }

  public void viewStatsFunction(){
    getContentPane().removeAll();

    viewStatsDisplay();

    revalidate();
    repaint();
  }

  public void viewStatsDisplay() {
    ArrayList<JLabel> rows = new ArrayList<>();
    JPanel redPanel = new JPanel(new GridBagLayout());
    redPanel.setBackground(Color.RED);

    // Create the upper (blue) panel
    JPanel upperPanel = new JPanel();
    upperPanel.setBackground(Color.BLUE);

    // Create the middle (grey) panel
    JPanel middlePanel = new JPanel();
    middlePanel.setBackground(Color.GRAY);
    middlePanel.setLayout(new GridBagLayout()); // Use GridBagLayout for the middle panel

    // Create the lower (white) panel
    JPanel lowerPanel = new JPanel();
    lowerPanel.setBackground(Color.WHITE);

    // Create a "Back" button using the backMainMenu method
    JButton backButton = backMainMenu();

    // Create a "More" button
    JButton moreButton = new JButton("More");

    moreButton.addActionListener(e -> {
      saveDatabase();
      moreStats();
    });


    // Create GridBagConstraints for the "Back" button
    GridBagConstraints buttonConstraints = new GridBagConstraints();
    buttonConstraints.anchor = GridBagConstraints.NORTHWEST;
    buttonConstraints.insets = new Insets(10, 10, 10, 10); // Add some padding

    // Add the "Back" button to the left side of the lower panel
    lowerPanel.add(backButton);

    // Create GridBagConstraints for the "More" button
    GridBagConstraints moreButtonConstraints = new GridBagConstraints();
    moreButtonConstraints.anchor = GridBagConstraints.NORTHEAST;
    moreButtonConstraints.insets = new Insets(10, 10, 10, 10); // Add some padding

    // Add the "More" button to the right side of the lower panel
    lowerPanel.add(moreButton);

    // Create GridBagConstraints for the panels
    GridBagConstraints panelConstraints = new GridBagConstraints();
    panelConstraints.gridx = 0;
    panelConstraints.gridy = 0;
    panelConstraints.gridwidth = 2; // Make the upper panel span the width
    panelConstraints.weightx = 1.0;
    panelConstraints.anchor = GridBagConstraints.PAGE_START;

    JLabel kingLabel = new JLabel("KING OF THE HOUSE:");
    kingLabel.setFont(new Font("Serif", Font.BOLD, 100));
    kingLabel.setForeground(Color.WHITE);

    Player kingPlayer = findKingPlayer(database.getPlayerList());
    if (kingPlayer != null) {
      kingLabel.setText("KING OF THE HOUSE: " + kingPlayer.getName());
    }

    // Add the label to the upper (blue) panel
    upperPanel.add(kingLabel);

    redPanel.add(upperPanel, panelConstraints);

    panelConstraints.gridy = 1;
    panelConstraints.gridwidth = 1;
    panelConstraints.fill = GridBagConstraints.BOTH;
    panelConstraints.weightx = 1.0;

    // Add the middle panel to the red panel
    redPanel.add(middlePanel, panelConstraints);

    // Create GridBagConstraints for the rows within the middle panel
    GridBagConstraints rowConstraints = new GridBagConstraints();
    rowConstraints.gridx = 0;
    rowConstraints.gridy = GridBagConstraints.RELATIVE;
    rowConstraints.fill = GridBagConstraints.HORIZONTAL;
    rowConstraints.weightx = 1.0;
    rowConstraints.insets = new Insets(5, 5, 5, 5); // Adjust padding as needed

    // Create and add components to each row panel
    for (int i = 0; i < 5; i++) {
      JPanel rowPanel = new JPanel();
      rowPanel.setBackground(Color.GRAY);

      // Customize each row panel and add text or components
      JLabel rowLabel = new JLabel(); // Example text
      rows.add(rowLabel);
      rowPanel.add(rowLabel);

      middlePanel.add(rowPanel, rowConstraints);
    }

    rows.get(0).setText("Most Wins: ");
    rows.get(0).setForeground(Color.WHITE);
    rows.get(0).setFont(new Font("Serif", Font.BOLD, 48));

    rows.get(1).setText("Longest Streak: ");
    rows.get(1).setForeground(Color.WHITE);
    rows.get(1).setFont(new Font("Serif", Font.BOLD, 48));

    rows.get(2).setText("Most Goals: ");
    rows.get(2).setForeground(Color.WHITE);
    rows.get(2).setFont(new Font("Serif", Font.BOLD, 48));

    rows.get(3).setText("Best Passer: ");
    rows.get(3).setForeground(Color.WHITE);
    rows.get(3).setFont(new Font("Serif", Font.BOLD, 48));

    rows.get(4).setText("Biggest Hitter: ");
    rows.get(4).setForeground(Color.WHITE);
    rows.get(4).setFont(new Font("Serif", Font.BOLD, 48));

    Player mostWinsPlayer = findPlayerWithMostWins(database.getPlayerList());
    Player longestStreakPlayer = findPlayerWithLongestWinStreak(database.getPlayerList());
    Player topScorer = findPlayerWithMostGoals(database.getPlayerList());
    Player bestPasser = findPlayerWithBestPassPercentage(database.getPlayerList());
    Player biggestHitter = findPlayerWithMostHitsPerGame(database.getPlayerList());

    if (mostWinsPlayer != null) {
      rows.get(0).setText("Most Wins: " + mostWinsPlayer.getName() + " (" + mostWinsPlayer.getWins() + " wins)");
    }
    if (longestStreakPlayer != null) {
      rows.get(1).setText("Longest Streak: " + longestStreakPlayer.getName() + " (" + longestStreakPlayer.getLongestWinStreak() + " games)");
    }
    if (topScorer != null) {
      rows.get(2).setText("Most Goals: " + topScorer.getName() + " (" + topScorer.getGoalsFor() + ")");
    }
    if (bestPasser != null) {
      rows.get(3).setText("Best Passer: " + bestPasser.getName() + " (" + bestPasser.getPassPercentage() + "% Pass Percentage)");
    }
    if (biggestHitter != null) {
      rows.get(4).setText("Biggest Hitter: " + biggestHitter.getName() + " (" + biggestHitter.getHitsPerGame() + " Hits)");
    }


    panelConstraints.gridy = 2;
    panelConstraints.gridwidth = 2; // Make the lower panel span the width
    panelConstraints.weightx = 1.0;
    panelConstraints.anchor = GridBagConstraints.PAGE_END;

    // Add the lower panel to the red panel
    redPanel.add(lowerPanel, panelConstraints);

    // Add the red panel to the JFrame
    add(redPanel);

    revalidate();
    repaint();
  }

  public Player findPlayerWithMostHitsPerGame(ArrayList<Player> players) {
    Player biggestHitter = null;
    double maxHitsPerGame = -1.0;

    for (Player player : players) {
      double hitsPerGame = player.getHitsPerGame();
      if (hitsPerGame > maxHitsPerGame) {
        maxHitsPerGame = hitsPerGame;
        biggestHitter = player;
      }
    }

    return biggestHitter;
  }
  public Player findPlayerWithBestPassPercentage(ArrayList<Player> players) {
    Player bestPasser = null;
    double highestPassPercentage = 0.0;

    for (Player player : players) {
      double passPercentage = player.getPassPercentage();
      if (passPercentage > highestPassPercentage) {
        highestPassPercentage = passPercentage;
        bestPasser = player;
      }
    }

    return bestPasser;
  }

  public Player findPlayerWithMostGoals(ArrayList<Player> players) {
    Player topScorer = null;
    int mostGoals = 0;

    for (Player player : players) {
      int goals = player.getGoalsFor();

      if (goals > mostGoals) {
        mostGoals = goals;
        topScorer = player;
      }
    }

    return topScorer;
  }

  public Player findPlayerWithLongestWinStreak(ArrayList<Player> players) {
    Player longestStreakPlayer = null;
    int longestStreak = 0;

    for (Player player : players) {
      int currentStreak = player.getLongestWinStreak();

      if (currentStreak > longestStreak) {
        longestStreak = currentStreak;
        longestStreakPlayer = player;
      }
    }

    return longestStreakPlayer;
  }
  private Player findPlayerWithMostWins(ArrayList<Player> players) {
    Player mostWinsPlayer = null;
    int maxWins = -1;

    for (Player player : players) {
      if (player.getWins() > maxWins) {
        maxWins = player.getWins();
        mostWinsPlayer = player;
      }
    }

    return mostWinsPlayer;
  }

  private Player findKingPlayer(ArrayList<Player> players) {
    for (Player player : players) {
      if (player.isKing()) {
        return player;
      }
    }
    return null; // No king found
  }

  public void moreStats() {
    // Clear the entire frame
    getContentPane().removeAll();

    // Create a new panel with a grey background for more stats
    JPanel moreStatsPanel = new JPanel(new GridBagLayout());
    moreStatsPanel.setBackground(Color.GRAY);

    // Create the centered (center) panel
    JPanel centerPanel = new JPanel(new BorderLayout());
    centerPanel.setBackground(Color.GRAY);

    // Create the lower (white) panel
    JPanel lowerPanel = new JPanel();
    lowerPanel.setBackground(Color.WHITE);

    // Create a "Back" button
    JButton backButton = new JButton("Back");

    // Add an ActionListener to the "Back" button to go back to the main stats display
    backButton.addActionListener(e -> viewStatsFunction());

    // Create GridBagConstraints for the "Back" button
    GridBagConstraints buttonConstraints = new GridBagConstraints();
    buttonConstraints.anchor = GridBagConstraints.NORTHWEST;
    buttonConstraints.insets = new Insets(10, 10, 10, 10); // Add some padding

    // Add the "Back" button to the lower panel
    lowerPanel.add(backButton);

    JButton viewGameHistoryButton = new JButton("View Game History");

    // Add an ActionListener to the "View Game History" button
    viewGameHistoryButton.addActionListener(e -> {
      saveDatabase();
      viewGameHistory();
    });

    // Create GridBagConstraints for the "View Game History" button
    GridBagConstraints viewGameHistoryButtonConstraints = new GridBagConstraints();
    viewGameHistoryButtonConstraints.anchor = GridBagConstraints.NORTHEAST;
    viewGameHistoryButtonConstraints.insets = new Insets(10, 10, 10, 10); // Add some padding

    // Add the "View Game History" button to the lower panel
    lowerPanel.add(viewGameHistoryButton, viewGameHistoryButtonConstraints);

    // Create GridBagConstraints for the panels
    GridBagConstraints panelConstraints = new GridBagConstraints();
    panelConstraints.gridx = 0;
    panelConstraints.gridy = 0;
    panelConstraints.gridwidth = 2; // Make the center panel span the width
    panelConstraints.weightx = 1.0;
    panelConstraints.fill = GridBagConstraints.BOTH;

    // Create the PlayerTableModel with your list of players
    PlayerTableModel playerTableModel = new PlayerTableModel(database.getPlayerList());


    // Create a JTable with the PlayerTableModel
    JTable playerTable = new JTable(playerTableModel);
    JPopupMenu popupMenu = playerTableModel.createPopupMenu(playerTable);
    playerTable.setComponentPopupMenu(popupMenu);
    // Set a preferred size for the table
    playerTable.setPreferredScrollableViewportSize(new Dimension(800, 600)); // Adjust the size as needed

    // Create a JScrollPane with the JTable
    JScrollPane scrollPane = new JScrollPane(playerTable);

    // Set a preferred size for the scroll pane
    scrollPane.setPreferredSize(new Dimension(800, 600)); // Adjust the size as needed

    // Add the JScrollPane to the center panel
    centerPanel.add(scrollPane, BorderLayout.CENTER);

    // Add the center panel to the more stats panel
    moreStatsPanel.add(centerPanel, panelConstraints);

    panelConstraints.gridy = 1;
    panelConstraints.gridwidth = 2; // Make the lower panel span the width
    panelConstraints.weightx = 1.0;
    panelConstraints.anchor = GridBagConstraints.PAGE_END;

    // Add the lower panel to the more stats panel
    moreStatsPanel.add(lowerPanel, panelConstraints);

    // Add the more stats panel to the JFrame
    add(moreStatsPanel);

    // Repaint the frame to reflect the changes
    revalidate();
    repaint();
  }

  public void viewGameHistory(){
    // Clear the entire frame
    getContentPane().removeAll();

    // Create a new panel with a grey background for more stats
    JPanel viewGameHistoryPanel = new JPanel(new GridBagLayout());
    viewGameHistoryPanel.setBackground(Color.BLACK);

    // Create the centered (center) panel
    JPanel centerPanel = new JPanel(new BorderLayout());
    centerPanel.setBackground(Color.GRAY);

    // Create the lower (white) panel
    JPanel lowerPanel = new JPanel();
    lowerPanel.setBackground(Color.WHITE);

    // Create a "Back" button
    JButton backButton = new JButton("Back");

    // Add an ActionListener to the "Back" button to go back to the main stats display
    backButton.addActionListener(e -> moreStats());

    // Create GridBagConstraints for the "Back" button
    GridBagConstraints buttonConstraints = new GridBagConstraints();
    buttonConstraints.anchor = GridBagConstraints.NORTHWEST;
    buttonConstraints.insets = new Insets(10, 10, 10, 10); // Add some padding

    // Add the "Back" button to the lower panel
    lowerPanel.add(backButton);

    // Create GridBagConstraints for the panels
    GridBagConstraints panelConstraints = new GridBagConstraints();
    panelConstraints.gridx = 0;
    panelConstraints.gridy = 0;
    panelConstraints.gridwidth = 2; // Make the center panel span the width
    panelConstraints.weightx = 1.0;
    panelConstraints.fill = GridBagConstraints.BOTH;

    // Create the PlayerTableModel with your list of players
    GameHistoryTableModel gameHistoryTableModel = new GameHistoryTableModel(database.getGameList());


    // Create a JTable with the PlayerTableModel
    JTable playerTable = new JTable(gameHistoryTableModel);
    JPopupMenu popupMenu = new JPopupMenu();
    JMenuItem yourMenuItem = new JMenuItem("View Stats");
    popupMenu.add(yourMenuItem);
    playerTable.setComponentPopupMenu(popupMenu);

    yourMenuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int selectedRow = playerTable.getSelectedRow();
        if (selectedRow >= 0) {
          // Get the selected game or data from the selected row
          Game selectedGame = gameHistoryTableModel.getGameAtRow(selectedRow);

          JFrame confirmFrame = new JFrame();
          confirmFrame.setSize(400, 550);
          confirmFrame.setResizable(false);
          confirmFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          confirmFrame.setLocationRelativeTo(null);

          confirmFrame.add(selectedGame.bestIdeaEver());
          confirmFrame.setVisible(true);
          // Perform your action based on the selectedGame
          // Modify this part according to your specific use case.
        }
      }
    });
    // Set a preferred size for the table
    playerTable.setPreferredScrollableViewportSize(new Dimension(800, 600)); // Adjust the size as needed

    // Create a JScrollPane with the JTable
    JScrollPane scrollPane = new JScrollPane(playerTable);

    // Set a preferred size for the scroll pane
    scrollPane.setPreferredSize(new Dimension(800, 600)); // Adjust the size as needed

    // Add the JScrollPane to the center panel
    centerPanel.add(scrollPane, BorderLayout.CENTER);

    // Add the center panel to the more stats panel
    viewGameHistoryPanel.add(centerPanel, panelConstraints);

    panelConstraints.gridy = 1;
    panelConstraints.gridwidth = 2; // Make the lower panel span the width
    panelConstraints.weightx = 1.0;
    panelConstraints.anchor = GridBagConstraints.PAGE_END;

    // Add the lower panel to the more stats panel
    viewGameHistoryPanel.add(lowerPanel, panelConstraints);

    // Add the more stats panel to the JFrame
    add(viewGameHistoryPanel);

    // Repaint the frame to reflect the changes
    revalidate();
    repaint();
  }

  public void saveDatabase() {
      try {
        FileOutputStream fileOut = new FileOutputStream("PlayerDatabase.sav");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(database);
        out.close();
        fileOut.close();
      } catch (IOException e) {
        System.out.print(e.getClass() + ": " + e.getMessage());
      }
  }


  public static void main(String[] args) {
    Display frame = new Display();
  }
}

