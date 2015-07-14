package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class World {
    public void paint(Graphics g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, 900, 700);
        g2d.setColor(Color.blue);
        g2d.drawRect(0, 0, 401 , 661);
    }
    
    public void paintSpaceMessage(Graphics g2d, boolean isStarted) {
        if(!isStarted) {
            g2d.setColor(Color.white);
            g2d.setFont(new Font("Verdana", Font.BOLD, 30));
            g2d.drawString("Press SPACE to", 77, 50); 
            g2d.drawString("start the game", 77, 100); 
        }
    }
    
}
