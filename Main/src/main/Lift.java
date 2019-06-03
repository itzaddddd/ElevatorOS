package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

class Lift extends JPanel {
private BufferedImage bg;
private int numLift;
private int yOffset = 750;
private int yDelta =-5;
private int xPos;
private int yPos = getHeight() - bg.getHeight();
private int direction = 1;

//
//  public Lift(int numLift){
//      numLift = this.numLift;
//      try {
//      
//      bg = ImageIO.read(new File("Image/lift04.png"));
//      
//    } catch (Exception ex) {
//      ex.printStackTrace();
//    }
//  }
  
//  public Lift() {
//    try {
//      
//      bg = ImageIO.read(new File("Image/lift01.png"));
//      
//    } catch (Exception ex) {
//      ex.printStackTrace();
//    }
//
//    Timer timer = new Timer(40, new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent e) {
//        yOffset += yDelta;
//        if (yOffset > getHeight()) {
//            yDelta = 5;
//        }
//        repaint();
//      }
//    });
//    timer.start();
//    if(yOffset==650){
//        timer.stop();
//        yDelta=0;
//    }
//  }

//  public Lift(int direction){
//      direction = this.direction;
//    
// }
  public Lift() {
            try {
                bg = ImageIO.read(new File("Image/lift01.png"));
                Timer timer = new Timer(10, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        yPos += direction;
//                        if (yPos + bg.getWidth() > getWidth()) {
//                            yPos = getWidth() - bg.getWidth();
//                            direction *= -1;
//                        } else if (yPos < 0) {
//                            yPos = 0;
//                            direction *= -1;
//                        }
                        repaint();
                    }

                });
                timer.setRepeats(true);
                timer.setCoalesce(true);
                timer.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

  @Override
public Dimension getPreferredSize() {
            return bg == null ? super.getPreferredSize() : new Dimension(bg.getWidth() * 4, bg.getHeight());
        }



  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    int x = getWidth() - bg.getWidth();
            g.drawImage(bg, x, yPos, this);
  }
}

