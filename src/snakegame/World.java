package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class World {
    private int [] xPoints = new int [3];
    private int [] yPoints = new int [3];
    
    public void paint(Graphics g2d) {
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, 900, 700);
        g2d.setColor(Color.black);
        g2d.drawRect(0, 0, 401 , 661);
        
        paintSpaceMessage(g2d);
        paintGameInstructions(g2d);
    }
    
    public void paintSpaceMessage(Graphics g2d) {
        if(!SnakeGame.isStarted) {
            g2d.setColor(Color.black);
            g2d.setFont(new Font("Verdana", Font.BOLD, 30));
            g2d.drawString("Press SPACE to", 77, 50); 
            g2d.drawString("start the game", 77, 100); 
        }
    }
    
    public void paintGameInstructions(Graphics g2d) {
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Verdana", Font.BOLD, 10));
        g2d.drawString("Use", 420, 600);
        g2d.drawString("to move", 497, 600);
        // Up arrow
        fillxPoints(465, 468, 471);
        fillyPoints(585, 580, 585);
        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.fillRect(467, 584, 2, 5);
        // Down arrow
        fillxPoints(465, 468, 471);
        fillyPoints(597, 602, 597);
        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.fillRect(467, 595, 2, 5);
        // Right arrow
        fillxPoints(483, 488, 483);
        fillyPoints(595, 598, 601);
        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.fillRect(478, 597, 5, 2);
        // Left arrow
        fillxPoints(455, 450, 455);
        fillyPoints(595, 598, 601);
        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.fillRect(455, 597, 5, 2);
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
    
}
