
package snakegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class SnakeBody {
    private int horizontal;
    private int vertical;
    private boolean available = false;
    static private final int SNAKESIZE = 20;

        
    protected void paint(Graphics2D g2d) {
        g2d.setColor(Color.green);
        g2d.fillRect(horizontal, vertical, SNAKESIZE, SNAKESIZE);
    }
    
    protected boolean getAvailability() {
        return available;
    }
    
    protected void setAvailability(boolean availability) {
        available = availability;
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
     
    protected Rectangle getBounds() { 
        return new Rectangle(horizontal, vertical, SNAKESIZE, SNAKESIZE);
    }
}


