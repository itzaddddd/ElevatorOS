/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class NewLift {
private String alien3 = "lift01.png";

    private int x;
    private int y;
    private int axd = 1;
    private int ayd;                         
    private int width;
    private int height;
    private boolean visible;
    private Image image;

    public NewLift(int x, int y)
    {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(alien3));
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        visible = true;
        this.x = x;
        this.y = y;
    }


    public void move()
    {
         x -= axd;
         
         y += ayd;

        if (x < 1)
        {
            x = 1;
        }

        if (y < 1)
        {
            y = 1;
        }
            
     }
    public int getX()
    {
        return x;
    }

    public int getY() 
    {
        return y;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void setVisible(Boolean visible)
    {
        this.visible = visible;
    }

    public Image getImage() 
    {
        return image;
    }

    public Rectangle getBounds()
    {
        return new Rectangle(x, y, width, height);
    }
}
