
package snakegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Lives {
    private Random random = new Random();
    private int horizontal;
    private int vertical;
    private final int LIVESIZE = 20;
    
    private int [] xPoints = new int [3];
    private int [] yPoints = new int [3];
    
    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.red);
        fillxPoints(horizontal - 2, horizontal + 10, horizontal + LIVESIZE+2);
        fillyPoints(vertical + 7, vertical + 20, vertical + 7);
        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.fillOval(horizontal - 2, vertical, 12, 12);
        g2d.fillOval(horizontal + 10, vertical, 12, 12);
        g2d.drawPolygon(xPoints, yPoints, 3);
        g2d.setColor(Color.black);
        g2d.drawPolygon(xPoints, yPoints, 3);
        g2d.drawOval(horizontal - 2, vertical, 12, 12);
        g2d.drawOval(horizontal + 10, vertical, 12, 12);
        g2d.setColor(Color.red);
        g2d.fillOval(horizontal + 1, vertical + 1, 9, 12);
        g2d.fillOval(horizontal + 11, vertical + 1 , 9, 12);
        g2d.fillRect(horizontal + 5, vertical + 5, 10, 9);
        g2d.fillRect(horizontal, vertical + 7, 2, 2);
        g2d.setColor(Color.red);
        g2d.fillRect(horizontal + 19, vertical + 7, 2, 2);
    }
    
    protected Rectangle getBounds() {
        return new Rectangle(horizontal, vertical, LIVESIZE, LIVESIZE);
    }

    protected void getCoordinates() {
        int liveProbability = random.nextInt(400);
        if(liveProbability != 5 || SnakeGame.getLives() >= 5) return;
    
        int randomHorizontal = random.nextInt(381) + 1;
        int randomVertical = random.nextInt(641) + 1;
        
        while((randomHorizontal - 1) % 20 != 0) 
            randomHorizontal = random.nextInt(381) + 1;
        while((randomVertical - 1) % 20 != 0) 
            randomVertical = random.nextInt(641) + 1;
        
        this.horizontal = randomHorizontal;
        this.vertical = randomVertical;
        
        for(int i = SnakeGame.getScore(); i >= 0; i--) 
            if(SnakeGame.snakeBody[i].getBounds().intersects(this.getBounds())) 
               getCoordinates();
        
        if(SnakeGame.food.getBounds().intersects(this.getBounds()));
            getCoordinates();
       
        SnakeGame.isLiveShown = true;
    }
    
    private void fillxPoints(int x1, int x2, int x3) {
        xPoints[0] = x1;
        xPoints[1] = x2;
        xPoints[2] = x3;
    }
    
    private void fillyPoints(int y1, int y2, int y3) {
        yPoints[0] = y1;
        yPoints[1] = y2;
        yPoints[2] = y3;
    } 
    
    protected void resetCoordinates() {
        horizontal = -20;
        vertical = - 20;
    }
}
