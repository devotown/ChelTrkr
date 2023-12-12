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

public class GameHistoryTableModel extends AbstractTableModel {
    private ArrayList<Game> games;
    private String[] columnNames = {"Time","Home Player", "Home Team", "Home Score", " ", "Away Score", "Away Team", "Away Player", "King Match"};

    public GameHistoryTableModel(ArrayList<Game> games) {
      this.games = games;
    }

    @Override
    public int getRowCount() {
      return games.size();
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
      Game game = games.get(rowIndex);

      // Return the appropriate data based on the column index
      switch (columnIndex) {
        case 0:
          return game.getTimeAndDate();
        case 1:
          return game.getHomePlayer().getName();
        case 2:
          return game.getHomeTeam();
        case 3:
          return game.getHomeGoals();
        case 4:
          return "vs.";
        case 5:
          return game.getAwayGoals();
        case 6:
          return game.getAwayTeam();
        case 7:
          return game.getAwayPlayer().getName();
        case 8:
          return game.isKingMatch();

        default:
          return null;
      }
    }
  public Game getGameAtRow(int row) {
    if (row >= 0 && row < games.size()) {
      return games.get(row);
    } else {
      return null; // Return null if the row is out of bounds
    }
  }
  }

