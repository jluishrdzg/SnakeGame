package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class World {
    private int [] xPoints = new int [3];
    private int [] yPoints = new int [3];
    
    public void paint(Graphics2D g2d) {
        paintPlayFrameZone(g2d);
        paintSpaceMessage(g2d);
        paintScoreOrWolrd(g2d);
        paintHighScoreOrRestFood(g2d);
        paintLivesOrSpeed(g2d);
        paintTypeOfGame(g2d);
        paintGameInstructions(g2d);
    }
    private void paintPlayFrameZone(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, 900, 700);
        g2d.setColor(Color.black);
        g2d.drawRect(0, 0, 401 , 661);
    }
    
    private void paintSpaceMessage(Graphics2D g2d) {
        if(!SnakeGame.isStarted) {
            g2d.setColor(Color.black);
            g2d.setFont(new Font("Verdana", Font.BOLD, 30));
            g2d.drawString("Press SPACE to", 77, 50); 
            g2d.drawString("start the game", 77, 100); 
        }
    }
    
    private void paintScoreOrWolrd(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        if(SnakeGame.isByScore) {
            g2d.drawString("SCORE" , 440, 50); 
            g2d.drawString(String.valueOf(SnakeGame.getScore()), 490, 100);
        } else {
            g2d.drawString("LEVEL" , 450, 50); 
            g2d.drawString(String.valueOf(SnakeGame.getCurrentWorld()), 490, 100);
        }
    }
    
    private void paintHighScoreOrRestFood(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Verdana", Font.BOLD, 15));
        if(SnakeGame.isByScore) {
            g2d.drawString("High score:" , 420, 150); 
            g2d.drawString(String.valueOf(SnakeGame.getHighScore()), 520, 150);
        } else {
           g2d.drawString("Rest of food:" , 420, 150); 
           g2d.drawString(String.valueOf(SnakeGame.getRestOfFood()), 535, 150);
        }

    }
    
    private void paintLivesOrSpeed(Graphics2D g2d) {
        g2d.setColor(Color.black);    
        g2d.setFont(new Font("Verdana", Font.BOLD, 15));
        
        if(SnakeGame.isByLevels)
        {
            g2d.drawString("Lives:" , 420, 208); 
            g2d.setColor(Color.red);  

            for(int i = SnakeGame.getLives(); i > 0 ; i--) {
                int margin = 20 * i;
                    fillxPoints(459 + margin, 467 + margin, 475 + margin);
                    fillyPoints(200, 210, 200);
                    g2d.fillPolygon(xPoints, yPoints, 3);
                    g2d.fillOval(459 + margin, 196, 8, 7);
                    g2d.fillOval(467 + margin, 196, 8, 7);
            }
        } else {
            int speed = SnakeGame.getCounterSpeed();
            g2d.drawString("Speed:" , 420, 208); 
            for(int i = 0; i < 6 ; i++) {
                int margin = 15 * i;
                 
                if(speed != 0) {
                    g2d.setColor(Color.black); 
                    speed--;
                } else { g2d.setColor(Color.gray); }
                g2d.fillRect(485 + margin, 195, 15, 15);
                g2d.setColor(Color.white); 
                fillxPoints(485 + margin, 493 + margin, 485 + margin);
                fillyPoints(195, 202, 210);
                g2d.fillPolygon(xPoints, yPoints, 3);
                fillxPoints(493 + margin, 500 + margin, 500 + margin);
                fillyPoints(195, 195, 202);
                g2d.fillPolygon(xPoints, yPoints, 3);
                fillxPoints(493 + margin, 500 + margin, 500 + margin);
                fillyPoints(210, 202, 210);
                g2d.fillPolygon(xPoints, yPoints, 3);
            }
        }
    }
    
    private void paintTypeOfGame(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Verdana", Font.BOLD, 10));
        g2d.drawString("Chose type of game", 440, 250);
        g2d.drawString("By Leves", 480, 275);
        g2d.drawString("By Score", 480, 300);
        
        g2d.fillRect(450, 264, 15, 15);
        if(SnakeGame.isByLevels) {
            g2d.setColor(Color.gray);
        } else {
            g2d.setColor(Color.white);
        }
        g2d.fillRect(452, 266, 11, 11);
        g2d.setColor(Color.black);
        g2d.fillRect(450, 288, 15, 15);
        if(SnakeGame.isByScore) {
            g2d.setColor(Color.gray);
        } else {
            g2d.setColor(Color.white);
        }
        g2d.fillRect(452, 290, 11, 11);
    }
    
    private void paintGameInstructions(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Verdana", Font.BOLD, 10));
        g2d.drawString("Use", 435, 600);
        g2d.drawString("to move", 512, 600);
        // Up arrow
        fillxPoints(480, 483, 486);
        fillyPoints(585, 580, 585);
        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.fillRect(482, 584, 2, 5);
        // Down arrow
        fillxPoints(480, 483, 486);
        fillyPoints(597, 602, 597);
        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.fillRect(482, 595, 2, 5);
        // Right arrow
        fillxPoints(498, 503, 498);
        fillyPoints(595, 598, 601);
        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.fillRect(493, 597, 5, 2);
        // Left arrow
        fillxPoints(470, 465, 470);
        fillyPoints(595, 598, 601);
        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.fillRect(470, 597, 5, 2);
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