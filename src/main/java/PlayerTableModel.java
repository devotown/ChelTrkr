package main.java;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableRowSorter;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerTableModel extends AbstractTableModel {
  private ArrayList<Player> players;
  private String[] columnNames = {"Name", "GP","W", "L", "For", "Against", "Shots", "SPG" ,"Hits", "HPG", "Time Attack", "Pass %","Faceoff %", "Penalty Min", "Power Play %","Shorthanded Goals", "Win Streak", "King Games", "King Streak", "King?"};

  public PlayerTableModel(ArrayList<Player> players) {
    this.players = players;
  }

  @Override
  public int getRowCount() {
    return players.size();
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public String getColumnName(int column) {
    return columnNames[column];
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Player player = players.get(rowIndex);

    // Return the appropriate data based on the column index
    switch (columnIndex) {
      case 0:
        return player.getName();
      case 1:
        return player.getGamesPlayed();
      case 2:
        return player.getWins();
      case 3:
        return player.getLosses();
      case 4:
        return player.getGoalsFor();
      case 5:
        return player.getGoalsAgainst();
      case 6:
        return player.getShots();
      case 7:
        return player.getSPG();
      case 8:
        return player.getHits();
      case 9:
        return player.getHitsPerGame();
      case 10:
        return player.getTimeOnAttackAvg();
      case 11:
        return player.getPassPercentage();
      case 12:
        return player.getFaceoffPercentage();
      case 13:
        return player.getPMPG();
      case 14:
        return player.getPPP();
      case 15:
        return player.getShorthandedGoals();
      case 16:
        return player.getLongestWinStreak();
      case 17:
        return player.getGamesAsKing();
      case 18:
        return player.longestKingStreak();
      case 19:
        if (player.isKing()) {
          return "KING";
        } else {
          return "Peasant.";
        }
      default:
        return null;
    }
  }

  public JPopupMenu createPopupMenu(final JTable table) {
    JPopupMenu popupMenu = new JPopupMenu();

    JMenuItem sortByNameItem = new JMenuItem("Sort by Name");
    JMenuItem sortByWinsItem = new JMenuItem("Sort by Wins");
    JMenuItem setAsKingItem = new JMenuItem("Set as King");

    sortByNameItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        TableRowSorter<PlayerTableModel> sorter = new TableRowSorter<>(PlayerTableModel.this);
        table.setRowSorter(sorter);
        sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
        sorter.sort();
      }
    });

    sortByWinsItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        TableRowSorter<PlayerTableModel> sorter = new TableRowSorter<>(PlayerTableModel.this);
        table.setRowSorter(sorter);
        sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(1, SortOrder.ASCENDING)));
        sorter.sort();
      }
    });

    setAsKingItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
          // Get the player associated with the selected row and implement your logic to set the player as king.
          Player selectedPlayer = players.get(table.convertRowIndexToModel(selectedRow));
          // Add your logic here to set the player as king.
          for (Player player: players) {
            player.setIsKing(false);
          }
          selectedPlayer.setIsKing(true);

        }
      }
    });

    popupMenu.add(sortByNameItem);
    popupMenu.add(sortByWinsItem);
    popupMenu.add(setAsKingItem);

    return popupMenu;
  }
}
