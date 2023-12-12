package main.java;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class MyPanel extends JPanel {
  private BufferedImage backgroundImage;

  public MyPanel(String imagePath) {
    try {
      backgroundImage = ImageIO.read(new File(imagePath)); // Provide the path to your image file
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (backgroundImage != null) {
      g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
  }
}
