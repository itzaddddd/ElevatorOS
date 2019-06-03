package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

class Broad extends JPanel {
private BufferedImage bg;
int numlift;
int level;

//    public Broad(int numlift,int level){
//       numlift = this.numlift;
//       level = this.level;
//       callBroad();
//    }
//    
//    public void callBroad(){
//        xPos=50;
//        yPos=750;
//        for(i=1;i<=level;i++){
//            for(j=1;j<=numlift;j++){
//                setBroad(xPos,yPos);
//                xPos=60+xPos;
//            }
//            yPos=yPos+60;
//        }
//    }
    
    public Broad(int numlift,int level){
      numlift = this.numlift;
      level = this.level;
      
      try {
      
      bg = ImageIO.read(new File("Image/lift04.png"));
      
    } catch (IOException ex) {
        ex.printStackTrace();
    }
      
  }
    
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    Graphics2D g2d = (Graphics2D) g.create();
    int i,j;
    int xPos=810;
    int yPos=750;
    int level=7;
    int numlift=6;
    
    for(i=1;i<=level;i++){
        for(j=1;j<=numlift;j++){
        g2d.drawImage(bg, xPos, yPos,150,150, this);
        xPos=xPos+92;
        }
        xPos=810;
        yPos=yPos-120;
    }       
    g2d.dispose();
    
  }
}
