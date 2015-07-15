
package snakegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class SnakeTail {
    
    private int [] xPoints = new int [3];
    private int [] yPoints = new int [3];
    private int horizontal = 0;
    private int vertical = 0;
    private int SNAKESIZE = 20;
    private String orientation;
    
    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        
        if(orientation.compareTo("up") == 0)
            setUpPoints(g2d);
        if(orientation.compareTo("right") == 0)
            setRightPoints(g2d);
        if(orientation.compareTo("down") == 0)
            paintDownTail(g2d);
        if(orientation.compareTo("left") == 0)
            setLeftPoints(g2d);

        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.setColor(Color.black);
        g2d.drawPolygon(xPoints, yPoints, 3);
    }
    
    public void paintWhitoutBody(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        
        if(orientation.compareTo("up") == 0)
            setUpPoints(g2d);
        if(orientation.compareTo("right") == 0)
            setRightPoints(g2d);
        if(orientation.compareTo("down") == 0)
            paintDownTail(g2d);
        if(orientation.compareTo("left") == 0)
            setLeftPoints(g2d);
        
        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.setColor(Color.black);
        g2d.drawPolygon(xPoints, yPoints, 3);
    }

    private void setUpPoints(Graphics2D g2d) {
        xPoints[0] = horizontal;
        yPoints[0] = vertical;
        xPoints[1] = horizontal + 20;
        yPoints[1] = vertical;
        xPoints[2] = horizontal + 10;
        yPoints[2] = vertical + 20;
        
        for (int i = 0; i < yPoints.length; i++) {
            yPoints[i] = yPoints[i] + 20;
        }
    }

    private void setRightPoints(Graphics2D g2d) {
        xPoints[0] = horizontal + 20;
        yPoints[0] = vertical;
        xPoints[1] = horizontal + 20;
        yPoints[1] = vertical + 20;
        xPoints[2] = horizontal;
        yPoints[2] = vertical + 10;
        
        for (int i = 0; i < xPoints.length; i++) {
            xPoints[i] = xPoints[i] - 20;
        }
    }

    private void paintDownTail(Graphics2D g2d) {
        xPoints[0] = horizontal;
        yPoints[0] = vertical + 20;
        xPoints[1] = horizontal + 20;
        yPoints[1] = vertical + 20;
        xPoints[2] = horizontal + 10;
        yPoints[2] = vertical;
        
        for (int i = 0; i < yPoints.length; i++) {
            yPoints[i] = yPoints[i] - 20;
        }
    }

    private void setLeftPoints(Graphics2D g2d) {
        xPoints[0] = horizontal;
        yPoints[0] = vertical;
        xPoints[1] = horizontal;
        yPoints[1] = vertical + 20;
        xPoints[2] = horizontal + 20;
        yPoints[2] = vertical + 10;
        
        for (int i = 0; i < xPoints.length; i++) {
            xPoints[i] = xPoints[i] + 20;
        }
    }
    
    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }
    
    public void setVertical(int vertical) {
        this.vertical = vertical;
    }
    
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(horizontal, vertical, SNAKESIZE - 1, SNAKESIZE - 1);
    }
}
