package snakegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Food {
    private Random random = new Random();
    
    private int horizontal = random.nextInt(381) + 1;
    private int vertical = random.nextInt(641) + 1;
    private final int FOODSIZE = 20;
     
    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.drawOval(horizontal, vertical, FOODSIZE, FOODSIZE);
    }
    // generates a new postion for the food
    protected void newPosition() { 
        int randomHorizontal = random.nextInt(381) + 1;
        int randomVertical = random.nextInt(641) + 1;
        
        while((randomHorizontal - 1) % 20 != 0) 
            randomHorizontal = random.nextInt(381) + 1;

        while((randomVertical - 1) % 20 != 0) 
            randomVertical = random.nextInt(641) + 1;

        this.horizontal = randomHorizontal;
        this.vertical = randomVertical;
        //if food apperas on a mine, get other position
        for(int i = SnakeGame.getMinesOnWorld() -1; i >= 0; i--) 
            if(this.getBounds().intersects(SnakeGame.mines[i].getBounds()) && 1 != 0) 
                newPosition();
        // if the food appears on the snake, get other position   
        for(int i = SnakeGame.getScore(); i >= 0; i--) 
            if(SnakeGame.snakeBody[i].getBounds().intersects(this.getBounds()))
                newPosition();
    }
    
    protected Rectangle getBounds() {
        return new Rectangle(horizontal, vertical, FOODSIZE, FOODSIZE);
    }
}
