
package snakegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Mine {
    private Random random = new Random();
    
    private int horizontal;
    private int vertical;
    private final int MINESIZE = 20;
    
    protected void paint(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.fillOval(horizontal, vertical, MINESIZE, MINESIZE);
    }
    
    protected boolean isGotCoortinates() {
        int liveProbability = random.nextInt(1000);
        if(liveProbability != 5) return false;
        
        int randomHorizontal = random.nextInt(381) + 1;
        int randomVertical = random.nextInt(641) + 1;
        
        while((randomHorizontal - 1) % 20 != 0) {
            randomHorizontal = random.nextInt(381) + 1;
        }
        
        while((randomVertical - 1) % 20 != 0) {
            randomVertical = random.nextInt(641) + 1;
        }
        
        this.horizontal = randomHorizontal;
        this.vertical = randomVertical;
        
        if(SnakeGame.food.getBounds().intersects(this.getBounds()))
            isGotCoortinates();
        
        return true;
    }
    
    protected Rectangle getBounds() {
        return new Rectangle(horizontal, vertical, MINESIZE, MINESIZE);
    }
}
