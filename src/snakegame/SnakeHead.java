package snakegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class SnakeHead {
     
    static private int horizontal = 201;
    static private int vertical = 301;
    
    static private final int SNAKESIZE = 20;
    
    public SnakeHead() {}
    
    public SnakeHead(int horizontal, int vertical) {
        SnakeHead.horizontal = horizontal;
        SnakeHead.vertical = vertical; 
    }
    
    protected void paint(Graphics2D g2d) {   
        g2d.setColor(Color.white);
        g2d.fillOval(horizontal, vertical, SNAKESIZE, SNAKESIZE);
        g2d.setColor(Color.black);
        g2d.drawOval(horizontal, vertical, SNAKESIZE, SNAKESIZE);
        
        if(!SnakeGame.isStarted) {
            g2d.setColor(Color.black);   
            g2d.fillRect(horizontal + 2, vertical + 6, 6, 2);
            g2d.fillRect(horizontal + 12, vertical + 6, 6, 2);
            
            g2d.setColor(Color.white);  
            g2d.fillRect(horizontal , vertical + 10, SNAKESIZE, SNAKESIZE / 2);
            g2d.setColor(Color.black);  
            g2d.drawLine(horizontal, vertical + 10, horizontal, vertical + 20);
            g2d.drawLine(horizontal + 20, vertical + 10, horizontal+ 20, vertical + 20);
            g2d.drawLine(horizontal, vertical + 20, horizontal+ 20, vertical + 20); 
        }
        
        if(SnakeGame.upState) paintUptHead(g2d);
        if(SnakeGame.rightState) paintRightHead(g2d);
        if(SnakeGame.downState) paintDownHead(g2d);
        if(SnakeGame.leftState) paintLeftHead(g2d);
    }
    
    protected void paintUptHead(Graphics2D g2d) {
        g2d.setColor(Color.white);  
        g2d.fillRect(horizontal , vertical + 10, SNAKESIZE, SNAKESIZE / 2);
        g2d.setColor(Color.black);   
        g2d.drawLine(horizontal, vertical + 10, horizontal, vertical + 20);
        g2d.drawLine(horizontal + 20, vertical + 10, horizontal+ 20, vertical + 20);
        g2d.drawLine(horizontal, vertical + 20, horizontal+ 20, vertical + 20);
        g2d.fillRect(horizontal + 5, vertical + 5, 3, 5);
        g2d.fillRect(horizontal + 11, vertical + 5, 3, 5);
        g2d.setColor(Color.red);
        g2d.fillRect(horizontal + 7, vertical - 5, 6, 5);
        g2d.fillRect(horizontal + 7, vertical - 7, 2, 2);
        g2d.fillRect(horizontal + 11, vertical - 7, 2, 2);
    }
    
    protected void paintRightHead(Graphics2D g2d) {
        g2d.setColor(Color.white);  
        g2d.fillRect(horizontal , vertical, SNAKESIZE / 2, SNAKESIZE);
        g2d.setColor(Color.black);
        g2d.drawLine(horizontal, vertical, horizontal + 10, vertical);
        g2d.drawLine(horizontal, vertical + 20, horizontal + 10, vertical + 20);
        g2d.drawLine(horizontal, vertical, horizontal, vertical + 20);
        g2d.fillRect(horizontal + 10, vertical + 5, 5, 3);
        g2d.fillRect(horizontal + 10, vertical + 11, 5, 3);
        g2d.setColor(Color.red);
        g2d.fillRect(horizontal + 20, vertical + 7, 5, 6);
        g2d.fillRect(horizontal + 25, vertical + 7, 2, 2);
        g2d.fillRect(horizontal + 25, vertical + 11, 2, 2);
    }
    
    protected void paintDownHead(Graphics2D g2d) {
        g2d.setColor(Color.white);  
        g2d.fillRect(horizontal , vertical, SNAKESIZE, SNAKESIZE / 2);
        g2d.setColor(Color.black);
        g2d.drawLine(horizontal, vertical, horizontal, vertical + 10);
        g2d.drawLine(horizontal + 20, vertical, horizontal + 20, vertical + 10);
        g2d.drawLine(horizontal, vertical, horizontal + 20, vertical);
        g2d.fillRect(horizontal + 5, vertical + 10, 3, 5);
        g2d.fillRect(horizontal + 11, vertical + 10, 3, 5);
        g2d.setColor(Color.red);
        g2d.fillRect(horizontal + 7, vertical + 20, 6, 5);
        g2d.fillRect(horizontal + 7, vertical + 25, 2, 2);
        g2d.fillRect(horizontal + 11, vertical + 25, 2, 2);
    }
    
    protected void paintLeftHead(Graphics2D g2d) {
        g2d.setColor(Color.white);  
        g2d.fillRect(horizontal + 10 , vertical, SNAKESIZE / 2, SNAKESIZE);
        g2d.setColor(Color.black);
        g2d.drawLine(horizontal + 10, vertical, horizontal + 20, vertical);
        g2d.drawLine(horizontal + 10, vertical + 20, horizontal + 20, vertical + 20);
        g2d.drawLine(horizontal + 20, vertical, horizontal + 20, vertical + 20);
        g2d.fillRect(horizontal + 5, vertical + 5, 5, 3);
        g2d.fillRect(horizontal + 5, vertical + 11, 5, 3);
        g2d.setColor(Color.red);
        g2d.fillRect(horizontal - 5, vertical + 7, 5, 6);
        g2d.fillRect(horizontal - 7, vertical + 7, 2, 2);
        g2d.fillRect(horizontal - 7, vertical + 11, 2, 2);
    }
    
    protected Rectangle getBounds() {
        return new Rectangle(horizontal, vertical, SNAKESIZE, SNAKESIZE);
    }
    
    protected void moveRight() {
        horizontal = horizontal + SNAKESIZE;
    }
      
     protected void moveDown() {
        vertical = vertical + SNAKESIZE;
    }
    
    protected void moveLeft() {
        horizontal = horizontal - SNAKESIZE;
    }
    
    protected void moveUp() {
        vertical = vertical - SNAKESIZE;
    }
    
    public int getHorizontal() {
        return horizontal;
    }
    
    public int getVertical() {
        return vertical;
    }
    
    public int getSnakeSize() {
        return SNAKESIZE;
    }
    
    public void resetPosition() {
        horizontal = 201;
        vertical = 301;
    }
}
