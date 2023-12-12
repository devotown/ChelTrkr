package main.java;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class OutlinedTextLabel extends JLabel {
  private Color textColor;
  private Color outlineColor;
  private int outlineSize;

  public OutlinedTextLabel(String text, Color textColor, Color outlineColor, int outlineSize) {
    super(text);
    this.textColor = textColor;
    this.outlineColor = outlineColor;
    this.outlineSize = outlineSize;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    // Create a BufferedImage to render the text with an outline
    BufferedImage textImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics2D textGraphics = textImage.createGraphics();

    // Draw the text with an opaque outline
    textGraphics.setComposite(AlphaComposite.SrcOver);
    textGraphics.setFont(getFont());

    // Draw the outline
    textGraphics.setColor(outlineColor);
    textGraphics.drawString(getText(), 0, getFontMetrics(getFont()).getAscent());

    // Draw the text on top of the outline
    textGraphics.setColor(textColor);
    textGraphics.drawString(getText(), outlineSize, getFontMetrics(getFont()).getAscent() + outlineSize);

    // Draw the text image onto the panel
    g2d.drawImage(textImage, 0, 0, null);
  }
}
