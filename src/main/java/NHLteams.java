package main.java;

import javax.swing.*;
import java.awt.*;

public class NHLteams {
  private JLabel teamImage;
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

  private static final String[] shortenedTeamNames = {
      "ANA", "ARI", "BOS", "BUF", "CGY", "CAR", "CHI", "COL", "CBJ", "DAL", "DET", "EDM", "FLA", "LAK", "MIN", "MTL",
      "NSH", "NJD", "NYI", "NYR", "OTT", "PHI", "PIT", "SJS", "SEA", "STL", "TBL", "TOR", "VAN", "VGK", "WSH", "WPG"
  };

  private ImageIcon selectedImage;

  private String selectedTeam;

  public static final ImageIcon Anaheim_Ducks = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Anaheim.png");
  public static final ImageIcon Arizona_Coyotes = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Arizona.png");
  public static final ImageIcon Boston_Bruins = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Boston.png");
  public static final ImageIcon Buffalo_Sabres = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Buffalo.png");
  public static final ImageIcon Calgary_Flames = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Calgary.png");
  public static final ImageIcon Carolina_Hurricanes = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Carolina.png");
  public static final ImageIcon Chicago_Blackhawks = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Chicago.png");
  public static final ImageIcon Colorado_Avalanche = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Colorado.png");
  public static final ImageIcon Columbus_Blue_Jackets = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Columbus.png");
  public static final ImageIcon Dallas_Stars = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Dallas.png");
  public static final ImageIcon Detroit_Red_Wings = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Detroit.png");
  public static final ImageIcon Edmonton_Oilers = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Edmonton.png");
  public static final ImageIcon Florida_Panthers = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Florida.png");
  public static final ImageIcon Los_Angeles_Kings = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Los Angeles.png");
  public static final ImageIcon Minnesota_Wild = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Minnesota.png");
  public static final ImageIcon Montreal_Canadiens = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Montreal.png");
  public static final ImageIcon Nashville_Predators = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Nashville.png");
  public static final ImageIcon New_Jersey_Devils = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/New Jersey.png");
  public static final ImageIcon New_York_Islanders = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/NY Islanders.png");
  public static final ImageIcon New_York_Rangers = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/NY Rangers.png");
  public static final ImageIcon Ottawa_Senators = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Ottawa.png");
  public static final ImageIcon Philadelphia_Flyers = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Philadelphia.png");
  public static final ImageIcon Pittsburgh_Penguins = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Pittsburgh.png");
  public static final ImageIcon San_Jose_Sharks = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/San Jose.png");
  public static final ImageIcon Seattle_Kraken = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Seattle.png");
  public static final ImageIcon St_Louis_Blues = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/St. Louis.png");
  public static final ImageIcon Tampa_Bay_Lightning = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Tampa Bay.png");
  public static final ImageIcon Toronto_Maple_Leafs = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Toronto.png");
  public static final ImageIcon Vancouver_Canucks = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Vancouver 2.png");
  public static final ImageIcon Vegas_Golden_Knights = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Vegas.png");
  public static final ImageIcon Washington_Capitals = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Washington.png");
  public static final ImageIcon Winnipeg_Jets = new ImageIcon("/Users/devontownsend/eclipse-workspace/ChelTrkr/src/Logos/Winnipeg 2.png");

  private static final ImageIcon[] teamIcons = resizeNHLTeamLogos();
  public NHLteams (String selectedTeam){
    int index = findStringIndex(selectedTeam, nhlTeams);

    selectedImage = teamIcons[index];
    this.selectedTeam = shortenedTeamNames[index];
  }
  public static int findStringIndex(String target, String[] array) {
    for (int i = 0; i < array.length; i++) {
      if (target.equals(array[i])) {
        return i; // Return the index of the matching string
      }
    }
    return -1;
  }

  public ImageIcon getSelectedImage() {
    return selectedImage;
  }
  public String getSelectedTeam(){
    return selectedTeam;
  }

  public static ImageIcon[] resizeNHLTeamLogos() {
    ImageIcon[] nhlTeamLogos = {Anaheim_Ducks, Arizona_Coyotes, Boston_Bruins, Buffalo_Sabres,
        Calgary_Flames, Carolina_Hurricanes, Chicago_Blackhawks, Colorado_Avalanche,
        Columbus_Blue_Jackets, Dallas_Stars, Detroit_Red_Wings, Edmonton_Oilers, Florida_Panthers,
        Los_Angeles_Kings, Minnesota_Wild, Montreal_Canadiens, Nashville_Predators,
        New_Jersey_Devils, New_York_Islanders, New_York_Rangers, Ottawa_Senators,
        Philadelphia_Flyers, Pittsburgh_Penguins, San_Jose_Sharks, Seattle_Kraken, St_Louis_Blues,
        Tampa_Bay_Lightning, Toronto_Maple_Leafs, Vancouver_Canucks, Vegas_Golden_Knights,
        Washington_Capitals, Winnipeg_Jets};

    int desiredWidth = 60;
    int desiredHeight = 60;

    for (int i = 0; i < nhlTeamLogos.length; i++) {
      nhlTeamLogos[i] = resizeImageIcon(nhlTeamLogos[i], desiredWidth, desiredHeight);
    }

    return nhlTeamLogos;
  }

  public static ImageIcon resizeImageIcon(ImageIcon originalIcon, int width, int height) {
    Image originalImage = originalIcon.getImage();
    Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(resizedImage);
  }


}
