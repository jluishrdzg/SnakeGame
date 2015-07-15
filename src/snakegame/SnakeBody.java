
package snakegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class SnakeBody {
    private int horizontal;
    private int vertical;
    private int position = 0;
    static private final int SNAKESIZE = 20;
    
    private boolean available = false;
    private String orientation;
  
    protected void paint(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillRect(horizontal, vertical, SNAKESIZE, SNAKESIZE);
        g2d.setColor(Color.black);
        g2d.drawRect(horizontal, vertical, SNAKESIZE, SNAKESIZE);
        
    }
    
    protected boolean getAvailability() {
        return available;
    }
    
    protected int getHorizontal() {
        return horizontal;
    }
    
    protected int getVertical() {
        return vertical;
    }
    
    protected void setHorizontal(int horizontal){
        this.horizontal = horizontal;
    }
    
    protected void setVertical(int vertical) {
        this.vertical = vertical;
    }
    
    protected void setPosition(int position) {
        this.position = position;
    }
    
    protected void setAvailability(boolean availability) {
        available = availability;
    }
    
    protected void setOrientation(String orientation) {
        this.orientation = orientation;
    }
    
    protected String getOrientation() {
        return orientation;
    }
     
    protected Rectangle getBounds() { 
        return new Rectangle(horizontal, vertical, SNAKESIZE, SNAKESIZE);
    }
}


