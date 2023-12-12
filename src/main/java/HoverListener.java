package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class HoverListener extends MouseAdapter {
  private final JButton button;
  private final Font originalFont;
  private final Font hoverFont;

  public HoverListener(JButton button, Font originalFont) {
    this.button = button;
    this.originalFont = originalFont;
    this.hoverFont = new Font(originalFont.getName(), originalFont.getStyle(), originalFont.getSize() + 10); // Increase font size when hovering
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    button.setFont(hoverFont);
  }

  @Override
  public void mouseExited(MouseEvent e) {
    button.setFont(originalFont);
  }
}
